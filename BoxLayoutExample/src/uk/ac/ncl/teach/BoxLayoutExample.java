package uk.ac.ncl.teach;

import java.awt.*;
import javax.swing.*;

/**
 * Illustrate the layout style of a BoxLayout.
 */

public class BoxLayoutExample
{
    private JFrame frame;

    /**
     * Constructor for objects of class BoxLayoutExample
     */
    public BoxLayoutExample() {
        makeFrame();
    }

    /**
     * Create a BoxLayout and place five components within it.
     */
    private void makeFrame() {
        frame = new JFrame("BoxLayout Example");

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(new JButton("first"));
        contentPane.add(new JButton("second"));
        contentPane.add(new JButton("the third string is very long"));
        contentPane.add(new JButton("fourth"));
        contentPane.add(new JButton("fifth"));

        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        BoxLayoutExample b = new BoxLayoutExample();

    }

}
