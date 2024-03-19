package app.controllers;

import app.persistence.ConnectionPool;
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




}
