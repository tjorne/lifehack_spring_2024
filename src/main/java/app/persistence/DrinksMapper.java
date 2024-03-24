package app.persistence;

import app.entities.Drink;
import app.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrinksMapper {
    public static List<Drink> searchDrinks(String input, ConnectionPool connectionPool) throws DatabaseException {
        List<Drink> matchingDrinks = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.getConnection();
            String query = "SELECT drinkname, drinkcategory, ingredients, instructions, drinkid FROM drinks WHERE ingredients LIKE ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + input + "%");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String drinkName = resultSet.getString("drinkname");
                String drinkCategory = resultSet.getString("drinkcategory");
                String ingredients = resultSet.getString("ingredients");
                String instructions = resultSet.getString("instructions");
                int drinkId = resultSet.getInt("drinkid");

                Drink drink = new Drink(drinkName, drinkCategory, ingredients, instructions, drinkId);
                matchingDrinks.add(drink);
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

}
