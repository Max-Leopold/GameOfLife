import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class GameOfLife extends JFrame implements MouseListener, MouseMotionListener, ActionListener, Serializable {

    public int height;
    public int width;
    private Cell[][] cells;
    Timer timer = new Timer(100, this);
    public int generation;
    JLabel generationLabel;
    private static final long serialVersionUID = 1L;

    public GameOfLife(int height, int width) {

        this.height = height;
        this.width = width;

        cells = new Cell[height][width];

        GridLayout board = new GridLayout(width, height);
        setLayout(board);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = new Cell();
                cell.addMouseListener(this);
                cell.addMouseMotionListener(this);

                cells[i][j] = cell;

                add(cell);
            }
        }

        generationLabel = new JLabel("Generation: " + generation);



        JMenuBar menuBar = new JMenuBar();

        JMenu startMenu = new JMenu("Start/ Pause");
        JMenu saveMenu = new JMenu("Load/ Save");
        JMenu clearMenu = new JMenu("Clear");

        JMenuItem start = new JMenuItem("Start");
        JMenuItem pause = new JMenuItem("Pause");

        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");

        JMenuItem clear = new JMenuItem("Clear");

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                timer.stop();

                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        cells[i][j].setLebendigeNachbarn(0);
                        cells[i][j].setZustand(false);
                        cells[i][j].setBackground(new Color(30, 30, 30));
                    }
                }

                
            }
        });

        clearMenu.add(clear);

        start.addActionListener(this);
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });

        save.addActionListener(this::saveGameButton);
        load.addActionListener(this::loadGameButton);

        startMenu.add(start);
        startMenu.add(pause);

        saveMenu.add(save);
        saveMenu.add(load);

        menuBar.add(startMenu);
        menuBar.add(saveMenu);
        menuBar.add(clearMenu);

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
                try {
                    GameOfLife gof = ss.startGame(Integer.valueOf(ss.height.getText()), Integer.valueOf(ss.width.getText()));
                    gof.startGame(gof);
                    ss.dispose();
                } catch (NumberFormatException ea) {
                    ea.printStackTrace();
                    ss.pane.setText("Keine Zahl eingegeben");
                }
            }
        });
    }

    public void startGame(GameOfLife gof) {
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
        cell.updateColor();
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

    @Override
    public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {

                    Cell cell = cells[i][j];
                    cell.setLebendigeNachbarn(0);

                    try {

                        if (j == width - 1 && i == 0) {
                            if (cells[height - 1][j].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[height - 1][j - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[height - 1][0].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i + 1][0].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i][0].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i][j - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i + 1][j].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i + 1][j - 1].isZustand()) cell.addOneLivingNeighbour();
                        } else if (j == width - 1 && i == height - 1) {
                            if (cells[i - 1][0].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[0][0].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i][0].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[0][j].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[0][j - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i - 1][j - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i - 1][j].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i][j - 1].isZustand()) cell.addOneLivingNeighbour();
                        } else if (j == width - 1) {
                            if (cells[i - 1][0].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i + 1][0].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i][0].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i - 1][j].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i - 1][j - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i + 1][j].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i + 1][j - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i][j - 1].isZustand()) cell.addOneLivingNeighbour();
                        } else if (j == 0 && i == 0) {
                            if (cells[height - 1][j].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[height - 1][width - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[height - 1][j + 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i + 1][width - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i][width - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i + 1][j].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i + 1][j + 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i][j + 1].isZustand()) cell.addOneLivingNeighbour();
                        } else if (j == 0 && i == height - 1) {
                            if (cells[i - 1][width - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[0][j].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[0][width - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[0][j + 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i][width - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i - 1][j].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i][j + 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i - 1][j + 1].isZustand()) cell.addOneLivingNeighbour();
                        } else if (j == 0) {
                            if (cells[i - 1][width - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i + 1][width - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i][width - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i - 1][j].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i - 1][j + 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i + 1][j].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i + 1][j + 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i][j + 1].isZustand()) cell.addOneLivingNeighbour();
                        } else if (i == 0) {
                            if (cells[height - 1][j].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[height - 1][j - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[height - 1][j + 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i + 1][j].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i + 1][j - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i + 1][j + 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i][j + 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i][j - 1].isZustand()) cell.addOneLivingNeighbour();
                        } else if (i == height - 1) {
                            if (cells[i - 1][j].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i - 1][j - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i - 1][j + 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[0][j].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[0][j - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[0][j + 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i][j + 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i][j - 1].isZustand()) cell.addOneLivingNeighbour();
                        } else {
                            if (cells[i - 1][j].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i - 1][j - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i - 1][j + 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i + 1][j].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i + 1][j - 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i + 1][j + 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i][j + 1].isZustand()) cell.addOneLivingNeighbour();
                            if (cells[i][j - 1].isZustand()) cell.addOneLivingNeighbour();
                        }

                    } catch (ArrayIndexOutOfBoundsException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    cells[i][j].updateZustand();
                    cells[i][j].updateColor();
                }
            }

            generation++;
            generationLabel.setText("Generation: " + generation);

        timer.start();
        }


    public void saveGameButton(ActionEvent e){

        try {
            saveGame();
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public void loadGameButton(ActionEvent e){
        try {
            loadGame();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void saveGame() throws IOException {

        JFileChooser fileChooser = new JFileChooser(new File("c://"));

        int returnVal = fileChooser.showSaveDialog(this);
        File file;

        if(returnVal == JFileChooser.APPROVE_OPTION){
            file = fileChooser.getSelectedFile();

            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    oos.writeBoolean(cells[i][j].isZustand());
                    oos.writeInt(cells[i][j].getLebendigeNachbarn());
                }
            }


        }
    }

    public void loadGame() throws IOException {

        JFileChooser fileChooser = new JFileChooser(new File("c://"));

        int returnVal = fileChooser.showOpenDialog(this);
        File file;

        if(returnVal == JFileChooser.APPROVE_OPTION){

            file = fileChooser.getSelectedFile();

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);


            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    cells[i][j].setZustand(ois.readBoolean());
                    cells[i][j].setLebendigeNachbarn(ois.readInt());
                }
            }




            Timer timer = new Timer(0, this::actionPerformed);
            timer.start();
            timer.setRepeats(false);
        }
    }

}






