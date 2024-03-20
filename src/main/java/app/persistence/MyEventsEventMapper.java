package app.persistence;

import app.entities.MyEventsCategory;
import app.entities.MyEventsEvent;
import app.entities.Task;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyEventsEventMapper {

    public static List<MyEventsEvent> getAllEvents(ConnectionPool connectionPool) throws DatabaseException {
        List<MyEventsEvent> eventList = new ArrayList<>();
        String sql = "SELECT * FROM my_events.events " +
                     "INNER JOIN my_events.postal_codes ON events.event_zip = postal_codes.zip";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("event_id");
                String name = rs.getString("event_name");
                LocalDateTime date = rs.getTimestamp("event_date").toLocalDateTime();
                String place = rs.getString("event_place");
                int zip = rs.getInt("event_zip");
                String resume = rs.getString("event_resume");
                String details = rs.getString("event_details");
                String link = rs.getString("event_link");
                eventList.add(new MyEventsEvent(id, name, date, place, zip, resume, details, link));
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Database Error", e.getMessage());
        }
        return eventList;
    }

    public static List<MyEventsEvent> getAllEventsByZip(int zip, ConnectionPool connectionPool) throws DatabaseException {
        return getAllEventsByZip(zip, Collections.emptyList(), connectionPool);
    }

    public static List<MyEventsEvent> getAllEventsByZip(int zip, List<MyEventsCategory> categories, ConnectionPool connectionPool) throws DatabaseException {
        return null;
    }

    public static List<MyEventsEvent> getAllUserFavoriteEvents(int userId, ConnectionPool connectionPool) throws DatabaseException {
        return null;
    }

    public static void addEventToUserFavorites(int userId, int eventId, ConnectionPool connectionPool) throws DatabaseException {

    }

    public static void removeEventFromUserFavorites(int userId, int eventId, ConnectionPool connectionPool) throws DatabaseException {

    }

    public static MyEventsEvent getEventById(int eventId, ConnectionPool connectionPool) throws DatabaseException {
        return null;
    }
}
