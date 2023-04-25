package uk.ac.ncl.teach;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TelphoneActionListener - drive a phone application state machine, in
 * response to user key/button presses at the GUI of a given phone.
 */


public class TelephoneActionListener implements ActionListener
{
    private final Telephone phone;

    /**
     * Construct a telephone action listener given the phone in use.
     *
     * @param phone the phone in use
     */
    public TelephoneActionListener(Telephone phone)
    {
        this.phone = phone;
    }

    /**
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent event)
    {
        final String cmdTxt = event.getActionCommand();
        final Telephone.Command cmd = phone.getCommand(cmdTxt);

        switch (phone.getState())
        {
            case IDLE:
                if (cmd == Telephone.Command.KEY_PRESS)
                    phone.startDialling(cmdTxt);
                break;
            case DIALLING:
                if (cmd == Telephone.Command.KEY_PRESS)
                    phone.dial(cmdTxt);
                else if (cmd == Telephone.Command.CALL)
                    phone.call();
                else if (cmd == Telephone.Command.HANG_UP)
                    phone.hangUp();
                break;
            case CALLING:
                if (cmd == Telephone.Command.HANG_UP)
                    phone.hangUp();
                break;
            case BUSY:
                if (cmd == Telephone.Command.HANG_UP)
                    phone.hangUp();
                break;
            case CONNECTED:
                if (cmd == Telephone.Command.HANG_UP)
                    phone.hangUp();
                break;
        }
    }

}
