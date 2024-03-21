package app.entities;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyEventsCategory category = (MyEventsCategory) o;
        return getId() == category.getId() && Objects.equals(getName(), category.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "MyEventsCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
