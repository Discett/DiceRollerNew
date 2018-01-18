package luongr.diceroller.Multiplayer.fragment.MultiplayerHostRollFragment.presenter;

import java.util.List;

import luongr.diceroller.Multiplayer.fragment.MultiplayerHostRollFragment.model.MultiplayerHostRollFragmentInteractor;
import luongr.diceroller.Selection;

/**
 * Created by Luong Randy on 1/18/2018.
 */

public class MultiplayerHostRollFragmentPresenter implements IMultiplayerHostRollFragmentPresenter {
    MultiplayerHostRollFragmentInteractor interactor;
    public MultiplayerHostRollFragmentPresenter(MultiplayerHostRollFragmentInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public List<Selection> getSelectionList() {
        return interactor.getSelectionList();
    }

    @Override
    public void addSelection(String s) {
        interactor.addSelection(s);
    }
}
