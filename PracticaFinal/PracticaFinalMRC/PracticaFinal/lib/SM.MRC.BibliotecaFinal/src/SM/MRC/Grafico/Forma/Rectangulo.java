package SM.MRC.Grafico.Forma;

import SM.MRC.Grafico.Estilo.EstiloRelleno;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Forma Rectángulo (rellenable).
 * 
 * @author Mario
 */
public class Rectangulo extends FormaRelleno implements PatronDibujoUnPaso{

    private Rectangle2D rectangulo;
    private Point2D d1;
       
    /**
     * Constructor por defecto.
     * @param p1 Esquina superior izquierda.
     * @param width Ancho.
     * @param height Alto.
     */ 
    public Rectangulo(Point2D p1, double width, double height){
        super();
        rectangulo = new Rectangle2D.Double(p1.getX(), p1.getY(), width, height);
        d1 = p1;
    }
    
    /**
     * Constructor por defecto, indicando un estilo.
     * 
     * @param p1 Esquina superior izquierda.
     * @param width Ancho.
     * @param height Alto.
     * @param estilo Estilo del rectángulo.
     */
    public Rectangulo(Point2D p1, double width, double height,EstiloRelleno estilo){
        super(estilo);
        rectangulo = new Rectangle2D.Double(p1.getX(), p1.getY(), width, height);
        d1 = p1;
    }
    
    /**
     * Constructor de copia.
     * 
     * @param rectangulo Nuevo rectángulo.
     */
    public Rectangulo(Rectangulo rectangulo){
        super(((EstiloRelleno)rectangulo.estilo));
        this.d1 = rectangulo.d1;
        this.rectangulo = new Rectangle2D.Double(rectangulo.getP1().getX(), rectangulo.getP1().getX(), rectangulo.getWidth(), rectangulo.getHeight());
    }
    
    /**
     * Obtiene la esquina superior izquierda del rectángulo.
     * @return Punto que representa la esquina superior izquierda del rectángulo.
     */
    public Point2D getP1() {
        return getPuntoPaso0();
    }

    /**
     * Modifica la esquina superior izquierda del rectángulo.
     * 
     * @param p1 Punto de la nueva esquina superior izquierda.
     */
    public void setP1(Point2D p1) {
        setPuntoPaso0(p1);
    }
    
    @Override
    public Point2D getPuntoPaso0() {
        return new Point2D.Double(rectangulo.getX(), rectangulo.getY());
    }
    
    @Override
    public void setPuntoPaso0(Point2D d1) {
        rectangulo.setFrameFromDiagonal(d1, getPuntoPaso1());
        this.d1 = d1;
    }
    
    @Override
    public Point2D getPuntoPaso1() {
        return new Point2D.Double(rectangulo.getX()+rectangulo.getWidth(), rectangulo.getY()+rectangulo.getHeight());
    }
    
    @Override
    public void setPuntoPaso1(Point2D d2) {
        rectangulo.setFrameFromDiagonal(d1, d2);
    }
        
    @Override
    public Shape getShape() {
        return rectangulo;
    }    
    
    @Override
    public void setLocation(Point2D p) {
        rectangulo.setFrameFromDiagonal(p, new Point2D.Double(p.getX()+rectangulo.getBounds().getWidth(),p.getY()+rectangulo.getBounds().getHeight()));
        d1 = p;
    }
    
}
