import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Cell extends JPanel {

    private boolean zustand = false; //True = lebendig, False = tot;
    private int lebendigeNachbarn;

    public Cell(){

        Random r = new Random();

        zustand = r.nextBoolean();

        /*if(!zustand){
            setBackground(Color.BLACK);
        }
        else {
            setBackground(Color.GREEN);
        }*/



        setBorder(BorderFactory.createLineBorder(Color.RED, 1, false));

    }

    public boolean isZustand() {
        return zustand;
    }

    public int getLebendigeNachbarn() {
        return lebendigeNachbarn;
    }

}
