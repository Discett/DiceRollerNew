package luongr.diceroller;

import java.util.Random;

/**
 * Created by Luong Randy on 12/22/2017.
 */

public class Dice implements IDice {
    private static volatile Dice diceInstance = new Dice();
    private int diceSides = 6;
    private int diceSidesRange = 6;
    private int numberOfSelections = 0;

    private Dice() {
    }

    public int getDiceSides() {
        return diceSides;
    }

    public int getDiceSidesRange() {
        return diceSidesRange;
    }

    public int getNumberOfSelections() {
        return numberOfSelections;
    }

    public static Dice getInstance(){
        return diceInstance;
    }

    @Override
    public void setDice(int x) {
        this.diceSides = x;
        this.diceSidesRange = x;
    }

    @Override
    public void setDiceRange(int x, int y) {
        if(x < y){
            this.diceSides = y;
            this.diceSidesRange = x;
        } else {
            this.diceSides = x;
            this.diceSidesRange = y;
        }
    }

    @Override
    public int rollDice() {
        Random random = new Random();
        if(diceSides != diceSidesRange){
            return random.nextInt((diceSides + 1) - diceSidesRange) + diceSidesRange;
        } else {
            return random.nextInt((diceSides + 1) - 1) + 1;
        }
    }

    @Override
    public void setNumberOfSelections(int x) {
        this.numberOfSelections = x;
    }
}
