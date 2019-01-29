import javax.swing.*;
import java.awt.*;

public class Smiley extends JPanel {

    //declare some variables
    int eyeX, eyeY, eyeHeight, eyeWidth, mouthX, mouthY, mouthAngle, mouthArcAngle, mouthWidth, mouthHeight;
    private int faceX, faceY, faceWidth, faceHeight;
    private int eyeGap;
    private int pupilX, pupilY, pupilWidth, pupilHeight;
    private final int MOUTH_ANGLE = 180;
    private Color eyeColor, pupilColor, mouthColor, faceColor;
    Boolean isWeird = false, isHappy = true;
    Graphics2D g2d;


    public Smiley() {
        setBackground(new Color(27, 5, 61));

        //call method that sets default values for smiley
        setUpSmiley();
    }

    public void paintComponent(Graphics g) {
        //call paint and set anti-aliasing
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //draw the smiley one way if it should be weird
        if (isWeird){
            drawWeird(g2d);
            //draw the smiley another way if it should be normal
        } else {
            drawNormal(g2d);
        }
    }

    //randomize colors
    public Color randomizeColors() {
        int r = (int) (Math.random() * 256);
        int gr = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        Color eyeColor = new Color(r, gr, b);
        return eyeColor;
    }

    //assign the random color to each facial component
    public void setRandomColors() {
        eyeColor = randomizeColors();
        mouthColor = randomizeColors();
        faceColor = randomizeColors();
        pupilColor = randomizeColors();
    }

    //method to change the facial component sizes
    public void changeSize(int size) {
        faceWidth += size;
        faceHeight += size;

        //ensure eyes stay proportionate to face
        setEyes();

        //ensure pupils stay proportionate to eyes
        setPupils();

        //ensure mouth stays proportionate to face
        setMouth();
    }

    //method that changes the mouth to look happy
    public void makeHappy() {
        mouthArcAngle = -MOUTH_ANGLE;
    }

    //change the mouth to look sad
    public void makeSad() {
        mouthArcAngle = MOUTH_ANGLE;
    }

    //how to draw the weird face
    public void drawWeird(Graphics2D g){
        //draw face
        g.setColor(faceColor);
        g.fillRect(faceX, faceY, faceWidth, faceHeight);

        //draw eyes as tiny mouths
        g.setColor(mouthColor);
        g.fillArc(eyeX, eyeY, eyeWidth, eyeHeight, mouthAngle, mouthArcAngle);
        g.fillArc(eyeX + eyeGap, eyeY, eyeWidth, eyeHeight, mouthAngle, mouthArcAngle);

        //draw mouth as giant eye
        g.setColor(eyeColor);
        g.fillOval(mouthX, (int) (mouthY * 0.9), mouthWidth, mouthHeight); //shifted mouthY up a bit to compensate for oval being taller than arc
    }

    //draw a normal smiley
    public void drawNormal(Graphics2D g){
        //draw face
        g.setColor(faceColor);
        g.fillOval(faceX, faceY, faceWidth, faceHeight);

        //draw eyes
        g.setColor(eyeColor);
        g.fillOval(eyeX, eyeY, eyeWidth, eyeHeight);
        g.fillOval(eyeX + eyeGap, eyeY, eyeWidth, eyeHeight);

        //draw pupils
        g.setColor(pupilColor);
        g.fillOval(pupilX, pupilY, pupilWidth, pupilHeight);
        g.fillOval(pupilX + eyeGap, pupilY, pupilWidth, pupilHeight);

        //draw mouth
        g.setColor(mouthColor);
        g.fillArc(mouthX, mouthY, mouthWidth, mouthHeight, mouthAngle, mouthArcAngle);
    }

    //the following methods just set default values for facial components
    public void setDefaultColors(){
        faceColor = Color.YELLOW;
        eyeColor = Color.BLACK;
        pupilColor = Color.BLUE;
        mouthColor = Color.RED;
    }

    //sets default face size
    public void setDefaultFaceSize(){
        faceX = 100;
        faceY = 0;
        faceWidth = 250;
        faceHeight = 250;
    }

    //sets eyes relative to face to ensure proper scaling when resizing
    public void setEyes(){
        eyeX = faceX + faceWidth / 4;
        eyeY = faceY + faceHeight / 6;
        eyeGap = (int) (faceWidth / 2.5);
        eyeWidth = faceWidth / 10;
        eyeHeight = faceHeight / 10;
    }

    //sets pupils relative to eyes, which are relative to face, to ensure proper scaling when resizing
    public void setPupils() {
        pupilX = eyeX + eyeWidth / 3;
        pupilY = eyeY + eyeHeight / 3;
        pupilWidth = eyeWidth / 3;
        pupilHeight = eyeHeight / 3;
    }

    //sets mouth relative to face to ensure proper scaling when resizing
    public void setMouth(){
        mouthX = faceX + faceWidth / 6;
        if (isHappy) {
            mouthY = (int) (faceHeight * 0.44);
            mouthArcAngle = -MOUTH_ANGLE;
        } else {
            mouthY = (int) (faceHeight * 0.55);
            mouthArcAngle = MOUTH_ANGLE;
        }
        mouthWidth = (int) (faceWidth * 0.7);
        mouthHeight = faceHeight / 2;
        mouthAngle = 0;
    }

    //changes the eye location based on where user clicks
    public void changeEyes(int x, int y){
        eyeX = x;
        eyeY = y;
        setPupils();
    }

    //changes mouth location based on where user clicks
    public void changeMouth(int x, int y){
        mouthX = x;
        mouthY = y;
    }

    //method that calls methods to get their default values and held all in one spot.. with colors excluded
    public void setUpSmiley(){
        setDefaultColors();
        setDefaultFaceSize();
        setEyes();
        setPupils();
        setMouth();
    }
}




