package fr.univ_lyon1.info.m1.mes.model;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private List<String> messages = new ArrayList<>();
    private String text;

    public Message(final String text) {
        this.text = text;
        messages.add(text);
        System.out.println(messages);
    }

    public void addMessage(final String textMessage) {
        messages.add(textMessage);
    }

    public List<String> getMessages() {
        return this.messages;
    }

    public String getTextMessage() {
        return text;
    }

    public String messageFromPatient() {
        return text;
    }
}
