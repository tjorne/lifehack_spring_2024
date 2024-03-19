package app.controllers;

import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.DrinksMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

//CHECK PATHS
public class DrinksController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("/search", ctx -> search(ctx, connectionPool));
    }

    private static void search(Context ctx, ConnectionPool connectionPool) {
//get ingredients from request
        String ingredientInput = ctx.formParam("ingredients");

        // call method to search for drinks based on user input
        try {
            List<String> matchingDrinks = DrinksMapper.searchDrinks(ingredientInput, connectionPool);
            ctx.render("/TemplatesGruppe5/gruppe5.html");
            //rename
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("/TemplatesGruppe5/gruppe5.html");
        }
    }
}
