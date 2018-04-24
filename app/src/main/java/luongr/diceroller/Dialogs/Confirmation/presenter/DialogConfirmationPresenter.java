package luongr.diceroller.Dialogs.Confirmation.presenter;

import luongr.diceroller.Dialogs.Confirmation.model.DialogConfirmationInteractor;

/**
 * Created by Luong Randy on 1/18/2018.
 */

public class DialogConfirmationPresenter implements IDialogConfirmationPresenter{
    DialogConfirmationInteractor interactor;

    public DialogConfirmationPresenter(DialogConfirmationInteractor interactor) {
        this.interactor = interactor;
    }

}
