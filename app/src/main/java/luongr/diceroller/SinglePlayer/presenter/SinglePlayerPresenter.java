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

            @Override
            public void onNoSelection() {

            }

            @Override
            public void onAvailableSelections() {

            }

            @Override
            public void showEdtSelections() {

            }

            @Override
            public void hideEdtSelections() {

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

            @Override
            public void onNoSelection() {

            }

            @Override
            public void onAvailableSelections() {

            }

            @Override
            public void showEdtSelections() {

            }

            @Override
            public void hideEdtSelections() {

            }
        });
    }

    @Override
    public void checkSelection() {
        interactor.checkSelection(new SinglePlayerInteractor.Callback() {
            @Override
            public void onRolled(int roll) {

            }

            @Override
            public void onSettings() {

            }

            @Override
            public void onNoSelection() {
                view.hideSelections();
            }

            @Override
            public void onAvailableSelections() {
                view.showSelections();
            }

            @Override
            public void showEdtSelections() {

            }

            @Override
            public void hideEdtSelections() {

            }
        });
    }

    @Override
    public void checkListEntries() {
        interactor.checkListEntries(new SinglePlayerInteractor.Callback() {
            @Override
            public void onRolled(int roll) {

            }

            @Override
            public void onSettings() {

            }

            @Override
            public void onNoSelection() {

            }

            @Override
            public void onAvailableSelections() {

            }

            @Override
            public void showEdtSelections() {
                view.showEdtSelections();
            }

            @Override
            public void hideEdtSelections() {
                view.hideEdtSelections();
            }
        });
    }
}
