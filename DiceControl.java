/**
 * Final Project
 * @author Matt Butler
 * @version Spring 2018
 * CSci 1130-91
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiceControl extends JPanel implements ActionListener {

    private final int NUM_DICE = 5;
    JButton[] holdButton = new JButton[NUM_DICE];
    ScoreSheet sheet;
    Dice dice;
    JPanel controlPanel;
    JButton rollButton, confirmButton, endTurnButton, quitButton;


    public DiceControl(Dice d, ScoreSheet s){
        sheet = s;
        dice = d;
        setupControls();
        registerListeners();
        setupControlPanel();

        int currentVal=dice.getCurrentValue()[0];
        dice.getCurrentValue()[0]=6;
    }




    //method that defines controls
    public void setupControls(){
        rollButton = new JButton("Roll");
        rollButton.setFocusPainted(false);

        for (int i = 0; i <NUM_DICE; i++){
            holdButton[i] = new JButton("Hold Die #" + (i+1));
            holdButton[i].setFocusPainted(false);
        }

        confirmButton = new JButton(("Confirm Score"));
        confirmButton.setFocusPainted(false);

        endTurnButton = new JButton("End Turn");
        endTurnButton.setFocusPainted(false);

        quitButton = new JButton("Quit");
        quitButton.setFocusPainted(false);
    }

    //method that adds controls to listeners
    public void registerListeners(){
        rollButton.addActionListener(this);
        for(int i = 0; i<NUM_DICE; i++){
            holdButton[i].addActionListener(this);
        }
        confirmButton.addActionListener(this);
        endTurnButton.addActionListener(this);
        quitButton.addActionListener(this);
    }

    //method for dControl panel
    public void setupControlPanel(){
        controlPanel = new JPanel(new FlowLayout());

        controlPanel.add(rollButton);
        for(int i =0; i<NUM_DICE; i++){
            controlPanel.add(holdButton[i]);
        }
        controlPanel.add(confirmButton);
        controlPanel.add(endTurnButton);
        controlPanel.add(quitButton);

        add(controlPanel, BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //quit button
        if(e.getSource() == quitButton){ //confirm the user truly intends to exit the applet
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit Game?", JOptionPane.YES_NO_OPTION);
            if (response == 0) {
                System.exit(0);
            }
        }

        //hold button
        for(int i = 0; i<NUM_DICE; i++){
            if(e.getSource() == holdButton[i]) {
                dice.setDiceHeld(i);
            }
        }

        //confirm score
        if(e.getSource() == confirmButton){
            if(dice.enteredScore){
                rollButton.setEnabled(true);
            }   else{
                    final JOptionPane pane = new JOptionPane("Please Enter A Score First");
                    final JDialog d = pane.createDialog("Enter Score");
                    d.setLocationRelativeTo(null);
                    d.setVisible(true);
            }
        }

        //end turn button
        if(e.getSource() == endTurnButton){
            rollButton.setEnabled(false);
            dice.endTurn();
        }

        //roll button
        if(e.getSource() == rollButton){
            dice.enteredScore = false;
            dice.updateDiceValue();
            dice.updateRollsRemaining();
            if(dice.rollsRemaining > 0){
                rollButton.setEnabled(true);
            } else{
                rollButton.setEnabled(false);
            }
        }

    } //end action event
} //end class



