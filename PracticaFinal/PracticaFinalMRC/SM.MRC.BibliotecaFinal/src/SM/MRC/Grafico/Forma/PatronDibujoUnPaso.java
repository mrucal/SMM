package SM.MRC.Grafico.Forma;

import java.awt.geom.Point2D;

/**
 * Interfaz que establece métodos para el proceso de dibujo (en un paso) de una forma en un panel.
 * 
 * @author Mario
 */
public interface PatronDibujoUnPaso {
    
    /**
     * Obtiene el primer punto de la diagonal que dibuja la forma.
     * @return Punto del paso 0.
     */
    Point2D getPuntoPaso0();
    
    /**
     * Modifica el primer punto de la diagonal que dibuja la forma.
     * 
     * @param d1 Primer punto de la diagonal.
     */
    void setPuntoPaso0(Point2D d1); 
    
    /**
     * Obtiene el segundo punto de la diagonal que dibuja la forma.
     * 
     * @return Segundo punto de la diagonal que dibuja la forma.
     */
    Point2D getPuntoPaso1();

    /**
     * Modifica el segundo punto de la diagonal que dibuja la forma.
     * 
     * @param d2 Segundo punto de la diagonal que dibuja la forma.
     */
    void setPuntoPaso1(Point2D d2);
}
