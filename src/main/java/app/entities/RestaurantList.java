package app.entities;

import java.util.ArrayList;
import java.util.List;

public class RestaurantList {
    private static List<Restaurant> allRestaurants = new ArrayList<Restaurant>();

    public static void addToRestaurantList(Restaurant restaurant) {
        allRestaurants.add(restaurant);
    }

    public static List<Restaurant> getRestaurantsWithCertainCuisine(String cuisine) {
        List<Restaurant> restaurantsWithCertainCuisine = new ArrayList<Restaurant>();
        for (Restaurant r: allRestaurants) {
            if (r.getTypeOfCuisine().equals(cuisine)) {
                restaurantsWithCertainCuisine.add(r);
            }
        }
        return restaurantsWithCertainCuisine;
    }
}
