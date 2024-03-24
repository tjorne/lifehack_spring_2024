package app;

import app.config.ThymeleafConfig;
import app.controllers.DrinkController;
import app.controllers.JeopardyController;
import app.controllers.BookiBoisController;
import app.controllers.RestaurantFinderController;
import app.controllers.MyEventsController;
import app.controllers.QuotesGeneratorController;
import app.controllers.TimeZonesController;
import app.controllers.UnitConverterController;
import app.controllers.UserController;
import app.entities.Drink;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.DrinksMapper;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

import java.util.List;
import java.util.Map;

public class Main 
{
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "lifehack";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);

    public static void main(String[] args)
    {
        // Initializing Javalin and Jetty webserver
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
            config.staticFiles.add("/templates");
        }).start(7070);

        // Routing
        app.get("/", ctx -> ctx.render("index.html"));
        UserController.addRoutes(app, connectionPool);
        TimeZonesController.addRoutes(app, connectionPool);
        DrinkController.addRoutes(app, connectionPool);
        JeopardyController.addRoutes(app, connectionPool);
        BookiBoisController.addRoutes(app, connectionPool);
        RestaurantFinderController.addRoutes(app, connectionPool);
        UnitConverterController.addRoutes(app, connectionPool);
        MyEventsController.addRoutes(app, connectionPool);
        QuotesGeneratorController.addRoutes(app, connectionPool);
    }
}