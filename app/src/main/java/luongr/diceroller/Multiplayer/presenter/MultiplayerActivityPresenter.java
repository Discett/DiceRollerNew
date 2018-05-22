package luongr.diceroller.Multiplayer.presenter;

import java.util.List;

import luongr.diceroller.Multiplayer.interactor.MultiplayerActivityInteractor;
import luongr.diceroller.Selection;

/**
 * Created by Luong on 5/14/2018.
 */

public class MultiplayerActivityPresenter implements IMultiplayerActivityPresenter {

    MultiplayerActivityInteractor interactor;

    public MultiplayerActivityPresenter(MultiplayerActivityInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void setSelectionList(List<Selection> selectionList) {
        interactor.setSelectionList(selectionList);
    }

    @Override
    public List<Selection> getSelectionList() {
        return interactor.getSelectionList();
    }
}
