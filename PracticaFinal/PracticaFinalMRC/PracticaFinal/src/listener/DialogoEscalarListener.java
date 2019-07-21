package listener;

import SM.MRC.Dialogo.DialogoEscalar;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import practicafinal.VentanaInternaImagen;
import practicafinal.VentanaPrincipal;

/**
 *
 * @author Mario
 */
public class DialogoEscalarListener extends DialogoListener {

    public DialogoEscalarListener(VentanaPrincipal vp) {
        super(vp);
    }

    @Override
    public void windowClosed(WindowEvent we) {
        DialogoEscalar de = (DialogoEscalar) we.getSource();
        VentanaInternaImagen vi = vp.getVentanaInternaImagenActiva(false);
        switch(de.getAccion()) {
            case DialogoEscalar.ESCALAR:
                if (vi != null) {
                    BufferedImage imgSource = vi.getPanel().getImagen();
                    if (imgSource != null) 
                        try {
                            AffineTransform at = AffineTransform.getScaleInstance(de.getAncho() / imgSource.getWidth(), de.getAlto() / imgSource.getHeight());
                            AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                            BufferedImage imgdest = atop.filter(imgSource, null);
                            vi.getPanel().setImagen(imgdest);
                            vi.getPanel().setSize(new Dimension(imgdest.getWidth(), imgdest.getHeight()));
                            vi.getPanel().setPreferredSize(new Dimension((int)de.getAncho(),(int) de.getAlto()));
                            vi.getPanel().setSize(new Dimension((int)de.getAncho(),(int) de.getAlto()));
                            vi.getPanel().repaint();
                            de.dispose();
                        } catch (IllegalArgumentException e) {
                            System.err.println(e.getLocalizedMessage());
                        }
                }
                break;
            case DialogoEscalar.REDIMENSIONAR:
                vi.getPanel().setPreferredSize(new Dimension((int)de.getAncho(),(int) de.getAlto()));
                vi.getPanel().setSize(new Dimension((int)de.getAncho(),(int) de.getAlto()));
                de.dispose();
                break;
            case DialogoEscalar.CANCELAR:
                break;
        }
    }
}
