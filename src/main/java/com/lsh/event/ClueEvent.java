package com.lsh.event;

import org.springframework.context.ApplicationEvent;
public  class ClueEvent extends ApplicationEvent {
    private String message;

    public ClueEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
