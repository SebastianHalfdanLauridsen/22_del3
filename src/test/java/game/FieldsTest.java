package game;
import gui_main.GUI;
import org.junit.Test;
import org.junit.Assert;


public class FieldsTest {
    Game game;

    GUI gui;
    Fields fields;

    @Test
    public void fieldTest() {
        Main.setLanguage();

        game = new Game();

        gui = game.getGui();

        fields = new Fields();
        fields.getFields();

        Assert.assertEquals(Game.MAX_FIELDS, fields.getFields().length);
        Assert.assertEquals(Game.MAX_FIELDS, gui.getFields().length);

        Game.sleep(5000);
    }
}
