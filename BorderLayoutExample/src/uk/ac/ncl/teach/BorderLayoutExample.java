package uk.ac.ncl.teach;

import java.awt.*;
import javax.swing.*;

/**
 * Illustrate the layout style of a BorderLayout.
 *
 */

public class BorderLayoutExample
{

    private JFrame frame;


    public BorderLayoutExample()
    {
        makeFrame();
    }

    /**
     * Place five components in the available regions.
     */
    private void makeFrame()
    {
        frame = new JFrame("BorderLayout Example");

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new JButton("north"), BorderLayout.NORTH);
        contentPane.add(new JButton("also north"), BorderLayout.NORTH);
        contentPane.add(new JButton("south"), BorderLayout.SOUTH);
        contentPane.add(new JButton("center"), BorderLayout.CENTER);
        contentPane.add(new JButton("west"), BorderLayout.WEST);
        contentPane.add(new JButton("east"), BorderLayout.EAST);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        BorderLayoutExample b = new BorderLayoutExample();

    }

}
