package app.entities;

public class Quote {
    private final int quote_id;
    private final String quote_genre;
    private final String quote_body;

    public Quote (int quote_id, String quote_genre, String quote_body){
        this.quote_id = quote_id;
        this.quote_genre = quote_genre;
        this.quote_body = quote_body;
    }
    public int getUserId() {
        return quote_id;
    }

    public String getQuote_genre() {
        return quote_genre;
    }

    public String getQuote_body() {
        return quote_body;
    }




    @Override
    public String toString() {
        return "Quote{" +
                "Quote_id=" + quote_id +
                ", Quote_Genre='" + quote_genre + '\'' +
                ", Quote_body=" + quote_body +
                '}';
    }
}
