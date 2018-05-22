package luongr.diceroller.Multiplayer.fragment.MultiplayerHostFinalFragment.presenter;

import java.util.List;

import luongr.diceroller.Multiplayer.fragment.MultiplayerHostFinalFragment.interactor.MultiplayerHostFinalInteractor;
import luongr.diceroller.Multiplayer.fragment.MultiplayerHostFinalFragment.view.MultiplayerHostFinalFragment;
import luongr.diceroller.Selection;

/**
 * Created by Luong on 5/14/2018.
 */

public class MultiplayerHostFinalFragmentPresenter implements IMultiplayerHostFinalFragmentPresenter {

    MultiplayerHostFinalInteractor interactor;
    MultiplayerHostFinalFragment view;


    public MultiplayerHostFinalFragmentPresenter(MultiplayerHostFinalFragment view, MultiplayerHostFinalInteractor interactor) {
        this.interactor = interactor;
        this.view = view;
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

    @Override
    public void onRandomSelection() {
        interactor.onRandomSelection(new MultiplayerHostFinalInteractor.Callback() {
            @Override
            public void onRandomSelected(String selection) {
                view.onSendToJoin(selection);
                view.onSetSelectionWinner(selection);
            }
        });
    }
}
