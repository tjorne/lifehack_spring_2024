package app.entities;
import java.nio.file.Path;

public class Restaurant {
    private String name;
    private String adress;
    private String description;
    private String typeOfCuisine;

    private String websiteLink;


    public Restaurant(String name, String adress, String description, String typeOfCuisine, String websiteLink) {
        this.name = name;
        this.adress = adress;
        this.description = description;
        this.typeOfCuisine = typeOfCuisine;
        this.websiteLink = websiteLink;

    }


    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getDescription() {
        return description;
    }

    public String getTypeOfCuisine() {
        return typeOfCuisine;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTypeOfCuisine(String typeOfCuisine) {
        this.typeOfCuisine = typeOfCuisine;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", description='" + description + '\'' +
                ", typeOfCuisine='" + typeOfCuisine + '\'' +
                ", websiteLink=" + websiteLink +
                '}';
    }
}
