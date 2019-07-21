package practicafinal;

import SM.MRC.Dialogo.DialogoSlider;
import java.awt.Frame;
import listener.DialogoSliderListener;

/**
 *
 * @author Mario
 */
public class DialogoSliderPracticaFinal extends DialogoSlider{

    public DialogoSliderPracticaFinal(Frame parent, int inicio, int min, int max, int majortrick, int minortrick, String name) {
        super(parent, inicio, min, max, majortrick, minortrick, name);
    }
    
    @Override
    protected void miSliderStateChanged(javax.swing.event.ChangeEvent evt) {
        DialogoSliderListener.cambiarSlider(getSlider(), (((VentanaPrincipal)getParent()).getVentanaInternaImagenActiva(false)));
    }
}
