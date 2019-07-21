package listener;

import SM.MRC.Dialogo.DialogoContraste;
import SM.MRC.Util.Util;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import practicafinal.VentanaInternaImagen;
import practicafinal.VentanaPrincipal;
import sm.image.LookupTableProducer;

/**
 *
 * @author Mario
 */
public class DialogoContrasteListener extends DialogoListener {

    public DialogoContrasteListener(VentanaPrincipal vp) {
        super(vp);
    }

    @Override
    public void windowActivated(WindowEvent we) {
        VentanaInternaImagen vi = vp.getVentanaInternaImagenActiva(false);
        if (vi == null)
            Util.setEnabledGroup(((DialogoContraste) we.getSource()).getGrupoContraste(),false);
        else
            Util.setEnabledGroup(((DialogoContraste) we.getSource()).getGrupoContraste(),true);
    }

    @Override
    public void windowClosed(WindowEvent we) {
        DialogoContraste dc = (DialogoContraste) we.getSource();
        VentanaInternaImagen vi = vp.getVentanaInternaImagenActiva(false);
        if (dc.isAceptar()) {
            if (vi != null) {
                BufferedImage imgSource = vi.getPanel().getImagen();
                if (imgSource != null)
                    switch (dc.getContraste()) {
                        case DialogoContraste.NORMAL:
                            try {
                                int type = LookupTableProducer.TYPE_SFUNCION;
                                LookupTable lt = LookupTableProducer.createLookupTable(type);
                                LookupOp lop = new LookupOp(lt, null);
                                lop.filter(imgSource, imgSource);
                                vi.repaint();
                            } catch (Exception e) {
                                System.err.println(e.getLocalizedMessage());
                            }
                            break;
                        case DialogoContraste.ILUMINADO:
                            try {
                                int type = LookupTableProducer.TYPE_LOGARITHM;
                                LookupTable lt = LookupTableProducer.createLookupTable(type);
                                LookupOp lop = new LookupOp(lt, null);
                                lop.filter(imgSource, imgSource);
                                vi.repaint();
                            } catch (Exception e) {
                                System.err.println(e.getLocalizedMessage());
                            }
                            break;
                        case DialogoContraste.OSCURECIDO:
                            try {
                                int type = LookupTableProducer.TYPE_POWER;
                                LookupTable lt = LookupTableProducer.createLookupTable(type);
                                LookupOp lop = new LookupOp(lt, null);
                                lop.filter(imgSource, imgSource);
                                vi.repaint();
                            } catch (Exception e) {
                                System.err.println(e.getLocalizedMessage());
                            }
                            break;

                    }
            }

            }
        }
    
}
