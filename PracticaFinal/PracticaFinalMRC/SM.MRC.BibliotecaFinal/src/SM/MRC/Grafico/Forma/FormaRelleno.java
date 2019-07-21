package SM.MRC.Grafico.Forma;

import SM.MRC.Grafico.Estilo.EstiloRelleno;
import SM.MRC.Grafico.Estilo.Relleno;
import SM.MRC.Util.TipoRelleno;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 * Clase abstracta que representa una forma RELLENABLE.
 * 
 * @author Mario
 */
public abstract class FormaRelleno extends Forma{

    //Relleno relleno;
    
    /**
     * Constructor por defecto
     */
    public FormaRelleno(){
        estilo = new EstiloRelleno();
    }
       
    /**
     * Constructor por defecto, indicando un estilo.
     * 
     * @param estilo Atributos que tendrá la forma.
     */
    public FormaRelleno(EstiloRelleno estilo){
        this.estilo = new EstiloRelleno(estilo);
    }
    
    /**
     * Obtiene el color de fondo del relleno.
     * 
     * @return Color de fondo del relleno
     */
    public Color getColorFondoRelleno(){
        return ((EstiloRelleno)estilo).getColorFondo();
    }
    
    /**
     * Modifica el color de fondo del relleno.
     * 
     * @param color Color de fondo de relleno.
     */
    public void setColorFondoRelleno(Color color){
        ((EstiloRelleno)estilo).setColorFondo(color);
    }
    
    /**
     * Obtiene el color de frente de relleno.
     * @return Color de frente de relleno.
     */
    public Color getColorFrenteRelleno(){
        return ((EstiloRelleno)estilo).getColorFrente();
    }    
    
    /**
     * Modifica el color de frente del relleno
     * 
     * @param color Color de frente del relleno.
     */
    public void setColorFrenteRelleno(Color color){
        ((EstiloRelleno)estilo).setColorFrente(color);
    }
    
    /**
     * Obtener el tipo de relleno.
     * 
     * @return Tipo de relleno. Puede ser {@value SM.MRC.Grafico.Estilo.Relleno#SIN_RELLENO},{@value SM.MRC.Grafico.Estilo.Relleno#LISO} o {@value SM.MRC.Grafico.Estilo.Relleno#DEGRADADO}
     */
    public int getTipoRelleno(){
        return ((EstiloRelleno)estilo).getRelleno().getTipo();
    }
    
    /**
     * Obtiene el enumerado que representa el tipo de relleno.
     * 
     * @return Tipo de Relleno. Puede ser SIN_RELLENO, LISO, DEGRADADO_HORIZONTAL o DEGRADADO_VERTICAL.
     */
    public TipoRelleno getTipoRellenoEnum(){
        return ((EstiloRelleno)estilo).getRelleno().getTipoRelleno();
    }
    
    /**
     * Modificar el tipo de relleno.
     * 
     * @param tipo Tipo de relleno: Puede ser {@value SM.MRC.Grafico.Estilo.Relleno#SIN_RELLENO},{@value SM.MRC.Grafico.Estilo.Relleno#LISO} o {@value SM.MRC.Grafico.Estilo.Relleno#DEGRADADO}
     */
    public void setTipoRelleno(int tipo){
        ((EstiloRelleno)estilo).getRelleno().setTipo(tipo);
    }
    
    /**
     * Obtiene el tipo de degradado.
     * 
     * @return Tipo de degradado: Puede ser {@value SM.MRC.Grafico.Estilo.Relleno#HORIZONTAL} o {@value SM.MRC.Grafico.Estilo.Relleno#VERTICAL}.
     */
    public int getTipoDegradado(){
        return ((EstiloRelleno)estilo).getRelleno().getTipoDegradado();
    }
    
    /**
     * Modificar el tipo de degradado.
     * 
     * @param tipo Tipo de degradado: Puede ser {@value SM.MRC.Grafico.Estilo.Relleno#HORIZONTAL} o {@value SM.MRC.Grafico.Estilo.Relleno#VERTICAL}.
     */
    public void setTipoDegradado(int tipo){
        ((EstiloRelleno)estilo).getRelleno().setTipoDegradado(tipo);
    }
    
    /**
     * Obtiene el primer punto pra hacer el degradado.
     * 
     * @return Primer punto del degradado.
     */
    Point2D getP1Degradado(){
        if(this instanceof PatronDibujoUnPaso)
            return ((PatronDibujoUnPaso)this).getPuntoPaso0();
        if(this instanceof PatronDibujoDosPasos)
            return ((PatronDibujoDosPasos)this).getPuntoPaso0();
        return null;
    }
    
    /**
     * Obitene el segundo punto para hacer el degradado.
     * 
     * @return Segungo punto del degradado.
     */
    Point2D getP2Degradado(){
        if(this instanceof PatronDibujoUnPaso)
            return ((PatronDibujoUnPaso)this).getPuntoPaso1();
        if(this instanceof PatronDibujoDosPasos)
            return ((PatronDibujoDosPasos)this).getPuntoPaso1();
        return null;
    }
    
    /**
     * Indica que la forma es rellenable.
     * 
     * @return true
     */
    @Override
    public boolean isRellenable(){
        return true;
    }
    
    /**
     * Dibuja la forma con el tipo de relleno que indica el estilo de la forma.
     * @param g2d Graphics2D donde se dibuja la forma.
     */
    @Override
    public void drawForma(Graphics2D g2d){
        if(((EstiloRelleno)estilo).getRelleno().getTipo() != Relleno.SIN_RELLENO){
            ((EstiloRelleno)estilo).getRelleno().paint(g2d, getP1Degradado(), getP2Degradado());
            g2d.fill((Shape) getShape());
        }
        super.drawContorno(g2d);
    }
    
}
