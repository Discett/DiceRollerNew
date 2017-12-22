package luongr.diceroller.SinglePlayer.model;

import luongr.diceroller.Dice;

/**
 * Created by Luong Randy on 12/22/2017.
 */

public class SinglePlayerInteractor {
    Dice dice;

    public SinglePlayerInteractor() {
        dice = Dice.getInstance();
    }

    public void onRoll(Callback callback) {
        callback.onRolled(dice.rollDice());
    }

    public void onSettings(Callback callback) {
        callback.onSettings();
    }

    public interface Callback{
        void onRolled(int roll);

        void onSettings();
    }
}
