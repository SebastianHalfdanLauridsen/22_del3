package player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {
    @Test
    public void setFieldPositionTest() {
        int startField = 0;
        int nextField = 2;
        Player player = new Player(startField,new GUI_Player("name", 23, new GUI_Car()));
        Assert.assertEquals(startField,player.getFieldPosition());
        player.setFieldPosition(nextField);
        Assert.assertEquals(nextField,player.getFieldPosition());
    }

    @Test
    public void addOwnedFieldTest() {
        int fieldIndex = 1;
        Player player = new Player(0,new GUI_Player("name", 23, new GUI_Car()));
        player.addOwnedField(fieldIndex);

        int ownedFieldIndex = player.getOwnedFields().get(0);

        Assert.assertEquals(fieldIndex,ownedFieldIndex);
    }
}
