package SM.MRC.Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Clase Panel Imagen.
 * 
 * Panel en el que es posible dibujar formas y que contiene una imagen.  
 * 
 * @author Mario
 */
public class PanelImagen extends PanelLienzo{
    
    private BufferedImage imagen;
    
    /**
     * Constructor por defecto;
     */
    public PanelImagen(){
        super();
        setBackground(Color.WHITE);
    }

    /**
     * Obtiene la imagen que hay en el panel.
     * @return Imagen que hay en el panel.
     */
    public BufferedImage getImagen() {
        return imagen;
    }
    
    /**
     * Obtiene la imagen que hay en el panel, añadiendo las formas que hay dibujadas.
     * 
     * @param drawVector Indica si se añaden las formas o no.
     * @return Imagen que hay en el panel con las formas (o no) que hay dibujadas.
     */
    public BufferedImage getImagen(boolean drawVector) {
        if (drawVector){
            BufferedImage imagenSalida = new BufferedImage(imagen.getWidth(),imagen.getHeight(),imagen.getType());
            this.paint(imagenSalida.createGraphics());
            return imagenSalida;
        }else
            return getImagen();
    }

    /**
     * Modifica la imagen que hay actualemente en el panel.
     * 
     * @param imagen Imagen que va a contener el panel.
     */
    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
        if(imagen != null) 
            setSize(new Dimension(imagen.getWidth(), imagen.getHeight()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, this);
            drawBorde(g2d);
        }        
    }
}
