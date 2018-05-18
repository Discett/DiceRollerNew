package luongr.diceroller.Multiplayer.fragment.MultiplayerHostFinalFragment.presenter;

import java.util.List;

import luongr.diceroller.Selection;

/**
 * Created by Luong on 5/14/2018.
 */

public interface IMultiplayerHostFinalFragmentPresenter {
    void setSelectionList(List<Selection> selectionList);
    List<Selection> getSelectionList();

    void parseMessageList(String readMessage);
}
