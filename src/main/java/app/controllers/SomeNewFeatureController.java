package app.controllers;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;

public class SomeNewFeatureController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/someNewFeature", ctx -> ctx.render("someNewFeature.html"));
    }
}
