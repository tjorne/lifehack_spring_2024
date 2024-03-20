package app.controllers;

import app.entities.MyEventsEvent;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.MyEventsEventMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class MyEventsController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/myevents", ctx -> index(ctx, connectionPool));
        app.get("/myevents/event", ctx -> eventOverview(ctx, connectionPool));
    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/myevents/index.html");
    }

    private static void eventOverview(Context ctx, ConnectionPool connectionPool) {
        String eventId = ctx.queryParam("id");

        if (eventId == null) {
            System.out.println("Error: id query parameter is null.");
            return;
        }

        try {
            MyEventsEvent event = MyEventsEventMapper.getEventById(Integer.parseInt(eventId), connectionPool);

            ctx.attribute("eventItem", event);
            ctx.render("eventoverview.html");
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (DatabaseException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
}