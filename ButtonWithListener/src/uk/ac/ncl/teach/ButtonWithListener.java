package uk.ac.ncl.teach;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class ButtonWithListener
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        JButton helloButton = new JButton("Say hello");
        JButton goodbyeButton = new JButton("Goodbye");
        final JTextField tField = new JTextField(20);
        tField.setText("Click a button!");

        helloButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                tField.setText("Hello World!");
            }
        });

        goodbyeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                tField.setText("Goodbye cruel World!");
            }
        });
        frame.setLayout(new FlowLayout());
        frame.add(tField);
        frame.add(helloButton);
        frame.add(goodbyeButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        System.out.println("Finished!");

    }

}
