import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;


public class OuterFrame extends JFrame{

    private static final long serialVersionUID = 1L;
    private JDesktopPane desk;
    private ArrayList<GameOfLife> childs;

	public OuterFrame(){

        desk = new JDesktopPane();
        desk.setDesktopManager(new DefaultDesktopManager());
        setContentPane(desk);

        desk.setBackground(Color.WHITE);

        childs = new ArrayList<>();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Menü
        JMenuBar menuBar = new JMenuBar();

        JMenu startMenu = new JMenu("Start/ Pause");
        JMenu saveMenu = new JMenu("Load/ Save");
        JMenu clearMenu = new JMenu("Clear");
        JMenu addMenu = new JMenu("Add");

        JMenuItem start = new JMenuItem("Start");
        JMenuItem pause = new JMenuItem("Pause");
        JMenuItem startAll = new JMenuItem("Start All");
        JMenuItem pauseAll = new JMenuItem("Pause All");

        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");

        JMenuItem clear = new JMenuItem("Clear");

        JMenuItem addGameOfLive = new JMenuItem("Add Game of Live");

        clear.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                GameOfLife frame = (GameOfLife) desk.getSelectedFrame();
                frame.clearGame();
            }
        });
        clearMenu.add(clear);
        clearMenu.add(clear);

        start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                GameOfLife frame = (GameOfLife) desk.getSelectedFrame();
                frame.startGame();
            }
        }); //startGame();
        pause.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                GameOfLife frame = (GameOfLife) desk.getSelectedFrame();
                frame.clearGame();
            }
        }); //pauseGame();

        startAll.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for (GameOfLife gof : childs) {
                    gof.startGame();
                }
            }
        });

        pauseAll.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for (GameOfLife gof : childs) {
                    gof.pauseGame();
                }
            }
        });

        save.addActionListener(null); //saveGame();
        load.addActionListener(null); //loadGame();

        addGameOfLive.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                addChild(new GameOfLife(10, 10));
            }
        });

        startMenu.add(start);
        startMenu.add(pause);
        startMenu.add(startAll);
        startMenu.add(pauseAll);

        saveMenu.add(save);
        saveMenu.add(load);

        addMenu.add(addGameOfLive);

        menuBar.add(startMenu);
        menuBar.add(saveMenu);
        menuBar.add(clearMenu);
        menuBar.add(addMenu);

        setJMenuBar(menuBar);

        JPopupMenu rightClick = new JPopupMenu();
        rightClick.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent ev) {
                if (ev.isPopupTrigger()) {
                    rightClick.show(ev.getComponent(), ev.getX(), ev.getY());
                }
            }
        });

        JMenuItem test1 = new JMenuItem("Test1");

        rightClick.add(test1);

        GameOfLife gof = new GameOfLife(30, 30);
        GameOfLife gof2 = new GameOfLife(20, 20);

        addChild(gof);
        addChild(gof2);
    
    }

    public void addChild(GameOfLife child){
        child.setSize(child.height * 25, child.width * 25);
        child.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        add(child);
        child.setResizable(true);
        child.setVisible(true);
        childs.add(child);
    }
}