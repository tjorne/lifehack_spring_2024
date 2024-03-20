package app.entities.unitconverter;

//note tal med _ altså 1_000_000 er for readability
//tal med L er for at gøre dem til long hvis tallet er for stort
// kun methoder for cubicmeter, liter, mili
public class VolumeConverter {

    double volumeInput = 0;
    public void setVolumeInput(double volumeInput) {
        this.volumeInput = volumeInput;
    }

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
        return milliliters / 1_000_000;
    }
    public static double milliliterToLiter(double milliliters) {
        return milliliters / 1000;
    }

}
