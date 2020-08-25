/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure.type;

import java.util.*;

/**
 *
 * @author pierpaolo
 */
public class AdvObject {

    private final int id;

    private String name;

    private String description;

    private String turnableText;
    
    private Set<String> alias;

    private Dialogue dialogue;

    private boolean openable = false;

    private boolean ignitable = false;

    private boolean turnedOn = false;

    private boolean pickupable = true;

    private boolean turnable = false;

    private boolean speakable = false;

    private boolean open = false;

    private boolean usable = false;

    private boolean key = false;

    private Room roomKey = null;

    public AdvObject(int id) {
        this.id = id;
    }

    public AdvObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public AdvObject(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public AdvObject(int id, String name, String description, String textTurnable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.turnableText = textTurnable;
    }

    public AdvObject(int id, String name, String description, Set<String> alias) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOpenable() {
        return openable;
    }

    public void setOpenable(boolean openable) {
        this.openable = openable;
    }

    public void setRoomKey(Room room){this.roomKey = room;}

    public Room getRoomKey(){return roomKey;}

    public String getTextTurnable() {return turnableText;}

    public boolean isPickupable() {
        return pickupable;
    }

    public void setPickupable(boolean pickupable) {
        this.pickupable = pickupable;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isKey(){return key;}

    public void setKey(boolean key){this.key = key;}

    public boolean isTurnable() {return turnable;}

    public void setTurnable(boolean turnable){ this.turnable = turnable;}

    public boolean isUsable() {return usable;}

    public void setUsable(boolean interaction){ this.usable = interaction;}

    public Set<String> getAlias() {
        return alias;
    }

    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }
    
    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    public int getId() {
        return id;
    }

    public void setDialogue(Dialogue dialogue){this.dialogue = dialogue;}

    public Dialogue getDialogue(){return dialogue;}

    public void setSpeakable(boolean speakable){this.speakable = speakable;}

    public boolean isSpeakable(){return speakable;}

    public void setTurnedOn(boolean turned){this.turnedOn = turned;}

    public boolean getTurnedOn(){return turnedOn;}

    public void setIgnitable(boolean ignitable){this.ignitable = ignitable;}

    public boolean getIgnitable(){return ignitable;}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AdvObject other = (AdvObject) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
