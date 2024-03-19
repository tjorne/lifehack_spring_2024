package app.persistence;

import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
