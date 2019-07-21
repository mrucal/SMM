package SM.MRC.Grafico.Estilo;

import java.awt.Color;

/**
 * Clase que agrupa los atributos de estilo asociados a una forma.
 * 
 * @author Mario
 */
public class Estilo {
    
    Color colorFondo;
    Color colorFrente;
    Contorno contorno;
    Alisado alisado;
    Transparencia transparencia;
    
    /**
     * Constructor por defecto.
     */
    public Estilo(){
        colorFondo = Color.BLACK;
        colorFrente = Color.WHITE;
        contorno = new Contorno();
        alisado = new Alisado();
        transparencia = new Transparencia();
    }
    
    /**
     * Constructor de copia.
     * 
     * @param estilo Estilo que se va a copiar.
     */
    public Estilo(Estilo estilo){
        colorFondo = estilo.getColorFondo();
        colorFrente = estilo.getColorFrente();
        contorno = new Contorno(estilo.getContorno());
        alisado = new Alisado(estilo.getAlisado());
        transparencia = new Transparencia(estilo.getTransparencia());
    }
    
    /**
     * Obtiene el color de fondo del estilo.
     * 
     * @return Color de fondo del estilo.
     */
    public Color getColorFondo() {
        return (Color)colorFondo;
    }
    
    /**
     * Modifica el color de fondo del estilo.
     * 
     * @param colorFondo Color de fondo del estilo.
     */
    public void setColorFondo(Color colorFondo) {
        this.colorFondo = colorFondo;
    }
    
    /**
     * Obtiene el color de frente del estilo.
     * 
     * @return Color de frente del estilo.
     */
    public Color getColorFrente() {
        return (Color)colorFrente;
    }
    
    /**
     * Modifica el color de frente del estilo.
     * 
     * @param colorFrente Colorde frente del estilo
     */
    public void setColorFrente(Color colorFrente) {
        this.colorFrente = colorFrente;
    }
    
    /**
     * Obtiene el atributo contorno.
     * 
     * @return Contorno del estilo.
     */
    public Contorno getContorno(){
        return contorno;
    }
    
    /**
     * Modifica el contorno del estilo.
     * @param contorno Contorno del estilo.
     */
    public void setContorno(Contorno contorno){
        this.contorno = contorno;
    }    

    /**
     * Obtiene el atributo alisado.
     * 
     * @return Alisado del estilo.
     */
    public Alisado getAlisado() {
        return alisado;
    }

    /**
     * Modifica el alisado del estilo.
     * 
     * @param alisado Alisado del estilo.
     */
    public void setAlisado(Alisado alisado) {
        this.alisado = alisado;
    }
    
    /**
     * Obitene el atributo transparencia del estilo.
     * 
     * @return Transparencia del estilo.
     */
    public Transparencia getTransparencia(){
        return transparencia;
    }
    
    /**
     * Modifica la transparencia del estilo.
     * 
     * @param transparencia Transparenca del estilo.
     */
    public void setTransparencia(Transparencia transparencia){
        this.transparencia = transparencia;
    }
}
