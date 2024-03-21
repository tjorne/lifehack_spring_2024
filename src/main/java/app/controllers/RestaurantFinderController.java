package app.controllers;


import app.entities.Restaurant;
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
        RestaurantMapper.createRestaurants();
        app.get("restaurantFinder", ctx -> ctx.render("restaurantFinder/startPage.html"));

        app.post("restaurantFinder/chosenCuisine", ctx -> {
            getAllMatchingRestaurants(ctx);
        });

        app.get("restaurantFinder/restaurantchoice/{id}", ctx -> {
            getChosenRestaurant(ctx);
        });
    }

    private static void getChosenRestaurant(Context ctx) {
        int restaurantId = Integer.parseInt(ctx.pathParam("id"));
        Restaurant chosenRestaurant = RestaurantMapper.getRestaurant(restaurantId-1);
        ctx.render("restaurantFinder/restaurantchoice.html", Map.of("chosenRestaurant", chosenRestaurant));
    }

    private static void getAllMatchingRestaurants(Context ctx) {
        String chosenCuisine = ctx.formParam("chosenCuisine");
        List<Restaurant> allMatchingRestaurants = RestaurantMapper.getRestaurantsWithCertainCuisine(chosenCuisine);
        ctx.render("restaurantFinder/searchresults.html", Map.of("allMatchingRestaurants", allMatchingRestaurants));
    }
}