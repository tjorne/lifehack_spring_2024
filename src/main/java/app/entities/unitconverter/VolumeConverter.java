package app.entities.unitconverter;

public class VolumeConverter {

    public static double cubicMeterToLiter(double cubicMeters) {
        return cubicMeters * 1000;
    }
    public static double cubicMeterToMilliliter(double cubicMeters) {
        return cubicMeters * 1000000;
    }


    public static double literToCubicMeter(double liters) {
        return liters / 1000;
    }
    public static double literToMilliliter(double liters) {
        return liters * 1000;
    }


    public static double milliliterToCubicMeter(double milliliters) {
        return milliliters / 1000000;
    }
    public static double milliliterToLiter(double milliliters) {
        return milliliters / 1000;
    }

}
