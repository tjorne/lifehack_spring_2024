package app.persistence;

import app.entities.Timeslot;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
            } else
            {

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
        updateTimeslotBoolean(date,time,connectionPool);
    }

    public static ArrayList<Timeslot> checkTime(String dato, String behandling, ConnectionPool connectionPool) throws DatabaseException {
        {
            ArrayList<Timeslot> taskList = new ArrayList<>();
            String sql = "select * from timeslot where date=? AND booked = FALSE";

            try (
                    Connection connection = connectionPool.getConnection();
                    PreparedStatement ps = connection.prepareStatement(sql)
            )
            {
                System.out.println(dato);
                ps.setString(1, dato);
                //ps.setString(2, behandling);
                ResultSet rs = ps.executeQuery();

                while (rs.next())
                {
                    String time = rs.getString("timeslot");
                    String date = rs.getString("date");
                    Boolean booked = rs.getBoolean("booked");
                    taskList.add(new Timeslot(time, date, booked));
                }
            }
            catch (SQLException e)
            {
                throw new DatabaseException("Fejl!!!!", e.getMessage());
            }
            return taskList;
        }
        //return new ArrayList<Timeslot>();
    }
    public static void updateTimeslotBoolean(String date, String time, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "UPDATE timeslot SET booked = TRUE WHERE date = ? AND timeslot = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, date);
            ps.setString(2, time);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl ved opdatering af timeslot boolean");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Database fejl: " + e.getMessage());
        }
    }
}
