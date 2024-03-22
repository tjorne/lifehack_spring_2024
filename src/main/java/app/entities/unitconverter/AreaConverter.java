package app.entities.unitconverter;


public class AreaConverter
{
    public double squareMetersToSquareKilometers(double squareMeters)
    {
        return squareMeters / 1000000;
    }

    public double squareMetersToSquareYards(double squareMeters)
    {
        return squareMeters * 1.19599;
    }

    public double squareMetersToSquareMiles(double squareMeters)
    {
        return squareMeters / 2589988;
    }

    public double squareKilometersToSquareMeters(double squareKilometers)
    {
        return squareKilometers * 1000000;
    }

    public double squareKilometersToSquareYards(double squareKilometers)
    {
        return squareKilometers * 1195990.05;
    }

    public double squareKilometersToSquareMiles(double squareKilometers)
    {
        return squareKilometers * 0.386102;
    }

    public double squareYardsToSquareMeters(double squareYards)
    {
        return squareYards * 0.836127;
    }

    public double squareYardsToSquareKilometers(double squareYards)
    {
        return squareYards * 8.36127e-7;
    }

    public double squareYardsToSquareMiles(double squareYards)
    {
        return squareYards * 3.2283e-7;
    }

    public double squareMilesToSquareMeters(double squareMiles)
    {
        return squareMiles * 2589988.11;
    }

    public double squareMilesToSquareKilometers(double squareMiles)
    {
        return squareMiles * 2.58998811;
    }

    public double squareMilesToSquareYards(double squareMiles)
    {
        return squareMiles * 3097600;
    }
}
