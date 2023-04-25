package uk.ac.ncl.teach;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;


public class CloseDemo
{

    public static void main(String[] args)
    {
        JFrame jframe = new JFrame("Example");
        jframe.setSize(400, 100);
        jframe.setVisible(true);
        MyCodeToHandleWindowClose m = new MyCodeToHandleWindowClose();
        jframe.addWindowListener(m);

    }

}

class MyCodeToHandleWindowClose implements WindowListener {
    public void windowClosing(WindowEvent e) { System.exit(0); }
    public void windowClosed(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
}
