package SM.MRC.Grafico.Forma;

import SM.MRC.Grafico.Estilo.EstiloRelleno;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

/**
 * Forma MSello (rellenable).
 * 
 * Forma propia, construida mediante areas, que dibuja una elisple al que se le resta una forma M.
 * 
 * @author Mario
 */
public class MSello extends FormaRelleno implements PatronDibujoUnPaso{
    
    private Area area;
    private Elipse elipse;
    private M m;
    
    private Point2D d1;
    
    /**
     * Constructor por defecto indicando un estilo.
     * 
     * @param d1 Punto de la esquina superior izquierda.
     * @param estilo Estilo de la forma.
     */
    public MSello(Point2D d1, EstiloRelleno estilo){
        super(estilo);
        this.d1 = d1;
        area = new Area();
        elipse = new Elipse(d1, (int)d1.getX(), (int)d1.getY(),estilo);
        m = new M(d1,estilo);
    }
    
    /**
     * Constructor de copia.
     * 
     * @param msello Nuevo MSello
     */
    public MSello(MSello msello){
        super(((EstiloRelleno)msello.estilo));
        this.d1 = msello.d1;
        elipse = new Elipse(msello.elipse);
        m = new M(msello.m);
        double cex = elipse.getP1().getX() + (elipse.getWidth()/2);
        double cey = elipse.getP1().getY() + (elipse.getHeight()/2);
        
        double wm = m.getWidth() / 2;
        double hm = m.getHeight() / 2;
        
        m.setLocation(new Point2D.Double(
                cex - wm,
                cey - hm));
        
        area = new Area();
        area.add(new Area(msello.elipse.getShape()));
        area.subtract(new Area(msello.m.getShape()));
    }
    
    @Override
    public Point2D getPuntoPaso0() {return new Point2D.Double(area.getBounds2D().getX(),area.getBounds2D().getY());}

    @Override
    public void setPuntoPaso0(Point2D d1) {this.d1 = d1;}

    @Override
    public Point2D getPuntoPaso1() {
        return new Point2D.Double(area.getBounds2D().getX()+area.getBounds2D().getWidth()
                ,area.getBounds2D().getY()+area.getBounds2D().getHeight());
    }
    
    @Override
    public void setPuntoPaso1(Point2D d2){
        
        area = new Area();
        
        elipse.setPuntoPaso1(d2);
        m.setPuntoPaso1(new Point2D.Double(elipse.getP1().getX()+(elipse.getWidth()*0.6),elipse.getP1().getY()+(elipse.getHeight()*0.6)));
        
        double cex = elipse.getP1().getX() + (elipse.getWidth()/2);
        double cey = elipse.getP1().getY() + (elipse.getHeight()/2);
        
        double wm = m.getWidth() / 2;
        double hm = m.getHeight() / 2;
        
        m.setLocation(new Point2D.Double(
                cex - wm,
                cey - hm));
        
        area.add(new Area(elipse.getShape()));
        area.subtract(new Area(m.getShape()));
        
    }

    @Override
    Shape getShape() {
        return area;
    }

    @Override
    public void setLocation(Point2D p) {
        AffineTransform at = new AffineTransform();
        at.translate(-getLocation().getX(), -getLocation().getY());
        at.translate(p.getX(), p.getY());
        area.transform(at);
    }
    
}