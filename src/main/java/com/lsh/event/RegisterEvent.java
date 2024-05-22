package com.lsh.event;

import com.lsh.domain.User;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


public class RegisterEvent extends ApplicationEvent {

    private User user;

    public RegisterEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
