package ru.levelp.example.web;

import javax.validation.constraints.Size;

public class EventAddFormBean {
    @Size(min = 5, max = 50)
    private String title = "";

    @Size(min = 5, max = 50)
    private String description = "";

    @Size(min = 5, max = 50)
    private String start = "";

    @Size(min = 5, max = 50)
    private String end = start;

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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
