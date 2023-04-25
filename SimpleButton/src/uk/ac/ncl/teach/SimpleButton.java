package uk.ac.ncl.teach;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;


public class SimpleButton
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        JButton helloButton = new JButton("Say hello");
        JButton goodbyeButton = new JButton("Goodbye");
        frame.setLayout(new FlowLayout());
        frame.add(helloButton);
        frame.add(goodbyeButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}
