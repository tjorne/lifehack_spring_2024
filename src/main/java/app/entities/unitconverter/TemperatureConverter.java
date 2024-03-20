package app.entities.unitconverter;

public class TemperatureConverter {
    double temperatureInput = 0;
    public void setTemperatureInput(double temperatureInput) {
        this.temperatureInput = temperatureInput;
    }

    public static double celsiusToFahrenheit(double temperatureInput) {
        return (temperatureInput * 9/5) + 32;
    }
    public static double celsiusToKelvin(double temperatureInput) {
        return temperatureInput + 273.15;
    }


    public static double fahrenheitToCelsius(double temperatureInput) {
        return (temperatureInput - 32) * 5/9;
    }
    public static double fahrenheitToKelvin(double temperatureInput) {
        return (temperatureInput + 459.67) * 5/9;
    }


    public static double kelvinToCelsius(double temperatureInput) {
        return temperatureInput - 273.15;
    }
    public static double kelvinToFahrenheit(double temperatureInput) {
        return (temperatureInput * 9/5) - 459.67;
    }


}
