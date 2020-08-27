/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;
import di.uniba.map.b.adventure.type.AdvObject;

/**
 *
 * @author pierpaolo
 */
public class Inventory {

    private List<AdvObject> list = new ArrayList<>();

    public List<AdvObject> getList() {
        return list;
    }

    public void setList(List<AdvObject> list) {
        this.list = list;
    }

    public void add(AdvObject o) {
        list.add(o);
    }

    public void remove(AdvObject o) {
        list.remove(o);
    }

    //controllo se oggetto è presente nell'inventario
    public boolean isThere(AdvObject o){
        boolean trovato = false;
        for(AdvObject oggetto : getList()){
            if(o == oggetto){
                trovato = true;
            }
        }
        return trovato;
    }
}
