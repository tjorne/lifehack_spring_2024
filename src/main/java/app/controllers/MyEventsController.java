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

import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyEventsController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        MyEventsEventMapper.setMapperSchema("my_events");

        app.get("/myevents", ctx -> index(ctx, connectionPool));
        app.post("/myevents/search", ctx -> searchResults(ctx, connectionPool));
        app.post("/event", ctx -> eventOverview(ctx, connectionPool));

        app.get("/myevents/favorites", ctx -> viewUserFavourites(ctx, connectionPool));
        app.post("/myevents/addtofavorite", ctx -> addToFavorite(ctx, connectionPool));
        app.post("/myevents/removefromfavorite", ctx -> removeFromFavorite(ctx, connectionPool));
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

    private static void removeFromFavorite(Context ctx, ConnectionPool connectionPool) {

    }

    private static void addToFavorite(Context ctx, ConnectionPool connectionPool) {

        User user = ctx.sessionAttribute("currentUser");

        try {
            int event_id = Integer.parseInt(ctx.formParam("event_id"));
            MyEventsEventMapper.addEventToUserFavorites(user.getUserId(), event_id, connectionPool);

            List<MyEventsEvent> eventList = MyEventsEventMapper.getAllEvents(connectionPool);
            List<MyEventsEvent> userFavoriteEventList = MyEventsEventMapper.getAllUserFavoriteEvents(user.getUserId(), connectionPool);

            Map<MyEventsEvent, Boolean> eventsMap = new LinkedHashMap<>();

            for (MyEventsEvent event : eventList) {
                if (userFavoriteEventList.contains(event)) {
                    eventsMap.put(event, true);
                } else {
                    eventsMap.put(event, false);
                }
            }
            ctx.attribute("eventMap", eventsMap);
            //ctx.attribute("eventList", eventList);
            ctx.render("favorites.html");

        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("/myevents/index.html");
        }
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

    private static void eventOverview(Context ctx, ConnectionPool connectionPool) {
        String eventId = ctx.formParam("id");

        if (eventId == null) {
            System.out.println("Error: id query parameter is null.");
            return;
        }

        try {
            MyEventsEvent event = MyEventsEventMapper.getEventById(Integer.parseInt(eventId), connectionPool);

            ctx.attribute("eventItem", event);
            ctx.render("/myevents/eventbyid.html");
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (DatabaseException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    private static void viewUserFavourites(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        User user = ctx.sessionAttribute("currentUser");

        try {
            List<MyEventsEvent> favoriteEvents = MyEventsEventMapper.getAllUserFavoriteEvents(user.getUserId(), connectionPool);
            ctx.attribute("favorites", favoriteEvents);
            ctx.render("/myevents/favorites.html");

        } catch (DatabaseException e) {
            throw new DatabaseException("Error in trying to load user favourite list" + e.getMessage());
        }
    }
}