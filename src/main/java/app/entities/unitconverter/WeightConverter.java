package app.entities.unitconverter;

public class WeightConverter
{
    public static double kgToTon(double kg)
        {
        return kg / 1000;
        }

    public static double kgToPounds(double kg)
        {
        return kg * 2.20462;
        }

    public static double tonToKg(double ton)
        {
        return ton * 1000;
        }

    public static double tonToPounds(double ton)
        {
        return ton * 2204.62;
        }

    public static double poundsToKg(double pounds)
        {
        return pounds / 2.20462;
        }

    public static double poundsToTon(double pounds)
        {
        return pounds / 2204.62;
        }
}