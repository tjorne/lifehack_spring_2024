package app.controllers;

import app.entities.unitconverter.*;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class UnitConverterController
{

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/Gruppe-B8-unit-converter", ctx -> index(ctx, connectionPool));
        app.post("/convert", ctx -> convert(ctx));


    }

    private static void convert(Context ctx) {
        double result = 0.0;
        String unitType = ctx.formParam("unit");
        String fromUnit = ctx.formParam("fromUnit");
        String toUnit = ctx.formParam("toUnit"); // Not used in your initial methods, but added for completeness
        double value = Double.parseDouble(ctx.formParam("value"));



        switch (unitType) {
            case "weight":
                WeightConverter weightConverter = new WeightConverter();
                // Assume 'value' is the input number to convert
                if ("kilogram".equals(fromUnit) && "pound".equals(toUnit)) {
                    result = weightConverter.kgToPounds(value);
                    System.out.println(result);
                } else if ("kilogram".equals(fromUnit) && "metricTon".equals(toUnit)) {
                    result = weightConverter.kgToTon(value);
                    System.out.println(result);
                }
                // Add more 'else if' branches for other conversions within 'weight'
                break;
/*
            case "volume":
                VolumeConverter volumeConverter = new VolumeConverter();
                result = volumeConverter.milliliterToLiter(value);
                break;
            case "time":
                TimeConverter timeConverter = new TimeConverter();
                result = timeConverter.daysToHours(value);
                break;
            case "temperature":
                TemperatureConverter temperatureConverter = new TemperatureConverter();
                result = temperatureConverter.celsiusToFahrenheit(value);
                break;
            case "length":
                LengthConverter lengthConverter = new LengthConverter();
                result = lengthConverter.kilometerToMeter(value);
                break;
            case "area":
                AreaConverter areaConverter = new AreaConverter();
                result = areaConverter.squareMetersToSquareKilometers(value);
                break;
            // Add additional cases as necessary*/
            default:
                System.out.println("noget galt");
               // throw new IllegalArgumentException("Unsupported unit type");
        }

        // Set the result in the context to be available in the response or view
        ctx.attribute("selectedUnit", unitType); // Preserve the selected unit type for rendering
        ctx.attribute("result", result); // Pass the result for rendering
        ctx.render("/Gruppe-B8-unit-converter/index.html");
    }

    private static void index(Context ctx, ConnectionPool connectionPool) {
        ctx.render("/Gruppe-B8-unit-converter/index.html");
    }

    // Example conversion method (adjust or add methods as necessary)
    // Assuming kgToTon is part of WeightConverter and returns double
}


