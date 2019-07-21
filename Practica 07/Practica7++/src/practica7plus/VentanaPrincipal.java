package practica7plus;

import SM.MRC.util.Colores;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxRenderer;



/**
 *
 * @author Mario
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        miInitComponents();
    }

    public JLabel getBarraEstado() {
        return barraEstado;
    }

    public JLabel getLabelPosicion() {
        return labelPosicion;
    }

    public JPanel getPanelPosicion() {
        return panelPosicion;
    }

    public JToggleButton getBotonAlisar() {
        return botonAlisar;
    }

    public JToggleButton getBotonRelleno() {
        return botonRelleno;
    }

    public JToggleButton getBotonTransparencia() {
        return botonTransparencia;
    }
    
    public ButtonGroup getGrupoBarraHerramientas() {
        return grupoBarraHerramientas;
    }

    public ButtonGroup getGrupoColores() {
        return grupoColores;
    }

    public JCheckBoxMenuItem getItemAtributos() {
        return itemAtributos;
    }

    public JSpinner getSpinnerGrosor() {
        return spinnerGrosor;
    }

    public JComboBox getComboColor() {
        return comboColor;
    }
        
    private void miInitComponents(){
        
        class ColorListener implements ActionListener  {
            ColorListener() {}
            @Override
            public void actionPerformed(ActionEvent evt){
                JButton botonColor = (JButton) evt.getSource();
                getVentanaInternaActiva().getLienzo2D().setColor(Colores.valueOf(botonColor.getName()));
                getVentanaInternaActiva().getLienzo2D().repaint();
            }
        }
        ColorListener colorListener = new ColorListener();
        botonNegro.addActionListener(colorListener);
        botonRojo.addActionListener(colorListener);
        botonAzul.addActionListener(colorListener);
        botonBlanco.addActionListener(colorListener);
        botonAmarillo.addActionListener(colorListener);
        botonVerde.addActionListener(colorListener);
        
        class HerramientasListener extends MouseAdapter {
            HerramientasListener(){}
            @Override
            public void mouseClicked(MouseEvent evt){
                JToggleButton herramientaBoton = (JToggleButton) evt.getSource();
                getVentanaInternaActiva().getLienzo2D().setHerramienta(herramientaBoton.getName());
                getVentanaInternaActiva().getLienzo2D().setEditar(herramientaBoton.getName()=="Editar");
                barraEstado.setText(herramientaBoton.getName());
            }
        }
        HerramientasListener herramientasListener = new HerramientasListener();
        botonLapiz.addMouseListener(herramientasListener);
        botonLinea.addMouseListener(herramientasListener);
        botonRectangulo.addMouseListener(herramientasListener);
        botonElipse.addMouseListener(herramientasListener);
        botonEditar.addMouseListener(herramientasListener);

        class ButtonComboBoxRenderer extends BasicComboBoxRenderer implements ListCellRenderer {

            public ButtonComboBoxRenderer() {
                super();
            }

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof JButton) {
                    ((JButton) value).setPreferredSize(new Dimension(10, 25));
                    return (Component) value;
                } else {
                    setText((value == null) ? "" : value.toString());
                }
                return this;
            }
        }

        class ButtonComboBoxListener implements ActionListener {

            JComboBox combobox;
            JFrame frame;

            ButtonComboBoxListener(JFrame frame, JComboBox combobox) {
                this.frame = frame;
                this.combobox = combobox;
                combobox.setSelectedIndex(0);
            }

            public void actionPerformed(ActionEvent e) {
                Object selectedItem = combobox.getSelectedItem();
                if (selectedItem instanceof JButton) {
                    ((JButton) selectedItem).doClick();
                }
            }
        }

        comboColor.setRenderer(new ButtonComboBoxRenderer());
        comboColor.addActionListener(new ButtonComboBoxListener(this, comboColor));

    }
    
    public VentanaInterna getVentanaInternaActiva(){
        return (VentanaInterna)escritorio.getSelectedFrame();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBarraHerramientas = new javax.swing.ButtonGroup();
        grupoColores = new javax.swing.ButtonGroup();
        dialogoAbrir = new javax.swing.JDialog();
        fileChooserAbrir = new javax.swing.JFileChooser();
        dialogoGuardar = new javax.swing.JDialog();
        fileChooserGuardar = new javax.swing.JFileChooser();
        botonNegro = new javax.swing.JButton();
        botonRojo = new javax.swing.JButton();
        botonAzul = new javax.swing.JButton();
        botonBlanco = new javax.swing.JButton();
        botonAmarillo = new javax.swing.JButton();
        botonVerde = new javax.swing.JButton();
        panelSuperior = new javax.swing.JPanel();
        barraArchivo = new javax.swing.JToolBar();
        botonNuevo = new javax.swing.JButton();
        botonAbrir = new javax.swing.JButton();
        botonGuardar = new javax.swing.JButton();
        barraFormas = new javax.swing.JToolBar();
        separatorFormas = new javax.swing.JToolBar.Separator();
        botonLapiz = new javax.swing.JToggleButton();
        botonLinea = new javax.swing.JToggleButton();
        botonRectangulo = new javax.swing.JToggleButton();
        botonElipse = new javax.swing.JToggleButton();
        botonEditar = new javax.swing.JToggleButton();
        barraAtributos = new javax.swing.JToolBar();
        separador1 = new javax.swing.JToolBar.Separator();
        comboColor = new javax.swing.JComboBox(new Object[]{botonNegro,botonRojo,botonAzul,botonBlanco,botonAmarillo,botonVerde});
        separador2 = new javax.swing.JToolBar.Separator();
        spinnerGrosor = new javax.swing.JSpinner();
        botonRelleno = new javax.swing.JToggleButton();
        botonTransparencia = new javax.swing.JToggleButton();
        botonAlisar = new javax.swing.JToggleButton();
        panelPrincipal = new javax.swing.JPanel();
        escritorio = new javax.swing.JDesktopPane();
        panelInferior = new javax.swing.JPanel();
        barraEstado = new javax.swing.JLabel();
        panelPosicion = new javax.swing.JPanel();
        separador3 = new javax.swing.JToolBar.Separator();
        labelPosicion = new javax.swing.JLabel();
        menuPrincipal = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        itemNuevo = new javax.swing.JMenuItem();
        itemAbrir = new javax.swing.JMenuItem();
        itemGuardar = new javax.swing.JMenuItem();
        menuEditar = new javax.swing.JMenu();
        itemEstado = new javax.swing.JCheckBoxMenuItem();
        itemFormas = new javax.swing.JCheckBoxMenuItem();
        itemAtributos = new javax.swing.JCheckBoxMenuItem();

        FormListener formListener = new FormListener();

        dialogoAbrir.setSize(new java.awt.Dimension(643, 419));
        dialogoAbrir.getContentPane().setLayout(new javax.swing.BoxLayout(dialogoAbrir.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
        dialogoAbrir.getContentPane().add(fileChooserAbrir);

        dialogoGuardar.setSize(new java.awt.Dimension(643, 419));
        dialogoGuardar.getContentPane().setLayout(new javax.swing.BoxLayout(dialogoGuardar.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
        dialogoGuardar.getContentPane().add(fileChooserGuardar);

        botonNegro.setBackground(new java.awt.Color(0, 0, 0));
        grupoColores.add(botonNegro);
        botonNegro.setFocusable(false);
        botonNegro.setName("Negro"); // NOI18N

        botonRojo.setBackground(new java.awt.Color(255, 0, 0));
        grupoColores.add(botonRojo);
        botonRojo.setFocusable(false);
        botonRojo.setName("Rojo"); // NOI18N

        botonAzul.setBackground(new java.awt.Color(0, 0, 255));
        botonAzul.setAlignmentX(0.5F);
        grupoColores.add(botonAzul);
        botonAzul.setFocusable(false);
        botonAzul.setName("Azul"); // NOI18N

        botonBlanco.setBackground(new java.awt.Color(255, 255, 255));
        grupoColores.add(botonBlanco);
        botonBlanco.setFocusable(false);
        botonBlanco.setName("Blanco"); // NOI18N

        botonAmarillo.setBackground(new java.awt.Color(255, 255, 0));
        grupoColores.add(botonAmarillo);
        botonAmarillo.setFocusable(false);
        botonAmarillo.setName("Amarillo"); // NOI18N

        botonVerde.setBackground(new java.awt.Color(0, 255, 0));
        botonVerde.setAlignmentY(0.0F);
        grupoColores.add(botonVerde);
        botonVerde.setFocusable(false);
        botonVerde.setName("Verde"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PaintBásico2D++ MRC");
        addWindowListener(formListener);

        panelSuperior.setPreferredSize(new java.awt.Dimension(100, 34));
        panelSuperior.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        barraArchivo.setFloatable(false);
        barraArchivo.setRollover(true);
        barraArchivo.setPreferredSize(new java.awt.Dimension(100, 35));

        botonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo.png"))); // NOI18N
        botonNuevo.setToolTipText("Nuevo");
        botonNuevo.setFocusable(false);
        botonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonNuevo.addActionListener(formListener);
        barraArchivo.add(botonNuevo);

        botonAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/abrir.png"))); // NOI18N
        botonAbrir.setToolTipText("Abrir");
        botonAbrir.setFocusable(false);
        botonAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonAbrir.addActionListener(formListener);
        barraArchivo.add(botonAbrir);

        botonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardar.png"))); // NOI18N
        botonGuardar.setToolTipText("Guardar");
        botonGuardar.setFocusable(false);
        botonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonGuardar.addActionListener(formListener);
        barraArchivo.add(botonGuardar);

        panelSuperior.add(barraArchivo);

        barraFormas.setFloatable(false);
        barraFormas.setRollover(true);
        barraFormas.setMinimumSize(new java.awt.Dimension(118, 31));
        barraFormas.setPreferredSize(new java.awt.Dimension(165, 35));
        barraFormas.add(separatorFormas);

        grupoBarraHerramientas.add(botonLapiz);
        botonLapiz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/punto.png"))); // NOI18N
        botonLapiz.setSelected(true);
        botonLapiz.setToolTipText("Punto");
        botonLapiz.setFocusable(false);
        botonLapiz.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLapiz.setName("Punto"); // NOI18N
        botonLapiz.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFormas.add(botonLapiz);

        grupoBarraHerramientas.add(botonLinea);
        botonLinea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/linea.png"))); // NOI18N
        botonLinea.setToolTipText("Línea");
        botonLinea.setFocusable(false);
        botonLinea.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLinea.setName("Línea"); // NOI18N
        botonLinea.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFormas.add(botonLinea);

        grupoBarraHerramientas.add(botonRectangulo);
        botonRectangulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rectangulo.png"))); // NOI18N
        botonRectangulo.setToolTipText("Rectángulo");
        botonRectangulo.setFocusable(false);
        botonRectangulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRectangulo.setName("Rectángulo"); // NOI18N
        botonRectangulo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFormas.add(botonRectangulo);

        grupoBarraHerramientas.add(botonElipse);
        botonElipse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/elipse.png"))); // NOI18N
        botonElipse.setToolTipText("Elipse");
        botonElipse.setFocusable(false);
        botonElipse.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonElipse.setName("Elipse"); // NOI18N
        botonElipse.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFormas.add(botonElipse);

        grupoBarraHerramientas.add(botonEditar);
        botonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/seleccion.png"))); // NOI18N
        botonEditar.setToolTipText("Editar");
        botonEditar.setFocusable(false);
        botonEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonEditar.setName("Editar"); // NOI18N
        botonEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFormas.add(botonEditar);

        panelSuperior.add(barraFormas);

        barraAtributos.setFloatable(false);
        barraAtributos.setRollover(true);
        barraAtributos.setPreferredSize(new java.awt.Dimension(250, 35));
        barraAtributos.add(separador1);

        comboColor.setModel(new DefaultComboBoxModel(new Object[]{botonNegro,botonRojo,botonAzul,botonBlanco,botonAmarillo,botonVerde}));
        comboColor.setToolTipText("Color");
        comboColor.setPreferredSize(new java.awt.Dimension(50, 28));
        barraAtributos.add(comboColor);
        barraAtributos.add(separador2);

        spinnerGrosor.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinnerGrosor.setToolTipText("Grosor");
        spinnerGrosor.setMinimumSize(new java.awt.Dimension(40, 20));
        spinnerGrosor.setPreferredSize(new java.awt.Dimension(50, 30));
        spinnerGrosor.addChangeListener(formListener);
        barraAtributos.add(spinnerGrosor);

        botonRelleno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rellenar.png"))); // NOI18N
        botonRelleno.setToolTipText("Relleno");
        botonRelleno.setFocusable(false);
        botonRelleno.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRelleno.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonRelleno.addMouseListener(formListener);
        barraAtributos.add(botonRelleno);

        botonTransparencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/transparencia.png"))); // NOI18N
        botonTransparencia.setToolTipText("Transparencia");
        botonTransparencia.setFocusable(false);
        botonTransparencia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonTransparencia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonTransparencia.addMouseListener(formListener);
        barraAtributos.add(botonTransparencia);

        botonAlisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/alisar.png"))); // NOI18N
        botonAlisar.setToolTipText("Alisar");
        botonAlisar.setFocusable(false);
        botonAlisar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAlisar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonAlisar.addMouseListener(formListener);
        barraAtributos.add(botonAlisar);

        panelSuperior.add(barraAtributos);

        getContentPane().add(panelSuperior, java.awt.BorderLayout.NORTH);

        panelPrincipal.setMinimumSize(new java.awt.Dimension(100, 300));
        panelPrincipal.setPreferredSize(new java.awt.Dimension(741, 500));
        panelPrincipal.setLayout(new java.awt.BorderLayout());

        escritorio.setPreferredSize(new java.awt.Dimension(841, 200));

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 807, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 507, Short.MAX_VALUE)
        );

        panelPrincipal.add(escritorio, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelPrincipal, java.awt.BorderLayout.CENTER);

        panelInferior.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        panelInferior.setPreferredSize(new java.awt.Dimension(500, 30));
        panelInferior.setLayout(new java.awt.BorderLayout());

        barraEstado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        barraEstado.setText("Punto");
        barraEstado.setPreferredSize(new java.awt.Dimension(100, 30));
        panelInferior.add(barraEstado, java.awt.BorderLayout.CENTER);

        panelPosicion.setPreferredSize(new java.awt.Dimension(10, 30));
        panelPosicion.setLayout(new java.awt.BorderLayout());

        separador3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        panelPosicion.add(separador3, java.awt.BorderLayout.WEST);

        labelPosicion.setPreferredSize(new java.awt.Dimension(10, 30));
        panelPosicion.add(labelPosicion, java.awt.BorderLayout.CENTER);

        panelInferior.add(panelPosicion, java.awt.BorderLayout.EAST);

        getContentPane().add(panelInferior, java.awt.BorderLayout.SOUTH);

        menuArchivo.setText("Archivo");

        itemNuevo.setText("Nuevo");
        itemNuevo.addActionListener(formListener);
        menuArchivo.add(itemNuevo);

        itemAbrir.setText("Abrir");
        itemAbrir.addActionListener(formListener);
        menuArchivo.add(itemAbrir);

        itemGuardar.setText("Guardar");
        itemGuardar.addActionListener(formListener);
        menuArchivo.add(itemGuardar);

        menuPrincipal.add(menuArchivo);

        menuEditar.setText("Edicion");

        itemEstado.setSelected(true);
        itemEstado.setText("Ver barra de estado");
        itemEstado.addActionListener(formListener);
        menuEditar.add(itemEstado);

        itemFormas.setSelected(true);
        itemFormas.setText("Ver barra de formas");
        itemFormas.addActionListener(formListener);
        menuEditar.add(itemFormas);

        itemAtributos.setSelected(true);
        itemAtributos.setText("Ver barra de atributos");
        itemAtributos.addActionListener(formListener);
        menuEditar.add(itemAtributos);

        menuPrincipal.add(menuEditar);

        setJMenuBar(menuPrincipal);

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.MouseListener, java.awt.event.WindowListener, javax.swing.event.ChangeListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == botonNuevo) {
                VentanaPrincipal.this.botonNuevoActionPerformed(evt);
            }
            else if (evt.getSource() == botonAbrir) {
                VentanaPrincipal.this.botonAbrirActionPerformed(evt);
            }
            else if (evt.getSource() == botonGuardar) {
                VentanaPrincipal.this.botonGuardarActionPerformed(evt);
            }
            else if (evt.getSource() == itemNuevo) {
                VentanaPrincipal.this.itemNuevoActionPerformed(evt);
            }
            else if (evt.getSource() == itemAbrir) {
                VentanaPrincipal.this.itemAbrirActionPerformed(evt);
            }
            else if (evt.getSource() == itemGuardar) {
                VentanaPrincipal.this.itemGuardarActionPerformed(evt);
            }
            else if (evt.getSource() == itemEstado) {
                VentanaPrincipal.this.itemEstadoActionPerformed(evt);
            }
            else if (evt.getSource() == itemFormas) {
                VentanaPrincipal.this.itemFormasActionPerformed(evt);
            }
            else if (evt.getSource() == itemAtributos) {
                VentanaPrincipal.this.itemAtributosActionPerformed(evt);
            }
        }

        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == botonRelleno) {
                VentanaPrincipal.this.botonRellenoMouseClicked(evt);
            }
            else if (evt.getSource() == botonTransparencia) {
                VentanaPrincipal.this.botonTransparenciaMouseClicked(evt);
            }
            else if (evt.getSource() == botonAlisar) {
                VentanaPrincipal.this.botonAlisarMouseClicked(evt);
            }
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
        }

        public void windowActivated(java.awt.event.WindowEvent evt) {
        }

        public void windowClosed(java.awt.event.WindowEvent evt) {
        }

        public void windowClosing(java.awt.event.WindowEvent evt) {
        }

        public void windowDeactivated(java.awt.event.WindowEvent evt) {
        }

        public void windowDeiconified(java.awt.event.WindowEvent evt) {
        }

        public void windowIconified(java.awt.event.WindowEvent evt) {
        }

        public void windowOpened(java.awt.event.WindowEvent evt) {
            if (evt.getSource() == VentanaPrincipal.this) {
                VentanaPrincipal.this.formWindowOpened(evt);
            }
        }

        public void stateChanged(javax.swing.event.ChangeEvent evt) {
            if (evt.getSource() == spinnerGrosor) {
                VentanaPrincipal.this.spinnerGrosorStateChanged(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void itemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevoActionPerformed
        VentanaInterna vi = new VentanaInterna(this);
        // Mostrar las nuevas ventanas en cascada
        if(escritorio.getComponentCount()>0){
            // Obtener la posición de la última ventana
            Point2D p_ultima_vi = escritorio.getComponents()[0].getLocation();
            Point2D cascada = new Point((int)p_ultima_vi.getX()+25, (int)p_ultima_vi.getY()+25);
            vi.setLocation((Point) cascada);
        }
        escritorio.add(vi);
        vi.setVisible(true);
        
    }//GEN-LAST:event_itemNuevoActionPerformed

    private void itemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAbrirActionPerformed
        dialogoAbrir.setVisible(true); 
        dialogoAbrir.setTitle("Abrir");
        double x = this.getWidth()/2 - dialogoAbrir.getWidth()/2;
        double y = this.getHeight()/2 - dialogoAbrir.getHeight()/2;
        dialogoAbrir.setLocation(new Point((int)x,(int)y));
    }//GEN-LAST:event_itemAbrirActionPerformed

    private void itemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGuardarActionPerformed
        dialogoGuardar.setVisible(true); 
        dialogoGuardar.setTitle("Guardar");
        double x = this.getWidth()/2 - dialogoGuardar.getWidth()/2;
        double y = this.getHeight()/2 - dialogoGuardar.getHeight()/2;
        dialogoGuardar.setLocation(new Point((int)x,(int)y));
    }//GEN-LAST:event_itemGuardarActionPerformed

    private void itemEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEstadoActionPerformed
        barraEstado.setVisible(itemEstado.isSelected());
    }//GEN-LAST:event_itemEstadoActionPerformed

    private void itemFormasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFormasActionPerformed
        barraFormas.setVisible(itemFormas.isSelected());
    }//GEN-LAST:event_itemFormasActionPerformed

    private void itemAtributosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAtributosActionPerformed
        barraAtributos.setVisible(itemAtributos.isSelected());
    }//GEN-LAST:event_itemAtributosActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        VentanaInterna vi = new VentanaInterna(this);
        escritorio.add(vi);
        vi.setVisible(true);
        vi.setLocation(new Point(1,1));
    }//GEN-LAST:event_formWindowOpened

    private void spinnerGrosorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerGrosorStateChanged
        getVentanaInternaActiva().getLienzo2D().setGrosor((int) spinnerGrosor.getValue());
        getVentanaInternaActiva().getLienzo2D().repaint();
    }//GEN-LAST:event_spinnerGrosorStateChanged

    private void botonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoActionPerformed
        itemNuevoActionPerformed(evt);
    }//GEN-LAST:event_botonNuevoActionPerformed

    private void botonAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAbrirActionPerformed
        itemAbrirActionPerformed(evt);
    }//GEN-LAST:event_botonAbrirActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        itemGuardarActionPerformed(evt);
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void botonRellenoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRellenoMouseClicked
        getVentanaInternaActiva().getLienzo2D().setRelleno(botonRelleno.isSelected());
        getVentanaInternaActiva().getLienzo2D().repaint();
    }//GEN-LAST:event_botonRellenoMouseClicked

    private void botonTransparenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonTransparenciaMouseClicked
        getVentanaInternaActiva().getLienzo2D().setTransparencia(botonTransparencia.isSelected());
        getVentanaInternaActiva().getLienzo2D().repaint();
    }//GEN-LAST:event_botonTransparenciaMouseClicked

    private void botonAlisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAlisarMouseClicked
        getVentanaInternaActiva().getLienzo2D().setAlisar(botonAlisar.isSelected());
        getVentanaInternaActiva().getLienzo2D().repaint();
    }//GEN-LAST:event_botonAlisarMouseClicked

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar barraArchivo;
    private javax.swing.JToolBar barraAtributos;
    private javax.swing.JLabel barraEstado;
    private javax.swing.JToolBar barraFormas;
    private javax.swing.JButton botonAbrir;
    private javax.swing.JToggleButton botonAlisar;
    private javax.swing.JButton botonAmarillo;
    private javax.swing.JButton botonAzul;
    private javax.swing.JButton botonBlanco;
    private javax.swing.JToggleButton botonEditar;
    private javax.swing.JToggleButton botonElipse;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JToggleButton botonLapiz;
    private javax.swing.JToggleButton botonLinea;
    private javax.swing.JButton botonNegro;
    private javax.swing.JButton botonNuevo;
    private javax.swing.JToggleButton botonRectangulo;
    private javax.swing.JToggleButton botonRelleno;
    private javax.swing.JButton botonRojo;
    private javax.swing.JToggleButton botonTransparencia;
    private javax.swing.JButton botonVerde;
    private javax.swing.JComboBox comboColor;
    private javax.swing.JDialog dialogoAbrir;
    private javax.swing.JDialog dialogoGuardar;
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JFileChooser fileChooserAbrir;
    private javax.swing.JFileChooser fileChooserGuardar;
    private javax.swing.ButtonGroup grupoBarraHerramientas;
    private javax.swing.ButtonGroup grupoColores;
    private javax.swing.JMenuItem itemAbrir;
    private javax.swing.JCheckBoxMenuItem itemAtributos;
    private javax.swing.JCheckBoxMenuItem itemEstado;
    private javax.swing.JCheckBoxMenuItem itemFormas;
    private javax.swing.JMenuItem itemGuardar;
    private javax.swing.JMenuItem itemNuevo;
    private javax.swing.JLabel labelPosicion;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuEditar;
    private javax.swing.JMenuBar menuPrincipal;
    private javax.swing.JPanel panelInferior;
    private javax.swing.JPanel panelPosicion;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JToolBar.Separator separador1;
    private javax.swing.JToolBar.Separator separador2;
    private javax.swing.JToolBar.Separator separador3;
    private javax.swing.JToolBar.Separator separatorFormas;
    private javax.swing.JSpinner spinnerGrosor;
    // End of variables declaration//GEN-END:variables

}
