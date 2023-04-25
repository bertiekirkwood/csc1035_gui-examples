package uk.ac.ncl.teach;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FirstButton {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);

        JLabel text = new JLabel("Change this text.");
        JButton appleButton = new JButton("Click here for apples");
        JButton bananaButton = new JButton("Click here for bananas");

        frame.setLayout(new FlowLayout());
        frame.add(text);
        frame.add(appleButton);
        frame.add(bananaButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create anonymous listeners
        appleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText("Apples");
            }
        });

        bananaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText("Bananas");
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
}

