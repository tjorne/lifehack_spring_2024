package app.entities.unitconverter;

public class VolumeConverter
{
    public double cubicMeterToLiter(double cubicMeters)
    {
        return cubicMeters * 1000;
    }

    public double cubicMeterToMilliliter(double cubicMeters)
    {
        return cubicMeters * 1000000;
    }

    public double literToCubicMeter(double liters)
    {
        return liters / 1000;
    }

    public double literToMilliliter(double liters)
    {
        return liters * 1000;
    }

    public double milliliterToCubicMeter(double milliliters)
    {
        return milliliters / 1000000;
    }

    public double milliliterToLiter(double milliliters)
    {
        return milliliters / 1000;
    }
}
