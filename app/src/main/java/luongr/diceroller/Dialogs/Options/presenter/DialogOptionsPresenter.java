package luongr.diceroller.Dialogs.Options.presenter;

import luongr.diceroller.Dialogs.Options.model.DialogOptionsInteractor;
import luongr.diceroller.Dialogs.Options.view.IDialogOptions;

/**
 * Created by Luong Randy on 12/22/2017.
 */

public class DialogOptionsPresenter implements IDialogOptionsPresenter{
    DialogOptionsInteractor interactor;
    IDialogOptions view;

    public DialogOptionsPresenter(IDialogOptions view, DialogOptionsInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onPresetSelection(int x) {
        interactor.onPresetSelection(x);
    }

    @Override
    public void setMaxSelection(int i) {
        interactor.onMaxSelection(i);
    }
}
