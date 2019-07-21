package SM.MRC.Grafico.Estilo;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Atributo Alisado.
 * 
 * Tipo de atributo que indica si se aplicará alisado o no.
 * @author Mario
 */
public class Alisado {
    
    private boolean activado;
    
    private RenderingHints alisado;
    
    /**
     * Constructor por defecto.
     */
    public Alisado(){
        activado = false;
        this.alisado =  new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }
    
    /**
     * Constructor indicando si se activa el alisado.
     * @param activado True, si se activa el alisado, false en caso contrario.
     */
    public Alisado(boolean activado){
        this.activado = activado;
        if(activado)
            this.alisado =  new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        else
            this.alisado =  new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }
    
    /**
     * Constructor de copia.
     * 
     * @param alisado Nuevo alisado.
     */
    public Alisado(Alisado alisado){
        this.activado = alisado.isActivado();
        if(activado)
            this.alisado =  new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        else
            this.alisado =  new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }

    /**
     * Indica si está activado el alisado.
     * 
     * @return True si esta activado, false en caso contrario.
     */
    public boolean isActivado() {
        return activado;
    }

    /**
     * Activa o desactiva el alisado.
     * 
     * @param activado True i se activa el alisado, false en caso contrario.
     */
    public void setActivado(boolean activado) {
        this.activado = activado;
        if(activado)
            this.alisado =  new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        else
            this.alisado =  new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }
    
    /**
     * Aplica el estilo de alisado a un objeto Graphics2D.
     * 
     * @param g2d Graphics2D donde se va a pintar.
     */
     public void paint(Graphics2D g2d){
         g2d.setRenderingHints(alisado);
     }
}
