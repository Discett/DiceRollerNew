package luongr.diceroller.Dialogs.Options.model;

import luongr.diceroller.Dice;

/**
 * Created by Luong Randy on 12/22/2017.
 */

public class DialogOptionsInteractor {
    Dice dice;

    public DialogOptionsInteractor() {
        dice = Dice.getInstance();
    }

    public void onPresetSelection(int x) {
        dice.setDice(x);
    }
}
