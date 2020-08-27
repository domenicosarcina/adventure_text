package di.uniba.map.b.adventure.type;

public class Answer {

    private String answer = null; //testo della risposta

    private Action.actionOnEvent actionOnEvent = null; //azione collegata a risposta

    public void setAnswer(String string){this.answer = string;}

    public String getAnswer(){return answer;}

    public void setActionOnEvent(Action.actionOnEvent A){this.actionOnEvent = A;}

    public Action.actionOnEvent getActionOnEvent(){
        return this.actionOnEvent;
    }

}
