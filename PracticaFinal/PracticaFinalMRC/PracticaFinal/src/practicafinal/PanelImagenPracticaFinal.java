package practicafinal;

import SM.MRC.Grafico.Forma.FormaRelleno;
import SM.MRC.Panel.PanelImagen;
import SM.MRC.Util.TipoRelleno;
import java.awt.geom.Point2D;

/**
 *
 * @author Mario
 */
public class PanelImagenPracticaFinal extends PanelImagen{
    
    VentanaInternaImagen vi;
    
    public PanelImagenPracticaFinal(VentanaInternaImagen vi){
        super();
        this.vi = vi;
    }
    
    @Override
    public void getSelectedForma(Point2D p){
            super.getSelectedForma(p);
            if(seleccionado!=null){
                vi.getVentanaPrincipal().getBotonAlisar().setSelected(seleccionado.isAlisado());
                vi.getVentanaPrincipal().getSpinnerGrosor().setValue(seleccionado.getGrosor());
                vi.getVentanaPrincipal().getPanelPaletaColores().setBotonContorno(seleccionado.getColorContorno());
                vi.getVentanaPrincipal().getPanelPaletaColores().setBotonFondo(seleccionado.getColorFondo());
                vi.getVentanaPrincipal().getPanelPaletaColores().setBotonFrente(seleccionado.getColorFrente());
                if(seleccionado.isRellenable())
                    vi.getVentanaPrincipal().getBotonRelleno().setIcon(vi.getIconoRelleno((((FormaRelleno)seleccionado).getTipoRellenoEnum()),0));
                else
                    vi.getVentanaPrincipal().getBotonRelleno().setIcon(vi.getIconoRelleno( TipoRelleno.SIN_RELLENO,0));
            }else{
                vi.getVentanaPrincipal().getSpinnerGrosor().setValue(vi.getPanel().getEstilo().getContorno().getGrosor());                    
                vi.getVentanaPrincipal().getBotonRelleno().setIcon(vi.getIconoRelleno( vi.getPanel().getEstilo().getRelleno().getTipoRelleno(),0));
            }
    }
    
    @Override
    public void eliminarSeleccion(){
        super.eliminarSeleccion();
        vi.getVentanaPrincipal().getBotonAlisar().setSelected(vi.getPanel().getEstilo().getAlisado().isActivado());
        vi.getVentanaPrincipal().getPanelPaletaColores().setBotonContorno(vi.getPanel().getEstilo().getContorno().getColor());
        vi.getVentanaPrincipal().getPanelPaletaColores().setBotonFondo(vi.getPanel().getEstilo().getRelleno().getColorFondo());
        vi.getVentanaPrincipal().getPanelPaletaColores().setBotonFrente(vi.getPanel().getEstilo().getRelleno().getColorFrente());
    }
    
}
