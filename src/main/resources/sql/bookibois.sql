CREATE TABLE IF NOT EXISTS public.booking
(
    booking_id serial NOT NULL,
    booking_type character varying(75) COLLATE pg_catalog."default" NOT NULL,
    date character varying(16) COLLATE pg_catalog."default" NOT NULL,
    "time" character varying(16) COLLATE pg_catalog."default" NOT NULL,
    handler character varying(50) COLLATE pg_catalog."default",
    customer_name character varying(75) COLLATE pg_catalog."default" NOT NULL,
    customer_mobile character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT booking_pkey PRIMARY KEY (booking_id)
    );
