/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM.MRC.iu;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Mario
 */
public class Lienzo2DImagen extends Lienzo2D {

    BufferedImage imagen;

    public BufferedImage getImagen() {
        return imagen;
    }
    
    public BufferedImage getImagen(boolean drawVector) {
        if (drawVector){
            BufferedImage imagenSalida = new BufferedImage(imagen.getWidth(),imagen.getHeight(),imagen.getType());
            this.paint(imagenSalida.createGraphics());
            return imagenSalida;
        }else
            return getImagen();
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
        setBackground(new java.awt.Color(204, 204, 204));
        if(imagen != null) {
            setPreferredSize(new Dimension(imagen.getWidth(), imagen.getHeight()));
            setSize(new Dimension(imagen.getWidth(), imagen.getHeight()));
        }
    }
    
    public boolean isAlfa(){
        return this.imagen.isAlphaPremultiplied();
    }
    
    public Lienzo2DImagen() {
        initComponents();
        imagen = null;
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        if (imagen != null) {
            
            
            g.drawImage(imagen, 0, 0, this);
            if(isBorde()){
                g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1, new float[]{3, 3}, 0));
                g2d.draw(new Rectangle(0, 0, this.getWidth()+1, this.getHeight()+1));
            }
        }
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
