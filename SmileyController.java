import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SmileyController extends JPanel implements ActionListener, ItemListener, MouseListener, KeyListener {

    //declare variables
    private int SIZE_CHANGE = 10;
    Smiley smiley;

    JPanel controlPanel;

    JButton randomize;
    JRadioButton happy, sad;
    JCheckBox weird;

    ButtonGroup happyGroup;

    boolean ctrl; //used to keep track of whether ctrl is being pressed to allow combined key+mouse events

    //create constructor that calls on a method to set up controls
    public SmileyController(Smiley s) {
        setLayout(new BorderLayout());
        smiley = s;

        setUpControls();
        addListeners();
    }

    public void setUpControls() {
        //set look of control panel
        controlPanel = new JPanel(new FlowLayout());
        controlPanel.setBackground(Color.BLACK);

        //set up buttons
        randomize = new JButton("<html><font color=#835CBC>Randomize Colors!</font></html>");
        randomize.setFocusPainted(false);
        randomize.setBackground(Color.WHITE);

        //set up checkbox
        weird = new JCheckBox("<html><font color=#835CBC>Make It Weird</font></html>");
        weird.setBackground(Color.BLACK);

        //setup radios
        happy = new JRadioButton("<html><font color=#835CBC>Happy :)</font></html>");
        happy.setBackground(Color.BLACK);
        sad = new JRadioButton("<html><font color=#835CBC>Sad :(</font></html>");
        sad.setBackground(Color.BLACK);

        //add radios to group
        happyGroup = new ButtonGroup();
        happyGroup.add(happy);
        happyGroup.add(sad);

        //add control buttons to panel, whitespace between button type
        controlPanel.add(weird); //checkbox

        controlPanel.add(randomize); //jbutton

        controlPanel.add(happy); //radios
        controlPanel.add(sad);

        //add control panel to smiley panel
        add(controlPanel, BorderLayout.SOUTH);
    }

    //add listeners to appropriate elements
    public void addListeners(){
        randomize.addActionListener(this);
        randomize.addKeyListener(this);

        weird.addItemListener(this);
        weird.addKeyListener(this);

        happy.addItemListener(this);
        happy.addKeyListener(this);

        sad.addItemListener(this);
        sad.addKeyListener(this);

        controlPanel.setFocusable(true);
        controlPanel.addMouseListener(this);
        controlPanel.addKeyListener(this);

        smiley.addMouseListener(this);
        smiley.addKeyListener(this);
    }


    //--------------------------------------------------------------action events---------------------------------------------------------------------------

    @Override
    public void actionPerformed(ActionEvent e) {
        //randomize colors
        if (e.getSource() == randomize) {
            smiley.setRandomColors();
            smiley.repaint();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        //if Make It Weird is pressed, make the smiley weird by tripping a Boolean
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getSource() == weird) {
                smiley.isWeird = true;
                smiley.repaint();
            }

            //make the smiley happy
            if (e.getSource() == happy){
                smiley.isHappy = true;
                smiley.makeHappy();
                smiley.repaint();
            }

            //make the smiley a frowny
            if (e.getSource() == sad){
                smiley.isHappy = false;
                smiley.makeSad();
                smiley.repaint();
            }
        } //closes Selected

        //if Make It Weird becomes unchecked, trip Boolean to false and force a repaint
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            if (e.getSource() == weird) {
                smiley.isWeird = false;
                smiley.repaint();
            }
        } //closes Deselected
    } //closes itemStateChanged


    //--------------------------------------------------------------mouse events---------------------------------------------------------------------------

    @Override
    public void mouseClicked(MouseEvent e) {
        //this makes it so when you click a smiley, it becomes "in focus" and will be the one that the listeners make changes to
        controlPanel.requestFocus();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //determine what happens when you right-click
        if(e.getButton() == MouseEvent.BUTTON3){    //nested if/else to ensure different actions depending on whether or not ctrl is pressed
            if(!ctrl){                              //if ctrl isn't being pressed
            smiley.changeEyes(e.getX(), e.getY()); //change the eye location
            smiley.repaint();
        } else{                                     //else, meaning if ctrl is being pressed
                smiley.changeMouth(e.getX(), e.getY() - smiley.mouthHeight / 2);    //change mouth location
                smiley.repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    //--------------------------------------------------------------keyboard events---------------------------------------------------------------------------
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key=e.getKeyCode(); //this helps improve code readability

        //make smiley bigger if either + sign is pressed
        if (key == KeyEvent.VK_ADD || key == KeyEvent.VK_PLUS) {
            smiley.changeSize(SIZE_CHANGE);
            smiley.repaint();
        }
        //make smiley smaller if either - sign is pressed
        if (key == KeyEvent.VK_MINUS || key == KeyEvent.VK_SUBTRACT){
            smiley.changeSize(-SIZE_CHANGE);
            smiley.repaint();
        }

        if (key == KeyEvent.VK_CONTROL){ //boolean switched to true if ctrl is being pressed
            ctrl = true;                //this allows ctrl + click
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key=e.getKeyCode();

        if (key == KeyEvent.VK_CONTROL){ //boolean switched to false when ctrl is released
            ctrl = false;
        }
    }
} //closes class
