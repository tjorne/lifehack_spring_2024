package app.controllers;

import app.entities.MyEventsCategory;
import app.entities.MyEventsEvent;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.MyEventsCategoryMapper;
import app.persistence.MyEventsEventMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class MyEventsController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        MyEventsEventMapper.setMapperSchema("my_events");

        app.get("/myevents", ctx -> index(ctx, connectionPool));
        app.post("/myevents/search", ctx -> searchResults(ctx, connectionPool));
        app.post("/event", ctx -> eventOverview(ctx, connectionPool));
        app.get("/myevents/favorites", ctx -> viewUserFavourites(ctx, connectionPool));
    }

    private static void searchResults(Context ctx, ConnectionPool connectionPool) {
        String searchZip = ctx.formParam("zipcode");
        List<String> selectedCategories = ctx.formParams("category");
        int zipcode = -1;

        try {
            zipcode = Integer.parseInt(searchZip);
        } catch (NullPointerException | NumberFormatException e) {
            ctx.attribute("message", "You must provide a zip code to search.");
            index(ctx, connectionPool);
            return;
        }

        List<MyEventsCategory> searchCategories = new ArrayList<>();

        try {
            for (MyEventsCategory category : MyEventsCategoryMapper.getAllCategories(connectionPool)) {
                if (selectedCategories.contains(category.getName())) {
                    searchCategories.add(category);
                }
            }
        } catch (DatabaseException e) {
            ctx.attribute("message", "Failed to obtain categories");
            ctx.render("/myevents/index.html");
        }

        try {
            List<MyEventsEvent> events = MyEventsEventMapper.getAllEventsByZip(zipcode, searchCategories, connectionPool);
            ctx.attribute("events", events);
            ctx.attribute("message", "");
            ctx.render("/myevents/eventlist.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", "Failed to get events");
            ctx.render("/myevents/index.html");
        }
    }

    private static void index(Context ctx, ConnectionPool connectionPool) {
        try {
            List<MyEventsCategory> categories = MyEventsCategoryMapper.getAllCategories(connectionPool);
            ctx.attribute("categories", categories);
            ctx.render("/myevents/index.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", "Failed to get events");
            ctx.render("/myevents/index.html");
        }
    }

    private static void eventOverview(Context ctx, ConnectionPool connectionPool) {
        String eventId = ctx.formParam("id");

        if (eventId == null) {
            System.out.println("Error: id query parameter is null.");
            return;
        }

        try {
            MyEventsEvent event = MyEventsEventMapper.getEventById(Integer.parseInt(eventId), connectionPool);

            ctx.attribute("eventItem", event);
            ctx.attribute("message", "");
            ctx.render("/myevents/eventbyid.html");
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (DatabaseException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    private static void viewUserFavourites(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        User user = ctx.sessionAttribute("currentUser");

        if (user == null) {
            ctx.attribute("message", "You must be logged in to view your favorites.");
            index(ctx, connectionPool);
            return;
        }

        try {
            List<MyEventsEvent> favoriteEvents = MyEventsEventMapper.getAllUserFavoriteEvents(user.getUserId(), connectionPool);
            ctx.attribute("favorites", favoriteEvents);
            ctx.render("/myevents/favorites.html");
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in trying to load user favourite list" + e.getMessage());
        }
    }
}