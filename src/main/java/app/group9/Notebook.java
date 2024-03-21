package app.group9;

import java.util.ArrayList;
import java.util.List;

public class Notebook {
    private List<Notes> notes;

    public Notebook() {
        this.notes = new ArrayList<>();
    }
    public void addNote(Notes note) {
        notes.add(note);
    }

    public List<Notes> getNotes() {
        return notes;
    }
}

