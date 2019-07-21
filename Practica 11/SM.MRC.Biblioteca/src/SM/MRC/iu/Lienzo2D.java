/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM.MRC.iu;

import SM.MRC.util.Colores;
import SM.MRC.util.Herramienta;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mario
 */
public class Lienzo2D extends javax.swing.JPanel {

    private List<Shape> formas = new ArrayList();
    
    private Herramienta herramienta;
    
    private Paint color = Color.BLACK;
    private Stroke grosor;    
    private boolean relleno;
    private Composite transparencia;
    private RenderingHints alisar;
    private boolean editar;

    private Point2D p;
    private Shape seleccionado;
    
    private boolean istransparencia, isalisar;
    
    private boolean borde = false;
    
    public Lienzo2D() {
        initComponents();
        herramienta = Herramienta.Punto;
        grosor = new BasicStroke();
        relleno = false;
        transparencia = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0f);
        alisar =  new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        istransparencia = false;
        isalisar = false;
        borde = false;
    }

    public boolean isBorde() {
        return borde;
    }

    public void setBorde(boolean borde) {
        this.borde = borde;
    }
    
    public Herramienta getHerramienta() {
        return herramienta;
    }

    public void setHerramienta(String herramienta) {
        this.herramienta = Herramienta.valueOf(herramienta);
    }
    
    public Colores getColor(){
        if(this.color.equals(Color.BLACK))
            return Colores.Negro;
        if(this.color.equals(Color.RED))
            return Colores.Rojo;
        if(this.color.equals(Color.BLUE))
            return Colores.Azul;
        if(this.color.equals(Color.WHITE))
            return Colores.Blanco;
        if(this.color.equals(Color.YELLOW))
            return Colores.Amarillo;
        if(this.color.equals(Color.GREEN))
            return Colores.Verde;
        return null;
    }
    
    public void setColor(Colores color){
        switch (color.ordinal()){            
            case 0:
                this.color = Color.BLACK;
                break;
            case 1:
                this.color = Color.RED;
                break;
            case 2:
                this.color = Color.BLUE;
                break;
            case 3:
                this.color = Color.WHITE;
                break;
            case 4:
                this.color = Color.YELLOW;
                break;
            case 5:
                this.color = Color.GREEN;
                break;
        }
    }
    
    public int getGrosor(){
        return (int) ((BasicStroke) grosor).getLineWidth();
    }
    
    public void setGrosor(int grosor){
        this.grosor = new BasicStroke(grosor);
    }

    public boolean isRelleno() {
        return relleno;
    }

    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }
    
    public boolean isTransparencia(){
        return istransparencia;
    }
    
    public void setTransparencia(boolean transparencia){
        istransparencia = transparencia;
        if(transparencia)
            this.transparencia = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f);
        else
            this.transparencia = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0f);
    }
    
    public boolean isAlisar(){
        return isalisar;
    }
    
    public void setAlisar(boolean alisar){
        isalisar = alisar;
        if(alisar)
            this.alisar =  new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        else
            this.alisar =  new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }
    
    public void paintBorder(Graphics g){
        super.paintBorder(g);
        Graphics2D g2d = (Graphics2D)g;
        if(borde){
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1, new float[]{3, 3}, 0));
            g2d.draw(new Rectangle(0, 0, this.getWidth(), this.getHeight()));
        }
    }
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
                
        g2d.setPaint(color);
        g2d.setStroke(grosor);
        g2d.setComposite(transparencia);
        g2d.setRenderingHints(alisar);
        
        g2d.clip(new Rectangle(0, 0, this.getWidth(), this.getHeight()));
        
        for(Shape s:formas)
            if(relleno && (s instanceof Rectangle || s instanceof Ellipse2D)){
                g2d.draw(s);
                g2d.fill(s);
            }else
                g2d.draw(s);
        
    }
    
    private boolean isNear(Line2D l, Point2D p){
        if(l.getP1().equals(l.getP2()))
            return l.getP1().distance(p) <= 2;
        else
            return l.ptLineDist(p) <= 2;
    }
        
    private Shape getSelectedShape(Point2D p){
        for(Shape s:formas){
            if((s instanceof Line2D) && isNear((Line2D)s,p)) return s;
            if(s.contains(p)) return s;
        }
        return null;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FormListener formListener = new FormListener();

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addMouseMotionListener(formListener);
        addMouseListener(formListener);

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
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.MouseListener, java.awt.event.MouseMotionListener {
        FormListener() {}
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == Lienzo2D.this) {
                Lienzo2D.this.formMousePressed(evt);
            }
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == Lienzo2D.this) {
                Lienzo2D.this.formMouseReleased(evt);
            }
        }

        public void mouseDragged(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == Lienzo2D.this) {
                Lienzo2D.this.formMouseDragged(evt);
            }
        }

        public void mouseMoved(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == Lienzo2D.this) {
                Lienzo2D.this.formMouseMoved(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        
        p = evt.getPoint();
        if (editar)
            seleccionado = getSelectedShape(p);
        else {
            switch (herramienta.ordinal()) {
                case 1:
                    formas.add((Shape) new Line2D.Double());
                    break;
                case 2:
                    formas.add((Shape) new Rectangle());
                    break;
                case 3:
                    formas.add((Shape) new Ellipse2D.Double());
                    break;
            }
        }
        
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
              
        if(editar) {                 
            if (seleccionado != null) {
                this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                if (seleccionado instanceof Line2D) {
                    int x1 = (int) (((Line2D) seleccionado).getX1() + evt.getPoint().getX() - p.getX());
                    int y1 = (int) (((Line2D) seleccionado).getY1() + evt.getPoint().getY() - p.getY());
                    int x2 = (int) (((Line2D) seleccionado).getX2() + evt.getPoint().getX() - p.getX());
                    int y2 = (int) (((Line2D) seleccionado).getY2() + evt.getPoint().getY() - p.getY());
                    ((Line2D) seleccionado).setLine(x1, y1, x2, y2);
                }
                if(seleccionado instanceof Rectangle) {
                    int x = (int) (((Rectangle) seleccionado).getX() + evt.getPoint().getX() - p.getX());
                    int y = (int) (((Rectangle) seleccionado).getY() + evt.getPoint().getY() - p.getY());
                    ((Rectangle) seleccionado).setLocation(x, y);
                }
                if(seleccionado instanceof Ellipse2D){
                    int x = (int) (((Ellipse2D) seleccionado).getX() + evt.getPoint().getX() - p.getX());
                    int y = (int) (((Ellipse2D) seleccionado).getY() + evt.getPoint().getY() - p.getY());
                    int cx = (int) (((Ellipse2D) seleccionado).getCenterX() + evt.getPoint().getX() - p.getX());
                    int cy = (int) (((Ellipse2D) seleccionado).getCenterY() + evt.getPoint().getY() - p.getY());
                    ((Ellipse2D) seleccionado).setFrameFromCenter(cx, cy, x, y);
                }
                p = evt.getPoint();
            }
        } else {
            switch (herramienta.ordinal()) {
                case 1:
                    ((Line2D) (formas.get(formas.size() - 1))).setLine(p, evt.getPoint());
                    break;
                case 2:
                    ((Rectangle) (formas.get(formas.size() - 1))).setFrameFromDiagonal(p, evt.getPoint());
                    break;
                case 3:
                    ((Ellipse2D) (formas.get(formas.size() - 1))).setFrameFromDiagonal(p, evt.getPoint());
                    break;
            }
        }
        this.repaint();
    }//GEN-LAST:event_formMouseDragged

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        if(herramienta.ordinal() == 0 && !editar)
            formas.add((Shape) new Line2D.Double(p,p));
        else
            this.formMouseDragged(evt);
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.repaint();
    }//GEN-LAST:event_formMouseReleased

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        
        if(editar)
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        else
            this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));            
    }//GEN-LAST:event_formMouseMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
