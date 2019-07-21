package SM.MRC.Dialogo;

import SM.MRC.Panel.PanelTamaño;

/**
 * Clase diálogo para escalar o redimensionar una imagen.
 * 
 * @author Mario
 */
public class DialogoEscalar extends Dialogo{

    public static final int ESCALAR = 0;
    public static final int REDIMENSIONAR = 1;
    public static final int CANCELAR = 2;
    
    private int accion;
    
    /**
     * Constructor por defecto.
     * @param parent Frame que ha llamado al diálogo.
     */
    public DialogoEscalar(java.awt.Frame parent) {
        super(parent);
        initComponents();
        accion = CANCELAR;
    }

    /** 
     * Obtiene el botón que se ha seleccionado.
     * @return Entero que representa el botón seleccionado. Puede ser: {@value #ESCALAR}, {@value #REDIMENSIONAR} o {@value #CANCELAR}
     */
    public int getAccion(){
        return accion;
    }
    
    /**
     * Obtiene el panel del tamaño.
     * 
     * @return Panel de tamaño.
     */
    public PanelTamaño getPanelTamaño() {
        return panelTamaño;
    }
    
    /** 
     * Obtiene el ancho indicado.
     * @return Ancho.
     */
    public double getAncho(){
        return panelTamaño.getAncho();
    }

    /**
     * Obtiene el alto indicado.
     * @return Alto.
     */
    public double getAlto(){
        return panelTamaño.getAlto();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        botonEscalar = new javax.swing.JButton();
        botonRedimensionar = new javax.swing.JButton();
        botonCancelarTamaño = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        panelTamaño = new SM.MRC.Panel.PanelTamaño();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Escalar/Redimensionar");

        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jSeparator1, java.awt.BorderLayout.NORTH);

        jPanel4.setPreferredSize(new java.awt.Dimension(400, 50));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonEscalar.setText("Escalar");
        botonEscalar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEscalarActionPerformed(evt);
            }
        });
        jPanel4.add(botonEscalar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 70, -1));

        botonRedimensionar.setText("Redimensionar");
        botonRedimensionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRedimensionarActionPerformed(evt);
            }
        });
        jPanel4.add(botonRedimensionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 120, -1));

        botonCancelarTamaño.setText("Cancelar");
        botonCancelarTamaño.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarTamañoActionPerformed(evt);
            }
        });
        jPanel4.add(botonCancelarTamaño, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atributos/image_resize.png"))); // NOI18N
        jPanel2.add(jLabel2, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 40));
        jPanel3.add(panelTamaño);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonEscalarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEscalarActionPerformed
        accion = ESCALAR;
        dispose();
    }//GEN-LAST:event_botonEscalarActionPerformed

    private void botonRedimensionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRedimensionarActionPerformed
        accion = REDIMENSIONAR;
        dispose();
    }//GEN-LAST:event_botonRedimensionarActionPerformed

    private void botonCancelarTamañoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarTamañoActionPerformed
        dispose();
    }//GEN-LAST:event_botonCancelarTamañoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelarTamaño;
    private javax.swing.JButton botonEscalar;
    private javax.swing.JButton botonRedimensionar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private SM.MRC.Panel.PanelTamaño panelTamaño;
    // End of variables declaration//GEN-END:variables
}
