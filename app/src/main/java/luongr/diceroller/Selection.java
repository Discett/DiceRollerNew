package luongr.diceroller;

/**
 * Created by Luong Randy on 12/26/2017.
 */

public class Selection {
    private String name;
    private Integer rollResult;

    public Selection(String name) {
        this.name = name;
        rollResult = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRollResult() {
        return rollResult;
    }

    public void setRollResult(Integer rollResult) {
        this.rollResult = rollResult;
    }
}
