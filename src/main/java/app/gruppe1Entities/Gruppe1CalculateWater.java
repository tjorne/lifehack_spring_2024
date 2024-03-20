package app.gruppe1Entities;

public class Gruppe1CalculateWater
{
   private float waterNeeded = 2; //Den nødvendige mængde vand.
   private float waterConsumed; //Den mængde vand der er indtaget.
   private float remainingWater; //Differancen af vand.
    public Gruppe1CalculateWater (){
        this.waterConsumed = waterConsumed;
        this.remainingWater = remainingWater;
    }

    public void setWaterConsumed(float waterConsumed) {
        this.waterConsumed = waterConsumed;
    }
    public float calculateRemainingWater(){
        float remainingWater = waterConsumed - waterNeeded;

        return remainingWater;
    }
    public String toString(){
        float remainingWater = calculateRemainingWater();
        if(waterConsumed < waterNeeded){
            return "You need to drink = " + remainingWater + "L" + " to maintain a healthy water balance.";
        }else if (waterConsumed > 2.0){
            return "You are " + remainingWater + "L" + " over the recommended water amount." + "\nYou have enough water in your system. You should be good for now. Good day.";
        }else{
            return "Your spot on! Have a nice day!";
        }
    }
}