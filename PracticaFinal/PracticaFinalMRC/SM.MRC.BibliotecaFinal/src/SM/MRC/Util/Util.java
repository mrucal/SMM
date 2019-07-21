package SM.MRC.Util;

import java.awt.Container;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;

/**
 * Clase que contiene métodos y vaiables útiles para el desarrollo de la bibloteca y posibles proyectos.
 * 
 * @author Mario
 */
public class Util {
    
    public static ArrayList<Herramienta> HerramientasForma = new ArrayList(Arrays.asList(Herramienta.Línea, Herramienta.Editar));
    public static ArrayList<Herramienta> HerramientasFormaRelleno = new ArrayList(Arrays.asList(Herramienta.Rectángulo, Herramienta.RectánguloRedondeado, Herramienta.Elipse, Herramienta.Arco, Herramienta.CurvaCuadrada,Herramienta.TrazoLibre,Herramienta.M, Herramienta.SelloM, Herramienta.Editar,Herramienta.Pegar));
    
    /**
     * Coloca un diálogo en el centro de un frame.
     * 
     * @param jframe Frame que contiene al dialogo.
     * @param dialogo Dialogo que se va a mover.
     */
    public static void mostrarDialogoCentro(JFrame jframe, Container dialogo){
        double x = jframe.getWidth()/2 - dialogo.getWidth()/2 + jframe.getLocation().getX();
        double y = jframe.getHeight()/2 - dialogo.getHeight()/2 + jframe.getLocation().getY();
        dialogo.setLocation(new Point((int)x,(int)y));
    }    
    
    /**
     * Habilita(o deshabilita) todos los botones de un grupo de botones.
     * 
     * @param bg Grupo de botones.
     * @param enabled True si se habilitan, false si se deshabilita.
     */
    public static void setEnabledGroup(ButtonGroup bg, boolean enabled) {
        Enumeration<AbstractButton> enumeration = bg.getElements();
        while (enumeration.hasMoreElements()) {
            enumeration.nextElement().setEnabled(enabled);
        }
    }
    
    /**
     * Devuelve la función que implementa la operación seno en una imagen.
     * 
     * @param w Parámetro de la función seno.
     * @return LookupTable de la función.
     */
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
    
    /**
     * Devuelve la función que implementa la operación de contraste mediante lookup.
     * 
     * @return LookupTable de la función.
     */
    public static LookupTable contrasteLookup() {
        double K = (255/2);
        byte lt[] = new byte[256];
        lt[0] = 0;
        for (int i = 1; i < 256; i++) {
            double sin = Math.sin(Math.toRadians(((180.0/255)*i)-(180/2)));
            lt[i] = (byte) ((K * (sin +1)));
        }
        ByteLookupTable slt = new ByteLookupTable(0, lt);
        return slt;
    }
    
    /**
     * Obtiene la banda i de una imagen.
     * 
     * @param src Imagen de entrada.
     * @param iBanda Banda que se va a obtener.
     * @return Imagen de salida correspondiente a la banda i     de la imagen de entrada.
     */
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
    
    /**
     * Copia de una imagen.
     * 
     * @param img Imagen de entrada.
     * @return Imagen de salida.
     */
    public static BufferedImage copiaImagen(BufferedImage img){
        ColorModel cm = img.getColorModel();
        WritableRaster raster = img.copyData(null);
        boolean alfaPre = img.isAlphaPremultiplied();
        return new BufferedImage(cm,raster,alfaPre,null);
    }
    
    /**
     * Devuelve la distancia de un punto a una elipse.
     * 
     * @param p Punto.
     * @param centro Centro de la elipse.
     * @param radio1 Radio del ancho de la elipse.
     * @param radio2 Radio del alto de la elipse.
     * @return Distancia del punto a la elipse.
     */
    public static double distaciaAelipse(Point p, Point centro, double radio1 ,double radio2){
        // Si la distancia es menor que 1, el punto está dentro de la elipse
        return Math.pow(p.getX() - centro.getX(),2) / Math.pow(radio1, 2) + Math.pow(p.getY() - centro.getY(),2) / Math.pow(radio2, 2); 
    }
    
    /**
     * Devuelve el tiempo en formato "hh:mm:ss" a partir de un tiempo en segundos.
     * @param segundos Tiempo en segundos.
     * @return Tiempo en formato "hh:mm:ss"
     */
    public static String getTiempo(int segundos){
        int minutos = 0;
        int horas = 0;
        if(segundos > 60){
            minutos = segundos / 60;
            segundos = segundos % 60;
        }
        if(minutos > 60){
            horas = minutos / 60;
            minutos = minutos % 60;
        }
        String shoras = Integer.toString(horas), sminutos = Integer.toString(minutos), ssegundos = Integer.toString(segundos);
        if(horas<10)
            shoras = "0"+shoras;
        if(minutos<10)
            sminutos = "0"+sminutos;
        if(segundos<10)
            ssegundos = "0"+ssegundos;
        return shoras + ":" + sminutos + ":" + ssegundos;
    }
    
}
