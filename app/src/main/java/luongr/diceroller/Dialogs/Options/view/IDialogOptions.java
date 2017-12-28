package luongr.diceroller.Dialogs.Options.view;

/**
 * Created by Luong Randy on 12/22/2017.
 */

public interface IDialogOptions {
    void setDiceInfoRange(int selections, int diceSides, int diceRange);

    void setDiceInfo(int selections, int diceSides);

    void setUpSeekBar(int maxSelections);

    void onDiceRangeChange(int sides, int range);

    void onDiceSidesChange(int sides);
}
