package listener;

import SM.MRC.Dialogo.DialogoOrdenacion;
import SM.MRC.Grafico.Forma.Forma;
import SM.MRC.Util.Util;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import practicafinal.VentanaInternaImagen;
import practicafinal.VentanaPrincipal;

/**
 *
 * @author Mario
 */
public class DialogoOrdenacionListener extends DialogoListener {

    public DialogoOrdenacionListener(VentanaPrincipal vp) {
        super(vp);
    }

    @Override
    public void windowActivated(WindowEvent we) {
        VentanaInternaImagen vi =  vp.getVentanaInternaImagenActiva(false);
        if (vi != null){
            if (vi.getPanel().getSeleccionado() == null)
                Util.setEnabledGroup(((DialogoOrdenacion) we.getSource()).getGrupoOrdenacion(),false);
            else
                if(vi.getPanel().getFormas().size() < 2)
                    Util.setEnabledGroup(((DialogoOrdenacion) we.getSource()).getGrupoOrdenacion(),false);
        }else
            Util.setEnabledGroup(((DialogoOrdenacion) we.getSource()).getGrupoOrdenacion(),false);
    }

    @Override
    public void windowClosed(WindowEvent we) {
        DialogoOrdenacion d_o = (DialogoOrdenacion) we.getSource();
        VentanaInternaImagen vi = vp.getVentanaInternaImagenActiva(false);
        if (d_o.isAceptar()) 
            if (vi != null) {
                Forma seleccionado = vi.getPanel().getSeleccionado();
                ArrayList<Forma> formas = (ArrayList) vi.getPanel().getFormas();
                int i = formas.indexOf(seleccionado);
                if (seleccionado != null) 
                    if (formas.size() >= 2) 
                        switch (d_o.getOrdenacion()) {
                            case ENVIAR_FONDO:
                                vi.getPanel().cambiarOrden(i, 0);
                                break;
                            case ENVIAR_ATRAS:
                                if (i > 0) 
                                    vi.getPanel().cambiarOrden(i, i - 1);
                                break;
                            case TRAER_ADELANTE:
                                if (i < formas.size() - 1) {
                                    vi.getPanel().cambiarOrden(i, i + 1);
                                }
                                break;
                            case TRAER_FRENTE:
                                vi.getPanel().cambiarOrden(i, formas.size() - 1);
                                break;
                        }
            }
    }
}
