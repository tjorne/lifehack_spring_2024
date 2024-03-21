package app.entities.unitconverter;

public class WeightConverter
{
    public double kgToTon(double kg)
        {
        return kg / 1000;
        }

    public double kgToPounds(double kg)
        {
        return kg * 2.20462;
        }

    public double tonToKg(double ton)
        {
        return ton * 1000;
        }

    public double tonToPounds(double ton)
        {
        return ton * 2204.62;
        }

    public double poundsToKg(double pounds)
        {
        return pounds / 2.20462;
        }

    public double poundsToTon(double pounds)
        {
        return pounds / 2204.62;
        }
}