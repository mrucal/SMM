/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM.MRC.imagen;

import SM.MRC.util.Util;
import java.awt.Point;
import java.awt.image.BufferedImage;
import sm.image.BufferedImageOpAdapter;

/**
 *
 * @author Mario
 */
public class ViñetaOp extends BufferedImageOpAdapter{
    
    // radio: valor entre 0 y 1 que indica el porcentaje de altura y anchura que 
    //        se toman como radios de la elipse
    // k :    valor entre 0 y 1 que indica como de oscuro será la viñeta (0 normal, 1 oscuro)
    private double radio, k;
    
    public ViñetaOp(){
        this.radio = 0.8;
        this.k = 0.1;
    }
    
    public ViñetaOp(double radio){
        this.radio = radio;
        this.k = 0.1;
    }
    
    public ViñetaOp(double radio, double k){
        this.radio = radio;
        this.k = k;
    }

    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        if (src == null) {
            throw new NullPointerException("src image is null");
        }
        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }
        double radio1 = (src.getWidth()*radio)/2;
        double radio2 = (src.getHeight()*radio)/2;
        Point centro = new Point(src.getWidth()/2, src.getHeight()/2);
        int sample = 0;
        double distancia;
        for (int x = 0; x < src.getWidth(); x++) 
            for (int y = 0; y < src.getHeight(); y++) 
                for (int b = 0; b < src.getRaster().getNumBands(); b++){
                    distancia = Util.distaciaAelipse(new Point(x,y), centro , radio1, radio2);
                    if (b<3 && distancia > 1 ){
                        sample = src.getRaster().getSample(x, y, b);
                        sample = (int)(sample /((distancia)+(distancia/2*k)));
                        src.getRaster().setSample(x, y, b, sample);
                    }
                }
        return dest;
    }
    
    
}
