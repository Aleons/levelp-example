package ru.levelp.example.dao;

import ru.levelp.example.model.Event;

import java.util.Date;
import java.util.List;

public interface EventsDAO {
    List<Event> findEvents(Date date);
    void add(Event event);
}
