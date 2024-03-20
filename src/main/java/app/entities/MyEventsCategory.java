package app.entities;

public class MyEventsCategory {

    private int id;
    private String name;

    public MyEventsCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MyEventsCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
