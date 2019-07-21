package SM.MRC.Dialogo;

import SM.MRC.Util.Ordenacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

/**
 * Clase dialógo para modificar el orden de las figuras dibujadas en el panel.
 * @author Mario
 */
public class DialogoOrdenacion extends Dialogo {

    private Ordenacion ordenacion = null;
    
    /**
     * Constructor por defecto.
     * @param parent Frame que ha llamado al diálogo.
     */
    public DialogoOrdenacion(java.awt.Frame parent) {
        super(parent);
        initComponents();
        ActionListener al = new OrdenacionListener(this);
        botonEnviarFondo.addActionListener(al);
        botonEnviarAtras.addActionListener(al);
        botonTraerAdelante.addActionListener(al);
        botonTraerFrente.addActionListener(al);
    }
    
    /**
     * Clase listener para obtener el botón de ordenación que se ha seleccionado.
     */
    class OrdenacionListener implements ActionListener {        
            
        DialogoOrdenacion dr;
            
        OrdenacionListener(DialogoOrdenacion dr){this.dr = dr;}
        @Override
        public void actionPerformed(ActionEvent evt){
            JButton botonRelleno = (JButton) evt.getSource();
            ordenacion = Ordenacion.valueOf(botonRelleno.getName());
            dr.aceptar = true;
            dr.dispose();
        }
    }

    /**
     * Obtiene el tipo de ordenación seleccionado.
     * @return Ordenacion.
     */
    public Ordenacion getOrdenacion() {
        return ordenacion;
    }

    /** 
     * Modificar el tipo de ordenación.
     * @param ordenacion Tipo de ordenación.
     */
    public void setOrdenacion(Ordenacion ordenacion) {
        this.ordenacion = ordenacion;
    }

    /**
     * Obtener el grupo de botones de ordenación.
     * 
     * @return Grupo de botones de ordenación.
     */
    public ButtonGroup getGrupoOrdenacion(){
        return grupoOrdenacion;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoOrdenacion = new javax.swing.ButtonGroup();
        botonEnviarFondo = new javax.swing.JButton();
        botonEnviarAtras = new javax.swing.JButton();
        botonTraerAdelante = new javax.swing.JButton();
        botonTraerFrente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ordenación");

        botonEnviarFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraEditar/enviarFondo.png"))); // NOI18N
        grupoOrdenacion.add(botonEnviarFondo);
        botonEnviarFondo.setFocusable(false);
        botonEnviarFondo.setName("ENVIAR_FONDO"); // NOI18N

        botonEnviarAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraEditar/enviarAtras.png"))); // NOI18N
        grupoOrdenacion.add(botonEnviarAtras);
        botonEnviarAtras.setFocusable(false);
        botonEnviarAtras.setName("ENVIAR_ATRAS"); // NOI18N

        botonTraerAdelante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraEditar/traerAdelante.png"))); // NOI18N
        grupoOrdenacion.add(botonTraerAdelante);
        botonTraerAdelante.setFocusable(false);
        botonTraerAdelante.setName("TRAER_ADELANTE"); // NOI18N

        botonTraerFrente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraEditar/traerFrente.png"))); // NOI18N
        grupoOrdenacion.add(botonTraerFrente);
        botonTraerFrente.setFocusable(false);
        botonTraerFrente.setName("TRAER_FRENTE"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonEnviarFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonEnviarAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonTraerAdelante, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonTraerFrente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonTraerFrente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonTraerAdelante, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonEnviarAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonEnviarFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEnviarAtras;
    private javax.swing.JButton botonEnviarFondo;
    private javax.swing.JButton botonTraerAdelante;
    private javax.swing.JButton botonTraerFrente;
    private javax.swing.ButtonGroup grupoOrdenacion;
    // End of variables declaration//GEN-END:variables
}
