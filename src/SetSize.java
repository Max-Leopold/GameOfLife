import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetSize extends JFrame {

    JButton submit = new JButton("Submit");
    JTextField height = new JTextField(20);
    JTextField width = new JTextField(20);
    JLabel pane = new JLabel();

    public SetSize(){

        GridLayout gridLayout = new GridLayout(3, 2);
        setLayout(gridLayout);

        JLabel heightLabel = new JLabel("Height:");
        heightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel widthLabel = new JLabel("Width");
        widthLabel.setHorizontalAlignment(SwingConstants.CENTER);





        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    startGame(Integer.valueOf(height.getText()), Integer.valueOf(width.getText()));
                }catch (NumberFormatException ea) {
                    ea.printStackTrace();
                    pane.setText("Keine Zahl eingegeben");
                }
            }
        });



        add(heightLabel);
        add(height);
        add(widthLabel);
        add(width);
        add(pane);
        add(submit);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public GameOfLife startGame(int height, int width){
        GameOfLife gof = new GameOfLife(height, width);
        return gof;
    }
}
