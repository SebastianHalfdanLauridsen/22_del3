package player;

import gui_fields.GUI_Player;
import game.Game;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Player class represents the player with their counterpart GUI_Player, field position,
 *   list of owned fields and whether they have a jail card or are in jail
 */
public class Player {
    private static final Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private int fieldPosition;
    private final GUI_Player GUIPlayer;
    private final ArrayList<Integer> ownedFields;
    private boolean inJail = false;
    private boolean jailCard = false;

    protected Player(int startField, GUI_Player GUIPlayer) {
        this.fieldPosition = startField;
        this.GUIPlayer = GUIPlayer;
        this.ownedFields = new ArrayList<>();
    }
    protected void addOwnedField(int fieldIndex) {
        ownedFields.add(fieldIndex);
    }
    protected ArrayList<Integer> getOwnedFields() {
        return ownedFields;
    }
    public int getFieldPosition() {
        return fieldPosition;
    }
    public GUI_Player getGUIPlayer() {
        return GUIPlayer;
    }

    /**
     * Sets the field position of the player if it is valid
     * @param fieldPosition the field position the player must be moved to
     */
    public void setFieldPosition(int fieldPosition) {
        if(fieldPosition < 0 || fieldPosition > Game.MAX_FIELDS) {
            logr.log(Level.INFO, "The player's field position cannot be less than 0 " +
                    "or more than the total amount of fields on the board");
            return;
        }
        this.fieldPosition = fieldPosition;
    }

    public boolean hasJailCard() {
        return jailCard;
    }
    public void setJailCard(boolean jailCard) {
        this.jailCard = jailCard;
    }
    public boolean isInJail() {
        return inJail;
    }
    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }
}
