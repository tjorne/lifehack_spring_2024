package app.persistence;

import app.entities.MyEventsCategory;
import app.entities.MyEventsEvent;
import app.exceptions.DatabaseException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyEventsEventMapper {

    static {
        mapperSchema = "public";
    }

    private static String mapperSchema;

    public static void setMapperSchema(String schema) {
        mapperSchema = schema;
    }

    public static List<MyEventsEvent> getAllEvents(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT events.event_id, events.event_name, events.event_date, events.event_place, events.event_zip, postal_codes.city, events.event_resume, events.event_details, events.event_link FROM {schema}.events " +
                "INNER JOIN {schema}.postal_codes ON events.event_zip = postal_codes.zip";
        sql = sql.replace("{schema}", mapperSchema);

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            return getEventsFromResultSet(rs);

        } catch (SQLException e) {
            throw new DatabaseException("Database Error", e.getMessage());
        }
    }

    public static List<MyEventsEvent> getAllEventsByZip(int zip, ConnectionPool connectionPool) throws DatabaseException {
        return getAllEventsByZip(zip, Collections.emptyList(), connectionPool);
    }

    public static List<MyEventsEvent> getAllEventsByZip(int zip, List<MyEventsCategory> categories, ConnectionPool connectionPool) throws DatabaseException {
        StringBuilder sql = new StringBuilder(("SELECT DISTINCT events.event_id, events.event_name, events.event_date, events.event_place, events.event_zip, postal_codes.city, events.event_resume, events.event_details, events.event_link FROM {schema}.events " +
                "INNER JOIN {schema}.postal_codes ON events.event_zip = postal_codes.zip " +
                "INNER JOIN {schema}.events_categories ON events.event_id = events_categories.events_event_id " +
                "WHERE events.event_zip = ?").replace("{schema}", mapperSchema));

        if (!categories.isEmpty()) {
            sql.append(" AND (");

            for (int i = 0; i < categories.size(); i++) {
                if (i == 0) {
                    sql.append("categories_category_id = ?");
                } else {
                    sql.append(" OR categories_category_id = ?");
                }
            }
            sql.append(")");
        }

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql.toString())
        ) {
            ps.setInt(1, zip);

            for (int i = 0; i < categories.size(); i++) {
                ps.setInt(i + 2, categories.get(i).getId());
            }

            ResultSet rs = ps.executeQuery();
            return getEventsFromResultSet(rs);

        } catch (SQLException e) {
            throw new DatabaseException("Error in getting the events with zip = " + zip, e.getMessage());
        }
    }

    public static List<MyEventsEvent> getAllUserFavoriteEvents(int userId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT events.event_id, events.event_name, events.event_date, events.event_place, events.event_zip, postal_codes.city, events.event_resume, events.event_details, events.event_link FROM {schema}.event_favorites " +
                " JOIN {schema}.events ON events.event_id = event_favorites.event_id" +
                " JOIN {schema}.postal_codes ON events.event_zip = postal_codes.zip" +
                " WHERE event_favorites.user_id = ?";
        sql = sql.replace("{schema}", mapperSchema);

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            return getEventsFromResultSet(rs);

        } catch (SQLException e) {
            throw new DatabaseException(" SQL ERROR", e.getMessage());
        }
    }

    public static void addEventToUserFavorites(int userId, int eventId, ConnectionPool connectionPool) throws DatabaseException {

        String sql = "insert into {schema}.event_favorites (user_id, event_id) values (?, ?)";
        sql = sql.replace("{schema}", mapperSchema);

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, userId);
            ps.setInt(2, eventId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Error while adding event to favorite : " + userId + eventId);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error in DB connection", e.getMessage());
        }
    }

    public static void removeEventFromUserFavorites(int userId, int eventId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "DELETE FROM {schema}.event_favorites where user_id = ? AND event_id = ?";
        sql = sql.replace("{schema}", mapperSchema);

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, userId);
            ps.setInt(2, eventId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Error in updating user favorite list");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error in deleting event from favorite list", e.getMessage());
        }
    }

    public static MyEventsEvent getEventById(int eventId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT events.event_id, events.event_name, events.event_date, events.event_place, events.event_zip, postal_codes.city, events.event_resume, events.event_details, events.event_link FROM {schema}.events " +
                "INNER JOIN {schema}.postal_codes ON events.event_zip = postal_codes.zip " +
                "WHERE event_id = ?";
        sql = sql.replace("{schema}", mapperSchema);

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return getEventInfo(rs);
            } else {
                throw new DatabaseException("Error, no event matching the id...");
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error in getting the event id = " + eventId, e.getMessage());
        }
    }

    private static List<MyEventsEvent> getEventsFromResultSet(ResultSet rs) throws SQLException {
        List<MyEventsEvent> eventList = new ArrayList<>();

        while (rs.next()) {
            eventList.add(getEventInfo(rs));
        }
        return eventList;
    }

    private static MyEventsEvent getEventInfo(ResultSet rs) throws SQLException {

        int id = rs.getInt("event_id");
        String name = rs.getString("event_name");
        LocalDateTime date = rs.getTimestamp("event_date").toLocalDateTime();
        String place = rs.getString("event_place");
        int zip = rs.getInt("event_zip");
        String city = rs.getString("city");
        String resume = rs.getString("event_resume");
        String details = rs.getString("event_details");
        String link = rs.getString("event_link");
        return new MyEventsEvent(id, name, date, place, zip, city, resume, details, link);
    }
}
