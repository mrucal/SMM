package SM.MRC.Sonido;

import SM.MRC.Util.Util;
import java.io.File;
import sm.sound.SMClipPlayer;

/**
 * Clase Player
 * 
 * Clase que añade alguna funcionalidad a la del paquete sm.sound
 * 
 * @author Mario
 */
public class Player {
    
    SMClipPlayer player;
    File f;
    
    private boolean is_playing;
    private boolean is_pause;
    
    /**
     * Constructor por defecto.
     */
    public Player() {
        is_playing = false;
        is_pause = false;
    }

    /**
     * Obtiene el player (SMClipPlayer)
     * 
     * @return Player SMClipPlayer.
     */
    public SMClipPlayer getPlayer() {
        return player;
    }

    /**
     * Asocia al player un fichero de audio.
     * 
     * @param f Fichero que se va a asociar.
     */
    public void setPlayer(File f) {
        this.player = new SMClipPlayer(f);
        this.f = f;
    }
    
    /**
     * Reinicia las variables de control.
     */
    public void reiniciar(){
        this.player = null;
        is_playing = false;
        is_pause = false;
    }
    
    /**
     * Reproduce el fichero que tiene asociado.
     */
    public void play(){
        if(player != null){
            player.play();        
            is_playing = true;
            is_pause = false;
        }
    }
    
    /**
     * Para la reproducción.
     */
    public void stop() {
        if(player != null){
            player.stop();
            is_playing = false;
            is_pause = false;
        }
    }

    /**
     * Pausa la reproducción.
     */
    public void pause() {
        if(player != null){
            player.pause();
            is_playing = false;
            is_pause = true;
        }
    }

    /**
     * Indica si el reproductor está reproduciendo.
     * @return True si está reproduciendo, false en caso contrario.
     */
    public boolean isPlaying() {
        if(this != null)
            return is_playing;
        return false;
    }

    /**
     * Indica si el reproductor está pausado.
     * @return True si esta pausado, false en caso contrario.
     */
    public boolean isPause() {
        return is_pause;
    }

    /**
     * Modifica la variable que controla si el reproductor está pausado.
     * 
     * @param is_pause True si está pausado, false en caso contrario.
     */
    public void setPause(boolean is_pause) {
        this.is_pause = is_pause;
    }
    
    /**
     * Obtiene la duración total en formato "hh:mm:ss" del fichero que tiene asociado.
     * @return String en formato "hh:mm:ss" que representa el tiempo total de duración.
     */
    public String getDuracion(){
        int segundos = Math.toIntExact(player.getClip().getMicrosecondLength() / 1000000);
        return Util.getTiempo(segundos);
    }
    
    /**
     * Obtiene la duración total en segundos del fichero que tiene asociado.
     * 
     * @return Tiempo total de duración en segundos.
     */
    public int getDuracionSegundos(){
        return Math.toIntExact(player.getClip().getMicrosecondLength()/1000000);
    }
}
