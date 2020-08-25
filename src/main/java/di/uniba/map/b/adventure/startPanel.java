package di.uniba.map.b.adventure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import di.uniba.map.b.adventure.games.TreasureGame;
import di.uniba.map.b.adventure.language.Language;
import di.uniba.map.b.adventure.type.Player;

public class startPanel {
    private JPanel startPanel;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton SendButton;
    private JButton BackButton;

    public startPanel() {
        SendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                 String text = textField1.getText();

                 String lang = comboBox1.getSelectedItem().toString();

                 Language language = new Language(lang);

                 language.start();

                try {
                    language.sleep(2000);
                } catch (InterruptedException exc) {
                    exc.printStackTrace();
                }

                if(text.isEmpty() == true && lang.equals("Italiano")){
                    text = "Giovanni";
                    System.out.println("Non hai inserito un nome per il tuo eroe. Lo chiamerò Giovanni perchè mi piace.");
                } else if(text.isEmpty() && lang.equals("English")){
                    text = "Giovanni";
                    System.out.println("You didn't type a name fot your hero. I will call him Giovanni because I like it..");
                }

                if(lang.equals("Italiano")){
                    System.out.println("Digita 'comandi' o 'aiuto' per sapere tutti i comandi possibili");
                } else System.out.println("Type 'commands' or 'help' for the command list");

                Player player = new Player(text);
                Engine engine = new Engine(new TreasureGame(), language);
                engine.run(language);

            }
        });
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Adventure gate by Domenico Sarcina");
                frame.setPreferredSize(new Dimension(450, 400));
                frame.setContentPane(new MainApp().getMainJPanel());

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public JPanel getStartPanel(){return this.startPanel;};


}
