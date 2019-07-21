package practica10;

import SM.MRC.util.Colores;
import SM.MRC.util.Filtro;
import com.sun.glass.events.KeyEvent;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ByteLookupTable;
import java.awt.image.ColorModel;
import java.awt.image.ConvolveOp;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import sm.image.KernelProducer;
import sm.image.LookupTableProducer;



/**
 *
 * @author Mario
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private BufferedImage imgOriginal;  
    
    public VentanaPrincipal() {
        initComponents();
        miInitComponents();
        Dimension sizescreen = Toolkit.getDefaultToolkit().getScreenSize();
        double x = sizescreen.getWidth()/2 - this.getWidth()/2;
        double y = sizescreen.getHeight()/2 - this.getHeight()/2;
        this.setLocation(new Point((int)x,(int)y));
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
                getVentanaInternaActiva().getLienzo().setColor(Colores.valueOf(botonColor.getName()));
                getVentanaInternaActiva().getLienzo().repaint();
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
                getVentanaInternaActiva().getLienzo().setHerramienta(herramientaBoton.getName());
                getVentanaInternaActiva().getLienzo().setEditar(herramientaBoton.getName()=="Editar");
                barraEstado.setText(herramientaBoton.getName());
            }
        }
        HerramientasListener herramientasListener = new HerramientasListener();
        botonLapiz.addMouseListener(herramientasListener);
        botonLinea.addMouseListener(herramientasListener);
        botonRectangulo.addMouseListener(herramientasListener);
        botonElipse.addMouseListener(herramientasListener);
        botonEditar.addMouseListener(herramientasListener);
        
        class TextFieldsTamañoListener extends KeyAdapter {

            TextFieldsTamañoListener() {}

            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JFormattedTextField tf = (JFormattedTextField) evt.getSource();
                int i = tf.getCaretPosition();
                String textBackspace, textDelete;

                if (evt.getKeyCode() == KeyEvent.VK_BACKSPACE && i > 0) {
                    textBackspace = tf.getText().substring(0, i - 1) + tf.getText().substring(i, tf.getText().length());
                    if (Integer.parseInt(tf.getText()) < 10 || Integer.valueOf(textBackspace) == 0) {
                        evt.consume();
                    }
                }

                if (evt.getKeyCode() == KeyEvent.VK_DELETE && i < tf.getText().length()) {
                    textDelete = tf.getText().substring(0, i) + tf.getText().substring(i + 1, tf.getText().length());
                    if (Integer.parseInt(tf.getText()) < 10 || Integer.valueOf(textDelete) == 0) {
                        evt.consume();
                    }
                }
                
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dialogoTamaño.dispose();
                }

                
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JFormattedTextField tf = (JFormattedTextField) evt.getSource();
                
                if (evt.getKeyChar() == '0' && tf.getCaretPosition() == 0) {
                    evt.consume();
                }

                if (!Character.isDigit(evt.getKeyChar())) {
                    evt.consume();
                } else if (Integer.parseInt(tf.getText()) > 999) {
                    evt.consume();
                }
            }
        }

        TextFieldsTamañoListener tfTamañoListener = new TextFieldsTamañoListener();
        tfAncho.addKeyListener(tfTamañoListener);
        tfAlto.addKeyListener(tfTamañoListener);

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
        
        mostrarDialogoCentro(fileChooserAbrir);
        filtrarExtensionesImagenFileChooser(fileChooserAbrir);
        mostrarDialogoCentro(fileChooserGuardar);
        filtrarExtensionesImagenFileChooser(fileChooserGuardar);
        
    }
    
    public VentanaInterna getVentanaInternaActiva(){
        return (VentanaInterna)escritorio.getSelectedFrame();
    }
    
    private BufferedImage copiaImagen(BufferedImage img){
        ColorModel cm = img.getColorModel();
        WritableRaster raster = img.copyData(null);
        boolean alfaPre = img.isAlphaPremultiplied();
        return new BufferedImage(cm,raster,alfaPre,null);
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
        fileChooserAbrir = new javax.swing.JFileChooser();
        fileChooserGuardar = new javax.swing.JFileChooser();
        botonNegro = new javax.swing.JButton();
        botonRojo = new javax.swing.JButton();
        botonAzul = new javax.swing.JButton();
        botonBlanco = new javax.swing.JButton();
        botonAmarillo = new javax.swing.JButton();
        botonVerde = new javax.swing.JButton();
        dialogoTamaño = new javax.swing.JDialog(this);
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        botonEscalar = new javax.swing.JButton();
        botonRedimensionar = new javax.swing.JButton();
        botonCancelarTamaño = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfAncho = new javax.swing.JFormattedTextField();
        tfAlto = new javax.swing.JFormattedTextField();
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
        barraAtributos2 = new javax.swing.JToolBar();
        jPanel5 = new javax.swing.JPanel();
        panelBrillo = new javax.swing.JPanel();
        panelLabelBrillo = new javax.swing.JPanel();
        labelBrillo = new javax.swing.JLabel();
        panelSliderBrillo = new javax.swing.JPanel();
        sliderBrillo = new javax.swing.JSlider();
        labelSliderBrillo = new javax.swing.JLabel();
        panelFiltro = new javax.swing.JPanel();
        panelLabelFiltro = new javax.swing.JPanel();
        labelFiltro = new javax.swing.JLabel();
        panelComboFiltro = new javax.swing.JPanel();
        comboFiltro = new javax.swing.JComboBox<>();
        panelContraste = new javax.swing.JPanel();
        panelLabelContraste = new javax.swing.JPanel();
        labelContrasete = new javax.swing.JLabel();
        panelComboContraste = new javax.swing.JPanel();
        botonContraste = new javax.swing.JButton();
        botonIluminar = new javax.swing.JButton();
        botonOscurecer = new javax.swing.JButton();
        panelSeno = new javax.swing.JPanel();
        panelLabelSeno = new javax.swing.JPanel();
        panelComboSeno = new javax.swing.JPanel();
        botonSeno = new javax.swing.JButton();
        panelRotacion = new javax.swing.JPanel();
        panelLabelRotacion = new javax.swing.JPanel();
        labelRotacion = new javax.swing.JLabel();
        panelComboRotacion = new javax.swing.JPanel();
        sliderRotacion = new javax.swing.JSlider();
        panelBotonesRotacion = new javax.swing.JPanel();
        botonRotacion90 = new javax.swing.JButton();
        botonRotacion180 = new javax.swing.JButton();
        botonRotacion270 = new javax.swing.JButton();
        panelEscala = new javax.swing.JPanel();
        panelLabelEscala = new javax.swing.JPanel();
        labelEscala = new javax.swing.JLabel();
        panelComboEscala = new javax.swing.JPanel();
        botonEscalaAumentar = new javax.swing.JButton();
        botonEscalaReducir = new javax.swing.JButton();
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
        itemBorde = new javax.swing.JCheckBoxMenuItem();
        itemEstado = new javax.swing.JCheckBoxMenuItem();
        itemFormas = new javax.swing.JCheckBoxMenuItem();
        itemAtributos = new javax.swing.JCheckBoxMenuItem();
        menuImagen = new javax.swing.JMenu();
        itemTamaño = new javax.swing.JMenuItem();
        itemRescaleOp = new javax.swing.JMenuItem();
        itemConvolveOp = new javax.swing.JMenuItem();
        itemAffineTransformOp = new javax.swing.JMenuItem();
        itemLookupOp = new javax.swing.JMenuItem();

        FormListener formListener = new FormListener();

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

        dialogoTamaño.setIconImages(null);
        dialogoTamaño.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        dialogoTamaño.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        dialogoTamaño.setResizable(false);
        dialogoTamaño.setSize(new java.awt.Dimension(360, 260));
        dialogoTamaño.addKeyListener(formListener);

        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jSeparator1, java.awt.BorderLayout.NORTH);

        jPanel4.setPreferredSize(new java.awt.Dimension(400, 50));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonEscalar.setText("Escalar");
        botonEscalar.addActionListener(formListener);
        jPanel4.add(botonEscalar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 70, -1));

        botonRedimensionar.setText("Redimensionar");
        botonRedimensionar.addActionListener(formListener);
        jPanel4.add(botonRedimensionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 120, -1));

        botonCancelarTamaño.setText("Cancelar");
        botonCancelarTamaño.addActionListener(formListener);
        jPanel4.add(botonCancelarTamaño, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        dialogoTamaño.getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/image_resize.png"))); // NOI18N
        jPanel2.add(jLabel2, java.awt.BorderLayout.CENTER);

        dialogoTamaño.getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_START);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Ancho:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Alto");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("px");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("px");

        tfAncho.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("####"))));
        tfAncho.setFocusCycleRoot(true);
        tfAncho.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfAncho.setPreferredSize(new java.awt.Dimension(50, 25));

        tfAlto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        tfAlto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfAlto.setPreferredSize(new java.awt.Dimension(50, 25));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfAlto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfAncho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(tfAncho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(tfAlto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        dialogoTamaño.getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PaintBásico2D");
        setPreferredSize(new java.awt.Dimension(1040, 700));
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
            .addGap(0, 1020, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE)
        );

        panelPrincipal.add(escritorio, java.awt.BorderLayout.CENTER);

        barraAtributos2.setRollover(true);
        barraAtributos2.setPreferredSize(new java.awt.Dimension(1020, 90));

        jPanel5.setPreferredSize(new java.awt.Dimension(1090, 88));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 5));

        panelBrillo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelBrillo.setPreferredSize(new java.awt.Dimension(200, 79));
        panelBrillo.setLayout(new java.awt.BorderLayout());

        panelLabelBrillo.setPreferredSize(new java.awt.Dimension(200, 18));

        labelBrillo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelBrillo.setText("Brillo");
        labelBrillo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labelBrillo.setPreferredSize(new java.awt.Dimension(29, 25));
        labelBrillo.setRequestFocusEnabled(false);

        javax.swing.GroupLayout panelLabelBrilloLayout = new javax.swing.GroupLayout(panelLabelBrillo);
        panelLabelBrillo.setLayout(panelLabelBrilloLayout);
        panelLabelBrilloLayout.setHorizontalGroup(
            panelLabelBrilloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelBrillo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelLabelBrilloLayout.setVerticalGroup(
            panelLabelBrilloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLabelBrilloLayout.createSequentialGroup()
                .addComponent(labelBrillo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelBrillo.add(panelLabelBrillo, java.awt.BorderLayout.NORTH);

        panelSliderBrillo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        panelSliderBrillo.setAlignmentY(2.0F);
        panelSliderBrillo.setPreferredSize(new java.awt.Dimension(200, 50));
        panelSliderBrillo.setLayout(new java.awt.BorderLayout());

        sliderBrillo.setMaximum(255);
        sliderBrillo.setMinimum(-255);
        sliderBrillo.setValue(0);
        sliderBrillo.addChangeListener(formListener);
        sliderBrillo.addFocusListener(formListener);
        panelSliderBrillo.add(sliderBrillo, java.awt.BorderLayout.CENTER);

        labelSliderBrillo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSliderBrillo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        panelSliderBrillo.add(labelSliderBrillo, java.awt.BorderLayout.SOUTH);

        panelBrillo.add(panelSliderBrillo, java.awt.BorderLayout.CENTER);

        jPanel5.add(panelBrillo);

        panelFiltro.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelFiltro.setPreferredSize(new java.awt.Dimension(100, 79));
        panelFiltro.setLayout(new java.awt.BorderLayout());

        panelLabelFiltro.setPreferredSize(new java.awt.Dimension(120, 18));
        panelLabelFiltro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelFiltro.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelFiltro.setText("Filtro");
        labelFiltro.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labelFiltro.setPreferredSize(new java.awt.Dimension(38, 25));
        panelLabelFiltro.add(labelFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 20));

        panelFiltro.add(panelLabelFiltro, java.awt.BorderLayout.NORTH);

        panelComboFiltro.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        panelComboFiltro.setAlignmentY(2.0F);
        panelComboFiltro.setPreferredSize(new java.awt.Dimension(120, 50));
        panelComboFiltro.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 8));

        comboFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Media", "Binomial", "Enfoque", "Relieve", "Lapaciano" }));
        comboFiltro.setPreferredSize(new java.awt.Dimension(80, 35));
        comboFiltro.addActionListener(formListener);
        panelComboFiltro.add(comboFiltro);

        panelFiltro.add(panelComboFiltro, java.awt.BorderLayout.CENTER);

        jPanel5.add(panelFiltro);

        panelContraste.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelContraste.setPreferredSize(new java.awt.Dimension(160, 79));
        panelContraste.setLayout(new java.awt.BorderLayout());

        panelLabelContraste.setPreferredSize(new java.awt.Dimension(120, 18));
        panelLabelContraste.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelContrasete.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelContrasete.setText("Contraste");
        labelContrasete.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labelContrasete.setPreferredSize(new java.awt.Dimension(38, 25));
        panelLabelContraste.add(labelContrasete, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 20));

        panelContraste.add(panelLabelContraste, java.awt.BorderLayout.NORTH);

        panelComboContraste.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        panelComboContraste.setAlignmentY(2.0F);
        panelComboContraste.setPreferredSize(new java.awt.Dimension(180, 50));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 8);
        flowLayout1.setAlignOnBaseline(true);
        panelComboContraste.setLayout(flowLayout1);

        botonContraste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/contraste.png"))); // NOI18N
        botonContraste.setPreferredSize(new java.awt.Dimension(48, 35));
        botonContraste.addActionListener(formListener);
        panelComboContraste.add(botonContraste);

        botonIluminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/iluminar.png"))); // NOI18N
        botonIluminar.setPreferredSize(new java.awt.Dimension(48, 35));
        botonIluminar.addActionListener(formListener);
        panelComboContraste.add(botonIluminar);

        botonOscurecer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/oscurecer.png"))); // NOI18N
        botonOscurecer.setPreferredSize(new java.awt.Dimension(48, 35));
        botonOscurecer.addActionListener(formListener);
        panelComboContraste.add(botonOscurecer);

        panelContraste.add(panelComboContraste, java.awt.BorderLayout.CENTER);

        jPanel5.add(panelContraste);

        panelSeno.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelSeno.setPreferredSize(new java.awt.Dimension(80, 79));
        panelSeno.setLayout(new java.awt.BorderLayout());

        panelLabelSeno.setPreferredSize(new java.awt.Dimension(120, 18));
        panelLabelSeno.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelSeno.add(panelLabelSeno, java.awt.BorderLayout.NORTH);

        panelComboSeno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        panelComboSeno.setAlignmentY(2.0F);
        panelComboSeno.setPreferredSize(new java.awt.Dimension(180, 50));
        java.awt.FlowLayout flowLayout3 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 8);
        flowLayout3.setAlignOnBaseline(true);
        panelComboSeno.setLayout(flowLayout3);

        botonSeno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/sinusoidal.png"))); // NOI18N
        botonSeno.setPreferredSize(new java.awt.Dimension(48, 35));
        botonSeno.addActionListener(formListener);
        panelComboSeno.add(botonSeno);

        panelSeno.add(panelComboSeno, java.awt.BorderLayout.CENTER);

        jPanel5.add(panelSeno);

        panelRotacion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelRotacion.setPreferredSize(new java.awt.Dimension(280, 79));
        panelRotacion.setLayout(new java.awt.BorderLayout());

        panelLabelRotacion.setPreferredSize(new java.awt.Dimension(120, 18));
        panelLabelRotacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelRotacion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelRotacion.setText("Rotación");
        labelRotacion.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labelRotacion.setPreferredSize(new java.awt.Dimension(38, 25));
        panelLabelRotacion.add(labelRotacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 20));

        panelRotacion.add(panelLabelRotacion, java.awt.BorderLayout.NORTH);

        panelComboRotacion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        panelComboRotacion.setAlignmentY(2.0F);
        panelComboRotacion.setPreferredSize(new java.awt.Dimension(120, 50));

        sliderRotacion.setMaximum(360);
        sliderRotacion.setValue(0);
        sliderRotacion.setMinimumSize(new java.awt.Dimension(36, 20));
        sliderRotacion.setPreferredSize(new java.awt.Dimension(100, 20));
        sliderRotacion.addChangeListener(formListener);
        sliderRotacion.addFocusListener(formListener);
        panelComboRotacion.add(sliderRotacion);

        panelBotonesRotacion.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 5));

        botonRotacion90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rotacion90.png"))); // NOI18N
        botonRotacion90.setPreferredSize(new java.awt.Dimension(48, 35));
        botonRotacion90.addActionListener(formListener);
        panelBotonesRotacion.add(botonRotacion90);

        botonRotacion180.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rotacion180.png"))); // NOI18N
        botonRotacion180.setPreferredSize(new java.awt.Dimension(48, 35));
        botonRotacion180.addActionListener(formListener);
        panelBotonesRotacion.add(botonRotacion180);

        botonRotacion270.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rotacion270.png"))); // NOI18N
        botonRotacion270.setPreferredSize(new java.awt.Dimension(48, 35));
        botonRotacion270.addActionListener(formListener);
        panelBotonesRotacion.add(botonRotacion270);

        panelComboRotacion.add(panelBotonesRotacion);

        panelRotacion.add(panelComboRotacion, java.awt.BorderLayout.CENTER);

        jPanel5.add(panelRotacion);

        panelEscala.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelEscala.setPreferredSize(new java.awt.Dimension(120, 79));
        panelEscala.setLayout(new java.awt.BorderLayout());

        panelLabelEscala.setPreferredSize(new java.awt.Dimension(120, 18));
        panelLabelEscala.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelEscala.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelEscala.setText("Escala");
        labelEscala.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labelEscala.setPreferredSize(new java.awt.Dimension(38, 25));
        panelLabelEscala.add(labelEscala, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 20));

        panelEscala.add(panelLabelEscala, java.awt.BorderLayout.NORTH);

        panelComboEscala.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        panelComboEscala.setAlignmentY(2.0F);
        panelComboEscala.setPreferredSize(new java.awt.Dimension(120, 50));
        panelComboEscala.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 8));

        botonEscalaAumentar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/aumentar.png"))); // NOI18N
        botonEscalaAumentar.setPreferredSize(new java.awt.Dimension(48, 35));
        botonEscalaAumentar.addActionListener(formListener);
        panelComboEscala.add(botonEscalaAumentar);

        botonEscalaReducir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/disminuir.png"))); // NOI18N
        botonEscalaReducir.setPreferredSize(new java.awt.Dimension(48, 35));
        botonEscalaReducir.addActionListener(formListener);
        panelComboEscala.add(botonEscalaReducir);

        panelEscala.add(panelComboEscala, java.awt.BorderLayout.CENTER);

        jPanel5.add(panelEscala);

        barraAtributos2.add(jPanel5);

        panelPrincipal.add(barraAtributos2, java.awt.BorderLayout.SOUTH);

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

        itemBorde.setSelected(true);
        itemBorde.setText("Ver borde");
        itemBorde.addActionListener(formListener);
        menuEditar.add(itemBorde);

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

        menuImagen.setText("Imagen");

        itemTamaño.setText("Tamaño nueva imagen");
        itemTamaño.addActionListener(formListener);
        menuImagen.add(itemTamaño);

        itemRescaleOp.setText("RescaleOp");
        itemRescaleOp.addActionListener(formListener);
        menuImagen.add(itemRescaleOp);

        itemConvolveOp.setText("ConvolveOp");
        itemConvolveOp.addActionListener(formListener);
        menuImagen.add(itemConvolveOp);

        itemAffineTransformOp.setText("AffineTransformOp");
        itemAffineTransformOp.addActionListener(formListener);
        menuImagen.add(itemAffineTransformOp);

        itemLookupOp.setText("LookupOp");
        itemLookupOp.addActionListener(formListener);
        menuImagen.add(itemLookupOp);

        menuPrincipal.add(menuImagen);

        setJMenuBar(menuPrincipal);

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.FocusListener, java.awt.event.KeyListener, java.awt.event.MouseListener, java.awt.event.WindowListener, javax.swing.event.ChangeListener {
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
            else if (evt.getSource() == comboFiltro) {
                VentanaPrincipal.this.comboFiltroActionPerformed(evt);
            }
            else if (evt.getSource() == botonContraste) {
                VentanaPrincipal.this.botonContrasteActionPerformed(evt);
            }
            else if (evt.getSource() == botonIluminar) {
                VentanaPrincipal.this.botonIluminarActionPerformed(evt);
            }
            else if (evt.getSource() == botonOscurecer) {
                VentanaPrincipal.this.botonOscurecerActionPerformed(evt);
            }
            else if (evt.getSource() == botonSeno) {
                VentanaPrincipal.this.botonSenoActionPerformed(evt);
            }
            else if (evt.getSource() == botonRotacion90) {
                VentanaPrincipal.this.botonRotacion90ActionPerformed(evt);
            }
            else if (evt.getSource() == botonRotacion180) {
                VentanaPrincipal.this.botonRotacion180ActionPerformed(evt);
            }
            else if (evt.getSource() == botonRotacion270) {
                VentanaPrincipal.this.botonRotacion270ActionPerformed(evt);
            }
            else if (evt.getSource() == botonEscalaAumentar) {
                VentanaPrincipal.this.botonEscalaAumentarActionPerformed(evt);
            }
            else if (evt.getSource() == botonEscalaReducir) {
                VentanaPrincipal.this.botonEscalaReducirActionPerformed(evt);
            }
            else if (evt.getSource() == botonEscalar) {
                VentanaPrincipal.this.botonEscalarActionPerformed(evt);
            }
            else if (evt.getSource() == botonRedimensionar) {
                VentanaPrincipal.this.botonRedimensionarActionPerformed(evt);
            }
            else if (evt.getSource() == botonCancelarTamaño) {
                VentanaPrincipal.this.botonCancelarTamañoActionPerformed(evt);
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
            else if (evt.getSource() == itemBorde) {
                VentanaPrincipal.this.itemBordeActionPerformed(evt);
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
            else if (evt.getSource() == itemTamaño) {
                VentanaPrincipal.this.itemTamañoActionPerformed(evt);
            }
            else if (evt.getSource() == itemRescaleOp) {
                VentanaPrincipal.this.itemRescaleOpActionPerformed(evt);
            }
            else if (evt.getSource() == itemConvolveOp) {
                VentanaPrincipal.this.itemConvolveOpActionPerformed(evt);
            }
            else if (evt.getSource() == itemAffineTransformOp) {
                VentanaPrincipal.this.itemAffineTransformOpActionPerformed(evt);
            }
            else if (evt.getSource() == itemLookupOp) {
                VentanaPrincipal.this.itemLookupOpActionPerformed(evt);
            }
        }

        public void focusGained(java.awt.event.FocusEvent evt) {
            if (evt.getSource() == sliderBrillo) {
                VentanaPrincipal.this.sliderBrilloFocusGained(evt);
            }
            else if (evt.getSource() == sliderRotacion) {
                VentanaPrincipal.this.sliderRotacionFocusGained(evt);
            }
        }

        public void focusLost(java.awt.event.FocusEvent evt) {
            if (evt.getSource() == sliderBrillo) {
                VentanaPrincipal.this.sliderBrilloFocusLost(evt);
            }
            else if (evt.getSource() == sliderRotacion) {
                VentanaPrincipal.this.sliderRotacionFocusLost(evt);
            }
        }

        public void keyPressed(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == dialogoTamaño) {
                VentanaPrincipal.this.dialogoTamañoKeyPressed(evt);
            }
        }

        public void keyReleased(java.awt.event.KeyEvent evt) {
        }

        public void keyTyped(java.awt.event.KeyEvent evt) {
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
            else if (evt.getSource() == sliderBrillo) {
                VentanaPrincipal.this.sliderBrilloStateChanged(evt);
            }
            else if (evt.getSource() == sliderRotacion) {
                VentanaPrincipal.this.sliderRotacionStateChanged(evt);
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
        BufferedImage img;
        img = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
        img.getGraphics().fillRect(0, 0, vi.getLienzo().getWidth(), vi.getLienzo().getHeight());
        vi.getLienzo().setImagen(img);
    }//GEN-LAST:event_itemNuevoActionPerformed
    
    private void mostrarDialogoCentro(Container dialogo){
        double x = this.getWidth()/2 - dialogo.getWidth()/2 + this.getLocation().getX();
        double y = this.getHeight()/2 - dialogo.getHeight()/2 + this.getLocation().getY();
        dialogo.setLocation(new Point((int)x,(int)y));
    }
    
    private void filtrarExtensionesImagenFileChooser(JFileChooser fc){
        ArrayList<String> extensiones = new ArrayList();
        String todos = "";
        fc.removeChoosableFileFilter(fc.getFileFilter());
        for(String s:ImageIO.getReaderFormatNames()){
            s = s.toLowerCase();
            if(!extensiones.contains(s)){
                extensiones.add(s);
                fc.addChoosableFileFilter(new FileNameExtensionFilter("*."+s,s));
                todos = todos + "*."+s+"; ";
            }
        }
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Todos los archivos compatibles ("+todos.substring(0, todos.length()-2)+")",ImageIO.getReaderFormatNames()));
    }
    
    private void itemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAbrirActionPerformed
        
        int resp = fileChooserAbrir.showOpenDialog(this);
        if (resp == JFileChooser.APPROVE_OPTION) {
            try {
                File f = fileChooserAbrir.getSelectedFile();
                BufferedImage img = ImageIO.read(f);
                
                VentanaInterna vi = new VentanaInterna(this);
                
                vi.getLienzo().setImagen(img);
                this.escritorio.add(vi);
                vi.setTitle(f.getName());
                vi.setVisible(true);
                vi.repaint();
                System.out.println("break2 "+vi.getLienzo().getWidth()+" "+vi.getLienzo().getHeight());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al leer la imagen", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_itemAbrirActionPerformed

    private ArrayList<String> getExtensionesImagen(){
        ArrayList<String> extensiones = new ArrayList();
        for(String s:ImageIO.getReaderFormatNames()){
            s = s.toLowerCase();
            if(!extensiones.contains(s))
                extensiones.add(s);
        }
        return extensiones;
    }
    
    private void itemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGuardarActionPerformed
        
        VentanaInterna vi = (VentanaInterna) escritorio.getSelectedFrame();
        if (vi != null) {
            int resp = fileChooserGuardar.showSaveDialog(this);
            
            if (resp == JFileChooser.APPROVE_OPTION) {
                vi.getLienzo().setBorde(false);
                vi.getLienzo().repaint();
                try {
                    BufferedImage img = vi.getLienzo().getImagen(true);
                    if (img != null) {
                        File f = fileChooserGuardar.getSelectedFile();
                        String extension = f.getName().substring(f.getName().lastIndexOf(".")+1, f.getName().length());
                        if(getExtensionesImagen().contains(extension)){
                            ImageIO.write(img, extension, f);
                            vi.setTitle(f.getName());
                        }else
                            JOptionPane.showMessageDialog(this, "Formato no soportado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al guardarF la imagen", "Error", JOptionPane.ERROR_MESSAGE);
                }
                vi.getLienzo().setBorde(true);
                vi.getLienzo().repaint();
            }
        }
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
        BufferedImage img;
        img = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
        img.getGraphics().fillRect(0, 0, vi.getLienzo().getWidth(), vi.getLienzo().getHeight());
        vi.getLienzo().setImagen(img);
    }//GEN-LAST:event_formWindowOpened

    private void spinnerGrosorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerGrosorStateChanged
        getVentanaInternaActiva().getLienzo().setGrosor((int) spinnerGrosor.getValue());
        getVentanaInternaActiva().getLienzo().repaint();
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
        getVentanaInternaActiva().getLienzo().setRelleno(botonRelleno.isSelected());
        getVentanaInternaActiva().getLienzo().repaint();
    }//GEN-LAST:event_botonRellenoMouseClicked

    private void botonTransparenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonTransparenciaMouseClicked
        getVentanaInternaActiva().getLienzo().setTransparencia(botonTransparencia.isSelected());
        getVentanaInternaActiva().getLienzo().repaint();
    }//GEN-LAST:event_botonTransparenciaMouseClicked

    private void botonAlisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAlisarMouseClicked
        getVentanaInternaActiva().getLienzo().setAlisar(botonAlisar.isSelected());
        getVentanaInternaActiva().getLienzo().repaint();
    }//GEN-LAST:event_botonAlisarMouseClicked

    private void itemRescaleOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRescaleOpActionPerformed
        VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImagen();
            if (imgSource != null) {
                try {
                    RescaleOp rop = new RescaleOp(1.0F, 100.0F, null);
                    //RescaleOp rop = new RescaleOp(new float [] {1.0F,1.0F,1.0F,0.0F}, new float [] {100.0F,100.0F,100.0F,0.0F}, null);
                    rop.filter(imgSource, imgSource);
                    vi.getLienzo().repaint();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_itemRescaleOpActionPerformed

    private void itemConvolveOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConvolveOpActionPerformed
        VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImagen();
            if (imgSource != null) {
                try {
                    //ConvolveOp cop = new ConvolveOp(new Kernel(3,3,new float[] {0.1f,0.1f,0.1f,0.1f,0.2f,0.1f,0.1f,0.1f,0.1f}));
                    ConvolveOp cop = new ConvolveOp(KernelProducer.createKernel(KernelProducer.TYPE_MEDIA_3x3));
                    BufferedImage imgdest = cop.filter(imgSource, null);
                    vi.getLienzo().setImagen(imgdest);
                    vi.getLienzo().repaint();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_itemConvolveOpActionPerformed

    private void sliderBrilloFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sliderBrilloFocusGained
        VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
        if (vi != null) {
            imgOriginal = copiaImagen(vi.getLienzo().getImagen());
            labelSliderBrillo.setText("");
        }
    }//GEN-LAST:event_sliderBrilloFocusGained

    private void sliderBrilloStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderBrilloStateChanged
        VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImagen();
            if (imgSource != null) {
                try {

                    labelSliderBrillo.setText(String.valueOf(sliderBrillo.getValue()));
                    RescaleOp rop = new RescaleOp(1.0F, sliderBrillo.getValue(), null);
                    if(imgOriginal == null)
                        imgOriginal = copiaImagen(vi.getLienzo().getImagen());
                    rop.filter(imgOriginal, vi.getLienzo().getImagen());
                    vi.getLienzo().repaint();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_sliderBrilloStateChanged

    private void sliderBrilloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sliderBrilloFocusLost
        imgOriginal = null;
        sliderBrillo.setValue(0);
        labelSliderBrillo.setText("");
    }//GEN-LAST:event_sliderBrilloFocusLost

    private void botonCancelarTamañoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarTamañoActionPerformed
        dialogoTamaño.dispose(); 
    }//GEN-LAST:event_botonCancelarTamañoActionPerformed

    private void itemTamañoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTamañoActionPerformed
        mostrarDialogoCentro(dialogoTamaño);
        tfAncho.setValue(getVentanaInternaActiva().getLienzo().getWidth());
        tfAlto.setValue(getVentanaInternaActiva().getLienzo().getHeight());
        dialogoTamaño.setVisible(true);        
    }//GEN-LAST:event_itemTamañoActionPerformed

    private void botonEscalarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEscalarActionPerformed
        /*AffineTransform at = AffineTransform.getScaleInstance(Integer.parseInt(tfAncho.getText()), Integer.parseInt(tfAlto.getText()));
        AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        BufferedImage imgdest = atop.filter(imgSource, null);
        VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
        vi.getLienzo().setImagen(imgdest);
        vi.getLienzo().setSize(new Dimension(imgdest.getWidth(), imgdest.getHeight()));
        vi.getLienzo().repaint();
        */
        
        VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImagen();
            if (imgSource != null) {
                try {

                    AffineTransform at = AffineTransform.getScaleInstance(Integer.parseInt(tfAncho.getText())/imgSource.getWidth(), Integer.parseInt(tfAlto.getText())/imgSource.getHeight());
                    AffineTransformOp atop = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
                    System.out.println(Integer.parseInt(tfAncho.getText())+" "+ Integer.parseInt(tfAlto.getText()));
                    BufferedImage imgdest = atop.filter(imgSource, null);
                    vi.getLienzo().setImagen(imgdest);
                    vi.getLienzo().setSize(new Dimension(imgdest.getWidth(),imgdest.getHeight()));
                    vi.getLienzo().repaint();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_botonEscalarActionPerformed

    private void botonRedimensionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRedimensionarActionPerformed
        getVentanaInternaActiva().getLienzo().setPreferredSize(new Dimension(Integer.parseInt(tfAncho.getText()),Integer.parseInt(tfAlto.getText())));
        getVentanaInternaActiva().getLienzo().setSize(new Dimension(Integer.parseInt(tfAncho.getText()),Integer.parseInt(tfAlto.getText())));
        dialogoTamaño.dispose();
    }//GEN-LAST:event_botonRedimensionarActionPerformed

    private void itemBordeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBordeActionPerformed
        getVentanaInternaActiva().getLienzo().setBorde(itemBorde.isSelected());
        getVentanaInternaActiva().getLienzo().repaint();
    }//GEN-LAST:event_itemBordeActionPerformed

    private void comboFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFiltroActionPerformed
        VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImagen();
            if (imgSource != null) {
                try {
                    //ConvolveOp cop = new ConvolveOp(new Kernel(3,3,new float[] {0.1f,0.1f,0.1f,0.1f,0.2f,0.1f,0.1f,0.1f,0.1f}));
                    ConvolveOp cop = null;
                    switch(Filtro.valueOf((String) comboFiltro.getSelectedItem()).ordinal()){
                        case 0:
                            cop = new ConvolveOp(KernelProducer.createKernel(KernelProducer.TYPE_MEDIA_3x3));
                            break;
                        case 1:
                            cop = new ConvolveOp(KernelProducer.createKernel(KernelProducer.TYPE_BINOMIAL_3x3));
                            break;
                        case 2:
                            cop = new ConvolveOp(KernelProducer.createKernel(KernelProducer.TYPE_ENFOQUE_3x3));
                            break;
                        case 3:
                            cop = new ConvolveOp(KernelProducer.createKernel(KernelProducer.TYPE_RELIEVE_3x3));
                            break;
                        case 4:
                            cop = new ConvolveOp(KernelProducer.createKernel(KernelProducer.TYPE_LAPLACIANA_3x3));
                            break;
                    }
                     
                    BufferedImage imgdest = cop.filter(imgSource, null);
                    vi.getLienzo().setImagen(imgdest);
                    vi.getLienzo().repaint();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
        
    }//GEN-LAST:event_comboFiltroActionPerformed

    private void itemAffineTransformOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAffineTransformOpActionPerformed
        VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImagen();
            if (imgSource != null) {
                try {

                    AffineTransform at = AffineTransform.getScaleInstance(1.25, 1.25);
                    AffineTransformOp atop = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage imgdest = atop.filter(imgSource, null);
                    vi.getLienzo().setImagen(imgdest);
                    vi.getLienzo().setSize(new Dimension(imgdest.getWidth(),imgdest.getHeight()));
                    vi.getLienzo().repaint();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_itemAffineTransformOpActionPerformed

    private void itemLookupOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLookupOpActionPerformed
        VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImagen();
            if (imgSource != null) {
                try {
                    byte f[] = new byte[256];
                    for (int i = 0; i < 256; i++) {
                        f[i] = (byte) (255 - i); // Negativo
                    }
                    LookupTable lt = LookupTableProducer.sFuction(128.0,3.0);
                    LookupOp lop = new LookupOp(lt, null);
                    BufferedImage imgdest = lop.filter(imgSource, null);
                    vi.getLienzo().setImagen(imgdest);
                    vi.getLienzo().repaint();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_itemLookupOpActionPerformed

    private void dialogoTamañoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dialogoTamañoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_dialogoTamañoKeyPressed

    private void botonContrasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonContrasteActionPerformed
        VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImagen();
            if (imgSource != null) {
                try {
                    int type = LookupTableProducer.TYPE_SFUNCION;
                    LookupTable lt = LookupTableProducer.createLookupTable(type);
                    LookupOp lop = new LookupOp(lt, null);
                    // Imagen origen y destino iguales
                    lop.filter(imgSource, imgSource);
                    vi.repaint();
                } catch (Exception e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_botonContrasteActionPerformed

    private void botonIluminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIluminarActionPerformed
        VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImagen();
            if (imgSource != null) {
                try {
                    int type = LookupTableProducer.TYPE_LOGARITHM;
                    LookupTable lt = LookupTableProducer.createLookupTable(type);
                    LookupOp lop = new LookupOp(lt, null);
                    // Imagen origen y destino iguales
                    lop.filter(imgSource, imgSource);
                    vi.repaint();
                } catch (Exception e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_botonIluminarActionPerformed

    private void botonOscurecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOscurecerActionPerformed
        VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImagen();
            if (imgSource != null) {
                try {
                    int type = LookupTableProducer.TYPE_POWER;
                    LookupTable lt = LookupTableProducer.createLookupTable(type);
                    LookupOp lop = new LookupOp(lt, null);
                    // Imagen origen y destino iguales
                    lop.filter(imgSource, imgSource);
                    vi.repaint();
                } catch (Exception e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_botonOscurecerActionPerformed

    public static LookupTable seno(double w) {
        double K = 255.0;
        byte lt[] = new byte[256];
        lt[0] = 0;
        for (int l = 1; l < 256; l++) {
            lt[l] = (byte) (K * Math.sin(Math.toRadians(w*l)));
        }
        ByteLookupTable slt = new ByteLookupTable(0, lt);
        return slt;
    }
    
    private void botonSenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSenoActionPerformed
        VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImagen();
            if (imgSource != null) {
                try {
                    LookupTable lt = seno(180.0/255.0);
                    LookupOp lop = new LookupOp(lt, null);
                    // Imagen origen y destino iguales
                    lop.filter(imgSource, imgSource);
                    vi.repaint();
                } catch (Exception e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_botonSenoActionPerformed

    private void sliderRotacionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderRotacionStateChanged
        VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImagen();
            if (imgSource != null) {
                try {
                    AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(sliderRotacion.getValue()), imgSource.getWidth() / 2, imgSource.getHeight() / 2);
                    AffineTransformOp atop;
                    atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    if (imgOriginal == null)
                        imgOriginal = copiaImagen(vi.getLienzo().getImagen());
                    BufferedImage imgdest = atop.filter(imgOriginal, null);
                    vi.getLienzo().setImagen(imgdest);
                    vi.repaint();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_sliderRotacionStateChanged

    private void sliderRotacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sliderRotacionFocusGained
        VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
        if (vi != null) {
            imgOriginal = copiaImagen(vi.getLienzo().getImagen());
        }
    }//GEN-LAST:event_sliderRotacionFocusGained

    private void sliderRotacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sliderRotacionFocusLost
        imgOriginal = null;
        sliderRotacion.setValue(0);
    }//GEN-LAST:event_sliderRotacionFocusLost

    private void botonRotacion90ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRotacion90ActionPerformed
        VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImagen();
            if (imgSource != null) {
                try {
                    AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90.0), imgSource.getWidth() / 2, imgSource.getHeight() / 2);
                    AffineTransformOp atop; 
                    atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    imgOriginal = copiaImagen(vi.getLienzo().getImagen());
                    
                    BufferedImage imgdest = atop.filter(imgOriginal, null);
                    vi.getLienzo().setImagen(imgdest);
                    //vi.getLienzo().setSize(vi.getLienzo().getImagen().getWidth(),vi.getLienzo().getImagen().getHeight());
                    vi.repaint();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_botonRotacion90ActionPerformed

    private void botonRotacion180ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRotacion180ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonRotacion180ActionPerformed

    private void botonRotacion270ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRotacion270ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonRotacion270ActionPerformed

    private void botonEscalaAumentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEscalaAumentarActionPerformed
        VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImagen();
            if (imgSource != null) {
                try {

                    AffineTransform at = AffineTransform.getScaleInstance(1.25, 1.25);
                    AffineTransformOp atop = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage imgdest = atop.filter(imgSource, null);
                    vi.getLienzo().setImagen(imgdest);
                    vi.getLienzo().setSize(new Dimension(imgdest.getWidth(),imgdest.getHeight()));
                    vi.getLienzo().repaint();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_botonEscalaAumentarActionPerformed

    private void botonEscalaReducirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEscalaReducirActionPerformed
        VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImagen();
            if (imgSource != null) {
                try {

                    AffineTransform at = AffineTransform.getScaleInstance(0.75, 0.75);
                    AffineTransformOp atop = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage imgdest = atop.filter(imgSource, null);
                    vi.getLienzo().setImagen(imgdest);
                    vi.getLienzo().setSize(new Dimension(imgdest.getWidth(),imgdest.getHeight()));
                    vi.getLienzo().repaint();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_botonEscalaReducirActionPerformed

 
        
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar barraArchivo;
    private javax.swing.JToolBar barraAtributos;
    private javax.swing.JToolBar barraAtributos2;
    private javax.swing.JLabel barraEstado;
    private javax.swing.JToolBar barraFormas;
    private javax.swing.JButton botonAbrir;
    private javax.swing.JToggleButton botonAlisar;
    private javax.swing.JButton botonAmarillo;
    private javax.swing.JButton botonAzul;
    private javax.swing.JButton botonBlanco;
    private javax.swing.JButton botonCancelarTamaño;
    private javax.swing.JButton botonContraste;
    private javax.swing.JToggleButton botonEditar;
    private javax.swing.JToggleButton botonElipse;
    private javax.swing.JButton botonEscalaAumentar;
    private javax.swing.JButton botonEscalaReducir;
    private javax.swing.JButton botonEscalar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonIluminar;
    private javax.swing.JToggleButton botonLapiz;
    private javax.swing.JToggleButton botonLinea;
    private javax.swing.JButton botonNegro;
    private javax.swing.JButton botonNuevo;
    private javax.swing.JButton botonOscurecer;
    private javax.swing.JToggleButton botonRectangulo;
    private javax.swing.JButton botonRedimensionar;
    private javax.swing.JToggleButton botonRelleno;
    private javax.swing.JButton botonRojo;
    private javax.swing.JButton botonRotacion180;
    private javax.swing.JButton botonRotacion270;
    private javax.swing.JButton botonRotacion90;
    private javax.swing.JButton botonSeno;
    private javax.swing.JToggleButton botonTransparencia;
    private javax.swing.JButton botonVerde;
    private javax.swing.JComboBox comboColor;
    private javax.swing.JComboBox<String> comboFiltro;
    private javax.swing.JDialog dialogoTamaño;
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JFileChooser fileChooserAbrir;
    private javax.swing.JFileChooser fileChooserGuardar;
    private javax.swing.ButtonGroup grupoBarraHerramientas;
    private javax.swing.ButtonGroup grupoColores;
    private javax.swing.JMenuItem itemAbrir;
    private javax.swing.JMenuItem itemAffineTransformOp;
    private javax.swing.JCheckBoxMenuItem itemAtributos;
    private javax.swing.JCheckBoxMenuItem itemBorde;
    private javax.swing.JMenuItem itemConvolveOp;
    private javax.swing.JCheckBoxMenuItem itemEstado;
    private javax.swing.JCheckBoxMenuItem itemFormas;
    private javax.swing.JMenuItem itemGuardar;
    private javax.swing.JMenuItem itemLookupOp;
    private javax.swing.JMenuItem itemNuevo;
    private javax.swing.JMenuItem itemRescaleOp;
    private javax.swing.JMenuItem itemTamaño;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelBrillo;
    private javax.swing.JLabel labelContrasete;
    private javax.swing.JLabel labelEscala;
    private javax.swing.JLabel labelFiltro;
    private javax.swing.JLabel labelPosicion;
    private javax.swing.JLabel labelRotacion;
    private javax.swing.JLabel labelSliderBrillo;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuEditar;
    private javax.swing.JMenu menuImagen;
    private javax.swing.JMenuBar menuPrincipal;
    private javax.swing.JPanel panelBotonesRotacion;
    private javax.swing.JPanel panelBrillo;
    private javax.swing.JPanel panelComboContraste;
    private javax.swing.JPanel panelComboEscala;
    private javax.swing.JPanel panelComboFiltro;
    private javax.swing.JPanel panelComboRotacion;
    private javax.swing.JPanel panelComboSeno;
    private javax.swing.JPanel panelContraste;
    private javax.swing.JPanel panelEscala;
    private javax.swing.JPanel panelFiltro;
    private javax.swing.JPanel panelInferior;
    private javax.swing.JPanel panelLabelBrillo;
    private javax.swing.JPanel panelLabelContraste;
    private javax.swing.JPanel panelLabelEscala;
    private javax.swing.JPanel panelLabelFiltro;
    private javax.swing.JPanel panelLabelRotacion;
    private javax.swing.JPanel panelLabelSeno;
    private javax.swing.JPanel panelPosicion;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelRotacion;
    private javax.swing.JPanel panelSeno;
    private javax.swing.JPanel panelSliderBrillo;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JToolBar.Separator separador1;
    private javax.swing.JToolBar.Separator separador2;
    private javax.swing.JToolBar.Separator separador3;
    private javax.swing.JToolBar.Separator separatorFormas;
    private javax.swing.JSlider sliderBrillo;
    private javax.swing.JSlider sliderRotacion;
    private javax.swing.JSpinner spinnerGrosor;
    private javax.swing.JFormattedTextField tfAlto;
    private javax.swing.JFormattedTextField tfAncho;
    // End of variables declaration//GEN-END:variables

}
