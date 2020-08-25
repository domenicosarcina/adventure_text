package di.uniba.map.b.adventure.language;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Language extends Thread{

    public Language(String language){

        this.language = language;

    }

    public void run(){

        File lang = null;

        //caricamento dei file per la lingua contententi le stringhe del gioco

        if(language.equals("Italiano")) {
            lang = new File("../adventure_gate/src/main/java/di/uniba/map/b/adventure/language/strings_it.xml");
            System.out.println("\nCaricamento...\n");
        } else{
            lang = new File("../adventure_gate/src/main/java/di/uniba/map/b/adventure/language/strings_en.xml");
            System.out.println("Loading...\n");
        }

        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(lang);
            this.document = document;
        } catch (ParserConfigurationException | SAXException | IOException e){
            System.out.println("\nLanguage selection error.");
        }

    }

    private String language = null;

    private Document document = null;

    public String getLanguage(){return this.language;}

    public Document getDocument(){return this.document;}

}
