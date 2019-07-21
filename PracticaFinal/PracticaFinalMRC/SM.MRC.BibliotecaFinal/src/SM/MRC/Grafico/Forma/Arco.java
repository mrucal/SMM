package SM.MRC.Grafico.Forma;

import SM.MRC.Grafico.Estilo.EstiloRelleno;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;

/**
 * Forma Rectángulo (rellenable).
 * 
 * @author Mario
 */
public class Arco extends FormaRelleno implements PatronDibujoDosPasos{

    private Arc2D arco;
    private Point2D d1;
       
    /**
     * Constructor por defecto.
     * @param p1 Esquina superior izquierda.
     * @param width Ancho.
     * @param height Alto.
     */ 
    public Arco(Point2D p1, double width, double height){
        super();
        arco = new Arc2D.Double(p1.getX(), p1.getY(), width, height,0,0,Arc2D.PIE);
        d1 = p1;
    }
    
    /**
     * Constructor por defecto, indicando un estilo.
     * 
     * @param p1 Esquina superior izquierda.
     * @param width Ancho.
     * @param height Alto.
     * @param estilo Estilo del arco.
     */
    public Arco(Point2D p1, double width, double height,EstiloRelleno estilo){
        super(estilo);
        arco = new Arc2D.Double(p1.getX(), p1.getY(), width, height,0,0,Arc2D.PIE);
        d1 = p1;
    }
    
    /**
     * Constructor de copia.
     * 
     * @param arco Nuevo arco.
     */
    public Arco(Arco arco){
        super(((EstiloRelleno)arco.estilo));
        this.d1 = arco.d1;
        this.arco = new Arc2D.Double(arco.getP1().getX(), arco.getP1().getX(), arco.getWidth(), arco.getHeight(),
                            arco.arco.getAngleStart(),arco.arco.getAngleExtent(),Arc2D.PIE);
    }
    
    /**
     * Obtiene la esquina superior izquierda del arco.
     * @return Punto que representa la esquina superior izquierda del arco.
     */
    public Point2D getP1() {
        return getPuntoPaso0();
    }

    /**
     * Modifica la esquina superior izquierda del arco.
     * 
     * @param p1 Punto de la nueva esquina superior izquierda.
     */
    public void setP1(Point2D p1) {
        setPuntoPaso0(p1);
    }
    
    @Override
    public Point2D getPuntoPaso0() {
        return new Point2D.Double(arco.getX(), arco.getY());
    }
    
    @Override
    public void setPuntoPaso0(Point2D d1) {
        arco.setFrameFromDiagonal(d1, getPuntoPaso1());
        this.d1 = d1;
    }
    
    @Override
    public Point2D getPuntoPaso1() {
        return new Point2D.Double(arco.getX()+arco.getWidth(), arco.getY()+arco.getHeight());
    }
    
    @Override
    public void setPuntoPaso1(Point2D d2) {
        arco.setFrameFromDiagonal(d1, d2);
    }
    
    @Override
    public Point2D getPuntoPaso2() {
        return new Point2D.Double(arco.getAngleExtent(),arco.getAngleStart());
    }

    @Override
    public void setPuntoPaso2(Point2D d3) {
        Point2D centro = new Point2D.Double(arco.getCenterX(),arco.getCenterY());
        double angulo = (d3.distance(centro)*3 < 360) ? d3.distance(centro)*3: 360;
        this.arco.setAngleExtent(angulo);
    }
    
    @Override
    public Shape getShape() {
        return arco;
    }
        
    @Override
    public void setLocation(Point2D p) {
        arco.setFrameFromDiagonal(p, new Point2D.Double(p.getX()+arco.getBounds().getWidth(),p.getY()+arco.getBounds().getHeight()));
        d1 = p;
    }
    
}
