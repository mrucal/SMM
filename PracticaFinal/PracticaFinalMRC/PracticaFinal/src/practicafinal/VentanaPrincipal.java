package practicafinal;

import SM.MRC.Sonido.Player;
import SM.MRC.Dialogo.DialogoRelleno;
import SM.MRC.Dialogo.DialogoSlider;
import listener.ColorListener;
import SM.MRC.Dialogo.DialogoContorno;
import SM.MRC.Dialogo.DialogoContraste;
import SM.MRC.Dialogo.DialogoEscalar;
import SM.MRC.Dialogo.DialogoOrdenacion;
import SM.MRC.Grafico.Forma.Forma;
import SM.MRC.Imagen.EspejoHorizontalOP;
import SM.MRC.Imagen.EspejoVerticalOP;
import SM.MRC.Imagen.SepiaOp;
import SM.MRC.Panel.PanelPaletaColores;
import SM.MRC.Panel.PanelTamaño;
import SM.MRC.Util.Cronometro;
import SM.MRC.Util.Ficheros;
import SM.MRC.Util.Filtro;
import SM.MRC.Util.TipoRecurso;
import SM.MRC.Util.Util;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ByteLookupTable;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import listener.DialogoRellenoListener;
import listener.DialogoSliderListener;
import listener.DialogoContornoListener;
import listener.DialogoContrasteListener;
import listener.DialogoEscalarListener;
import listener.DialogoOrdenacionListener;
import listener.PlayerListener;
import listener.RecorderListener;
import sm.image.KernelProducer;
import sm.sound.SMRecorder;
import sm.sound.SMSoundRecorder;

/**
 *
 * @author Mario
 */
public class VentanaPrincipal extends javax.swing.JFrame {


    private Player player = null;
    private SMRecorder recorder = null;
    private File temp = null;
    
    private Cronometro cronometro;
        
    public VentanaPrincipal() {
        
        initComponents();
        
        Dimension sizescreen = Toolkit.getDefaultToolkit().getScreenSize();
        double x = sizescreen.getWidth()/2 - this.getWidth()/2;
        double y = sizescreen.getHeight()/2 - this.getHeight()/2;
        this.setLocation(new Point((int)x,(int)y));
        
        ColorListener colorListener = new ColorListener(this);
        panelPaletaColores.getBotonFondo().addMouseListener(colorListener);
        panelPaletaColores.getBotonFrente().addMouseListener(colorListener);
        panelPaletaColores.getBotonContorno().addMouseListener(colorListener);
        panelPaletaColores.getBotonNegro().addMouseListener(colorListener);
        panelPaletaColores.getBotonRojo().addMouseListener(colorListener);
        panelPaletaColores.getBotonAzul().addMouseListener(colorListener);
        panelPaletaColores.getBotonBlanco().addMouseListener(colorListener);
        panelPaletaColores.getBotonAmarillo().addMouseListener(colorListener);
        panelPaletaColores.getBotonVerde().addMouseListener(colorListener);
        for(JTextField jtf: panelPaletaColores.getColoresNuevos())
            jtf.addMouseListener(colorListener);
        panelPaletaColores.getBotonPaleta().addMouseListener(colorListener);
        
        HerramientasListener herramientasListener = new HerramientasListener();
        botonLinea.addActionListener(herramientasListener);
        botonRectangulo.addActionListener(herramientasListener);
        botonRectanguloRedondeado.addActionListener(herramientasListener);
        botonElipse.addActionListener(herramientasListener);
        botonArco.addActionListener(herramientasListener);
        botonCurvaCuadrada.addActionListener(herramientasListener);
        botonTrazoLibre.addActionListener(herramientasListener);
        botonM.addActionListener(herramientasListener);
        botonSelloM.addActionListener(herramientasListener);
        botonEditar.addActionListener(herramientasListener);
        botonPegar.addActionListener(herramientasListener);
                
        dialogoOrdenacion = new DialogoOrdenacion(this);
        dialogoOrdenacion.addWindowListener(new DialogoOrdenacionListener(this));
        dialogoContorno = new DialogoContorno(this){
            @Override
            protected void miSpinnerGrosorStateChanged(javax.swing.event.ChangeEvent evt){
                DialogoContornoListener.cambiarGrosor(getSpinnerGrosor(), getVentanaInternaImagenActiva(false));
            }
            @Override
            protected void miCheckboxTipoStateChanged(javax.swing.event.ChangeEvent evt){
                DialogoContornoListener.cambiarTipo(getCheckboxTipo(), getVentanaInternaImagenActiva(false));
            }
            @Override
            protected void miRadioStateChanged(javax.swing.event.ChangeEvent evt){
                DialogoContornoListener.cambiarDiscontinuidad(getRadioContinuo(), getVentanaInternaImagenActiva(false));
            }
        };
        dialogoContorno.addWindowListener(new DialogoContornoListener(this));
        dialogoRelleno = new DialogoRelleno(this);
        dialogoRelleno.addWindowListener(new DialogoRellenoListener(this));
        dialogoSliderTransparencia = new DialogoSliderPracticaFinal(this, 0, 0, 100, 50, 25, "Transparencia");
        dialogoSliderTransparencia.addWindowListener(new DialogoSliderListener(this));
        dialogoEscalar = new DialogoEscalar(this);
        dialogoEscalar.addWindowListener(new DialogoEscalarListener(this));
        dialogoSliderBrillo = new DialogoSliderPracticaFinal(this, 0, -255, 255, 255, 128, "Brillo") ;
        dialogoSliderBrillo.addWindowListener(new DialogoSliderListener(this));
        dialogoContraste = new DialogoContraste(this);
        dialogoContraste.addWindowListener(new DialogoContrasteListener(this));
        dialogoSliderViñeta = new DialogoSliderPracticaFinal(this, 0, 0, 100, 50, 25, "Viñeta");
        dialogoSliderViñeta.addWindowListener(new DialogoSliderListener(this));
        dialogoSliderRotacion = new DialogoSliderPracticaFinal(this, 0, 0, 360, 90, 45, "Rotación");
        dialogoSliderRotacion.addWindowListener(new DialogoSliderListener(this));
        dialogoSliderTintado = new DialogoSliderPracticaFinal(this, 0, 0, 100, 50, 25, "Tintado");
        dialogoSliderTintado.addWindowListener(new DialogoSliderListener(this));

        Ficheros.filtrarExtensionesFileChooser(fileChooserAbrir);
        
        player = new Player();
        listaReproduccion.addItemListener(new ItemChangeListener(this));
            
        cronometro = new Cronometro(labelTiempo,progresoReproductor);
        
        labelDuracionRecord.setVisible(false);
    }
    
    public VentanaInternaCamara getVentanaInternaCamaraActiva(boolean showMensaje){
        if((VentanaInterna) escritorio.getSelectedFrame() != null){
            if (((VentanaInterna) escritorio.getSelectedFrame()).getTipo() == TipoRecurso.CAMARA) 
                return (VentanaInternaCamara) escritorio.getSelectedFrame();
            else if (showMensaje)
                JOptionPane.showMessageDialog(escritorio, "La ventana seleccionada no contiene ninguna webcam.", "Error", JOptionPane.ERROR_MESSAGE);
        }else if (showMensaje)
            JOptionPane.showMessageDialog(escritorio, "No hay ninguna ventana seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
        return null;
        
    }    
    
    public VentanaInternaVideo getVentanaInternaVideoActiva(boolean showMensaje){
        if((VentanaInterna) escritorio.getSelectedFrame() != null){
            if (((VentanaInterna) escritorio.getSelectedFrame()).getTipo() == TipoRecurso.VIDEO) 
                return (VentanaInternaVideo) escritorio.getSelectedFrame();
            else if (showMensaje)
                JOptionPane.showMessageDialog(escritorio, "La ventana seleccionada no contiene ningun vídeo.", "Error", JOptionPane.ERROR_MESSAGE);
        }else if (showMensaje)
            JOptionPane.showMessageDialog(escritorio, "No hay ninguna ventana seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
        return null;
    }    
    
    public VentanaInternaImagen getVentanaInternaImagenActiva(boolean showMensaje){
        if((VentanaInterna) escritorio.getSelectedFrame() != null){
            if (((VentanaInterna) escritorio.getSelectedFrame()).getTipo() == TipoRecurso.IMAGEN) 
                return (VentanaInternaImagen) escritorio.getSelectedFrame();
            else if (showMensaje)
                JOptionPane.showMessageDialog(escritorio, "La ventana seleccionada no contiene ninguna imagen.", "Error", JOptionPane.ERROR_MESSAGE);
        }else if (showMensaje)
            JOptionPane.showMessageDialog(escritorio, "No hay ninguna ventana seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
        return null;
    }    
    
    public int getNumVentanaInterna(){
        return escritorio.getAllFrames().length;
    }
    
    class HerramientasListener implements ActionListener {

        HerramientasListener() {}

        @Override
        public void actionPerformed(ActionEvent ae) {
            JToggleButton herramientaBoton = (JToggleButton) ae.getSource();
            VentanaInternaImagen vi = (VentanaInternaImagen) getVentanaInternaImagenActiva(false);
            if (vi != null) 
                if (vi.getTipo() == TipoRecurso.IMAGEN) {
                    vi.getPanel().setHerramienta(herramientaBoton.getName());
                    vi.getPanel().setEditar(herramientaBoton.getName() == "Editar");
                    if (!herramientaBoton.getName().equals("Editar") && vi.getPanel().getSeleccionado() != null) {
                        vi.getPanel().eliminarSeleccion();
                        vi.getPanel().repaint();
                    }
                    vi.getPanel().setPegar(herramientaBoton.getName() == "Pegar");
                    if (!herramientaBoton.getName().equals("Pegar")) 
                        vi.getPanel().borrarPortapapeles();
                    barraEstado.setText(herramientaBoton.getToolTipText());
                }
        }
    }

    class ItemChangeListener implements ItemListener {

        VentanaPrincipal vp;
        ItemChangeListener (VentanaPrincipal vp){this.vp = vp;}
        
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                if(player!=null){
                    player.stop();
                    stopCronometro(labelTiempo);
                }
                player.setPlayer((File)listaReproduccion.getSelectedItem());
                player.getPlayer().addLineListener(new PlayerListener(vp));
                labelTiempo.setText("00:00:00");
                labelDuracion.setText("00:00:00");
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBarraHerramientas = new javax.swing.ButtonGroup();
        fileChooserAbrir = new javax.swing.JFileChooser();
        fileChooserGuardar = new javax.swing.JFileChooser();
        jDialog1 = new javax.swing.JDialog();
        panelSuperior = new javax.swing.JPanel();
        barraArchivo = new javax.swing.JToolBar();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        botonNuevo = new javax.swing.JButton();
        botonAbrir = new javax.swing.JButton();
        botonGuardar = new javax.swing.JButton();
        barraFormas = new javax.swing.JToolBar();
        separatorFormas = new javax.swing.JToolBar.Separator();
        botonLinea = new javax.swing.JToggleButton();
        botonRectangulo = new javax.swing.JToggleButton();
        botonRectanguloRedondeado = new javax.swing.JToggleButton();
        botonElipse = new javax.swing.JToggleButton();
        botonArco = new javax.swing.JToggleButton();
        botonCurvaCuadrada = new javax.swing.JToggleButton();
        botonTrazoLibre = new javax.swing.JToggleButton();
        botonM = new javax.swing.JToggleButton();
        botonSelloM = new javax.swing.JToggleButton();
        barraEditar = new javax.swing.JToolBar();
        separador2 = new javax.swing.JToolBar.Separator();
        botonEditar = new javax.swing.JToggleButton();
        botonEliminar = new javax.swing.JButton();
        botonOrdenacion = new javax.swing.JButton();
        botonCopiar = new javax.swing.JButton();
        botonPegar = new javax.swing.JToggleButton();
        barraAtributos = new javax.swing.JToolBar();
        separador1 = new javax.swing.JToolBar.Separator();
        spinnerGrosor = new javax.swing.JSpinner();
        botonContorno = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        botonRelleno = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        botonTransparencia = new javax.swing.JButton();
        botonAlisar = new javax.swing.JToggleButton();
        botonTamaño = new javax.swing.JButton();
        panelPrincipal = new javax.swing.JPanel();
        escritorio = new javax.swing.JDesktopPane();
        panelInferior = new javax.swing.JPanel();
        panelSonidoVideoImagen = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        barraSonido = new javax.swing.JToolBar();
        separatorFormas1 = new javax.swing.JToolBar.Separator();
        botonPlay = new javax.swing.JButton();
        botonStop = new javax.swing.JButton();
        labelTiempo = new javax.swing.JLabel();
        progresoReproductor = new javax.swing.JProgressBar();
        labelDuracion = new javax.swing.JLabel();
        listaReproduccion = new javax.swing.JComboBox<>();
        botonRecord = new javax.swing.JButton();
        labelDuracionRecord = new javax.swing.JLabel();
        barraWebCam = new javax.swing.JToolBar();
        jSeparator17 = new javax.swing.JToolBar.Separator();
        botonWebCam = new javax.swing.JButton();
        botonCapturaWebCam = new javax.swing.JButton();
        barraImagen = new javax.swing.JToolBar();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        botonBrillo = new javax.swing.JButton();
        botonContraste = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        comboFiltro = new javax.swing.JComboBox<>();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        botonSeno = new javax.swing.JButton();
        botonSepia = new javax.swing.JButton();
        botonNegativo = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        botonBandas = new javax.swing.JButton();
        comboModoColor = new javax.swing.JComboBox<>();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        botonContrasteLookup = new javax.swing.JButton();
        botonViñeta = new javax.swing.JButton();
        botonEspejoHorizontal = new javax.swing.JButton();
        botonEspejoVertical = new javax.swing.JButton();
        botonTintado = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JToolBar.Separator();
        botonEscalaAumentar = new javax.swing.JButton();
        botonEscalaReducir = new javax.swing.JButton();
        botonRotacion = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        barraEstado = new javax.swing.JLabel();
        panelPosicion = new javax.swing.JPanel();
        separador3 = new javax.swing.JToolBar.Separator();
        labelRGB = new javax.swing.JLabel();
        labelPosicion = new javax.swing.JLabel();
        barraPaletaColores = new javax.swing.JToolBar();
        panelPaletaColores = new SM.MRC.Panel.PanelPaletaColores();
        menuPrincipal = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        itemNuevo = new javax.swing.JMenuItem();
        itemAbrir = new javax.swing.JMenuItem();
        itemGuardar = new javax.swing.JMenuItem();
        menuEditar = new javax.swing.JMenu();
        itemBorde = new javax.swing.JCheckBoxMenuItem();
        itemPaletaColores = new javax.swing.JCheckBoxMenuItem();
        itemFormas = new javax.swing.JCheckBoxMenuItem();
        itemImagen = new javax.swing.JCheckBoxMenuItem();
        itemSonidoVideo = new javax.swing.JCheckBoxMenuItem();
        menuImagen = new javax.swing.JMenu();
        itemTamaño = new javax.swing.JMenuItem();
        itemDuplicar = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenu1 = new javax.swing.JMenu();
        itemAcercaDe = new javax.swing.JMenuItem();

        FormListener formListener = new FormListener();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Práctica Final MRC");
        setPreferredSize(new java.awt.Dimension(1000, 730));

        panelSuperior.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelSuperior.setPreferredSize(new java.awt.Dimension(1000, 45));
        panelSuperior.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        barraArchivo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        barraArchivo.setFloatable(false);
        barraArchivo.setRollover(true);
        barraArchivo.setPreferredSize(new java.awt.Dimension(115, 40));
        barraArchivo.add(jSeparator5);

        botonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraArchivo/nuevo.png"))); // NOI18N
        botonNuevo.setToolTipText("Nuevo");
        botonNuevo.setFocusable(false);
        botonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonNuevo.setPreferredSize(new java.awt.Dimension(35, 35));
        botonNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonNuevo.addActionListener(formListener);
        barraArchivo.add(botonNuevo);

        botonAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraArchivo/abrir.png"))); // NOI18N
        botonAbrir.setToolTipText("Abrir");
        botonAbrir.setFocusable(false);
        botonAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAbrir.setPreferredSize(new java.awt.Dimension(35, 35));
        botonAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonAbrir.addActionListener(formListener);
        barraArchivo.add(botonAbrir);

        botonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraArchivo/guardar.png"))); // NOI18N
        botonGuardar.setToolTipText("Guardar");
        botonGuardar.setFocusable(false);
        botonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonGuardar.setPreferredSize(new java.awt.Dimension(35, 35));
        botonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonGuardar.addActionListener(formListener);
        barraArchivo.add(botonGuardar);

        panelSuperior.add(barraArchivo);

        barraFormas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        barraFormas.setFloatable(false);
        barraFormas.setRollover(true);
        barraFormas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        barraFormas.setMinimumSize(new java.awt.Dimension(118, 31));
        barraFormas.setPreferredSize(new java.awt.Dimension(335, 40));
        barraFormas.add(separatorFormas);

        grupoBarraHerramientas.add(botonLinea);
        botonLinea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraFormas/mi_linea.png"))); // NOI18N
        botonLinea.setToolTipText("Línea");
        botonLinea.setFocusable(false);
        botonLinea.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLinea.setName("Línea"); // NOI18N
        botonLinea.setPreferredSize(new java.awt.Dimension(35, 35));
        botonLinea.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFormas.add(botonLinea);

        grupoBarraHerramientas.add(botonRectangulo);
        botonRectangulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraFormas/mi_rectangulo.png"))); // NOI18N
        botonRectangulo.setToolTipText("Rectángulo");
        botonRectangulo.setFocusable(false);
        botonRectangulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRectangulo.setName("Rectángulo"); // NOI18N
        botonRectangulo.setPreferredSize(new java.awt.Dimension(35, 35));
        botonRectangulo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFormas.add(botonRectangulo);

        grupoBarraHerramientas.add(botonRectanguloRedondeado);
        botonRectanguloRedondeado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraFormas/mi_rectangulo_redondeado.png"))); // NOI18N
        botonRectanguloRedondeado.setToolTipText("Rectángulo Redondeado");
        botonRectanguloRedondeado.setFocusable(false);
        botonRectanguloRedondeado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRectanguloRedondeado.setName("RectánguloRedondeado"); // NOI18N
        botonRectanguloRedondeado.setPreferredSize(new java.awt.Dimension(35, 35));
        botonRectanguloRedondeado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFormas.add(botonRectanguloRedondeado);

        grupoBarraHerramientas.add(botonElipse);
        botonElipse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraFormas/mi_elipse.png"))); // NOI18N
        botonElipse.setToolTipText("Elipse");
        botonElipse.setFocusable(false);
        botonElipse.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonElipse.setName("Elipse"); // NOI18N
        botonElipse.setPreferredSize(new java.awt.Dimension(35, 35));
        botonElipse.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFormas.add(botonElipse);

        grupoBarraHerramientas.add(botonArco);
        botonArco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraFormas/mi_arco.png"))); // NOI18N
        botonArco.setToolTipText("Arco");
        botonArco.setFocusable(false);
        botonArco.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonArco.setName("Arco"); // NOI18N
        botonArco.setPreferredSize(new java.awt.Dimension(35, 35));
        botonArco.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFormas.add(botonArco);

        grupoBarraHerramientas.add(botonCurvaCuadrada);
        botonCurvaCuadrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraFormas/mi_curva_cuadrado.png"))); // NOI18N
        botonCurvaCuadrada.setToolTipText("Curva Cuadrada");
        botonCurvaCuadrada.setFocusable(false);
        botonCurvaCuadrada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonCurvaCuadrada.setName("CurvaCuadrada"); // NOI18N
        botonCurvaCuadrada.setPreferredSize(new java.awt.Dimension(35, 35));
        botonCurvaCuadrada.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFormas.add(botonCurvaCuadrada);

        grupoBarraHerramientas.add(botonTrazoLibre);
        botonTrazoLibre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraFormas/mi_trazo_libre.png"))); // NOI18N
        botonTrazoLibre.setToolTipText("Trazo Libre");
        botonTrazoLibre.setFocusable(false);
        botonTrazoLibre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonTrazoLibre.setName("TrazoLibre"); // NOI18N
        botonTrazoLibre.setPreferredSize(new java.awt.Dimension(35, 35));
        botonTrazoLibre.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFormas.add(botonTrazoLibre);

        grupoBarraHerramientas.add(botonM);
        botonM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraFormas/mi_m.png"))); // NOI18N
        botonM.setToolTipText("Letra M");
        botonM.setFocusable(false);
        botonM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonM.setName("M"); // NOI18N
        botonM.setPreferredSize(new java.awt.Dimension(35, 35));
        botonM.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFormas.add(botonM);

        grupoBarraHerramientas.add(botonSelloM);
        botonSelloM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraFormas/mi_sello_m.png"))); // NOI18N
        botonSelloM.setToolTipText("Sello M");
        botonSelloM.setFocusable(false);
        botonSelloM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonSelloM.setName("SelloM"); // NOI18N
        botonSelloM.setPreferredSize(new java.awt.Dimension(35, 35));
        botonSelloM.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFormas.add(botonSelloM);

        panelSuperior.add(barraFormas);

        barraEditar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        barraEditar.setFloatable(false);
        barraEditar.setRollover(true);
        barraEditar.setPreferredSize(new java.awt.Dimension(190, 40));
        barraEditar.add(separador2);

        grupoBarraHerramientas.add(botonEditar);
        botonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraEditar/seleccionar.png"))); // NOI18N
        botonEditar.setToolTipText("Editar forma");
        botonEditar.setFocusable(false);
        botonEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonEditar.setName("Editar"); // NOI18N
        botonEditar.setPreferredSize(new java.awt.Dimension(35, 35));
        botonEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraEditar.add(botonEditar);

        botonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraEditar/borrar.png"))); // NOI18N
        botonEliminar.setToolTipText("Eliminar forma");
        botonEliminar.setFocusable(false);
        botonEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonEliminar.setPreferredSize(new java.awt.Dimension(35, 35));
        botonEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonEliminar.addActionListener(formListener);
        barraEditar.add(botonEliminar);

        botonOrdenacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraEditar/ordenar.png"))); // NOI18N
        botonOrdenacion.setToolTipText("Ordenar forma");
        botonOrdenacion.setFocusable(false);
        botonOrdenacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonOrdenacion.setPreferredSize(new java.awt.Dimension(35, 35));
        botonOrdenacion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonOrdenacion.addActionListener(formListener);
        barraEditar.add(botonOrdenacion);

        botonCopiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraEditar/copiar.png"))); // NOI18N
        botonCopiar.setToolTipText("Copiar forma");
        botonCopiar.setFocusable(false);
        botonCopiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonCopiar.setPreferredSize(new java.awt.Dimension(35, 35));
        botonCopiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonCopiar.addActionListener(formListener);
        barraEditar.add(botonCopiar);

        grupoBarraHerramientas.add(botonPegar);
        botonPegar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraEditar/pegar.png"))); // NOI18N
        botonPegar.setToolTipText("Pegar forma");
        botonPegar.setFocusable(false);
        botonPegar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonPegar.setName("Pegar"); // NOI18N
        botonPegar.setPreferredSize(new java.awt.Dimension(35, 35));
        botonPegar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraEditar.add(botonPegar);

        panelSuperior.add(barraEditar);

        barraAtributos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        barraAtributos.setFloatable(false);
        barraAtributos.setRollover(true);
        barraAtributos.setPreferredSize(new java.awt.Dimension(300, 40));
        barraAtributos.add(separador1);

        spinnerGrosor.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinnerGrosor.setToolTipText("Grosor");
        spinnerGrosor.setMinimumSize(new java.awt.Dimension(40, 20));
        spinnerGrosor.setPreferredSize(new java.awt.Dimension(60, 30));
        spinnerGrosor.addChangeListener(formListener);
        barraAtributos.add(spinnerGrosor);

        botonContorno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atributos/trazo.png"))); // NOI18N
        botonContorno.setToolTipText("Contorno");
        botonContorno.setFocusable(false);
        botonContorno.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonContorno.setPreferredSize(new java.awt.Dimension(35, 35));
        botonContorno.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonContorno.addActionListener(formListener);
        barraAtributos.add(botonContorno);
        barraAtributos.add(jSeparator1);

        botonRelleno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atributos/sin_relleno.png"))); // NOI18N
        botonRelleno.setToolTipText("Relleno");
        botonRelleno.setFocusable(false);
        botonRelleno.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRelleno.setPreferredSize(new java.awt.Dimension(35, 35));
        botonRelleno.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonRelleno.addActionListener(formListener);
        barraAtributos.add(botonRelleno);
        barraAtributos.add(jSeparator3);

        botonTransparencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atributos/mi_transparencia.png"))); // NOI18N
        botonTransparencia.setToolTipText("Transparencia");
        botonTransparencia.setFocusable(false);
        botonTransparencia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonTransparencia.setPreferredSize(new java.awt.Dimension(35, 35));
        botonTransparencia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonTransparencia.addActionListener(formListener);
        barraAtributos.add(botonTransparencia);

        botonAlisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atributos/alisar.png"))); // NOI18N
        botonAlisar.setToolTipText("Alisar");
        botonAlisar.setFocusable(false);
        botonAlisar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAlisar.setPreferredSize(new java.awt.Dimension(35, 35));
        botonAlisar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonAlisar.addActionListener(formListener);
        barraAtributos.add(botonAlisar);

        botonTamaño.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atributos/resize24.png"))); // NOI18N
        botonTamaño.setToolTipText("Cambiar tamaño forma");
        botonTamaño.setFocusable(false);
        botonTamaño.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonTamaño.setPreferredSize(new java.awt.Dimension(35, 35));
        botonTamaño.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonTamaño.addActionListener(formListener);
        barraAtributos.add(botonTamaño);

        panelSuperior.add(barraAtributos);

        getContentPane().add(panelSuperior, java.awt.BorderLayout.NORTH);

        panelPrincipal.setLayout(new java.awt.BorderLayout());

        escritorio.setName("Línea"); // NOI18N

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 940, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 531, Short.MAX_VALUE)
        );

        panelPrincipal.add(escritorio, java.awt.BorderLayout.CENTER);

        panelInferior.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        panelInferior.setMinimumSize(new java.awt.Dimension(519, 70));
        panelInferior.setPreferredSize(new java.awt.Dimension(1000, 120));
        panelInferior.setLayout(new java.awt.BorderLayout());

        panelSonidoVideoImagen.setPreferredSize(new java.awt.Dimension(1000, 80));
        panelSonidoVideoImagen.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(994, 40));
        jPanel2.setLayout(new java.awt.BorderLayout());

        barraSonido.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        barraSonido.setFloatable(false);
        barraSonido.setRollover(true);
        barraSonido.setMinimumSize(new java.awt.Dimension(118, 31));
        barraSonido.setPreferredSize(new java.awt.Dimension(610, 35));
        barraSonido.add(separatorFormas1);

        botonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraSonido/play24x24.png"))); // NOI18N
        botonPlay.setToolTipText("Play");
        botonPlay.setFocusable(false);
        botonPlay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonPlay.setPreferredSize(new java.awt.Dimension(38, 38));
        botonPlay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonPlay.addActionListener(formListener);
        barraSonido.add(botonPlay);

        botonStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraSonido/stop24x24.png"))); // NOI18N
        botonStop.setToolTipText("Pause");
        botonStop.setFocusable(false);
        botonStop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonStop.setPreferredSize(new java.awt.Dimension(38, 38));
        botonStop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonStop.addActionListener(formListener);
        barraSonido.add(botonStop);

        labelTiempo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelTiempo.setText("00:00:00");
        labelTiempo.setToolTipText("Tiempo de reproducción");
        labelTiempo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5));
        barraSonido.add(labelTiempo);

        progresoReproductor.setToolTipText("Progreso de reproducción");
        progresoReproductor.setPreferredSize(new java.awt.Dimension(150, 38));
        barraSonido.add(progresoReproductor);

        labelDuracion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDuracion.setText("00:00:00");
        labelDuracion.setToolTipText("Duración");
        labelDuracion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5));
        barraSonido.add(labelDuracion);

        listaReproduccion.setToolTipText("Lista de reproducción");
        listaReproduccion.setPreferredSize(new java.awt.Dimension(200, 35));
        barraSonido.add(listaReproduccion);

        botonRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraSonido/record24x24.png"))); // NOI18N
        botonRecord.setToolTipText("Record");
        botonRecord.setFocusable(false);
        botonRecord.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRecord.setPreferredSize(new java.awt.Dimension(38, 38));
        botonRecord.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonRecord.addActionListener(formListener);
        barraSonido.add(botonRecord);

        labelDuracionRecord.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDuracionRecord.setText("00:00:00");
        labelDuracionRecord.setToolTipText("Tiempo de grabación");
        labelDuracionRecord.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5));
        barraSonido.add(labelDuracionRecord);

        jPanel2.add(barraSonido, java.awt.BorderLayout.WEST);

        barraWebCam.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        barraWebCam.setFloatable(false);
        barraWebCam.setRollover(true);
        barraWebCam.setPreferredSize(new java.awt.Dimension(1000, 40));
        barraWebCam.add(jSeparator17);

        botonWebCam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraWebCam/Camara.png"))); // NOI18N
        botonWebCam.setToolTipText("WebCam");
        botonWebCam.setFocusable(false);
        botonWebCam.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonWebCam.setPreferredSize(new java.awt.Dimension(38, 38));
        botonWebCam.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonWebCam.addActionListener(formListener);
        barraWebCam.add(botonWebCam);

        botonCapturaWebCam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraWebCam/Capturar.png"))); // NOI18N
        botonCapturaWebCam.setToolTipText("Screenshoot");
        botonCapturaWebCam.setFocusable(false);
        botonCapturaWebCam.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonCapturaWebCam.setPreferredSize(new java.awt.Dimension(38, 38));
        botonCapturaWebCam.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonCapturaWebCam.addActionListener(formListener);
        barraWebCam.add(botonCapturaWebCam);

        jPanel2.add(barraWebCam, java.awt.BorderLayout.CENTER);

        panelSonidoVideoImagen.add(jPanel2, java.awt.BorderLayout.CENTER);

        barraImagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        barraImagen.setFloatable(false);
        barraImagen.setRollover(true);
        barraImagen.setPreferredSize(new java.awt.Dimension(100, 40));
        barraImagen.add(jSeparator4);

        botonBrillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraImagenes/mi_brillo.png"))); // NOI18N
        botonBrillo.setToolTipText("Brillo");
        botonBrillo.setFocusable(false);
        botonBrillo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonBrillo.setPreferredSize(new java.awt.Dimension(38, 38));
        botonBrillo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonBrillo.addActionListener(formListener);
        barraImagen.add(botonBrillo);

        botonContraste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraImagenes/mi_contraste_config.png"))); // NOI18N
        botonContraste.setToolTipText("Contraste");
        botonContraste.setFocusable(false);
        botonContraste.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonContraste.setPreferredSize(new java.awt.Dimension(38, 38));
        botonContraste.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonContraste.addActionListener(formListener);
        barraImagen.add(botonContraste);
        barraImagen.add(jSeparator6);

        comboFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Media", "Binomial", "Enfoque", "Relieve", "Lapaciano", "SobelX", "SobelY" }));
        comboFiltro.setToolTipText("Filtros");
        comboFiltro.setPreferredSize(new java.awt.Dimension(80, 35));
        comboFiltro.addActionListener(formListener);
        barraImagen.add(comboFiltro);
        barraImagen.add(jSeparator7);

        botonSeno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraImagenes/sinusoidal.png"))); // NOI18N
        botonSeno.setToolTipText("Seno");
        botonSeno.setFocusable(false);
        botonSeno.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonSeno.setPreferredSize(new java.awt.Dimension(38, 38));
        botonSeno.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonSeno.addActionListener(formListener);
        barraImagen.add(botonSeno);

        botonSepia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraImagenes/sepia.png"))); // NOI18N
        botonSepia.setToolTipText("Sepia");
        botonSepia.setFocusable(false);
        botonSepia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonSepia.setPreferredSize(new java.awt.Dimension(38, 38));
        botonSepia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonSepia.addActionListener(formListener);
        barraImagen.add(botonSepia);

        botonNegativo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraImagenes/negativo.png"))); // NOI18N
        botonNegativo.setToolTipText("Negativo");
        botonNegativo.setFocusable(false);
        botonNegativo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonNegativo.setPreferredSize(new java.awt.Dimension(38, 38));
        botonNegativo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonNegativo.addActionListener(formListener);
        barraImagen.add(botonNegativo);
        barraImagen.add(jSeparator8);

        botonBandas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraImagenes/bandas.png"))); // NOI18N
        botonBandas.setToolTipText("Obtener bandas");
        botonBandas.setFocusable(false);
        botonBandas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonBandas.setPreferredSize(new java.awt.Dimension(38, 38));
        botonBandas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonBandas.addActionListener(formListener);
        barraImagen.add(botonBandas);

        comboModoColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "RBG", "YCC", "GREY" }));
        comboModoColor.setToolTipText("Cambiar espacio color");
        comboModoColor.setPreferredSize(new java.awt.Dimension(70, 35));
        comboModoColor.addActionListener(formListener);
        barraImagen.add(comboModoColor);
        barraImagen.add(jSeparator9);

        botonContrasteLookup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraImagenes/mi_contraste_mejorado.png"))); // NOI18N
        botonContrasteLookup.setToolTipText("Contraste lookup");
        botonContrasteLookup.setFocusable(false);
        botonContrasteLookup.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonContrasteLookup.setPreferredSize(new java.awt.Dimension(38, 38));
        botonContrasteLookup.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonContrasteLookup.addActionListener(formListener);
        barraImagen.add(botonContrasteLookup);

        botonViñeta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraImagenes/viñeta.png"))); // NOI18N
        botonViñeta.setToolTipText("Viñeta");
        botonViñeta.setFocusable(false);
        botonViñeta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonViñeta.setPreferredSize(new java.awt.Dimension(38, 38));
        botonViñeta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonViñeta.addActionListener(formListener);
        barraImagen.add(botonViñeta);

        botonEspejoHorizontal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraImagenes/espejohorizontal.png"))); // NOI18N
        botonEspejoHorizontal.setToolTipText("Espejo horizontal");
        botonEspejoHorizontal.setFocusable(false);
        botonEspejoHorizontal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonEspejoHorizontal.setPreferredSize(new java.awt.Dimension(38, 38));
        botonEspejoHorizontal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonEspejoHorizontal.addActionListener(formListener);
        barraImagen.add(botonEspejoHorizontal);

        botonEspejoVertical.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraImagenes/espejovertical.png"))); // NOI18N
        botonEspejoVertical.setToolTipText("Espejo vertical");
        botonEspejoVertical.setFocusable(false);
        botonEspejoVertical.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonEspejoVertical.setPreferredSize(new java.awt.Dimension(38, 38));
        botonEspejoVertical.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonEspejoVertical.addActionListener(formListener);
        barraImagen.add(botonEspejoVertical);

        botonTintado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraImagenes/tintado.png"))); // NOI18N
        botonTintado.setToolTipText("Tintado");
        botonTintado.setFocusable(false);
        botonTintado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonTintado.setPreferredSize(new java.awt.Dimension(38, 38));
        botonTintado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonTintado.addActionListener(formListener);
        barraImagen.add(botonTintado);
        barraImagen.add(jSeparator10);

        botonEscalaAumentar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraImagenes/aumentar.png"))); // NOI18N
        botonEscalaAumentar.setToolTipText("Aumentar escala");
        botonEscalaAumentar.setFocusable(false);
        botonEscalaAumentar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonEscalaAumentar.setPreferredSize(new java.awt.Dimension(20, 35));
        botonEscalaAumentar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonEscalaAumentar.addActionListener(formListener);
        barraImagen.add(botonEscalaAumentar);

        botonEscalaReducir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraImagenes/disminuir.png"))); // NOI18N
        botonEscalaReducir.setToolTipText("Disminuir escala");
        botonEscalaReducir.setFocusable(false);
        botonEscalaReducir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonEscalaReducir.setPreferredSize(new java.awt.Dimension(20, 35));
        botonEscalaReducir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonEscalaReducir.addActionListener(formListener);
        barraImagen.add(botonEscalaReducir);

        botonRotacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/barraImagenes/rotacion.png"))); // NOI18N
        botonRotacion.setToolTipText("Rotación");
        botonRotacion.setFocusable(false);
        botonRotacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRotacion.setPreferredSize(new java.awt.Dimension(38, 38));
        botonRotacion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonRotacion.addActionListener(formListener);
        barraImagen.add(botonRotacion);

        panelSonidoVideoImagen.add(barraImagen, java.awt.BorderLayout.SOUTH);

        panelInferior.add(panelSonidoVideoImagen, java.awt.BorderLayout.NORTH);

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel3.setPreferredSize(new java.awt.Dimension(954, 30));
        jPanel3.setLayout(new java.awt.BorderLayout());

        barraEstado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        barraEstado.setText("Línea");
        barraEstado.setToolTipText("");
        barraEstado.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        barraEstado.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        barraEstado.setPreferredSize(new java.awt.Dimension(1260, 30));
        jPanel3.add(barraEstado, java.awt.BorderLayout.WEST);

        panelPosicion.setPreferredSize(new java.awt.Dimension(10, 30));
        panelPosicion.setLayout(new javax.swing.BoxLayout(panelPosicion, javax.swing.BoxLayout.LINE_AXIS));

        separador3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        panelPosicion.add(separador3);

        labelRGB.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        labelRGB.setPreferredSize(new java.awt.Dimension(10, 30));
        panelPosicion.add(labelRGB);

        labelPosicion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        labelPosicion.setPreferredSize(new java.awt.Dimension(10, 30));
        panelPosicion.add(labelPosicion);

        jPanel3.add(panelPosicion, java.awt.BorderLayout.EAST);

        panelInferior.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        panelPrincipal.add(panelInferior, java.awt.BorderLayout.SOUTH);

        getContentPane().add(panelPrincipal, java.awt.BorderLayout.CENTER);

        barraPaletaColores.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        barraPaletaColores.setOrientation(javax.swing.SwingConstants.VERTICAL);
        barraPaletaColores.setRollover(true);
        barraPaletaColores.setToolTipText("Paleta de Colores");
        barraPaletaColores.setMaximumSize(new java.awt.Dimension(5, 259));
        barraPaletaColores.setMinimumSize(new java.awt.Dimension(5, 259));
        barraPaletaColores.setPreferredSize(new java.awt.Dimension(60, 620));

        panelPaletaColores.setToolTipText("Mostrar paleta avanzada de colores");
        panelPaletaColores.setMinimumSize(new java.awt.Dimension(50, 540));
        panelPaletaColores.setPreferredSize(new java.awt.Dimension(40, 660));
        barraPaletaColores.add(panelPaletaColores);

        getContentPane().add(barraPaletaColores, java.awt.BorderLayout.EAST);

        menuPrincipal.setPreferredSize(new java.awt.Dimension(145, 30));

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

        menuEditar.setText("Ver");

        itemBorde.setSelected(true);
        itemBorde.setText("Ver borde");
        itemBorde.addActionListener(formListener);
        menuEditar.add(itemBorde);

        itemPaletaColores.setSelected(true);
        itemPaletaColores.setText("Ver paleta de colores");
        itemPaletaColores.addActionListener(formListener);
        menuEditar.add(itemPaletaColores);

        itemFormas.setSelected(true);
        itemFormas.setText("Ver barra de formas");
        itemFormas.addActionListener(formListener);
        menuEditar.add(itemFormas);

        itemImagen.setSelected(true);
        itemImagen.setText("Ver barra de imagen");
        itemImagen.addActionListener(formListener);
        menuEditar.add(itemImagen);

        itemSonidoVideo.setSelected(true);
        itemSonidoVideo.setText("Ver barra de sonido/video");
        itemSonidoVideo.addActionListener(formListener);
        menuEditar.add(itemSonidoVideo);

        menuPrincipal.add(menuEditar);

        menuImagen.setText("Imagen");

        itemTamaño.setText("Tamaño nueva imagen");
        itemTamaño.addActionListener(formListener);
        menuImagen.add(itemTamaño);

        itemDuplicar.setText("Duplicar");
        itemDuplicar.addActionListener(formListener);
        menuImagen.add(itemDuplicar);
        menuImagen.add(jSeparator2);

        menuPrincipal.add(menuImagen);

        jMenu1.setText("Ayuda");

        itemAcercaDe.setText("Acerca de");
        itemAcercaDe.addActionListener(formListener);
        jMenu1.add(itemAcercaDe);

        menuPrincipal.add(jMenu1);

        setJMenuBar(menuPrincipal);

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, javax.swing.event.ChangeListener {
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
            else if (evt.getSource() == botonEliminar) {
                VentanaPrincipal.this.botonEliminarActionPerformed(evt);
            }
            else if (evt.getSource() == botonOrdenacion) {
                VentanaPrincipal.this.botonOrdenacionActionPerformed(evt);
            }
            else if (evt.getSource() == botonCopiar) {
                VentanaPrincipal.this.botonCopiarActionPerformed(evt);
            }
            else if (evt.getSource() == botonContorno) {
                VentanaPrincipal.this.botonTrazo(evt);
            }
            else if (evt.getSource() == botonRelleno) {
                VentanaPrincipal.this.botonRellenoActionPerformed(evt);
            }
            else if (evt.getSource() == botonTransparencia) {
                VentanaPrincipal.this.botonTransparenciaActionPerformed(evt);
            }
            else if (evt.getSource() == botonAlisar) {
                VentanaPrincipal.this.botonAlisarActionPerformed(evt);
            }
            else if (evt.getSource() == botonTamaño) {
                VentanaPrincipal.this.botonTamañoActionPerformed(evt);
            }
            else if (evt.getSource() == botonPlay) {
                VentanaPrincipal.this.botonPlayActionPerformed(evt);
            }
            else if (evt.getSource() == botonStop) {
                VentanaPrincipal.this.botonStopActionPerformed(evt);
            }
            else if (evt.getSource() == botonRecord) {
                VentanaPrincipal.this.botonRecordActionPerformed(evt);
            }
            else if (evt.getSource() == botonWebCam) {
                VentanaPrincipal.this.botonWebCamActionPerformed(evt);
            }
            else if (evt.getSource() == botonCapturaWebCam) {
                VentanaPrincipal.this.botonCapturaWebCambotonTrazo(evt);
            }
            else if (evt.getSource() == botonBrillo) {
                VentanaPrincipal.this.botonBrilloActionPerformed(evt);
            }
            else if (evt.getSource() == botonContraste) {
                VentanaPrincipal.this.botonContrasteActionPerformed(evt);
            }
            else if (evt.getSource() == comboFiltro) {
                VentanaPrincipal.this.comboFiltroActionPerformed(evt);
            }
            else if (evt.getSource() == botonSeno) {
                VentanaPrincipal.this.botonSenoActionPerformed(evt);
            }
            else if (evt.getSource() == botonSepia) {
                VentanaPrincipal.this.botonSepiaActionPerformed(evt);
            }
            else if (evt.getSource() == botonNegativo) {
                VentanaPrincipal.this.botonNegativoActionPerformed(evt);
            }
            else if (evt.getSource() == botonBandas) {
                VentanaPrincipal.this.botonBandasActionPerformed(evt);
            }
            else if (evt.getSource() == comboModoColor) {
                VentanaPrincipal.this.comboModoColorActionPerformed(evt);
            }
            else if (evt.getSource() == botonContrasteLookup) {
                VentanaPrincipal.this.botonContrasteLookupActionPerformed(evt);
            }
            else if (evt.getSource() == botonViñeta) {
                VentanaPrincipal.this.botonViñetaActionPerformed(evt);
            }
            else if (evt.getSource() == botonEspejoHorizontal) {
                VentanaPrincipal.this.botonEspejoHorizontalActionPerformed(evt);
            }
            else if (evt.getSource() == botonEspejoVertical) {
                VentanaPrincipal.this.botonEspejoVerticalActionPerformed(evt);
            }
            else if (evt.getSource() == botonTintado) {
                VentanaPrincipal.this.botonTintadoActionPerformed(evt);
            }
            else if (evt.getSource() == botonEscalaAumentar) {
                VentanaPrincipal.this.botonEscalaAumentarActionPerformed(evt);
            }
            else if (evt.getSource() == botonEscalaReducir) {
                VentanaPrincipal.this.botonEscalaReducirActionPerformed(evt);
            }
            else if (evt.getSource() == botonRotacion) {
                VentanaPrincipal.this.botonRotacionbotonTrazo(evt);
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
            else if (evt.getSource() == itemPaletaColores) {
                VentanaPrincipal.this.itemPaletaColoresActionPerformed(evt);
            }
            else if (evt.getSource() == itemFormas) {
                VentanaPrincipal.this.itemFormasActionPerformed(evt);
            }
            else if (evt.getSource() == itemImagen) {
                VentanaPrincipal.this.itemImagenActionPerformed(evt);
            }
            else if (evt.getSource() == itemSonidoVideo) {
                VentanaPrincipal.this.itemSonidoVideoActionPerformed(evt);
            }
            else if (evt.getSource() == itemTamaño) {
                VentanaPrincipal.this.itemTamañoActionPerformed(evt);
            }
            else if (evt.getSource() == itemDuplicar) {
                VentanaPrincipal.this.itemDuplicarActionPerformed(evt);
            }
            else if (evt.getSource() == itemAcercaDe) {
                VentanaPrincipal.this.itemAcercaDeActionPerformed(evt);
            }
        }

        public void stateChanged(javax.swing.event.ChangeEvent evt) {
            if (evt.getSource() == spinnerGrosor) {
                VentanaPrincipal.this.spinnerGrosorStateChanged(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void itemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevoActionPerformed
        PanelTamaño myPanel = new PanelTamaño();
        int ancho, alto;
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Introduce ancho y alto de la imagen", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/iconos/atributos/image_resize_peque.png")));
        if (result == JOptionPane.OK_OPTION) {
            ancho = myPanel.getAncho();
            alto = myPanel.getAlto();
            VentanaInternaImagen vi = new VentanaInternaImagen(this);
            vi.setTitle("Nuevo");
            BufferedImage img = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
            vi.getPanel().setImagen(img);
            vi.getPanel().setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
            img.getGraphics().fillRect(0, 0, vi.getPanel().getWidth(), vi.getPanel().getHeight());
        }       
    }//GEN-LAST:event_itemNuevoActionPerformed

    private void botonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoActionPerformed
        itemNuevoActionPerformed(evt);
    }//GEN-LAST:event_botonNuevoActionPerformed

    private void itemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAbrirActionPerformed
        int resp = fileChooserAbrir.showOpenDialog(this);
        if (resp == JFileChooser.APPROVE_OPTION) {
            File f = fileChooserAbrir.getSelectedFile();
            switch (Ficheros.getTipoFichero(f)) {
                case IMAGEN:
                    try {
                        BufferedImage img = ImageIO.read(f);
                        VentanaInternaImagen vi = new VentanaInternaImagen(this);
                        vi.getPanel().setImagen(img);
                        vi.getPanel().setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
                        vi.setTitle(f.getName());
                        vi.repaint();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Error al abrir la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case SONIDO:
                    try {
                        File mif = new File(f.getAbsolutePath()) {
                            @Override
                            public String toString() {
                                return this.getName();
                            }
                        };
                        addListaReproduccion(mif);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Error al abrir sonido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case VIDEO:
                    VentanaInternaVideo.getInstance(this,f);
                    break;
            }
        }
    }//GEN-LAST:event_itemAbrirActionPerformed

    private void botonAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAbrirActionPerformed
        itemAbrirActionPerformed(evt);
    }//GEN-LAST:event_botonAbrirActionPerformed

    private void itemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGuardarActionPerformed
        if (getVentanaInternaImagenActiva(true) != null) {
            Ficheros.filtrarExtensionesFileChooserImagen(fileChooserGuardar);
            int resp = fileChooserGuardar.showSaveDialog(this);
            if (resp == JFileChooser.APPROVE_OPTION) {
                VentanaInternaImagen vi = getVentanaInternaImagenActiva(false);
                vi.getPanel().setBorde(false);
                Color color = vi.getPanel().getBackground();
                vi.getPanel().setBackground(new java.awt.Color(255,0, 0, 0));
                vi.getPanel().repaint();
                try {
                    BufferedImage img = vi.getPanel().getImagen(true);
                    if (img != null) {
                        File f = fileChooserGuardar.getSelectedFile();
                        String extension = Ficheros.getExtensionFichero(f);
                        if (vi.getPanel().getImagen().getRaster().getNumBands() == 4 && (!extension.equals("png") && !extension.equals("PNG"))) {
                            JOptionPane.showMessageDialog(this, "La imagen debe almacenarse con extensión png ya que tiene canal alfa.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else 
                            if (Ficheros.extensiones_imagen.contains(extension) || Ficheros.extensiones_mayuscula_imagen.contains(extension)) {
                                ImageIO.write(img, extension, f);
                                vi.setTitle(f.getName());
                            } else 
                                JOptionPane.showMessageDialog(this, "Formato no soportado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al guardar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
                }
                vi.getPanel().setBorde(true);
                vi.getPanel().setBackground(color);
                vi.getPanel().repaint();
            }
        }
    }//GEN-LAST:event_itemGuardarActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        itemGuardarActionPerformed(evt);
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void spinnerGrosorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerGrosorStateChanged
        VentanaInternaImagen vi = getVentanaInternaImagenActiva(false);
        if (vi != null) 
            if (vi.getTipo() == TipoRecurso.IMAGEN){
                if(vi.getPanel().getSeleccionado() == null)
                    vi.getPanel().getEstilo().getContorno().setGrosor((int)spinnerGrosor.getValue());
                else
                    vi.getPanel().getSeleccionado().setGrosor((int)spinnerGrosor.getValue());
                vi.getPanel().repaint();
            }
    }//GEN-LAST:event_spinnerGrosorStateChanged

    private void botonTrazo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTrazo
        Util.mostrarDialogoCentro(this, dialogoContorno);
        dialogoContorno.setVisible(true);
    }//GEN-LAST:event_botonTrazo

    private void botonRellenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRellenoActionPerformed
        Util.mostrarDialogoCentro(this, dialogoRelleno);
        dialogoRelleno.setVisible(true);
    }//GEN-LAST:event_botonRellenoActionPerformed

    private void botonTransparenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTransparenciaActionPerformed
        Util.mostrarDialogoCentro(this, dialogoSliderTransparencia);
        dialogoSliderTransparencia.setVisible(true);
    }//GEN-LAST:event_botonTransparenciaActionPerformed

    private void botonAlisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAlisarActionPerformed
        VentanaInternaImagen vi = getVentanaInternaImagenActiva(true);
        if (vi != null) 
            if (vi.getTipo() == TipoRecurso.IMAGEN){
                if(vi.getPanel().getSeleccionado() == null)
                    vi.getPanel().getEstilo().getAlisado().setActivado(botonAlisar.isSelected());
                else
                    vi.getPanel().getSeleccionado().setActivarAlisado(botonAlisar.isSelected());
                vi.getPanel().repaint();
            }
    }//GEN-LAST:event_botonAlisarActionPerformed

    private void botonOrdenacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOrdenacionActionPerformed
        Util.mostrarDialogoCentro(this, dialogoOrdenacion);
        dialogoOrdenacion.setVisible(true);
    }//GEN-LAST:event_botonOrdenacionActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        VentanaInternaImagen vi = getVentanaInternaImagenActiva(true);
        if (vi != null) 
            if (vi.getTipo() == TipoRecurso.IMAGEN && vi.getPanel().getSeleccionado() != null)
                vi.getPanel().eliminar(vi.getPanel().getSeleccionado());
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void itemBordeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBordeActionPerformed
        VentanaInternaImagen vi = getVentanaInternaImagenActiva(true);
        if (vi != null) {
            if (vi.getTipo() == TipoRecurso.IMAGEN){
                vi.getPanel().setBorde(itemBorde.isSelected());
                vi.repaint();
            }
        }else
            itemBorde.setSelected(!itemBorde.isSelected());
    }//GEN-LAST:event_itemBordeActionPerformed

    private void itemTamañoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTamañoActionPerformed
        VentanaInternaImagen vi = getVentanaInternaImagenActiva(true);
        if(vi != null){
            Util.mostrarDialogoCentro(this,dialogoEscalar);
            dialogoEscalar.getPanelTamaño().getTfAncho().setValue(vi.getPanel().getWidth());
            dialogoEscalar.getPanelTamaño().getTfAlto().setValue(vi.getPanel().getHeight());
            dialogoEscalar.setVisible(true);
        }else
            JOptionPane.showMessageDialog(this, "No hay ventana seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);        
    }//GEN-LAST:event_itemTamañoActionPerformed

    private void itemDuplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDuplicarActionPerformed
        VentanaInternaImagen vi_actual = getVentanaInternaImagenActiva(true);
        if( vi_actual != null){
            VentanaInternaImagen vi = new VentanaInternaImagen(this);
            BufferedImage imgSource = vi_actual.getPanel().getImagen();
            BufferedImage img;
            if (imgSource != null){
                img = Util.copiaImagen(imgSource);
                vi.setTitle(vi_actual.getTitle()+" (copia)");
                vi.getPanel().setImagen(img);
                vi.getPanel().setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
            }
        }
    }//GEN-LAST:event_itemDuplicarActionPerformed

    private void comboFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFiltroActionPerformed
        VentanaInternaImagen vi = getVentanaInternaImagenActiva(false);
        if (vi != null) {
            BufferedImage imgSource = vi.getPanel().getImagen();
            if (imgSource != null) {
                try {
                    ConvolveOp cop = null;
                    float m[];
                    Kernel k;
                    switch (Filtro.valueOf((String) comboFiltro.getSelectedItem()).ordinal()) {
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
                        case 5:
                            m = new float[]{-1.0f,-2.0f,-1.0f,0.0f,0.0f,0.0f,1.0f,2.0f,1.0f};
                            k = new Kernel(3, 3, m);
                            cop = new ConvolveOp(k);
                            break;
                        case 6:
                            m = new float[]{1.0f,0.0f,-1.0f,2.0f,0.0f,-2.0f,1.0f,0.0f,-1.0f};
                            k = new Kernel(3, 3, m);
                            cop = new ConvolveOp(k);
                            break;
                    }
                    BufferedImage imgdest = cop.filter(imgSource, null);
                    vi.getPanel().setImagen(imgdest);
                    vi.getPanel().repaint();
                } catch (Exception e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }

    }//GEN-LAST:event_comboFiltroActionPerformed

    private void botonSenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSenoActionPerformed
        VentanaInternaImagen vi = getVentanaInternaImagenActiva(true);
        if (vi != null) {
            BufferedImage imgSource = vi.getPanel().getImagen();
            if (imgSource != null) 
                try {
                    LookupTable lt = Util.seno(180.0/255.0);
                    LookupOp lop = new LookupOp(lt, null);
                    lop.filter(imgSource, imgSource);
                    vi.repaint();
                } catch (Exception e) {
                    System.err.println(e.getLocalizedMessage());
                }
        }
    }//GEN-LAST:event_botonSenoActionPerformed

    private void botonSepiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSepiaActionPerformed
        VentanaInternaImagen vi = getVentanaInternaImagenActiva(true);
        if (vi != null) {
            BufferedImage imgSource = vi.getPanel().getImagen();
            if (imgSource != null) 
                try {
                    SepiaOp sepiaop = new SepiaOp();
                    sepiaop.filter(imgSource, imgSource);
                    vi.repaint();
                } catch (Exception e) {
                    System.err.println(e.getLocalizedMessage());
                }
        }
    }//GEN-LAST:event_botonSepiaActionPerformed

    private void botonBandasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBandasActionPerformed
        VentanaInternaImagen vi = getVentanaInternaImagenActiva(true);
        if(vi!=null){
            BufferedImage img = vi.getPanel().getImagen();
            for(int i = 0; i < img.getRaster().getNumBands(); i++){
                VentanaInternaImagen vi_i = new VentanaInternaImagen(this,vi,i);
                vi_i.getPanel().setImagen(Util.getBanda(img,i));
                vi_i.setTitle(vi.getTitle()+" [banda "+i+"]");
                vi_i.getPanel().setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
                vi_i.repaint();
            }
        }
    }//GEN-LAST:event_botonBandasActionPerformed

    private void comboModoColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboModoColorActionPerformed
        VentanaInternaImagen vi = getVentanaInternaImagenActiva(false);
        if(vi != null){
            BufferedImage imgSource = vi.getPanel().getImagen();
            BufferedImage imgdest = null;
            String titulo = "";
            if(imgSource!=null){
                switch(comboModoColor.getSelectedIndex()){
                    case 0:
                        if (!imgSource.getColorModel().getColorSpace().isCS_sRGB()) {
                            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
                            ColorConvertOp cop = new ColorConvertOp(cs, null);
                            imgdest = cop.filter(imgSource, null);
                            titulo = "RGB";
                        }
                        break;
                    case 1:
                        if (imgSource.getColorModel().getColorSpace().getType() != ColorSpace.CS_PYCC) {
                            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_PYCC);
                            ColorConvertOp cop = new ColorConvertOp(cs, null);
                            imgdest = cop.filter(imgSource, null);
                            titulo = "YCC";
                        }
                        break;
                    case 2:
                        if (imgSource.getColorModel().getColorSpace().getType() != ColorSpace.CS_GRAY) {
                            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
                            ColorConvertOp cop = new ColorConvertOp(cs, null);
                            imgdest = cop.filter(imgSource, null);
                            titulo = "GREY";
                        }
                        break;
                }
                VentanaInternaImagen vi_nueva = new VentanaInternaImagen(this);
                vi_nueva.mostrarVentanaCascada(escritorio);
                vi_nueva.setVisible(true);

                if (imgdest != null) {
                    vi_nueva.setTitle(vi.getTitle() + " ("+titulo+")");
                    vi_nueva.getPanel().setImagen(imgdest);
                }
                vi_nueva.repaint();
            }
        }
    }//GEN-LAST:event_comboModoColorActionPerformed

    private void botonNegativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNegativoActionPerformed
        VentanaInternaImagen vi = getVentanaInternaImagenActiva(true);
        if (vi != null) {
            BufferedImage imgSource = vi.getPanel().getImagen();
            if (imgSource != null) {
                try {
                    int num_bands = imgSource.getRaster().getNumBands() == 4 ? 3 : imgSource.getRaster().getNumBands();
                    byte f[][] = new byte[num_bands][256];
                    for (int i = 0; i < num_bands; i++) 
                        for(int j = 0; j < 256; j++)
                            f[i][j] = (byte) (255 - j); 
                    LookupOp lop = new LookupOp(new ByteLookupTable(0,f), null);
                    BufferedImage imgdest = lop.filter(imgSource, null);
                    vi.getPanel().setImagen(imgdest);
                    vi.getPanel().repaint();
                } catch (Exception e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_botonNegativoActionPerformed

    private void botonViñetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonViñetaActionPerformed
        Util.mostrarDialogoCentro(this, dialogoSliderViñeta);
        dialogoSliderViñeta.setVisible(true);
    }//GEN-LAST:event_botonViñetaActionPerformed

    private void botonEscalaAumentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEscalaAumentarActionPerformed
        VentanaInternaImagen vi = getVentanaInternaImagenActiva(true);
        if (vi != null) {
            BufferedImage imgSource = vi.getPanel().getImagen();
            if (imgSource != null) {
                try {
                    AffineTransform at = AffineTransform.getScaleInstance(1.25, 1.25);
                    AffineTransformOp atop = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage imgdest = atop.filter(imgSource, null);
                    vi.getPanel().setImagen(imgdest);
                    vi.getPanel().setPreferredSize(new Dimension(imgdest.getWidth(),imgdest.getHeight()));
                    vi.getPanel().repaint();
                } catch (Exception e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_botonEscalaAumentarActionPerformed

    private void botonEscalaReducirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEscalaReducirActionPerformed
        VentanaInternaImagen vi = getVentanaInternaImagenActiva(true);
        if (vi != null) {
            BufferedImage imgSource = vi.getPanel().getImagen();
            if (imgSource != null) {
                try {
                    AffineTransform at = AffineTransform.getScaleInstance(0.75, 0.75);
                    AffineTransformOp atop = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage imgdest = atop.filter(imgSource, null);
                    vi.getPanel().setImagen(imgdest);
                    vi.getPanel().setPreferredSize(new Dimension(imgdest.getWidth(),imgdest.getHeight()));
                    vi.getPanel().repaint();
                } catch (Exception e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_botonEscalaReducirActionPerformed

    private void botonRotacionbotonTrazo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRotacionbotonTrazo
        Util.mostrarDialogoCentro(this, dialogoSliderRotacion);
        dialogoSliderRotacion.setVisible(true);
    }//GEN-LAST:event_botonRotacionbotonTrazo

    private void botonContrasteLookupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonContrasteLookupActionPerformed
        VentanaInternaImagen vi = getVentanaInternaImagenActiva(true);
        if (vi != null) {
            BufferedImage imgSource = vi.getPanel().getImagen();
            if (imgSource != null) 
                try {
                    LookupTable lt = Util.contrasteLookup();
                    LookupOp lop = new LookupOp(lt, null);
                    lop.filter(imgSource, imgSource);
                    vi.getPanel().repaint();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getLocalizedMessage());
                }
        }
        
    }//GEN-LAST:event_botonContrasteLookupActionPerformed

    private void botonEspejoHorizontalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEspejoHorizontalActionPerformed
        VentanaInternaImagen vi = getVentanaInternaImagenActiva(true);
        if (vi != null) {
            BufferedImage imgSource = vi.getPanel().getImagen();
            if (imgSource != null) 
                try {
                    EspejoHorizontalOP espejoHop = new EspejoHorizontalOP();
                    BufferedImage dest = espejoHop.filter(imgSource, null);
                    vi.getPanel().setImagen(dest);
                    vi.repaint();
                } catch (Exception e) {
                    System.err.println(e.getLocalizedMessage());
                }
        }
    }//GEN-LAST:event_botonEspejoHorizontalActionPerformed

    private void botonEspejoVerticalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEspejoVerticalActionPerformed
        VentanaInternaImagen vi = getVentanaInternaImagenActiva(true);
        if (vi != null) {
            BufferedImage imgSource = vi.getPanel().getImagen();
            if (imgSource != null) 
                try {
                    EspejoVerticalOP espejoVop = new EspejoVerticalOP();
                    BufferedImage dest = espejoVop.filter(imgSource, null);
                    vi.getPanel().setImagen(dest);
                    vi.repaint();
                } catch (Exception e) {
                    System.err.println(e.getLocalizedMessage());
                }
        }
    }//GEN-LAST:event_botonEspejoVerticalActionPerformed

    private void botonCapturaWebCambotonTrazo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCapturaWebCambotonTrazo
        VentanaInternaCamara vc = getVentanaInternaCamaraActiva(false);
        if(vc != null)
            vc.capturarPantalla();
        else{
            VentanaInternaVideo viv = getVentanaInternaVideoActiva(false);
            if(viv != null)
                viv.capturarPantalla();
            else
                 getVentanaInternaImagenActiva(true);
        }
        
    }//GEN-LAST:event_botonCapturaWebCambotonTrazo

    private void botonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPlayActionPerformed
        
        VentanaInternaVideo viv = getVentanaInternaVideoActiva(false);
        if(viv != null){
            if(viv.isPlaying()){
                viv.pause();
                pauseCronometro();
            }else{
                viv.play();
                playCronometro(labelTiempo, progresoReproductor);
            }
        }else{
            File f = (File) listaReproduccion.getSelectedItem();
            if(f!=null){
                if(player.getPlayer() == null){
                    player.setPlayer(f);
                    player.getPlayer().addLineListener(new PlayerListener(this));
                    labelTiempo.setText("00:00:00");
                }
                if(player.isPlaying()){
                    player.pause();
                    pauseCronometro();
                }else{
                    player.play();
                    labelDuracion.setText(player.getDuracion());
                    progresoReproductor.setMaximum(player.getDuracionSegundos());
                    playCronometro(labelTiempo, progresoReproductor);
                }
            }else
                JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún fichero de audio", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botonPlayActionPerformed

    private void botonStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonStopActionPerformed
        VentanaInternaVideo viv = getVentanaInternaVideoActiva(false);
        if(viv != null){
            viv.stop();
            stopCronometro(labelTiempo);
        }else{
            File f = (File) listaReproduccion.getSelectedItem();
            if (f != null) {
                    player.stop();
                    stopCronometro(labelTiempo);
            }else
                JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún fichero de audio", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botonStopActionPerformed

    private void botonRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRecordActionPerformed
        if (recorder != null) {
            recorder.stop();
            stopCronometro(labelDuracionRecord);
            labelDuracionRecord.setVisible(false);
            barraSonido.setPreferredSize(new Dimension(610,35));
            recorder = null;
            Ficheros.filtrarExtensionesFileChooserSonido(fileChooserGuardar);
            int resp = fileChooserGuardar.showSaveDialog(this);
            if (resp == JFileChooser.APPROVE_OPTION) {
                try {
                    File mif = new File(temp.getAbsolutePath()) {
                        @Override
                        public String toString() {
                            return this.getName();
                        }
                    };
                    listaReproduccion.removeItem(mif);
                    File f = fileChooserGuardar.getSelectedFile();
                    temp.renameTo(new File(f.getAbsolutePath()));
                    temp.delete();
                    mif = new File(f.getAbsolutePath()) {
                        @Override
                        public String toString() {
                            return this.getName();
                        }
                    };
                    addListaReproduccion(mif);
                    if (!Ficheros.extensiones_sonido.contains(Ficheros.getExtensionFichero(f))) {
                        f.delete();
                        listaReproduccion.removeItem(mif);
                        JOptionPane.showMessageDialog(this, "Formato no soportado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al abrir sonido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                File mif = new File(temp.getAbsolutePath()) {
                    @Override
                    public String toString() {
                        return this.getName();
                    }
                };
                listaReproduccion.removeItem(mif);
                temp.delete();
            }
        } else {
            temp = new File("temp.wav");
            File mif = new File(temp.getAbsolutePath()) {
                @Override
                public String toString() {
                    return this.getName();
                }
            };
            addListaReproduccion(mif);
            if (mif != null) {
                recorder = (SMRecorder) new SMSoundRecorder(temp);
                ((SMSoundRecorder) recorder).addLineListener(new RecorderListener(this));
                if (recorder != null) {
                    recorder.record();   
                    Cronometro.setSegundos(0);
                    labelDuracionRecord.setVisible(true);
                    barraSonido.setPreferredSize(new Dimension(685,35));
                    playCronometro(labelDuracionRecord,null);
                }
            }
        }
    }//GEN-LAST:event_botonRecordActionPerformed

    private void botonWebCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonWebCamActionPerformed
        VentanaInternaCamara.getInstance(this);
    }//GEN-LAST:event_botonWebCamActionPerformed

    private void itemFormasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFormasActionPerformed
        barraFormas.setVisible(itemFormas.isSelected());
        barraEditar.setVisible(itemFormas.isSelected());
        barraAtributos.setVisible(itemFormas.isSelected());
    }//GEN-LAST:event_itemFormasActionPerformed

    private void itemPaletaColoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPaletaColoresActionPerformed
        barraPaletaColores.setVisible(itemPaletaColores.isSelected());
    }//GEN-LAST:event_itemPaletaColoresActionPerformed

    private void itemImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemImagenActionPerformed
        barraImagen.setVisible(itemImagen.isSelected());
        Dimension psi = panelInferior.getPreferredSize();
        Dimension pssvi = panelSonidoVideoImagen.getPreferredSize();
        if(itemImagen.isSelected()){
            panelInferior.setPreferredSize(new Dimension(1000,psi.height+40));
            panelSonidoVideoImagen.setPreferredSize(new Dimension(1000,pssvi.height+40));
        }else{
            panelInferior.setPreferredSize(new Dimension(1000,psi.height-40));
            panelSonidoVideoImagen.setPreferredSize(new Dimension(1000,pssvi.height-40));
        }
    }//GEN-LAST:event_itemImagenActionPerformed

    private void itemSonidoVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSonidoVideoActionPerformed
        barraSonido.setVisible(itemSonidoVideo.isSelected());
        barraWebCam.setVisible(itemSonidoVideo.isSelected());
        Dimension psi = panelInferior.getPreferredSize();
        Dimension pssvi = panelSonidoVideoImagen.getPreferredSize();
        if(itemSonidoVideo.isSelected()){
            panelInferior.setPreferredSize(new Dimension(1000,psi.height+40));
            panelSonidoVideoImagen.setPreferredSize(new Dimension(1000,pssvi.height+40));
        }else{
            panelInferior.setPreferredSize(new Dimension(1000,psi.height-40));
            panelSonidoVideoImagen.setPreferredSize(new Dimension(1000,pssvi.height-40));
        }
    }//GEN-LAST:event_itemSonidoVideoActionPerformed

    private void itemAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAcercaDeActionPerformed
        DialogoAcercaDe dad = new DialogoAcercaDe(this);
        Util.mostrarDialogoCentro(this, dad);
        dad.setVisible(true);
    }//GEN-LAST:event_itemAcercaDeActionPerformed

    private void botonTamañoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTamañoActionPerformed
        VentanaInternaImagen vi = getVentanaInternaImagenActiva(true);
        if(vi != null){
            if(vi.getPanel().getSeleccionado() != null){
                Forma seleccionado = vi.getPanel().getSeleccionado();
                PanelTamaño panelTamaño = new PanelTamaño();
                panelTamaño.getTfAncho().setText(Integer.toString((int)seleccionado.getWidth()));
                panelTamaño.getTfAlto().setText(Integer.toString((int)seleccionado.getHeight()));
                int ancho, alto;
                int result = JOptionPane.showConfirmDialog(null, panelTamaño, "Introduce ancho y alto de la forma", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/iconos/atributos/resize65.png")));
                if (result == JOptionPane.OK_OPTION) {
                    ancho = panelTamaño.getAncho();
                    alto = panelTamaño.getAlto();
                    seleccionado.setWidth(ancho);
                    seleccionado.setHeight(alto);
                    vi.repaint();
                }
            }else
                JOptionPane.showMessageDialog(escritorio, "No hay forma seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botonTamañoActionPerformed

    private void botonTintadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTintadoActionPerformed
        Util.mostrarDialogoCentro(this, dialogoSliderTintado);
        dialogoSliderTintado.setVisible(true);
    }//GEN-LAST:event_botonTintadoActionPerformed

    private void botonCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCopiarActionPerformed
        VentanaInternaImagen vi = getVentanaInternaImagenActiva(true);
        if (vi != null) 
            if(vi.getPanel().getSeleccionado()!=null)
                vi.getPanel().copiarForma(vi.getPanel().getSeleccionado());
    }//GEN-LAST:event_botonCopiarActionPerformed

    private void botonBrilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBrilloActionPerformed
        Util.mostrarDialogoCentro(this, dialogoSliderBrillo);
        dialogoSliderBrillo.setVisible(true);
    }//GEN-LAST:event_botonBrilloActionPerformed

    private void botonContrasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonContrasteActionPerformed
        Util.mostrarDialogoCentro(this, dialogoContraste);
        dialogoContraste.setVisible(true);
    }//GEN-LAST:event_botonContrasteActionPerformed

    private DialogoOrdenacion dialogoOrdenacion;
    private DialogoContorno dialogoContorno;
    private DialogoRelleno dialogoRelleno;
    private DialogoSlider dialogoSliderTransparencia;
    private DialogoEscalar dialogoEscalar;
    private DialogoSlider dialogoSliderBrillo;
    private DialogoContraste dialogoContraste;
    private DialogoSlider dialogoSliderViñeta;
    private DialogoSlider dialogoSliderRotacion;
    private DialogoSlider dialogoSliderTintado;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar barraArchivo;
    private javax.swing.JToolBar barraAtributos;
    private javax.swing.JToolBar barraEditar;
    private javax.swing.JLabel barraEstado;
    private javax.swing.JToolBar barraFormas;
    private javax.swing.JToolBar barraImagen;
    private javax.swing.JToolBar barraPaletaColores;
    private javax.swing.JToolBar barraSonido;
    private javax.swing.JToolBar barraWebCam;
    private javax.swing.JButton botonAbrir;
    private javax.swing.JToggleButton botonAlisar;
    private javax.swing.JToggleButton botonArco;
    private javax.swing.JButton botonBandas;
    private javax.swing.JButton botonBrillo;
    private javax.swing.JButton botonCapturaWebCam;
    private javax.swing.JButton botonContorno;
    private javax.swing.JButton botonContraste;
    private javax.swing.JButton botonContrasteLookup;
    private javax.swing.JButton botonCopiar;
    private javax.swing.JToggleButton botonCurvaCuadrada;
    private javax.swing.JToggleButton botonEditar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JToggleButton botonElipse;
    private javax.swing.JButton botonEscalaAumentar;
    private javax.swing.JButton botonEscalaReducir;
    private javax.swing.JButton botonEspejoHorizontal;
    private javax.swing.JButton botonEspejoVertical;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JToggleButton botonLinea;
    private javax.swing.JToggleButton botonM;
    private javax.swing.JButton botonNegativo;
    private javax.swing.JButton botonNuevo;
    private javax.swing.JButton botonOrdenacion;
    private javax.swing.JToggleButton botonPegar;
    private javax.swing.JButton botonPlay;
    private javax.swing.JButton botonRecord;
    private javax.swing.JToggleButton botonRectangulo;
    private javax.swing.JToggleButton botonRectanguloRedondeado;
    private javax.swing.JButton botonRelleno;
    private javax.swing.JButton botonRotacion;
    private javax.swing.JToggleButton botonSelloM;
    private javax.swing.JButton botonSeno;
    private javax.swing.JButton botonSepia;
    private javax.swing.JButton botonStop;
    private javax.swing.JButton botonTamaño;
    private javax.swing.JButton botonTintado;
    private javax.swing.JButton botonTransparencia;
    private javax.swing.JToggleButton botonTrazoLibre;
    private javax.swing.JButton botonViñeta;
    private javax.swing.JButton botonWebCam;
    private javax.swing.JComboBox<String> comboFiltro;
    private javax.swing.JComboBox<String> comboModoColor;
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JFileChooser fileChooserAbrir;
    private javax.swing.JFileChooser fileChooserGuardar;
    private javax.swing.ButtonGroup grupoBarraHerramientas;
    private javax.swing.JMenuItem itemAbrir;
    private javax.swing.JMenuItem itemAcercaDe;
    private javax.swing.JCheckBoxMenuItem itemBorde;
    private javax.swing.JMenuItem itemDuplicar;
    private javax.swing.JCheckBoxMenuItem itemFormas;
    private javax.swing.JMenuItem itemGuardar;
    private javax.swing.JCheckBoxMenuItem itemImagen;
    private javax.swing.JMenuItem itemNuevo;
    private javax.swing.JCheckBoxMenuItem itemPaletaColores;
    private javax.swing.JCheckBoxMenuItem itemSonidoVideo;
    private javax.swing.JMenuItem itemTamaño;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator10;
    private javax.swing.JToolBar.Separator jSeparator17;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JLabel labelDuracion;
    private javax.swing.JLabel labelDuracionRecord;
    private javax.swing.JLabel labelPosicion;
    private javax.swing.JLabel labelRGB;
    private javax.swing.JLabel labelTiempo;
    private javax.swing.JComboBox<File> listaReproduccion;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuEditar;
    private javax.swing.JMenu menuImagen;
    private javax.swing.JMenuBar menuPrincipal;
    private javax.swing.JPanel panelInferior;
    private SM.MRC.Panel.PanelPaletaColores panelPaletaColores;
    private javax.swing.JPanel panelPosicion;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelSonidoVideoImagen;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JProgressBar progresoReproductor;
    private javax.swing.JToolBar.Separator separador1;
    private javax.swing.JToolBar.Separator separador2;
    private javax.swing.JToolBar.Separator separador3;
    private javax.swing.JToolBar.Separator separatorFormas;
    private javax.swing.JToolBar.Separator separatorFormas1;
    private javax.swing.JSpinner spinnerGrosor;
    // End of variables declaration//GEN-END:variables

    public PanelPaletaColores getPanelPaletaColores() {
        return panelPaletaColores;
    }

    public JSpinner getSpinnerGrosor() {
        return spinnerGrosor;
    }
    
    public JDesktopPane getEscritorio() {
        return escritorio;
    }
    
    public JButton getBotonRelleno(){
        return botonRelleno;
    }

    public JToggleButton getBotonAlisar() {
        return botonAlisar;
    }

    public JToggleButton getBotonEditar() {
        return botonEditar;
    }

    public JLabel getBarraEstado() {
        return barraEstado;
    }

    public ButtonGroup getGrupoBarraHerramientas() {
        return grupoBarraHerramientas;
    }

    public JPanel getPanelPosicion() {
        return panelPosicion;
    }

    public JLabel getLabelPosicion() {
        return labelPosicion;
    }

    public JLabel getLabelDuracion() {
        return labelDuracion;
    }

    public JLabel getLabelRGB() {
        return labelRGB;
    }

    public JLabel getLabelTiempo() {
        return labelTiempo;
    }

    public JProgressBar getProgresoReproductor() {
        return progresoReproductor;
    }

    public Cronometro getCronometroTiempo() {
        return cronometro;
    }
    
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public JButton getBotonPlay() {
        return botonPlay;
    }
    
    public void terminarPlay(){
        player = null;
    }

    public JButton getBotonRecord() {
        return botonRecord;
    }
    
    public void terminarRecord(){
        recorder = null;
    }
    
    public void addListaReproduccion(File mif){
        ArrayList<File> af = new ArrayList();
        for(int i = 0; i < listaReproduccion.getItemCount(); i++)
            af.add(listaReproduccion.getItemAt(i));
        if(!af.contains(mif)){
        listaReproduccion.addItem(mif);
        listaReproduccion.setSelectedItem(mif);
        
        }
    }
    
    private void playCronometro(JLabel label, JProgressBar progreso){
        cronometro = new Cronometro(label,progreso);
        cronometro.playCronometro();
    }
    
    public void stopCronometro(JLabel label){
        cronometro.stopCronometro();
        label.setText("00:00:00");
        progresoReproductor.setValue(0);
        Cronometro.setSegundos(0);
    }
    
    public void pauseCronometro(){
        cronometro.pauseCronometro();
    }
}
