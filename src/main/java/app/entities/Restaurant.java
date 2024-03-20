package app.entities;

public class Restaurant {
    private String name;
    private String address;
    private String openingHours;
    private String description;
    private String typeOfCuisine;
    private String websiteLink;

    public Restaurant(String name, String address, String openingHours, String description, String typeOfCuisine, String websiteLink) {
        this.name = name;
        this.address = address;
        this.openingHours = openingHours;
        this.description = description;
        this.typeOfCuisine = typeOfCuisine;
        this.websiteLink = websiteLink;

    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getOpeningHours() {
        return openingHours;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
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

    public String getPicName() {
        return name.replace(" ", "");
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", openingHours='" + openingHours + '\'' +
                ", description='" + description + '\'' +
                ", typeOfCuisine='" + typeOfCuisine + '\'' +
                ", websiteLink='" + websiteLink + '\'' +
                '}';
    }
}
