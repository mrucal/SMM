package SM.MRC.Dialogo;

import com.sun.glass.events.KeyEvent;
import java.awt.Frame;

/**
 * Superclase Diálogo que implementa algunas opciones y 
 * comportamientos por defecto de los diálogos que se van a usar.
 * @author Mario
 */
public abstract class Dialogo extends javax.swing.JDialog {

    Frame parent;
 
    boolean aceptar = false;
    
    /**
     * Constructor por defecto.
     * @param parent Frame que ha llamado al diálogo.
     */
    public Dialogo(java.awt.Frame parent) {
        super(parent);
        this.parent = parent;
        initComponents();
    }

    /** 
     * Indica si se ha cerrado (o no) el diálogo mediante el boton aceptar.
     * @return true si se ha cerrado mediante el botón aceptar, 
     * false si se ha cerrado mediante el botón de cerrar o de cancelar.
     */
    public boolean isAceptar() {
        return aceptar;
    }

    /**
     * Modificar la variable que controla si se ha cerrado el díalogo mediante el botón aceptar.
     * @param aceptar true, si se ha cerrado mediante el botón aceptar, 
     * false si se ha cerrado mediante el botón de cerrar o de cancelar.
     */
    public void setAceptar(boolean aceptar) {
        this.aceptar = aceptar;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.dispose();
        }
    }//GEN-LAST:event_formKeyPressed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
