package SM.MRC.Imagen;

import java.awt.Color;
import java.awt.image.BufferedImage;
import sm.image.BufferedImageOpAdapter;

/**
 * Clase que implementa la operación "tintado" a una imagen.
 * 
 * @author Mario
 */
public class TintadoOp extends BufferedImageOpAdapter{
    
    private float alpha, color[];
    
    /**
     * Constructor por defecto.
     * 
     * @param color Color con el que se va a tintar.
     * @param alpha Grado de aplicación (transparencia del color a tintar).
     */
    public TintadoOp(Color color, float alpha) {
        this.alpha = alpha;
        this.color = color.getColorComponents(null);
        for (int b = 0; b < this.color.length; b++) {
            this.color[b] *= 255;
        }
    }

    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }
        
        for (int x = 0; x < src.getRaster().getWidth(); x++) {
            for (int y = 0; y < src.getRaster().getHeight(); y++) {
                for (int band = 0; band < src.getRaster().getNumBands(); band++) {
                    int sample = src.getRaster().getSample(x, y, band);
                    sample = (int) (alpha * color[band] + (1.0f - alpha) * sample);
                    dest.getRaster().setSample(x, y, band, sample);
                }
            }
        }
        return dest;
    }

}
