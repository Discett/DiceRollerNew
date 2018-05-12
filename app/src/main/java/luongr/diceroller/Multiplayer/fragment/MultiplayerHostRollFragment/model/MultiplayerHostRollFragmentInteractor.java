package luongr.diceroller.Multiplayer.fragment.MultiplayerHostRollFragment.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import luongr.diceroller.Dice;
import luongr.diceroller.Multiplayer.service.MultiplayerBluetoothService;
import luongr.diceroller.R;
import luongr.diceroller.Selection;

/**
 * Created by Luong Randy on 1/18/2018.
 */

public class MultiplayerHostRollFragmentInteractor {
    List<Selection> selectionList = new ArrayList<>();
    List<Selection> playersSelectionList = new ArrayList<>();
    Dice dice = Dice.getInstance();
    Context context;

    public MultiplayerHostRollFragmentInteractor(Context context) {
        this.context = context;
    }

    public List<Selection> getSelectionList() {
        return selectionList;
    }

    public void addSelection(String s) {
        if(!s.isEmpty()){
            Selection temp = new Selection(s);
            selectionList.add(temp);
        }
    }

    public void parseMessageList(String s) {
        if(!s.isEmpty()){
            String[] parse = s.split("//");
            for(int i = 0; i < parse.length; i++){
                //TODO: add parsed messages to the list, then find a way to sanitize the string
                //before sending it, weed out the delimiters.
                Selection temp = new Selection(parse[i]);
                playersSelectionList.add(temp);
                Log.d("Parsed Message", parse[i]);
            }
        }
    }

    public void checkMaxSelections(Callback callback) {
        if(selectionList.size() < dice.getNumberOfSelections()){
            callback.onShowAddSelection();
        } else {
            callback.onHideAddSelection();
        }
    }

    public byte[] diceInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(MultiplayerBluetoothService.MessageConstants.DICE_NUMBER_OF_SELECTION);
        sb.append(dice.getDiceSides());
        sb.append(':');
        sb.append(dice.getDiceSidesRange());
        sb.append(':');
        sb.append(dice.getNumberOfSelections());
        return sb.toString().getBytes();
    }

    public String getDiceInfoHeader() {
        StringBuilder sb = new StringBuilder();
        if(dice.getDiceSides() == dice.getDiceSidesRange()){
            sb.append(context.getResources().getString(R.string.current_dice_selected,dice.getDiceSides()));
            sb.append(" with ");
            sb.append(context.getResources().getString(R.string.current_number_of_selections,dice.getNumberOfSelections()));
        } else {
            sb.append(context.getResources().getString(R.string.current_dice_range_selected,dice.getDiceSides(),dice.getDiceSidesRange()));
            sb.append(" with ");
            sb.append(context.getResources().getString(R.string.current_number_of_selections,dice.getNumberOfSelections()));
        }
        return sb.toString();
    }

    public interface Callback{
        public void onShowAddSelection();
        public void onHideAddSelection();
    }
}
