package app.controllers;

import app.entities.Drink;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.DrinksMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.util.Map;

public class DrinkController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/drink", ctx -> index(ctx, connectionPool));

        app.post("/search", ctx -> {
            String ingredients = ctx.formParam("ingredients");
            try {
                List<Drink> matchingDrinks = DrinksMapper.searchDrinks(ingredients, connectionPool);
                ctx.render("/drink/index.html", Map.of("matchingDrinks", matchingDrinks));
            } catch (DatabaseException e) {
                ctx.status(500).result("Error searching for drinks: " + e.getMessage());
            }
        });
    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/drink/index.html");
    }
}