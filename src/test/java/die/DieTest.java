package die;

import org.junit.Test;
import org.junit.Assert;

import java.util.Arrays;

public class DieTest {

    @Test
    public void rangeTest(){
        Die die = new Die();
        int rollAmount = 1000000;

        for(int i = 0; i < rollAmount; i++){
            die.roll();
            int face = die.getFace();
            Assert.assertTrue("Die out of range", 1 <= face && face <= 6);
        }
    }

    @Test
    public void errorTest(){
        //percentage
        double allowedError = 0.1;
        Die die = new Die();
        int rollAmount = 1000000;

        int[] rolls = new int[6];
        Arrays.fill(rolls, 0);

        for(int i = 0; i < rollAmount; i++){
            die.roll();
            int face = die.getFace() - 1;
            rolls[face] += 1;
        }
        double maxVal =  Arrays.stream(rolls).max().getAsInt();
        double minVal =  Arrays.stream(rolls).min().getAsInt();

        double diff = (minVal / maxVal);

        Assert.assertTrue(1-diff < allowedError);
    }
}