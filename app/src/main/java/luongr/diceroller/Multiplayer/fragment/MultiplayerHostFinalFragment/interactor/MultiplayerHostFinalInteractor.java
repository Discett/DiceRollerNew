package luongr.diceroller.Multiplayer.fragment.MultiplayerHostFinalFragment.interactor;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import luongr.diceroller.Dice;
import luongr.diceroller.Selection;

/**
 * Created by Luong on 5/14/2018.
 */

public class MultiplayerHostFinalInteractor {
    List<Selection> selectionList = new ArrayList<>();
    Dice dice = Dice.getInstance();

    public void setSelectionList(List<Selection> selectionList) {
        this.selectionList = selectionList;
    }

    public List<Selection> getSelectionList() {
        for(int i = 0; i < selectionList.size(); i++){
            Log.d("SelectionList",selectionList.get(i).getName());
        }
        return selectionList;
    }

    public void parseMessageList(String s) {
        if(!s.isEmpty()){
            String[] parse = s.split("//");
            for(int i = 0; i < parse.length; i++){
                //TODO: add parsed messages to the list, then find a way to sanitize the string
                //before sending it, weed out the delimiters.
                Selection temp = new Selection(parse[i]);
                //playersSelectionList.add(temp);
                selectionList.add(temp);
                Log.d("Parsed Message", parse[i]);
            }
        }
    }

    public void onRandomSelection(Callback callback) {
        //TODO: change this if you ever wanted to do elimiation style.
        Random random = new Random();
        callback.onRandomSelected(selectionList.get(random.nextInt(selectionList.size())).getName());
    }

    public interface Callback {
        public void onRandomSelected(String selection);
    }
}
