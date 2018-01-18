package luongr.diceroller.Multiplayer.fragment.MultiplayerHostRollFragment.model;

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
}
