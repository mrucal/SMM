package SM.MRC.Grafico.Estilo;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;

/**
 * Atibuto Transparencia.
 * 
 * Tipo de atributo formado por el grado de transparencia.
 * 
 * @author Mario
 */
public class Transparencia {
    
    private float grado_transparencia;
    private Composite transparencia;
    
    /**
     * Constructor por defecto.
     */
    public Transparencia(){
        grado_transparencia = 0.0f;
        transparencia = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f - grado_transparencia);
    }
    
    /**
     * Constructor con un grado de transparencia.
     * @param grado_transparencia Grado de transparencia. Valor entre 0 y 1. 0 Opaco, 1 Transparente
     */
    public Transparencia(float grado_transparencia){
        assert grado_transparencia < 0.0f || grado_transparencia > 1.0f;
        this.grado_transparencia = grado_transparencia;
        transparencia = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f - grado_transparencia);
    }
    
    /**
     * Constructor de copia.
     * 
     * @param transparencia Nueva transparencia.
     */
    public Transparencia(Transparencia transparencia){
        assert transparencia.getGradoTransparencia() < 0.0f || transparencia.getGradoTransparencia() > 1.0f;
        this.grado_transparencia = transparencia.getGradoTransparencia();
        this.transparencia = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f - grado_transparencia);
    }

    /**
     * Obtiene el grado de transparencia.
     * @return Grado de transparencia. Valor entre 0 y 1. 0 Opaco, 1 Transparente
     */
    public float getGradoTransparencia() {
        return grado_transparencia;
    }

    /**
     * Modifica el grado de transparencia.
     * @param grado_transparencia Grado de transparencia. Valor entre 0 y 1. 0 Opaco, 1 Transparente
     */
    public void setGradoTransparencia(float grado_transparencia) {
        assert grado_transparencia < 0.0f || grado_transparencia > 1.0f;
        this.grado_transparencia = grado_transparencia;
        transparencia = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f - grado_transparencia);
    }
    
    /**
     * Aplica el estilo de transparencia a un objeto Graphics2D.
     * 
     * @param g2d Graphics2D donde se va a pintar.
     */
    public void paint(Graphics2D g2d){
        g2d.setComposite(transparencia);
    }
}
