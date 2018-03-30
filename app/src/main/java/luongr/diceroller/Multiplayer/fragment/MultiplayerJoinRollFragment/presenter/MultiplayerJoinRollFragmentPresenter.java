package luongr.diceroller.Multiplayer.fragment.MultiplayerJoinRollFragment.presenter;

import android.content.Context;

import luongr.diceroller.Multiplayer.fragment.MultiplayerJoinRollFragment.model.MultiplayerJoinRollFragmentInteractor;
import luongr.diceroller.Multiplayer.fragment.MultiplayerJoinRollFragment.view.MultiplayerJoinRollFragment;

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
            }
        });
    }
}
