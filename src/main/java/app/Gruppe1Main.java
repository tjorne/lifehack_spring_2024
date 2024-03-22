package app;

import app.config.ThymeleafConfig;
import app.gruppe1Controller.Gruppe1Controller;
import app.gruppe1Entities.Gruppe1CalculateWater;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

import java.util.Scanner;


public class Gruppe1Main
{
    public static void main(String[] args)
    {

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
        }).start(7070);


        app.get("/", ctx -> ctx.render("gruppe1Templates/index.html"));
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
}
