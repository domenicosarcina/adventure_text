package di.uniba.map.b.adventure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import di.uniba.map.b.adventure.startPanel;

public class MainApp {
    private JButton playGameButton;
    private JPanel mainJPanel;
    private JButton ExitButton;
    private JButton historyButton;

    public MainApp() {

        playGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();

                JFrame frame = new JFrame("Adventure gate by Domenico Sarcina");
                frame.setPreferredSize(new Dimension(450, 400));
                frame.setContentPane(new startPanel().getStartPanel());

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("\nITALIANO\nSei un semplice ragazzo che ha trovato una lettera scritta dal nonno che racconta di un tesoro che si trova proprio nella tua casa.\nRoba che capita tutti i giorni insomma...\n");
                System.out.println ("ENGLISH \nYou are a simple boy who has found a letter written by his grandfather that tells of a treasure that is right in your house. \nStuff that happens every day...");
            }
        });
    }

    public static void main(String args[]) {

        JFrame frame = new JFrame("Adventure gate by Domenico Sarcina");
        frame.setPreferredSize(new Dimension(450, 400));
        frame.setContentPane(new MainApp().mainJPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public JPanel getMainJPanel(){return this.mainJPanel;}
}
