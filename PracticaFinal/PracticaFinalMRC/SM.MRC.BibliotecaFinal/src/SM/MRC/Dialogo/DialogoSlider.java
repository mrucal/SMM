package SM.MRC.Dialogo;

import javax.swing.JButton;
import javax.swing.JSlider;

/**
 * Clase diálogo que modifica una propiedad mediante un slider. 
 * 
 * @author Mario
 */
public class DialogoSlider extends Dialogo {


    /** Constructor por defecto
     * 
     * @param parent Frame que ha llamado al diálogo.
     * @param inicio Valor inicial del slider.
     * @param min Valor minimo del slider.
     * @param max Valor máximo del slider.
     * @param majortrick Valor majortrick del slider.
     * @param minortrick Valor minortrick del slider.
     * @param name Nombre del slider (de la propiedad que va a cambiar).
     */
    public DialogoSlider(java.awt.Frame parent, int inicio, int min, int max, int majortrick, int minortrick, String name) {
        super(parent);
        initComponents();
        slider.setValue(inicio);
        slider.setMaximum(max);
        slider.setMinimum(min);
        slider.setName(name);
        slider.setMajorTickSpacing(majortrick);
        slider.setMinorTickSpacing(minortrick);
    }

    /**
     * Obtiene el slider.
     * @return Slider.
     */
    public JSlider getSlider() {
        return slider;
    }    

    /**
     * Obtiene el botón aceptar.
     * 
     * @return Botón acpetar.
     */
    public JButton getBotonAceptar() {
        return botonAceptar;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        slider = new javax.swing.JSlider();
        separador = new javax.swing.JSeparator();
        botonAceptar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(25);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderStateChanged(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonAceptar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(slider, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(separador)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(slider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separador, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAceptar)
                    .addComponent(botonCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        aceptar = true;
        this.dispose();
    }//GEN-LAST:event_botonAceptarActionPerformed

    protected void miSliderStateChanged(javax.swing.event.ChangeEvent evt){}
    private void sliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderStateChanged
       miSliderStateChanged(evt);
    }//GEN-LAST:event_sliderStateChanged

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        this.dispose();
        aceptar = false;
    }//GEN-LAST:event_botonCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JSeparator separador;
    private javax.swing.JSlider slider;
    // End of variables declaration//GEN-END:variables
}
