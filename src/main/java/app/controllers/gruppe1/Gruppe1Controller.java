package app.controllers.gruppe1;

import app.entities.gruppe1.Gruppe1CalculateWater;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class Gruppe1Controller
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/gruppe1Templates", ctx -> index(ctx, connectionPool));
        app.post("/waterIntakeMessage", ctx -> {
            float waterConsumed = Float.parseFloat(ctx.formParam("water"));
            Gruppe1CalculateWater calculateWater = new Gruppe1CalculateWater();
            calculateWater.setWaterConsumed(waterConsumed);

            // Beregn resterende vandbehov og gem resultatet i sessionen
            ctx.sessionAttribute("waterIntake", calculateWater.toString());

            // Send brugeren tilbage til startsiden
            ctx.redirect("/");
        });
    }
    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/gruppe1Templates/index.html");
    }
}