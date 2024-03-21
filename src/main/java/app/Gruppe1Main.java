package app;

import app.gruppe1Controller.Gruppe1Controller;
import app.gruppe1Entities.Gruppe1CalculateWater;

import java.util.Scanner;


public class Gruppe1Main
{
    public static void main(String[] args)
    {
        Gruppe1Vand-CalculateWater
        Gruppe1CalculateWater calculateWater = new Gruppe1CalculateWater();
        Scanner UserInput = new Scanner(System.in);

        System.out.println("Enter the amount of water you have been drinking.");

        float waterConsumed = UserInput.nextFloat();

        calculateWater.setWaterConsumed(waterConsumed);
        calculateWater.calculateRemainingWater();
        System.out.println(calculateWater.toString());
        Gruppe1Vand
    }
}
