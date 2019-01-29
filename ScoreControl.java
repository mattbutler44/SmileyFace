/**
 * Final Project
 * @author Matt Butler
 * @version Spring 2018
 * CSci 1130-91
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreControl implements ActionListener {

    private final int NUM_UPPER_CATEGORIES = 6, NUM_LOWER_CATEGORIES = 8, BONUS = 35, UPPER_GOAL = 63, NUM_FIELDS = 18, SCORE_FIELDS = 2, NUM_DICE = 5;
    ScoreSheet sheet;
    Dice diceModel;
    int matches;
    int die1, die2, die3, die4, die5;
    int match1, match2, match3, match4, match5;


    public ScoreControl(ScoreSheet ss, Dice d) {
        sheet = ss;
        diceModel = d;

    }

    public void countMatches(){
        die1 = diceModel.currentValue[0];
        die2 = diceModel.currentValue[1];
        die3 = diceModel.currentValue[2];
        die4 = diceModel.currentValue[3];
        die5 = diceModel.currentValue[4];
        match1 = 1;
        match2 = 1;
        match3 = 1;
        match4 = 1;
        match5 = 1;

        //8772105511325785 acct #
        //855-652-3446

        //die 1
        if(die1 == die2){
            match1++;
        }
        if(die1 == die3){
            match1++;
        }
        if(die1 == die4){
            match1++;
        }
        if(die1 == die5){
            match1++;
        }
        // die 2
        if(die2 == die1){
            match2++;
        }
        if(die2 == die3){
            match2++;
        }
        if(die2 == die4){
            match2++;
        }
        if(die2 == die5){
            match2++;
        }
        // die 3
        if(die3 == die1){
            match3++;
        }
        if(die3 == die2){
            match3++;
        }
        if(die3 == die4){
            match3++;
        }
        if(die3 == die5){
            match3++;
        }
        // die 4
        if(die4 == die1){
            match4++;
        }
        if(die4 == die2){
            match4++;
        }
        if(die4 == die3){
            match4++;
        }
        if(die4 == die5){
            match4++;
        }
        // die 5
        if(die5 == die1){
            match5++;
        }
        if(die5 == die2){
            match5++;
        }
        if(die5 == die3){
            match5++;
        }
        if(die5 == die4){
            match5++;
        }
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i<NUM_UPPER_CATEGORIES; i++){
            if(e.getSource() == sheet.upperCategories[i]){
                diceModel.startNewTurn();
            }
        }

        for(int i = 0; i<NUM_LOWER_CATEGORIES; i++){
            if(e.getSource() == sheet.lowerCategories[i]){
                diceModel.startNewTurn();
            }
        }

        if (e.getSource() == sheet.upperCategories[0]) {

            countMatches();
            System.out.println("die1matches: " + String.valueOf(match1));
        }
    }


}
