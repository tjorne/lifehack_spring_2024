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
        app.get("/bookibois", ctx -> index(ctx, connectionPool));

        app.get("/bookingsite", ctx -> ctx.render("/bookibois/bookingsite.html"));
        app.post("/createbooking", ctx -> createBooking(ctx, connectionPool));
        app.get("/createbooking", ctx -> ctx.render("/bookibois/bookingsite.html"));

        app.post("/gettimeslots", ctx -> checkTimeslot(ctx, connectionPool));
        app.post("/selectTimeslot", ctx -> selectTimeslot(ctx));
    }

    private static void selectTimeslot(Context ctx){
        String timeslot = ctx.formParam("timeslot");
        ctx.sessionAttribute("timeslot",timeslot);
        //ctx.render("/bookibois/bookingsite.html");
    }
    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/bookibois/bookingsite.html");
    }

    private static void checkTimeslot(Context ctx, ConnectionPool connectionPool) {
        //User user = ctx.sessionAttribute("currentUser");
        try {
            String dato = ctx.formParam("dato");
            System.out.println(dato);
            String behandling = ctx.formParam("behandling");
            //BookingMapper.checkTime(dato, behandling, connectionPool);
            ArrayList<Timeslot> taskList = BookingMapper.checkTime(dato, behandling, connectionPool);

            ctx.attribute("taskList", taskList);
            ctx.sessionAttribute("dato", dato);
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
        //String username = ctx.formParam("titel");
        String behandling = ctx.formParam("behandling");
        String date = ctx.sessionAttribute("dato");
        //String time = ctx.formParam("timeslot");
        String time = ctx.sessionAttribute("timeslot");
        String behandler = ctx.formParam("behandler");
        String navn = ctx.formParam("name");
        String tlfnummer = ctx.formParam("tlf");

        // Check om bruger findes i DB med de angivne username + password
        try {
            BookingMapper.createBooking(behandling, date, time, behandler, navn, tlfnummer, connectionPool);
            //ctx.sessionAttribute("currentUser", user);
            // Hvis ja, send videre til forsiden med login besked
            ctx.attribute("message", "Din booking er hermed bekræftet");
            ctx.render("/bookibois/bookingsite.html");
        } catch (DatabaseException e) {
            // Hvis nej, send tilbage til login side med fejl besked
            ctx.attribute("message", e.getMessage());
            //ctx.render("index.html");
        }

    }

    private static void createUser(Context ctx, ConnectionPool connectionPool) {
        // Hent form parametre
        String username = ctx.formParam("username");
        String password1 = ctx.formParam("password1");
        String password2 = ctx.formParam("password2");

        if (password1.equals(password2)) {
            try {
                UserMapper.createuser(username, password1, connectionPool);
                ctx.attribute("message", "Du er hermed oprettet med brugernavn: " + username +
                        ". Nu skal du logge på.");
                ctx.render("index.html");
            } catch (DatabaseException e) {
                ctx.attribute("message", "Dit brugernavn findes allerede. Prøv igen, eller log ind");
                ctx.render("createuser.html");
            }
        } else {
            ctx.attribute("message", "Dine to passwords matcher ikke! Prøv igen");
            ctx.render("createuser.html");
        }

    }

    private static void logout(Context ctx) {
        ctx.req().getSession().invalidate();
        ctx.redirect("/");
    }


    public static void login(Context ctx, ConnectionPool connectionPool) {
        // Hent form parametre
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        // Check om bruger findes i DB med de angivne username + password
        try {
            User user = UserMapper.login(username, password, connectionPool);
            ctx.sessionAttribute("currentUser", user);
            // Hvis ja, send videre til forsiden med login besked
            ctx.attribute("message", "Du er nu logget ind");
            ctx.render("index.html");
        } catch (DatabaseException e) {
            // Hvis nej, send tilbage til login side med fejl besked
            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");
        }

    }




}

