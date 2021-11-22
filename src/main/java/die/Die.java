package die;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The die class represents a die with a given amount of sides
 *   and a method for rolling the die and getting a face value
 */
public class Die {
    private static final Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private int face;
    private int sides = 0;

    protected Die(int face, int sides) {
        if(face < 1 || face > sides) {
            logr.log(Level.INFO, "The die's face value cannot be less than 1 " +
                    "or more than the amount of sides.");
            return;
        }
        this.face = face;
        this.sides = sides;
    }

    protected Die(int sides) {
        if(sides < 1) {
            logr.log(Level.INFO, "The die cannot have less than 1 side.");
            return;
        }
        this.sides = sides;
        roll();
    }

    public Die() {
        this.sides = 6;
        roll();
    }

    protected void roll() {
        face = ThreadLocalRandom.current().nextInt(1,sides + 1);
    }

    public int getFace() {
        return face;
    }

}