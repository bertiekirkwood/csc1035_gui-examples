package uk.ac.ncl.teach;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * KuriousOranjPhone - description.
 *
 * @author Nick Cook &lt;nick.cook@ncl.ac.uk&gt;
 * @version $Revision: 406 $<br>
 * $Date: 2007-11-29 18:52:10 +0000 (Thu, 29 Nov 2007) $<br>
 * Copyright (C) 2007 Newcastle University, UK
 */
public class SimpleTelephone implements Telephone
{

    private static final int CALLING_TIMER_DELAY = 500;
    private static final int DISPLAY_WIDTH = 5;
    private static final int DISPLAY_HEIGHT = 10;
    private static final String DISPLAY_CALLING_MSG = "\ncalling .";
    private static final String DISPLAY_CONNECTED_MSG = "\nconnected";
    private static final String DISPLAY_BUSY_MSG = "\nbusy";
    private static final String DISPLAY_HUNGUP_MSG = "\nhung up";
    private static final String DISPLAY_WAITING_MSG = ".";
    private static final Font PHONE_FONT = new Font(null, Font.PLAIN, 16);
    private static final Font PHONE_TEXT_FONT = PHONE_FONT.deriveFont(Font.BOLD, PHONE_FONT.getSize() + 2);
    private static final Font PHONE_TITLE_FONT= PHONE_FONT.deriveFont(Font.BOLD, PHONE_FONT.getSize() + 4);
    private static final String PHONE_COMPANY = "KuriousOranj";
    private static final String PHONE_CALL_CMD = "Call";
    private static final String PHONE_HANGUP_CMD = "HangUp";
    private static final String PHONE_KEYS = "123456789*0#";

    // display text area to write dialling info etc.
    private final JTextArea displayTextArea;
    private final Timer callingTimer;
    private final Random random;    // used to decide whether a call succeeds
    private State state;
    private JTextField debugTextField; // non-null if switched on in debug mode

    /**
     * Switch on the SimpleTelephone phone.
     *
     * @param args if the <code>args</code> array is not empty then the phone is
     * switched on in debug mode with a status display of the phone's
     * state
     */
    public static void main(String[] args)
    {
        boolean debugMode = args.length > 0;

        new SimpleTelephone().switchOn(debugMode);
    }

    /**
     * Construct a telephone - simply initializes member fields and
     * sets up a calling timer. No GUI elements are made visible.
     */
    public SimpleTelephone()
    {
        state = State.IDLE;
        random = new Random();

        displayTextArea = new JTextArea(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        displayTextArea.setEditable(false);
        displayTextArea.setLineWrap(true);
        displayTextArea.setBackground(Color.LIGHT_GRAY);
        displayTextArea.setFont(PHONE_TEXT_FONT);

        // timer to wait for call connection
        callingTimer = new Timer(CALLING_TIMER_DELAY, new
                ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        // roughly 5/6 chance of waiting
                        if (random.nextInt() % 6 != 0)
                            // keep waiting
                            displayTextArea.append(DISPLAY_WAITING_MSG);
                        else
                            connect();

                        debug();
                    }
                });

    }

    public void call() {
        displayTextArea.append(DISPLAY_CALLING_MSG);

        // roughly 3/4 chance of clear (not busy) line
        if (random.nextInt() % 4 != 0)
        {
            state = State.CALLING;
            // start timer for waiting for call connection
            callingTimer.start();
        }
        else
        {
            // line is busy
            displayTextArea.append(DISPLAY_BUSY_MSG);
            state = State.BUSY;
        }

        debug();
    }

    /**
     * @see my.awt.tests.Telephone#connect()
     */
    public void connect()
    {
        callingTimer.stop();
        displayTextArea.append(DISPLAY_CONNECTED_MSG);
        state = State.CONNECTED;

        debug();
    }

    /**
     * @see my.awt.tests.Telephone#dial(java.lang.String)
     */
    public void dial(String key) {
        displayTextArea.append(key);
    }

    /**
     * @see my.awt.tests.Telephone#getCommand(java.lang.String)
     */
    public Command getCommand(String commandText) {
        if (commandText.length() == 0)
            throw new NullPointerException("empty commandText");

        Command cmd = Command.OTHER;

        if (commandText.equals(PHONE_CALL_CMD))
        {
            cmd = Command.CALL;
        }
        else if (commandText.equals(PHONE_HANGUP_CMD))
        {
            cmd = Command.HANG_UP;
        }
        else if (commandText.length() == 1
                && PHONE_KEYS.indexOf(commandText) != -1)
        {
            cmd = Command.KEY_PRESS;
        }

        return cmd;
    }

    public State getState() {
        return state;
    }

    public void hangUp() {
        if (callingTimer.isRunning())
            callingTimer.stop();

        displayTextArea.append(DISPLAY_HUNGUP_MSG);
        state = State.IDLE;

        debug();
    }

    /**
     * @see my.awt.tests.Telephone#startDialling(java.lang.String)
     */
    public void startDialling(String key) {
        displayTextArea.setText(key);
        state = State.DIALLING;

        debug();
    }

    /**
     * Switch on this phone. Sets up all the GUI components and is ready
     * for input from keypad or control panel. The phone outputs to a
     * display area.
     *
     * @param debugMode if <code>true</code> the phone is switched on with a
     * debug output text field that displays the phone's state
     */
    public void switchOn(boolean debugMode)
    {
        final ActionListener listener = new TelephoneActionListener(this);

        final JPanel display = getDisplay();
        final JPanel keyPad = getKeyPad(listener);
        final JPanel controlPad = getControlPad(listener);

        final JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        frame.add(display, BorderLayout.NORTH);
        frame.add(keyPad, BorderLayout.CENTER);
        frame.add(controlPad, BorderLayout.SOUTH);

        if (debugMode)
        {
            debugTextField = new JTextField(8);
            debugTextField.setText(state.toString());

            controlPad.add(debugTextField);
        }

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // create a control button with given command string
    private JButton getControlButton(String cmd)
    {
        final JButton button = new JButton(cmd);

        button.setFont(PHONE_FONT);
        button.setFont(PHONE_FONT);
        button.setBackground(Color.LIGHT_GRAY);

        return button;
    }

    // get the control pad panel with call and hang up buttons
    private JPanel getControlPad(ActionListener listener)
    {
        final JPanel controlPad = new JPanel();

        controlPad.setLayout(new FlowLayout());

        controlPad.setBackground(Color.LIGHT_GRAY);

        final JButton callButton = getControlButton(PHONE_CALL_CMD);
        final JButton hangUpButton = getControlButton(PHONE_HANGUP_CMD);

        callButton.addActionListener(listener);
        hangUpButton.addActionListener(listener);

        controlPad.add(callButton);
        controlPad.add(hangUpButton);

        return controlPad;
    }

    // get the display panel for phone output
    private JPanel getDisplay()
    {
        final JPanel display = new JPanel();

        display.setLayout(new BorderLayout());

        final JLabel phoneTitle
                = new JLabel(PHONE_COMPANY, SwingConstants.CENTER);
        phoneTitle.setFont(PHONE_TITLE_FONT);

        final JScrollPane textPane = new JScrollPane(displayTextArea);
        textPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        display.add(phoneTitle, BorderLayout.NORTH);

        display.add(textPane, BorderLayout.CENTER);

        return display;
    }

    // get the key pad for number dialling (each key has action listener)
    private JPanel getKeyPad(ActionListener listener)
    {
        final JPanel keyPad = new JPanel();
        keyPad.setLayout(new GridLayout(4, 3));

        for (char k : PHONE_KEYS.toCharArray())
        {
            final String key = String.valueOf(k);
            final JButton keyButton = new JButton(key);
            keyButton.setFont(PHONE_FONT);

            keyPad.add(keyButton);

            keyButton.addActionListener(listener);
        }

        return keyPad;
    }

    // outputs phone state if in debug mode
    private void debug()
    {
        if (debugTextField != null)
            debugTextField.setText(state.toString());
    }



}
