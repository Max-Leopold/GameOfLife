import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Cell extends JPanel implements Serializable {

    private boolean zustand = false; //True = lebendig, False = tot;
    private int lebendigeNachbarn;

    public Cell(){

        setBackground(new Color(30, 30, 30));

        setBorder(BorderFactory.createLineBorder(new Color(129, 129, 129)));

    }

    public boolean isZustand() {
        return zustand;
    }

    public int getLebendigeNachbarn() {
        return lebendigeNachbarn;
    }

    public void addOneLivingNeighbour(){
        lebendigeNachbarn++;
    }

    public void setLebendigeNachbarn(int lebendigeNachbarn) {
        this.lebendigeNachbarn = lebendigeNachbarn;
    }

    public void changeState(){
        zustand = !zustand;
    }

    public void updateColor(){

        if(!zustand){
            setBackground(new Color(30, 30, 30));
        }
        else{
            setBackground(new Color(129, 129, 129));
        }
    }

    public void updateZustand(){

        if(!zustand){
            if(lebendigeNachbarn == 3){
                zustand = true;
            }
        }
        else if(zustand){

            if(lebendigeNachbarn < 2 || lebendigeNachbarn > 3){
                zustand = false;
            }
            else{
                zustand = true;
            }
        }
    }

}
