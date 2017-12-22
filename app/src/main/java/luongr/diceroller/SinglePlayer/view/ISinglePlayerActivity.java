package luongr.diceroller.SinglePlayer.view;

/**
 * Created by Luong Randy on 12/22/2017.
 */

public interface ISinglePlayerActivity {
    void showSelections();
    void hideSelections();
    void setTxtRoll(int roll);

    void showSettingsDialog();
}
