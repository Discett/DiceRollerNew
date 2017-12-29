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

    @Override
    public void getDiceInfo() {
        interactor.getDiceInfo(new DialogOptionsInteractor.Callback() {
            @Override
            public void onDiceInfo(int selections, int diceSides) {
                view.setDiceInfo(selections,diceSides);
            }

            @Override
            public void onDiceInfo(int selections, int diceSides, int diceRange) {
                view.setDiceInfoRange(selections,diceSides,diceRange);
            }

            @Override
            public void onSeekBarSetup(int maxSelections) {

            }

            @Override
            public void onDiceSidesChange(int sides) {

            }

            @Override
            public void onDiceRangeChange(int sides, int range) {

            }
        });
    }

    @Override
    public void setUpSeekBar() {
        interactor.setUpSeekBar(new DialogOptionsInteractor.Callback() {
            @Override
            public void onDiceInfo(int selections, int diceSides) {

            }

            @Override
            public void onDiceInfo(int selections, int diceSides, int diceRange) {

            }

            @Override
            public void onSeekBarSetup(int maxSelections) {
                view.setUpSeekBar(maxSelections);
            }

            @Override
            public void onDiceSidesChange(int sides) {

            }

            @Override
            public void onDiceRangeChange(int sides, int range) {

            }
        });
    }

    @Override
    public void onCustomDiceFieldOne(String value) {
        interactor.onCustomDiceFieldOne(value, new DialogOptionsInteractor.Callback() {
            @Override
            public void onDiceInfo(int selections, int diceSides) {

            }

            @Override
            public void onDiceInfo(int selections, int diceSides, int diceRange) {

            }

            @Override
            public void onSeekBarSetup(int maxSelections) {

            }

            @Override
            public void onDiceSidesChange(int sides) {
                view.onDiceSidesChange(sides);
            }

            @Override
            public void onDiceRangeChange(int sides, int range) {
                view.onDiceRangeChange(sides, range);
            }
        });
    }

    @Override
    public void onCustomDiceFieldTwo(String value) {
        interactor.onCustomDiceFieldTwo(value, new DialogOptionsInteractor.Callback() {
            @Override
            public void onDiceInfo(int selections, int diceSides) {

            }

            @Override
            public void onDiceInfo(int selections, int diceSides, int diceRange) {

            }

            @Override
            public void onSeekBarSetup(int maxSelections) {

            }

            @Override
            public void onDiceSidesChange(int sides) {
                view.onDiceSidesChange(sides);
            }

            @Override
            public void onDiceRangeChange(int sides, int range) {
                view.onDiceRangeChange(sides, range);
            }
        });
    }
}
