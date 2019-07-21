/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM.MRC.util;

import java.awt.Point;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ByteLookupTable;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.LookupTable;
import java.awt.image.WritableRaster;

/**
 *
 * @author Mario
 */
public class Util {
    
    public Util(){}
    
    
    public static LookupTable seno(double w) {
        double K = 255.0;
        byte lt[] = new byte[256];
        lt[0] = 0;
        for (int l = 1; l < 256; l++) {
            lt[l] = (byte) (K * Math.sin(Math.toRadians(w*l)));
        }
        ByteLookupTable slt = new ByteLookupTable(0, lt);
        return slt;
    }
    
    public static BufferedImage getBanda(BufferedImage src, int iBanda) {
        //Creamos el modelo de color de la nueva imagen basado en un espcio de color GRAY
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ComponentColorModel cm = new ComponentColorModel(cs, false, false,Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        //Creamos el nuevo raster a partir del raster de la imagen original
        int bandList[] = {iBanda};
        WritableRaster bandRaster = (WritableRaster) src.getRaster().createWritableChild(0, 0,src.getWidth(), src.getHeight(), 0, 0, bandList);
        //Creamos una nueva imagen que contiene como raster el correspondiente a la banda
        return new BufferedImage(cm, bandRaster, false, null);
    }
    
    public static BufferedImage copiaImagen(BufferedImage img){
        ColorModel cm = img.getColorModel();
        WritableRaster raster = img.copyData(null);
        boolean alfaPre = img.isAlphaPremultiplied();
        return new BufferedImage(cm,raster,alfaPre,null);
    }
    
    public static double distaciaAelipse(Point p, Point centro, double radio1 ,double radio2){
        // Si la distancia es menor que 1, el punto está dentro de la elipse
        return Math.pow(p.getX() - centro.getX(),2) / Math.pow(radio1, 2) + Math.pow(p.getY() - centro.getY(),2) / Math.pow(radio2, 2); 
    }
}
