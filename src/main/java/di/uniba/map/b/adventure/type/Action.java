package di.uniba.map.b.adventure.type;

public class Action {
    @FunctionalInterface
    public interface actionOnEvent {
        void action();
    }
}