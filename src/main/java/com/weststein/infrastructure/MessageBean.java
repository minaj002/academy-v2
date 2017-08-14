package com.weststein.infrastructure;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MessageBean {

    private List<String> messages = new ArrayList();

    public void add(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }
}
