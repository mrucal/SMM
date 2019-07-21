package SM.MRC.Grafico.Estilo;

import SM.MRC.Util.TipoRelleno;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

/**
 * Atributo Relleno.
 * 
 * Tipo de atributo formado por color de frente y fondo, y tipo de relleno.
 * 
 * @author Mario
 */
public class Relleno {
    
    public static final int SIN_RELLENO = 0;
    public static final int LISO = 1;
    public static final int DEGRADADO = 2;
    
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public static final int DIAGONAL_IZQUIERDA = 2;
    public static final int DIAGONAL_DERECHA = 3;
    public static final int RADIAL = 4;
    
    private Color colorFondo;
    private Color colorFrente;
    private int tipo;
    
    private int tipo_degradado;
    
    /**
     * Constructor por defecto.
     */
    public Relleno(){
        colorFondo = Color.WHITE;
        colorFrente = Color.BLACK;
        tipo = SIN_RELLENO;
        tipo_degradado = HORIZONTAL;
    }
    
    /**
     * Constructor con un tipo.
     * 
     * @param tipo Tipo de relleno. Puede ser {@value #SIN_RELLENO}, {@value #LISO} o {@value #DEGRADADO}.
     */
    public Relleno(int tipo){
        colorFondo = Color.WHITE;
        colorFrente = Color.BLACK;
        this.tipo = tipo;
        tipo_degradado = HORIZONTAL;
    }
    
    /**
     * Constructor con un tipo y un degradado.
     * 
     * @param tipo Tipo de relleno. Puede ser {@value #SIN_RELLENO}, {@value #LISO} o {@value #DEGRADADO}.
     * @param tipo_degradado Tipo de degradado. Puede ser {@value #HORIZONTAL} o {@value #VERTICAL}.
     */
    public Relleno(int tipo, int tipo_degradado){
        colorFondo = Color.BLACK;
        colorFrente = Color.BLACK;
        this.tipo = tipo;
        this.tipo_degradado = tipo_degradado;
    }
    
    /**
     * Constructor de copia.
     * 
     * @param relleno Nuevo relleno.
     */
    public Relleno(Relleno relleno){
        colorFondo = relleno.getColorFondo();
        colorFrente = relleno.getColorFrente();
        this.tipo = relleno.getTipo();
        tipo_degradado = relleno.getTipoDegradado();
    }
    
    /**
     * Obtiene el color de fondo del relleno.
     * 
     * @return Color de fondo del relleno.
     */
    public Color getColorFondo(){
        return colorFondo;
    }
    
    /**
     * Modifica el color de fondo del relleno.
     * 
     * @param colorFondo Color de fondo del relleno.
     */
    public void setColorFondo(Color colorFondo){
        this.colorFondo = colorFondo;
    }
    
    /**
     * Obtiene el color de frente del relleno.
     * 
     * @return Color de frente del relleno.
     */
    public Color getColorFrente() {
        return colorFrente;
    }
    
    /**
     * Modifica el color de frente del relleno.
     * 
     * @param colorFrente Color de frente del relleno.
     */
    public void setColorFrente(Color colorFrente) {
        this.colorFrente = colorFrente;
    }

    /**
     * Obtiene el tipo de relleno.
     * 
     * @return Tipo de relleno. Puede ser {@value #SIN_RELLENO}, {@value #LISO} o {@value #DEGRADADO}.
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Modifica el tipo de relleno.
     * 
     * @param tipo Tipo de relleno. Puede ser {@value #SIN_RELLENO}, {@value #LISO} o {@value #DEGRADADO}.
     */
    public void setTipo(int tipo) {
        assert tipo != Relleno.SIN_RELLENO || tipo != Relleno.LISO || tipo != Relleno.DEGRADADO;
        this.tipo = tipo;
    }

    /**
     * Obtiene el tipo de degradado.
     * 
     * @return Tipo de degradado. Puede ser {@value #HORIZONTAL} o {@value #VERTICAL}.
     */
    public int getTipoDegradado() {
        return tipo_degradado;
    }

    /**
     * Modifica el tipo de degradado.
     * 
     * @param tipo_degradado Tipo de degradado. Puede ser {@value #HORIZONTAL} o {@value #VERTICAL}.
     */
    public void setTipoDegradado(int tipo_degradado) {
        assert tipo_degradado != Relleno.HORIZONTAL || tipo_degradado != Relleno.VERTICAL
                || tipo_degradado != Relleno.DIAGONAL_IZQUIERDA || tipo_degradado != Relleno.DIAGONAL_DERECHA 
                || tipo_degradado != Relleno.RADIAL;
        this.tipo_degradado = tipo_degradado;
    }
    
    /**
     * Obtiene un enumerado que indica el tipo de relleno (incluyendo tipo de degradado).
     * @return Enum Tipo de Relleno.
     */
    public TipoRelleno getTipoRelleno(){
        switch(tipo){
            case SIN_RELLENO:
                return TipoRelleno.SIN_RELLENO;
            case LISO:
                return TipoRelleno.LISO;
            case DEGRADADO:
                switch(tipo_degradado){
                    case HORIZONTAL:
                        return TipoRelleno.HORIZONTAL;
                    case VERTICAL:
                        return TipoRelleno.VERTICAL;
                    case DIAGONAL_IZQUIERDA:
                        return TipoRelleno.DIAGONAL_IZQUIERDA;
                    case DIAGONAL_DERECHA:
                        return TipoRelleno.DIAGONAL_DERECHA;
                    case RADIAL:
                        return TipoRelleno.RADIAL;
                }
        }
        return null;
    }
    
    /**
     * Obtiene el objeto Paint que pintara el relleno.
     * 
     * @param p1 Primer punto del degradado.
     * @param p2 Segundo punto dle degradado.
     * @return 
     */
    private Paint getPaint(Point2D p1, Point2D p2){
        switch(getTipoRelleno()){
            case LISO:
                return colorFondo;
            case HORIZONTAL:
                return new GradientPaint(p1, colorFondo, new Point2D.Double((int) p2.getX(), (int) p1.getY()), colorFrente);
            case VERTICAL:
                return new GradientPaint(p1, colorFondo, new Point2D.Double((int) p1.getX(), (int) p2.getY()), colorFrente);
            case DIAGONAL_IZQUIERDA:
                return new GradientPaint(p1, colorFondo, new Point2D.Double((int) p2.getX(), (int) p2.getY()), colorFrente);
            case DIAGONAL_DERECHA:
                return new GradientPaint(new Point2D.Double((int) p1.getX(), (int) p2.getY()), colorFondo, new Point2D.Double((int) p2.getX(), (int) p1.getY()), colorFrente);
            case RADIAL:
                Point2D centro = new Point2D.Double(p1.getX()+((p2.getX()-p1.getX())/2),p1.getY()+((p2.getY()-p1.getY())/2));
                return new RadialGradientPaint(centro,(float)(2*centro.distance(p1)),new float[]{0.0f,0.6f},new Color[]{colorFrente,colorFondo});
        }
        return null;
    }
        
    /**
     * Aplica el estilo de relleno a un objeto Graphics2D.
     * 
     * @param g2d Graphics2D donde se va a pintar.
     * @param p1 Primer punto del degradado.
     * @param p2 Segundo punto dle degradado.
     */
    public void paint(Graphics2D g2d, Point2D p1, Point2D p2){
         g2d.setPaint(getPaint(p1,p2));
     }
}
