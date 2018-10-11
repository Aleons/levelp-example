package ru.levelp.example.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EVENTS")
public class Event {
    @Id
    @GeneratedValue
    private int id;

    @Column
    @Length(min = 5, max = 50)
    private String title;

    @Column(length = 50)
    @Length(min = 5, max = 50)
    private String description;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @Future
    private Date start;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @Future
    private Date end;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    public Event() {
    }

    public Event(String title, String description, Date start, Date end) {
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
