package luongr.diceroller.Multiplayer.fragment.MultiplayerJoinRollFragment.model;

import android.content.Context;
import android.util.Log;

import luongr.diceroller.Dice;
import luongr.diceroller.R;

import static luongr.diceroller.Multiplayer.service.MultiplayerBluetoothService.MessageConstants.DICE_NUMBER_OF_SELECTION_CHECK;

/**
 * Created by Luong on 3/30/2018.
 */

public class MultiplayerJoinRollFragmentInteractor {
    Dice dice = Dice.getInstance();
    Context context;
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
                //TODO: Put a loading screen on join to wait for host to confirm
                //Needs to unlock the loading screen and also prevent people form submitting suggestions too early
                callback.onDisplayInfo(sb.toString());
            } else {
                //this is other information
                Log.d("MultiplayerInteractor", "not dice data");
            }
        }
    }

    public interface Callback{
        void onDisplayInfo(String info);
    }
}