package SM.MRC.Panel;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Clase Panel Paleta de Colores.
 * 
 * Panel que contiene una paleta de colores. La paleta está compuesta por seis colores básicos, y ocho personalizables
 * que pueden modificarse a través de un botón que muestra una paleta de colores avanzada. También cuenta con botones para 
 * modificar el color de contorno, de fondo y de frente.
 * 
 * @author Mario
 */
public class PanelPaletaColores extends javax.swing.JPanel {
    
    private JTextField botonActivo;
    private ArrayList<JTextField> coloresNuevos;

    /**
     * Constructor por defecto.
     */
    public PanelPaletaColores() {
        initComponents();
        botonActivo = botonContorno;
        coloresNuevos = new ArrayList<JTextField>();
        coloresNuevos.add(botonColor1);
        coloresNuevos.add(botonColor2);
        coloresNuevos.add(botonColor3);
        coloresNuevos.add(botonColor4);
        coloresNuevos.add(botonColor5);
        coloresNuevos.add(botonColor6);
        coloresNuevos.add(botonColor7);
        coloresNuevos.add(botonColor8);
    }

    /**
     * Obtiene el boton de color básico amarillo.
     * 
     * @return Botón de color amarillo.
     */
    public JTextField getBotonAmarillo() {
        return botonAmarillo;
    }

    /**
     * Obtiene el botón de color básico azul.
     * 
     * @return Botón de color azul.
     */
    public JTextField getBotonAzul() {
        return botonAzul;
    }

    /**
     * Obtiene el botón de color básico blanco.
     * 
     * @return Botón de color blanco.
     */
    public JTextField getBotonBlanco() {
        return botonBlanco;
    }

    /**
     * Obtiene el botón de color de fondo.
     * 
     * @return Botón de color de fondo.
     */
    public JTextField getBotonFondo() {
        return botonFondo;
    }

    /**
     * Modifica el color del botón de color de fondo.
     * 
     * @param color Color de fondo.
     */
    public void setBotonFondo(Color color) {
        botonFondo.setBackground(color);
    }

    /**
     * Obtiene el botón de color de frente.
     * 
     * @return Botón de color de frente.
     */
    public JTextField getBotonFrente() {
        return botonFrente;
    }

    /**
     * Modifica el color del botón de color de frente.
     * 
     * @param color Color de frente.
     */
    public void setBotonFrente(Color color) {
        botonFrente.setBackground(color);
    }

    /**
     * Obtiene el botón de color de contorno.
     * 
     * @return Botón de color de contorno.
     */
    public JTextField getBotonContorno() {
        return botonContorno;
    }

    /**
     * Modifica el color del botón de color de contorno.
     * 
     * @param color Color de contorno.
     */
    public void setBotonContorno(Color color) {
        botonContorno.setBackground(color);
    }

    /**
     * Obtiene el botón de color básico negro.
     * 
     * @return Botón de color negro.
     */
    public JTextField getBotonNegro() {
        return botonNegro;
    }

    /**
     * Obtiene el botón de color básico rojo.
     * 
     * @return Botón de color rojo.
     */
    public JTextField getBotonRojo() {
        return botonRojo;
    }

    /**
     * Obtiene el botón de color básico verde.
     * 
     * @return Botón de color verde.
     */
    public JTextField getBotonVerde() {
        return botonVerde;
    }
    
    /**
     * Obtiene el botón activo.
     * 
     * @return Botón de color activo. Puede ser el botón de contorno, de fondo o de frente.
     */
    public JTextField getBotonActivo(){
        return botonActivo;
    }

    /**
     * Obtiene el i-ésimo botón personalizable.
     * 
     * @param i Índice del botón personalizable.
     * @return I-ésimo botón personalizable.
     */
    public JTextField getBotonColorPersonalizable(int i) {
        return coloresNuevos.get(i);
    }
    
    /**
     * Obtiene el botón que muestra la paleta avanzada.
     * 
     * @return Botón de paleta avanzada.
     */
    public JLabel getBotonPaleta() {
        return botonPaleta;
    }

    /**
     * Obtiene la lista de botones personalizables.
     * 
     * @return Lista de botones personalizables.
     */
    public ArrayList<JTextField> getColoresNuevos() {
        return coloresNuevos;
    }

    /**
     * Modifica el botón que está activo en la paleta.
     * 
     * @param botonActivo Botón activo actualmente.
     */
    public void setBotonActivo(JTextField botonActivo){
        this.botonActivo = botonActivo;
        JTextField botonNoActivo = botonFondo;
        JTextField botonNoActivo2 = botonFrente;
        switch (botonActivo.getName()) {
            case "colorFondo":
                botonNoActivo = botonFrente;
                botonNoActivo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new Color(204,204,204), new Color(204,204,204)));
                botonNoActivo2 = botonContorno;
                botonNoActivo2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new Color(204,204,204), new Color(204,204,204)));
                break;
            case "colorFrente":
                botonNoActivo = botonFondo;
                botonNoActivo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new Color(204,204,204), new Color(204,204,204)));
                botonNoActivo2 = botonContorno;
                botonNoActivo2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new Color(204,204,204), new Color(204,204,204)));
                break;
            case "contorno":
                botonNoActivo = botonFondo;
                botonNoActivo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new Color(204,204,204), new Color(204,204,204)));
                botonNoActivo2 = botonFrente;
                botonNoActivo2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new Color(204,204,204), new Color(204,204,204)));
                break;
        }
        botonActivo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new Color(102,102,102), new Color(102,102,102)));
        

    }

    /**
     * Obtiene el dialogo de la paleta avanzada.
     * 
     * @return Dialogo de la paleta avanzada.
     */
    public JDialog getDialogoPaleta() {
        return dialogoPaleta;
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogoPaleta = new javax.swing.JDialog();
        botonContorno = new javax.swing.JTextField();
        botonFondo = new javax.swing.JTextField();
        botonFrente = new javax.swing.JTextField();
        botonNegro = new javax.swing.JTextField();
        botonRojo = new javax.swing.JTextField();
        botonAzul = new javax.swing.JTextField();
        botonBlanco = new javax.swing.JTextField();
        botonAmarillo = new javax.swing.JTextField();
        botonVerde = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        botonColor1 = new javax.swing.JTextField();
        botonColor2 = new javax.swing.JTextField();
        botonColor3 = new javax.swing.JTextField();
        botonColor4 = new javax.swing.JTextField();
        botonColor5 = new javax.swing.JTextField();
        botonColor6 = new javax.swing.JTextField();
        botonColor7 = new javax.swing.JTextField();
        botonColor8 = new javax.swing.JTextField();
        botonPaleta = new javax.swing.JLabel();

        javax.swing.GroupLayout dialogoPaletaLayout = new javax.swing.GroupLayout(dialogoPaleta.getContentPane());
        dialogoPaleta.getContentPane().setLayout(dialogoPaletaLayout);
        dialogoPaletaLayout.setHorizontalGroup(
            dialogoPaletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        dialogoPaletaLayout.setVerticalGroup(
            dialogoPaletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(50, 800));

        botonContorno.setBackground(new java.awt.Color(0, 0, 0));
        botonContorno.setToolTipText("Color de contorno");
        botonContorno.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        botonContorno.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonContorno.setFocusable(false);
        botonContorno.setName("contorno"); // NOI18N
        botonContorno.setPreferredSize(new java.awt.Dimension(30, 15));

        botonFondo.setToolTipText("Color de fondo");
        botonFondo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        botonFondo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonFondo.setFocusable(false);
        botonFondo.setName("colorFondo"); // NOI18N
        botonFondo.setPreferredSize(new java.awt.Dimension(15, 15));

        botonFrente.setBackground(new java.awt.Color(0, 0, 0));
        botonFrente.setToolTipText("Color de fondo 2");
        botonFrente.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        botonFrente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonFrente.setFocusable(false);
        botonFrente.setName("colorFrente"); // NOI18N
        botonFrente.setPreferredSize(new java.awt.Dimension(15, 15));

        botonNegro.setBackground(new java.awt.Color(0, 0, 0));
        botonNegro.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        botonNegro.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonNegro.setFocusable(false);
        botonNegro.setName("Negro"); // NOI18N
        botonNegro.setPreferredSize(new java.awt.Dimension(30, 30));

        botonRojo.setBackground(new java.awt.Color(255, 0, 0));
        botonRojo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        botonRojo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonRojo.setFocusable(false);
        botonRojo.setName("Rojo"); // NOI18N
        botonRojo.setPreferredSize(new java.awt.Dimension(30, 30));

        botonAzul.setBackground(new java.awt.Color(0, 0, 255));
        botonAzul.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        botonAzul.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonAzul.setFocusable(false);
        botonAzul.setName("Azul"); // NOI18N
        botonAzul.setPreferredSize(new java.awt.Dimension(30, 30));

        botonBlanco.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        botonBlanco.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonBlanco.setFocusable(false);
        botonBlanco.setName("Blanco"); // NOI18N
        botonBlanco.setPreferredSize(new java.awt.Dimension(30, 30));

        botonAmarillo.setBackground(new java.awt.Color(255, 255, 0));
        botonAmarillo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        botonAmarillo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonAmarillo.setFocusable(false);
        botonAmarillo.setName("Amarillo"); // NOI18N
        botonAmarillo.setPreferredSize(new java.awt.Dimension(30, 30));

        botonVerde.setBackground(new java.awt.Color(0, 255, 0));
        botonVerde.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        botonVerde.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonVerde.setFocusable(false);
        botonVerde.setName("Verde"); // NOI18N
        botonVerde.setPreferredSize(new java.awt.Dimension(30, 30));

        botonColor1.setBackground(new java.awt.Color(204, 204, 204));
        botonColor1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        botonColor1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonColor1.setFocusable(false);
        botonColor1.setName("botonColor1"); // NOI18N
        botonColor1.setPreferredSize(new java.awt.Dimension(30, 30));

        botonColor2.setBackground(new java.awt.Color(204, 204, 204));
        botonColor2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        botonColor2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonColor2.setFocusable(false);
        botonColor2.setName("botonColor2"); // NOI18N
        botonColor2.setPreferredSize(new java.awt.Dimension(30, 30));

        botonColor3.setBackground(new java.awt.Color(204, 204, 204));
        botonColor3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        botonColor3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonColor3.setFocusable(false);
        botonColor3.setName("botonColor3"); // NOI18N
        botonColor3.setPreferredSize(new java.awt.Dimension(30, 30));

        botonColor4.setBackground(new java.awt.Color(204, 204, 204));
        botonColor4.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        botonColor4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonColor4.setFocusable(false);
        botonColor4.setName("botonColor3"); // NOI18N
        botonColor4.setPreferredSize(new java.awt.Dimension(30, 30));

        botonColor5.setBackground(new java.awt.Color(204, 204, 204));
        botonColor5.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        botonColor5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonColor5.setFocusable(false);
        botonColor5.setName("botonColor1"); // NOI18N
        botonColor5.setPreferredSize(new java.awt.Dimension(30, 30));

        botonColor6.setBackground(new java.awt.Color(204, 204, 204));
        botonColor6.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        botonColor6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonColor6.setFocusable(false);
        botonColor6.setName("botonColor2"); // NOI18N
        botonColor6.setPreferredSize(new java.awt.Dimension(30, 30));

        botonColor7.setBackground(new java.awt.Color(204, 204, 204));
        botonColor7.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        botonColor7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonColor7.setFocusable(false);
        botonColor7.setName("botonColor1"); // NOI18N
        botonColor7.setPreferredSize(new java.awt.Dimension(30, 30));

        botonColor8.setBackground(new java.awt.Color(204, 204, 204));
        botonColor8.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        botonColor8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonColor8.setFocusable(false);
        botonColor8.setName("botonColor2"); // NOI18N
        botonColor8.setPreferredSize(new java.awt.Dimension(30, 30));

        botonPaleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atributos/paleta.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(botonColor8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(botonColor7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(botonPaleta))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonColor4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(botonColor6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(botonColor5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(botonColor3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonColor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonColor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(botonContorno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonVerde, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonAmarillo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonBlanco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonAzul, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonRojo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonNegro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(botonFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(botonFrente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(10, 10, 10))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonContorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addComponent(botonFrente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonNegro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonRojo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonAzul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonBlanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonAmarillo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonVerde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonColor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonColor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonColor3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonColor4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonColor5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonColor6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonColor7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonColor8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonPaleta)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField botonAmarillo;
    private javax.swing.JTextField botonAzul;
    private javax.swing.JTextField botonBlanco;
    private javax.swing.JTextField botonColor1;
    private javax.swing.JTextField botonColor2;
    private javax.swing.JTextField botonColor3;
    private javax.swing.JTextField botonColor4;
    private javax.swing.JTextField botonColor5;
    private javax.swing.JTextField botonColor6;
    private javax.swing.JTextField botonColor7;
    private javax.swing.JTextField botonColor8;
    private javax.swing.JTextField botonContorno;
    private javax.swing.JTextField botonFondo;
    private javax.swing.JTextField botonFrente;
    private javax.swing.JTextField botonNegro;
    private javax.swing.JLabel botonPaleta;
    private javax.swing.JTextField botonRojo;
    private javax.swing.JTextField botonVerde;
    private javax.swing.JDialog dialogoPaleta;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
