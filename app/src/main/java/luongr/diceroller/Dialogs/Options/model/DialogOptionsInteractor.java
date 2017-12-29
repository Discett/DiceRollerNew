package luongr.diceroller.Dialogs.Options.model;

import luongr.diceroller.Dice;

/**
 * Created by Luong Randy on 12/22/2017.
 */

public class DialogOptionsInteractor {
    Dice dice;
    boolean field1 = false;
    boolean field2 = false;
    int intField1 = 0;
    int intField2 = 0;

    public DialogOptionsInteractor() {
        dice = Dice.getInstance();
    }

    public void onPresetSelection(int x) {
        dice.setDice(x);
    }

    public void onMaxSelection(int i) {
        dice.setNumberOfSelections(i);
    }

    public void getDiceInfo(Callback callback) {
        if(dice.getDiceSides() == dice.getDiceSidesRange()){
            callback.onDiceInfo(dice.getNumberOfSelections(),dice.getDiceSides());
        } else {
            callback.onDiceInfo(dice.getNumberOfSelections(),dice.getDiceSides(),dice.getDiceSidesRange());
        }
    }

    public void setUpSeekBar(Callback callback) {
        callback.onSeekBarSetup(dice.getMaxNumberOfSelections());
    }
//TODO: exception handeling for overflow crash
    public void onCustomDiceFieldOne(String value, Callback callback) {
        if(!value.isEmpty() && !Integer.valueOf(value).equals(0)){
            field1 = true;
            intField1 = Integer.valueOf(value);
            if(field2){
                dice.setDiceRange(Integer.valueOf(value),intField2);
                callback.onDiceRangeChange(dice.getDiceSides(),dice.getDiceSidesRange());
            } else {
                dice.setDice(Integer.valueOf(value));
                callback.onDiceSidesChange(dice.getDiceSides());
            }
        } else {
            field1 = false;
            intField1 = 0;
        }
    }

    public void onCustomDiceFieldTwo(String value, Callback callback) {
        if(!value.isEmpty() && !Integer.valueOf(value).equals(0)){
            field2 = true;
            intField2 = Integer.valueOf(value);
            if(field1){
                dice.setDiceRange(Integer.valueOf(value),intField1);
                callback.onDiceRangeChange(dice.getDiceSides(),dice.getDiceSidesRange());
            } else {
                dice.setDice(Integer.valueOf(value));
                callback.onDiceSidesChange(dice.getDiceSides());
            }
        } else {
            field2 = false;
            intField2 = 0;
        }
    }

    public interface Callback{
        void onDiceInfo(int selections, int diceSides);
        void onDiceInfo(int selections, int diceSides, int diceRange);
        void onSeekBarSetup(int maxSelections);
        void onDiceSidesChange(int sides);
        void onDiceRangeChange(int sides, int range);
    }
}
