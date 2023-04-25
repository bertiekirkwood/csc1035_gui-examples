package uk.ac.ncl.teach;


import javax.swing.JFrame;

public class CarMover {
    private static final int FRAME_WIDTH = 1400;
    private static final int FRAME_HEIGHT = 1400;


    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CarComp component = new CarComp();
        frame.add(component);


        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);


    }

}
