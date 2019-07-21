package SM.MRC.Dialogo;

import SM.MRC.Grafico.Estilo.Relleno;
import SM.MRC.Util.TipoRelleno;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;

/**
 * Clase diálogo para modificar los parámetros del atributo relleno.
 * 
 * @author Mario
 */
public class DialogoRelleno extends Dialogo {

    int tipo = Relleno.SIN_RELLENO;
    int tipo_degradado = Relleno.HORIZONTAL;
    
    /**
     * Constructor por defecto.
     * @param parent Frame que ha llamado al diálogo.
     */
    public DialogoRelleno(java.awt.Frame parent) {
        super(parent);
        initComponents();
        ActionListener al = new RellenoListener(this);
        botonSinRelleno.addActionListener(al);
        botonRellenoLiso.addActionListener(al);
        botonDegradadoHorizontal.addActionListener(al);
        botonDegradadoVertical.addActionListener(al);
        botonDegradadoDiagonalIzquierda.addActionListener(al);
        botonDegradadoDiagonalDerecha.addActionListener(al);
        botonRadial.addActionListener(al);
    }
    
    /**
     * Clase listener para obtener el tipo de relleno seleccionado.
     */
    class RellenoListener implements ActionListener {

        DialogoRelleno dr;

        RellenoListener(DialogoRelleno dr){this.dr = dr;}
        @Override
        public void actionPerformed(ActionEvent evt){
            JToggleButton botonRelleno = (JToggleButton) evt.getSource();
            switch(TipoRelleno.valueOf(botonRelleno.getName())){
                case SIN_RELLENO:
                    tipo = Relleno.SIN_RELLENO;
                    break;
                case LISO:
                    tipo = Relleno.LISO;
                    break;
                case HORIZONTAL:
                    tipo = Relleno.DEGRADADO;
                    tipo_degradado = Relleno.HORIZONTAL;
                    break;
                case VERTICAL:
                    tipo = Relleno.DEGRADADO;
                    tipo_degradado = Relleno.VERTICAL;
                    break;
                case DIAGONAL_IZQUIERDA:
                    tipo = Relleno.DEGRADADO;
                    tipo_degradado = Relleno.DIAGONAL_IZQUIERDA;
                    break;
                case DIAGONAL_DERECHA:
                    tipo = Relleno.DEGRADADO;
                    tipo_degradado = Relleno.DIAGONAL_DERECHA;
                    break;
                case RADIAL:
                    tipo = Relleno.DEGRADADO;
                    tipo_degradado = Relleno.RADIAL;
                    break;
            }
            dr.aceptar = true;
            dr.dispose();
        }
    }

    /**
     * Obtener el tipo de relleno seleccionado.
     * @return 
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Modificar el tipo de relleno.
     * @param tipo Tipo de relleno.
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtener el tipo de degradado seleccionado.
     * 
     * @return Tipo de Degradado.
     */
    public int getTipo_degradado() {
        return tipo_degradado;
    }

    /**
     * Modificar el tipo de degradado.
     * @param tipo_degradado Tipo de degradado.
     */
    public void setTipo_degradado(int tipo_degradado) {
        this.tipo_degradado = tipo_degradado;
    }

    /**
     * Obtener el grupo de botones de relleno.
     * @return Grupo de botones de relleno.
     */
    public ButtonGroup getGrupoRelleno(){
        return grupoRelleno;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoRelleno = new javax.swing.ButtonGroup();
        botonSinRelleno = new javax.swing.JToggleButton();
        botonRellenoLiso = new javax.swing.JToggleButton();
        botonDegradadoHorizontal = new javax.swing.JToggleButton();
        botonDegradadoVertical = new javax.swing.JToggleButton();
        botonDegradadoDiagonalIzquierda = new javax.swing.JToggleButton();
        botonDegradadoDiagonalDerecha = new javax.swing.JToggleButton();
        botonRadial = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relleno");

        grupoRelleno.add(botonSinRelleno);
        botonSinRelleno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atributos/sin.png"))); // NOI18N
        botonSinRelleno.setSelected(true);
        botonSinRelleno.setToolTipText("Sin Relleno");
        botonSinRelleno.setFocusable(false);
        botonSinRelleno.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonSinRelleno.setName("SIN_RELLENO"); // NOI18N
        botonSinRelleno.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        grupoRelleno.add(botonRellenoLiso);
        botonRellenoLiso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atributos/liso.png"))); // NOI18N
        botonRellenoLiso.setToolTipText("Liso");
        botonRellenoLiso.setFocusable(false);
        botonRellenoLiso.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRellenoLiso.setName("LISO"); // NOI18N
        botonRellenoLiso.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        grupoRelleno.add(botonDegradadoHorizontal);
        botonDegradadoHorizontal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atributos/horizontal.png"))); // NOI18N
        botonDegradadoHorizontal.setToolTipText("Degradado Horizontal");
        botonDegradadoHorizontal.setFocusable(false);
        botonDegradadoHorizontal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonDegradadoHorizontal.setName("HORIZONTAL"); // NOI18N
        botonDegradadoHorizontal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        grupoRelleno.add(botonDegradadoVertical);
        botonDegradadoVertical.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atributos/vertical.png"))); // NOI18N
        botonDegradadoVertical.setToolTipText("Degradado Vertical");
        botonDegradadoVertical.setFocusable(false);
        botonDegradadoVertical.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonDegradadoVertical.setName("VERTICAL"); // NOI18N
        botonDegradadoVertical.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        grupoRelleno.add(botonDegradadoDiagonalIzquierda);
        botonDegradadoDiagonalIzquierda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atributos/izquierdo.png"))); // NOI18N
        botonDegradadoDiagonalIzquierda.setToolTipText("Degradado Diagonal Izquierda");
        botonDegradadoDiagonalIzquierda.setFocusable(false);
        botonDegradadoDiagonalIzquierda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonDegradadoDiagonalIzquierda.setName("DIAGONAL_IZQUIERDA"); // NOI18N
        botonDegradadoDiagonalIzquierda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        grupoRelleno.add(botonDegradadoDiagonalDerecha);
        botonDegradadoDiagonalDerecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atributos/derecho.png"))); // NOI18N
        botonDegradadoDiagonalDerecha.setToolTipText("Degradado Diagonal Derecha");
        botonDegradadoDiagonalDerecha.setFocusable(false);
        botonDegradadoDiagonalDerecha.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonDegradadoDiagonalDerecha.setName("DIAGONAL_DERECHA"); // NOI18N
        botonDegradadoDiagonalDerecha.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        grupoRelleno.add(botonRadial);
        botonRadial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atributos/radial.png"))); // NOI18N
        botonRadial.setToolTipText("Degradado Radial");
        botonRadial.setFocusable(false);
        botonRadial.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRadial.setName("RADIAL"); // NOI18N
        botonRadial.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonSinRelleno, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonRellenoLiso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonDegradadoHorizontal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonDegradadoVertical, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonDegradadoDiagonalIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonDegradadoDiagonalDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonRadial, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonRadial, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonDegradadoDiagonalDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonDegradadoDiagonalIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonDegradadoVertical, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonDegradadoHorizontal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonRellenoLiso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonSinRelleno, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton botonDegradadoDiagonalDerecha;
    private javax.swing.JToggleButton botonDegradadoDiagonalIzquierda;
    private javax.swing.JToggleButton botonDegradadoHorizontal;
    private javax.swing.JToggleButton botonDegradadoVertical;
    private javax.swing.JToggleButton botonRadial;
    private javax.swing.JToggleButton botonRellenoLiso;
    private javax.swing.JToggleButton botonSinRelleno;
    private javax.swing.ButtonGroup grupoRelleno;
    // End of variables declaration//GEN-END:variables
}
