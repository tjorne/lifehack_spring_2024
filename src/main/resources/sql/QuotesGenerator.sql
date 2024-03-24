-- Table: public.quotes

-- DROP TABLE IF EXISTS public.quotes;

CREATE TABLE IF NOT EXISTS public.quotes
(
    quote_id SERIAL PRIMARY KEY,
    quote_genre character varying(50) NOT NULL,
    quote_body character varying(200) NOT NULL,
    CONSTRAINT unique_quotes_body UNIQUE (quote_body)
    )
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.quotes
    OWNER TO postgres;

INSERT INTO public.quotes(
    quote_id, quote_genre, quote_body)
VALUES (1, 'happynpc', '"There are always flowers for those who want to see them."'),
       (2, 'happynpc', 'Don’t ever be ashamed of loving the strange things that make your weird little heart '),
       (3, 'happynpc','A happy life is one spent in learning, earning, and yearning.'),
       (4, 'happynpc', 'If you want to be happy, be.'),
       (5, 'happynpc', 'Be happy for this moment. This moment is your life.'),
       (6, 'motivational', '"I dont stop when Im tired, I stop when Im done."'),
       (7, 'motivational', '"Analyze your schedule, kill your empty habits, burn out the bullshit, and see what’s left."'),
       (8, 'motivational', '"Whos gonna carry the boats, and the logs?"'),
       (9, 'motivational', 'You want to be uncommon amongst uncommon people. Period.'),
       (10, 'motivational', 'Become A Savage & Live on Your Own Terms.'),
       (11, 'silly', '"Just remember, at the end of the day its night."'),
       (12, 'silly', '"Its better to cum in the sink, than to sink in the cum."'),
       (13, 'silly', '"Its better to shit in the shower, than to shower in the shit."'),
       (14, 'silly', 'Just give up.'),
       (15, 'league', 'Talon E out of the balcony.'),
       (16, 'league', 'Tristana W off the building.'),
       (17, 'league', 'Zac E off a cliff.'),
       (18, 'league', 'Talon ult urself.'),
       (19, 'sadnpc', 'Lifes under no obligation to give us what we expect.'),
       (20, 'sadnpc', 'Learning is a gift. Even when pain is your teacher.'),
       (21, 'sadnpc', 'The word "happy" would lose its meaning if it were not balanced by sadness.'),
       (22, 'sadnpc', '"You know, a heart can be broken, but it keeps on beating, just the same."'),
       (23, 'sadnpc', '"Stab the body and it heals, but injure the heart and the wound lasts a lifetime."'),
       (24, 'sadnpc', '"It may be the worst day of your life, but its only the worst day of your life so far."'),
       (25, 'silly', 'Put a toothpick under your toenail and go kick a wall.'),
       (26, 'silly', '"Your life may seem bad now, but trust me, it gets worse."');