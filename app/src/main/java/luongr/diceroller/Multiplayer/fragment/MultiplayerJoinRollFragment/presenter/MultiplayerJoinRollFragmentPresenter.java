package luongr.diceroller.Multiplayer.fragment.MultiplayerJoinRollFragment.presenter;

import java.util.ArrayList;

import luongr.diceroller.Multiplayer.fragment.MultiplayerJoinRollFragment.model.MultiplayerJoinRollFragmentInteractor;
import luongr.diceroller.Multiplayer.fragment.MultiplayerJoinRollFragment.view.MultiplayerJoinRollFragment;
import luongr.diceroller.Selection;

/**
 * Created by Luong on 3/30/2018.
 */

public class MultiplayerJoinRollFragmentPresenter {
    MultiplayerJoinRollFragmentInteractor interactor;
    MultiplayerJoinRollFragment view;
    public MultiplayerJoinRollFragmentPresenter(MultiplayerJoinRollFragment view, MultiplayerJoinRollFragmentInteractor interactor) {
        this.interactor = interactor;
        this.view = view;
    }

    public void parseMessage(String readMessage) {
        interactor.parseMessage(readMessage, new MultiplayerJoinRollFragmentInteractor.Callback() {
            @Override
            public void onDisplayInfo(String info) {
                view.joinRollDisplayInfo(info);
                view.hideLoadingDialog();
            }

            @Override
            public void onHostReady() {
                view.hideLoadingDialog();
                view.onWriteToHost();
            }

            @Override
            public void onHideSelection() {
                //do nothing
            }

            @Override
            public void onShowSelection() {
                //do nothing
            }
        });
    }

    public ArrayList<Selection> getListOfSelection() {
        return interactor.getListOfSelection();
    }

    public void addSelectionToList(String selection) {
        interactor.addSelectionToList(selection);
    }

    public void checkMaxSelections() {
        interactor.checkMaxSelections(new MultiplayerJoinRollFragmentInteractor.Callback() {
            @Override
            public void onDisplayInfo(String info) {
                //do nothing
            }

            @Override
            public void onHostReady() {
                //do nothing
            }

            @Override
            public void onHideSelection() {
                view.hideEdtSelection();
            }

            @Override
            public void onShowSelection() {
                view.showEdtSelection();
            }
        });
    }

    public byte[] getByteArray() {
        return interactor.getByteArray();
    }
}
