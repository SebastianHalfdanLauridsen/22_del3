package die;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.ThreadLocalRandom;

/**
 * //TODO
 */
public class Die {
    private static final Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private int face;
    private int sides = 0;

    /**
     * //TODO
     * @param face
     * @param sides
     */
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
        this.sides = 6; //TODO GLOBAL VARIABLE WITH SIDES
        roll();
    }

    protected void roll() {
        face = ThreadLocalRandom.current().nextInt(1,sides + 1);
    }

    public int getFace() {
        return face;
    }

}