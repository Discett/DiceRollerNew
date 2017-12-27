package luongr.diceroller.SinglePlayer.presenter;

import java.util.List;

import luongr.diceroller.Selection;

/**
 * Created by Luong Randy on 12/22/2017.
 */

public interface ISinglePlayerPresenter {
    void onRoll();

    void onSettings();

    void checkSelection();

    void checkListEntries();

    void onAddSelection(String selection);

    List<Selection> getSelectionList();
}
