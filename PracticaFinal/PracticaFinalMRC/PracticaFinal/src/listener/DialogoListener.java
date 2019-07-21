package listener;

import SM.MRC.Dialogo.Dialogo;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import practicafinal.VentanaPrincipal;

/**
 *
 * @author Mario
 */
public abstract class DialogoListener extends WindowAdapter{
    VentanaPrincipal vp;
    
    public DialogoListener(VentanaPrincipal vp){
        this.vp = vp;
    }
    
    @Override
    public void windowOpened(WindowEvent we) {
         ((Dialogo) we.getSource()).setAceptar(false);
    }
    
    @Override
    public void windowActivated(WindowEvent we) {
        windowOpened(we);
        if( vp.getVentanaInternaImagenActiva(false) == null){
            ((Dialogo) we.getSource()).dispose();
            JOptionPane.showMessageDialog(vp, "No hay ninguna ventana seleccionada o la ventana seleccionada no es una imagen", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
