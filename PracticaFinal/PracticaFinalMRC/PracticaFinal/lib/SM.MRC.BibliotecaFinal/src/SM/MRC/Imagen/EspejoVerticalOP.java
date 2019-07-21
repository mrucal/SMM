package SM.MRC.Imagen;

import SM.MRC.Util.Util;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 * Clase que implementa la operación "espejo vertical" sobre una imagen.
 * 
 * La operación consiste en reflejar la imagen verticalmente. Clase propia de operación pixel a pixel.
 * 
 * @author Mario
 */
public class EspejoVerticalOP extends BufferedImageOpAdapter{
 
    
    public EspejoVerticalOP(){}

    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        if (src == null) {
            throw new NullPointerException("src image is null");
        }
        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }
        
        WritableRaster srcRaster = src.getRaster();        
        WritableRaster destRaster = dest.getRaster();
        
        BufferedImage aux = null;
        
        if(src.equals(dest)){
            aux = Util.copiaImagen(src);
            destRaster = aux.getRaster();
        }
        
        for (int x = 0; x < srcRaster.getWidth(); x++) {
            for (int y = 0; y < srcRaster.getHeight(); y++) {
                int[] pixel = null;
                pixel = srcRaster.getPixel(x, y, pixel);
                destRaster.setPixel(x, srcRaster.getHeight() - y - 1, pixel);       
            }
        }
        
        if(aux != null)
            dest = Util.copiaImagen(aux);
        return dest;
    }   
    
}
