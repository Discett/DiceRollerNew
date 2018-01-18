package luongr.diceroller.Multiplayer.fragment.MultiplayerHostRollFragment.presenter;

import java.util.List;

import luongr.diceroller.Selection;

/**
 * Created by Luong Randy on 1/18/2018.
 */

public interface IMultiplayerHostRollFragmentPresenter {
    List<Selection> getSelectionList();

    void addSelection(String s);
}
