package app.controllers;

import app.entities.MyEventsEvent;
import app.entities.Task;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.MyEventsEventMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class MyEventsController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/myevents", ctx -> eventlist(ctx, connectionPool));
        app.post("/myevents", ctx -> viewEvent(ctx, connectionPool));
    }

    private static void viewEvent(Context ctx, ConnectionPool connectionPool) {
        int id = Integer.parseInt(ctx.formParam("id"));
        ctx.render("/myevents/index.html");
    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/myevents/index.html");
    }

    private static void eventlist(Context ctx, ConnectionPool connectionPool)
    {
        try{
            List<MyEventsEvent> eventList = MyEventsEventMapper.getAllEvents(connectionPool);
            ctx.attribute("eventList", eventList);
            ctx.render("/myevents/eventlist.html");

        } catch (DatabaseException e)  {
            ctx.attribute("message", "Error in Task values, please try again");
        }


    }
}