package di.uniba.map.b.adventure.games;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.*;
import di.uniba.map.b.adventure.language.Language;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Scanner;

public class TreasureGame extends GameDescription {

    @Override
    public void init(Language language) throws Exception {

        //Comandi gioco
        Command nord = new Command(CommandType.NORD, "nord");
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD", "north"});
        getCommands().add(nord);
        Command inventory = new Command(CommandType.INVENTORY, "inventario");
        inventory.setAlias(new String[]{"inv", "i", "I", "inventory"});
        getCommands().add(inventory);
        Command sud = new Command(CommandType.SOUTH, "sud");
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD", "south"});
        getCommands().add(sud);
        Command est = new Command(CommandType.EAST, "est");
        est.setAlias(new String[]{"e", "E", "Est", "EST", "east"});
        getCommands().add(est);
        Command ovest = new Command(CommandType.WEST, "ovest");
        ovest.setAlias(new String[]{"o", "O", "Ovest", "OVEST", "west", "w"});
        getCommands().add(ovest);
        Command end = new Command(CommandType.END, "end");
        end.setAlias(new String[]{"end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati","exit"});
        getCommands().add(end);
        Command look = new Command(CommandType.LOOK_AT, "osserva");
        look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi", "look"});
        getCommands().add(look);
        Command pickup = new Command(CommandType.PICK_UP, "raccogli");
        pickup.setAlias(new String[]{"prendi", "pick"});
        getCommands().add(pickup);
        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{"open"});
        getCommands().add(open);
        Command turn = new Command(CommandType.TURN, "gira");
        turn.setAlias(new String[]{"turn"});
        getCommands().add(turn);
        Command commands = new Command(CommandType.COMMANDS, "comandi");
        commands.setAlias(new String[]{"aiuto", "help", "command", "commands"});
        getCommands().add(commands);
        Command map = new Command(CommandType.MAP, "mappa");
        map.setAlias(new String[]{"map", "mappe", "maps", "M", "m"});
        getCommands().add(map);
        Command use = new Command(CommandType.USE, "usa");
        use.setAlias(new String[]{"use"});
        getCommands().add(use);
        Command turnOn = new Command(CommandType.TURN_ON, "accendi");
        turnOn.setAlias(new String[]{"turnon"});
        getCommands().add(turnOn);
        Command turnOff = new Command(CommandType.TURN_OFF, "spegni");
        turnOff.setAlias(new String[]{"turnoff"});
        getCommands().add(turnOff);

        //Stanze  del gioco
        Room stanzetta = new Room(0, language.getDocument().getElementsByTagName("hero_bedroom").item(0).getTextContent(), language.getDocument().getElementsByTagName("hero_bedroom_descr").item(0).getTextContent());
        stanzetta.setLook(language.getDocument().getElementsByTagName("hero_bedroom_look").item(0).getTextContent());

        Room corridoio = new Room(1, language.getDocument().getElementsByTagName("corridor").item(0).getTextContent(), language.getDocument().getElementsByTagName("corridor_descr").item(0).getTextContent());
        corridoio.setLook(language.getDocument().getElementsByTagName("corridor_look").item(0).getTextContent());

        Room studio = new Room(2, language.getDocument().getElementsByTagName("office").item(0).getTextContent(), language.getDocument().getElementsByTagName("office_descr").item(0).getTextContent());
        studio.setLook(language.getDocument().getElementsByTagName("office_look").item(0).getTextContent());
        studio.setLocked(true);

        Room stanzaGenitori = new Room(3,language.getDocument().getElementsByTagName("parents_bedroom").item(0).getTextContent(), language.getDocument().getElementsByTagName("parents_bedroom_descr").item(0).getTextContent());
        stanzaGenitori.setLook(language.getDocument().getElementsByTagName("parents_bedroom_look").item(0).getTextContent());

        Room corridoioSegreto = new Room(4,language.getDocument().getElementsByTagName("hidden_corridor").item(0).getTextContent(), language.getDocument().getElementsByTagName("hidden_corridor_descr").item(0).getTextContent());
        corridoioSegreto.setLook(language.getDocument().getElementsByTagName("hidden_corridor_look").item(0).getTextContent());
        corridoioSegreto.setLocked(true);

        Room stanzaFinale = new Room(5, language.getDocument().getElementsByTagName("treasure_room").item(0).getTextContent(), language.getDocument().getElementsByTagName("treasure_room_descr").item(0).getTextContent());
        stanzaFinale.setFinalRoom(true);
        stanzaFinale.setLocked(true);
        stanzaFinale.setLook(language.getDocument().getElementsByTagName("treasure_room_look").item(0).getTextContent());


        //Mappa di gioco
        stanzetta.setWest(corridoio);
        corridoio.setEast(stanzetta);
        corridoio.setWest(studio);
        corridoio.setNorth(stanzaGenitori);
        studio.setEast(corridoio);
        studio.setSouth(corridoioSegreto);
        stanzaGenitori.setSouth(corridoio);
        corridoioSegreto.setNorth(studio);
        corridoioSegreto.setSouth(stanzaFinale);
        getRooms().add(corridoio);
        getRooms().add(stanzetta);
        getRooms().add(studio);
        getRooms().add(stanzaGenitori);
        getRooms().add(corridoioSegreto);

        //Oggetti di gioco
        AdvObject office_key = new AdvObject(1, language.getDocument().getElementsByTagName("key").item(0).getTextContent(), language.getDocument().getElementsByTagName("key_descr").item(0).getTextContent());
        office_key.setAlias(new String[]{"chiave", "chiavi", "keys", "key"});
        office_key.setUsable(true);
        office_key.setKey(true);
        office_key.setRoomKey(studio);
        getObjects().add(office_key);
        corridoio.getObjects().add(office_key);

        AdvObject torch = new AdvObject(2, language.getDocument().getElementsByTagName("torch").item(0).getTextContent(), language.getDocument().getElementsByTagName("torch_descr").item(0).getTextContent());
        torch.setIgnitable(true);
        getObjects().add(torch);
        torch.setAlias(new String[]{"torcia", "torch"});

        AdvObjectContainer drawer = new AdvObjectContainer(4, language.getDocument().getElementsByTagName("drawer").item(0).getTextContent(), language.getDocument().getElementsByTagName("drawer_descr").item(0).getTextContent());
        drawer.setAlias(new String[]{"cassetto", "drawer"});
        drawer.setOpenable(true);
        drawer.setPickupable(false);
        drawer.setOpen(false);
        drawer.setUsable(false);
        drawer.add(torch);
        getObjects().add(drawer);
        stanzetta.getObjects().add(drawer);

        AdvObject computer = new AdvObject(0, "computer", language.getDocument().getElementsByTagName("computer_descr").item(0).getTextContent());
        computer.setAlias(new String[]{"pc"});
        computer.setSpeakable(true);
        computer.setUsable(true);
        getObjects().add(computer);
        //creazione dialogo con oggetto
        Dialogue dialogue1 = new Dialogue();
        computer.setDialogue(dialogue1);
        Answer answer = new Answer();
        answer.setAnswer(language.getDocument().getElementsByTagName("computer_pass_answer").item(0).getTextContent());
        //azione che si svolge alla scelta di una risposta
        answer.setActionOnEvent(() -> {
            Scanner comando = new Scanner(System.in);
            System.out.print(language.getDocument().getElementsByTagName("computer_pass_answer2").item(0).getTextContent());
            //scelta risposta tramite numero intero
            int risposta = Integer.parseInt(comando.nextLine());
            if(risposta == 43875){
                dialogue1.getAnswers().remove(answer);
                if(corridoioSegreto.isLocked()){
                    Answer room_answer = new Answer();
                    room_answer.setAnswer(language.getDocument().getElementsByTagName("computer_door_answer").item(0).getTextContent());
                    room_answer.setActionOnEvent(() -> {
                            System.out.println(language.getDocument().getElementsByTagName("door_opened").item(0).getTextContent());
                            studio.setLook(language.getDocument().getElementsByTagName("office_look_v2").item(0).getTextContent());
                            corridoioSegreto.setLocked(false);
                    });
                    dialogue1.getAnswers().add(room_answer);
                } else{
                   System.out.println(language.getDocument().getElementsByTagName("computer_door_answer_opened").item(0).getTextContent());
                }
            } else
                System.out.println(language.getDocument().getElementsByTagName("computer_pass_answer_wrong").item(0).getTextContent());
        });
        dialogue1.getAnswers().add(answer);
        Answer endAnswer = new Answer();
        endAnswer.setAnswer(language.getDocument().getElementsByTagName("exit_answer").item(0).getTextContent());
        dialogue1.getAnswers().add(endAnswer);
        computer.setPickupable(false);
        studio.getObjects().add(computer);

        AdvObject grandpa_photo = new AdvObject(6, language.getDocument().getElementsByTagName("photo").item(0).getTextContent(), language.getDocument().getElementsByTagName("photo_descr").item(0).getTextContent(), language.getDocument().getElementsByTagName("photo_turn").item(0).getTextContent());
        grandpa_photo.setAlias(new String[]{"foto", "photo"});
        grandpa_photo.setTurnable(true);
        getObjects().add(grandpa_photo);
        stanzaGenitori.getObjects().add(grandpa_photo);

        AdvObject sensore = new AdvObject(7, language.getDocument().getElementsByTagName("sensor").item(0).getTextContent(), language.getDocument().getElementsByTagName("sensor_descr").item(0).getTextContent());
        sensore.setPickupable(false);
        sensore.setSpeakable(true);
        sensore.setUsable(true);
        sensore.setAlias(new String[]{"sensore", "sensor"});
        getObjects().add(sensore);
        Dialogue dialogue2 = new Dialogue();
        sensore.setDialogue(dialogue2);
        Answer answer2 = new Answer();
        answer2.setAnswer(language.getDocument().getElementsByTagName("sensor_scan_answer").item(0).getTextContent());
        answer2.setActionOnEvent(() -> {
            System.out.println(language.getDocument().getElementsByTagName("sensor_scan_answer2").item(0).getTextContent());
            Scanner comando = new Scanner(System.in);
            int i = 0;
            for(AdvObject o : getInventory()){
                System.out.println(i+1 +". "+ getInventory().get(i).getName());
                i++;
            }
            Integer scelta = Integer.parseInt(comando.nextLine());
            if (getInventory().get(scelta - 1).getName().equals(language.getDocument().getElementsByTagName("photo").item(0).getTextContent())) {
                dialogue2.getAnswers().remove(answer2);
                System.out.println(language.getDocument().getElementsByTagName("door_opened").item(0).getTextContent());
                stanzaFinale.setLocked(false);
                corridoioSegreto.setLook(language.getDocument().getElementsByTagName("hidden_corridor_look_v2").item(0).getTextContent());
            } else System.out.println(language.getDocument().getElementsByTagName("not_right_object").item(0).getTextContent());
        });
        Answer endAnswer2 = new Answer();
        endAnswer2.setAnswer(language.getDocument().getElementsByTagName("exit_answer").item(0).getTextContent());
        dialogue2.getAnswers().add(endAnswer2);
        dialogue2.getAnswers().add(answer2);
        corridoioSegreto.getObjects().add(sensore);

        setCurrentRoom(stanzetta);
    }

    public void nextMove(ParserOutput p, PrintStream out, Language language){

        if (p.getCommand().getType() == null) {
            out.println(language.getDocument().getElementsByTagName("dont_understand").item(0).getTextContent());
        } else {
            //move
            boolean noroom = false;
            boolean move = false;
            if (p.getCommand().getType() == CommandType.NORD) {
                if (getCurrentRoom().getNorth() != null) {
                    setCurrentRoom(getCurrentRoom().getNorth());
                    move = true;
                } else {
                    out.println(language.getDocument().getElementsByTagName("nothing_there").item(0).getTextContent());
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.SOUTH) {
                //controllo se è la stanza finale
                if (getCurrentRoom().getSouth() != null && getCurrentRoom().getSouth().getFinalRoom() == false && getCurrentRoom().getSouth().isLocked() == false) {
                    setCurrentRoom(getCurrentRoom().getSouth());
                    move = true;
                } else if(getCurrentRoom().getSouth().getFinalRoom() && getCurrentRoom().getSouth().isLocked() == false){
                    //se è la stanza finale, controllo sull'oggetto torcia, necessaria accesa per entrare nella stanza
                    boolean trovato = false;
                    for(AdvObject o : getInventory()){
                        if(o.getName().equals(language.getDocument().getElementsByTagName("torch").item(0).getTextContent())){
                            if(o.getTurnedOn()){
                                out.println(language.getDocument().getElementsByTagName("torch_on").item(0).getTextContent());
                                setCurrentRoom(getCurrentRoom().getSouth());
                                move = true;
                            } else out.println(language.getDocument().getElementsByTagName("torch_off").item(0).getTextContent());
                            trovato = true;
                        }
                    }
                    if(trovato == false){
                        out.println(language.getDocument().getElementsByTagName("torch_no").item(0).getTextContent());
                    }
                } else {
                    out.println(language.getDocument().getElementsByTagName("nothing_there").item(0).getTextContent());
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.EAST) {
                if (getCurrentRoom().getEast() != null) {
                    setCurrentRoom(getCurrentRoom().getEast());
                    move = true;
                } else {
                    out.println(language.getDocument().getElementsByTagName("nothing_there").item(0).getTextContent());
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.WEST) {
                //controllo se la porta è chiusa
                if (getCurrentRoom().getWest() != null && getCurrentRoom().getWest().isLocked() == false) {
                    setCurrentRoom(getCurrentRoom().getWest());
                    move = true;
                } else if(getCurrentRoom().getWest() != null && getCurrentRoom().getWest().isLocked() == true){
                    out.println(language.getDocument().getElementsByTagName("door_locked").item(0).getTextContent());
                } else {
                    out.println(language.getDocument().getElementsByTagName("nothing_there").item(0).getTextContent());
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.INVENTORY) {
                out.println(language.getDocument().getElementsByTagName("inventory").item(0).getTextContent());
                for (AdvObject o : getInventory()) {
                    out.println(o.getName() + ": " + o.getDescription());
                }
            } else if (p.getCommand().getType() == CommandType.LOOK_AT) {
                if (getCurrentRoom().getLook() != null) {
                    out.println(getCurrentRoom().getLook());
                } else {
                    out.println(language.getDocument().getElementsByTagName("nothing_interesting").item(0).getTextContent());
                }
            } else if(p.getCommand().getType() == CommandType.PICK_UP){
                if (p.getObject() != null) {
                    if (p.getObject().isPickupable() && getCurrentRoom().getObjects().contains(p.getObject())) {
                        getInventory().add(p.getObject());
                        getCurrentRoom().getObjects().remove(p.getObject());
                        out.println(language.getDocument().getElementsByTagName("picked").item(0).getTextContent() + p.getObject().getDescription());
                    } else {
                        out.println(language.getDocument().getElementsByTagName("cant_pick").item(0).getTextContent());
                    }
                } else {
                    out.println(language.getDocument().getElementsByTagName("nothing_to_pick").item(0).getTextContent());
                }
            } else if (p.getCommand().getType() == CommandType.OPEN) {
                if (p.getObject() == null && p.getInvObject() == null) {
                    out.println(language.getDocument().getElementsByTagName("nothing_to_open").item(0).getTextContent());
                } else {
                    if (p.getObject() != null) {
                        if (p.getObject().isOpenable() && p.getObject().isOpen() == false) {
                            if (p.getObject() instanceof AdvObjectContainer) {
                                out.println(language.getDocument().getElementsByTagName("opened").item(0).getTextContent() + p.getObject().getName());
                                AdvObjectContainer c = (AdvObjectContainer) p.getObject();
                                if (!c.getList().isEmpty()) {
                                    out.print(language.getDocument().getElementsByTagName("founded").item(0).getTextContent());
                                    Iterator<AdvObject> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        AdvObject next = it.next();
                                        out.print(next.getName() + ", ");
                                    }
                                    out.println();
                                    //scelta se raccogliere oggetti presenti nell'oggetto contenitore
                                    out.println(language.getDocument().getElementsByTagName("add_answer").item(0).getTextContent());
                                    Scanner comando = new Scanner(System.in);
                                    String risposta = comando.nextLine().toLowerCase();
                                    Iterator<AdvObject> ite = c.getList().iterator();
                                    if (risposta.equals("si") || risposta.equals("yes")) {
                                        while (ite.hasNext()) {
                                            AdvObject next = ite.next();
                                            getInventory().add(next);
                                            ite.remove();
                                        }
                                    } else if (risposta.equals("no")) {
                                        out.println(language.getDocument().getElementsByTagName("decision").item(0).getTextContent());
                                    } else out.println(language.getDocument().getElementsByTagName("wrong").item(0).getTextContent());
                                }
                            } else {
                                out.println(language.getDocument().getElementsByTagName("opened").item(0).getTextContent() + p.getObject().getName());
                            }
                        } else {
                            out.println(language.getDocument().getElementsByTagName("cant_open").item(0).getTextContent());
                        }
                    }
                    //nel gioco non sono stati previsti oggetti prendibili ed apribili allo stesso tempo
                    if (p.getInvObject() != null) {
                        out.println(language.getDocument().getElementsByTagName("cant_open").item(0).getTextContent());
                    }
                }
            } else if (p.getCommand().getType() == CommandType.TURN){
                if(p.getObject() == null && p.getInvObject() == null){
                    out.println(language.getDocument().getElementsByTagName("nothing_to_turn").item(0).getTextContent());
                }else {
                    if (p.getObject() != null) {
                        //controllo se l'oggetto è girabile
                        if (p.getObject().isTurnable()) {
                            //visualizzazione del testo presente dietro
                            out.println(language.getDocument().getElementsByTagName("turned").item(0).getTextContent() + p.getObject().getName());
                            out.println(language.getDocument().getElementsByTagName("turned_text").item(0).getTextContent() + p.getObject().getTextTurnable());
                        } else {
                            out.println(language.getDocument().getElementsByTagName("cant_turn").item(0).getTextContent());
                        }
                    }
                    if (p.getInvObject() != null) {
                        if (p.getInvObject().isTurnable()) {
                            out.println(language.getDocument().getElementsByTagName("turned_inventory").item(0).getTextContent() + p.getInvObject().getName());
                            out.println(language.getDocument().getElementsByTagName("turned_text").item(0).getTextContent() + p.getInvObject().getTextTurnable());
                        } else {
                            out.println(language.getDocument().getElementsByTagName("cant_turn").item(0).getTextContent());
                        }
                    }
                }
            } else if(p.getCommand().getType() == CommandType.MAP){
                    showMap();
            } else if(p.getCommand().getType() == CommandType.USE){
                if(p.getObject() == null && p.getInvObject() == null){
                    out.println(language.getDocument().getElementsByTagName("dont_understand_short").item(0).getTextContent());
                }
                if(p.getInvObject() != null){
                    if(p.getInvObject().isUsable()) {
                        if (p.getInvObject().isKey()) {
                            //controllo se l'utente si trova nella stanza vicina per aprire la porta con la chiave,
                            //non può trovarsi in una stanza distante
                            if(getCurrentRoom().getWest() != null) {
                                if (getCurrentRoom().getWest().isLocked() == true && getCurrentRoom().getWest() == p.getInvObject().getRoomKey()) {
                                    out.println(language.getDocument().getElementsByTagName("right_key").item(0).getTextContent());
                                    getCurrentRoom().getWest().setLocked(false);
                                } else out.println(language.getDocument().getElementsByTagName("near_room_key").item(0).getTextContent());
                            } else out.println(language.getDocument().getElementsByTagName("near_room_key").item(0).getTextContent());
                        }
                    }  else out.println(language.getDocument().getElementsByTagName("cant_use").item(0).getTextContent());
                }
                if(p.getObject() != null){
                    if (p.getObject().isUsable() == false) {
                         out.println(language.getDocument().getElementsByTagName("cant_use").item(0).getTextContent());
                    }
                    //controllo se l'oggetto è "parlabile"
                    if(p.getObject().isSpeakable()) {
                        boolean error = true;
                        while (error) {
                            int j = 0;
                            int i = 0;
                            Scanner comando = new Scanner(System.in);

                            for (Answer ans : p.getObject().getDialogue().getAnswers()) {
                                out.println(j + 1 + ". " + p.getObject().getDialogue().getAnswers().get(j).getAnswer());
                                j = j + 1;
                            }
                            try {
                                int risposta = comando.nextInt();
                                risposta = risposta -1;
                                if (p.getObject().getDialogue().getAnswers().get(risposta).getActionOnEvent() != null) {
                                    //azione collegata alla risposta, memorizzata prima con le lambda expressions
                                    p.getObject().getDialogue().getAnswers().get(risposta).getActionOnEvent().action();
                                } else if (p.getObject().getDialogue().getAnswers().get(risposta).getAnswer().equals(language.getDocument().getElementsByTagName("exit_answer").item(0).getTextContent())) {
                                    //risposta "esci"
                                    error = false;
                                } else {
                                    out.println(language.getDocument().getElementsByTagName("dont_understand_short").item(0).getTextContent());
                                }
                            } catch (Exception e) {
                                out.println(language.getDocument().getElementsByTagName("wrong").item(0).getTextContent());
                            }
                        }
                    }

                }
            }
            if(p.getCommand() != null && p.getCommand().getType() == CommandType.TURN_ON){
                if(p.getObject() == null && p.getInvObject() == null){
                    out.println(language.getDocument().getElementsByTagName("dont_understand_short").item(0).getTextContent());
                }
                if(p.getObject() != null){
                    if(p.getObject().getIgnitable()){
                        out.println(language.getDocument().getElementsByTagName("inventory_turnon").item(0).getTextContent());
                    } else out.println(language.getDocument().getElementsByTagName("cant_turnon").item(0).getTextContent());
                }
                if(p.getInvObject() != null){
                    //controllo se l'oggetto è accendibile/spegnibile
                    if(p.getInvObject().getIgnitable()){
                        //controllo se è gia acceso altrimenti lo accendo
                        if(p.getInvObject().getTurnedOn() == true){
                            out.println(language.getDocument().getElementsByTagName("is_just_turnedon").item(0).getTextContent());
                        } else {
                            out.println(language.getDocument().getElementsByTagName("turnon").item(0).getTextContent());
                            p.getInvObject().setTurnedOn(true);
                        }
                    } else out.println(language.getDocument().getElementsByTagName("cant_turnon").item(0).getTextContent());
                }
            }
            if(p.getCommand() != null && p.getCommand().getType() == CommandType.TURN_OFF){
                if(p.getObject() == null && p.getInvObject() == null){
                    out.println(language.getDocument().getElementsByTagName("dont_understand_short").item(0).getTextContent());
                }
                if(p.getObject() != null){
                    if(p.getObject().getIgnitable()){
                        out.println(language.getDocument().getElementsByTagName("inventory_turnon").item(0).getTextContent());
                    } else out.println(language.getDocument().getElementsByTagName("cant_turnon").item(0).getTextContent());
                }
                if(p.getInvObject() != null){
                    if(p.getInvObject().getIgnitable()){
                        //controllo se l'oggetto è acceso e quindi lo spengo, altrimenti è già spento
                        if(p.getInvObject().getTurnedOn() == true){
                            out.println(language.getDocument().getElementsByTagName("turnoff").item(0).getTextContent());
                            p.getInvObject().setTurnedOn(false);
                        } else out.println(language.getDocument().getElementsByTagName("is_just_turnedoff").item(0).getTextContent());
                    } else out.println(language.getDocument().getElementsByTagName("cant_turnon").item(0).getTextContent());
                }
            }
            //se stanza corrente è stanza finale, allora il gioco è finito
            if(getCurrentRoom().getFinalRoom()){
                out.println(language.getDocument().getElementsByTagName("founded_treasure").item(0).getTextContent());
                System.exit(0);
            }
        }
    }

    public void showMap(){

        System.out.println("\n");
        System.out.println("                 |--------------------|\n                 |                    |\n                 |                    |\n                 |                    |\n                 |   STANZA GENITORI  |\n                 |                    |\n                 |                    |\n                 |                    |\n                 |--------------------|\n");
        System.out.println("   |---------------||-----------||---------------|");
        System.out.println("   |               ||           ||               |\n   |               ||           ||               |\n   |               ||           ||               |\n   |    STUDIO     || CORRIDOIO ||   STANZETTA   |\n   |               ||           ||               |\n   |               ||           ||               |\n   |               ||           ||               |\n   |---------------||-----------||---------------|\n");

    }

}
