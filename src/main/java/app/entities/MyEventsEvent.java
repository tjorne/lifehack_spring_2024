package app.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class MyEventsEvent {
    private int id;
    private String name;
    private LocalDateTime date;
    private String place;
    private int zip;
    private String city;
    private String resume;
    private String details;
    private String link;

    public MyEventsEvent(int id, String name, LocalDateTime date, String place, int zip, String city, String resume, String details, String link) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.place = place;
        this.zip = zip;
        this.city = city;
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

    public int getZip() {
        return zip;
    }

    public String getCity() {
        return city;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyEventsEvent event = (MyEventsEvent) o;
        return getId() == event.getId() && getZip() == event.getZip() && Objects.equals(getName(), event.getName()) && Objects.equals(getDate(), event.getDate()) && Objects.equals(getPlace(), event.getPlace()) && Objects.equals(getCity(), event.getCity()) && Objects.equals(getResume(), event.getResume()) && Objects.equals(getDetails(), event.getDetails()) && Objects.equals(getLink(), event.getLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDate(), getPlace(), getZip(), getCity(), getResume(), getDetails(), getLink());
    }

    @Override
    public String toString() {
        return "MyEventsEvent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", place='" + place + '\'' +
                ", zip=" + zip +
                ", city='" + city + '\'' +
                ", resume='" + resume + '\'' +
                ", details='" + details + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}