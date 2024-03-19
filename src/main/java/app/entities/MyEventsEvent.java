package app.entities;

import java.time.LocalDateTime;

public class MyEventsEvent {
    private int id;
    private String name;
    private LocalDateTime date;
    private String place;
    private String resume;
    private String details;
    private String link;

    public MyEventsEvent(int id, String name, LocalDateTime date, String place, String resume, String details, String link) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.place = place;
        this.resume = resume;
        this.details = details;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public String getResume() {
        return resume;
    }

    public String getDetails() {
        return details;
    }

    public String getLink() {
        return link;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "MyEventsEvent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", place='" + place + '\'' +
                ", resume='" + resume + '\'' +
                ", details='" + details + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
