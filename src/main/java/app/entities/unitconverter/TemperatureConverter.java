package app.entities.unitconverter;

public class TemperatureConverter
{

    public double celsiusToFahrenheit(double celsius)
    {
        return (celsius * 9 / 5) + 32;
    }

    public double celsiusToKelvin(double celsius)
    {
        return celsius + 273.15;
    }


    public double fahrenheitToCelsius(double fahrenheit)
    {
        return (fahrenheit - 32) * 5 / 9;
    }

    public double fahrenheitToKelvin(double fahrenheit)
    {
        return (fahrenheit + 459.67) * 5 / 9;
    }


    public double kelvinToCelsius(double kelvin)
    {
        return kelvin - 273.15;
    }

    public double kelvinToFahrenheit(double kelvin)
    {
        return (kelvin * 9 / 5) - 459.67;
    }


}
