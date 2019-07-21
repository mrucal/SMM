package listener;

import SM.MRC.Dialogo.DialogoContorno;
import SM.MRC.Grafico.Estilo.Contorno;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import practicafinal.VentanaInternaImagen;
import practicafinal.VentanaPrincipal;

/**
 *
 * @author Mario
 */
public class DialogoContornoListener extends DialogoListener{
    
    private int grosor;
    private boolean tipo;
    private boolean continua;
    
    public DialogoContornoListener(VentanaPrincipal vp){
        super(vp);
        
    }
    
    @Override
    public void windowOpened(WindowEvent we) {
        super.windowOpened(we);
        DialogoContorno dt = (DialogoContorno) we.getSource();
        VentanaInternaImagen vi = vp.getVentanaInternaImagenActiva(false);
        if(vi != null)
            if(vi.getPanel().getSeleccionado() == null){
                dt.getSpinnerGrosor().setValue(vi.getPanel().getEstilo().getContorno().getGrosor());
                this.grosor = vi.getPanel().getEstilo().getContorno().getGrosor();
                dt.getCheckboxTipo().setSelected(!vi.getPanel().getEstilo().getContorno().getTipo());
                this.tipo = vi.getPanel().getEstilo().getContorno().getTipo();
                continua = vi.getPanel().getEstilo().getContorno().getDiscontinuidad() == Contorno.CONTINUA;                
                dt.getRadioContinuo().setSelected(continua);
                dt.getRadioPunteado().setSelected(!continua);   
            }else{
                dt.getSpinnerGrosor().setValue(vi.getPanel().getSeleccionado().getGrosor());
                this.grosor = vi.getPanel().getSeleccionado().getGrosor();
                dt.getCheckboxTipo().setSelected(!vi.getPanel().getSeleccionado().getTipoContorno());
                this.tipo = vi.getPanel().getSeleccionado().getTipoContorno();
                continua = vi.getPanel().getSeleccionado().getDiscontinuidad() == Contorno.CONTINUA;
                dt.getRadioContinuo().setSelected(continua);
                dt.getRadioPunteado().setSelected(!continua);  
            }
    }
    
    @Override
    public void windowClosed(WindowEvent we) {
        DialogoContorno dt = (DialogoContorno) we.getSource();
        if(!dt.isAceptar()){
            VentanaInternaImagen vi = vp.getVentanaInternaImagenActiva(false);
            if(vi != null)
                if(vi.getPanel().getSeleccionado() == null){
                    vp.getSpinnerGrosor().setValue(grosor);
                    vi.getPanel().getEstilo().getContorno().setGrosor(grosor);
                    vi.getPanel().getEstilo().getContorno().setTipo(tipo);
                    if(continua)
                        vi.getPanel().getEstilo().getContorno().setDiscontinuidad(Contorno.CONTINUA);
                    else
                        vi.getPanel().getEstilo().getContorno().setDiscontinuidad(Contorno.PUNTEADA);
                }else{
                    vp.getSpinnerGrosor().setValue(grosor);
                    vi.getPanel().getSeleccionado().setGrosor(grosor);
                    vi.getPanel().getSeleccionado().setTipoContorno(tipo);
                    if(continua)
                        vi.getPanel().getSeleccionado().setDiscontinuidad(Contorno.CONTINUA);
                    else
                        vi.getPanel().getSeleccionado().setDiscontinuidad(Contorno.PUNTEADA);
                    vi.repaint();
                }
        }
    }
    
    public static void cambiarGrosor(JSpinner spinner, VentanaInternaImagen vi){
        if(vi != null){
            if (vi.getPanel().getSeleccionado() == null) {
                vi.getVentanaPrincipal().getSpinnerGrosor().setValue(spinner.getValue());
                vi.getPanel().getEstilo().getContorno().setGrosor((int) spinner.getValue());
            } else {
                vi.getPanel().getSeleccionado().setGrosor((int) spinner.getValue());
                vi.getVentanaPrincipal().getSpinnerGrosor().setValue(spinner.getValue());
            }
            vi.repaint();
        }
    }
    
    public static void cambiarTipo(JCheckBox chechbox, VentanaInternaImagen vi){
        if(vi != null){
            if (vi.getPanel().getSeleccionado() == null)
                vi.getPanel().getEstilo().getContorno().setTipo(!chechbox.isSelected());
            else
                vi.getPanel().getSeleccionado().setTipoContorno(!chechbox.isSelected());
            vi.repaint();
        }                
    }
     
    public static void cambiarDiscontinuidad(JRadioButton radio, VentanaInternaImagen vi){
        if(vi != null) {
            if (vi.getPanel().getSeleccionado() == null) {
                if (radio.isSelected()) 
                    vi.getPanel().getEstilo().getContorno().setDiscontinuidad(Contorno.CONTINUA);
                else 
                    vi.getPanel().getEstilo().getContorno().setDiscontinuidad(Contorno.PUNTEADA);
            } else {
                if (radio.isSelected()) 
                    vi.getPanel().getSeleccionado().setDiscontinuidad(Contorno.CONTINUA);
                else 
                    vi.getPanel().getSeleccionado().setDiscontinuidad(Contorno.PUNTEADA);
                vi.repaint();
            }
            vi.repaint();
        }
    }
}
