package jpa.domain;

import java.sql.Date;
import javax.persistence.*;



@Entity
public class Appointment {


    private Long id;
    private String url;
    private String title;
    private Worker organizer;
    private User participant;
    private Date start;
    private Date end;

    public Appointment(String title, Worker organizer) {
        this.title = title;
        this.organizer = organizer;
    }

    public Appointment() {
        this.title = "Appointment " + this.getId();
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @GeneratedValue
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = this.title + this.id + url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @OneToOne
    public Worker getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Worker organizer) {
        this.organizer = organizer;
    }

    @OneToOne
    public User getParticipant() {
        return participant;
    }

    public void setParticipant(User participant) {
        this.participant = participant;
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
