package app.entities.unitconverter;

//note tal med _ altså 1_000_000 er for readability
// kun methoder for square meter til kilometer,yards,miles
public class AreaConverter {
    double areaInput = 0;
    public void setAreaInput(double areaInput) {
        this.areaInput = areaInput;
    }
    public static double squareMetersToSquareKilometers(double areaInput) {
        return areaInput / 1_000_000;
    }

    public static double squareMetersToSquareYards(double areaInput) {
        return areaInput * 1.19599;
    }
    public static double squareMetersToSquareMiles(double areaInput) {
        return areaInput / 2_589_988;
    }


}
