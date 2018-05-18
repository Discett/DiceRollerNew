package luongr.diceroller.Multiplayer.fragment.MultiplayerHostFinalFragment.presenter;

import java.util.List;

import luongr.diceroller.Multiplayer.fragment.MultiplayerHostFinalFragment.interactor.MultiplayerHostFinalInteractor;
import luongr.diceroller.Selection;

/**
 * Created by Luong on 5/14/2018.
 */

public class MultiplayerHostFinalFragmentPresenter implements IMultiplayerHostFinalFragmentPresenter {

    MultiplayerHostFinalInteractor interactor;


    public MultiplayerHostFinalFragmentPresenter(MultiplayerHostFinalInteractor interactor) {
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

    @Override
    public void parseMessageList(String readMessage) {
        interactor.parseMessageList(readMessage);
    }
}
