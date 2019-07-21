package SM.MRC.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import uk.co.caprica.vlcj.filter.VideoFileFilter;

/**
 * Clase que contiene métodos y variables para la gestión de ficheros y extensiones.
 * 
 * @author Mario
 */
public class Ficheros {
    
    public static ArrayList<String> extensiones_imagen = getExtensionesImagen();
    public static ArrayList<String> extensiones_mayuscula_imagen = getExtensionesMayusculas(extensiones_imagen);
    public static ArrayList<String> extensiones_sonido = getExtensionesSonido();
    public static ArrayList<String> extensiones_mayuscula_sonido = getExtensionesMayusculas(extensiones_sonido);
    public static ArrayList<String> extensiones_video = getExtensionesVideo();
    public static ArrayList<String> extensiones_mayuscula_video = getExtensionesMayusculas(extensiones_video);
    public static ArrayList<String> extensiones_total = getExtensionesTotal();
    
    /**
     * Obtiene la extensión de un fichero
     * @param f Fichero.
     * @return Extensión del fichero en formato String.
     */
    public static String getExtensionFichero(File f){
        return f.getName().substring(f.getName().lastIndexOf(".")+1, f.getName().length());
    }
    
    /**
     * Añade filtros de imágen, sonido y vídeo a un JFileChooser.
     * @param fc JFileChooser al que se añaden filtros.
     */
    public static void filtrarExtensionesFileChooser(JFileChooser fc){
        while (fc.getFileFilter() != null)
            fc.removeChoosableFileFilter(fc.getFileFilter());
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Todas las archivos compatibles",extensiones_total.toArray(new String[extensiones_total.size()])));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Todas las imágenes compatibles ("+extensiones_imagen.get(0).substring(0, extensiones_imagen.get(0).length()-2)+")",ImageIO.getReaderFormatNames()));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Todas los sonidos compatibles ("+extensiones_sonido.get(0).substring(0, extensiones_sonido.get(0).length()-2)+")",extensiones_sonido.toArray(new String[extensiones_sonido.size()])));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Todas los videos compatibles",(new VideoFileFilter()).getExtensions()));
        for(int i = 1; i < extensiones_imagen.size(); i++)
            fc.addChoosableFileFilter(new FileNameExtensionFilter("*."+extensiones_imagen.get(i),extensiones_imagen.get(i)));
        for(int i = 1; i < extensiones_sonido.size(); i++)
            fc.addChoosableFileFilter(new FileNameExtensionFilter("*."+extensiones_sonido.get(i),extensiones_sonido.get(i)));
        for(int i = 1; i < extensiones_video.size(); i++)
            fc.addChoosableFileFilter(new FileNameExtensionFilter("*."+extensiones_video.get(i),extensiones_video.get(i)));
    }   
    
    /**
     * Añade filtros de imágen a un JFileChooser.
     * @param fc JFileChooser al que se añaden filtros.
     */
    public static void filtrarExtensionesFileChooserImagen(JFileChooser fc){
        while (fc.getFileFilter() != null)
            fc.removeChoosableFileFilter(fc.getFileFilter());
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Todas las imágenes compatibles (" + extensiones_imagen.get(0).substring(0, extensiones_imagen.get(0).length() - 2) + ")", ImageIO.getReaderFormatNames()));
        for (int i = 1; i < extensiones_imagen.size(); i++)
            fc.addChoosableFileFilter(new FileNameExtensionFilter("*."+extensiones_imagen.get(i),extensiones_imagen.get(i)));   
    } 
    
    /**
     * Añade filtros de sonido a un JFileChooser.
     * @param fc JFileChooser al que se añaden filtros.
     */
    public static void filtrarExtensionesFileChooserSonido(JFileChooser fc){
        while (fc.getFileFilter() != null)
            fc.removeChoosableFileFilter(fc.getFileFilter());
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Todas los sonidos compatibles ("+extensiones_sonido.get(0).substring(0, extensiones_sonido.get(0).length()-2)+")",extensiones_sonido.toArray(new String[extensiones_sonido.size()])));
        for(int i = 1; i < extensiones_sonido.size(); i++)
            fc.addChoosableFileFilter(new FileNameExtensionFilter("*."+extensiones_sonido.get(i),extensiones_sonido.get(i)));
    } 
    
    /**
     * Crea una lista que contiene todas las extensiones soportadas de imagen, vídeo y sonido.
     * @return Lista de extensiones soportadas.
     */
    private static ArrayList<String> getExtensionesTotal(){
        ArrayList<String> total = new ArrayList(Arrays.asList(ImageIO.getReaderFormatNames()));
        total.addAll(extensiones_sonido);
        total.addAll(extensiones_video);
        return total;
    }
    
    /**
     * Crea una lista que contiene todas las extensiones soportadas de imagen.
     * @return Lista de extensiones soportadas.
     */
    private static ArrayList<String> getExtensionesImagen(){
        ArrayList<String> extensiones = new ArrayList();
        String todos = "";
        for(String s:ImageIO.getReaderFormatNames()){
            s = s.toLowerCase();
            if(!extensiones.contains(s)){
                extensiones.add(s);
                todos = todos + "*."+s+"; ";
            }
        }
        extensiones.add(0,todos);
        return extensiones;
    }
    
    /**
     * Crea una lista que contiene todas las extensiones soportadas de sonido.
     * @return Lista de extensiones soportadas.
     */
    private static ArrayList<String> getExtensionesSonido(){
        ArrayList<String> extensiones = new ArrayList();
        String todos = "";
        AudioFileFormat.Type[]  aTypes = AudioSystem.getAudioFileTypes();
        String s;
        for(AudioFileFormat.Type t: aTypes){
            s = t.getExtension().toLowerCase();
            if(!extensiones.contains(s)){
                extensiones.add(s);
                todos = todos + "*."+s+"; ";
            }
        }
        extensiones.add(0,todos);
        return extensiones;
    }
    
    /**
     * Crea una lista que contiene todas las extensiones soportadas de vídeo.
     * @return Lista de extensiones soportadas.
     */
    private static ArrayList<String> getExtensionesVideo(){
        ArrayList<String> extensiones = new ArrayList();
        String todos = "";
        VideoFileFilter vff = new VideoFileFilter();
        AudioFileFormat.Type[]  aTypes = AudioSystem.getAudioFileTypes();
        for(String s: Arrays.asList(vff.getExtensions())){
            s = s.toLowerCase();
            if(!extensiones.contains(s)){
                extensiones.add(s);
                todos = todos + "*."+s+"; ";
            }
        }
        extensiones.add(0,todos);
        return extensiones;
    }
    
    /** 
     * Crea una lista de extensiones en mayúscula a partir de una lista de extensiones minúsculas.
     * @param extensiones Lista de extensiones en minúscula.
     * @return Lista de extensiones en mayúscula.
     */
    private static ArrayList<String> getExtensionesMayusculas(ArrayList<String> extensiones) {
        ArrayList<String> extensiones_mayusculas = new ArrayList();
        for (String s : extensiones) {
            extensiones_mayusculas.add(s.toUpperCase());
        }
        return extensiones_mayusculas;
    }    
    
    /**
     * Devuelve el tipo de recurso contenido en el fichero.
     * @param f Fichero.
     * @return Tipo de recurso. Puede ser imagen, sonido, vídeo o vacío (si tiene un formato no soportado).
     */
    public static TipoRecurso getTipoFichero(File f){
        if(extensiones_imagen.contains(getExtensionFichero(f)))
            return TipoRecurso.IMAGEN;
        if(extensiones_sonido.contains(getExtensionFichero(f)))
            return TipoRecurso.SONIDO;
        if(extensiones_video.contains(getExtensionFichero(f)))
            return TipoRecurso.VIDEO;
        return TipoRecurso.VACIO;
    }
    
    
}
