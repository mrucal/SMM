package listener;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ImageIcon;
import practicafinal.VentanaPrincipal;

public class PlayerListener implements LineListener {

    VentanaPrincipal parent;
    
    public PlayerListener(VentanaPrincipal parent){
        this.parent = parent;
    }
    
    @Override
    public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.START) 
            if(parent.getPlayer()!=null)
                parent.getBotonPlay().setIcon(new ImageIcon(getClass().getResource("/iconos/barraSonido/pausa24x24.png")));
        if (event.getType() == LineEvent.Type.STOP) {
            parent.getBotonPlay().setIcon(new ImageIcon(getClass().getResource("/iconos/barraSonido/play24x24.png")));
            if(!parent.getPlayer().isPause()){
                parent.getPlayer().reiniciar();
                parent.stopCronometro(parent.getLabelTiempo());
            }
        }
    }

}