package ru.levelp.example.web;

import ru.levelp.example.model.Event;

import java.util.List;

public class EventsListBean {
    private String userAgent;
    private List<Event> events;

    public EventsListBean(String userAgent, List<Event> events) {
        this.userAgent = userAgent;
        this.events = events;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public List<Event> getEvents() {
        return events;
    }
}
