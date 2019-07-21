package SM.MRC.Listener;

import com.sun.glass.events.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;

/**
 * Clase para controlar como se comportan los TextFields del panel de tamaño.
 * 
 * @author Mario
 */
public class TextFieldsTamañoListener extends KeyAdapter {
    
    JDialog dialogo;

    @Override
    public void keyPressed(java.awt.event.KeyEvent evt) {
        JFormattedTextField tf = (JFormattedTextField) evt.getSource();
        int i = tf.getCaretPosition();
        String textBackspace, textDelete;

        if (evt.getKeyCode() == KeyEvent.VK_BACKSPACE && i > 0) {
            textBackspace = tf.getText().substring(0, i - 1) + tf.getText().substring(i, tf.getText().length());
            if (Integer.parseInt(tf.getText()) < 10 || Integer.valueOf(textBackspace) == 0) 
                evt.consume();            
        }

        if (evt.getKeyCode() == KeyEvent.VK_DELETE && i < tf.getText().length()) {
            textDelete = tf.getText().substring(0, i) + tf.getText().substring(i + 1, tf.getText().length());
            if (Integer.parseInt(tf.getText()) < 10 || Integer.valueOf(textDelete) == 0) 
                evt.consume();
        }
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent evt) {
        JFormattedTextField tf = (JFormattedTextField) evt.getSource();

        if (evt.getKeyChar() == '0' && tf.getCaretPosition() == 0) 
            evt.consume();

        if (!Character.isDigit(evt.getKeyChar())) 
            evt.consume();
         else if (Integer.parseInt(tf.getText()) > 999) 
            evt.consume();
        
    }
}
