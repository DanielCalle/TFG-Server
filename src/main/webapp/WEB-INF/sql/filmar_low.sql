--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 11.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: film; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.film (
    id bigint NOT NULL,
    country character varying(255),
    director character varying(255),
    duration integer NOT NULL,
    genre character varying(255),
    imageurl character varying(255),
    name character varying(255) NOT NULL,
    premiere timestamp without time zone NOT NULL,
    rating double precision NOT NULL,
    synopsis character varying(255),
    trailerurl character varying(255),
    uuid character varying(255) NOT NULL
);


ALTER TABLE public.film OWNER TO postgres;

--
-- Name: friendship; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.friendship (
    active boolean NOT NULL,
    date date,
    friend_id bigint NOT NULL,
    requester_id bigint NOT NULL
);


ALTER TABLE public.friendship OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: plan; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.plan (
    id bigint NOT NULL,
    date date,
    description character varying(255),
    location character varying(255),
    title character varying(255),
    creator_id bigint,
    film_id bigint
);


ALTER TABLE public.plan OWNER TO postgres;

--
-- Name: plan_joined_users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.plan_joined_users (
    plan_id bigint NOT NULL,
    joined_users_id bigint NOT NULL
);


ALTER TABLE public.plan_joined_users OWNER TO postgres;

--
-- Name: recommendation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recommendation (
    date timestamp without time zone,
    rating real NOT NULL,
    film_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.recommendation OWNER TO postgres;

--
-- Name: user_app; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_app (
    id bigint NOT NULL,
    email character varying(255),
    imageurl character varying(255),
    name character varying(255) NOT NULL,
    password character varying(255),
    uuid character varying(255) NOT NULL
);


ALTER TABLE public.user_app OWNER TO postgres;

--
-- Name: user_app_joined_plans; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_app_joined_plans (
    user_id bigint NOT NULL,
    joined_plans_id bigint NOT NULL
);


ALTER TABLE public.user_app_joined_plans OWNER TO postgres;

--
-- Name: user_film; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_film (
    rating real NOT NULL,
    film_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.user_film OWNER TO postgres;

--
-- Data for Name: film; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.film (id, country, director, duration, genre, imageurl, name, premiere, rating, synopsis, trailerurl, uuid) FROM stdin;
5	USA	Roger Allers, Rob Minkoff	88	Animation, Adventure, Drama, Family, Musical	http://filmar-develop.herokuapp.com/images/the-lion-king.jpg	The Lion King	1994-11-08 01:00:00	8.5	A Lion cub crown prince is tricked by a treacherous uncle into thinking he caused his father's death and flees into exile in despair, only to learn in adulthood his identity and his responsibilities.	https://www.youtube.com/watch?v=_ujGv5dhGfk&t=3s	c250465cb5ed43fd99b32ba5b82b7c6d
6	USA	Chris Buck, Jennifer Lee	102	Animation, Adventure, Comedy, Family, Fantasy, Musical	http://filmar-develop.herokuapp.com/images/frozen.jpg	Frozen	2013-11-29 01:00:00	7.5	When the newly-crowned Queen Elsa accidentally uses her power to turn things into ice to curse her home in infinite winter, her sister Anna teams up with a mountain man, his playful reindeer, and a snowman to change the weather condition.	https://www.youtube.com/watch?v=TbQm5doF_Uc&t=20s	5e39f9e1a0724903800ddc38966a59da
7	Japan	Tatsuya Nagamine	108	Animation, Action, Adventure, Fantasy	http://filmar-develop.herokuapp.com/images/one-piece-film-z.jpg	One Piece Film Z	2012-12-15 01:00:00	8	A former Marine stands in the way of the Straw Hat Pirates.	https://www.youtube.com/watch?v=qqlC1xyb63M	01ea98c2e5bc46d69a183f26287d3cca
8	USA, UK	Christopher Nolan	152	Action, Crime, Drama, Thriller	http://filmar-develop.herokuapp.com/images/the-dark-knight.jpg	The Dark Knight	2008-10-13 02:00:00	9	When the menace known as The Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.	https://www.youtube.com/watch?v=EXeTwQWrcwY	7e57093e919b460bb13a4b41b50cb8a8
9	New Zealand, USA	Peter Jackson	201	Adventure, Drama, Fantasy	http://filmar-develop.herokuapp.com/images/the-lord-of-the-rings-the-return-of-the-king.jpg	The Lord of the Rings: The Return of the King	2003-12-17 01:00:00	8.90000000000000036	Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.	https://www.youtube.com/watch?v=r5X-hFf6Bwo	549171562078451ba39ad3585c2d1aed
10	Australia, USA	Baz Luhrmann	143	Drama, Romance	http://filmar-develop.herokuapp.com/images/the-great-gatsby.jpg	The Great Gatsby	2013-05-17 02:00:00	7.29999999999999982	A writer and wall street trader, Nick, finds himself drawn to the past and lifestyle of his millionaire neighbor, Jay Gatsby.	https://www.youtube.com/watch?v=rARN6agiW7o	3f185c39abdc47609d341d9a2be3ed04
11	USA	Tim Miller	108	Action, Adventure, Comedy, Sci-Fi	http://filmar-develop.herokuapp.com/images/deadpool.jpg	Deadpool	2016-02-19 01:00:00	8	A wisecracking mercenary gets experimented on and becomes immortal but ugly, and sets out to track down the man who ruined his looks.	https://www.youtube.com/watch?v=QyU7glpHg-c	bc40e189ef7f4898acc1640ba05e4995
12	USA, China, Hong Kong, Australia, Canada	Yimou Zhang	103	Action, Adventure, Fantasy, Thriller	http://filmar-develop.herokuapp.com/images/the-great-wall.jpg	The Great Wall	2017-02-17 01:00:00	6	In ancient China, a group of European mercenaries encounters a secret army that maintains and defends the Great Wall of China against a horde of monstrous creatures.	https://www.youtube.com/watch?v=6SKI9rgqFck	a63353cadce54c1ebf790846d36cf6f8
13	USA	Gary Ross	142	Action, Adventure, Sci-Fi, Thriller	http://filmar-develop.herokuapp.com/images/the-hunger-games.jpg	The Hunger Games	2012-04-20 02:00:00	7.20000000000000018	Katniss Everdeen voluntarily takes her younger sister's place in the Hunger Games: a televised competition in which two teenagers from each of the twelve Districts of Panem are chosen at random to fight to the death.	https://www.youtube.com/watch?v=mfmrPu43DF8	2aa9feb749b643599be8af911f053236
15	Japan, USA, UK	Rob Letterman	104	Action, Adventure, Comedy, Family, Mystery, Sci-Fi	http://filmar-develop.herokuapp.com/images/pokemon-detective-pikachu.jpg	Pokémon Detective Pikachu	2019-05-10 02:00:00	7.09999999999999964	In a world where people collect Pokémon to do battle, a boy comes across an intelligent talking Pikachu who seeks to be a detective.	https://www.youtube.com/watch?v=1roy4o4tqQM	2d542ed6ec8d4fbca0b9a4dc7ce148d2
14	USA	Anthony Russo, Joe Russo	181	Action, Adventure, Fantasy, Sci-Fi	http://filmar-develop.herokuapp.com/images/avengers-endgame.jpg	Avengers: Endgame	2019-04-25 02:00:00	8.80000000000000071	After the devastating events of Vengadores: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to undo Thanos' actions and restore order to the universe.	https://www.youtube.com/watch?v=TcMBFSGVi1c	94c8b578a35043738c1b4aae1631adb0
16	USA, France	Louis Leterrier	115	Crime, Mystery, Thriller	http://filmar-develop.herokuapp.com/images/now-you-see-me.jpg	Now You See Me	2013-07-19 02:00:00	7.29999999999999982	An F.B.I. Agent and an Interpol Detective track a team of illusionists who pull off bank heists during their performances, and reward their audiences with the money.	https://www.youtube.com/watch?v=4OtM9j2lcUA	40454358291148c3ad7c2287a091637d
\.


--
-- Data for Name: friendship; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.friendship (active, date, friend_id, requester_id) FROM stdin;
\.


--
-- Data for Name: plan; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.plan (id, date, description, location, title, creator_id, film_id) FROM stdin;
\.


--
-- Data for Name: plan_joined_users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.plan_joined_users (plan_id, joined_users_id) FROM stdin;
\.


--
-- Data for Name: recommendation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.recommendation (date, rating, film_id, user_id) FROM stdin;
\.


--
-- Data for Name: user_app; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_app (id, email, imageurl, name, password, uuid) FROM stdin;
1	zhong@ucm.es	http://filmar-develop.herokuapp.com/images/zihao.png	Zihao	zihao	596abcff71644f4eb855a2d372941674
2	dacuna@ucm.es	http://filmar-develop.herokuapp.com/images/diego.png	Diego	diego	4c8048623944436699b3456fad3238d2
3	dacalle@ucm.es	http://filmar-develop.herokuapp.com/images/daniel.png	Daniel	daniel	6cac53efcd2e4caebcda9ea401d0e782
4	cargom11@ucm.es	http://filmar-develop.herokuapp.com/images/carlos.png	Carlos	carlos	0ef4e072b24744869bb1c1a238032a2c
\.


--
-- Data for Name: user_app_joined_plans; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_app_joined_plans (user_id, joined_plans_id) FROM stdin;
\.


--
-- Data for Name: user_film; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_film (rating, film_id, user_id) FROM stdin;
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 16, true);


--
-- Name: film film_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.film
    ADD CONSTRAINT film_pkey PRIMARY KEY (id);


--
-- Name: friendship friendship_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.friendship
    ADD CONSTRAINT friendship_pkey PRIMARY KEY (friend_id, requester_id);


--
-- Name: plan plan_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plan
    ADD CONSTRAINT plan_pkey PRIMARY KEY (id);


--
-- Name: recommendation recommendation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recommendation
    ADD CONSTRAINT recommendation_pkey PRIMARY KEY (film_id, user_id);


--
-- Name: user_app uk_bm3hjcd1lreqoeofr5bf05edt; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_app
    ADD CONSTRAINT uk_bm3hjcd1lreqoeofr5bf05edt UNIQUE (uuid);


--
-- Name: film uk_e1t3eacyryxoedo9nbx7s9t67; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.film
    ADD CONSTRAINT uk_e1t3eacyryxoedo9nbx7s9t67 UNIQUE (uuid);


--
-- Name: user_app uk_mk1xvwget6ponrb1elcqe09uv; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_app
    ADD CONSTRAINT uk_mk1xvwget6ponrb1elcqe09uv UNIQUE (email);


--
-- Name: user_app user_app_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_app
    ADD CONSTRAINT user_app_pkey PRIMARY KEY (id);


--
-- Name: user_film user_film_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_film
    ADD CONSTRAINT user_film_pkey PRIMARY KEY (film_id, user_id);


--
-- Name: user_film fk1ofqx8ytchmo3ej5rguoxsafv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_film
    ADD CONSTRAINT fk1ofqx8ytchmo3ej5rguoxsafv FOREIGN KEY (user_id) REFERENCES public.user_app(id);


--
-- Name: user_app_joined_plans fk3c11q0mnvhsx1ct2ra9xdbgii; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_app_joined_plans
    ADD CONSTRAINT fk3c11q0mnvhsx1ct2ra9xdbgii FOREIGN KEY (user_id) REFERENCES public.user_app(id);


--
-- Name: plan_joined_users fk8k02ls57v8v15pnrf0m7wmx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plan_joined_users
    ADD CONSTRAINT fk8k02ls57v8v15pnrf0m7wmx FOREIGN KEY (plan_id) REFERENCES public.plan(id);


--
-- Name: user_film fk9035mkpx6x9m8mbeapvfobdo1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_film
    ADD CONSTRAINT fk9035mkpx6x9m8mbeapvfobdo1 FOREIGN KEY (film_id) REFERENCES public.film(id);


--
-- Name: user_app_joined_plans fkbgfjuea6tni438a41x7tqame8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_app_joined_plans
    ADD CONSTRAINT fkbgfjuea6tni438a41x7tqame8 FOREIGN KEY (joined_plans_id) REFERENCES public.plan(id);


--
-- Name: plan fkc2a2s5d9vvbhd6jta6kx0ymj1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plan
    ADD CONSTRAINT fkc2a2s5d9vvbhd6jta6kx0ymj1 FOREIGN KEY (film_id) REFERENCES public.film(id);


--
-- Name: recommendation fkc8i5ytgbq1h5uf9ogn7ka1na8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recommendation
    ADD CONSTRAINT fkc8i5ytgbq1h5uf9ogn7ka1na8 FOREIGN KEY (user_id) REFERENCES public.user_app(id);


--
-- Name: friendship fkfo3vbjkq68losswkh1eblfgob; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.friendship
    ADD CONSTRAINT fkfo3vbjkq68losswkh1eblfgob FOREIGN KEY (requester_id) REFERENCES public.user_app(id);


--
-- Name: plan fki4w7w3j8qgyi2ksdwo9qf8wkr; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plan
    ADD CONSTRAINT fki4w7w3j8qgyi2ksdwo9qf8wkr FOREIGN KEY (creator_id) REFERENCES public.user_app(id);


--
-- Name: recommendation fkm28spq7y7c3edm4u1x5vn7b3b; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recommendation
    ADD CONSTRAINT fkm28spq7y7c3edm4u1x5vn7b3b FOREIGN KEY (film_id) REFERENCES public.film(id);


--
-- Name: plan_joined_users fknv2pb67tx9ynyt8782j2drx21; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plan_joined_users
    ADD CONSTRAINT fknv2pb67tx9ynyt8782j2drx21 FOREIGN KEY (joined_users_id) REFERENCES public.user_app(id);


--
-- Name: friendship fkp6wnumhmn94es2i67nmy1nv9g; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.friendship
    ADD CONSTRAINT fkp6wnumhmn94es2i67nmy1nv9g FOREIGN KEY (friend_id) REFERENCES public.user_app(id);


--
-- PostgreSQL database dump complete
--

