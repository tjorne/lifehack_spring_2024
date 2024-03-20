package app.persistence;

import app.entities.MyEventsCategory;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyEventsCategoryMapper {

    public static List<MyEventsCategory> getAllCategories(ConnectionPool connectionPool) throws DatabaseException {
        List<MyEventsCategory> categories = new ArrayList<>();

        String sql = "select * from my_events.categories";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int categoryId = rs.getInt("category_id");
                String categoryName = rs.getString("category_name");
                categories.add(new MyEventsCategory(categoryId, categoryName));
            }
        } catch (SQLException e) {
            throw new DatabaseException("DB fejl", e.getMessage());
        }
        return categories;
    }

    public static List<MyEventsCategory> getAllEventCategories(int eventId, ConnectionPool connectionPool) throws DatabaseException {
        List<MyEventsCategory> categories = new ArrayList<>();

        String sql = "SELECT my_events.categories.category_id, my_events.categories.category_name FROM my_events.events " +
                "INNER JOIN my_events.events_categories ON my_events.events.event_id = my_events.events_categories.events_event_id " +
                "INNER JOIN my_events.categories ON my_events.categories.category_id = my_events.events_categories.categories_category_id " +
                "WHERE my_events.events.event_id = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, eventId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int categoryId = rs.getInt("category_id");
                String categoryName = rs.getString("category_name");
                categories.add(new MyEventsCategory(categoryId, categoryName));
            }
        } catch (SQLException e) {
            throw new DatabaseException("DB fejl", e.getMessage());
        }
        return categories;
    }

    public static MyEventsCategory getCategoryById(int categoryId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM my_events.categories WHERE my_events.categories.category_id = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, categoryId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String categoryName = rs.getString("category_name");
                return new MyEventsCategory(categoryId, categoryName);
            } else {
                throw new DatabaseException("Could not find category with id = " + categoryId);
            }
        } catch (SQLException e) {
            throw new DatabaseException("DB fejl", e.getMessage());
        }
    }
}
