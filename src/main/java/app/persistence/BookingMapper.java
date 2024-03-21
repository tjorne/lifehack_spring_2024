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

    public static ArrayList<Timeslot> checkTime(String dato, String behandling, ConnectionPool connectionPool) throws DatabaseException {
        {
            ArrayList<Timeslot> taskList = new ArrayList<>();

            String[] timeslots = new String[]{"8.30-9.30", "9.30-10.30", "10.30-11.30", "11.30-12.30", "12.30-13.30", "13.30-14.30", "14.30-15.30"};


            String sql = "select * from booking where date=? AND time = ? AND booking_type = ?";

            try (
                    Connection connection = connectionPool.getConnection();
                    PreparedStatement ps = connection.prepareStatement(sql)
            )
            {
                for(String timeslot : timeslots) {
                    ps.setString(1, dato);
                    ps.setString(2, timeslot);
                    ps.setString(3, behandling);
                    ResultSet rs = ps.executeQuery();
                    if(!rs.next()){
                        taskList.add(new Timeslot(timeslot,dato,false, behandling));
                    }
                }

            }
            catch (SQLException e)
            {
                throw new DatabaseException("Fejl!!!!", e.getMessage());
            }
            return taskList;
        }
    }

}
