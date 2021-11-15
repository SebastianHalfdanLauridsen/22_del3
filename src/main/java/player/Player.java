package player;
import gui_fields.GUI_Player;

//TODO make class
// - create private attribute 'fieldPosition' of type int
// - create private final attribute 'GUIPlayer' of type GUI_Player
// - create private final attribute 'name' of type String
// - create public constructor that takes parameters String 'name' and GUI_Player 'player'
//      and sets the players private attributes equal to the parameters
// - create public method setField that takes the parameter int 'fieldPosition'
//      and sets the fieldPosition of the player (remember to check if it is a valid parameter).
// - craete other appropriate get and set methods as well as a custom toString method.

public class Player {

    private int fieldPosition;

    private final GUI_Player gui_Player;

    private final String name;

    public Player(String name, GUI_Player gui_Player) {
        this.name = name;
        this.gui_Player = gui_Player;
    }

    public void setFieldPosition(int fieldPosition) {
        this.fieldPosition = fieldPosition;
    }

    public int getFieldPosition() {
        return fieldPosition;
    }

    public GUI_Player getGui_Player() {
        return gui_Player;
    }

    public String getName() {
        return name;
    }
}