/**
 * Final Project
 * @author Matt Butler
 * @version Spring 2018
 * CSci 1130-91
 */

import javax.swing.*;
import java.awt.*;

public class GameView extends JApplet{
        JPanel titlePanel;
    JLabel title;

    DiceControl dControl;
    Dice dice;
    ScoreSheet sheet;
    ScoreControl sControl;


    public void init(){
        setupApplet(); //this is probably redundant but I wanted init to be super clean
        setupTitle();
        addPanels();
    }

    public void setupApplet(){
        //set BGColor, border layout, create instances of model and dControl
        dice = new Dice();
        dControl = new DiceControl(dice, sheet);
        sheet = new ScoreSheet(dice);
        sControl = new ScoreControl(sheet, dice);
        getContentPane().setBackground(new Color(241, 241, 241));
        setLayout(new BorderLayout());
    }


    public void setupTitle(){
        //set up the title panel
        titlePanel = new JPanel(new FlowLayout());
        title = new JLabel("<html><font size=+3 color=red>Yee-Hoo! America's Favorite Dice Game!</font></html>");
        titlePanel.setBackground(Color.DARK_GRAY);
        titlePanel.add(title);
    }

    public void addPanels(){
        add(titlePanel, BorderLayout.NORTH);
        add(sheet, BorderLayout.EAST);
        add(dControl, BorderLayout.SOUTH);
    }





}

