package die;

import org.junit.Assert;
import org.junit.Test;

public class HandTest {

    @Test
    public void HandConstructorTest() {
        int dieAmount = 1;
        int sides = 6;
        Hand hand = new Hand(dieAmount, sides);

        Assert.assertEquals(dieAmount, hand.getDice().size());
        Assert.assertEquals(dieAmount, hand.getDiesCount());

        dieAmount = 10;
        hand = new Hand(dieAmount, sides);

        Assert.assertEquals(dieAmount, hand.getDice().size());
        Assert.assertEquals(dieAmount, hand.getDiesCount());

        //thanks to https://howtodoinjava.com/junit5/expected-exception-example/
        Assert.assertThrows(NumberFormatException.class, () -> {
            int dieAmount1 = 0;
            new Hand(dieAmount1, 6);

        });

        Assert.assertThrows(NumberFormatException.class, () -> {
            int dieAmount1 = -1;
            new Hand(dieAmount1, 6);

        });

        Assert.assertThrows(NumberFormatException.class, () -> {
            int sidesAmount = 0;
            new Hand(2, sidesAmount);

        });

        Assert.assertThrows(NumberFormatException.class, () -> {
            int sidesAmount = -1;
            new Hand(2, sidesAmount);

        });
    }

    @Test
    public void rollTest() {
        int dieAmount = 1;
        int sides = 6;

        Hand hand = new Hand(dieAmount,sides);
        hand.roll();
        Die die = hand.getDice().get(0);
        int face = die.getFace();

        Assert.assertTrue("Die out of range", 1 <= face && face <= sides);
    }

    @Test
    public void sumTest() {
        int dieAmount = 4;
        int sides = 6;

        Hand hand = new Hand(dieAmount, sides);
        int sum = hand.roll();

        Assert.assertEquals(hand.sum(), sum);
        Assert.assertTrue("Sum out of range", dieAmount <= sum && sum <= sides * dieAmount);
    }
}
