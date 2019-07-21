package SM.MRC.Grafico.Estilo;

import java.awt.Color;

/**
 * Clase que agrupa los atributos de estilo asociados a una forma RELLENABLE.
 * 
 * @author Mario
 */
public class EstiloRelleno extends Estilo{
    
    private Relleno relleno;
    
    /**
     * Constructor por defecto.
     */
    public EstiloRelleno(){
        super();
        relleno = new Relleno();
    }
    
    /** 
     * Constructor por defecto, indicando un estilo y un relleno.
     * @param estilo Estilo de la forma.
     * @param relleno Relleno de la forma.
     */
    public EstiloRelleno(Estilo estilo, Relleno relleno){
        colorFondo = estilo.getColorFondo();
        colorFrente = estilo.getColorFrente();
        contorno = new Contorno(estilo.getContorno());
        alisado = new Alisado(estilo.getAlisado());
        transparencia = new Transparencia(estilo.getTransparencia());
        this.relleno = new Relleno(relleno);
    }
    
    /**
     * Constructor de copia.
     * 
     * @param estilo Estilo que se va a copiar.
     */
    public EstiloRelleno(EstiloRelleno estilo){
        colorFondo = estilo.getColorFondo();
        colorFrente = estilo.getColorFrente();
        contorno = new Contorno(estilo.getContorno());
        alisado = new Alisado(estilo.getAlisado());
        transparencia = new Transparencia(estilo.getTransparencia());
        relleno = new Relleno(estilo.getRelleno());
    }

    @Override
    public void setColorFondo(Color colorFondo){
        super.setColorFondo(colorFondo);
        relleno.setColorFondo(colorFondo);
    }
    
    @Override
    public void setColorFrente(Color colorFrente){
        super.setColorFrente(colorFrente);
        relleno.setColorFrente(colorFrente);
    }
    
    /**
     * Obtiene el atributo relleno del estilo.
     * 
     * @return Relleno del estilo.
     */
    public Relleno getRelleno(){
        return relleno;
    }
    
    /**
     * Modifica el atributo relleno del estilo.
     * 
     * @param relleno Relleno del estilo.
     */
    public void setRelleno(Relleno relleno){
        this.relleno = relleno;
    }
}
