package luongr.diceroller.Multiplayer.fragment.MultiplayerHostRollFragment.presenter;

import java.util.List;

import luongr.diceroller.Multiplayer.fragment.MultiplayerHostFragment.view.MultiplayerHostFragment;
import luongr.diceroller.Multiplayer.fragment.MultiplayerHostRollFragment.model.MultiplayerHostRollFragmentInteractor;
import luongr.diceroller.Multiplayer.fragment.MultiplayerHostRollFragment.view.MultiplayerHostRollFragment;
import luongr.diceroller.Selection;

/**
 * Created by Luong Randy on 1/18/2018.
 */

public class MultiplayerHostRollFragmentPresenter implements IMultiplayerHostRollFragmentPresenter {
    MultiplayerHostRollFragmentInteractor interactor;
    MultiplayerHostRollFragment view;
    public MultiplayerHostRollFragmentPresenter(MultiplayerHostRollFragment view, MultiplayerHostRollFragmentInteractor interactor) {
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public List<Selection> getSelectionList() {
        return interactor.getSelectionList();
    }

    @Override
    public void addSelection(String s) {
        interactor.addSelection(s);
    }

    @Override
    public void parseMessageList(String s) {
        interactor.parseMessageList(s);
    }

    @Override
    public void checkMaxSelections() {
        interactor.checkMaxSelections(new MultiplayerHostRollFragmentInteractor.Callback() {
            @Override
            public void onShowAddSelection() {
                view.showAddSelection();
            }

            @Override
            public void onHideAddSelection() {
                view.hideAddSelection();
            }
        });
    }

    @Override
    public byte[] diceInfo() {
        return interactor.diceInfo();
    }

    @Override
    public String getDiceInfoHeader() {
        return interactor.getDiceInfoHeader();
    }
}
