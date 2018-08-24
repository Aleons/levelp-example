package ru.levelp.example.model;

import java.util.Date;

public class Event {
    private String title;
    private String description;
    private Date start;
    private Date end;

    public Event() {
    }

    public Event(String title, String description, Date start, Date end) {
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
