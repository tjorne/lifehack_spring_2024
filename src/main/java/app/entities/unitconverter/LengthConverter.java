package app.entities.unitconverter;

public class LengthConverter
{
    public static double meterToKilometer(double meter)
        {
        return meter / 1000.0;
        }

    public static double meterToMiles(double meter)
        {
        return meter * 0.000621371;
        }

    public static double kilometerToMeter(double kilometer)
        {
        return kilometer * 1000.0;
        }

    public static double kilometerToMiles(double kilometer)
        {
        return kilometer * 0.621371;
        }

    public static double milesToMeter(double miles)
        {
        return miles / 0.000621371;
        }

    public static double milesToKilometer(double miles)
        {
        return miles / 0.621371;
        }


}