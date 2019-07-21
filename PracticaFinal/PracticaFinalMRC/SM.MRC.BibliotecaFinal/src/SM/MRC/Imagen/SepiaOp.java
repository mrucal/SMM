package SM.MRC.Imagen;


import java.awt.image.BufferedImage;
import sm.image.BufferedImageOpAdapter;

/**
 * Clase que implementa el operador sepia sobre una imagen.
 * 
 * La operación consiste en aplicarle un efecto "sepia" a la imagen.
 * 
 * @author Mario
 */
public class SepiaOp extends BufferedImageOpAdapter {

    public SepiaOp () {}
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        if (src == null) {
            throw new NullPointerException("src image is null");
        }
        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }
        int [] pixel = null;
        int [] nuevo_pixel = new int[3];
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                pixel = src.getRaster().getPixel(x, y, pixel);
		nuevo_pixel[0] = Math.min(255, (int)(0.393*pixel[0]) + (int)(0.769*pixel[1]) + (int)(0.189*pixel[2]));
                nuevo_pixel[1] = Math.min(255, (int)(0.349*pixel[0]) + (int)(0.686*pixel[1]) + (int)(0.468*pixel[2]));
                nuevo_pixel[2] = Math.min(255, (int)(0.272*pixel[0]) + (int)(0.534*pixel[1]) + (int)(0.131*pixel[2]));
                dest.getRaster().setPixel(x, y, nuevo_pixel);
            }
        }
        return dest;
    }
}
