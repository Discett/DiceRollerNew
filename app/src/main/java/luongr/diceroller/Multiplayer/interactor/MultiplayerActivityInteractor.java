package luongr.diceroller.Multiplayer.interactor;

import java.util.ArrayList;
import java.util.List;

import luongr.diceroller.Selection;

/**
 * Created by Luong on 5/14/2018.
 */

public class MultiplayerActivityInteractor {
    List<Selection> selectionList = new ArrayList<>();
    
    public void setSelectionList(List<Selection> selectionList) {
        this.selectionList = selectionList;
    }

    public List<Selection> getSelectionList() {
        return selectionList;
    }
}
