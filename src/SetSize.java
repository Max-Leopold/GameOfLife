import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetSize extends JFrame {

    private static final long serialVersionUID = 1L;
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


        submit.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    OuterFrame of = new OuterFrame();
                    of.setSize(1920, 1080);
                    of.setVisible(true);
                    dispose();
                }catch(NumberFormatException ex){
                    ex.printStackTrace();
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
}
