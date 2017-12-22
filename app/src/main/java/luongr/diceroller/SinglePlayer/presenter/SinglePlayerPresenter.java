package luongr.diceroller.SinglePlayer.presenter;

import luongr.diceroller.SinglePlayer.model.SinglePlayerInteractor;
import luongr.diceroller.SinglePlayer.view.ISinglePlayerActivity;

/**
 * Created by Luong Randy on 12/22/2017.
 */

public class SinglePlayerPresenter implements ISinglePlayerPresenter{
    SinglePlayerInteractor interactor;
    ISinglePlayerActivity view;

    public SinglePlayerPresenter(ISinglePlayerActivity view, SinglePlayerInteractor interactor) {
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void onRoll() {
        interactor.onRoll(new SinglePlayerInteractor.Callback() {
            @Override
            public void onRolled(int roll) {
                view.setTxtRoll(roll);
            }

            @Override
            public void onSettings() {

            }
        });
    }

    @Override
    public void onSettings() {
        interactor.onSettings(new SinglePlayerInteractor.Callback() {
            @Override
            public void onRolled(int roll) {

            }

            @Override
            public void onSettings() {
                view.showSettingsDialog();
            }
        });
    }
}
