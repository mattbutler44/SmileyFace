/**
 * Final Project
 * @author Matt Butler
 * @version Spring 2018
 * CSci 1130-91
 */

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;


public class DrawDice extends JPanel {

    int dieWidth, dieHeight, dotWidth, dotHeight, dotX, dotY;
    private final Color dieColor = Color.WHITE, dotColor = Color.BLACK;
    Dice dice;
    Image[] diceImage;
    String[] imageNames = {"die1.jpg", "die2.jpg", "die3.jpg", "die4.jpg", "die5.jpg", "die6.jpg"};
    private final int NUM_PICS = imageNames.length;
    int currentValue = dice.getCurrentValue()[0];

    public DrawDice() {
        dice = new Dice();
        setLayout(new FlowLayout());
    }

    public void drawDice(int faceValue) {
        currentValue = faceValue;
    }

    public void paint(Graphics g) {
        super.paint(g);

        //set up anti-aliasing
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        dieWidth = this.getWidth() / 10;
        dieHeight = this.getHeight() / 10;
        dotHeight = dieHeight / 5;
        dotWidth = dieWidth / 5;



    }
}

