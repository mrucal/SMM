package listener;

import SM.MRC.Dialogo.DialogoSlider;
import SM.MRC.Imagen.TintadoOp;
import SM.MRC.Imagen.ViñetaOp;
import SM.MRC.Util.Util;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import javax.swing.JSlider;
import practicafinal.VentanaInternaImagen;
import practicafinal.VentanaPrincipal;

/**
 *
 * @author Mario
 */
public class DialogoSliderListener extends DialogoListener{

    private int transparencia;
    private static BufferedImage imgParentOriginal;
    private static BufferedImage imgOriginal;
    
    public DialogoSliderListener(VentanaPrincipal vp){
        super(vp);
        imgOriginal = null;
        imgParentOriginal = null;
    }
    
    @Override
    public void windowOpened(WindowEvent we){
        super.windowOpened(we);
        DialogoSlider ds = (DialogoSlider) we.getSource();
        JSlider slider = ds.getSlider();
        VentanaInternaImagen vi = vp.getVentanaInternaImagenActiva(false);
        if(vi!=null)
            if(slider.getName().equals("Rotación") && vi.getVentanaInternaImagenParent() != null){
                slider.setEnabled(false);
                ds.getBotonAceptar().setEnabled(false);
            }
        ds.setTitle(slider.getName());
        
    }
    
    @Override
    public void windowActivated(WindowEvent we) {
        super.windowActivated(we);
        DialogoSlider ds = (DialogoSlider) we.getSource();
        JSlider slider = ds.getSlider();
        VentanaInternaImagen vi = vp.getVentanaInternaImagenActiva(false);
        if(vi != null){
            switch(slider.getName()){
                    case "Transparencia":
                        slider.setMajorTickSpacing(50);
                        slider.setMinorTickSpacing(25);
                        if(vi.getPanel().getSeleccionado() == null){
                            ds.getSlider().setValue((int)(vi.getPanel().getEstilo().getTransparencia().getGradoTransparencia()*100));
                            transparencia = (int)(vi.getPanel().getEstilo().getTransparencia().getGradoTransparencia()*100);
                        }else{
                            ds.getSlider().setValue((int)(vi.getPanel().getSeleccionado().getGradoTransparencia()*100));
                            transparencia = (int)(vi.getPanel().getSeleccionado().getGradoTransparencia()*100);
                        }
                        break;
                    case "Brillo":
                    case "Rotación":
                        slider.setPaintLabels(false);
                        slider.setValue(0);
                        imgOriginal = null;
                        imgParentOriginal = null;
                        break;
                    case "Tintado":
                    case "Viñeta":
                        slider.setValue(0);
                        slider.setPaintLabels(true);
                        imgOriginal = null;
                        imgParentOriginal = null;
                        if(slider.getName().equals("Viñeta"))
                            cambiarSlider(slider,vi);
            }
            vi.repaint();
        }
    }
    
    
    @Override
    public void windowClosed(WindowEvent we) {
        DialogoSlider ds = (DialogoSlider) we.getSource();
        JSlider slider = ds.getSlider();
        VentanaInternaImagen vi = vp.getVentanaInternaImagenActiva(false);
        if(!ds.isAceptar()){
            if(vi!=null){
                switch (slider.getName()) {
                    case "Transparencia":
                        if (vi.getPanel().getSeleccionado() == null) {
                            vi.getPanel().getEstilo().getTransparencia().setGradoTransparencia(transparencia / 100.0f);
                        } else {
                            vi.getPanel().getSeleccionado().setGradoTransparencia(transparencia / 100.0f);
                        }
                        vi.repaint();
                        break;
                    case "Brillo":
                    case "Rotación":
                    case "Viñeta":
                    case "Tintado":
                        if (imgOriginal != null){ 
                            vi.getPanel().setImagen(imgOriginal);
                            if(vi.getVentanaInternaImagenParent() != null){
                                vi.getVentanaInternaImagenParent().getPanel().setImagen(imgParentOriginal);
                                vi.getVentanaInternaImagenParent().repaint();
                                vi.getPanel().setImagen(Util.getBanda(imgParentOriginal, vi.getBanda()));
                            }
                            vi.repaint();
                        }
                }
            }
        }else{
            imgOriginal = null;
            imgParentOriginal = null;
        }
    }
    
    public static void cambiarSlider(JSlider slider, VentanaInternaImagen vi) {
        if (vi != null) {
            switch (slider.getName()) {
                case "Transparencia":
                    if (vi.getPanel().getSeleccionado() == null) 
                        vi.getPanel().getEstilo().getTransparencia().setGradoTransparencia(slider.getValue() / 100.0f);
                    else 
                        vi.getPanel().getSeleccionado().setGradoTransparencia(slider.getValue() / 100.0f);
                    vi.repaint();
                    break;
                case "Brillo":
                    BufferedImage imgSource = vi.getPanel().getImagen();
                    if (imgSource != null) {
                        try {
                            RescaleOp rop = new RescaleOp(1.0F, slider.getValue(), null);
                            if (imgOriginal == null) {
                                imgOriginal = Util.copiaImagen(vi.getPanel().getImagen());
                                if(vi.getVentanaInternaImagenParent() != null)
                                    imgParentOriginal = Util.copiaImagen(vi.getVentanaInternaImagenParent().getPanel().getImagen());
                            }
                            if (imgOriginal.getRaster().getNumBands() == 4) {
                                for (int i = 0; i < 3; i++) 
                                    rop.filter(Util.getBanda(imgOriginal, i), Util.getBanda(vi.getPanel().getImagen(), i));
                            } else 
                                rop.filter(imgOriginal, vi.getPanel().getImagen());
                            vi.getVentanaPrincipal().repaint();
                        } catch (IllegalArgumentException e) {
                            System.err.println(e.getLocalizedMessage());
                        }
                    }
                    break;
                case "Rotación":
                    imgSource = vi.getPanel().getImagen();
                    if (imgSource != null) {
                        try {
                            if (imgOriginal == null) {
                                imgOriginal = Util.copiaImagen(vi.getPanel().getImagen());
                                if(vi.getVentanaInternaImagenParent() != null)
                                    imgParentOriginal = Util.copiaImagen(vi.getVentanaInternaImagenParent().getPanel().getImagen());
                            }
                            AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(slider.getValue()), imgOriginal.getWidth() / 2, imgOriginal.getHeight() / 2);
                            AffineTransformOp atop;
                            atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                            BufferedImage imgdest = atop.filter(imgOriginal, null);
                            vi.getPanel().setImagen(imgdest);
                            vi.getPanel().setBackground(new java.awt.Color(204, 204, 204));
                            vi.getPanel().setPreferredSize(new Dimension(imgdest.getWidth(), imgdest.getHeight()));
                            vi.repaint();
                        } catch (IllegalArgumentException e) {
                            System.err.println(e.getLocalizedMessage());
                        }
                    }

                    break;
                case "Viñeta":
                    imgSource = vi.getPanel().getImagen();
                    if (imgSource != null) {
                        try {
                            if (imgOriginal == null) {
                                imgOriginal = Util.copiaImagen(imgSource);
                                if(vi.getVentanaInternaImagenParent() != null)
                                    imgParentOriginal = Util.copiaImagen(vi.getVentanaInternaImagenParent().getPanel().getImagen());
                            }
                            ViñetaOp viñetaop = new ViñetaOp(0.8,slider.getValue() / 100.0f);
                            viñetaop.filter(imgOriginal, vi.getPanel().getImagen());
                            vi.getVentanaPrincipal().repaint();
                        } catch (Exception e) {
                            System.err.println(e.getLocalizedMessage());
                        }
                    }
                    break;
                case "Tintado":
                    imgSource = vi.getPanel().getImagen();
                    if (imgSource != null) {
                        try {
                            if (imgOriginal == null) {
                                imgOriginal = Util.copiaImagen(imgSource);
                                if(vi.getVentanaInternaImagenParent() != null)
                                    imgParentOriginal = Util.copiaImagen(vi.getVentanaInternaImagenParent().getPanel().getImagen());
                            }
                            TintadoOp tintadoop = new TintadoOp(vi.getPanel().getEstilo().getColorFondo(),slider.getValue() / 100.0f);
                            tintadoop.filter(imgOriginal, vi.getPanel().getImagen());
                            vi.getVentanaPrincipal().repaint();
                        } catch (Exception e) {
                            System.err.println(e.getLocalizedMessage());
                        }
                    }
                    break;
            }
            vi.repaint();
        }
    }
}
