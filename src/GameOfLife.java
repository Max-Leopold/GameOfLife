import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLife extends JFrame {

    public int height;
    public int width;

    public GameOfLife(int height, int width){

        this.height = height;
        this.width = width;

        GridLayout board = new GridLayout(width, height);
        setLayout(board);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = new Cell();
                add(cell);
            }
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        SetSize ss = new SetSize();
        ss.setSize(300, 150);
        ss.setVisible(true);
        ss.setResizable(false);

        ss.submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    GameOfLife gof = ss.startGame(Integer.valueOf(ss.height.getText()), Integer.valueOf(ss.width.getText()));
                    gof.startGame(gof);
                    ss.dispose();
                }catch (NumberFormatException ea) {
                    ea.printStackTrace();
                    ss.pane.setText("Keine Zahl eingegeben");
                }
            }
        });
    }

    public void startGame(GameOfLife gof){
        gof.setSize(500, 500);
        gof.setVisible(true);
    }
}
