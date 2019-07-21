package practicafinal;

import SM.MRC.Util.TipoRecurso;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class VentanaInternaCamara extends VentanaInterna {

    private Webcam camara = null;
    
    private VentanaInternaCamara(VentanaPrincipal parent) {
        super(parent);
        tipo = TipoRecurso.CAMARA;
        initComponents();
        camara = Webcam.getDefault();
        if (camara != null) {
            Dimension resoluciones[] = camara.getViewSizes();
            Dimension maxRes = resoluciones[resoluciones.length-1];
            camara.setViewSize(maxRes);
            try{
                WebcamPanel areaVisual = new WebcamPanel(camara);
                if (areaVisual != null) {
                    getContentPane().add(areaVisual, BorderLayout.CENTER);
                    pack();
                    this.setTitle("WebCam");
                    nCapturas = 0;
                }
            }catch(com.github.sarxos.webcam.WebcamLockException e){
                JOptionPane.showMessageDialog(parent, "La webcam está siendo utilizada por otro programa.", "Error", JOptionPane.ERROR_MESSAGE);
                this.dispose();
            }
        }
    }
    
    public static VentanaInternaCamara getInstance(VentanaPrincipal parent) {
        VentanaInternaCamara v = new VentanaInternaCamara(parent);
        return (v.camara != null ? v : null);
    }
    
    public void capturarPantalla(){
        VentanaInternaImagen vi = null;
        if(camara!=null){
            nCapturas++;
            vi = new VentanaInternaImagen(parent);
            BufferedImage img = camara.getImage();
            vi.getPanel().setImagen(img);
            vi.getPanel().setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
            vi.setTitle("Captura WebCam ["+nCapturas+"]");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        if(camara!=null)
            camara.close();
    }//GEN-LAST:event_formInternalFrameClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
