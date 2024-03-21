package app.entities;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MyEventsEvent {
    private int id;
    private String name;
    private LocalDateTime date;
    private String place;
    private int zip;
    private String resume;
    private String details;
    private String link;

    public MyEventsEvent(int id, String name, LocalDateTime date, String place, int zip, String resume, String details, String link) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.place = place;
        this.zip = zip;
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

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd" + " || " + "HH:mm");
        return date.format(formatter);
    }

    public String getPlace() {
        return place;
    }

    public int getZip() {
        return zip;
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

    public void setZip(int zip) {
        this.zip = zip;
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
                ", zip=" + zip +
                ", resume='" + resume + '\'' +
                ", details='" + details + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}