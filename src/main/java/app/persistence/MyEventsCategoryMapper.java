package app.persistence;

import app.entities.MyEventsCategory;
import app.exceptions.DatabaseException;

import java.util.List;

public class MyEventsCategoryMapper {

    public static List<MyEventsCategory> getAllCategories(ConnectionPool connectionPool) throws DatabaseException {
        return null;
    }

    public static List<MyEventsCategory> getAllEventCategories(int eventId, ConnectionPool connectionPool) throws DatabaseException {
        return null;
    }

    public static MyEventsCategory getCategoryById(int categoryId, ConnectionPool connectionPool) throws DatabaseException {
        return null;
    }
}
