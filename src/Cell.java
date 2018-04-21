import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Cell extends JPanel {

    private boolean zustand = false; //True = lebendig, False = tot;
    private int lebendigeNachbarn;

    public Cell(){

        Random r = new Random();

        zustand = r.nextBoolean();

        setBackground(new Color(30, 30, 30));

        setBorder(BorderFactory.createLineBorder(new Color(129, 129, 129)));

    }

    public boolean isZustand() {
        return zustand;
    }

    public int getLebendigeNachbarn() {
        return lebendigeNachbarn;
    }

    public void changeState(){
        zustand = !zustand;
    }

    public void update(){
        if(!zustand){
            setBackground(new Color(30, 30, 30));
        }
        else{
            setBackground(new Color(129, 129, 129));
        }
    }

}
