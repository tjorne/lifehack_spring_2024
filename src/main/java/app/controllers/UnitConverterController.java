package app.controllers;

import app.entities.unitconverter.WeightConverter;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class UnitConverterController
{

    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/Gruppe-B8-unit-converter", ctx -> index(ctx, connectionPool));
        app.post("/convert", ctx -> convert(ctx));


    }

    private static void convert(Context ctx)
    {
        double result = 0.0;
        String unitType = ctx.formParam("unit");
        String fromUnit = ctx.formParam("fromUnit");
        String toUnit = ctx.formParam("toUnit");
        double value = Double.parseDouble(ctx.formParam("value"));


        switch (unitType)
        {
            case "weight":
                WeightConverter weightConverter = new WeightConverter();

                switch (fromUnit)
                {
                    case "kilogram":
                        result = weightConverter.kgToPounds(value);
                        switch (toUnit)
                        {
                            case "metricton":
                                result = weightConverter.kgToTon(value);
                        }
                }
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


        ctx.attribute("selectedUnit", unitType);
        ctx.attribute("result", result);
        ctx.render("/Gruppe-B8-unit-converter/index.html");
    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/Gruppe-B8-unit-converter/index.html");
    }

}


