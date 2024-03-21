package app.entities.unitconverter;

public class LengthConverter
{
    public double meterToKilometer(double meter)
        {
        return meter / 1000.0;
        }

    public double meterToMiles(double meter)
        {
        return meter * 0.000621371;
        }

    public double kilometerToMeter(double kilometer)
        {
        return kilometer * 1000.0;
        }

    public double kilometerToMiles(double kilometer)
        {
        return kilometer * 0.621371;
        }

    public double milesToMeter(double miles)
        {
        return miles / 0.000621371;
        }

    public double milesToKilometer(double miles)
        {
        return miles / 0.621371;
        }


}