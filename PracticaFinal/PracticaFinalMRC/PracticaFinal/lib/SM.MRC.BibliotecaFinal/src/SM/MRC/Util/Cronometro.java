package SM.MRC.Util;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 * Clase que implementa el cronómetro utilizado en el reproductor.
 * 
 * @author Mario
 */
public class Cronometro extends Thread{
    JLabel label;
    JProgressBar progreso;
    static int segundos = 0;
    
    static boolean play = false;
    static boolean pause = false;
    
    static boolean stop = false;
    
    /**
     * Constructor por defecto.
     * @param label Label donde se va a visualizar el cronómetro.
     * @param progreso Barra de progreso donde se visualiza el cronómetro. (Puede ser null)
     */
    public Cronometro(JLabel label, JProgressBar progreso){
        this.label = label;
        this.progreso = progreso;
    }
    
    @Override
    public void run(){
        try{
            if(!pause)
                segundos = 0;
            else
                pause = false;
            while (play){
                Thread.sleep(1000);
                segundos +=1;
                label.setText(Util.getTiempo(segundos));
                if(progreso != null)
                    progreso.setValue(segundos);
            }
            if(stop){
                label.setText("00:00:00");
                segundos = 0;
                if(progreso != null)
                    progreso.setValue(segundos);
            }
        }catch(Exception e){
            
        }
    }
    
    /**
     * Inicia el cronómetro.
     */
    public void playCronometro(){
        play = true;
        stop = false;
        start();        
    }
    
    /**
     * Para el cronómetro.
     */
    public void stopCronometro(){
        play = false;
        stop = true;
    }
    
    /**
     * Pausa el cronómetro.
     */
    public void pauseCronometro(){
        pause = true;
        play = false;
        stop = false;
    }

    /**
     * Obtiene el segundo actual del cronómetro.
     * @return Segundo.
     */
    public static int getSegundos() {
        return segundos;
    }

    /**
     * Modifica el segundo actual del cronómetro.
     * @param segundos Segundo.
     */
    public static void setSegundos(int segundos) {
        Cronometro.segundos = segundos;
    }
}
