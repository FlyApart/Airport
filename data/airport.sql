PGDMP     :                     x            airport    12.1    12.1 V    t           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            u           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            v           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            w           1262    16394    airport    DATABASE     �   CREATE DATABASE airport WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE airport;
                postgres    false            �            1259    16670    airline    TABLE     �   CREATE TABLE public.airline (
    id bigint NOT NULL,
    name integer NOT NULL,
    website character varying(50),
    fleet bigint NOT NULL,
    id_country integer,
    founded date NOT NULL,
    flights_per_year integer
);
    DROP TABLE public.airline;
       public         heap    postgres    false            �            1259    16668     airline_id_seq    SEQUENCE     z   CREATE SEQUENCE public." airline_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public." airline_id_seq";
       public          postgres    false    219            x           0    0     airline_id_seq    SEQUENCE OWNED BY     D   ALTER SEQUENCE public." airline_id_seq" OWNED BY public.airline.id;
          public          postgres    false    218            �            1259    16483 	   airplanes    TABLE     �   CREATE TABLE public.airplanes (
    id bigint NOT NULL,
    model character varying NOT NULL,
    seats integer,
    max_flight_duration time without time zone,
    built date
);
    DROP TABLE public.airplanes;
       public         heap    postgres    false            �            1259    16481     airplanes_id_seq    SEQUENCE     |   CREATE SEQUENCE public." airplanes_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public." airplanes_id_seq";
       public          postgres    false    207            y           0    0     airplanes_id_seq    SEQUENCE OWNED BY     H   ALTER SEQUENCE public." airplanes_id_seq" OWNED BY public.airplanes.id;
          public          postgres    false    206            �            1259    16495    airports    TABLE     �   CREATE TABLE public.airports (
    id integer NOT NULL,
    title character varying NOT NULL,
    id_country bigint NOT NULL
);
    DROP TABLE public.airports;
       public         heap    postgres    false            �            1259    16493    airports_id_seq    SEQUENCE     �   CREATE SEQUENCE public.airports_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.airports_id_seq;
       public          postgres    false    209            z           0    0    airports_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.airports_id_seq OWNED BY public.airports.id;
          public          postgres    false    208            �            1259    16547    country    TABLE     �   CREATE TABLE public.country (
    id bigint NOT NULL,
    name character varying NOT NULL,
    population bigint,
    location character varying NOT NULL
);
    DROP TABLE public.country;
       public         heap    postgres    false            �            1259    16545    country_id_seq    SEQUENCE     w   CREATE SEQUENCE public.country_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.country_id_seq;
       public          postgres    false    215            {           0    0    country_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.country_id_seq OWNED BY public.country.id;
          public          postgres    false    214            �            1259    16535 	   discounts    TABLE     �   CREATE TABLE public.discounts (
    id bigint NOT NULL,
    title character varying NOT NULL,
    sum integer NOT NULL,
    id_tickets bigint NOT NULL
);
    DROP TABLE public.discounts;
       public         heap    postgres    false            �            1259    16533    discounts_id_seq    SEQUENCE     y   CREATE SEQUENCE public.discounts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.discounts_id_seq;
       public          postgres    false    213            |           0    0    discounts_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.discounts_id_seq OWNED BY public.discounts.id;
          public          postgres    false    212            �            1259    16474    flights    TABLE     `  CREATE TABLE public.flights (
    id bigint NOT NULL,
    " flights_number" character varying(40) NOT NULL,
    " departure_date" timestamp with time zone NOT NULL,
    arrive_date timestamp with time zone NOT NULL,
    " flight_time" time without time zone,
    free_places integer NOT NULL,
    id_airplanes bigint,
    id_airport bigint NOT NULL
);
    DROP TABLE public.flights;
       public         heap    postgres    false            �            1259    16472    flights_id_seq    SEQUENCE     �   CREATE SEQUENCE public.flights_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.flights_id_seq;
       public          postgres    false    205            }           0    0    flights_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.flights_id_seq OWNED BY public.flights.id;
          public          postgres    false    204            �            1259    16462 
   passengers    TABLE       CREATE TABLE public.passengers (
    id bigint NOT NULL,
    first_name character varying(100) NOT NULL,
    second_name character varying(100) NOT NULL,
    login character varying(20) NOT NULL,
    password character varying NOT NULL,
    id_country bigint NOT NULL,
    created date,
    changed timestamp without time zone,
    id_tickets bigint,
    date_birth date NOT NULL
);
    DROP TABLE public.passengers;
       public         heap    postgres    false            �            1259    16460    passengers_id_seq    SEQUENCE     �   CREATE SEQUENCE public.passengers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.passengers_id_seq;
       public          postgres    false    203            ~           0    0    passengers_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.passengers_id_seq OWNED BY public.passengers.id;
          public          postgres    false    202            �            1259    16567    passport    TABLE     �   CREATE TABLE public.passport (
    id bigint NOT NULL,
    series integer NOT NULL,
    number integer NOT NULL,
    id_passengers integer
);
    DROP TABLE public.passport;
       public         heap    postgres    false            �            1259    16565    passport_id_seq    SEQUENCE     x   CREATE SEQUENCE public.passport_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.passport_id_seq;
       public          postgres    false    217                       0    0    passport_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.passport_id_seq OWNED BY public.passport.id;
          public          postgres    false    216            �            1259    16508    tickets    TABLE     �   CREATE TABLE public.tickets (
    id bigint NOT NULL,
    id_flights bigint NOT NULL,
    place character varying(6) NOT NULL,
    price double precision NOT NULL,
    id_discounts bigint NOT NULL,
    reservation boolean
);
    DROP TABLE public.tickets;
       public         heap    postgres    false            �            1259    16506    tickets_id_seq    SEQUENCE     w   CREATE SEQUENCE public.tickets_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.tickets_id_seq;
       public          postgres    false    211            �           0    0    tickets_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.tickets_id_seq OWNED BY public.tickets.id;
          public          postgres    false    210            �
           2604    16673 
   airline id    DEFAULT     k   ALTER TABLE ONLY public.airline ALTER COLUMN id SET DEFAULT nextval('public." airline_id_seq"'::regclass);
 9   ALTER TABLE public.airline ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    218    219            �
           2604    16486    airplanes id    DEFAULT     o   ALTER TABLE ONLY public.airplanes ALTER COLUMN id SET DEFAULT nextval('public." airplanes_id_seq"'::regclass);
 ;   ALTER TABLE public.airplanes ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    206    207    207            �
           2604    16498    airports id    DEFAULT     j   ALTER TABLE ONLY public.airports ALTER COLUMN id SET DEFAULT nextval('public.airports_id_seq'::regclass);
 :   ALTER TABLE public.airports ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    208    209    209            �
           2604    16550 
   country id    DEFAULT     h   ALTER TABLE ONLY public.country ALTER COLUMN id SET DEFAULT nextval('public.country_id_seq'::regclass);
 9   ALTER TABLE public.country ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214    215            �
           2604    16538    discounts id    DEFAULT     l   ALTER TABLE ONLY public.discounts ALTER COLUMN id SET DEFAULT nextval('public.discounts_id_seq'::regclass);
 ;   ALTER TABLE public.discounts ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    213    213            �
           2604    16517 
   flights id    DEFAULT     h   ALTER TABLE ONLY public.flights ALTER COLUMN id SET DEFAULT nextval('public.flights_id_seq'::regclass);
 9   ALTER TABLE public.flights ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    205    204    205            �
           2604    16574    passengers id    DEFAULT     n   ALTER TABLE ONLY public.passengers ALTER COLUMN id SET DEFAULT nextval('public.passengers_id_seq'::regclass);
 <   ALTER TABLE public.passengers ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    203    202    203            �
           2604    16570    passport id    DEFAULT     j   ALTER TABLE ONLY public.passport ALTER COLUMN id SET DEFAULT nextval('public.passport_id_seq'::regclass);
 :   ALTER TABLE public.passport ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    217    217            �
           2604    16650 
   tickets id    DEFAULT     h   ALTER TABLE ONLY public.tickets ALTER COLUMN id SET DEFAULT nextval('public.tickets_id_seq'::regclass);
 9   ALTER TABLE public.tickets ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    211    210    211            q          0    16670    airline 
   TABLE DATA           b   COPY public.airline (id, name, website, fleet, id_country, founded, flights_per_year) FROM stdin;
    public          postgres    false    219   �a       e          0    16483 	   airplanes 
   TABLE DATA           Q   COPY public.airplanes (id, model, seats, max_flight_duration, built) FROM stdin;
    public          postgres    false    207   �a       g          0    16495    airports 
   TABLE DATA           9   COPY public.airports (id, title, id_country) FROM stdin;
    public          postgres    false    209   �a       m          0    16547    country 
   TABLE DATA           A   COPY public.country (id, name, population, location) FROM stdin;
    public          postgres    false    215   �a       k          0    16535 	   discounts 
   TABLE DATA           ?   COPY public.discounts (id, title, sum, id_tickets) FROM stdin;
    public          postgres    false    213   �a       c          0    16474    flights 
   TABLE DATA           �   COPY public.flights (id, " flights_number", " departure_date", arrive_date, " flight_time", free_places, id_airplanes, id_airport) FROM stdin;
    public          postgres    false    205   b       a          0    16462 
   passengers 
   TABLE DATA           �   COPY public.passengers (id, first_name, second_name, login, password, id_country, created, changed, id_tickets, date_birth) FROM stdin;
    public          postgres    false    203   5b       o          0    16567    passport 
   TABLE DATA           E   COPY public.passport (id, series, number, id_passengers) FROM stdin;
    public          postgres    false    217   Rb       i          0    16508    tickets 
   TABLE DATA           Z   COPY public.tickets (id, id_flights, place, price, id_discounts, reservation) FROM stdin;
    public          postgres    false    211   ob       �           0    0     airline_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public." airline_id_seq"', 1, false);
          public          postgres    false    218            �           0    0     airplanes_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public." airplanes_id_seq"', 1, false);
          public          postgres    false    206            �           0    0    airports_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.airports_id_seq', 1, false);
          public          postgres    false    208            �           0    0    country_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.country_id_seq', 1, false);
          public          postgres    false    214            �           0    0    discounts_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.discounts_id_seq', 1, false);
          public          postgres    false    212            �           0    0    flights_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.flights_id_seq', 1, false);
          public          postgres    false    204            �           0    0    passengers_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.passengers_id_seq', 1, false);
          public          postgres    false    202            �           0    0    passport_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.passport_id_seq', 1, false);
          public          postgres    false    216            �           0    0    tickets_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.tickets_id_seq', 1, false);
          public          postgres    false    210            �
           2606    16681    airline  airline_pk 
   CONSTRAINT     S   ALTER TABLE ONLY public.airline
    ADD CONSTRAINT " airline_pk" PRIMARY KEY (id);
 ?   ALTER TABLE ONLY public.airline DROP CONSTRAINT " airline_pk";
       public            postgres    false    219            �
           2606    16492    airplanes  airplanes_pk 
   CONSTRAINT     W   ALTER TABLE ONLY public.airplanes
    ADD CONSTRAINT " airplanes_pk" PRIMARY KEY (id);
 C   ALTER TABLE ONLY public.airplanes DROP CONSTRAINT " airplanes_pk";
       public            postgres    false    207            �
           2606    16504    airports airports_pk 
   CONSTRAINT     R   ALTER TABLE ONLY public.airports
    ADD CONSTRAINT airports_pk PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.airports DROP CONSTRAINT airports_pk;
       public            postgres    false    209            �
           2606    16556    country country_pk 
   CONSTRAINT     P   ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pk PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.country DROP CONSTRAINT country_pk;
       public            postgres    false    215            �
           2606    16544    discounts discounts_pk 
   CONSTRAINT     T   ALTER TABLE ONLY public.discounts
    ADD CONSTRAINT discounts_pk PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.discounts DROP CONSTRAINT discounts_pk;
       public            postgres    false    213            �
           2606    16519    flights flights_pk 
   CONSTRAINT     P   ALTER TABLE ONLY public.flights
    ADD CONSTRAINT flights_pk PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.flights DROP CONSTRAINT flights_pk;
       public            postgres    false    205            �
           2606    16576    passengers passengers_pk 
   CONSTRAINT     V   ALTER TABLE ONLY public.passengers
    ADD CONSTRAINT passengers_pk PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.passengers DROP CONSTRAINT passengers_pk;
       public            postgres    false    203            �
           2606    16573    passport passport_pk 
   CONSTRAINT     R   ALTER TABLE ONLY public.passport
    ADD CONSTRAINT passport_pk PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.passport DROP CONSTRAINT passport_pk;
       public            postgres    false    217            �
           2606    16652    tickets tickets_pk 
   CONSTRAINT     P   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_pk PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_pk;
       public            postgres    false    211            �
           1259    16679     airline_id_uindex    INDEX     M   CREATE UNIQUE INDEX " airline_id_uindex" ON public.airline USING btree (id);
 (   DROP INDEX public." airline_id_uindex";
       public            postgres    false    219            �
           1259    16490     airplanes_id_uindex    INDEX     Q   CREATE UNIQUE INDEX " airplanes_id_uindex" ON public.airplanes USING btree (id);
 *   DROP INDEX public." airplanes_id_uindex";
       public            postgres    false    207            �
           1259    16502    airports_id_uindex    INDEX     L   CREATE UNIQUE INDEX airports_id_uindex ON public.airports USING btree (id);
 &   DROP INDEX public.airports_id_uindex;
       public            postgres    false    209            �
           1259    16554    country_id_uindex    INDEX     J   CREATE UNIQUE INDEX country_id_uindex ON public.country USING btree (id);
 %   DROP INDEX public.country_id_uindex;
       public            postgres    false    215            �
           1259    16542    discounts_id_uindex    INDEX     N   CREATE UNIQUE INDEX discounts_id_uindex ON public.discounts USING btree (id);
 '   DROP INDEX public.discounts_id_uindex;
       public            postgres    false    213            �
           1259    16577    passengers_id_uindex    INDEX     P   CREATE UNIQUE INDEX passengers_id_uindex ON public.passengers USING btree (id);
 (   DROP INDEX public.passengers_id_uindex;
       public            postgres    false    203            �
           1259    16690    passengers_login_uindex    INDEX     V   CREATE UNIQUE INDEX passengers_login_uindex ON public.passengers USING btree (login);
 +   DROP INDEX public.passengers_login_uindex;
       public            postgres    false    203            �
           1259    16571    passport_id_uindex    INDEX     L   CREATE UNIQUE INDEX passport_id_uindex ON public.passport USING btree (id);
 &   DROP INDEX public.passport_id_uindex;
       public            postgres    false    217            �
           1259    16662    passport_series_number_uindex    INDEX     c   CREATE UNIQUE INDEX passport_series_number_uindex ON public.passport USING btree (series, number);
 1   DROP INDEX public.passport_series_number_uindex;
       public            postgres    false    217    217            �
           2606    16674    airline  airline_id_country_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.airline
    ADD CONSTRAINT " airline_id_country_fk" FOREIGN KEY (id_country) REFERENCES public.country(id);
 J   ALTER TABLE ONLY public.airline DROP CONSTRAINT " airline_id_country_fk";
       public          postgres    false    219    215    2768            �
           2606    16626    airports airports_id_country_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.airports
    ADD CONSTRAINT airports_id_country_fk FOREIGN KEY (id_country) REFERENCES public.country(id);
 I   ALTER TABLE ONLY public.airports DROP CONSTRAINT airports_id_country_fk;
       public          postgres    false    2766    209    215            �
           2606    16663 !   discounts discounts_id_tickets_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.discounts
    ADD CONSTRAINT discounts_id_tickets_fk FOREIGN KEY (id_tickets) REFERENCES public.tickets(id);
 K   ALTER TABLE ONLY public.discounts DROP CONSTRAINT discounts_id_tickets_fk;
       public          postgres    false    213    211    2762            �
           2606    16639    flights flights_id_airplanes_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.flights
    ADD CONSTRAINT flights_id_airplanes_fk FOREIGN KEY (id_airplanes) REFERENCES public.airplanes(id);
 I   ALTER TABLE ONLY public.flights DROP CONSTRAINT flights_id_airplanes_fk;
       public          postgres    false    205    207    2757            �
           2606    16644    flights flights_id_airport_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.flights
    ADD CONSTRAINT flights_id_airport_fk FOREIGN KEY (id_airport) REFERENCES public.airports(id);
 G   ALTER TABLE ONLY public.flights DROP CONSTRAINT flights_id_airport_fk;
       public          postgres    false    205    209    2760            �
           2606    16616 #   passengers passengers_id_country_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.passengers
    ADD CONSTRAINT passengers_id_country_fk FOREIGN KEY (id_country) REFERENCES public.country(id);
 M   ALTER TABLE ONLY public.passengers DROP CONSTRAINT passengers_id_country_fk;
       public          postgres    false    203    215    2768            �
           2606    16653 #   passengers passengers_id_tickets_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.passengers
    ADD CONSTRAINT passengers_id_tickets_fk FOREIGN KEY (id_tickets) REFERENCES public.tickets(id);
 M   ALTER TABLE ONLY public.passengers DROP CONSTRAINT passengers_id_tickets_fk;
       public          postgres    false    2762    203    211            �
           2606    16591 "   passport passport_id_passengers_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.passport
    ADD CONSTRAINT passport_id_passengers_fk FOREIGN KEY (id_passengers) REFERENCES public.passengers(id);
 L   ALTER TABLE ONLY public.passport DROP CONSTRAINT passport_id_passengers_fk;
       public          postgres    false    2752    203    217            �
           2606    16601    tickets tickets_id_discounts_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_id_discounts_fk FOREIGN KEY (id_discounts) REFERENCES public.discounts(id);
 I   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_id_discounts_fk;
       public          postgres    false    211    213    2765            �
           2606    16606    tickets tickets_id_flights_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_id_flights_fk FOREIGN KEY (id_flights) REFERENCES public.flights(id);
 G   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_id_flights_fk;
       public          postgres    false    211    2754    205            q      x������ � �      e      x������ � �      g      x������ � �      m      x������ � �      k      x������ � �      c      x������ � �      a      x������ � �      o      x������ � �      i      x������ � �     