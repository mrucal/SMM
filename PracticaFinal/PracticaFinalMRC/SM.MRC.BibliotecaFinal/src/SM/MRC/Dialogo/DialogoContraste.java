package SM.MRC.Dialogo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

/**
 * Clase diálogo para modificar el contraste de una imagen.
 *
 * @author Mario
 */
public class DialogoContraste extends Dialogo {

    public static final int NORMAL = 0;
    public static final int ILUMINADO = 1;
    public static final int OSCURECIDO = 2;
    
    private int contraste;
    
    /**
     * Constructor por defecto.
     * @param parent Frame que ha llamado al diálogo.
     */
    public DialogoContraste(java.awt.Frame parent) {
        super(parent);
        initComponents();
        ActionListener al = new ContrasteListener(this);
        botonContrasteNormal.addActionListener(al);
        botonContrasteIluminado.addActionListener(al);
        botonContrasteOscurecido.addActionListener(al);
    }
    
    /**
     * Listener para los botones del contraste.
     */
    class ContrasteListener implements ActionListener {
            
            DialogoContraste dr;
            
            ContrasteListener(DialogoContraste dr){this.dr = dr;}
            @Override
            public void actionPerformed(ActionEvent evt){
                JButton bc = (JButton) evt.getSource();
                switch(bc.getName()){
                    case "normal":
                        contraste = NORMAL;
                        break;
                    case "iluminado":
                        contraste = ILUMINADO;
                        break;
                    case "oscurecido":
                        contraste = OSCURECIDO;
                        break;
                }
                dr.aceptar = true;
                dr.dispose();
            }
        }

    /**
     * Obtener el tipo de contraste que se ha seleccionado.
     * 
     * @return Tipo de contraste seleccionado. Puede ser {@value #NORMAL}, {@value #OSCURECIDO} o {@value #ILUMINADO}.
     */
    public int getContraste() {
        return contraste;
    }    

    /**
     * Obtiene el grupo de botones del contraste.
     * @return Grupo de botones del contraste.
     */
    public ButtonGroup getGrupoContraste(){
        return grupoContraste;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoContraste = new javax.swing.ButtonGroup();
        botonContrasteNormal = new javax.swing.JButton();
        botonContrasteIluminado = new javax.swing.JButton();
        botonContrasteOscurecido = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ordenación");

        botonContrasteNormal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraImagenes/contraste.png"))); // NOI18N
        grupoContraste.add(botonContrasteNormal);
        botonContrasteNormal.setFocusable(false);
        botonContrasteNormal.setName("normal"); // NOI18N

        botonContrasteIluminado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraImagenes/iluminar.png"))); // NOI18N
        grupoContraste.add(botonContrasteIluminado);
        botonContrasteIluminado.setFocusable(false);
        botonContrasteIluminado.setName("iluminado"); // NOI18N

        botonContrasteOscurecido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraImagenes/oscurecer.png"))); // NOI18N
        grupoContraste.add(botonContrasteOscurecido);
        botonContrasteOscurecido.setFocusable(false);
        botonContrasteOscurecido.setName("oscurecido"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonContrasteNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonContrasteIluminado, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonContrasteOscurecido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonContrasteOscurecido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonContrasteIluminado, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonContrasteNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonContrasteIluminado;
    private javax.swing.JButton botonContrasteNormal;
    private javax.swing.JButton botonContrasteOscurecido;
    private javax.swing.ButtonGroup grupoContraste;
    // End of variables declaration//GEN-END:variables
}
