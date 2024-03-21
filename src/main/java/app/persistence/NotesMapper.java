package app.persistence;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.group9.Notes;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotesMapper {
    public static Notes createNotes(User user, String title, String content, ConnectionPool connectionPool) throws DatabaseException {

        Notes newNotes = null;
        String sql = "insert into public.\"group9\"\n  (title, content, user_id, date) values (?,?,?,?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setInt(3,user.getUserId());
            ps.setDate(4, Date.valueOf(LocalDate.now().toString()));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl ved oprettelse af ny note");
            }
        } catch (SQLException e) {
            String msg = "Der er sket en fejl. Prøv igen";
            if (e.getMessage().startsWith("ERROR: duplicate key value ")) {
                msg = "Note title findes allerede. Vælg et andet";
            }
            throw new DatabaseException(msg, e.getMessage());
        }
        return newNotes;
    }

    public static List<Notes> getAllNotesPerUser(int userId, ConnectionPool connectionPool) throws DatabaseException
    {
        List<Notes> notesList = new ArrayList<>();
        String sql = "SELECT * FROM public.\"group9\" WHERE \"user_id\" = ? ORDER BY \"date\" desc ";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("notes_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                java.sql.Date sqlDate = rs.getDate("date");
                LocalDate javaDate = Date.valueOf(sqlDate.toLocalDate()).toLocalDate();
                notesList.add(new Notes(title, content, userId, id, javaDate));
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return notesList;

    }
}

