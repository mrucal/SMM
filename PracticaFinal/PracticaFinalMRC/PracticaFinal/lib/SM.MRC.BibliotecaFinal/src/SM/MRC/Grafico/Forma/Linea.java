package SM.MRC.Grafico.Forma;

import SM.MRC.Grafico.Estilo.Estilo;
import java.awt.geom.Point2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * Forma Línea (no rellenable).
 * 
 * @author Mario
 */
public class Linea extends Forma implements PatronDibujoUnPaso{
    
    private Line2D linea;
    
    /**
     * Constructor por defecto.
     * 
     * @param p1 Punto de comienzo de la línea.
     * @param p2 Punto de fin de la línea.
     */
    public Linea(Point2D p1, Point2D p2){
        super();
        linea = new Line2D.Double(p1,p2);
    }
    
    /**
     * Constructor por defecto, indicando el estilo.
     * 
     * @param p1 Punto de comienzo de la línea.
     * @param p2 Punto de fin de la línea.
     * @param estilo Estilo de la línea.
     */
    public Linea(Point2D p1, Point2D p2, Estilo estilo){
        super(estilo);
        linea = new Line2D.Double(p1,p2);
    }
    
    /**
     * Constructor de copia.
     * 
     * @param linea Nueva línea.
     */
    public Linea(Linea linea){
        super(linea.estilo);
        this.linea = new Line2D.Double(linea.getP1(),linea.getP2());
    }
    
    /**
     * Obtiene el punto de inicio de la línea. Coincide con el primer punto de la diagonal.
     * 
     * @return Punto de inicio de la línea. Coincide con el primer punto de la diagonal.
     */
    public Point2D getP1() {
        return getPuntoPaso0();
    }

    /**
     * Modifica el punto de inicio de la línea.
     * 
     * @param p1 Punto de inicio de la línea.
     */
    public void setP1(Point2D p1) {
        setPuntoPaso0(p1);
    }
    
    /**
     * Obtiene el punto de fin de la línea. Coincide con el primer punto de la diagonal.
     * @return Obtiene el punto de fin de la línea. Coincide con el segundo punto de la diagonal de la línea.
     */
    public Point2D getP2() {
        return getPuntoPaso1();
    }

    /**
     * Modifica el punto de fin de la línea.
     * 
     * @param p2 Punto de fin de la línea.
     */
    public void setP2(Point2D p2) {
        setPuntoPaso1(p2);
    }

    @Override
    public Point2D getPuntoPaso0() {
        return linea.getP1();
    }

    @Override
    public void setPuntoPaso0(Point2D p1) {
        linea.setLine(p1,linea.getP2());
    }

    @Override
    public Point2D getPuntoPaso1() {
        return linea.getP2();
    }
    
    @Override
    public void setPuntoPaso1(Point2D p2) {
        linea.setLine(linea.getP1(), p2);
    }
    
    @Override
    public void setWidth(double width){
        double w = getP2().getX() - getP1().getX();
        double signow = w / Math.abs(w);
        Point2D p1 = new Point2D.Double(getPuntoPaso0().getX()+(signow*width), getPuntoPaso1().getY());
        setPuntoPaso1(p1);
    }
    
    @Override
    public void setHeight(double height){
        double h = getP2().getY() - getP1().getY();
        double signoh = h / Math.abs(h);
        Point2D p1 = new Point2D.Double(getPuntoPaso1().getX(), getPuntoPaso0().getY()+(signoh*height));
        setPuntoPaso1(p1);
    }

    @Override
    public Shape getShape() {
        return linea;
    }
    
    @Override
    public void setLocation(Point2D p) {
        linea.setLine(p, new Point2D.Double(p.getX()+linea.getX2()-linea.getX1(),p.getY()+linea.getY2()-linea.getY1()));
    }

    @Override
    public boolean contains(Point2D p) {
        if(linea.getP1().equals(linea.getP2()))
            return linea.getP1().distance(p) <= 2;
        else
            return linea.getBounds().contains(p);
    }
}
