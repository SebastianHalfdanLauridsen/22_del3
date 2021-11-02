package die;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hand {

    private static final Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final int diesCount;
    private final ArrayList<Die> dice;

    public Hand(int dice, int sides) {
        if(dice < 1 || sides < 1) {
            logr.log(Level.INFO, "Hand constructor arguments cannot be less than 1.");
        }
        this.diesCount = dice;
        this.dice = new ArrayList<>();
        for(int i = 0; i < dice; i++) {
            this.dice.add(new Die(sides));
        }
    }

    public void roll() {
        for(int i = 0; i < diesCount; i++) {
            dice.get(i).roll();
        }
    }

    public int sum() {
        int sum = 0;
        for (int i = 0; i < diesCount; i++) {
            sum += dice.get(i).getFace();
        }
        return sum;
    }

    public int getDiesCount() {
        return diesCount;
    }
    public List<Die> getDice() {
        return dice;
    }

    //TODO
    @Override
    public String toString() {
        return "tostringHand";
    }

}
