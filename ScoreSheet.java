/**
 * Final Project
 * @author Matt Butler
 * @version Spring 2018
 * CSci 1130-91
 */

import javax.swing.*;
import java.awt.*;


public class ScoreSheet extends JPanel {

    private final int NUM_UPPER_CATEGORIES = 6, NUM_LOWER_CATEGORIES = 8, BONUS = 35, UPPER_GOAL = 63, NUM_FIELDS = 18, SCORE_FIELDS = 2;
    int upperBonus, upperTotal, lowerTotal, grandTotal;
    JPanel leftPanel, rightPanel, sheetPanel;

    JButton[] upperCategories = new JButton[NUM_UPPER_CATEGORIES];
    String[] upperNames = {"Aces", "Twos", "Threes", "Fours", "Fives", "Sixes"};
    JLabel[] upperScores = new JLabel[SCORE_FIELDS];

    JButton[] lowerCategories =  new JButton[NUM_LOWER_CATEGORIES];
    String[] lowerNames = {"Three Of A Kind", "Four Of A Kind", "Full House", "Small Straight", "Large Straight", "Yee-Hoo", "Yee-Hoo Bonus", "Chance"};
    JLabel[] lowerScores = new JLabel[SCORE_FIELDS];

    JTextField[] scoreFields = new JTextField[NUM_FIELDS];
    GridLayout grid;

    ScoreControl sc;
    Dice dice;



    public ScoreSheet(Dice d){
        dice = d;
        sc = new ScoreControl(this, dice);
        grid = new GridLayout(0, 1, 10, 3);
        setupUpperSection();
        setupLowerSection();
        setupFields();
        setupScorePanel();
        setupScoreSheet();
    }

    public void setupUpperSection(){
        setupUpperButtons();
        setupUpperScores();
    }

    public void setupLowerSection(){
        setupLowerButtons();
        setupLowerScores();
    }

    public void setupUpperButtons(){
        for(int i = 0; i<NUM_UPPER_CATEGORIES; i++){
            upperCategories[i] = new JButton(upperNames[i]);
            upperCategories[i].addActionListener(sc);
            upperCategories[i].setFocusPainted(false);
        }
    }

    public void setupUpperScores(){
        upperScores[0] = new JLabel("<html><u>Upper Section Bonus</u></html>");
        upperScores[1] = new JLabel("<html><u>Upper Section Total</u></html>");
    }

    public void setupFields(){
        for(int i = 0; i<NUM_FIELDS; i++) {
            scoreFields[i] = new JTextField(8);
            scoreFields[i].setEditable(false);
            scoreFields[i].setBackground(Color.WHITE);
            scoreFields[i].setHorizontalAlignment(JTextField.CENTER);
        }
        scoreFields[6].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        scoreFields[7].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        scoreFields[16].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        scoreFields[17].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
    }

    public void setupLowerButtons() {
        for (int i = 0; i < NUM_LOWER_CATEGORIES; i++) {
            lowerCategories[i] = new JButton(lowerNames[i]);
            lowerCategories[i].addActionListener(sc);
            lowerCategories[i].setFocusPainted(false);
            lowerCategories[i].setEnabled(false);
        }
    }

    public void setupLowerScores(){
        lowerScores[0] = new JLabel("<html><u>Lower Section Total</u></html>");
        lowerScores[1] = new JLabel("<html><u>Grand Total</u></html>");
    }

    public void setupScorePanel() {
        leftPanel = new JPanel(grid);
        rightPanel = new JPanel(grid);
        for (int i = 0; i < NUM_UPPER_CATEGORIES; i++) {
            leftPanel.add(upperCategories[i]);
        }
        for(int i = 0; i<SCORE_FIELDS; i++) {
            leftPanel.add(upperScores[i]);
        }
        for (int i = 0; i < NUM_LOWER_CATEGORIES; i++) {
            leftPanel.add(lowerCategories[i]);
        }
        for (int i = 0; i < SCORE_FIELDS; i++) {
            leftPanel.add(lowerScores[i]);
        }
        for (int i = 0; i < NUM_FIELDS; i++) {
            rightPanel.add(scoreFields[i]);
        }
    }

    public void setupScoreSheet(){
        sheetPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("<html><font size=+2 color=red>Yee-Hoo Scores</font></html>");

        sheetPanel.add(title, BorderLayout.NORTH);
        sheetPanel.add(leftPanel, BorderLayout.WEST);
        sheetPanel.add(rightPanel, BorderLayout.EAST);
        add(sheetPanel, grid);
    }

    public void updateField(String text){
        scoreFields[0].setText(text);
    }

}
