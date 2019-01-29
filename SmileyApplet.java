

import javax.swing.*;
import java.awt.*;


public class SmileyApplet extends JApplet {

    //declare variables
    int numSmileys;
    BorderLayout border;
    JPanel titlePanel, displayContainer, footerPanel;
    JPanel[] display;
    JLabel title, footer;
    Smiley[] smiley;
    SmileyController[] control;
    Color bgColor = new Color(77, 44, 125);


    //set a border layout, run methods to set up the display
    public void init() {
        border = new BorderLayout();
        setLayout(border);

        setUpHeader();
        getNumSmileys();
        setUpDisplay();
        setupContainer();
        setupFooter();
    }

    //create panel for the header and add it to the north section of the border layout
    public void setUpHeader() {
        titlePanel = new JPanel(new FlowLayout());
        titlePanel.setBackground(Color.BLACK);
        title = new JLabel("<html><font size=+2 color=#835CBC>Control size with + and -</font></html>");
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);
    }

    //create a panel that explains some controls placed on the bottom of the applet
    public void setupFooter(){
        footerPanel = new JPanel(new FlowLayout());
        footerPanel.setBackground(Color.BLACK);
        footer = new JLabel("<html><font size=+2 color=#835CBC>Ctrl + right-click to change mouth location<br>Right-click to change eye location</font></html>");
        footerPanel.add(footer);
        add(footerPanel, BorderLayout.SOUTH);
    }

    //method that gets user input to determine how many smileys to create
    //a while loop is used, and input validation is used to ensure an integer > 0
    public void getNumSmileys(){
        while(numSmileys < 1) {
            try {
                numSmileys = Integer.parseInt(JOptionPane.showInputDialog(this, "How many smileys would you like to control?"));
            } catch (NumberFormatException e) {
                final JOptionPane pane = new JOptionPane("Please enter a valid number");
                final JDialog d = pane.createDialog("Invalid response");
                d.setLocationRelativeTo(null);
                d.setVisible(true);
            }
        }
        display = new JPanel[numSmileys];
        smiley = new Smiley[numSmileys];
        control = new SmileyController[numSmileys];
    }

    //method that creates display panels using an array traversal
    public void setUpDisplay() {
        for(int i = 0; i<numSmileys; i++) {
            display[i] = new JPanel(new BorderLayout());
            display[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
        }

        //call methods that set up the smiley and control panels
        setUpSmileys();
        setupControls();
        setupSmileyPanel();
    }

    //method that creates instances of the smiley class using an array traversal
    public void setUpSmileys() {
        for(int i = 0; i<numSmileys; i++) {
            smiley[i] = new Smiley();
            smiley[i].setPreferredSize(new Dimension(250, 250));
        }
    }

    //method that creates instances of the SmileyController class using an array traversal
    public void setupControls() {
        for(int i = 0; i<numSmileys; i++) {
            control[i] = new SmileyController(smiley[i]);
        }
    }

    public void setupSmileyPanel(){
        //add smiley panels and control panels to display panels using a parallel array traversal
        for(int i = 0; i<numSmileys; i++) {
            display[i].add(smiley[i], BorderLayout.CENTER);
            display[i].add(control[i], BorderLayout.SOUTH);
        }
    }

    public void setupContainer(){
        //create a panel to contain the display panels
        displayContainer = new JPanel(new FlowLayout());
        displayContainer.setBackground(bgColor);

        //add display panels to the display container panel using an array traversal
        for(int i = 0; i<numSmileys; i++) {
            displayContainer.add(display[i]);
        }

        //add the display container to the center of the border layout
        add(displayContainer, BorderLayout.CENTER);
    }
}