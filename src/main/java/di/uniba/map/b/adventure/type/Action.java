package di.uniba.map.b.adventure.type;

public class Action {
    @FunctionalInterface
    public interface actionOnEvent {
        //metodo per collegare azioni che si svolgeranno da una risposta dell'oggetto
        void action();
    }
}