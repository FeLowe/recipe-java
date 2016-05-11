--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE clients (
    id integer NOT NULL,
    name character varying,
    stylist_id integer
);


ALTER TABLE clients OWNER TO "Guest";

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients_id_seq OWNER TO "Guest";

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE clients_id_seq OWNED BY clients.id;


--
-- Name: contacts; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE contacts (
    id integer NOT NULL,
    name character varying,
    connection character varying
);


ALTER TABLE contacts OWNER TO "Guest";

--
-- Name: contacts_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE contacts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE contacts_id_seq OWNER TO "Guest";

--
-- Name: contacts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE contacts_id_seq OWNED BY contacts.id;


--
-- Name: email; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE email (
    id integer NOT NULL,
    email_address character varying,
    type character varying,
    contact_id integer
);


ALTER TABLE email OWNER TO "Guest";

--
-- Name: email_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE email_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE email_id_seq OWNER TO "Guest";

--
-- Name: email_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE email_id_seq OWNED BY email.id;


--
-- Name: mailing_address; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE mailing_address (
    id integer NOT NULL,
    street_address character varying,
    city character varying,
    state character varying,
    zipcode integer,
    type character varying,
    contact_id integer
);


ALTER TABLE mailing_address OWNER TO "Guest";

--
-- Name: mailing_address_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE mailing_address_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mailing_address_id_seq OWNER TO "Guest";

--
-- Name: mailing_address_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE mailing_address_id_seq OWNED BY mailing_address.id;


--
-- Name: phone; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE phone (
    id integer NOT NULL,
    areacode integer,
    number integer,
    type character varying,
    contact_id integer
);


ALTER TABLE phone OWNER TO "Guest";

--
-- Name: phone_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE phone_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE phone_id_seq OWNER TO "Guest";

--
-- Name: phone_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE phone_id_seq OWNED BY phone.id;


--
-- Name: stylists; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE stylists (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE stylists OWNER TO "Guest";

--
-- Name: stylists_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE stylists_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stylists_id_seq OWNER TO "Guest";

--
-- Name: stylists_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE stylists_id_seq OWNED BY stylists.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY clients ALTER COLUMN id SET DEFAULT nextval('clients_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY contacts ALTER COLUMN id SET DEFAULT nextval('contacts_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY email ALTER COLUMN id SET DEFAULT nextval('email_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY mailing_address ALTER COLUMN id SET DEFAULT nextval('mailing_address_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY phone ALTER COLUMN id SET DEFAULT nextval('phone_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY stylists ALTER COLUMN id SET DEFAULT nextval('stylists_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY clients (id, name, stylist_id) FROM stdin;
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('clients_id_seq', 1, false);


--
-- Data for Name: contacts; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY contacts (id, name, connection) FROM stdin;
1	Charlie	business
2	Didi	family
3	Max	friends
4	Frank	school
\.


--
-- Name: contacts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('contacts_id_seq', 4, true);


--
-- Data for Name: email; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY email (id, email_address, type, contact_id) FROM stdin;
1	Charlie@pattyspub.com	work	1
2	Didi@pattyspub.com	work	2
3	Max@pattyspub.com	work	3
4	Frank@pattyspub.com	work	4
5	Charlie@gmail.com	personal	1
6	Didi@gmail.com	personal	2
7	Max@gmail.com	personal	3
8	Frank@gmail.com	personal	4
\.


--
-- Name: email_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('email_id_seq', 8, true);


--
-- Data for Name: mailing_address; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY mailing_address (id, street_address, city, state, zipcode, type, contact_id) FROM stdin;
1	2323 Pattys Ln	Philadelphia	PA	19101	Work	1
2	2323 Pattys Ln	Philadelphia	PA	19101	Work	2
3	2323 Pattys Ln	Philadelphia	PA	19101	Work	3
5	2323 Pattys Ln	Philadelphia	PA	19101	Work	4
6	2020 Home Rd	Philadelphia	PA	19101	Home	1
7	2020 Home Rd	Philadelphia	PA	19101	Home	2
8	2020 Home Rd	Philadelphia	PA	19101	Home	3
9	2020 Home Rd	Philadelphia	PA	19101	Home	4
\.


--
-- Name: mailing_address_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('mailing_address_id_seq', 9, true);


--
-- Data for Name: phone; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY phone (id, areacode, number, type, contact_id) FROM stdin;
1	215	5551212	work	1
2	215	5561212	work	2
3	215	5571212	work	3
4	215	5581212	work	4
5	215	5552020	home	1
6	215	5562020	home	2
7	215	5572020	home	3
8	215	5582020	home	4
\.


--
-- Name: phone_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('phone_id_seq', 8, true);


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY stylists (id, name) FROM stdin;
\.


--
-- Name: stylists_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('stylists_id_seq', 1, false);


--
-- Name: clients_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: contacts_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY contacts
    ADD CONSTRAINT contacts_pkey PRIMARY KEY (id);


--
-- Name: email_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY email
    ADD CONSTRAINT email_pkey PRIMARY KEY (id);


--
-- Name: mailing_address_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY mailing_address
    ADD CONSTRAINT mailing_address_pkey PRIMARY KEY (id);


--
-- Name: phone_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY phone
    ADD CONSTRAINT phone_pkey PRIMARY KEY (id);


--
-- Name: stylists_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY stylists
    ADD CONSTRAINT stylists_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

