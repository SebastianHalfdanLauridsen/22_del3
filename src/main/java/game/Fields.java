package game;

import gui_fields.*;


import java.awt.Color;

public class Fields {

    private GUI_Field[] fields;

    public Fields() {
        createFields();
    }

    /**
     *
     *///TODO needs resourcebundle language overhaul
    private void createFields() {
        fields = new GUI_Field[] {
                //START MOTTA M2 NÅR DU PASSERER
                new GUI_Start(Main.getLanguage().getString("startTitle"),
                        "<-------",
                        Main.getLanguage().getString("startDescription"),
                        new Color(229, 239,222),
                        Color.BLACK),

                //BRUNE FELT
                new GUI_Street(Main.getLanguage().getString("brownTitle1"),
                        "M1",
                        Main.getLanguage().getString("streetDescription"),
                        "1",
                        new Color(123, 119, 68),
                        Color.BLACK),
                new GUI_Street(Main.getLanguage().getString("brownTitle2"),
                        "M1",
                        Main.getLanguage().getString("streetDescription"),
                        "1",
                        new Color(123, 119, 68),
                        Color.BLACK),

                //SJANSE
                new GUI_Chance("?",
                        Main.getLanguage().getString("chanceSubText"),
                        Main.getLanguage().getString("chanceDescription"),
                        new Color(229, 239,222),
                        Color.BLACK),

                //LYSEBLÅ FELT
                new GUI_Street(Main.getLanguage().getString("lightblueTitle1"),
                        "M1",
                        Main.getLanguage().getString("streetDescription"),
                        "1",
                        new Color(161, 191, 193),
                        Color.BLACK),
                new GUI_Street(Main.getLanguage().getString("lightblueTitle2"),
                        "M1",
                        Main.getLanguage().getString("streetDescription"),
                        "1",
                        new Color(161, 191, 193),
                        Color.BLACK),

                //I FENGSEL / BARE PÅ BESØK
                new GUI_Jail("",
                        Main.getLanguage().getString("jailTitleAndSubText"),
                        Main.getLanguage().getString("jailTitleAndSubText"),
                        Main.getLanguage().getString("jailDescription"),
                        new Color(243, 222, 138),
                        Color.BLACK),

                //PINK FELT
                new GUI_Street(Main.getLanguage().getString("pinkTitle1"),
                        "M2",
                        Main.getLanguage().getString("streetDescription"),
                        "2",
                        new Color(179, 127, 139),
                        Color.BLACK),
                new GUI_Street(Main.getLanguage().getString("pinkTitle2"),
                        "M2",
                        Main.getLanguage().getString("streetDescription"),
                        "2",
                        new Color(179, 127, 139),
                        Color.BLACK),

                //SJANSE
                new GUI_Chance("?",
                        Main.getLanguage().getString("chanceSubText"),
                        Main.getLanguage().getString("chanceDescription"),
                        new Color(229, 239,222),
                        Color.BLACK),

                //ORANGE FELT
                new GUI_Street(Main.getLanguage().getString("orangeTitle1"),
                        "M2",
                        Main.getLanguage().getString("streetDescription"),
                        "2",
                        new Color(247, 233, 148),
                        Color.BLACK),
                new GUI_Street(Main.getLanguage().getString("orangeTitle2"),
                        "M2",
                        Main.getLanguage().getString("streetDescription"),
                        "2",
                        new Color(247, 233, 148),
                        Color.BLACK),

                //GRATIS PARKERING
                new GUI_Brewery("",
                        Main.getLanguage().getString("parkingTitleAndDescription"),
                        Main.getLanguage().getString("parkingSubText"),
                        Main.getLanguage().getString("parkingTitleAndDescription"),
                        new Color(229, 239,222),
                        Color.BLACK),

                //RØDE FELT
                new GUI_Street(Main.getLanguage().getString("redTitle1"),
                        "M3",
                        Main.getLanguage().getString("streetDescription"),
                        "3",
                        new Color(186, 106, 79),
                        Color.BLACK),
                new GUI_Street(Main.getLanguage().getString("redTitle2"),
                        "M3",
                        Main.getLanguage().getString("streetDescription"),
                        "3",
                        new Color(186, 106, 79),
                        Color.BLACK),

                //SJANSE FELT
                new GUI_Chance("?",
                        Main.getLanguage().getString("chanceSubText"),
                        Main.getLanguage().getString("chanceDescription"),
                        new Color(229, 239,222),
                        Color.BLACK),

                //GULE FELT
                new GUI_Street(Main.getLanguage().getString("yellowTitle1"),
                        "M3",
                        Main.getLanguage().getString("streetDescription"),
                        "3",
                        new Color(252, 254, 125),
                        Color.BLACK),
                new GUI_Street(Main.getLanguage().getString("yellowTitle2"),
                        "M3",
                        Main.getLanguage().getString("streetDescription"),
                        "3",
                        new Color(252, 254, 125),
                        Color.BLACK),

                //GÅ I FENGSEL
                new GUI_Tax(
                        Main.getLanguage().getString("goToJail"),
                        Main.getLanguage().getString("goToJail"),
                        Main.getLanguage().getString("goToJail"),
                        new Color(88, 136, 160),
                        Color.BLACK),

                //GRØNNE FELT
                new GUI_Street(Main.getLanguage().getString("greenTitle1"),
                        "M4",
                        Main.getLanguage().getString("streetDescription"),
                        "4",
                        new Color(10, 130, 90),
                        Color.BLACK),
                new GUI_Street(Main.getLanguage().getString("greenTitle2"),
                        "M4",
                        Main.getLanguage().getString("streetDescription"),
                        "4",
                        new Color(10, 130, 90),
                        Color.BLACK),

                //SJANSE
                new GUI_Chance("?",
                        Main.getLanguage().getString("chanceSubText"),
                        Main.getLanguage().getString("chanceDescription"),
                        new Color(229, 239,222),
                        Color.BLACK),

                //MØRKEBLÅ FELT
                new GUI_Street(Main.getLanguage().getString("darkblueTitle1"),
                        "M5",
                        Main.getLanguage().getString("streetDescription"),
                        "5",
                        new Color(18, 73, 117),
                        Color.BLACK),
                new GUI_Street(Main.getLanguage().getString("darkblueTitle2"),
                        "M5",
                        Main.getLanguage().getString("streetDescription"),
                        "5",
                        new Color(18, 73, 117),
                        Color.BLACK),
        };
    }

    public GUI_Field[] getFields() {
        return fields;
    }
}
