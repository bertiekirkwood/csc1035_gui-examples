package uk.ac.ncl.teach;

import java.awt.*;
import javax.swing.*;

/**
 * Illustrate the layout style of a GridLayout.
 *
 */

public class GridLayoutExample
{
    private JFrame frame;

    /**
     * Constructor for objects of class GridLayoutExample
     */
    public GridLayoutExample() {
        makeFrame();
    }

    /**
     * Create a 3x2 grid and place five components within it.
     */
    private void makeFrame() {
        frame = new JFrame("GridLayout Example");

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(2, 3));
        contentPane.add(new JButton("first"));
        contentPane.add(new JButton("second"));
        contentPane.add(new JButton("the third string is very long"));
        contentPane.add(new JButton("fourth"));
        contentPane.add(new JButton("fifth"));

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        GridLayoutExample g = new GridLayoutExample();

    }

}
