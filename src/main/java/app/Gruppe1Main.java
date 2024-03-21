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

        /*
        Gruppe1CalculateWater calculateWater = new Gruppe1CalculateWater();
        Scanner UserInput = new Scanner(System.in);

        System.out.println("Enter the amount of water you have been drinking.");

        float waterConsumed = UserInput.nextFloat();

        calculateWater.setWaterConsumed(waterConsumed);
        calculateWater.calculateRemainingWater();
        System.out.println(calculateWater.toString());

         */

        app.get("/", ctx -> ctx.render("gruppe1Templates/index.html"));
        app.post("/waterIntakeMessage", ctx -> ctx.render("gruppe1Templates/waterIntakeMessage.html"));

    }
}
