package app.controllers;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class UnitConverterController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/Gruppe-B8-unit-converter", ctx -> index(ctx, connectionPool));
    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/Gruppe-B8-unit-converter/index.html");
    }
}
