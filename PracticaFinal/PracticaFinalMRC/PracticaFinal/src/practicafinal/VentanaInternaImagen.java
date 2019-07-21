package practicafinal;

import SM.MRC.Grafico.Estilo.EstiloRelleno;
import SM.MRC.Panel.PanelImagen;
import SM.MRC.Util.TipoRecurso;
import SM.MRC.Util.TipoRelleno;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author Mario
 */
public class VentanaInternaImagen extends VentanaInterna {

    JTextField botonColorActivo;
    private VentanaInternaImagen vi_parent;
    private int banda;
    
    public VentanaInternaImagen(VentanaPrincipal parent) {
        super(parent);
        initComponents();
        botonColorActivo = parent.getPanelPaletaColores().getBotonContorno();
        panelImagen.setBorde(true);
        tipo = TipoRecurso.IMAGEN;
        vi_parent = null;
        banda = 0;
        reiniciarValores();
    }
    
    public VentanaInternaImagen(VentanaPrincipal parent,VentanaInternaImagen vi_parent, int i_banda) {
        super(parent);
        initComponents();
        botonColorActivo = parent.getPanelPaletaColores().getBotonContorno();
        panelImagen.setBorde(true);
        tipo = TipoRecurso.IMAGEN;
        this.vi_parent = vi_parent;
        this.banda = i_banda;
        reiniciarValores();
    }

    @Override
    public PanelImagen getPanel(){
        return panelImagen;
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        panelImagen = new PanelImagenPracticaFinal(this);

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jScrollPane1.setPreferredSize(new java.awt.Dimension(302, 302));

        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImagen.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelImagenMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                panelImagenMouseMoved(evt);
            }
        });
        panelImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelImagenMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelImagenMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelImagenLayout = new javax.swing.GroupLayout(panelImagen);
        panelImagen.setLayout(panelImagenLayout);
        panelImagenLayout.setHorizontalGroup(
            panelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        panelImagenLayout.setVerticalGroup(
            panelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        panel.add(panelImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jScrollPane1.setViewportView(panel);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void reiniciarValores(){
        EstiloRelleno estilo = panelImagen.getEstilo();
        
        parent.getGrupoBarraHerramientas().clearSelection();

        // Obtener el JToggleButton seleccionado del grupo de botones
        JToggleButton jb = new JToggleButton();
        for (Enumeration<AbstractButton> h = parent.getGrupoBarraHerramientas().getElements(); h.hasMoreElements();) {
            jb = (JToggleButton) h.nextElement();
            
            if(jb.getName().equals(panelImagen.getHerramienta().name()))
                break;
        }
        jb.setSelected(true);

        parent.getBarraEstado().setText(jb.getToolTipText());
        
        parent.getSpinnerGrosor().setValue(estilo.getContorno().getGrosor());
        parent.getBotonRelleno().setIcon(getIconoRelleno(estilo.getRelleno().getTipoRelleno(),0));
        parent.getBotonAlisar().setSelected(estilo.getAlisado().isActivado());
        parent.getPanelPaletaColores().setBotonContorno(estilo.getContorno().getColor());
        parent.getPanelPaletaColores().setBotonFondo(estilo.getRelleno().getColorFondo());
        parent.getPanelPaletaColores().setBotonFrente(estilo.getRelleno().getColorFrente());
        parent.getPanelPaletaColores().setBotonActivo(botonColorActivo);
    }
    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        reiniciarValores();
    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        parent.getSpinnerGrosor().setValue(1);
        parent.getBotonRelleno().setIcon(getIconoRelleno(TipoRelleno.SIN_RELLENO,0));
        parent.getBotonAlisar().setSelected(false);
        parent.getPanelPaletaColores().setBotonFondo(Color.WHITE);
        parent.getPanelPaletaColores().setBotonFrente(Color.BLACK);
        parent.getPanelPaletaColores().setBotonActivo(parent.getPanelPaletaColores().getBotonFondo());
    }//GEN-LAST:event_formInternalFrameClosed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        if(parent.getNumVentanaInterna() == 1){
            panelImagen.getEstilo().setColorFondo(parent.getPanelPaletaColores().getBotonFondo().getBackground());
            panelImagen.getEstilo().setColorFrente(parent.getPanelPaletaColores().getBotonFrente().getBackground());
            botonColorActivo = parent.getPanelPaletaColores().getBotonActivo();
        }else
            formInternalFrameActivated(evt);
    }//GEN-LAST:event_formInternalFrameOpened

    private void panelImagenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelImagenMouseEntered
        parent.getPanelPosicion().setPreferredSize(new Dimension(205,30));
    }//GEN-LAST:event_panelImagenMouseEntered

    private void panelImagenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelImagenMouseExited
        parent.getPanelPosicion().setPreferredSize(new Dimension(10,30));
        parent.getLabelPosicion().setText("");
        parent.getLabelRGB().setText("");
    }//GEN-LAST:event_panelImagenMouseExited

    private void panelImagenMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelImagenMouseMoved
        parent.getLabelPosicion().setText("("+(int)evt.getPoint().getX()+","+(int)evt.getPoint().getY()+")");
        if (evt.getPoint().getX() > 1000 && evt.getPoint().getX() > 1000)
            parent.getPanelPosicion().setPreferredSize(new Dimension(215,30));
        BufferedImage imgSource = getPanel().getImagen();
        if(imgSource != null)
            if( imgSource.getColorModel().getColorSpace().isCS_sRGB()){
                if((new Rectangle(0, 0, imgSource.getWidth(), imgSource.getHeight())).contains(evt.getPoint()))
                    parent.getLabelRGB().setText(
                         "R: "  + imgSource.getRaster().getSample((int)evt.getPoint().getX(), (int)evt.getPoint().getY(), 0)+
                         " G: " + imgSource.getRaster().getSample((int)evt.getPoint().getX(), (int)evt.getPoint().getY(), 1)+
                         " B: " + imgSource.getRaster().getSample((int)evt.getPoint().getX(), (int)evt.getPoint().getY(), 2)
                    ); 
                else
                    parent.getLabelRGB().setText(""); 
            }else{
                parent.getLabelRGB().setText("B: " + imgSource.getRaster().getSample((int)evt.getPoint().getX(), (int)evt.getPoint().getY(), 0));
            }
    }//GEN-LAST:event_panelImagenMouseMoved

    private void panelImagenMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelImagenMouseDragged
        parent.getLabelPosicion().setText("("+(int)evt.getPoint().getX()+","+(int)evt.getPoint().getY()+")");
        BufferedImage imgSource = getPanel().getImagen();
        if( imgSource.getColorModel().getColorSpace().isCS_sRGB()){
                if((new Rectangle(0, 0, imgSource.getWidth(), imgSource.getHeight())).contains(evt.getPoint()))
                    parent.getLabelRGB().setText(
                         "R: "  + imgSource.getRaster().getSample((int)evt.getPoint().getX(), (int)evt.getPoint().getY(), 0)+
                         " G: " + imgSource.getRaster().getSample((int)evt.getPoint().getX(), (int)evt.getPoint().getY(), 1)+
                         " B: " + imgSource.getRaster().getSample((int)evt.getPoint().getX(), (int)evt.getPoint().getY(), 2)
                    ); 
                else
                    parent.getLabelRGB().setText(""); 
        }
    }//GEN-LAST:event_panelImagenMouseDragged
    
    public ImageIcon getIconoRelleno(TipoRelleno tr, int tipo){
        switch(tr){
            case SIN_RELLENO:
                if (tipo == 0)
                    return new ImageIcon(getClass().getResource("/iconos/atributos/sin_relleno.png"));
                else
                    return new ImageIcon(getClass().getResource("/iconos/atributos/sin.png"));
            case LISO:
                if (tipo == 0)
                    return new ImageIcon(getClass().getResource("/iconos/atributos/relleno_liso.png"));
                else
                    return new ImageIcon(getClass().getResource("/iconos/atributos/liso.png"));
            case HORIZONTAL:
                if (tipo == 0)
                    return new ImageIcon(getClass().getResource("/iconos/atributos/degradado_horizontal.png"));
                else
                    return new ImageIcon(getClass().getResource("/iconos/atributos/horizontal.png"));
            case VERTICAL:
                if (tipo == 0)
                    return new ImageIcon(getClass().getResource("/iconos/atributos/degradado_vertical.png"));
                else
                    return new ImageIcon(getClass().getResource("/iconos/atributos/vertical.png"));
            case DIAGONAL_IZQUIERDA:
                if (tipo == 0)
                    return new ImageIcon(getClass().getResource("/iconos/atributos/degradado_izquierdo.png"));
                else
                    return new ImageIcon(getClass().getResource("/iconos/atributos/izquierdo.png"));
            case DIAGONAL_DERECHA:
                if (tipo == 0)
                    return new ImageIcon(getClass().getResource("/iconos/atributos/degradado_derecho.png"));
                else
                    return new ImageIcon(getClass().getResource("/iconos/atributos/derecho.png"));
            case RADIAL:
                if (tipo == 0)
                    return new ImageIcon(getClass().getResource("/iconos/atributos/degradado_radial.png"));
                else
                    return new ImageIcon(getClass().getResource("/iconos/atributos/radial.png"));
        }
        return null;
    }
    
    public JTextField getBoconColorActivo(){
        return botonColorActivo;
    }
    
    public void setBotonColorActivo(JTextField botonActivo){
        botonColorActivo = botonActivo;
    }

    public VentanaInternaImagen getVentanaInternaImagenParent() {
        return vi_parent;
    }

    public int getBanda() {
        return banda;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    private SM.MRC.Panel.PanelImagen panelImagen;
    // End of variables declaration//GEN-END:variables
}
