package luongr.diceroller.Multiplayer.fragment.MultiplayerHostRollFragment.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import luongr.diceroller.Selection;

/**
 * Created by Luong Randy on 1/18/2018.
 */

public class MultiplayerHostRollFragmentInteractor {
    List<Selection> selectionList = new ArrayList<>();
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
                Log.d("Parsed Message", parse[i]);
            }
        }
    }
}
