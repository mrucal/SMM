package listener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import practicafinal.VentanaInternaImagen;
import practicafinal.VentanaPrincipal;

public class ColorListener extends MouseAdapter {

    private JTextField botonActivo;
    private JTextField botonNoActivo;
    private JTextField botonNoActivo2;

    private VentanaPrincipal vp;

    private int iboton;

    public ColorListener(VentanaPrincipal vp) {
        this.vp = vp;
        botonActivo = vp.getPanelPaletaColores().getBotonContorno();
        botonNoActivo = vp.getPanelPaletaColores().getBotonFrente();
        botonNoActivo2 = vp.getPanelPaletaColores().getBotonFondo();
        iboton = 0;
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        VentanaInternaImagen vi = vp.getVentanaInternaImagenActiva(false);
        if (evt.getSource() instanceof JLabel) {
            Color colorNuevo = JColorChooser.showDialog(vp.getPanelPaletaColores().getDialogoPaleta(), "Elige un color", Color.BLACK);
            ArrayList<JTextField> coloresNuevos = vp.getPanelPaletaColores().getColoresNuevos();

            coloresNuevos.get(iboton).setBackground(colorNuevo);
            iboton = (iboton + 1) % coloresNuevos.size();

            if (vi != null) {
                switch (vi.getBoconColorActivo().getName()) {
                    case "colorFondo":
                        if (vi.getPanel().getSeleccionado() == null) {
                            vi.getPanel().getEstilo().setColorFondo(colorNuevo);
                        } else 
                            vi.getPanel().getSeleccionado().setColorFondo(colorNuevo);
                        break;
                    case "colorFrente":
                        if (vi.getPanel().getSeleccionado() == null) 
                            vi.getPanel().getEstilo().setColorFrente(colorNuevo);
                        else 
                            vi.getPanel().getSeleccionado().setColorFrente(colorNuevo);
                        break;
                    case "contorno":
                        if (vi.getPanel().getSeleccionado() == null) 
                            vi.getPanel().getEstilo().getContorno().setColor(colorNuevo);
                        else 
                            vi.getPanel().getSeleccionado().setColorContorno(colorNuevo);
                        break;
                    default:
                        vi.getPanel().getEstilo().setColorFrente(colorNuevo);
                        break;
                }
                vi.getBoconColorActivo().setBackground(colorNuevo);
                vi.repaint();
            } else {
                vp.getPanelPaletaColores().setBotonActivo(botonActivo);
                botonActivo.setBackground(colorNuevo);
                botonActivo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new Color(102, 102, 102), new Color(102, 102, 102)));
                botonNoActivo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new Color(204, 204, 204), new Color(204, 204, 204)));
                botonNoActivo2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new Color(204, 204, 204), new Color(204, 204, 204)));
            }
        } else {
            JTextField botonColor = (JTextField) evt.getSource();
            switch (botonColor.getName()) {
                case "colorFondo":
                    botonActivo = vp.getPanelPaletaColores().getBotonFondo();
                    botonNoActivo = vp.getPanelPaletaColores().getBotonFrente();
                    botonNoActivo2 = vp.getPanelPaletaColores().getBotonContorno();
                    if (vi != null) {
                        vi.setBotonColorActivo(botonActivo);
                        vp.getPanelPaletaColores().setBotonActivo(botonActivo);
                    }
                    break;
                case "colorFrente":
                    botonActivo = vp.getPanelPaletaColores().getBotonFrente();
                    botonNoActivo = vp.getPanelPaletaColores().getBotonFondo();
                    botonNoActivo2 = vp.getPanelPaletaColores().getBotonContorno();
                    if (vi != null) {
                        vi.setBotonColorActivo(botonActivo);
                        vp.getPanelPaletaColores().setBotonActivo(botonActivo);
                    }
                    break;
                case "contorno":
                    botonActivo = vp.getPanelPaletaColores().getBotonContorno();
                    botonNoActivo = vp.getPanelPaletaColores().getBotonFondo();
                    botonNoActivo2 = vp.getPanelPaletaColores().getBotonFrente();
                    if (vi != null) {
                        vi.setBotonColorActivo(botonActivo);
                        vp.getPanelPaletaColores().setBotonActivo(botonActivo);
                    }
                    break;
                default:
                    if (vi != null) {
                        switch (vi.getBoconColorActivo().getName()) {
                            case "colorFondo":
                                if (vi.getPanel().getSeleccionado() == null) 
                                    vi.getPanel().getEstilo().setColorFondo(botonColor.getBackground());
                                else 
                                    vi.getPanel().getSeleccionado().setColorFondo(botonColor.getBackground());
                                break;
                            case "colorFrente":
                                if (vi.getPanel().getSeleccionado() == null) 
                                    vi.getPanel().getEstilo().setColorFrente(botonColor.getBackground());
                                else 
                                    vi.getPanel().getSeleccionado().setColorFrente(botonColor.getBackground());
                                break;
                            case "contorno":
                                if (vi.getPanel().getSeleccionado() == null) 
                                    vi.getPanel().getEstilo().getContorno().setColor(botonColor.getBackground());
                                else 
                                    vi.getPanel().getSeleccionado().setColorContorno(botonColor.getBackground());                                
                                break;
                            default:
                                vi.getPanel().getEstilo().setColorFrente(botonColor.getBackground());
                                break;
                        }
                        vi.getBoconColorActivo().setBackground(botonColor.getBackground());
                        vi.repaint();
                    } else 
                        botonActivo.setBackground(botonColor.getBackground());
                    break;
            }
            if (vi == null) {
                vp.getPanelPaletaColores().setBotonActivo(botonActivo);
                botonActivo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new Color(102, 102, 102), new Color(102, 102, 102)));
                botonNoActivo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new Color(204, 204, 204), new Color(204, 204, 204)));
                botonNoActivo2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new Color(204, 204, 204), new Color(204, 204, 204)));
            }
        }
    }
}
