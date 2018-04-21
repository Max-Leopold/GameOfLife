import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOfLife extends JFrame implements MouseListener, MouseMotionListener {

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
                cell.addMouseListener(this);
                cell.addMouseMotionListener(this);
                add(cell);
            }
        }

        JMenuBar menuBar = new JMenuBar();

        JMenu startMenu = new JMenu("Start/ Pause");

        JMenuItem start = new JMenuItem("Start");
        JMenuItem pause = new JMenuItem("Pause");

        startMenu.add(start);
        startMenu.add(pause);

        menuBar.add(startMenu);

        setJMenuBar(menuBar);

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
        gof.setSize(height * 25, width * 25);
        gof.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Cell cell = (Cell) e.getSource();
        cell.changeState();
        cell.update();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
