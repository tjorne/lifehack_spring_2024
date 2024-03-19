package app.persistence;

import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingMapper {



    public static void createBooking(String behandling, String date, String time, String behandler, String navn, String tlfnummer, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "insert into booking (booking_type, date, time, handler, customer_name, customer_mobile) values (?,?,?,?,?,?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setString(1, behandling);
            ps.setString(2, date);
            ps.setString(3, time);
            ps.setString(4, behandler);
            ps.setString(5, navn);
            ps.setString(6, tlfnummer);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1)
            {
                throw new DatabaseException("Fejl ved oprettelse af ny booking");
            }
        }
        catch (SQLException e)
        {
            String msg = "Der er sket en fejl. Prøv igen";
            if (e.getMessage().startsWith("ERROR: duplicate key value "))
            {
                msg = "Der er sket en fejl! Prøv igen!";
            }
            throw new DatabaseException(msg, e.getMessage());
        }
    }
}
