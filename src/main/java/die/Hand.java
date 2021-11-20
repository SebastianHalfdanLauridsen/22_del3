package die;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * //TODO
 */
public class Hand {

    private static final Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final int diesCount;
    private final ArrayList<Die> dice;

    public Hand(int dice, int sides) throws NumberFormatException{
        if(dice < 1 || sides < 1) {
            logr.log(Level.INFO, "Hand constructor arguments cannot be less than 1.");
            throw new NumberFormatException();
        }
        this.diesCount = dice;
        this.dice = new ArrayList<>();
        for(int i = 0; i < dice; i++) {
            this.dice.add(new Die(sides));
        }
    }

    public int roll() {
        for(int i = 0; i < diesCount; i++) {
            dice.get(i).roll();
        }
        return sum();
    }

    public int sum() {
        int sum = 0;
        for (int i = 0; i < diesCount; i++) {
            sum += dice.get(i).getFace();
        }
        return sum;
    }

    public ArrayList<Die> getDice() {
        return dice;
    }
    public int getDiesCount() {
        return diesCount;
    }
}
