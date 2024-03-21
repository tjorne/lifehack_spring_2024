package app.entities.unitconverter;


public class AreaConverter {
    public static double squareMetersToSquareKilometers(double squareMeters) {
        return squareMeters / 1000000;
    }
    public static double squareMetersToSquareYards(double squareMeters) {
        return squareMeters * 1.19599;
    }
    public static double squareMetersToSquareMiles(double squareMeters) {
        return squareMeters / 2589988;
    }


    public static double squareKilometersToSquareMeters(double squareKilometers) {
        return squareKilometers * 1000000;
    }
    public static double squareKilometersToSquareYards(double squareKilometers) {
        return squareKilometers * 1195990.05;
    }
    public static double squareKilometersToSquareMiles(double squareKilometers) {
        return squareKilometers * 0.386102;
    }


    public static double squareYardsToSquareMeters(double squareYards) {
        return squareYards * 0.836127;
    }
    public static double squareYardsToSquareKilometers(double squareYards) {
        return squareYards * 8.36127e-7;
    }
    public static double squareYardsToSquareMiles(double squareYards) {
        return squareYards * 3.2283e-7;
    }


    public static double squareMilesToSquareMeters(double squareMiles) {
        return squareMiles * 2589988.11;
    }
    public static double squareMilesToSquareKilometers(double squareMiles) {
        return squareMiles * 2.58998811;
    }
    public static double squareMilesToSquareYards(double squareMiles) {
        return squareMiles * 3097600;
    }


}
