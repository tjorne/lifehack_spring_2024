--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

-- Started on 2024-03-21 23:47:15

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 41264)
-- Name: drinks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.drinks (
                               drinkname character varying(50) NOT NULL,
                               drinkcategory character varying(20) NOT NULL,
                               ingredients text NOT NULL,
                               instructions text NOT NULL,
                               drinkid integer NOT NULL
);


ALTER TABLE public.drinks OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 41270)
-- Name: drinks_drinkid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.drinks_drinkid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.drinks_drinkid_seq OWNER TO postgres;

--
-- TOC entry 4793 (class 0 OID 0)
-- Dependencies: 221
-- Name: drinks_drinkid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.drinks_drinkid_seq OWNED BY public.drinks.drinkid;


--
-- TOC entry 4642 (class 2604 OID 41271)
-- Name: drinks drinkid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.drinks ALTER COLUMN drinkid SET DEFAULT nextval('public.drinks_drinkid_seq'::regclass);


--
-- TOC entry 4786 (class 0 OID 41264)
-- Dependencies: 220
-- Data for Name: drinks; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Old Fashioned', 'Cocktail', '60 ml bourbon, 1 sukkerterning, 2 dashes Angostura bitter, appelsin- og kirsebærskive til pynt', 'Placer sukkerterningen i et glas og tilsæt bitter. Knus sukkeret, tilsæt bourbon og fyld glasset med is. Rør godt og pynt med en appelsin- og kirsebærskive.', 1);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Espresso Martini', 'Cocktail', '45 ml vodka, 30 ml kaffelikør, 1 shot espresso, 15 ml sukkerlage', 'Ryst alle ingredienser med is og si det i et martini-glas. Pynt eventuelt med tre kaffebønner.', 2);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Piña Colada', 'Cocktail', '60 ml hvid rom, 120 ml ananasjuice, 60 ml kokosmælk, 15 ml limejuice, 30 ml sukkerlage', 'Bland alle ingredienserne med knust is i en blender, indtil det er glat. Server i et højt glas og pynt med en ananasvinge og en parasol.', 3);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Mai Tai', 'Cocktail', '45 ml lys rom, 15 ml mørk rom, 15 ml appelsinlikør, 22.5 ml limejuice, 7.5 ml orgeat-sirup', 'Ryst alle ingredienser med is og si det i et glas fyldt med knust is. Pynt med en myntekvist og en limekile.', 4);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Negroni', 'Cocktail', '30 ml gin, 30 ml Campari, 30 ml sød vermouth', 'Rør alle ingredienser med is og si det i et glas fyldt med is. Pynt med en appelsinskræl.', 5);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Daiquiri', 'Cocktail', '60 ml lys rom, 22.5 ml frisk limejuice, 15 ml sukkerlage', 'Ryst alle ingredienser med is og si det i et afkølet cocktailglas. Pynt med en limekile.', 6);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Long Island Iced Tea', 'Cocktail', '15 ml vodka, 15 ml gin, 15 ml lys rom, 15 ml tequila, 15 ml triple sec, 30 ml frisk limejuice, cola', 'Ryst vodka, gin, rom, tequila, triple sec og limejuice med is. Si det i et højt glas fyldt med is og top op med cola.', 7);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Whiskey Sour', 'Cocktail', '60 ml bourbon, 22.5 ml frisk citronsaft, 15 ml sukkerlage, appelsin- og kirsebærskive til pynt', 'Ryst bourbon, citronsaft og sukkerlage med is. Si det i et glas fyldt med knust is og pynt med en appelsin- og kirsebærskive.', 8);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Singapore Sling', 'Cocktail', '45 ml gin, 15 ml cherry brandy, 7.5 ml Cointreau, 7.5 ml Bénédictine, 60 ml ananasjuice, 22.5 ml limejuice, 7.5 ml grenadine, club soda, appelsin- og kirsebærskive til pynt', 'Ryst gin, cherry brandy, Cointreau, Bénédictine, ananasjuice, limejuice og grenadine med is. Si det i et glas fyldt med knust is og top op med club soda. Pynt med en appelsin- og kirsebærskive.', 9);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Gin and Tonic', 'Long Drink', '60 ml gin, tonicvand, lime eller agurk til pynt', 'Fyld et glas med is, tilsæt gin og top op med tonicvand. Rør let og pynt med en skive lime eller agurk.', 10);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Martini', 'Cocktail', '60 ml gin eller vodka, 10 ml tør vermouth, oliven eller citronskal til pynt', 'Rør gin eller vodka og vermouth med is og si det i et afkølet martini-glas. Pynt med en oliven eller citronskal.', 11);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Tom Collins', 'Long Drink', '45 ml gin, 30 ml frisk citronsaft, 15 ml sukkerlage, club soda, appelsin- og kirsebærskive til pynt', 'Rør gin, citronsaft og sukkerlage med is og si det i et højt glas fyldt med is. Top op med club soda og pynt med en appelsin- og kirsebærskive.', 12);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Whiskey Highball', 'Long Drink', '60 ml bourbon eller whisky, ginger ale eller soda, citronskive til pynt', 'Fyld et højt glas med is, tilsæt bourbon eller whisky og top op med ginger ale eller soda. Rør let og pynt med en citronskive.', 13);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Sazerac', 'Cocktail', '50 ml cognac eller rye whiskey, 10 ml sukkerlage, 3 dashes Peychaud''s bitter, absinthe eller pastis, citronskal til pynt', 'Skyll et afkølet glas med absinthe eller pastis og hæld overskydende væske ud. Rør cognac eller rye whiskey, sukkerlage og bitter med is og si det i det forberedte glas. Klem citronskal over glasset for at frigive olien og gnid det langs kanten. Pynt med citronskal.', 14);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Bloody Mary', 'Cocktail', '45 ml vodka, 90 ml tomatjuice, 15 ml frisk citronsaft, 2 dashes Worcestershire sauce, et par dråber tabascosauce, knivspids sellerisalt, knivspids sort peber, selleristang, citronskive, oliven til pynt', 'Rør alle ingredienser med is undtagen selleristang, citronskive og oliven. Si det i et glas fyldt med is. Pynt med selleristang, citronskive og oliven.', 15);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Pisco Sour', 'Cocktail', '60 ml pisco, 30 ml frisk limejuice, 22.5 ml sukkerlage, 1 æggehvide, 3 dashes Angostura bitter, citronskal til pynt', 'Ryst pisco, frisk limejuice, sukkerlage og æggehvide uden is. Tilføj is og ryst igen. Si det i et afkølet glas og top med Angostura bitter. Pynt med citronskal.', 16);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('White Russian', 'Cocktail', '45 ml vodka, 30 ml kaffelikør, 45 ml fløde', 'Byg alle ingredienser i et glas fyldt med is og rør forsigtigt.', 17);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Mint Julep', 'Cocktail', '60 ml bourbon, 1 teskefuld sukker, 6-8 mynteblade, myntekvist til pynt', 'Muddle mynteblade og sukker i et glas. Tilsæt bourbon og fyld glasset med knust is. Rør godt og pynt med en mynte kvist.', 18);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Irish Coffee', 'Cocktail', '45 ml irsk whiskey, 90 ml varm kaffe, 30 ml flødeskum, 1 teskefuld brunt sukker', 'Hæld irsk whiskey og brunt sukker i et forvarmet glas. Tilføj varm kaffe og rør, indtil sukkeret er opløst. Top med flødeskum.', 19);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Blue Lagoon', 'Cocktail', '45 ml vodka, 15 ml blue curaçao, 7.5 ml frisk limejuice, lemonade, citronskive til pynt', 'Byg vodka, blue curaçao og frisk limejuice i et højt glas fyldt med is. Top op med lemonade og rør let. Pynt med en citronskive.', 20);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Sex on the Beach', 'Cocktail', '45 ml vodka, 15 ml peach schnapps, 45 ml tranebærjuice, 45 ml appelsinjuice, appelsinskive og kirsebær til pynt', 'Byg vodka, peach schnapps, tranebærjuice og appelsinjuice i et højt glas fyldt med is. Rør let og pynt med en appelsinskive og et kirsebær.', 21);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Tequila Sunrise', 'Cocktail', '45 ml tequila, 90 ml appelsinjuice, 15 ml grenadine, appelsinskive og kirsebær til pynt', 'Byg tequila og appelsinjuice i et højt glas fyldt med is. Rør let. Hæld grenadine forsigtigt ned langs indersiden af glasset, så den lægger sig i bunden. Pynt med en appelsinskive og et kirsebær.', 22);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Planter''s Punch', 'Cocktail', '45 ml mørk rom, 30 ml appelsinjuice, 30 ml ananasjuice, 15 ml frisk limejuice, 15 ml grenadine, 7.5 ml sukkerlage, club soda, appelsin- og kirsebærskive til pynt', 'Ryst mørk rom, appelsinjuice, ananasjuice, limejuice, grenadine og sukkerlage med is. Si det i et glas fyldt med knust is. Top op med club soda og pynt med en appelsin- og kirsebærskive.', 23);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Rum Punch', 'Cocktail', '45 ml lys rom, 30 ml appelsinjuice, 30 ml ananasjuice, 15 ml limejuice, 15 ml grenadine, 7.5 ml sukkerlage, appelsin- og kirsebærskive til pynt', 'Ryst lys rom, appelsinjuice, ananasjuice, limejuice, grenadine og sukkerlage med is. Si det i et glas fyldt med knust is. Pynt med en appelsin- og kirsebærskive.', 24);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Blue Hawaiian', 'Cocktail', '45 ml hvid rom, 30 ml blue curaçao, 60 ml ananasjuice, 30 ml kokosmælk, 15 ml limejuice, appelsin- og kirsebærskive til pynt', 'Bland alle ingredienserne med knust is i en blender, indtil det er glat. Server i et højt glas og pynt med en appelsin- og kirsebærskive.', 25);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Alabama Slammer', 'Cocktail', '45 ml amaretto, 45 ml sloe gin, 45 ml appelsinjuice, 15 ml frisk limejuice, appelsinskive og kirsebær til pynt', 'Ryst amaretto, sloe gin, appelsinjuice og limejuice med is. Si det i et glas fyldt med knust is. Pynt med en appelsinskive og et kirsebær.', 26);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('B-52', 'Shot', '15 ml Kahlúa, 15 ml Baileys Irish Cream, 15 ml Grand Marnier', 'Hæld ingredienserne forsigtigt i et shotglas i lag, startende med Kahlúa, derefter Baileys og til sidst Grand Marnier.', 27);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Screwdriver', 'Cocktail', '60 ml vodka, appelsinjuice', 'Byg vodka og appelsinjuice i et glas fyldt med is. Rør let og pynt eventuelt med en appelsinskive.', 28);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Cuba Libre', 'Long Drink', '45 ml lys rom, cola, limebåd til pynt', 'Fyld et glas med is, tilsæt lys rom og top op med cola. Rør let og pynt med en limebåd.', 29);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('White Lady', 'Cocktail', '45 ml gin, 22.5 ml triple sec, 22.5 ml frisk citronsaft, citronskal til pynt', 'Ryst gin, triple sec og frisk citronsaft med is. Si det i et afkølet glas og pynt med citronskal.', 30);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Corpse Reviver No. 2', 'Cocktail', '22.5 ml gin, 22.5 ml Cointreau, 22.5 ml Lillet Blanc, 22.5 ml frisk citronsaft, et par dråber absinthe, appelsinskal til pynt', 'Ryst gin, Cointreau, Lillet Blanc og frisk citronsaft med is. Si det i et afkølet glas, der er blevet skyllet med absinthe. Pynt med appelsinskal.', 31);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('French 75', 'Cocktail', '45 ml gin, 15 ml frisk citronsaft, 15 ml sukkerlage, champagne', 'Ryst gin, frisk citronsaft og sukkerlage med is og si det i et champagneflute. Top op med champagne.', 32);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Dark and Stormy', 'Long Drink', '60 ml mørk rom, ingefærøl, limebåd til pynt', 'Fyld et glas med is, tilsæt mørk rom og top op med ingefærøl. Rør let og pynt med en limebåd.', 33);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Paloma', 'Cocktail', '60 ml tequila, 90 ml grapefrugtjuice, 15 ml frisk limejuice, 15 ml sukkerlage, club soda, grapefrugtskive til pynt', 'Rør tequila, grapefrugtjuice, limejuice og sukkerlage med is og si det i et højt glas fyldt med is. Top op med club soda og pynt med en grapefrugtskive.', 34);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Moscow Mule', 'Cocktail', '60 ml vodka, 120 ml ginger beer, 15 ml frisk limejuice, limekile til pynt', 'Fyld et kobberkrus med is, tilsæt vodka og limejuice. Top op med ginger beer og rør let. Pynt med en limekile.', 35);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('French Martini', 'Cocktail', '45 ml vodka, 15 ml Chambord, 45 ml ananasjuice, hindbær til pynt', 'Ryst vodka, Chambord og ananasjuice med is og si det i et afkølet glas. Pynt med et par hindbær.', 36);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Margarita', 'Cocktail', '60 ml tequila, 30 ml limejuice, 15 ml appelsinlikør, salt til kanten, limekile til pynt', 'Fugt kanten af et margaritaglas med en limekile og dyp det i salt. Ryst tequila, limejuice og appelsinlikør med is og si det i glasset. Pynt med en limekile.', 37);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Caipirinha', 'Cocktail', '60 ml cachaça, 1 lime skåret i kvarte, 2 teskefulde rørsukker', 'Muddle lime og sukker i et glas. Tilsæt cachaça og fyld glasset med knust is. Rør godt og server med sugerør.', 38);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Mojito', 'Cocktail', '60 ml hvid rom, 30 ml limejuice, 2 teskefulde sukker, 6-8 mynteblade, club soda', 'Muddle mynteblade og sukker i et glas. Tilsæt limesaft og rom. Fyld glasset med knust is og top op med club soda. Rør godt og server med sugerør.', 39);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Manhattan', 'Cocktail', '50 ml bourbon, 20 ml sød vermouth, 2 dashes Angostura bitter, kirsebær til pynt', 'Rør bourbon, sød vermouth og bitter med is og si det i et afkølet cocktailglas. Pynt med et kirsebær.', 40);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Cosmopolitan', 'Cocktail', '45 ml citrus vodka, 15 ml tranebærlikør, 15 ml limejuice, 15 ml tranebærsaft', 'Ryst alle ingredienser med is og si det i et cocktailglas. Pynt med en limekile.', 41);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Apple Martini', 'Cocktail', '45 ml vodka, 15 ml æblelikør, 15 ml frisk limejuice, grønt æble til pynt', 'Ryst vodka, æblelikør og frisk limejuice med is og si det i et afkølet glas. Pynt med et grønt æble.', 42);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('White Russian', 'Cocktail', '45 ml vodka, 30 ml kaffelikør, 45 ml fløde', 'Byg alle ingredienser i et glas fyldt med is og rør forsigtigt.', 43);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Black Russian', 'Cocktail', '45 ml vodka, 22.5 ml kaffelikør', 'Byg vodka og kaffelikør i et glas fyldt med is og rør forsigtigt.', 44);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Harvey Wallbanger', 'Cocktail', '45 ml vodka, 90 ml appelsinjuice, 15 ml galliano', 'Byg vodka og appelsinjuice i et glas fyldt med is. Top op med galliano og rør forsigtigt.', 45);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Gin Fizz', 'Cocktail', '60 ml gin, 30 ml frisk citronsaft, 15 ml sukkerlage, club soda', 'Ryst gin, frisk citronsaft og sukkerlage med is og si det i et højt glas fyldt med is. Top op med club soda.', 46);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Sidecar', 'Cocktail', '50 ml cognac, 20 ml triple sec, 20 ml frisk citronsaft, citronskal til pynt', 'Ryst cognac, triple sec og frisk citronsaft med is og si det i et afkølet glas. Pynt med citronskal.', 47);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Mint Julep', 'Cocktail', '60 ml bourbon, 1 teskefuld sukker, 6-8 mynteblade, myntekvist til pynt', 'Muddle mynteblade og sukker i et glas. Tilsæt bourbon og fyld glasset med knust is. Rør godt og pynt med en mynte kvist.', 48);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Bramble', 'Cocktail', '45 ml gin, 22.5 ml frisk citronsaft, 15 ml sukkerlage, 15 ml crème de mûre, brombær til pynt', 'Ryst gin, frisk citronsaft og sukkerlage med is og si det i et afkølet glas. Hæld langsomt crème de mûre over toppen, så den synker til bunden. Pynt med brombær.', 49);
INSERT INTO public.drinks (drinkname, drinkcategory, ingredients, instructions, drinkid) VALUES ('Caipiroska', 'Cocktail', '60 ml vodka, 1 lime skåret i kvarte, 2 teskefulde rørsukker', 'Muddle lime og sukker i et glas. Tilsæt vodka og fyld glasset med knust is. Rør godt og server med sugerør.', 50);


--
-- TOC entry 4794 (class 0 OID 0)
-- Dependencies: 221
-- Name: drinks_drinkid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.drinks_drinkid_seq', 50, true);


-- Completed on 2024-03-21 23:47:15

--
-- PostgreSQL database dump complete
--

