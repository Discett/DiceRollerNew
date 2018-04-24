package luongr.diceroller.Multiplayer.fragment.MultiplayerJoinRollFragment.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import luongr.diceroller.Dice;
import luongr.diceroller.R;
import luongr.diceroller.Selection;

import static luongr.diceroller.Multiplayer.service.MultiplayerBluetoothService.MessageConstants.DICE_NUMBER_OF_SELECTION_CHECK;
import static luongr.diceroller.Multiplayer.service.MultiplayerBluetoothService.MessageConstants.DICE_SELECTION_DELIMITER;

/**
 * Created by Luong on 3/30/2018.
 */

public class MultiplayerJoinRollFragmentInteractor {
    Dice dice = Dice.getInstance();
    Context context;
    private ArrayList<Selection> ListOfSelection = new ArrayList<>();

    public MultiplayerJoinRollFragmentInteractor(Context context) {
        this.context = context;
    }

    public void parseMessage(String readMessage, Callback callback) {
        Log.d("MultiplayerInteractor", "ParseMessage");
        if(!readMessage.isEmpty()){
            //Parse the string if d is before split then we know it's dice data
            String[] parse = readMessage.split(":");
            String check = parse[0];
            if(check.equals(DICE_NUMBER_OF_SELECTION_CHECK)){
                //continue with dice data
                int dicex     = Integer.valueOf(parse[1]);
                int dicey     = Integer.valueOf(parse[2]);
                int diceNumberOfSelections = Integer.valueOf(parse[3]);
                dice.setDiceRange(dicex,dicey);
                dice.setNumberOfSelections(diceNumberOfSelections);
                StringBuilder sb = new StringBuilder();
                if(dicex == dicey){
                    sb.append(context.getResources().getString(R.string.current_dice_selected,dicex));
                    sb.append(" with ");
                    sb.append(context.getResources().getString(R.string.current_number_of_selections,diceNumberOfSelections));
                } else {
                    sb.append(context.getResources().getString(R.string.current_dice_range_selected,dicex,dicey));
                    sb.append(" with ");
                    sb.append(context.getResources().getString(R.string.current_number_of_selections,diceNumberOfSelections));
                }
                Log.d("MultiplayerInteractor", sb.toString());
                //Needs to unlock the loading screen and also prevent people form submitting suggestions too early
                callback.onDisplayInfo(sb.toString());
            } else {
                //this is other information
                Log.d("MultiplayerInteractor", "not dice data");
            }
        }
    }

    public void addSelectionToList(String selection) {
        if(!selection.isEmpty()){
            Log.d("Interactor","adding to list");
            ListOfSelection.add(new Selection(selection));
        }
    }

    public ArrayList<Selection> getListOfSelection() {
        return ListOfSelection;
    }

    public void checkMaxSelections(Callback callback) {
        Log.d("CheckMaxSelections", String.valueOf(ListOfSelection.size()));
        Log.d("CheckMaxSelections", String.valueOf(dice.getMaxNumberOfSelections()));
        if(ListOfSelection.size() < dice.getNumberOfSelections()){
            callback.onShowSelection();
        } else {
            callback.onHideSelection();
        }
    }

    public byte[] getByteArray() {
        StringBuilder sb = new StringBuilder();
        //loop through your selections and convert the string into one string seperated by a delimiter
        //then convert that string into a byte array
        //sanitize the string if string has a delimiter put in already
        for(int i = 0; i < ListOfSelection.size(); i++){
            sb.append(ListOfSelection.get(i).getName());
            sb.append(DICE_SELECTION_DELIMITER);
        }
        return sb.toString().getBytes();
    }

    public interface Callback{
        void onDisplayInfo(String info);
        void onHideSelection();
        void onShowSelection();
    }
}
