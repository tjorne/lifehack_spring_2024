package app.entities;
import java.net.URL;
import java.nio.file.Path;

public class Restaurant {
    private String name;
    private String adress;
    private String description;
    private String typeOfCuisine;

    // indsæt URL til restaurantens hjemmeside.
    private URL websiteLink;

    // stigen til billeder fra restauranten
    private Path imagePath;

    public Restaurant(String name, String adress, String description, String typeOfCuisine, URL websiteLink, Path imagePath) {
        this.name = name;
        this.adress = adress;
        this.description = description;
        this.typeOfCuisine = typeOfCuisine;
        this.websiteLink = websiteLink;
        this.imagePath = imagePath;
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

    public URL getWebsiteLink() {
        return websiteLink;
    }

    public Path getImagePath() {
        return imagePath;
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

    public void setWebsiteLink(URL websiteLink) {
        this.websiteLink = websiteLink;
    }

    public void setImagePath(Path imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", description='" + description + '\'' +
                ", typeOfCuisine='" + typeOfCuisine + '\'' +
                ", websiteLink=" + websiteLink +
                ", imagePath=" + imagePath +
                '}';
    }
}
