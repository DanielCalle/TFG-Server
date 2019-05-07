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
    id bigint NOT NULL,
    rating real NOT NULL,
    film_id bigint,
    user_id bigint
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

COPY public.film (id, country, director, duration, genre, imageurl, name, rating, synopsis, trailerurl, uuid) FROM stdin;
1	USA	Roger Allers	88	Animation	http://filmar-develop.herokuapp.com/images/the-lion-king.jpg	El Rey Leon	8	Tras la muerte de su padre, Simba vuelve a enfrentar a su malvado tío, Scar, y reclamar el trono de rey.	https://www.youtube.com/watch?v=xB5ceAruYrI	28f361ae08014c4bb94e8a8d59b91130
2	Japan	Eichiro Oda	99999	Shonen	http://filmar-develop.herokuapp.com/images/z.jpg	Z	10	La película tiene lugar en el Nuevo Mundo, lugar donde los Piratas del Sombrero de Paja se enfrentan a su enemigo más fuerte, un hombre llamado Z.	https://www.youtube.com/watch?v=1gGt1Mg_zSo	zuuid
3	Japan	Eichiro Oda	99999	Shonen	http://filmar-develop.herokuapp.com/images/one-piece.jpg	One Piece	10	Riqueza, fama, poder... un hombre había obtenido todo en este mundo, era el Rey de los Piratas Gold Roger. ... Luffy, después de dicho suceso, decide que se convertirá en el próximo Rey de los Piratas y para ello, deberá encontrar el One Piece.	https://www.youtube.com/watch?v=6aQ_5jyamoU	onepieceuuid
4	USA	Tarantino	102	Cartoon	http://filmar-develop.herokuapp.com/images/frozen.jpg	Frozen	7	Una profecía condena al reino de Arandelle a vivir en un invierno eterno. La joven Anna, el temerario montañero Kristoff y el reno Sven deben emprender un viaje épico y lleno de aventuras en busca de Elsa, la hermana de Anna y Reina de las Nieves.	https://www.youtube.com/watch?v=riLpbAyA354	494b3e72525b4608a05ce27fa4c06e65
5	USA	Roger Allers	88	Animation	http://filmar-develop.herokuapp.com/images/the-lion-king.jpg	El Rey LeÔö£Ôöén	8.5	Tras la muerte de su padre, Simba vuelve a enfrentar a su malvado tío, Scar, y reclamar el trono de rey.	https://www.youtube.com/watch?v=xB5ceAruYrI	b1d63dd282174648a18b207057d9c177
6	USA	Chris Buck	99999	Cartoon	http://filmar-develop.herokuapp.com/images/frozen.jpg	Frozen	7	Una profecía condena al reino de Arandelle a vivir en un invierno eterno. La joven Anna, el temerario montañero Kristoff y el reno Sven deben emprender un viaje épico y lleno de aventuras en busca de Elsa, la hermana de Anna y Reina de las Nieves.	https://www.youtube.com/watch?v=TbQm5doF_Uc	frozenuuid
7	\N	\N	1	\N	\N	Edison Kinetoscopic Record of a Sneeze (1894)	1	\N	\N	1
8	\N	\N	2	\N	\N	Le manoir du diable (1896)	2	\N	\N	2
9	\N	\N	3	\N	\N	Le voyage dans la lune (1902)	3	\N	\N	3
10	\N	\N	4	\N	\N	The  s of Dollie (1908)	4	\N	\N	4
11	\N	\N	5	\N	\N	The Country Doctor (1909)	5	\N	\N	5
12	\N	\N	6	\N	\N	Frankenstein (1910)	6	\N	\N	6
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

COPY public.recommendation (id, rating, film_id, user_id) FROM stdin;
\.


--
-- Data for Name: user_app; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_app (id, email, imageurl, name, password, uuid) FROM stdin;
1	zhong@ucm.es	http://filmar-develop.herokuapp.com/images/zihao.png	Zihao	1234	596abcff71644f4eb855a2d372941674
2	dacuna@ucm.es	http://filmar-develop.herokuapp.com/images/diego.png	Diego	1234	4c8048623944436699b3456fad3238d2
3	dacalle@ucm.es	http://filmar-develop.herokuapp.com/images/daniel.png	Daniel	1234	6cac53efcd2e4caebcda9ea401d0e782
4	cargom11@ucm.es	http://filmar-develop.herokuapp.com/images/carlos.png	Carlos	1234	0ef4e072b24744869bb1c1a238032a2c
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
2	1	1
4	2	1
10	3	1
10	4	1
10	5	1
8	6	1
2	8	1
2	1	2
4	2	2
10	6	2
2	8	2
5	2	3
9	3	3
8	4	3
6	5	3
7	6	3
8	8	3
10	1	4
10	2	4
10	3	4
0	4	4
4	5	4
6	6	4
8	8	4
10	7	1
10	9	1
9	7	2
10	9	2
9	7	3
10	9	3
2	7	4
2	9	4
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 26, true);


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
    ADD CONSTRAINT recommendation_pkey PRIMARY KEY (id);


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
-- Name: user_film_film_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX user_film_film_index ON public.user_film USING btree (user_id);


--
-- Name: user_film_user_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX user_film_user_index ON public.user_film USING btree (user_id);


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

