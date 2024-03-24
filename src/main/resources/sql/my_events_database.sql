BEGIN;

CREATE schema IF NOT EXISTS my_events;

CREATE TABLE IF NOT EXISTS my_events.postal_codes
(
    zip integer NOT NULL,
    city character varying NOT NULL,
    PRIMARY KEY (zip)
    );

CREATE TABLE IF NOT EXISTS my_events.categories
(
    category_id serial NOT NULL,
    category_name character varying NOT NULL,
    PRIMARY KEY (category_id)
    );

CREATE TABLE IF NOT EXISTS my_events.events
(
    event_id serial NOT NULL,
    event_name character varying NOT NULL,
    event_date timestamp without time zone NOT NULL,
    event_place character varying NOT NULL,
    event_zip integer NOT NULL,
    event_resume character varying NOT NULL,
    event_details character varying NOT NULL,
    event_link character varying NOT NULL,
    PRIMARY KEY (event_id)
    );

CREATE TABLE IF NOT EXISTS my_events.event_favorites
(
    user_id integer NOT NULL,
    event_id integer NOT NULL
);

CREATE TABLE IF NOT EXISTS my_events.events_categories
(
    events_event_id serial NOT NULL,
    categories_category_id serial NOT NULL
);

ALTER TABLE IF EXISTS my_events.events
    ADD FOREIGN KEY (event_zip)
    REFERENCES my_events.postal_codes (zip) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS my_events.event_favorites
    ADD FOREIGN KEY (event_id)
    REFERENCES my_events.events (event_id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS my_events.event_favorites
    ADD FOREIGN KEY (user_id)
    REFERENCES public.users (user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS my_events.events_categories
    ADD FOREIGN KEY (events_event_id)
    REFERENCES my_events.events (event_id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS my_events.events_categories
    ADD FOREIGN KEY (categories_category_id)
    REFERENCES my_events.categories (category_id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


INSERT INTO my_events.postal_codes (zip, city)
VALUES
    (8000, 'Aarhus'),
    (1050, 'Copenhagen'),
    (5000, 'Odense'),
    (9000, 'Aalborg'),
    (1123, 'Copenhagen K');


INSERT INTO my_events.categories (category_name)
VALUES
    ('Music/Festival'),
    ('Networking'),
    ('Fundraising'),
    ('Art & Culture'),
    ('Sport');


INSERT INTO my_events.events (event_name, event_date, event_place, event_zip, event_resume, event_details, event_link)
VALUES
    (
        'Harmony Music Festival',
        '2024-08-10 10:00:00',
        'Jægergårdsgade', 8000,
        'Join us for a day of musical bliss with live performances from local bands and artists.',
        'Harmony Music Festival is a celebration of local talent and community spirit.
         Set in the heart of Aarhus, this one-day festival brings together music lovers of all ages for a lineup of diverse performances across multiple stages.
         From rock and indie to folk and electronic, theres something for everyone to enjoy.
         Food stalls, artisan markets, and family-friendly activities add to the festival atmosphere, making it a day to remember.',
        'https://i.imgur.com/KoeKdf4.png'
    ),
    (
        'Professional Networking Mixer',
        '2024-09-05 18:00:00',
        'Strøget', 1050,
        'Connect with professionals from various industries and expand your network over drinks and conversation.',
        'Looking to expand your professional circle? Join us at our Professional Networking Mixer in Copenhagen.
         Whether youre seeking new career opportunities, collaboration partners,
         or simply looking to meet like-minded professionals, this event provides the perfect platform.
         Enjoy complimentary drinks and appetizers as you engage in meaningful conversations and exchange business cards.
         Dont forget to bring your elevator pitch!',
        'https://i.imgur.com/9D0gzNB.png'
    ),
    (
        'Charity Gala Dinner',
        '2024-10-20 19:00:00',
        'Vestergade', 5000,
        'Support a worthy cause while enjoying an elegant evening of fine dining, entertainment, and philanthropy.',
        'The Charity Gala Dinner is an opportunity to make a difference in the lives of those in need.
         Held in the picturesque city of Odense, this black-tie event features a gourmet dinner prepared by renowned chefs,
         accompanied by fine wines and live music. Guests can participate in silent auctions, raffles, and pledge drives,
         with all proceeds going to support local charities and community initiatives. Join us for an unforgettable evening of generosity and goodwill.',
        'https://i.imgur.com/qEn3qSO.png'
    ),
    (
        'Art & Culture Exhibition',
        '2024-11-30 10:00:00',
        'Jomfru Ane Gade', 9000,
        'Immerse yourself in a showcase of creativity and expression with works of art from local and international artists.',
        'The Art & Culture Exhibition in Aalborg invites art enthusiasts to explore a diverse collection of paintings,
         sculptures, photography, and mixed media artworks.
         Hosted in a contemporary gallery space, the exhibition features both emerging talents and established artists,
         providing a platform for creative dialogue and appreciation. From abstract expressionism to figurative realism,
         theres something to inspire and captivate every visitor.',
        'https://i.imgur.com/stLm4IA.png'
    ),
    (
        'Copenhagen Marathon',
        '2024-06-20 09:00:00',
        'Strandstræde', 1123,
        'Lace up your running shoes and join thousands of participants in one of Europes largest and most scenic marathons.',
        'The Copenhagen Marathon is a bucket-list event for runners of all abilities.
         The flat and fast course takes participants on a tour of Copenhagens iconic landmarks,
         including the Little Mermaid statue, Nyhavn harbor, and Amalienborg Palace. With enthusiastic spectators lining the streets and live music along the route,
         the atmosphere is electric from start to finish. Whether youre aiming for a personal best or simply soaking up the atmosphere,
         the Copenhagen Marathon is an unforgettable experience.',
        'https://i.imgur.com/brZ9Bfs.png'
    ),
    (
        'Jazz in the Park',
        '2024-07-07 15:00:00',
        'Ny Munke Gade', 8000,
        'Relax in the park and enjoy a day of smooth jazz performances by talented musicians from across the country.',
        'Jazz in the Park is a summer tradition in Aarhus, bringing together music lovers for an afternoon of laid-back vibes and cool rhythms.
         Set in the lush surroundings of a local park, the event features live jazz bands performing a mix of classics and contemporary tunes.
         Pack a picnic, bring a blanket, and unwind with friends and family as you soak up the sun and the soulful sounds of jazz.',
        'https://i.imgur.com/CDy32sS.png'
    ),
    (
        'Startup Pitch Competition',
        '2024-04-25 16:00:00',
        'Boulevarden', 9000,
        'Watch aspiring entrepreneurs pitch their innovative business ideas to a panel of investors and industry experts.',
        'Calling all entrepreneurs and investors!
         The Startup Pitch Competition in Aalborg is your chance to discover the next big thing in the startup world. Entrepreneurs will take
         the stage to pitch their business concepts in front of a live audience and a panel of judges.
         From tech startups to social enterprises, you''ll hear a diverse range of ideas and innovations vying for investment and support.
         Join us for an evening of inspiration, networking, and entrepreneurial spirit.',
        'https://i.imgur.com/q9q0MmR.png'
    ),
    (
        'Nordic Winter Festival',
        '2024-12-15 16:00:00',
        'Åboulevarden', 8000,
        'Experience the magic of the holiday season with festive markets, traditional performances, and seasonal treats.',
        'The Nordic Winter Festival is a celebration of Scandinavian culture and winter traditions.
         Held in Aarhus, this three-day event transforms the city into a winter wonderland, complete with twinkling lights,
         cozy fire pits, and festive decorations. Visitors can browse artisanal crafts at the Christmas market,
         sample hot mulled wine and Nordic delicacies, and enjoy live performances of holiday classics.
         It''s a magical experience for the whole family to enjoy.',
        'https://i.imgur.com/bjbbaBI.png'
    ),
    (
        'Street Art Festival',
        '2024-08-03 10:00:00',
        'Copenhagen', 1050,
        'Witness the transformation of urban spaces into vibrant works of art by local and international street artists.',
        'The Street Art Festival brings color and creativity to the streets of Copenhagen, showcasing the talents of graffiti artists, muralists, and stencilists from around the world.
         Over three days, visitors can explore different neighborhoods to discover large-scale murals, interactive installations, and live painting performances.
         Guided tours, workshops, and artist talks offer insight into the techniques and inspirations behind the artworks, making it a dynamic and engaging cultural experience for all.',
        'https://i.imgur.com/osOQuai.png'
    ),
    (
        'Charity Run for Children''s Education',
        '2024-06-08 09:00:00',
        'Odense', 5000,
        'Lace up your running shoes and join us for a fun run to support children''s education initiatives in Denmark.',
        'The Charity Run for Children''s Education is a community event aimed at raising funds and awareness for educational programs serving children in need.
         Participants of all ages and fitness levels are welcome to walk, jog, or run the scenic route through Odense, with distances suitable for families and serious runners alike.
         In addition to the main race, the event features family-friendly activities, entertainment, and opportunities to learn about the impact of education on children''s lives.',
        'https://i.imgur.com/61EAMSs.png'
    );

INSERT INTO my_events.events_categories (events_event_id, categories_category_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 1),
    (7, 2),
    (8, 4),
    (9, 4),
    (10, 3),
    (10, 5);

END;