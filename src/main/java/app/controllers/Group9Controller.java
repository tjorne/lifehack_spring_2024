package app.controllers;

import app.entities.User;
import app.exceptions.DatabaseException;
import app.group9.Notes;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;
import app.persistence.NotesMapper;
import java.util.List;

public class Group9Controller {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("/group9/index.html", ctx -> loadNotes(ctx, connectionPool));


        app.get("/addnote", ctx -> ctx.render("group9/index.html"));
        app.post("/addnote", ctx -> addnotes(ctx, connectionPool));

    }

    public static void loadNotes(Context ctx, ConnectionPool connectionpool) {
        User user = ctx.sessionAttribute("currentUser");
        try {
            List<Notes> notesList = NotesMapper.getAllNotesPerUser(user.getUserId(), connectionpool);
            ctx.sessionAttribute("notesList", notesList);

            ctx.render("group9/index.html");

        } catch (DatabaseException e) {
            ctx.attribute("message", "Noget gik galt. Prøv eventuelt igen!");
            ctx.render("group9/index.html");
        }

    }
    private static void addnotes(Context ctx, ConnectionPool connectionpool) {

        String notes = ctx.formParam("notes");
        String title = ctx.formParam("title");
        String content = ctx.formParam("content");



        User user = ctx.sessionAttribute("currentUser");
        try {
            Notes newNotes = NotesMapper.createNotes(user, title, content, connectionpool);
            List<Notes> notesList = NotesMapper.getAllNotesPerUser(user.getUserId(), connectionpool);
            ctx.sessionAttribute("notesList", notesList);

            ctx.render("group9/index.html");

        } catch (DatabaseException e) {
            ctx.attribute("message", "Noget gik galt. Prøv eventuelt igen!");
            ctx.render("group9/index.html");
        }
    }
}