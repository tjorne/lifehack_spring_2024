package app.controllers;


import app.entities.Restaurant;
import app.entities.RestaurantList;
import app.persistence.ConnectionPool;
import app.persistence.RestaurantMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.util.Map;

public class RestaurantFinderController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("restaurantFinder", ctx -> ctx.render("restaurantFinder/startPage.html"));
        app.post("restaurantFinder/chosenCuisine", ctx -> {
            getRestaurants(ctx);
        });
    }

    private static void getRestaurants(Context context) {
        String chosenCuisine = context.formParam("chosenCuisine");
        RestaurantMapper.createRestaurants();
        List<Restaurant> allRestaurants = RestaurantList.getRestaurantsWithCertainCuisine(chosenCuisine);
        context.render("restaurantFinder/searchresults.html", Map.of("allRestaurants", allRestaurants));
    }
}