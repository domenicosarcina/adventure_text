/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.parser.Parser;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.language.Language;
import java.util.Scanner;

public class Engine {

    private final GameDescription game;

    private final Parser parser;

    public Engine(GameDescription game, Language language) {
        this.game = game;
        try {
            this.game.init(language);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        parser = new Parser();
    }

    public void run(Language language) {
        System.out.println(game.getCurrentRoom().getName());
        System.out.println("================================================");
        System.out.println(game.getCurrentRoom().getDescription());
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            ParserOutput p = parser.parse(command, game);
            if (p.getCommand() != null && p.getCommand().getType() == CommandType.END) {
                System.out.println(language.getDocument().getElementsByTagName("bye").item(0).getTextContent());
                System.exit(0);
            } else if (p.getCommand() != null && p.getCommand().getType() == CommandType.COMMANDS){
                //stampa comandi
                    System.out.println(language.getDocument().getElementsByTagName("command_init").item(0).getTextContent());
                    System.out.println(language.getDocument().getElementsByTagName("look").item(0).getTextContent());
                    System.out.println(language.getDocument().getElementsByTagName("north").item(0).getTextContent());
                    System.out.println(language.getDocument().getElementsByTagName("south").item(0).getTextContent());
                    System.out.println(language.getDocument().getElementsByTagName("east").item(0).getTextContent());
                    System.out.println(language.getDocument().getElementsByTagName("west").item(0).getTextContent());
                    System.out.println(language.getDocument().getElementsByTagName("inventory2").item(0).getTextContent());
                    System.out.println(language.getDocument().getElementsByTagName("pick").item(0).getTextContent());
                    System.out.println(language.getDocument().getElementsByTagName("open").item(0).getTextContent());
                    System.out.println(language.getDocument().getElementsByTagName("turn").item(0).getTextContent());
                    System.out.println(language.getDocument().getElementsByTagName("map").item(0).getTextContent());
                    System.out.println(language.getDocument().getElementsByTagName("use").item(0).getTextContent());
                    System.out.println(language.getDocument().getElementsByTagName("turn_on").item(0).getTextContent());
                    System.out.println(language.getDocument().getElementsByTagName("turn_off").item(0).getTextContent());
                    System.out.println(language.getDocument().getElementsByTagName("exit").item(0).getTextContent());
                } else {
                    game.nextMove(p, System.out, language);
                    System.out.println("================================================");
                }
        }
    }

    /**
     * @param args the command line arguments
     */
}
