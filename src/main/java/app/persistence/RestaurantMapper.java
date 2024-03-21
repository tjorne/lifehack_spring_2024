package app.persistence;

import app.entities.Restaurant;
import java.util.ArrayList;
import java.util.List;

public class RestaurantMapper {
    private static List<Restaurant> allRestaurants = new ArrayList<>();

    public static void createRestaurants() {
        Restaurant thai1 = new Restaurant(1,"Ayut Taya","Griffenfeldsgade 39, 2200 København N" ,
                "Mandag: Lukket\nAlle andre dage: 12:00 - 23:00\nKøkken lukker kl.22:00",
                "Ayuttaya åbnede som en af de første thai restauranter i Danmark d. 1. november 1994 med visionen om at bringe et strejf af Thailand til Danmark i form af god mad og friske råvarer.",
                "Thai", "https://ayuttaya.dk/");

        Restaurant thai2 = new Restaurant(2,"Green Mango", "Gentofte, Søborg, Christianshavn, Brønshøj, Lyngby, Nørrebro",
                "Alle dage: 12:30 – 21:30\nKøkkenet lukker kl. 21:00","Green Mango er skabt af Mathias, der sammen med sin familie i 12 år drev sin egen hotel – og restaurationsvirksomhed på Phi Phi Island i det sydlige Thailand." +
                "\nI 2010 flyttede Mathias og familien tilbage til Danmark og skabte den første Green Mango-restaurant ud fra kærligheden til det autentiske thailandske køkken.","Thai","https://greenmango.dk/");

        Restaurant indisk1 = new Restaurant(3,"Hind Indisk Restaurant","Landskronagade 74, 2100 København Ø","Mandag: 16:00 - 20:00\nAlle andre dage: 13:30 - 22:00",
                "Hvis du er på udkig efter en udsøgt kulinarisk smagsoplevelse når hverdagens mad er blevet for kedelig, skal du kigge ind hos Hind Indisk Restaurant. Her sætter vi lidt ekstra smag og farver på din hverdag, så du sent kommer til at glemme hvordan et måltid fra vores restaurant smager. ",
                "Indisk","https://hind-indisk.dk/");

        Restaurant indisk2 = new Restaurant(4,"Maharaja Indian Restaurant","Strandboulevarden 67, 2100 København Ø",
                "Alle dage: 12:00 - 22:30",
                "Restaurant Maharaja er en indisk restaurantkæde af høj kvalitet, hvor vi lægger vægt på den indiske madtradition. Vi tilbyder indiske specialiteter, hvor krydderierne tilpasses gæsternes individuelle behov og ønske. Hos os lægger vi vægt på den gode kombination af nordisk elegance og den klassiske Indiske.",
                "Indisk","https://restaurantmaharaja.dk/");

        Restaurant koreansk1 = new Restaurant(5,"Seoul Koreansk B.B.Q","Frederiksberg, Vanløse og Nordhavn",
                "Se hjemmeside",
                "20 års erfaring som en uovertruffen koreansk BBQ-destination i Danmark. Tag med os på en smagsrejse fyldt med autentiske og velsmagende koreanske grilloplevelser. " +
                        "Vores ærværdige traditioner og ekspertise gør os til det foretrukne valg for koreansk BBQ i hele Danmark. Kom og nyd vores perfekt grillet kød, sprøde grøntsager og enestående saucer, der vækker dine smagsløg til live. " +
                        "Vores passion for kvalitet og autenticitet har gjort os til et ikonisk sted for alle, der søger den ægte koreanske BBQ-oplevelse",
                "Koreansk","https://www.koreanskbbq.dk/");

        Restaurant koreansk2 = new Restaurant(6,"Ssam Korean Food Bar", "Vesterbro, Nørrebro og Fisketorvet",
                "Se hjemmeside",
                "SSAM er beliggende tre steder i københavn. Inspireret af den spændende livsstil i Korea og deres mangfoldige madkultur, ønsker SSAM at tilbyde de lokale københavnere muligheden for at nyde moderne koreansk mad på samme måde som det gøres 8000 km væk.",
                "Koreansk","https://ssam.dk/");

        allRestaurants.add(thai1);
        allRestaurants.add(thai2);
        allRestaurants.add(indisk1);
        allRestaurants.add(indisk2);
        allRestaurants.add(koreansk1);
        allRestaurants.add(koreansk2);
    }

    public static List<Restaurant> getRestaurantsWithCertainCuisine(String cuisine) {
        List<Restaurant> restaurantsWithCertainCuisine = new ArrayList<>();
        for (Restaurant r: allRestaurants) {
            if (r.getTypeOfCuisine().equals(cuisine)) {
                restaurantsWithCertainCuisine.add(r);
            }
        }
        return restaurantsWithCertainCuisine;
    }

    public static Restaurant getRestaurant(int restaurantId) {
        return allRestaurants.get(restaurantId);
    }
}
