package uk.ac.ncl.teach;

/**
 * Telephone - an interface to support decoupling action listener
 * from telephone GUI. This telephone interface provides support methods
 * for a decoupled action listener that drives the telephone state machine.
 */

public interface Telephone
{
    /**
     * State - defined phone states.
     */
    static enum State {IDLE, DIALLING, CALLING, BUSY, CONNECTED};

    /**
     * Command - defined phone commands, corresponding to phone control
     * buttons.
     */
    static enum Command {CALL, HANG_UP, KEY_PRESS, OTHER};

    /**
     * Start a call (after dialling phone keys).
     */
    void call();

    /**
     * Connect a call.
     */
    void connect();

    /**
     * Dial the given key.
     *
     * @param key the key to dial
     * @throws NullPointerException if <code>key</code> is empty or
     * <code>null</code>
     */
    void dial(String key);

    /**
     * Get the phone command for the given comand name string.
     *
     * @param commandText the command text
     * @return the phone command corresponding to the given string
     * @throws NullPointerException if <code>commandText</code> is empty or
     * <code>null</code>
     */
    Command getCommand(String commandText);

    /**
     * Get the phone's current state.
     *
     * @return the phone's current state.
     */
    State getState();

    /**
     * Hang up the phone.
     */
    void hangUp();

    /**
     * Start dialling with the given key.
     *
     * @param key the key to dial
     * @throws NullPointerException if <code>key</code> is empty or
     * <code>null</code>
     */
    void startDialling(String key);

}
