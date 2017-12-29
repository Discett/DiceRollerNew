package luongr.diceroller.Dialogs.Options.presenter;

/**
 * Created by Luong Randy on 12/22/2017.
 */

public interface IDialogOptionsPresenter {
    void onPresetSelection(int x);

    void setMaxSelection(int i);

    void getDiceInfo();

    void setUpSeekBar();

    void onCustomDiceFieldOne(String value);

    void onCustomDiceFieldTwo(String value);
}
