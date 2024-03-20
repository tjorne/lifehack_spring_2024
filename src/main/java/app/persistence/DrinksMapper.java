package app.persistence;

import app.entities.Drink;
import app.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//CHECK PATHS
public class DrinksMapper {
    public static List<String> searchDrinks(String input, ConnectionPool connectionPool) throws DatabaseException {
        List<String> matchingDrinks = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.getConnection();
            String query = "SELECT drinkname FROM drinks WHERE ingredients LIKE ?"; //change name accoring to db
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + input + "%");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                matchingDrinks.add(resultSet.getString("drinkname"));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Database error occurred", e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new DatabaseException("Failed to close resources", e.getMessage());
            }
        }
        return matchingDrinks;
    }

  /* public static Drink addDrink(String drinkname, String drinkcategory, String ingredients, String instructions) throws DatabaseException {
        Drink newDrink = null;
        //Connection connection;
        //PreparedStatement ps;
        String sql = "insert into drink (drinkname, typeofdrink, ingredients, instructions) values (?, ?, ?, ?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
                ){
            //connection = connectionPool.getConnection();

            //ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, drinkname);
            ps.setString(2, drinkcategory);
            ps.setString(3, ingredients);
            ps.setString(4, instructions);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                newDrink = new Drink(drinkname, drinkcategory, ingredients, instructions);
            } else {
                throw new DatabaseException("Fejl under indsætning af drink: " + drinkname);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl i DB connection", e.getMessage());
        }
        return newDrink;

    }*/
}
