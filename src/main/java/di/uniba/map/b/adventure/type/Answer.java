package di.uniba.map.b.adventure.type;

public class Answer {

    private String answer = null;

    private Action.actionOnEvent actionOnEvent = null;

    public void setAnswer(String string){this.answer = string;}

    public String getAnswer(){return answer;}

    public void setActionOnEvent(Action.actionOnEvent A){this.actionOnEvent = A;}

    public Action.actionOnEvent getActionOnEvent(){
        return this.actionOnEvent;
    }

}
