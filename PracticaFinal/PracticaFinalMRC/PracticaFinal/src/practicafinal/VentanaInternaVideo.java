package practicafinal;

import SM.MRC.Util.Cronometro;
import SM.MRC.Util.TipoRecurso;
import SM.MRC.Util.Util;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import listener.VideoListener;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 *
 * @author Mario
 */
public class VentanaInternaVideo extends VentanaInterna {

    private EmbeddedMediaPlayer vlcplayer = null;
    private File fMedia;    

    private VentanaInternaVideo(VentanaPrincipal parent, File f) {
        super(parent);
        tipo = TipoRecurso.VIDEO;
        initComponents();
        fMedia = f;
        EmbeddedMediaPlayerComponent aVisual = new EmbeddedMediaPlayerComponent();
        getContentPane().add(aVisual, java.awt.BorderLayout.CENTER);
        vlcplayer = aVisual.getMediaPlayer();
        vlcplayer.addMediaPlayerEventListener(new VideoListener(parent));
        this.setTitle(fMedia.getName());
        parent.getCronometroTiempo().setSegundos(Math.toIntExact(0));
        
    }

    public static VentanaInternaVideo getInstance(VentanaPrincipal parent,File f) {
        VentanaInternaVideo v = new VentanaInternaVideo(parent, f);
        return (v.vlcplayer != null ? v : null);
    }
    
    public void play() {
        if (vlcplayer != null) {
            if (vlcplayer.isPlayable()) 
                //Si se estaba reproduciendo
                vlcplayer.play();
            else 
                vlcplayer.playMedia(fMedia.getAbsolutePath());
        }
    }
    
    public void stop() {
        if (vlcplayer != null) 
            vlcplayer.stop();
    }
    
    public void pause(){
        if(vlcplayer != null)
            vlcplayer.pause();
    }
    
    public boolean isPlaying(){
        return vlcplayer!=null && vlcplayer.isPlaying();
    }
    
    public int getDuracion(){
        if(vlcplayer!=null)
            return Math.toIntExact(vlcplayer.getLength()/1000);
        return 0;
    }
    
    public void capturarPantalla(){
        VentanaInternaImagen vi = null;
        if(vlcplayer!=null){
            nCapturas++;
            BufferedImage img = vlcplayer.getSnapshot();
            if(img != null){
                vi = new VentanaInternaImagen(parent);
                vi.getPanel().setImagen(img);
                vi.getPanel().setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
                vi.setTitle("Captura "+fMedia.getName()+" ["+nCapturas+"]");
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameDeactivated(evt);
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
        if(vlcplayer != null)
            vlcplayer.stop();
    }//GEN-LAST:event_formInternalFrameClosed

    private void formInternalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeactivated
        if(vlcplayer != null){
            if(vlcplayer.isPlaying()){
                vlcplayer.pause();
                parent.pauseCronometro();
            }
        }
    }//GEN-LAST:event_formInternalFrameDeactivated

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        
        if(parent.getPlayer() != null){
            parent.getLabelTiempo().setText(Util.getTiempo(Math.toIntExact(vlcplayer.getTime()/1000)));
            Cronometro.setSegundos(Math.toIntExact(vlcplayer.getTime()/1000));
            parent.getLabelDuracion().setText(Util.getTiempo(getDuracion()));
            parent.getProgresoReproductor().setMaximum(getDuracion());
        }
    }//GEN-LAST:event_formInternalFrameActivated


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
