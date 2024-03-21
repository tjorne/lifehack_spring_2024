package app.persistence;

import app.entities.MyEventsCategory;
import app.entities.MyEventsEvent;
import app.exceptions.DatabaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class MyEventsEventMapperTest {
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=my_events_tests";
    private static final String DB = "lifehack";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);

    @BeforeAll
    public static void setUpClass() {
        try (Connection connection = connectionPool.getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                // The test schema is already created, so we only need to delete/create test tables
                stmt.execute("DROP TABLE IF EXISTS my_events_tests.events_categories");
                stmt.execute("DROP TABLE IF EXISTS my_events_tests.event_favorites");
                stmt.execute("DROP TABLE IF EXISTS my_events_tests.categories");
                stmt.execute("DROP TABLE IF EXISTS my_events_tests.events");
                stmt.execute("DROP TABLE IF EXISTS my_events_tests.postal_codes");

                stmt.execute("DROP SEQUENCE IF EXISTS my_events_tests.my_events_event_id_seq CASCADE;");
                stmt.execute("DROP SEQUENCE IF EXISTS my_events_tests.my_events_categories_category_id_seq CASCADE;");

                // Create tables as copy of original public schema structure
                stmt.execute("CREATE TABLE my_events_tests.postal_codes AS (SELECT * from my_events.postal_codes) WITH NO DATA");
                stmt.execute("CREATE TABLE my_events_tests.categories AS (SELECT * from my_events.categories) WITH NO DATA");
                stmt.execute("CREATE TABLE my_events_tests.events AS (SELECT * from my_events.events) WITH NO DATA");
                stmt.execute("CREATE TABLE my_events_tests.event_favorites AS (SELECT * from my_events.event_favorites) WITH NO DATA");
                stmt.execute("CREATE TABLE my_events_tests.events_categories AS (SELECT * from my_events.events_categories) WITH NO DATA");

                // Create sequences for auto generating id's for members and sports
                stmt.execute("CREATE SEQUENCE my_events_tests.my_events_event_id_seq");
                stmt.execute("ALTER TABLE my_events_tests.events ALTER COLUMN event_id SET DEFAULT nextval('my_events_tests.my_events_event_id_seq')");
                stmt.execute("CREATE SEQUENCE  my_events_tests.my_events_categories_category_id_seq");
                stmt.execute("ALTER TABLE my_events_tests.categories ALTER COLUMN category_id SET DEFAULT nextval('my_events_tests.my_events_categories_category_id_seq')");

            } catch (SQLException e) {
                fail("SQL Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            fail("Database connection failed");
        }
    }

    private List<MyEventsEvent> eventList;
    private List<MyEventsCategory> categoryList;

    @BeforeEach
    void setUp() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        eventList = new ArrayList<>();
        categoryList = new ArrayList<>();

        eventList.add(new MyEventsEvent(
                1,
                "Nordic Winter Festival",
                LocalDateTime.parse("2024-12-15 16:00:00", dateTimeFormatter),
                "Åboulevarden", 8000,
                "Experience the magic of the holiday season with festive markets, traditional performances, and seasonal treats.",
                "extra details",
                "link will be here"
        ));

        eventList.add(new MyEventsEvent(
                2,
                "Street Art Festival",
                LocalDateTime.parse("2024-08-03 10:00:00", dateTimeFormatter),
                "Copenhagen",
                1050,
                "Witness the transformation of urban spaces into vibrant works of art by local and international street artists.",
                "The Street Art Festival brings color and creativity to the streets of Copenhagen, showcasing the talents of graffiti artists, muralists, and stencilists from around the world. Over three days, visitors can explore different neighborhoods to discover large-scale murals, interactive installations, and live painting performances. Guided tours, workshops, and artist talks offer insight into the techniques and inspirations behind the artworks, making it a dynamic and engaging cultural experience for all.",
                "link will be here"
        ));

        eventList.add(new MyEventsEvent(
                3,
                "Charity Run for Children's Education",
                LocalDateTime.parse("2024-06-08 09:00:00", dateTimeFormatter),
                "Odense",
                5000,
                "Lace up your running shoes and join us for a fun run to support children's education initiatives in Denmark.",
                "The Charity Run for Children's Education is a community event aimed at raising funds and awareness for educational programs serving children in need. Participants of all ages and fitness levels are welcome to walk, jog, or run the scenic route through Odense, with distances suitable for families and serious runners alike. In addition to the main race, the event features family-friendly activities, entertainment, and opportunities to learn about the impact of education on children's lives.",
                "link will be here"
        ));

        categoryList.add(new MyEventsCategory(
                4,
                "Art & Culture"
        ));

        categoryList.add(new MyEventsCategory(
                3,
                "Fundraising"
        ));

        categoryList.add(new MyEventsCategory(
                5,
                "Sport"
        ));

        try (Connection connection = connectionPool.getConnection()) {
            try (Statement stmt = connection.createStatement()) {

                // Remove all rows from all tables
                stmt.execute("DELETE FROM my_events_tests.event_favorites");
                stmt.execute("DELETE FROM my_events_tests.events_categories");
                stmt.execute("DELETE FROM my_events_tests.categories");
                stmt.execute("DELETE FROM my_events_tests.events");
                stmt.execute("DELETE FROM my_events_tests.postal_codes");

                // Reset the sequence number
                stmt.execute("SELECT setval('my_events_tests.my_events_event_id_seq', 1)");
                stmt.execute("SELECT setval('my_events_tests.my_events_categories_category_id_seq',1)");

                // Insert rows
                stmt.execute("INSERT INTO my_events_tests.postal_codes VALUES " +
                        "(8000, 'Aarhus'), (1050, 'Copenhagen'), (5000, 'Odense')");

                StringBuilder sql = new StringBuilder("INSERT INTO my_events_tests.events (event_id, event_name, event_date, event_place, event_zip, event_resume, event_details, event_link) VALUES ");
                for (int i = 0; i < eventList.size(); i++) {
                    MyEventsEvent event = eventList.get(i);

                    if (i != 0) {
                        sql.append(",");
                    }
                    sql.append(
                            String.format("(%d, '%s', '%s', '%s', %d, '%s', '%s', '%s')",
                            event.getId(),
                            event.getName().replace("'", "''"),
                            event.getDate().format(dateTimeFormatter),
                            event.getPlace().replace("'", "''"),
                            event.getZip(),
                            event.getResume().replace("'", "''"),
                            event.getDetails().replace("'", "''"),
                            event.getLink()));
                }
                stmt.execute(sql.toString());

                // Set sequence to continue from the largest member_id
                stmt.execute("SELECT setval('my_events_tests.my_events_event_id_seq', COALESCE((SELECT MAX(event_id)+1 FROM my_events_tests.events), 1), false)");

                StringBuilder categorySql = new StringBuilder("INSERT INTO my_events_tests.categories (category_id, category_name) VALUES ");
                for (int i = 0; i < categoryList.size(); i++) {
                    MyEventsCategory category = categoryList.get(i);

                    if (i != 0) {
                        categorySql.append(",");
                    }
                    categorySql.append(String.format("(%d, '%s')", category.getId(), category.getName()));
                }
                stmt.execute(categorySql.toString());

                stmt.execute("SELECT setval('my_events_tests.my_events_categories_category_id_seq', COALESCE((SELECT MAX(category_id)+1 FROM my_events_tests.categories), 1), false)");
            }  catch (SQLException e) {
                fail("SQL Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            fail("Database connection failed");
        }
    }

    @Test
    void getAllEventsTest() throws DatabaseException {
        List<MyEventsEvent> allEvents = MyEventsEventMapper.getAllEvents(connectionPool);
        Assertions.assertEquals(eventList.size(), allEvents.size());
    }

}
