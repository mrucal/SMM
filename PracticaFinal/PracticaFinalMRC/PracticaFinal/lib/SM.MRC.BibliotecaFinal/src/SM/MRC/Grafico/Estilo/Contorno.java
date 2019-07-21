package SM.MRC.Grafico.Estilo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * Atributo Contorno.
 * 
 * Tipo de atributo formado por grosor, tipo y color de contorno y tipo de discontinuidad.
 * @author Mario
 */
public class Contorno {
    
    public static final int CONTINUA = 0;
    public static final int PUNTEADA = 1;
    
    private final float[] PATRON = {10.0f , 10.0f};
    
    private int grosor;
    private int discontinuidad;
    
    private boolean con_contorno;
    
    private Color color;
    
    private Stroke contorno;
    
    /**
     * Constructor por defecto.
     */
    public Contorno(){
        grosor = 1;
        discontinuidad = CONTINUA;
        this.con_contorno = true;
        contorno = new BasicStroke(grosor);
        this.color = Color.BLACK;
    }
    
    /** Constructor con grosor y tipo de discontinuidad.
     * 
     * @param grosor Grosor del contorno.
     * @param discontinuidad Tipo de discontinuidad del contorno. Puede ser {@value #CONTINUA} o {@value #PUNTEADA}
     */
    public Contorno(int grosor, int discontinuidad){
        assert discontinuidad != CONTINUA || discontinuidad != PUNTEADA;
        
        this.grosor = grosor;        
        this.discontinuidad = discontinuidad;
        this.con_contorno = true;
        if(discontinuidad == CONTINUA)
            contorno = new BasicStroke(grosor);
        else
            contorno = new BasicStroke(grosor,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER, 1.0f,PATRON, 0.0f);
        this.color = Color.BLACK;
    }

    /**
     * Constructor de copia.
     * 
     * @param contorno Nuevo contorno.
     */
    Contorno(Contorno contorno) {
        assert contorno.getDiscontinuidad() != CONTINUA || contorno.getDiscontinuidad() != PUNTEADA;
        
        this.grosor = contorno.getGrosor();        
        this.discontinuidad = contorno.getDiscontinuidad();
        this.con_contorno = contorno.getTipo();
        if(this.discontinuidad == CONTINUA)
            this.contorno = new BasicStroke(grosor);
        else
            this.contorno = new BasicStroke(grosor,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER, 1.0f,PATRON, 0.0f);
        this.color = contorno.getColor();
    }
    
    /**
     * Obtiene el grosor del contorno.
     * @return Grosor del contorno.
     */
    public int getGrosor(){
        return grosor;
    }
    
    /**
     * Modifica el grosor del contorno.
     * @param grosor Grosor del contorno.
     */
    public void setGrosor(int grosor){
        this.grosor = grosor;
        if(discontinuidad == CONTINUA)
            contorno = new BasicStroke(grosor);
        else
            contorno = new BasicStroke(grosor,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER, 1.0f,PATRON, 0.0f);
    }
    
    /**
     * Obtiene el tipo de discontinuidad de la línea que forma el contorno.
     * 
     * @return Tipo de discontinuidad del contorno. Puede ser {@value #CONTINUA} o {@value #PUNTEADA}
     */
    public int getDiscontinuidad(){
        return discontinuidad;
    }
    
    /**
     * Modifica el tipo de discontinuidad de la línea que forma el contorno.
     * 
     * @param discontinuidad Tipo de discontinuidad del contorno. Puede ser {@value #CONTINUA} o {@value #PUNTEADA}
     */
    public void setDiscontinuidad(int discontinuidad){
        assert discontinuidad != CONTINUA || discontinuidad != PUNTEADA;
        this.discontinuidad = discontinuidad;
        if(discontinuidad == CONTINUA)
            contorno = new BasicStroke(grosor);
        else
            contorno = new BasicStroke(grosor,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER, 1.0f,PATRON, 0.0f);
    }
    
    /**
     * Obtiene el tipo de contorno (con o sin contorno).
     * 
     * @return True, si es con contorno, false en caso contrario.
     */
    public boolean getTipo(){
        return con_contorno;
    }
    
    /**
     * Modifica el tipo de contorno (con o sin contorno).
     * 
     * @param tipo  True, si es con contorno, false en caso contrario.
     */
    public void setTipo(boolean tipo){
        this.con_contorno = tipo;
    }

    /**
     * Obtiene el color de trazo del contorno.
     * 
     * @return Color del contorno.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Modifica el color de trazo del contorno.
     * 
     * @param color  Color del contorno.
     */
    public void setColor(Color color) {
        this.color = color;
    } 
    
    /**
     * Aplica el estilo de contorno a un objeto Graphics2D.
     * 
     * @param g2d Graphics2D donde se va a pintar.
     */
    public void paint(Graphics2D g2d){
        g2d.setPaint(color);
        g2d.setStroke(contorno);
    }
    
}
