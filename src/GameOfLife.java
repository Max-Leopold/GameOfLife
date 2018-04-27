import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class GameOfLife extends JInternalFrame implements MouseListener, MouseMotionListener, ActionListener, Serializable {

    public int height;
    public int width;
    private Cell[][] cells;
    Timer timer = new Timer(100, this);
    public int generation;
    JLabel generationLabel;
    private static final long serialVersionUID = 1L;
    private boolean pressed;

    public GameOfLife(int height, int width) {

        super();
        setResizable(true);
        setClosable(true);

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

        pressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        pressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(pressed){
        Cell cell = (Cell) e.getSource();
        cell.changeState();
        cell.updateColor();
        }
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

            fos.close();
            oos.close();

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

            fis.close();
            ois.close();


            Timer timer = new Timer(0, this::actionPerformed);
            timer.start();
            timer.setRepeats(false);
        }
    }

    public void clearField(){
        timer.stop();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j].setLebendigeNachbarn(0);
                cells[i][j].setZustand(false);
                cells[i][j].setBackground(new Color(30, 30, 30));
            }
        }
    }

    public void startGame(){
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                Cell cell = cells[i][j];
                cell.setLebendigeNachbarn(0);

                try {

                    if (j == width - 1 && i == 0) {
                        if (cells[height - 1][j].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[height - 1][j - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[height - 1][0].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i + 1][0].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i][0].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i][j - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i + 1][j].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i + 1][j - 1].isZustand())
                            cell.addOneLivingNeighbour();
                    } else if (j == width - 1 && i == height - 1) {
                        if (cells[i - 1][0].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[0][0].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i][0].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[0][j].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[0][j - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i - 1][j - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i - 1][j].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i][j - 1].isZustand())
                            cell.addOneLivingNeighbour();
                    } else if (j == width - 1) {
                        if (cells[i - 1][0].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i + 1][0].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i][0].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i - 1][j].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i - 1][j - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i + 1][j].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i + 1][j - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i][j - 1].isZustand())
                            cell.addOneLivingNeighbour();
                    } else if (j == 0 && i == 0) {
                        if (cells[height - 1][j].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[height - 1][width - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[height - 1][j + 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i + 1][width - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i][width - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i + 1][j].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i + 1][j + 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i][j + 1].isZustand())
                            cell.addOneLivingNeighbour();
                    } else if (j == 0 && i == height - 1) {
                        if (cells[i - 1][width - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[0][j].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[0][width - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[0][j + 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i][width - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i - 1][j].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i][j + 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i - 1][j + 1].isZustand())
                            cell.addOneLivingNeighbour();
                    } else if (j == 0) {
                        if (cells[i - 1][width - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i + 1][width - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i][width - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i - 1][j].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i - 1][j + 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i + 1][j].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i + 1][j + 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i][j + 1].isZustand())
                            cell.addOneLivingNeighbour();
                    } else if (i == 0) {
                        if (cells[height - 1][j].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[height - 1][j - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[height - 1][j + 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i + 1][j].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i + 1][j - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i + 1][j + 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i][j + 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i][j - 1].isZustand())
                            cell.addOneLivingNeighbour();
                    } else if (i == height - 1) {
                        if (cells[i - 1][j].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i - 1][j - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i - 1][j + 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[0][j].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[0][j - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[0][j + 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i][j + 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i][j - 1].isZustand())
                            cell.addOneLivingNeighbour();
                    } else {
                        if (cells[i - 1][j].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i - 1][j - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i - 1][j + 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i + 1][j].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i + 1][j - 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i + 1][j + 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i][j + 1].isZustand())
                            cell.addOneLivingNeighbour();
                        if (cells[i][j - 1].isZustand())
                            cell.addOneLivingNeighbour();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		startGame();
    }
    
    public void pauseGame(){
        timer.stop();
    }

    public void clearGame(){
        timer.stop();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j].setLebendigeNachbarn(0);
                cells[i][j].setZustand(false);
                cells[i][j].setBackground(new Color(30, 30, 30));
            }
        }
    }


}

