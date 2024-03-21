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

    public static List<MyEventsEvent> getAllEvents(ConnectionPool connectionPool) throws DatabaseException {

        String sql = "SELECT * FROM my_events.events " +
                "INNER JOIN my_events.postal_codes ON my_events.events.event_zip = my_events.postal_codes.zip";

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

        StringBuilder sql = new StringBuilder("SELECT DISTINCT event_id, event_name, event_date, event_place, event_zip, event_resume, event_details, event_link FROM my_events.events " +
                "INNER JOIN my_events.events_categories ON my_events.events.event_id = my_events.events_categories.events_event_id " +
                "WHERE my_events.events.event_zip = ?");

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

        String sql = "SELECT * FROM my_events.event_favorites " +
                " JOIN my_events.events ON my_events.events.event_id = my_events.event_favorites.event_id" +
                " WHERE my_events.event_favorites.user_id = ?";

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

        String sql = "insert into my_events.event_favorites (user_id, event_id) values (?,?)";

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
        String sql = "DELETE FROM my_events.event_favorites where user_id = ? AND event_id = ?";

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
        MyEventsEvent event = null;

        String sql = "select * from my_events.events where event_id = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                event = getEventInfo(rs);
            } else {
                throw new DatabaseException("Error, no event matching the id...");
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error in getting the event id = " + eventId, e.getMessage());
        }
        return event;
    }


    public static List<MyEventsEvent> getEventsFromResultSet(ResultSet rs) throws SQLException {
        List<MyEventsEvent> eventList = new ArrayList<>();

        while (rs.next()) {
            eventList.add(getEventInfo(rs));
        }
        return eventList;
    }

    public static MyEventsEvent getEventInfo(ResultSet rs) throws SQLException {

        int id = rs.getInt("event_id");
        String name = rs.getString("event_name");
        LocalDateTime date = rs.getTimestamp("event_date").toLocalDateTime();
        String place = rs.getString("event_place");
        int zip = rs.getInt("event_zip");
        String resume = rs.getString("event_resume");
        String details = rs.getString("event_details");
        String link = rs.getString("event_link");
        return new MyEventsEvent(id, name, date, place, zip, resume, details, link);
    }
}


