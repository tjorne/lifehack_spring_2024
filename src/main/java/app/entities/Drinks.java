package app.entities;

public class Drinks {

    private String drinkname;
    private String typeofdrink;
    private String ingredients;
    private String instructions;

    public Drinks(String drinkname, String typeofdrink, String ingredients, String instructions) {
        this.drinkname = drinkname;
        this.typeofdrink = typeofdrink;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public String getDrinkname() {
        return drinkname;
    }

    public void setDrinkname(String drinkname) {
        this.drinkname = drinkname;
    }

    public String getTypeofdrink() {
        return typeofdrink;
    }

    public void setTypeofdrink(String typeofdrink) {
        this.typeofdrink = typeofdrink;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "Drinks{" +
                "drinkname='" + drinkname + '\'' +
                ", typeofdrink='" + typeofdrink + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
