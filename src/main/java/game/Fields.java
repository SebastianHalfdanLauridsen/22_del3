package game;

import gui_fields.*;


import java.awt.Color;

public class Fields {

    private GUI_Field[] fields;

    public Fields() {
        createFields();
    }
    //TODO needs resourcebundle language overhaul
    private void createFields() {
        fields = new GUI_Field[]{
                //START MOTTA M2 NÅR DU PASSERER
                new GUI_Start("START",
                        "<-------",
                        "description",
                        new Color(229, 239,222),
                        Color.BLACK),

                //BRUNE FELT
                new GUI_Street("GATE -KJØKKENET",
                        "M1",
                        "Kan kjøbes",
                        "1",
                        new Color(123, 119, 68),
                        Color.BLACK),
                new GUI_Street("PIZZAHUSET",
                        "M1",
                        "Kan kjøbes",
                        "1",
                        new Color(123, 119, 68),
                        Color.BLACK),

                //SJANSE
                new GUI_Chance("?",
                        "SJANSEFELT",
                        "321",
                        new Color(229, 239,222),
                        Color.BLACK),

                //LYSEBLÅ FELT
                new GUI_Street("GODTE -BUTIKKEN",
                        "M1",
                        "Kan kjøbes",
                        "1",
                        new Color(161, 191, 193),
                        Color.BLACK),
                new GUI_Street("ISKIOSKEN",
                        "M1",
                        "Kan kjøbes",
                        "1",
                        new Color(161, 191, 193),
                        Color.BLACK),

                //I FENGSEL / BARE PÅ BESØK
                new GUI_Jail("",
                        "I FENGSEL",
                        "I FENGSEL",
                        "ELLER PÅ BESØK",
                        new Color(243, 222, 138),
                        Color.BLACK),

                //PINK FELT
                new GUI_Street("MUSEET",
                        "M2",
                        "Kan kjøbes",
                        "2",
                        new Color(179, 127, 139),
                        Color.BLACK),
                new GUI_Street("BIBLIOTEKET",
                        "M2",
                        "Kan kjøbes",
                        "2",
                        new Color(179, 127, 139),
                        Color.BLACK),

                //SJANSE
                new GUI_Chance("?",
                        "SJANSEFELT",
                        "321",
                        new Color(229, 239,222),
                        Color.BLACK),

                //ORANGE FELT
                new GUI_Street("RULLEBRETT -PARKEN",
                        "M2",
                        "Kan kjøbes",
                        "2",
                        new Color(247, 233, 148),
                        Color.BLACK),
                new GUI_Street("SVØMME -BASSENGET",
                        "M2",
                        "Kan kjøbes",
                        "2",
                        new Color(247, 233, 148),
                        Color.BLACK),

                //GRATIS PARKERING
                new GUI_Tax("GRATIS",
                        "PARKERING",
                        "GRATIS",
                        new Color(229, 239,222),
                        Color.BLACK),

                //RØDE FELT
                new GUI_Street("SPILLE -HALLEN",
                        "M3",
                        "Kan kjøbes",
                        "3",
                        new Color(186, 106, 79),
                        Color.BLACK),
                new GUI_Street("KINOEN",
                        "M3",
                        "Kan kjøbes",
                        "3",
                        new Color(186, 106, 79),
                        Color.BLACK),

                //SJANSE FELT
                new GUI_Chance("?",
                        "SJANSEFELT",
                        "321",
                        new Color(229, 239,222),
                        Color.BLACK),

                //GULE FELT
                new GUI_Street("LEKETØYS -BUTIKKEN",
                        "M3",
                        "Kan kjøbes",
                        "3",
                        new Color(252, 254, 125),
                        Color.BLACK),
                new GUI_Street("DYRE -BUTIKKEN",
                        "M3",
                        "Kan kjøbes",
                        "3",
                        new Color(252, 254, 125),
                        Color.BLACK),

                //GÅ I FENGSEL
                new GUI_Tax(
                        "GÅ I FENGSEL",
                        "GÅ I FENGSEL",
                        "GÅ I FENGSEL",
                        new Color(88, 136, 160),
                        Color.BLACK),

                //GRØNNE FELT
                new GUI_Street("BOWLING -HALLEN",
                        "M4",
                        "Kan kjøbes",
                        "4",
                        new Color(10, 130, 90),
                        Color.BLACK),
                new GUI_Street("ZOOLOGISK HAGE",
                        "M4",
                        "Kan kjøbes",
                        "4",
                        new Color(10, 130, 90),
                        Color.BLACK),

                //SJANSE
                new GUI_Chance("?",
                        "SJANSEFELT",
                        "321",
                        new Color(229, 239,222),
                        Color.BLACK),

                //MØRKEBLÅ FELT
                new GUI_Street("VANNLANDET",
                        "M5",
                        "Kan kjøbes",
                        "5",
                        new Color(18, 73, 117),
                        Color.BLACK),
                new GUI_Street("STAND -PROMENADEN",
                        "M5",
                        "Kan kjøbes",
                        "5",
                        new Color(18, 73, 117),
                        Color.BLACK),
        };
    }

    public GUI_Field[] getFields() {
        return fields;
    }
}
