package player;

import gui_fields.GUI_Car;

import java.awt.Color;

/**
 * //TODO
 */
public class Cars {
    private GUI_Car[] cars;

    public Cars() {
        createCars();
    }

    private void createCars() {
        cars = new GUI_Car[]{
                //the car (bilen)
                new GUI_Car(new Color(89, 144, 95),
                        new Color(143, 182, 89),
                        GUI_Car.Type.CAR,
                        GUI_Car.Pattern.HORIZONTAL_GRADIANT),

                //the ufo (skibet)
                new GUI_Car(new Color(126, 159, 199),
                        new Color(112, 112, 153),
                        GUI_Car.Type.UFO,
                        GUI_Car.Pattern.DOTTED),

                //the racecar (katten)
                new GUI_Car(new Color(227, 153, 81),
                        new Color(162, 89, 73),
                        GUI_Car.Type.RACECAR,
                        GUI_Car.Pattern.ZEBRA),

                //the tractor (hunden)
                new GUI_Car(new Color(57, 69, 68),
                        new Color(163, 170, 171),
                        GUI_Car.Type.TRACTOR,
                        GUI_Car.Pattern.HORIZONTAL_LINE),
        };
    }
    public GUI_Car[] getCars() {
        return cars;
    }
}
