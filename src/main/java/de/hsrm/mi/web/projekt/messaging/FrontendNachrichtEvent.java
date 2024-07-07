package de.hsrm.mi.web.projekt.messaging;

public class FrontendNachrichtEvent {
    
    public enum EventTyp {
        TOUR
    }

    public enum Operation {
        CREATE, UPDATE, DELETE
    }

    public EventTyp typ;
    public long id;
    public Operation operation;

    public FrontendNachrichtEvent(EventTyp typ, long id, Operation operation){
        this.typ = typ;
        this.id = id;
        this.operation = operation;
    }
}
