package luongr.diceroller.SinglePlayer.model;

import java.util.ArrayList;
import java.util.List;

import luongr.diceroller.Dice;
import luongr.diceroller.Selection;

/**
 * Created by Luong Randy on 12/22/2017.
 */

public class SinglePlayerInteractor {
    private Dice dice;
    private List<Selection> selectionList = new ArrayList<>();
    private boolean selectionAvailable = false;

    public SinglePlayerInteractor() {
        dice = Dice.getInstance();
    }

    public void onRoll(Callback callback) {
        if(selectionAvailable){
            callback.onSelectionRoll();
        } else {
            callback.onRolled(dice.rollDice());
        }
    }

    public void onSettings(Callback callback) {
        callback.onSettings();
    }

    public void checkSelection(Callback callback) {
        if(dice.getNumberOfSelections() == 0){
            callback.onNoSelection();
            selectionAvailable = false;
        } else {
            callback.onAvailableSelections();
            selectionAvailable = true;
        }
    }

    public void checkListEntries(Callback callback) {
        if(selectionList.size() < dice.getNumberOfSelections() && selectionAvailable){
            callback.showEdtSelections();
        } else {
            callback.hideEdtSelections();
        }
    }

    public void onAddSelection(String selection, Callback callback) {
        if(!selection.isEmpty()){
            Selection addSelection = new Selection(selection);
            selectionList.add(addSelection);
        }
    }

    public List<Selection> getSelectionList() {
        return selectionList;
    }

    public interface Callback{
        void onRolled(int roll);

        void onSettings();

        void onNoSelection();

        void onAvailableSelections();

        void showEdtSelections();

        void hideEdtSelections();

        void onEmptySelection();

        void onValidSelection();

        void onSelectionRoll();
    }
}
