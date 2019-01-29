/**
 * Final Project
 * @author Matt Butler
 * @version Spring 2018
 * CSci 1130-91
 */

public class Dice{

    private final int NUM_DICE = 5, NUM_FACES = 6;
    int rollsRemaining = 3, dieValue;
    int currentValue[] = new int[NUM_DICE];
    boolean isHeld[] = new boolean[NUM_DICE];
    boolean rollAgain = true;
    boolean enteredScore = false;

    public Dice(){


    }

    public void startNewTurn(){
        enteredScore = true;
        rollsRemaining = 3;
        rollAgain = true;
        for(int i = 0; i<NUM_DICE; i++){ //this ensures that all currentValue will update on the first updateDiceValue
            releaseDiceHeld(i);
        }
    }

    public void setDiceHeld(int dieNum){
        isHeld[dieNum]=!isHeld[dieNum];
    }

    public void releaseDiceHeld(int dieNum){
        isHeld[dieNum]= false;
    }

    //method that updates the currentValue value and counts roll remaining
    public void updateDiceValue(){
            for (int i = 0; i < NUM_DICE; i++) {
                roll();
                if (!isHeld[i]) { //parallel arrays ensure only currentValue that are not held get updated values
                    currentValue[i] = dieValue;
                }
                System.out.println(currentValue[i]);
            }
    }

    public void updateRollsRemaining(){
        rollsRemaining--;
    }

    //generates a random number that will be used as the face value for the currentValue
    public int roll() {
        dieValue = (int) ((Math.random() * NUM_FACES) + 1);
        return dieValue;
    }

    public void endTurn(){
        rollAgain = false;
        rollsRemaining = 0;
    }

    public int[] getCurrentValue() {
        return currentValue;
    }
}
