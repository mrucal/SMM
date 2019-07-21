package SM.MRC.Grafico.Forma;

import SM.MRC.Grafico.Estilo.EstiloRelleno;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Forma Elipse (rellenable).
 * @author Mario
 */
public class Elipse extends FormaRelleno implements PatronDibujoUnPaso{
    
    Ellipse2D elipse;
    
    private Point2D d1;
    
    /**
     * Constructor por defecto.
     * 
     * @param p1 Esquina superior izquierda.
     * @param radio_width Radio del ancho.
     * @param radio_height Radio del alto.
     */ 
    public Elipse(Point2D p1, int radio_width, int radio_height){
        super();
        //rectangulo = new Rectangle(p1, new Dimension(Math.abs((int) (p1.getX()-p2.getX())),Math.abs((int)(p1.getY()-p2.getY()))));
        double width = 2*radio_width, height = 2*radio_height;
        elipse = new Ellipse2D.Double(p1.getX(), p1.getY(), width, height);
        d1 = p1;
    }
    
    /**
     * Constructor por defecto, indicando un estilo.
     * 
     * @param p1 Esquina superior izquierda.
     * @param radio_width Radio del ancho.
     * @param radio_height Radio del alto.
     * @param estilo Estilo de la elipse.
     */ 
    public Elipse(Point2D p1, int radio_width, int radio_height,EstiloRelleno estilo){
        super(estilo);
        //rectangulo = new Rectangle(p1, new Dimension(Math.abs((int) (p1.getX()-p2.getX())),Math.abs((int)(p1.getY()-p2.getY()))));
        double width = 2*radio_width, height = 2*radio_height;
        elipse = new Ellipse2D.Double(p1.getX(), p1.getY(), width, height);
        d1 = p1;
    }
    
    /**
     * Constructor de copia.
     * 
     * @param elipse Nueva elipse.
     */
    public Elipse(Elipse elipse){
        super(((EstiloRelleno)elipse.estilo));
        this.d1 = elipse.d1;
        this.elipse = new Ellipse2D.Double(d1.getX(), d1.getY(), elipse.getWidth(), elipse.getHeight());
    }    
     
    /**
     * Obtiene la esquina superior izquierda de la elipse.
     * 
     * @return Esquina superior izquierda de la elipse.
     */
    public Point2D getP1() {
        return getPuntoPaso0();
    }

    /**
     * Modifica la esquina superior izquierda de la elipse.
     * 
     * @param p1 Punto de la nueva esquina superior izquierda.
     */
    public void setP1(Point2D p1) {
        setPuntoPaso0(p1);
    }
    
    @Override
    public Point2D getPuntoPaso0() {
        return new Point2D.Double(elipse.getX(), elipse.getY());
    }
    
    @Override
    public void setPuntoPaso0(Point2D d1) {
        elipse.setFrameFromDiagonal(d1, getPuntoPaso1());
        this.d1 = d1;
    }
    
    @Override
    public Point2D getPuntoPaso1() {
        return new Point2D.Double(elipse.getX()+elipse.getWidth(), elipse.getY()+elipse.getHeight());
    }
    
    @Override
    public void setPuntoPaso1(Point2D d2) {
        elipse.setFrameFromDiagonal(d1, d2);
    }
    
    @Override
    public Shape getShape() {
        return elipse;
    }
    
    @Override
    public void setLocation(Point2D p) {
        elipse.setFrameFromDiagonal(p, new Point2D.Double(p.getX()+elipse.getBounds().getWidth(),p.getY()+elipse.getBounds().getHeight()));
        this.d1 = p;
    }
}
