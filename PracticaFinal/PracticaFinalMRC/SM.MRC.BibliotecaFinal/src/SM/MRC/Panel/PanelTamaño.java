package SM.MRC.Panel;

import SM.MRC.Listener.TextFieldsTamañoListener;
import javax.swing.JFormattedTextField;

/**
 *
 * Clase Panel Tamaño.
 * 
 * Panel para introducir un tamaño (ancho y alto).
 * 
 * @author Mario
 */
public class PanelTamaño extends javax.swing.JPanel {

    /**
     * Consructor por defecto.
     */
    public PanelTamaño() {
        initComponents();
        tfAlto.setText("300");
        tfAncho.setText("300");
        tfAlto.addKeyListener(new TextFieldsTamañoListener());
        tfAncho.addKeyListener(new TextFieldsTamañoListener());
    }
    
    /**
     * Obtiene el ancho.
     * 
     * @return Ancho.
     */
    public int getAncho(){
        return Integer.parseInt(getTfAncho().getText());
    }
    
    /**
     * Obtiene el alto.
     * 
     * @return Alto.
     */
    public int getAlto(){
        return Integer.parseInt(getTfAlto().getText());
    }

    /**
     * Obtiene el TextField en el que se introduce el alto.
     * 
     * @return TextField donde se introduce el alto.
     */
    public JFormattedTextField getTfAlto() {
        return tfAlto;
    }

    /**
     * Obtiene el TextField en el que se introduce el ancho.
     * 
     * @return TextField donde se introduce el ancho.
     */
    public JFormattedTextField getTfAncho() {
        return tfAncho;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfAncho = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfAlto = new javax.swing.JFormattedTextField();

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Alto");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("px");

        tfAncho.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("####"))));
        tfAncho.setFocusCycleRoot(true);
        tfAncho.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfAncho.setPreferredSize(new java.awt.Dimension(50, 25));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("px");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Ancho:");

        tfAlto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        tfAlto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfAlto.setPreferredSize(new java.awt.Dimension(50, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfAlto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfAncho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(tfAncho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(tfAlto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JFormattedTextField tfAlto;
    private javax.swing.JFormattedTextField tfAncho;
    // End of variables declaration//GEN-END:variables
}
