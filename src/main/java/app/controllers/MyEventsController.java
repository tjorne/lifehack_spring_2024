package app.controllers;

import app.entities.MyEventsCategory;
import app.entities.MyEventsEvent;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.MyEventsEventMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class MyEventsController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/myevents", ctx -> eventlist(ctx, connectionPool));
        app.post("/myevents", ctx -> viewEvent(ctx, connectionPool));
        app.get("/myevents/favourites", ctx -> viewUserFavourites(ctx, connectionPool));
    }

    private static void searchResults(Context ctx, ConnectionPool connectionPool) {
        String zipcode = ctx.formParam("zipcode");
        String[] selectedCategories = ctx.formParams("category").toArray(new String[0]);

        List<MyEventsCategory> categories = new ArrayList<>();
        for (String categoryName : selectedCategories) {
            MyEventsCategory category = new MyEventsCategory(-1, categoryName);
            categories.add(category);
        }

        try {
            List<MyEventsEvent> events = MyEventsEventMapper.getAllEventsByZip(Integer.parseInt(zipcode), categories, connectionPool);
            ctx.attribute("events", events);
            ctx.render("/myevents/eventlist.html");

        } catch (DatabaseException e) {
            ctx.attribute("message", "Something went wrong - try again");
        }
    }

    private static void viewEvent(Context ctx, ConnectionPool connectionPool) {
        int id = Integer.parseInt(ctx.formParam("id"));
        ctx.render("/myevents/index.html");
    }

    private static void index(Context ctx, ConnectionPool connectionPool) {
        ctx.render("/myevents/index.html");
    }

    private static void eventlist(Context ctx, ConnectionPool connectionPool) {
        try {
            List<MyEventsEvent> eventList = MyEventsEventMapper.getAllEvents(connectionPool);
            ctx.attribute("eventList", eventList);
            ctx.render("/myevents/eventlist.html");

        } catch (DatabaseException e) {
            ctx.attribute("message", "Error in Task values, please try again");
        }
    }

    private static void viewUserFavourites(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        User user = ctx.sessionAttribute("currentUser");

        try {
            List<MyEventsEvent> favouriteEvents = MyEventsEventMapper.getAllUserFavoriteEvents(user.getUserId(), connectionPool);
            ctx.attribute("favouriteList", favouriteEvents);
            ctx.render("favourite.html");

        } catch (DatabaseException e) {
            throw new DatabaseException("Error in trying to load user favourite list" + e.getMessage());
        }
    }
}