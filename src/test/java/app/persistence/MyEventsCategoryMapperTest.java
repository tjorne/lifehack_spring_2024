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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;

public class MyEventsCategoryMapperTest {
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=my_events_tests";
    private static final String DB = "lifehack";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);

    @BeforeAll
    public static void setUpClass() {
        MyEventsCategoryMapper.setMapperSchema("my_events_tests");

        try (Connection connection = connectionPool.getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("CREATE schema IF NOT EXISTS my_events_tests");

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

    private List<MyEventsEvent> expectedEventList;
    private List<MyEventsCategory> expectedCategoryList;
    private Map<Integer, String> expectedCityMap;

    @BeforeEach
    void setUp() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        expectedEventList = new ArrayList<>();
        expectedCategoryList = new ArrayList<>();
        expectedCityMap = new LinkedHashMap<>();

        expectedEventList.add(new MyEventsEvent(
                1,
                "Nordic Winter Festival",
                LocalDateTime.parse("2024-12-15 16:00:00", dateTimeFormatter),
                "Åboulevarden", 8000,
                "Aarhus",
                "Experience the magic of the holiday season with festive markets, traditional performances, and seasonal treats.",
                "extra details",
                "link will be here"
        ));

        expectedEventList.add(new MyEventsEvent(
                2,
                "Street Art Festival",
                LocalDateTime.parse("2024-08-03 10:00:00", dateTimeFormatter),
                "Copenhagen",
                1050,
                "Copenhagen",
                "Witness the transformation of urban spaces into vibrant works of art by local and international street artists.",
                "The Street Art Festival brings color and creativity to the streets of Copenhagen, showcasing the talents of graffiti artists, muralists, and stencilists from around the world. Over three days, visitors can explore different neighborhoods to discover large-scale murals, interactive installations, and live painting performances. Guided tours, workshops, and artist talks offer insight into the techniques and inspirations behind the artworks, making it a dynamic and engaging cultural experience for all.",
                "link will be here"
        ));

        expectedEventList.add(new MyEventsEvent(
                3,
                "Charity Run for Children's Education",
                LocalDateTime.parse("2024-06-08 09:00:00", dateTimeFormatter),
                "Odense",
                5000,
                "Odense",
                "Lace up your running shoes and join us for a fun run to support children's education initiatives in Denmark.",
                "The Charity Run for Children's Education is a community event aimed at raising funds and awareness for educational programs serving children in need. Participants of all ages and fitness levels are welcome to walk, jog, or run the scenic route through Odense, with distances suitable for families and serious runners alike. In addition to the main race, the event features family-friendly activities, entertainment, and opportunities to learn about the impact of education on children's lives.",
                "link will be here"
        ));

        expectedCategoryList.add(new MyEventsCategory(
                1,
                "Art & Culture"
        ));

        expectedCategoryList.add(new MyEventsCategory(
                2,
                "Fundraising"
        ));

        expectedCategoryList.add(new MyEventsCategory(
                3,
                "Sport"
        ));

        expectedCityMap.put(8000, "Aarhus");
        expectedCityMap.put(1050, "Copenhagen");
        expectedCityMap.put(5000, "Odense");

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

                StringBuilder citySql = new StringBuilder("INSERT INTO my_events_tests.postal_codes (zip, city) VALUES ");

                int cityCounter = 0;
                for (Map.Entry<Integer, String> entry : expectedCityMap.entrySet()) {
                    if (cityCounter != 0) {
                        citySql.append(",");
                    }
                    citySql.append(
                            String.format("(%d, '%s')",
                                    entry.getKey(),
                                    entry.getValue())
                    );
                    cityCounter++;
                }
                stmt.execute(citySql.toString());

                StringBuilder eventSql = new StringBuilder("INSERT INTO my_events_tests.events (event_id, event_name, event_date, event_place, event_zip, event_resume, event_details, event_link) VALUES ");
                for (int i = 0; i < expectedEventList.size(); i++) {
                    MyEventsEvent event = expectedEventList.get(i);

                    if (i != 0) {
                        eventSql.append(",");
                    }
                    eventSql.append(
                            String.format("(%d, '%s', '%s', '%s', %d, '%s', '%s', '%s')",
                                    event.getId(),
                                    event.getName().replace("'", "''"),
                                    event.getDate().format(dateTimeFormatter),
                                    event.getPlace().replace("'", "''"),
                                    event.getZip(),
                                    event.getResume().replace("'", "''"),
                                    event.getDetails().replace("'", "''"),
                                    event.getLink())
                    );
                }
                stmt.execute(eventSql.toString());

                // Set sequence to continue from the largest member_id
                stmt.execute("SELECT setval('my_events_tests.my_events_event_id_seq', COALESCE((SELECT MAX(event_id)+1 FROM my_events_tests.events), 1), false)");

                StringBuilder categorySql = new StringBuilder("INSERT INTO my_events_tests.categories (category_id, category_name) VALUES ");
                for (int i = 0; i < expectedCategoryList.size(); i++) {
                    MyEventsCategory category = expectedCategoryList.get(i);

                    if (i != 0) {
                        categorySql.append(",");
                    }
                    categorySql.append(String.format("(%d, '%s')", category.getId(), category.getName()));
                }
                stmt.execute(categorySql.toString());

                stmt.execute("SELECT setval('my_events_tests.my_events_categories_category_id_seq', COALESCE((SELECT MAX(category_id)+1 FROM my_events_tests.categories), 1), false)");

                stmt.execute("INSERT INTO my_events_tests.events_categories (events_event_id, categories_category_id) VALUES " +
                        "(1, 1), (2, 1), (3, 2), (3, 3)");
            } catch (SQLException e) {
                fail("SQL Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            fail("Database connection failed");
        }
    }

    @Test
    void getCategoryById() throws DatabaseException {
        MyEventsCategory actualCategory = MyEventsCategoryMapper.getCategoryById(2, connectionPool);

        Assertions.assertEquals(expectedCategoryList.get(1), actualCategory);
    }

    @Test
    void getAllCategories() throws DatabaseException {
        List<MyEventsCategory> actualCategoryList = MyEventsCategoryMapper.getAllCategories(connectionPool);

        Assertions.assertEquals(expectedCategoryList.size(), actualCategoryList.size());

        for (int i = 0; i < expectedCategoryList.size(); i++) {
            Assertions.assertEquals(expectedCategoryList.get(i), actualCategoryList.get(i));
        }
    }

    @Test
    void getAllEventCategories() throws DatabaseException {
        List<MyEventsCategory> actualCategoryList = MyEventsCategoryMapper.getAllEventCategories(3, connectionPool);

        Assertions.assertEquals(2, actualCategoryList.size());
        Assertions.assertEquals(expectedCategoryList.get(1), actualCategoryList.get(0));
        Assertions.assertEquals(expectedCategoryList.get(2), actualCategoryList.get(1));
    }
}
