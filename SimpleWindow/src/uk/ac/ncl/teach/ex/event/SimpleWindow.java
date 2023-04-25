package uk.ac.ncl.teach.ex.event;
import javax.swing.JFrame;


public class SimpleWindow
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //System.out.println("Finished");
    }

}
