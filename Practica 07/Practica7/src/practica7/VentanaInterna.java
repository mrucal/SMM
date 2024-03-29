/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica7;

import SM.MRC.iu.Lienzo2D;
import java.beans.PropertyVetoException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JToggleButton;

/**
 *
 * @author Mario
 */
public class VentanaInterna extends javax.swing.JInternalFrame {

    private VentanaPrincipal parent = null;

    public Lienzo2D getLienzo2D() {
        return lienzo2D;
    }    
    
    /**
     * Creates new form VentanaInterna
     */
    public VentanaInterna(VentanaPrincipal parent) {
        initComponents();
        this.parent = parent;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lienzo2D = new SM.MRC.iu.Lienzo2D();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Nuevo lienzo");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(false);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
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
            }
        });

        javax.swing.GroupLayout lienzo2DLayout = new javax.swing.GroupLayout(lienzo2D);
        lienzo2D.setLayout(lienzo2DLayout);
        lienzo2DLayout.setHorizontalGroup(
            lienzo2DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );
        lienzo2DLayout.setVerticalGroup(
            lienzo2DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );

        getContentPane().add(lienzo2D, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated

        parent.getGrupoBarraHerramientas().clearSelection();

        JToggleButton jb = new JToggleButton(); int i = 0;
        for (Enumeration<AbstractButton> h = parent.getGrupoBarraHerramientas().getElements(); h.hasMoreElements();) {
            jb = (JToggleButton) h.nextElement();
            if(i==lienzo2D.getHerramienta().ordinal())
                break;
            i++;
        }
        jb.setSelected(true);

        parent.getBarraEstado().setText(jb.getName());

        parent.getSpinnerGrosor().setValue(lienzo2D.getGrosor());

        parent.getCbRelleno().setSelected(lienzo2D.isRelleno());
        parent.getCbTransparencia().setSelected(lienzo2D.isTransparencia());
        parent.getCbAlisar().setSelected(lienzo2D.isAlisar());
        parent.getCbEditar().setSelected(lienzo2D.isEditar());
    }//GEN-LAST:event_formInternalFrameActivated


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private SM.MRC.iu.Lienzo2D lienzo2D;
    // End of variables declaration//GEN-END:variables
}
