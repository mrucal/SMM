package listener;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ImageIcon;
import practicafinal.VentanaPrincipal;

public class RecorderListener implements LineListener {

    VentanaPrincipal parent;
    
    public RecorderListener(VentanaPrincipal parent){
        this.parent = parent;
    }
    
    @Override
    public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.START) 
            parent.getBotonRecord().setIcon(new ImageIcon(getClass().getResource("/iconos/barraSonido/stopRecord24x24.png")));
        if (event.getType() == LineEvent.Type.STOP) {
            parent.getBotonRecord().setIcon(new ImageIcon(getClass().getResource("/iconos/barraSonido/record24x24.png")));
            parent.terminarRecord();
        }
    }

}