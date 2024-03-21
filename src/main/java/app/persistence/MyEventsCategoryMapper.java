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

    static {
        mapperSchema = "my_events";
    }

    private static String mapperSchema;

    public static void setMapperSchema(String schema) {
        mapperSchema = schema;
    }

    public static List<MyEventsCategory> getAllCategories(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select category_id, category_name from {schema}.categories";
        sql = sql.replace("{schema}", mapperSchema);

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            return getCategoriesFromResultSet(rs);
        } catch (SQLException e) {
            throw new DatabaseException("DB fejl", e.getMessage());
        }
    }

    public static List<MyEventsCategory> getAllEventCategories(int eventId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT category_id, category_name FROM {schema}.events " +
                "INNER JOIN {schema}.events_categories ON events.event_id = events_categories.events_event_id " +
                "INNER JOIN {schema}.categories ON categories.category_id = events_categories.categories_category_id " +
                "WHERE events.event_id = ?";
        sql = sql.replace("{schema}", mapperSchema);

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, eventId);

            ResultSet rs = ps.executeQuery();
            return getCategoriesFromResultSet(rs);
        } catch (SQLException e) {
            throw new DatabaseException("DB fejl", e.getMessage());
        }
    }

    public static MyEventsCategory getCategoryById(int categoryId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT category_id, category_name FROM {schema}.categories " +
                "WHERE categories.category_id = ?";
        sql = sql.replace("{schema}", mapperSchema);

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, categoryId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getCategoryFromResultSet(rs);
            } else {
                throw new DatabaseException("Could not find category with id = " + categoryId);
            }
        } catch (SQLException e) {
            throw new DatabaseException("DB fejl", e.getMessage());
        }
    }

    private static List<MyEventsCategory> getCategoriesFromResultSet(ResultSet rs) throws SQLException {
        List<MyEventsCategory> categories = new ArrayList<>();

        while (rs.next()) {
            categories.add(getCategoryFromResultSet(rs));
        }

        return categories;
    }

    private static MyEventsCategory getCategoryFromResultSet(ResultSet rs) throws SQLException {
        int categoryId = rs.getInt("category_id");
        String categoryName = rs.getString("category_name");
        return new MyEventsCategory(categoryId, categoryName);
    }
}
