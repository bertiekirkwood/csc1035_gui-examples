package uk.ac.ncl.teach;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class ButtonWithText
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        JButton helloButton = new JButton("Say hello");
        JButton goodbyeButton = new JButton("Goodbye");
        JTextField tField = new JTextField(20);
        tField.setText("Click a button!");
        frame.setLayout(new FlowLayout());

        frame.add(tField);
        frame.add(goodbyeButton);
        frame.add(helloButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

}
