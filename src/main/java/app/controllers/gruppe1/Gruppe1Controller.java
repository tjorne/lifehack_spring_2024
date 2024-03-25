package app.controllers.gruppe1;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class Gruppe1Controller
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/projectname", ctx -> index(ctx, connectionPool));
    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/projectname/index.html");
    }
}