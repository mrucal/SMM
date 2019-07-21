package SM.MRC.Grafico.Forma;

import SM.MRC.Grafico.Estilo.Estilo;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Clase abstracta que representa una forma (gemoetría y estilo).
 * 
 * @author Mario
 */
public abstract class Forma{
    
    Estilo estilo;
    
    boolean seleccionado = false;
    
    /**
     * Constructor por defecto
     */
    public Forma(){
        estilo = new Estilo();
    }
      
    /**
     * Constructor por defecto, indicando un estilo.
     * @param estilo Atributos que tendrá la forma.
     */
    public Forma(Estilo estilo){
        this.estilo = new Estilo(estilo);
    }
    
    /**
     * Indica si la forma está seleccionada en el modo edición.
     * @return true si la forma está seleccionada.
     */
    public boolean isSeleccionado() {
        return seleccionado;
    }

    /**
     * Seleccionar una forma.
     * @param seleccionado True si se selecciona la forma, false en caso contrario.
     */
    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
    
    /**
     * Obtiene el objeto Shape asociado a la forma
     * 
     * @return Objeto Shape que representa internamente la forma.
     */
    abstract Shape getShape();
    
    /**
     * Obtiene el ancho del bound en que está contenido la forma
     * 
     * @return Ancho del bound en que está contenido la forma
     */
    public double getWidth() {
        return getShape().getBounds2D().getWidth();
    }
    
    /**
     * Modifica el ancho del bound en que está contenido la forma.
     * 
     * @param width Ancho del bound en que está contenido la forma.
     */
    public void setWidth(double width){
        if( this instanceof PatronDibujoUnPaso){
            PatronDibujoUnPaso pdup = ((PatronDibujoUnPaso)this);
            Point2D p1 = new Point2D.Double(pdup.getPuntoPaso0().getX()+width, pdup.getPuntoPaso1().getY());
            pdup.setPuntoPaso1(p1);
        }
    }

    /**
     * Obtiene el alto del bound en el que está contenido la forma
     * 
     * @return Alto del bound en el que está contenido la forma
     */
    public double getHeight() {
        return getShape().getBounds2D().getHeight();
    }
    
    /**
     * Modifia el alto del bound en el que está contenido la forma.
     * 
     * @param height Alto del bound en el que está contenido la forma.
     */
    public void setHeight(double height){
        if( this instanceof PatronDibujoUnPaso){
            PatronDibujoUnPaso pdup = ((PatronDibujoUnPaso)this);
            Point2D p1 = new Point2D.Double(pdup.getPuntoPaso1().getX(), pdup.getPuntoPaso0().getY()+height);
            pdup.setPuntoPaso1(p1);
        }
    }

    /**
     * Obtiene el rectangulo(bound) en el que está contenido la forma.
     * 
     * @return Bound de la forma.
     */
    public Rectangle2D getBounds() {
        return getShape().getBounds2D();
    }
    
    /**
     * Obtiene el color de fondo de la forma.
     * 
     * @return Color de fondo de la forma.
     */
    public Color getColorFondo(){
        return estilo.getColorFondo();
    }
    
    /**
     * Modifica el color de fondo de la forma.
     * 
     * @param colorFondo Color de fondo de la forma.
     */
    public void setColorFondo(Color colorFondo){
        estilo.setColorFondo(colorFondo);
    }
    
    /**
     * Obtiene el color de frente de la forma.
     * 
     * @return Color de frente de la forma.
     */
    public Color getColorFrente(){
        return estilo.getColorFrente();
    }
    
    /**
     * Modifica el color de frente de la forma.
     * 
     * @param colorFrente Colorde frente de la forma
     */
    public void setColorFrente(Color colorFrente){
        estilo.setColorFrente(colorFrente);
    }
    
    /**
     * Obtiene el grosor del contorno de la forma.
     * 
     * @return Grosor del contorno de la forma.
     */
    public int getGrosor(){
        return estilo.getContorno().getGrosor();
    }
    
    /**
     * Modifica el grosor del contorno de la forma.
     * 
     * @param grosor Grosor del contorno de la forma.
     */
    public void setGrosor(int grosor){
        estilo.getContorno().setGrosor(grosor);
    }
    
    /**
     * Obtiene el tipo de discontinuidad del contorno. 
     * @return Tipo de discontinuidad del contorno. Puede ser {@value SM.MRC.Grafico.Estilo.Contorno#CONTINUA} o {@value SM.MRC.Grafico.Estilo.Contorno#PUNTEADA} 
     *  
     */
    public int getDiscontinuidad(){
        return estilo.getContorno().getDiscontinuidad();
    }
    
    /**
     * Modifica el tipo de discontinuidad del contorno. 
     * 
     * @param discontinuidad Tipo de discontinuidad del contorno. Puede ser {@value SM.MRC.Grafico.Estilo.Contorno#CONTINUA} o {@value SM.MRC.Grafico.Estilo.Contorno#PUNTEADA} 
     */
    public void setDiscontinuidad(int discontinuidad){
        estilo.getContorno().setDiscontinuidad(discontinuidad);
    }
    
    /**
     * Obtiene el tipo de contorno.
     * 
     * @return true, si la forma tiene contorno. false, en caso contrario.
     */
    public boolean getTipoContorno(){
        return this.estilo.getContorno().getTipo();
    }
    
    /**
     * Modifica el tipo de contorno de la forma. 
     * 
     * @param tipo Tipo de contorno. True, si tiene contorno, false en caso contrario.
     */
    public void setTipoContorno(boolean tipo){
        this.estilo.getContorno().setTipo(tipo);
    }
    
    /**
     * Obtiene el color de contorno de la forma.
     * 
     * @return Color de contorno de la forma.
     */
    public Color getColorContorno(){
        return this.estilo.getContorno().getColor();
    }
    
    /**
     * Modifica el color de contorno de la forma.
     * 
     * @param color Color de contorno de la forma.
     */
    public void setColorContorno(Color color){
        this.estilo.getContorno().setColor(color);
    }
    
    /**
     * Obtiene  el grado de transparencia de la forma.
     * @return Valor entre 0 y 1 que indica el grado de transparencia. 0 Opaco, 1 Transparente.
     */
    public float getGradoTransparencia(){
        return estilo.getTransparencia().getGradoTransparencia();
    }
    
    /**
     * Modifica el grado de transparencia.
     * 
     * @param grado_transparencia Valor entre 0 y 1 que indica el grado de transparencia.0 Opaco, 1 Transparente.
     */
    public void setGradoTransparencia(float grado_transparencia){
        estilo.getTransparencia().setGradoTransparencia(grado_transparencia);
    }
    
    /**
     * Indica si la forma se ha dibujado con alisado.
     * 
     * @return true, si se ha dibujado con alisado, false en caso contrario.
     */
    public boolean isAlisado(){
        return estilo.getAlisado().isActivado();
    }
    
    /**
     * Modifica el tipo de alisado.
     * 
     * @param activar true, para alisar la forma, false en caso contrario.
     */
    public void setActivarAlisado(boolean activar){
        estilo.getAlisado().setActivado(activar);
    }
    
    /**
     * Indica si la forma se puede rellenar.
     * 
     * @return true, si se puede rellenar, false en caso contrario.
     */
    public boolean isRellenable(){
        return false;
    }
    
    /** 
     * Devuelve la localización de la forma
     * 
     * @return Punto de referencia de la localización.
     */
    public Point2D getLocation(){
        if(this instanceof PatronDibujoUnPaso){
            return ((PatronDibujoUnPaso)this).getPuntoPaso0();
        }
        if(this instanceof PatronDibujoDosPasos){
            return ((PatronDibujoDosPasos)this).getPuntoPaso0();
        }
        return null;
    }
    
    /**
     * Mueve la forma a un punto dado.
     * 
     * @param p Punto de la nueva referencia a la localización.
     */
    public abstract void setLocation(Point2D p);
    
    /**
     * Indica si un punto esta contenido en la forma.
     * @param p Punto 
     * @return true, si el p está dentro de la forma. false, en caso contrario.
     */
    public boolean contains(Point2D p){
        return getShape().contains(p);
    }
    
    /**
     * Dibuja el boundingbox en el que esta contenido la forma.
     * 
     * @param g2d Graphics2D donde se va a dibujar el boundingBox.
     */
    public void drawBoundingBox(Graphics2D g2d){
        if(seleccionado){
            if(getColorContorno().equals(Color.RED))
                g2d.setPaint(Color.BLACK);
            else
                g2d.setPaint(Color.RED);
            g2d.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER, 1.0f,new float[]{5.0f , 5.0f}, 0.0f)); 
            g2d.draw(getBounds());
        }
    }
    
    /**
     * Dibuja el contorno de la forma.
     * 
     * @param g2d Graphics2D donde se va a dibujar la forma.
     */
    public void drawContorno(Graphics2D g2d){
        estilo.getContorno().paint(g2d);
        if(getTipoContorno())
            g2d.draw((Shape)getShape());
    }
    
    /**
     * Cambia los atributos del Graphics2D con los valores del estilo de la forma.
     * 
     * @param g2d Graphics2D donde se van a cambiar los atributos.
     */
    void drawEstilo(Graphics2D g2d){
        estilo.getAlisado().paint(g2d);
        estilo.getTransparencia().paint(g2d);
    }
    
    /**
     * Dibuja la forma.
     * 
     * @param g2d Graphics2D donde se va a dibujar la forma.
     */
    public void drawForma(Graphics2D g2d){
        drawContorno(g2d);
    }
    
    /**
     * Dibuja el boundingbox, el estilo y la forma.
     * 
     * @param g2d Graphics2D donde se va a dibujar.
     */
    public void draw(Graphics2D g2d){
        drawEstilo(g2d);
        drawForma(g2d);
        drawBoundingBox(g2d);
    }
    
}
