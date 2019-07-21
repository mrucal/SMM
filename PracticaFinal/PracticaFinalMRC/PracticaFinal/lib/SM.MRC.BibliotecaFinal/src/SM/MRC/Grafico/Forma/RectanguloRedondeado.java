package SM.MRC.Grafico.Forma;

import SM.MRC.Grafico.Estilo.EstiloRelleno;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Forma Rectángulo Redondeado (rellenable).
 * 
 * @author Mario
 */
public class RectanguloRedondeado extends FormaRelleno implements PatronDibujoDosPasos{

    private RoundRectangle2D rectangulo_redondeado;
    private Point2D d1;
       
    /**
     * Constructor por defecto.
     * @param p1 Esquina superior izquierda.
     * @param width Ancho.
     * @param height Alto.
     */ 
    public RectanguloRedondeado(Point2D p1, double width, double height){
        super();
        rectangulo_redondeado = new RoundRectangle2D.Double(p1.getX(), p1.getY(), width, height,0,0);
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
    public RectanguloRedondeado(Point2D p1, double width, double height,EstiloRelleno estilo){
        super(estilo);
        rectangulo_redondeado = new RoundRectangle2D.Double(p1.getX(), p1.getY(), width, height,0,0);
        d1 = p1;
    }
    
    /**
     * Constructor de copia.
     * 
     * @param rectangulo_redondeado Nuevo Rectangulo Redondeado.
     */
    public RectanguloRedondeado(RectanguloRedondeado rectangulo_redondeado){
        super(((EstiloRelleno)rectangulo_redondeado.estilo));
        this.d1 = rectangulo_redondeado.d1;
        this.rectangulo_redondeado = new RoundRectangle2D.Double(rectangulo_redondeado.getP1().getX(), rectangulo_redondeado.getP1().getX(),
                rectangulo_redondeado.getWidth(), rectangulo_redondeado.getHeight(),
                rectangulo_redondeado.rectangulo_redondeado.getArcWidth(),rectangulo_redondeado.rectangulo_redondeado.getArcHeight());
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
        return new Point2D.Double(rectangulo_redondeado.getX(), rectangulo_redondeado.getY());
    }
    
    @Override
    public void setPuntoPaso0(Point2D d1) {
        rectangulo_redondeado.setFrameFromDiagonal(d1, getPuntoPaso1());
        this.d1 = d1;
    }
    
    @Override
    public Point2D getPuntoPaso1() {
        return new Point2D.Double(rectangulo_redondeado.getX()+rectangulo_redondeado.getWidth(), rectangulo_redondeado.getY()+rectangulo_redondeado.getHeight());
    }
    
    @Override
    public void setPuntoPaso1(Point2D d2) {
        rectangulo_redondeado.setFrameFromDiagonal(d1, d2);
    }
    
    @Override
    public Point2D getPuntoPaso2() {
        return new Point2D.Double(rectangulo_redondeado.getArcHeight(),rectangulo_redondeado.getArcHeight());
    }

    @Override
    public void setPuntoPaso2(Point2D d3) {
        double arco = d3.distance(getPuntoPaso1());
        rectangulo_redondeado.setRoundRect(getPuntoPaso0().getX(), getPuntoPaso0().getY(), getWidth(),getHeight(), arco, arco);
    }
    
    @Override
    public Shape getShape() {
        return rectangulo_redondeado;
    }
    
    @Override
    public void setLocation(Point2D p) {
        rectangulo_redondeado.setFrameFromDiagonal(p, new Point2D.Double(p.getX()+rectangulo_redondeado.getBounds().getWidth(),p.getY()+rectangulo_redondeado.getBounds().getHeight()));
        d1 = p;
    }
    
}
