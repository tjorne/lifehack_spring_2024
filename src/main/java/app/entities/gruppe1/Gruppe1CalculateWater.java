package app.entities.gruppe1;

public class Gruppe1CalculateWater {
    private float waterNeeded = 2; //Den nødvendige mængde vand.
    private float waterConsumed; //Den mængde vand der er indtaget.

    public void setWaterConsumed(float waterConsumed) {
        this.waterConsumed = waterConsumed;
    }

    public float calculateRemainingWater() {
        float remainingWater = waterConsumed - waterNeeded;

        return remainingWater;
    }

    public String toString() {
        float remainingWater = calculateRemainingWater();
        if (waterConsumed < waterNeeded) {
            return "You need to drink = " + remainingWater + "L" + " to maintain a healthy water balance.";
        } else if (waterConsumed > 10) {
            return "This is not healthy, you should be taking it easy with the water drinking. You are " + remainingWater + "over the recommended water intake.";
        } else if (waterConsumed > 5) {
            return "You must be working out, or being really active. You are " + remainingWater + "over the recommended.";
        } else if (waterConsumed == 2.0) {
            return "Your spot on! Have a nice day!";
        } else if (waterConsumed > 2.0) {
            return "You are " + remainingWater + "L" + " over the recommended water amount." + "\nYou have enough water in your system. You should be good for now. Good day.";
        } else {
            return "Water is good.";
        }
    }
}