package listener;

import SM.MRC.Dialogo.DialogoRelleno;
import SM.MRC.Grafico.Forma.FormaRelleno;
import SM.MRC.Util.TipoRelleno;
import SM.MRC.Util.Util;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JToggleButton;
import practicafinal.VentanaInternaImagen;
import practicafinal.VentanaPrincipal;

/**
 *
 * @author Mario
 */
public class DialogoRellenoListener extends DialogoListener{
    
    public DialogoRellenoListener(VentanaPrincipal vp){
        super(vp);
    }
    
    @Override
    public void windowOpened(WindowEvent we) {
        super.windowOpened(we);
        DialogoRelleno dr = (DialogoRelleno) we.getSource();
        VentanaInternaImagen vi = vp.getVentanaInternaImagenActiva(false);
        if(vi != null){
            if(Util.HerramientasFormaRelleno.contains(vi.getPanel().getHerramienta())){
                Util.setEnabledGroup(dr.getGrupoRelleno(), true);
                dr.getGrupoRelleno().clearSelection();
                JToggleButton jb = new JToggleButton();
                for (Enumeration<AbstractButton> h = dr.getGrupoRelleno().getElements(); h.hasMoreElements();) {
                    jb = (JToggleButton) h.nextElement();
                    if (vi.getPanel().getSeleccionado() == null) {
                        if (TipoRelleno.valueOf(jb.getName()) == vi.getPanel().getEstilo().getRelleno().getTipoRelleno()) {
                            vi.getPanel().getEstilo().getRelleno().setColorFondo(vi.getPanel().getEstilo().getColorFondo());
                            vi.getPanel().getEstilo().getRelleno().setColorFrente(vi.getPanel().getEstilo().getColorFrente());
                            break;
                        }
                    } else {
                        if (vi.getPanel().getSeleccionado().isRellenable()) {
                            if (TipoRelleno.valueOf(jb.getName()) == ((FormaRelleno) (vi.getPanel().getSeleccionado())).getTipoRellenoEnum()) {
                                ((FormaRelleno) (vi.getPanel().getSeleccionado())).setColorFondoRelleno(vi.getPanel().getEstilo().getColorFondo());
                                ((FormaRelleno) (vi.getPanel().getSeleccionado())).setColorFrenteRelleno(vi.getPanel().getEstilo().getColorFrente());
                                vi.getPanel().getEstilo().getRelleno().setColorFrente(vi.getPanel().getEstilo().getColorFrente());
                                break;
                            }
                        } else 
                            Util.setEnabledGroup(dr.getGrupoRelleno(), false);
                    }
                }
                jb.setSelected(true);
            } else 
                Util.setEnabledGroup(dr.getGrupoRelleno(), false);
        }
    }
    
    @Override
    public void windowClosed(WindowEvent we) {
        DialogoRelleno dr = (DialogoRelleno) we.getSource();
        if(dr.isAceptar()){
            VentanaInternaImagen vi = vp.getVentanaInternaImagenActiva(false);
            if(vi != null)
                if(vi.getPanel().getSeleccionado() == null){
                    vi.getPanel().getEstilo().getRelleno().setTipo(dr.getTipo());
                    vi.getPanel().getEstilo().getRelleno().setTipoDegradado(dr.getTipo_degradado());
                    vi.getPanel().getEstilo().getRelleno().setColorFondo(vi.getPanel().getEstilo().getColorFondo());
                    vi.getPanel().getEstilo().getRelleno().setColorFrente(vi.getPanel().getEstilo().getColorFrente());
                    vp.getBotonRelleno().setIcon(vi.getIconoRelleno( vi.getPanel().getEstilo().getRelleno().getTipoRelleno(),0));
                }else{
                    FormaRelleno seleccionado = ((FormaRelleno)(vi.getPanel().getSeleccionado()));
                    seleccionado.setTipoRelleno(dr.getTipo());
                    seleccionado.setTipoDegradado(dr.getTipo_degradado());
                    seleccionado.setColorContorno(vp.getPanelPaletaColores().getBotonContorno().getBackground());
                    seleccionado.setColorFondo(vp.getPanelPaletaColores().getBotonFondo().getBackground());
                    seleccionado.setColorFrente(vp.getPanelPaletaColores().getBotonFrente().getBackground());
                    vp.getBotonRelleno().setIcon(vi.getIconoRelleno( seleccionado.getTipoRellenoEnum(),0));
                    vi.repaint();
                }
        }
    }
}
