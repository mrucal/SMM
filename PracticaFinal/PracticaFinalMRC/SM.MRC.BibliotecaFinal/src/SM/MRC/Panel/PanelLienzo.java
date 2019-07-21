package SM.MRC.Panel;

import SM.MRC.Grafico.Forma.CurvaCuadrada;
import SM.MRC.Grafico.Forma.Elipse;
import SM.MRC.Grafico.Estilo.EstiloRelleno;
import SM.MRC.Grafico.Forma.Arco;
import SM.MRC.Grafico.Forma.Forma;
import SM.MRC.Grafico.Forma.Linea;
import SM.MRC.Grafico.Forma.M;
import SM.MRC.Grafico.Forma.MSello;
import SM.MRC.Grafico.Forma.PatronDibujoDosPasos;
import SM.MRC.Grafico.Forma.Rectangulo;
import SM.MRC.Grafico.Forma.TrazoLibre;
import SM.MRC.Util.Herramienta;
import java.awt.BasicStroke;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;
import SM.MRC.Grafico.Forma.PatronDibujoUnPaso;
import SM.MRC.Grafico.Forma.RectanguloRedondeado;

/**
 * Clase Panel Lienzo.
 * 
 * Panel en el que es posible dibujar formas.
 * 
 * @author Mario
 */
public class PanelLienzo extends javax.swing.JPanel {

    protected List<Forma> formas = new ArrayList(); 
    
    Herramienta herramienta;
    
    EstiloRelleno estilo;
    
    private boolean editar;
    
    protected Forma seleccionado;
    private Point2D p_seleccion;
    
    private Forma copia;
    
    private boolean pegar;
    
    private int paso = 0;
    
    protected boolean borde;
    
    /**
     * Constructor por defecto.
     */
    public PanelLienzo() {
        initComponents();
        estilo = new EstiloRelleno();
        herramienta = Herramienta.Línea;
        editar = false;
        seleccionado = null;
        borde = true;
        pegar = false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FormListener formListener = new FormListener();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(300, 300));
        addMouseMotionListener(formListener);
        addMouseListener(formListener);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.MouseListener, java.awt.event.MouseMotionListener {
        FormListener() {}
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == PanelLienzo.this) {
                PanelLienzo.this.formMousePressed(evt);
            }
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == PanelLienzo.this) {
                PanelLienzo.this.formMouseReleased(evt);
            }
        }

        public void mouseDragged(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == PanelLienzo.this) {
                PanelLienzo.this.formMouseDragged(evt);
            }
        }

        public void mouseMoved(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == PanelLienzo.this) {
                PanelLienzo.this.formMouseMoved(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        if (editar) {
            getSelectedForma(evt.getPoint());
            p_seleccion = evt.getPoint();
        } else 
            if (pegar) {
                if (copia != null) {
                    copia.setLocation(evt.getPoint());
                    formas.add(copia);
                    copiarForma(copia);
                }
            } else {
                if (seleccionado != null) 
                    eliminarSeleccion();
                switch (herramienta) {
                    case Línea:
                        formas.add(new Linea(evt.getPoint(), evt.getPoint(), estilo));
                        break;
                    case Rectángulo:
                        formas.add(new Rectangulo(evt.getPoint(), 0, 0, estilo));
                        break;
                    case RectánguloRedondeado:
                        formas.add(new RectanguloRedondeado(evt.getPoint(), 0, 0, estilo));
                        break;
                    case Elipse:
                        formas.add(new Elipse(evt.getPoint(), 0, 0, estilo));
                        break;
                    case Arco:
                        formas.add(new Arco(evt.getPoint(), 0, 0, estilo));
                        break;
                    case CurvaCuadrada:
                        formas.add(new CurvaCuadrada(evt.getPoint(), evt.getPoint(), estilo));
                        break;
                    case TrazoLibre:
                        formas.add(new TrazoLibre(evt.getPoint(), estilo));
                        break;
                    case M:
                        formas.add(new M(evt.getPoint(), estilo));
                        break;
                    case SelloM:
                        formas.add(new MSello(evt.getPoint(), estilo));
                        break;
                    }
                }
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if(editar){
            if(seleccionado != null){
                this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                double x = seleccionado.getLocation().getX() + evt.getPoint().getX() - p_seleccion.getX();
                double y = seleccionado.getLocation().getY() + evt.getPoint().getY() - p_seleccion.getY();
                seleccionado.setLocation(new Point2D.Double(x, y));
                p_seleccion = evt.getPoint();
            }
        }else
            if(!pegar)
                switch(herramienta){
                    case Ninguna:
                        break;
                    case TrazoLibre:
                        // Para no almacenar excesivos puntos, los vamos almacenando de 4 en 4
                        if(paso%4==0)
                            ((TrazoLibre)formas.get(formas.size()-1)).addPunto(evt.getPoint());
                        paso++;
                        break;
                    default:
                        ((PatronDibujoUnPaso) formas.get(formas.size() - 1)).setPuntoPaso1(evt.getPoint());   
                }
        this.repaint();
    }//GEN-LAST:event_formMouseDragged

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        formMouseDragged(evt);
        switch(herramienta){
            case RectánguloRedondeado:
            case Arco:
            case CurvaCuadrada:
                paso = (paso + 1)%2;
                break;
            case TrazoLibre:
                paso = 0;
                ((TrazoLibre)formas.get(formas.size()-1)).cerrarTrazo();
                break;
        }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_formMouseReleased

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        if(editar)
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        else{
            this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            if(!pegar && paso == 1 )
                switch(herramienta){
                    case RectánguloRedondeado:
                    case Arco:
                    case CurvaCuadrada:
                        ((PatronDibujoDosPasos)formas.get(formas.size() - 1)).setPuntoPaso2(evt.getPoint()); 
                    this.repaint();
                }
        }
    }//GEN-LAST:event_formMouseMoved
    
    /**
     * Obtiene la lista de formas.
     * 
     * @return Lista de formas.
     */
    public List<Forma> getFormas(){
        return formas;
    }
    
    /** 
     * Obtiene la forma más cercana a un punto.
     * 
     * @param p Punto con el que se hace la selección.
     */
    public void getSelectedForma(Point2D p){
        eliminarSeleccion();
        for(int i = formas.size()-1; i >= 0; i--)
            if(formas.get(i).contains(p)){
                seleccionado = formas.get(i);
                break;
            }
        if(seleccionado != null)
            seleccionar();
    }
    
    /**
     * Obtiene el estilo actual del panel.
     * 
     * @return Estilo del panel.
     */
    public EstiloRelleno getEstilo(){
        return estilo;
    }

    /**
     * Obtiene la herramienta seleccionada en el panel actualmente.
     * 
     * @return Herramienta seleccionada.
     */
    public Herramienta getHerramienta() {
        return herramienta;
    }

    /**
     * Modifica la herramienta seleccionada en el panel.
     * 
     * @param herramienta Nombre de la herramienta que se va a seleccionar.
     */
    public void setHerramienta(String herramienta) {
        this.herramienta = Herramienta.valueOf(herramienta);
    }

    /**
     * Indica si el panel se encuentra en el modo de edición.
     * 
     * @return True si el panel está en modo edición, false en caso contrario.
     */
    public boolean isEditar() {
        return editar;
    }

    /**
     * Cambia al modo de edición o de dibujo.
     * 
     * @param editar True, si se va a cambiar al modo edición, false si se cambia a modo dibujo.
     */
    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    /**
     * Obtiene la forma seleccionada en el panel actualmente.
     * 
     * @return Fora seleccionada en el panel.
     */
    public Forma getSeleccionado() {
        return seleccionado;
    }

    /**
     * Indica si está habilitado el dibujo del borde.
     * 
     * @return True, si está habilitado el dibujo del borde, false en caso contrario.
     */
    public boolean isBorde() {
        return borde;
    }

    /**
     * Habilita o deshabilita el dibujo del borde.
     * 
     * @param borde True, si se habilita el dibujo del borde, false en caso contrario.
     */
    public void setBorde(boolean borde) {
        this.borde = borde;
    }

    /**
     * Cambiar a modo pegar.
     * 
     * @param pegar True, si se va a cambiar a modo pegar, false si se sale de el.
     */
    public void setPegar(boolean pegar) {
        this.pegar = pegar;
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    /**
     * Elimina una forma de la lista de formas que hay dibujadas.
     * 
     * @param f Forma que se va a eliminar.
     */
    public void eliminar(Forma f){
        formas.remove(f);
        this.repaint();
    }
    
    /**
     * Copia una forma al "portapapeles"
     * 
     * @param forma Forma que se va a copiar.
     */
    public void copiarForma(Forma forma){
        if(forma != null){
            if(forma instanceof Linea)
                copia = new Linea((Linea)forma);
            if(forma instanceof Rectangulo)
                copia = new Rectangulo((Rectangulo)forma);
            if(forma instanceof RectanguloRedondeado)
                copia = new RectanguloRedondeado((RectanguloRedondeado)forma);
            if(forma instanceof Elipse)
                copia = new Elipse((Elipse)forma);
            if(forma instanceof Arco)
                copia = new Arco((Arco)forma);
            if(forma instanceof CurvaCuadrada)
                copia = new CurvaCuadrada((CurvaCuadrada)forma);
            if(forma instanceof TrazoLibre)
                copia = new TrazoLibre((TrazoLibre)forma);
            if(forma instanceof M)
                copia = new M((M)forma);
            if(forma instanceof MSello)
                copia = new MSello((MSello)forma);
            eliminarSeleccion();
        }
        repaint();
    }
    
    /**
     * Eliminar lo que hay en el "portapapeles"
     */
    public void borrarPortapapeles(){
        copia = null;
    }
    
    /**
     * Cambia el orden en el que se van a dibujar las formas, intercambiando dos de ellas.
     * 
     * @param i índice de la forma, en la lista de formas, que se va a cambiar.
     * @param nuevo_i Índice de la nueva posición de la forma.
     */
    public void cambiarOrden(int i, int nuevo_i){
        Forma f = formas.get(i);
        formas.set(i, formas.get(nuevo_i));
        formas.set(nuevo_i, f);
        this.repaint();
    }
    
    /**
     * Selecciona la forma que está actualmente seleccionada en el panel.
     */
    public void seleccionar(){
        seleccionado.setSeleccionado(true);
    }
    
    /**
     * Elimina la seleccion de la forma que está acutalmente seleccionada en el panel.
     */
    public void eliminarSeleccion(){
        if(seleccionado!=null)
            seleccionado.setSeleccionado(false);
        seleccionado = null;
    }
    
    /**
     * Dibuja un borde al panel.
     * 
     * @param g2d Graphics2D donde se va a dibujar el borde.
     */
    public void drawBorde(Graphics2D g2d){
        if(borde){
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1, new float[]{3, 3}, 0));
            g2d.draw(new Rectangle(0, 0, this.getWidth(), this.getHeight()));
        }
    }
    
    @Override
    protected void paintBorder(Graphics g){
        super.paintBorder(g);
        Graphics2D g2d = (Graphics2D)g;
        drawBorde(g2d);
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        for(Forma f:formas)
            f.draw(g2d);
    }    
    
}
