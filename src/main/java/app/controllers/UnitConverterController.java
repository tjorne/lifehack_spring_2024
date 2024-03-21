package app.controllers;

import app.entities.unitconverter.*;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class UnitConverterController
{


    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
        {
        app.get("/Gruppe-B8-unit-converter", ctx -> index(ctx, connectionPool));
        app.post("/areaconverter", ctx -> areaConverter(ctx));
        app.post("/lengthconverter", ctx -> lengthConverter(ctx));
        app.post("/temperatureconverter", ctx -> temperatureConverter(ctx));
        app.post("/timeconverter", ctx -> timeConverter(ctx));
        app.post("/volumeconverter", ctx -> volumeConverter(ctx));
        app.post("/Weightconverter", ctx -> weightConverter(ctx));
        }

    private static void weightConverter(Context ctx)
        {
        WeightConverter weightConverter = new WeightConverter();
        double input = Double.parseDouble(ctx.formParam("number"));
        weightConverter.kgToTon(input);
        }

    private static void volumeConverter(Context ctx)
        {
        VolumeConverter volumeConverter = new VolumeConverter();
        double input = Double.parseDouble(ctx.formParam("number"));
        volumeConverter.milliliterToLiter(input);
        }

    private static void timeConverter(Context ctx)
        {
        TimeConverter timeConverter = new TimeConverter();
        double input = Double.parseDouble(ctx.formParam("number"));
        timeConverter.daysToHours(input);
        }

    private static void temperatureConverter(Context ctx)
        {
        TemperatureConverter temperatureConverter = new TemperatureConverter();
        double input = Double.parseDouble(ctx.formParam("number"));
        temperatureConverter.celsiusToFahrenheit(input);
        }

    private static void lengthConverter(Context ctx)
        {
        LengthConverter lengthConverter = new LengthConverter();
        double input = Double.parseDouble(ctx.formParam("number"));
        lengthConverter.kilometerToMeter(input);
        }

    public static void areaConverter(Context ctx)
        {
        AreaConverter areaConverter = new AreaConverter();
        double input = Double.parseDouble(ctx.formParam("number"));
        areaConverter.squareMetersToSquareKilometers(input);
        }

    private static void index(Context ctx, ConnectionPool connectionPool)
        {
        ctx.render("/Gruppe-B8-unit-converter/index.html");
        }


}


