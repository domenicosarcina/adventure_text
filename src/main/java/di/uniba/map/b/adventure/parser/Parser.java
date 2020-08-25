/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure.parser;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;
import di.uniba.map.b.adventure.type.Command;
import java.util.List;

/**
 *
 * @author pierpaolo
 */
public class Parser {

    public ParserOutput parse(String command, GameDescription game) {

        ParserOutput cmd = new ParserOutput(null, null);
        String[] string = command.toLowerCase().split(" ");
        Command stringCommand = new Command(null, null);
        AdvObject stringAdvObject = null;
        AdvObject stringInvAdvObject = null;

        //controllo se la stringa è un comando effettivo

        for (int i = 0; i < game.getCommands().size(); i++) {
            try {
                if (string[0].equals(game.getCommands().get(i).getName()) || game.getCommands().get(i).getAlias().contains(string[0])) {
                    stringCommand = new Command(game.getCommands().get(i).getType(), game.getCommands().get(i).getName());
                }
            }catch(ArrayIndexOutOfBoundsException e){
                break;
            }
        }

        if(stringCommand.getType() != null && string.length > 1){

            //controllo se la stringa contiene un aritcolo

            if (isThereArticle(string[1]) == true) {
                for (int i = 0; i < game.getObjects().size(); i++) {
                    if (string[2].equals(game.getObjects().get(i).getName()) || game.getObjects().get(i).getAlias().contains(string[2])) {
                        stringAdvObject = game.getObjects().get(i);
                    }
                }
            } else {
                for (int i = 0; i < game.getObjects().size(); i++) {
                    if (string[1].equals(game.getObjects().get(i).getName()) || game.getObjects().get(i).getAlias().contains(string[1])) {
                        stringAdvObject = game.getObjects().get(i);
                    }
                }
            }
            if(stringAdvObject != null){
                for(AdvObject o : game.getInventory()){
                    if(stringAdvObject == o){
                        stringInvAdvObject = stringAdvObject;
                    }
                }
            }
        }

        if(stringInvAdvObject != null)
            cmd = new ParserOutput(stringCommand, null, stringInvAdvObject);
        else if (stringAdvObject != null)
            cmd = new ParserOutput(stringCommand, stringAdvObject);
        else if(stringCommand != null)
            cmd = new ParserOutput(stringCommand, null);
        else
            cmd = new ParserOutput(null, null);

        return cmd;

    }

    public boolean isThereArticle(String string){

        boolean isArticle = false;

        if(string.equals("the") || string.equals("il") || string.equals("lo") || string.equals("la") || string.equals("i") || string.equals("gli") || string.equals("le")){
            isArticle = true;
        }

        return isArticle;

    }

}
