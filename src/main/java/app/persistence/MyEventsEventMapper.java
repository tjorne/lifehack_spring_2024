package app.persistence;

import app.entities.MyEventsCategory;
import app.entities.MyEventsEvent;
import app.exceptions.DatabaseException;

import java.util.Collections;
import java.util.List;

public class MyEventsEventMapper {

    public static List<MyEventsEvent> getAllEvents(ConnectionPool connectionPool) throws DatabaseException {
        return null;
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
