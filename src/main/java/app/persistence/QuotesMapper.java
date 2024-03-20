package app.persistence;

import app.entities.Quote;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuotesMapper {

    protected static final List<Quote> quoteList = new ArrayList<>();


    public static void createQuote(String quoteGenre, String quoteBody, ConnectionPool connectionPool) throws DatabaseException {

        String sql = "insert into quotes (quote_genre, quote_body) values (?,?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, quoteGenre);
            ps.setString(2, quoteBody);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl ved oprettelse af ny quote");
            }
        } catch (SQLException e) {
            String msg = "Der er sket en fejl. Prøv igen";
            if (e.getMessage().startsWith("ERROR: duplicate key value ")) {
                msg = "Quotet findes allerede. Vælg et andet";
            }
            throw new DatabaseException(msg, e.getMessage());
        }
    }

    public static void createUserQuote(String quoteGenre, String quoteBody, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "insert into quotes (quote_genre, quote_body) values (?, ?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, quoteGenre);
            ps.setString(2, quoteBody);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl ved oprettelse af ny quote");
            }
        } catch (SQLException e) {
            String msg = "Der er sket en fejl. Prøv igen";
            if (e.getMessage().startsWith("ERROR: duplicate key value ")) {
                msg = "Quotet findes allerede. Vælg et andet";
            }
            throw new DatabaseException(msg, e.getMessage());
        }
    }

    public static Quote getQuoteById(int quoteId, ConnectionPool connectionPool) throws DatabaseException
    {
        Quote quote = null;

        String sql = "select * from quotes where quote_id = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setInt(1, quoteId);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                int id = rs.getInt("quote_id");
                String genre = rs.getString("quote_genre");
                String body = rs.getString("quote_body");
                quote = new Quote(id, genre, body);
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl ved hentning af quote med id = " + quoteId, e.getMessage());
        }
        return quote;
    }

    public static List<Quote> loadQuotes(ConnectionPool connectionPool ) throws DatabaseException
    {

        String sql = "select * from quotes";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            //ps.setInt(1, quote_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("quote_id");
                String genre = rs.getString("quote_genre");
                String body = rs.getString("quote_body");
                quoteList.add(new Quote(id, genre, body));
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return quoteList;
    }

    public static String getRandomQuote(List<Quote> quoteListGeneral){

        try{
            Random random = new Random();
            int randomIndex = random.nextInt(quoteListGeneral.size());
            return quoteListGeneral.get(randomIndex).getQuote_body();

        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    public static List<Quote> loadHappyQuotes(ConnectionPool connectionPool) throws DatabaseException
    {
        List<Quote> happyQuotes = new ArrayList<>();

        String sql = "select * from quotes where quote_genre = 'happynpc'";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            //ps.setInt(1, quote_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("quote_id");
                String genre = rs.getString("quote_genre");
                String body = rs.getString("quote_body");
                happyQuotes.add(new Quote(id, genre, body));
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return happyQuotes;
    }
}


