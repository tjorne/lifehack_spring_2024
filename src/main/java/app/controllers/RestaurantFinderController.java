package app.controllers;


import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class RestaurantFinderController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("restaurantFinder", ctx -> ctx.render("restaurantFinder/startPage.html"));

    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("templates/restaurantFinder/startPage.html");
    }
}