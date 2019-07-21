package listener;

import SM.MRC.Util.Util;
import javax.swing.ImageIcon;
import practicafinal.VentanaPrincipal;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

/**
 *
 * @author Mario
 */
public class VideoListener extends MediaPlayerEventAdapter{
    
    VentanaPrincipal vp;
    
    public VideoListener(VentanaPrincipal vp){
        super();
        this.vp = vp;
    }
    
    @Override
    public void playing(MediaPlayer mediaPlayer){
        vp.getBotonPlay().setIcon(new ImageIcon(getClass().getResource("/iconos/barraSonido/pausa24x24.png")));
    }
    
    @Override
    public void paused(MediaPlayer mediaPlayer){
        vp.getBotonPlay().setIcon(new ImageIcon(getClass().getResource("/iconos/barraSonido/play24x24.png")));
    }
    
    @Override
    public void stopped(MediaPlayer mediaPlayer){
        vp.getBotonPlay().setIcon(new ImageIcon(getClass().getResource("/iconos/barraSonido/play24x24.png")));
    }
    
    @Override
    public void finished(MediaPlayer mediaPlayer){
        vp.getBotonPlay().setIcon(new ImageIcon(getClass().getResource("/iconos/barraSonido/play24x24.png")));
        vp.stopCronometro(vp.getLabelTiempo());
    }
    
    @Override
    public void lengthChanged(MediaPlayer mediaPlayer,long newLength){
        vp.getLabelDuracion().setText(Util.getTiempo(Math.toIntExact(newLength/1000)));
        vp.getProgresoReproductor().setMaximum(Math.toIntExact(newLength/1000));
    }
}
