package luongr.diceroller;

/**
 * Created by Luong Randy on 12/22/2017.
 */

interface IDice {
    void setDice(int x);
    void setDiceRange(int x, int y);
    int rollDice();
    void setNumberOfSelections(int x);
}
