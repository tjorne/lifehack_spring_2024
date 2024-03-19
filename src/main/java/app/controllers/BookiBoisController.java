package app.controllers;

import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class BookiBoisController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/BookiBois", ctx -> index(ctx, connectionPool));

        app.get("/bookingsite", ctx -> ctx.render("/bookiBois/BookingSite.html"));
    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/BookiBois/index.html");
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


    public static void createBooking(Context ctx, ConnectionPool connectionPool) {
        // Hent form parametre
        String username = ctx.formParam("titel");
        String behandling = ctx.formParam("behandling");
        String dato = ctx.formParam("date");
        String time = ctx.formParam("time");
        String behandler = ctx.formParam("behandler");
        String navn = ctx.formParam("name");
        String tlfnummer = ctx.formParam("tlf");

        // Check om bruger findes i DB med de angivne username + password
        try {
            BookingMapper.create(username, behandling, dato, time, behandler, navn, tlfnummer, connectionPool);
            //ctx.sessionAttribute("currentUser", user);
            // Hvis ja, send videre til forsiden med login besked
            ctx.attribute("message", "Din booking er bekræftet");
            //ctx.render("index.html");
        } catch (DatabaseException e) {
            // Hvis nej, send tilbage til login side med fejl besked
            ctx.attribute("message", e.getMessage());
            //ctx.render("index.html");
        }

    }

}

