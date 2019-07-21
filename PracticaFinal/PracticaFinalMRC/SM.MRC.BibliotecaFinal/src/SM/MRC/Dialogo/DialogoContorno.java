package SM.MRC.Dialogo;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

/**
 * Clase diálogo para modificar los parámetros del atributo contorno.
 * 
 * @author Mario
 */
public class DialogoContorno extends Dialogo {
    
    /**
     * Constructor por defecto.
     * @param parent Frame que ha llamado al diálogo.
     */
    public DialogoContorno(java.awt.Frame parent) {
        super(parent);
        initComponents();
    }

    /**
     * Obtiene el checkbox que modifica el tipo.
     * @return Checkbox del tipo de contorno.
     */
    public JCheckBox getCheckboxTipo() {
        return checkboxTipo;
    }

    /**
     * Modifica el checkbox que modifica el tipo de contorno.
     * @param checkboxTipo Checkbox del tipo de contorno.
     */
    public void setCheckboxTipo(JCheckBox checkboxTipo) {
        this.checkboxTipo = checkboxTipo;
    }

    /**
     * Obtiene el radiobutton que indica si el estilo de línea es continuo.
     * @return Radiobutton de estilo de contorno contínuo.
     */
    public JRadioButton getRadioContinuo() {
        return radioContinuo;
    }

    /**
     * Modifica el radiobutton que indica si el estilo de línea es continuo.
     * @param radioContinuo Radiobutton de estilo de contorno contínuo.
     */
    public void setRadioContinuo(JRadioButton radioContinuo) {
        this.radioContinuo = radioContinuo;
    }

    /**
     * Obtiene el radiobutton que indica si el estilo de línea es punteado.
     * @return Radiobutton de estilo de contorno punteado.
     */
    public JRadioButton getRadioPunteado() {
        return radioPunteado;
    }

    /**
     * Modifica el radiobutton que indica si el estilo de línea es punteado.
     * @param radioPunteado Radiobutton de estilo de contorno punteado.
     */
    public void setRadioPunteado(JRadioButton radioPunteado) {
        this.radioPunteado = radioPunteado;
    }

    /**
     * Obtiene el spinner que modifica el grosor.
     * 
     * @return Spineer de grosor de contorno.
     */
    public JSpinner getSpinnerGrosor() {
        return spinnerGrosor;
    }

    /**
     * Modifica el spinner que modifica el grosor.
     * 
     * @param spinnerGrosor Spineer de grosor de contorno.
     */
    public void setSpinnerGrosor(JSpinner spinnerGrosor) {
        this.spinnerGrosor = spinnerGrosor;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoTipoLinea = new javax.swing.ButtonGroup();
        spinnerGrosor = new javax.swing.JSpinner();
        radioContinuo = new javax.swing.JRadioButton();
        radioPunteado = new javax.swing.JRadioButton();
        labelTazoContinuo = new javax.swing.JLabel();
        labeltrazoPunteado = new javax.swing.JLabel();
        botonAceptar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        labelTipo = new javax.swing.JLabel();
        checkboxTipo = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Contorno");

        spinnerGrosor.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinnerGrosor.setToolTipText("Grosor");
        spinnerGrosor.setMinimumSize(new java.awt.Dimension(40, 20));
        spinnerGrosor.setPreferredSize(new java.awt.Dimension(50, 30));
        spinnerGrosor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerGrosorStateChanged(evt);
            }
        });

        grupoTipoLinea.add(radioContinuo);
        radioContinuo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radioContinuoStateChanged(evt);
            }
        });

        grupoTipoLinea.add(radioPunteado);
        radioPunteado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radioPunteadoStateChanged(evt);
            }
        });

        labelTazoContinuo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atributos/linea_trazo.png"))); // NOI18N

        labeltrazoPunteado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atributos/linea_discontinua.png"))); // NOI18N

        botonAceptar.setText("Aceptar");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        labelTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atributos/sin_relleno.png"))); // NOI18N

        checkboxTipo.setFocusable(false);
        checkboxTipo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                checkboxTipoStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botonCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonAceptar)))
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelTazoContinuo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labeltrazoPunteado, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioContinuo)
                            .addComponent(radioPunteado))
                        .addGap(18, 18, 18)
                        .addComponent(spinnerGrosor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 25, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(labelTipo)
                .addGap(18, 18, 18)
                .addComponent(checkboxTipo)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(checkboxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelTazoContinuo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioContinuo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labeltrazoPunteado, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioPunteado, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(spinnerGrosor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAceptar)
                    .addComponent(botonCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    protected void miSpinnerGrosorStateChanged(javax.swing.event.ChangeEvent evt){}
    private void spinnerGrosorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerGrosorStateChanged
        miSpinnerGrosorStateChanged(evt);
    }//GEN-LAST:event_spinnerGrosorStateChanged

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        aceptar = true;
        this.dispose();
    }//GEN-LAST:event_botonAceptarActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonCancelarActionPerformed

    protected void miCheckboxTipoStateChanged(javax.swing.event.ChangeEvent evt){}
    private void checkboxTipoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_checkboxTipoStateChanged
        miCheckboxTipoStateChanged(evt);
    }//GEN-LAST:event_checkboxTipoStateChanged
    
    protected void miRadioStateChanged(javax.swing.event.ChangeEvent evt){}
    private void radioContinuoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radioContinuoStateChanged
        miRadioStateChanged(evt);
    }//GEN-LAST:event_radioContinuoStateChanged

    private void radioPunteadoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radioPunteadoStateChanged
        miRadioStateChanged(evt);
    }//GEN-LAST:event_radioPunteadoStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JCheckBox checkboxTipo;
    private javax.swing.ButtonGroup grupoTipoLinea;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelTazoContinuo;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JLabel labeltrazoPunteado;
    private javax.swing.JRadioButton radioContinuo;
    private javax.swing.JRadioButton radioPunteado;
    private javax.swing.JSpinner spinnerGrosor;
    // End of variables declaration//GEN-END:variables
}
