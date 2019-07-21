package SM.MRC.Grafico.Forma;

import SM.MRC.Grafico.Estilo.EstiloRelleno;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

/**
 * Forma Cuva Cuadrada, con un punto de control (rellenable).
 * @author Mario
 */
public class CurvaCuadrada extends FormaRelleno implements PatronDibujoDosPasos{

    private QuadCurve2D curva;
    
    /**
     * Constructor por defecto con dos puntos.
     * 
     * @param p1 Punto de inicio de la curva.
     * @param p2 Punto de fin de la curva.
     */
    public CurvaCuadrada(Point2D p1, Point2D p2){
        super();
        curva = new QuadCurve2D.Double(p1.getX(),p1.getY(),p2.getX(),p1.getY(),p2.getX(),p2.getY());
    }
    
    /**
     * Constructor por defecto con dos puntos y un estilo.
     * 
     * @param p1 Punto de inicio de la curva.
     * @param p2 Punto de fin de la curva.
     * @param estilo Estilo de la curva.
     */
    public CurvaCuadrada(Point2D p1, Point2D p2, EstiloRelleno estilo){
        super(estilo);
        curva = new QuadCurve2D.Double(p1.getX(),p1.getY(),p2.getX(),p1.getY(),p2.getX(),p2.getY());
    }
    
    /**
     * Constructor por defecto con tres puntos.
     * 
     * @param p1 Punto de inicio de la curva.
     * @param p2 Punto de fin de la curva.
     * @param pc Punto de control de la curva.
     */
    public CurvaCuadrada(Point2D p1, Point2D p2, Point2D pc){
        super();
        curva = new QuadCurve2D.Double(p1.getX(),pc.getX(),pc.getY(),p1.getY(),p2.getX(),p2.getY());
    }
    
    /**
     * Constructor por defecto con tres puntos y un estilo.
     * 
     * @param p1 Punto de inicio de la curva.
     * @param p2 Punto de fin de la curva.
     * @param pc Punto de control de la curva.
     * @param estilo Estilo de la curva.
     */
    public CurvaCuadrada(Point2D p1, Point2D p2, Point2D pc, EstiloRelleno estilo){
        super(estilo);
        curva = new QuadCurve2D.Double(p1.getX(),pc.getX(),pc.getY(),p1.getY(),p2.getX(),p2.getY());
    }
    
    /**
     * Constructor de copia.
     * 
     * @param curva Nueva curva.
     */
    public CurvaCuadrada(CurvaCuadrada curva){
        super(((EstiloRelleno)curva.estilo));
        this.curva = new QuadCurve2D.Double(curva.getP1().getX(), curva.getP1().getY(), curva.getC().getX(), curva.getC().getY(),
                            curva.getP2().getX(),curva.getP2().getY());
    }
    
    /**
     * Obtiene el punto de control de la curva.
     * 
     * @return Punto de control de la curva.
     */
    public Point2D getC(){
        return curva.getCtrlPt();
    }
    
    /**
     * Modifica el punto de control de la curva.
     * 
     * @param c Punto de control de la curva.
     */
    public void setC(Point2D c){
        curva.setCurve(curva.getP1(), c, curva.getP2());
    }
    
    /**
     * Obtiene el punto de inicio de la curva.
     * 
     * @return Punto de inicio de la curva.
     */
    public Point2D getP1() {
        return curva.getP1();
    }

    /**
     * Modifica el punto de inicio de la curva.
     * 
     * @param p1 Punto de inicio de la curva.
     */
    public void setP1(Point2D p1) {
        curva.setCurve(p1, curva.getCtrlPt(), curva.getP2());
    }
    
    /**
     * Obtiene el punto de fin de la curva.
     * 
     * @return Punto de fin de la curva.
     */
    public Point2D getP2() {
        return curva.getP2();
    }

    /**
     * Modifica el punto de fin de la curva.
     * 
     * @param p2 Punto de fin de la curva.
     */
    public void setP2(Point2D p2) {
        curva.setCurve(curva.getP1(), curva.getCtrlPt() , p2);
    }
    
    @Override
    public Point2D getPuntoPaso0() {
        return getP1();
    }

    @Override
    public void setPuntoPaso0(Point2D d1) {
        setP1(d1);
    }

    @Override
    public Point2D getPuntoPaso1() {
        return getC();
    }

    @Override
    public void setPuntoPaso1(Point2D d2) {
        setC(d2);
        setP2(d2);  
    }

    @Override
    public Point2D getPuntoPaso2() {
        return getP2();
    }

    @Override
    public void setPuntoPaso2(Point2D d3) {     
        setP2(d3);
    }
    
    
    @Override
    Point2D getP2Degradado(){
        return getP2();
    }
    
    @Override
    public void setWidth(double width){
        Point2D esquina = new Point2D.Double(curva.getBounds2D().getX(), curva.getBounds2D().getY());
        
        double factor = width/getWidth();
        
        double p1factor = (getP1().getX() - esquina.getX());
        double cfactor = (getC().getX() - esquina.getX());
        double p2factor = (getP2().getX() - esquina.getX());
        
        setP1(new Point2D.Double(esquina.getX()+(p1factor*factor),getP1().getY()));
        setP2(new Point2D.Double(esquina.getX()+(p2factor*factor),getP2().getY()));
        setC(new Point2D.Double(esquina.getX()+(cfactor*factor),getC().getY()));
    }
    
    @Override
    public void setHeight(double height){        
        Point2D esquina = new Point2D.Double(curva.getBounds2D().getX(), curva.getBounds2D().getY());
        
        double factor = height/getHeight();
        
        double p1factor = (getP1().getY() - esquina.getY());
        double cfactor = (getC().getY() - esquina.getY());
        double p2factor = (getP2().getY() - esquina.getY());
        
        setP1(new Point2D.Double(getP1().getX(),esquina.getY()+(p1factor*factor)));
        setP2(new Point2D.Double(getP2().getX(),esquina.getY()+(p2factor*factor)));
        setC(new Point2D.Double(getC().getX(),esquina.getY()+(cfactor*factor)));
    }
    
    @Override
    Shape getShape() {
        return curva;
    }

    @Override
    public void setLocation(Point2D p) {
        double wc = curva.getCtrlX() - curva.getX1(), hc = curva.getCtrlY() - curva.getY1();
        double wp2 = curva.getX2() - curva.getX1(), hp2 = curva.getY2() - curva.getY1();
        curva.setCurve(p.getX(), p.getY(), p.getX() + wc , p.getY() + hc, p.getX() + wp2, p.getY() + hp2);
    }
    
}
