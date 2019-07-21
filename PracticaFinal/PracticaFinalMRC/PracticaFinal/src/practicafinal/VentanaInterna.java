package practicafinal;

import SM.MRC.Panel.PanelLienzo;
import SM.MRC.Util.TipoRecurso;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import javax.swing.JDesktopPane;

/**
 *
 * @author Mario
 */
public abstract class VentanaInterna extends javax.swing.JInternalFrame {
    
    VentanaPrincipal parent = null;
    
    protected TipoRecurso tipo;
    
    static int nCapturas;
    
    public VentanaInterna(VentanaPrincipal parent) {
        initComponents();
        this.parent = parent;
        mostrarVentanaCascada(parent.getEscritorio());
        parent.getEscritorio().add(this);
        this.setVisible(true);
    }
    
    public TipoRecurso getTipo(){
        return tipo;
    }
    
    public void mostrarVentanaCascada(JDesktopPane escritorio){
        // Mostrar las nuevas ventanas en cascada
        if(escritorio.getComponentCount()>0){
            // Obtener la posición de la última ventana
            Point2D p_ultima_vi = escritorio.getComponents()[0].getLocation();
            Point2D cascada = new Point((int)p_ultima_vi.getX()+25, (int)p_ultima_vi.getY()+25);
            this.setLocation((Point) cascada);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setPreferredSize(new java.awt.Dimension(300, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public VentanaPrincipal getVentanaPrincipal(){
        return parent;
    }
    
    public PanelLienzo getPanel(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setImagen(BufferedImage imagen) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
