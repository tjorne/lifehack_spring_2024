package app.group9;

import java.time.LocalDate;
import java.util.Date;

public class Notes {
    private String title;
    private String content; //indholdet i noten
    private int userId;
    private LocalDate date;

    public Notes(String title, String content, int userId, int id, LocalDate date) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
