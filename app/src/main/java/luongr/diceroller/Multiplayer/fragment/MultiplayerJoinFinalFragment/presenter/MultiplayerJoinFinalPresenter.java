package luongr.diceroller.Multiplayer.fragment.MultiplayerJoinFinalFragment.presenter;

import luongr.diceroller.Multiplayer.fragment.MultiplayerJoinFinalFragment.view.MultiplayerJoinFinalFragment;

/**
 * Created by Luong on 5/21/2018.
 */

public class MultiplayerJoinFinalPresenter {
    MultiplayerJoinFinalFragment view;

    public MultiplayerJoinFinalPresenter(MultiplayerJoinFinalFragment view) {
        this.view = view;
    }

    public void onGetSelectionWinner(String readMessage) {
        view.setSelectionWinner(readMessage);
    }
}
