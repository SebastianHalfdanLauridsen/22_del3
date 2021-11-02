package die;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.ThreadLocalRandom;


public class Die {
    private static final Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private int face;
    private int sides;

    public Die(int face, int sides) {
        if(face < 1 || face > sides) {
            logr.log(Level.INFO, "The die's face value cannot be less than 1 " +
                    "or more than the amount of sides.");
        }
        if(sides < 0) {
            logr.log(Level.INFO, "The die cannot have less than 1 side.");
        }
        this.face = face;
        this.sides = sides;
    }

    public Die(int sides) {
        if(sides < 1) {
            logr.log(Level.INFO, "The die cannot have less than 1 side.");
        }
        this.sides = sides;
        roll();
    }

    public Die() {
        this.sides = 6; //TODO GLOBAL VARIABLE WITH SIDES
        roll();
    }

    public void roll() {
        face = ThreadLocalRandom.current().nextInt(1,sides);
    }

    //getters
    public int getFace() {
        return face;
    }
    //setters
    public void setFace(int face) {
        if(face < 1 || face > sides) {
            logr.log(Level.INFO, "The die's face value cannot be less than 1 " +
                    "or more than the amount of sides.");
        }
        this.face = face;
    }

    @Override
    public String toString() {
        return Integer.toString(face);
    }
}