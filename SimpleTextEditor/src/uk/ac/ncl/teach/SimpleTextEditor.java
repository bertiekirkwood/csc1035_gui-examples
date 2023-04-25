package uk.ac.ncl.teach;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

/**
 * SimpleTextEditor - demonstration of a text editor.
 */

public class SimpleTextEditor
{
    private static final Font PLAIN_FONT = new Font(null, Font.PLAIN, 18);
    private static final Font BOLD_FONT = PLAIN_FONT.deriveFont(Font.BOLD);
    private static final String LOWER_CASE = "lower case";
    private static final String UPPER_CASE = "upper case";

    /**
     * Start the application.
     *
     * @param args arguments to main are ignored
     */
    public static void main(String[] args) throws Exception
    {
        new SimpleTextEditor().launch();
    }

    /**
     * Launches a text editor. For demonstration purposes the text editor
     * is initialised with some text explaining some of the Swing
     * components used in the GUI.
     */
    public void launch() throws Exception
    {
        final JPanel editor = new JPanel();
        editor.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        editor.setLayout(new BorderLayout());

        final JTextPane text = new JTextPane();
        text.setFont(PLAIN_FONT);

        final JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setPreferredSize(new Dimension(20, 200));
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        final JPanel buttons = getEditorButtons(text);

        editor.add(buttons, BorderLayout.NORTH);
        editor.add(scrollPane, BorderLayout.CENTER);

        text.setText(
                "This is an editable JTextPane within a JScrollPane. " +
                        "Buttons are provided for copy, paste, cut and switching case. " +
                        "There is a checkbox to switch bold on or off for all text. " +
                        "The buttons are laid out in a JPanel using the GridLayout. " +
                        "The scroll pane and button panel are laid out in another " +
                        "panel using the BorderLayout."
        );

        final JFrame frame = new JFrame("SimpleTextEditor");
        frame.add(editor);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Note: this method takes any JTextComponent
    private JPanel getEditorButtons(final JTextComponent text)
    {
        final JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 5));

        final JButton copy = new JButton("copy");
        final JButton cut = new JButton("cut");
        final JButton paste = new JButton("paste");
        final JButton changeCase = new JButton("upper case");
        final JCheckBox bold = new JCheckBox("bold");

        copy.addActionListener( new
                                        ActionListener()
                                        {
                                            public void actionPerformed(ActionEvent event)
                                            {
                                                text.copy();
                                            }
                                        });

        cut.addActionListener( new
                                       ActionListener()
                                       {
                                           public void actionPerformed(ActionEvent event)
                                           {
                                               text.cut();
                                           }
                                       });

        paste.addActionListener( new
                                         ActionListener()
                                         {
                                             public void actionPerformed(ActionEvent event)
                                             {
                                                 text.paste();
                                             }
                                         });

        changeCase.addActionListener( new
                                              ActionListener()
                                              {
                                                  public void actionPerformed(ActionEvent event)
                                                  {
                                                      if (text.getDocument().getLength() == 0)
                                                          return;

                                                      if (event.getActionCommand().equals(UPPER_CASE))
                                                      {
                                                          text.setText(text.getText().toUpperCase());
                                                          setActionCommand(changeCase, LOWER_CASE);
                                                      }
                                                      else
                                                      {
                                                          text.setText(text.getText().toLowerCase());
                                                          setActionCommand(changeCase, UPPER_CASE);
                                                      }
                                                  }
                                              });

        bold.addItemListener(new
                                     ItemListener()
                                     {
                                         public void itemStateChanged(ItemEvent event)
                                         {
                                             if (event.getStateChange() == ItemEvent.DESELECTED)
                                                 text.setFont(PLAIN_FONT);
                                             else
                                                 text.setFont(BOLD_FONT);

                                         }
                                     });

        buttons.add(copy);
        buttons.add(cut);
        buttons.add(paste);
        buttons.add(changeCase);
        buttons.add(bold);

        return buttons;
    }

    private static void setActionCommand(JButton button, String actionCommand)
    {
        button.setActionCommand(actionCommand);
        button.setText(actionCommand);
    }

}