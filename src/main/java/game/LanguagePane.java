package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

/**
 * Creates a JFrame and ask the user to choose a language before
 */
public class LanguagePane extends WindowAdapter {
    LanguagePane() {
        JFrame f;
        JTextField tf = new JTextField("Language / Sprog / Språk");

        f = new JFrame();
        f.setSize(320,250);

        tf.setBounds(50,50,160,20);

        JButton a = new JButton("English");
        JButton b = new JButton("Dansk");
        JButton c = new JButton("Norsk");

        JButton play = new JButton("...");

        a.setBounds(3, 100, 95, 30);
        b.setBounds(103, 100, 95, 30);
        c.setBounds(203, 100, 95, 30);
        play.setBounds(103, 150,95,30);

        f.add(a);
        f.add(b);
        f.add(c);
        f.add(tf);

        f.setLayout(null);
        f.setVisible(true);

        //button listeners
        a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf.setText("English");
                Main.setLanguage("en");
                f.add(play);
                play.setText(Main.getLanguage().getString("startTitle"));
            }
        });

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf.setText("Dansk");
                Main.setLanguage("da");
                f.add(play);
                play.setText(Main.getLanguage().getString("startTitle"));
            }
        });

        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf.setText("Norsk");
                Main.setLanguage("no");
                f.add(play);
                play.setText(Main.getLanguage().getString("startTitle"));
        }
        });

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //int sureMessage = JOptionPane.showConfirmDialog(f, Main.getLanguage().getString("confirmMessage"));
                String[] options = new String[2];
                options[0] = Main.getLanguage().getString("yesMessage");
                options[1] = Main.getLanguage().getString("noMessage");
                int sureMessage = JOptionPane.showOptionDialog(f.getContentPane(),
                        Main.getLanguage().getString("confirmMessage"),
                        "",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        null);
                if (sureMessage == JOptionPane.YES_OPTION){
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    f.dispose();
                    Main.runGame = true;
                }
            }
        });

    }

}
