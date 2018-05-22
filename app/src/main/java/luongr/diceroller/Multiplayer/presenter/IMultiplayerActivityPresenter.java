package luongr.diceroller.Multiplayer.presenter;

import java.util.List;

import luongr.diceroller.Selection;

/**
 * Created by Luong on 5/14/2018.
 */

public interface IMultiplayerActivityPresenter {
    void setSelectionList(List<Selection> selectionList);
    List<Selection> getSelectionList();
}
