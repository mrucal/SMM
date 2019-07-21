package SM.MRC.Grafico.Forma;

import java.awt.geom.Point2D;

/**
 * Interfaz que establece métodos para el proceso de dibujo (en un paso) de una forma en un panel.
 * 
 * @author Mario
 */
public interface PatronDibujoDosPasos  extends PatronDibujoUnPaso{
    
    /**
     * Obtiene el tercer punto de la diagonal que dibuja la forma.
     * @return Punto del paso 2.
     */
    Point2D getPuntoPaso2();
    
    /**
     * Modifica el tercer punto de la diagonal que dibuja la forma.
     * 
     * @param d3 Tercer punto.
     */
    void setPuntoPaso2(Point2D d3); 
}
