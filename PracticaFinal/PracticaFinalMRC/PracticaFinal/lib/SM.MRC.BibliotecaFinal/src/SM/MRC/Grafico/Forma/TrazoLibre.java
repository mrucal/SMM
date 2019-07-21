package SM.MRC.Grafico.Forma;

import SM.MRC.Grafico.Estilo.EstiloRelleno;
import SM.MRC.Grafico.Estilo.Relleno;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Forma Trazo Libre (rellenable).
 * 
 * Se compone de distintos puntos que se van añadiendo, unidos por líneas.
 * Puede ser cerrado (uniendo el punto inicial y final), en caso de que tenga algun tipo de relleno.
 * 
 * @author Mario
 */
public class TrazoLibre extends FormaRelleno{

    private GeneralPath trazo_libre;
    private ArrayList<Point2D> puntos;
    
    /**
     * Constructor por defecto.
     * 
     * @param p Punto de inicio del trazo.
     */
    public TrazoLibre(Point2D p){
        trazo_libre = new GeneralPath();
        trazo_libre.moveTo(p.getX(), p.getY());
        puntos = new ArrayList<>();
        puntos.add(p);
    }
    
    /**
     * Constructor por defecto indicando un estilo.
     * 
     * @param p Punto de inicio del trazo.
     * @param estilo Estilo del trazo.
     */
    public TrazoLibre(Point2D p, EstiloRelleno estilo){
        super(estilo);
        trazo_libre = new GeneralPath();
        trazo_libre.moveTo(p.getX(), p.getY());
        puntos = new ArrayList<>();
        puntos.add(p);
    }
    
    /**
     * Constructor de copia.
     * 
     * @param trazolibre Nuevo Trazo Libre.
     */
    public TrazoLibre(TrazoLibre trazolibre){
        super(((EstiloRelleno)trazolibre.estilo));
        trazo_libre = new GeneralPath();
        trazo_libre.moveTo(trazolibre.getOrigen().getX(), trazolibre.getOrigen().getY());
        puntos = new ArrayList();
        puntos.add(trazolibre.getOrigen());
        for(int i = 1; i < trazolibre.puntos.size(); i++)
            addPunto(trazolibre.puntos.get(i));
    }
    
    /**
     * Añade un nuevo punto al trazo.
     * 
     * @param p Punto que se va a añadir.
     */
    public void addPunto(Point2D p){
        trazo_libre.lineTo(p.getX(), p.getY());
        puntos.add(p);
    }
    
    /**
     * Une el punto inicial y final del trazo, en caso de que el trazo tenga algún tipo de relleno.
     */
    public void cerrarTrazo(){
        if(((EstiloRelleno)this.estilo).getRelleno().getTipo() != Relleno.SIN_RELLENO)
            trazo_libre.closePath();
    }
    
    /**
     * Obtiene el punto de inicio del trazo.
     * 
     * @return Punto de inicio (origen) del trazo.
     */
    public Point2D getOrigen(){
        return puntos.get(0);
    }
    
    @Override
    public void setTipoRelleno(int tipo){
        super.setTipoRelleno(tipo);
        cerrarTrazo();
    }
    
    @Override
    Point2D getP1Degradado(){
        return new Point2D.Double(trazo_libre.getBounds2D().getX(),trazo_libre.getBounds2D().getX());
    }
    
    @Override
    Point2D getP2Degradado(){
        return new Point2D.Double(getP1Degradado().getX()+trazo_libre.getBounds2D().getWidth(),
                                     getP1Degradado().getY()+trazo_libre.getBounds2D().getHeight());
    }
       
    @Override
    public void setWidth(double width){
        double factor = width/getWidth();
        Point2D origen = new Point2D.Double(trazo_libre.getBounds2D().getX(), trazo_libre.getBounds2D().getY());
        AffineTransform at = new AffineTransform();
        at.translate(origen.getX()*(1-factor), 0);
        at.scale(width / getWidth(), 1);
        trazo_libre.transform(at);
    }
    
    @Override
    public void setHeight(double height){
        double factor = height/getHeight();
        Point2D origen = new Point2D.Double(trazo_libre.getBounds2D().getX(), trazo_libre.getBounds2D().getY());
        AffineTransform at = new AffineTransform();
        at.translate(0,origen.getY()*(1-factor));
        at.scale(1,factor);
        trazo_libre.transform(at);
    }
    
    @Override
    Shape getShape() {
        return trazo_libre;
    }

    @Override
    public Point2D getLocation(){
        return getOrigen();
    }

    @Override
    public void setLocation(Point2D p) {
        AffineTransform at = new AffineTransform();
        at.translate(-getOrigen().getX(), -getOrigen().getY());
        at.translate(p.getX(), p.getY());
        trazo_libre.transform(at);
    }
    
}
