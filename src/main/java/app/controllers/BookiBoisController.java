package app.controllers;

import app.entities.Timeslot;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.UserMapper;
import app.persistence.BookingMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;

public class BookiBoisController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/bookibois", ctx -> index(ctx));

        app.get("/bookingsite", ctx -> ctx.render("/bookibois/bookingsite.html"));
        app.post("/createbooking", ctx -> createBooking(ctx, connectionPool));
        app.get("/createbooking", ctx -> ctx.render("/bookibois/bookingsite.html"));

        app.post("/gettimeslots", ctx -> checkTimeslot(ctx, connectionPool));
    }

    private static void index(Context ctx)
    {
        ctx.render("/bookibois/bookingsite.html");
    }

    private static void checkTimeslot(Context ctx, ConnectionPool connectionPool) {
        try {
            String dato = ctx.formParam("dato");
            String behandling = ctx.formParam("behandling");
            ctx.attribute("behandling", behandling);
            ArrayList<Timeslot> taskList = BookingMapper.checkTime(dato, behandling, connectionPool);
            ctx.attribute("taskList", taskList);
            ctx.attribute("dato", dato);
            ctx.render("/bookibois/bookingsite.html");

        } catch (NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }


    public static void createBooking(Context ctx, ConnectionPool connectionPool) {
        // Hent form parametre
        String behandling = ctx.formParam("behandling");
        String date = ctx.formParam("dato");
        String time = ctx.formParam("timeslot");
        String behandler = ctx.formParam("behandler");
        String navn = ctx.formParam("name");
        String tlfnummer = ctx.formParam("tlf");

        // Check om bruger findes i DB med de angivne username + password
        try {
            BookingMapper.createBooking(behandling, date, time, behandler, navn, tlfnummer, connectionPool);
            ctx.attribute("message", "Din booking er hermed bekræftet");
            ctx.render("/bookibois/bookingsite.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
        }
    }

}

