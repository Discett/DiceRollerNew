package luongr.diceroller.Multiplayer.fragment.MultiplayerHostFinalFragment.interactor;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import luongr.diceroller.Selection;

/**
 * Created by Luong on 5/14/2018.
 */

public class MultiplayerHostFinalInteractor {
    List<Selection> selectionList = new ArrayList<>();

    public void setSelectionList(List<Selection> selectionList) {
        this.selectionList = selectionList;
    }

    public List<Selection> getSelectionList() {
        for(int i = 0; i < selectionList.size(); i++){
            Log.d("SelectionList",selectionList.get(i).getName());
        }
        return selectionList;
    }
}
