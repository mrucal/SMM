package SM.MRC.Grafico.Forma;

import SM.MRC.Grafico.Estilo.EstiloRelleno;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Forma M (rellenable).
 * 
 * Forma propia, construida mediante areas, que dibuja una letra M.
 * 
 * @author Mario
 */
public class M extends FormaRelleno implements PatronDibujoUnPaso{
    
    private Area area;
    private ArrayList<FormaRelleno> forma_relleno;
    
    private Point2D d1;
    
    /**
     * Constructor por defecto indicando un estilo.
     * 
     * @param d1 Punto de la esquina superior izquierda.
     * @param estilo Estilo de la forma.
     */
    public M(Point2D d1, EstiloRelleno estilo){
        super(estilo);
        this.d1 = d1;
        area = new Area();
        forma_relleno = new ArrayList();
    }
    
    /**
     * Constructor de copia.
     * 
     * @param m Nueva M.
     */
    public M(M m){
        super(((EstiloRelleno)m.estilo));
        this.d1 = m.d1;
        area = new Area();
        for(Forma f: m.forma_relleno)
            area.add(new Area(f.getShape()));
        forma_relleno = new ArrayList(m.forma_relleno);
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
        forma_relleno = new ArrayList();
        
        double w = d2.getX() - d1.getX();
        double h = d2.getY() - d1.getY();
        
        Point2D p = d1;
        if(w < 0 && h < 0)
            p = d2;
        if(w > 0 && h < 0)
            p = new Point2D.Double((int)(d2.getX()-w),(int)d2.getY());
        if(w < 0 && h > 0)
            p = new Point2D.Double((int)(d2.getX()), (int)(d2.getY()-h));
        w = Math.abs(w);
        h = Math.abs(h);
        
        forma_relleno.add(new Rectangulo(p, (w*0.25), (h)));
        forma_relleno.add(new Mpico(p, w, h*0.60, new EstiloRelleno(((EstiloRelleno)this.estilo))));
        forma_relleno.add(new Rectangulo(new Point2D.Double(p.getX()+(w*0.75),p.getY()), w*0.25, h));
       
        for(FormaRelleno s: forma_relleno)
            area.add(new Area(s.getShape()));
        
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
    
    private class Mpico extends FormaRelleno{
        
        TrazoLibre trazolibre;
        
        Mpico(Point2D desplazamiento, double width, double height, EstiloRelleno estilo){
            super(estilo);
            trazolibre = new TrazoLibre(new Point2D.Double(0,0), estilo);
            trazolibre.addPunto(new Point2D.Double(width*0.25,0));
            trazolibre.addPunto(new Point2D.Double(width*0.5,height*0.5));
            trazolibre.addPunto(new Point2D.Double(width*0.75,0));
            trazolibre.addPunto(new Point2D.Double(width,0));
            trazolibre.addPunto(new Point2D.Double(width*0.75,height*0.5));
            trazolibre.addPunto(new Point2D.Double(width*0.5,height));
            trazolibre.addPunto(new Point2D.Double(width*0.25,height*0.5));
            trazolibre.addPunto(new Point2D.Double(0,0));
            
            trazolibre.setLocation(desplazamiento);
        }
        
        @Override
        Point2D getP1Degradado(){
            return getPuntoPaso0();
        }

        @Override
        Point2D getP2Degradado(){
            return getPuntoPaso1();
        }

        @Override
        Shape getShape() {
            return trazolibre.getShape();
        }

        @Override
        public Point2D getLocation(){
            return trazolibre.getLocation();
        }

        @Override
        public void setLocation(Point2D p) {
            trazolibre.setLocation(p);
        }

    }
}