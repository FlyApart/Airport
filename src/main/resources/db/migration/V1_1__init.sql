create table airlines
(
    id               bigserial   NOT NULL,
    fleet            int8,
    flights_per_year int8,
    name             varchar(50) NOT NULL,
    website          varchar(50) NOT NULL,
    countries_id       int8,
    primary key (id)
);

create unique index airline_id_uindex
    on airlines (id);

create unique index airline_name_site_uindex
    on airlines USING btree (name, website);

create table cities
(
    id                  bigserial   NOT NULL,
    name                varchar(50) NOT NULL,
    countries_id         int8 NOT NULL ,
    primary key (id)
);

create unique index cities_id_uindex
    on cities (id);


create table airplanes
(
    id                  bigserial   NOT NULL,
    built               timestamp,
    max_flight_duration int8,
    model               varchar(50) NOT NULL,
    seats               int8,
    countries_id          int8,
    primary key (id)
);

create unique index airplanes_id_uindex
    on airplanes (id);
create unique index airplanes_model_uindex
    on airplanes (model);

create table airports
(
    id         bigserial   NOT NULL,
    title      varchar(75) NOT NULL,
    cities_id int8,
    primary key (id)
);

create unique index airports_title_uindex
    on airports (title);

create unique index airports_id_uindex
    on airports (id);

create table countries
(
    id         bigserial   NOT NULL,
    location   varchar(50),
    name       varchar(50) NOT NULL,
    population int8,
    primary key (id)
);

create unique index countries_id_uindex
    on countries (id);

create unique index countries_name_uindex
    on countries (name);

create table discounts
(
    id    bigserial   NOT NULL,
    cost  float8      NOT NULL,
    title varchar(50) NOT NULL,
    primary key (id)
);

create unique index discounts_id_uindex
    on discounts (id);

create unique index discounts_title_uindex
    on discounts (title);

create table flights
(
    id                   bigserial   NOT NULL,
    arrive_date          timestamp   NOT NULL,
    changed              timestamp,
    departure_date       timestamp   NOT NULL,
    flights_number       varchar(50) NOT NULL,
    price                float8      NOT NULL,
    airline_id           int8        NOT NULL,
    airplane_id          int8        NOT NULL,
    arrive_airport_id    int8        NOT NULL,
    departure_airport_id int8        NOT NULL,
    primary key (id)
);

create unique index flights_flight_number_uindex
    on flights (flights_number);
create unique index flights_id_uindex
    on flights (id);

create table flights_discounts
(
    flights_id   int8 NOT NULL,
    discounts_id int8 NOT NULL,
    primary key (discounts_id, flights_id)
);

create unique index flights_discounts_uindex
    on flights_discounts USING btree (flights_id, discounts_id);

create table passengers
(
    id         bigserial   NOT NULL,
    date_birth timestamp,
    changed    timestamp,
    created    timestamp,
    login      varchar(50) NOT NULL,
    name       varchar(50) NOT NULL,
    password   varchar(255) NOT NULL,
    surname    varchar(50) NOT NULL,
    cities_id int8,
    primary key (id)
);

create unique index passengers_login_uindex
    on passengers (login);

create unique index passengers_id_uindex
    on passengers (id);


create table passports
(
    id            bigserial NOT NULL,
    number        int8  NOT NULL,
    series        int8  NOT NULL,
    types         varchar(50) NOT NULL,
    passengers_id int8 NOT NULL,
    primary key (id)
);

create unique index passports_id_uindex
    on passports (id);

create unique index passports_serial_number_uindex
    on passports
        using btree (number, series);

create unique index passports_unique_types_uindex
    on passports
        using btree (passengers_id, types);



create table tickets
(
    id          bigserial NOT NULL,
    place       varchar(10) NOT NULL,
    reservation boolean,
    total_price float8 NOT NULL,
    flights_id  int8 not null,
    passengers_id int8 NOT NULL,
    primary key (id)
);

create unique index tickets_id_uindex
    on tickets (id);

create unique index tickets_place_flight_uindex
    on tickets USING btree (flights_id,place);

create unique index tickets_flight_id_passenger_id_uindex
    on tickets USING btree (flights_id,passengers_id);



/*TODO add role*/
create table role
(
    id            bigserial NOT NULL,
    role          varchar(255),
    passengers_id int8,
    primary key (id)
);

create unique index role_id_uindex
    on role (id);

/*alter table TEST
    add constraint TEST_pk
        primary key (id);
*/

alter table if exists airlines
    add constraint airline_countries_fk foreign key (countries_id) references countries
        on update cascade on delete cascade;

alter table if exists airplanes
    add constraint airplanes_countries_fk foreign key (countries_id) references countries
        on update cascade on delete cascade;

alter table if exists airports
    add constraint airports_cities_fk foreign key (cities_id) references cities
        on update cascade on delete cascade;

alter table if exists flights
    add constraint flights_airline_fk foreign key (airline_id) references airlines
        on update cascade on delete cascade;

alter table if exists flights
    add constraint flights_airplanes_fk foreign key (airplane_id) references airplanes
        on update cascade on delete cascade;

alter table if exists flights
    add constraint flights_arrive_airport_fk foreign key (arrive_airport_id) references airports
        on update cascade on delete cascade;

alter table if exists flights
    add constraint flights_departure_airport_fk foreign key (departure_airport_id) references airports
        on update cascade on delete cascade;

alter table if exists flights_discounts
    add constraint flights_discounts_discount_fk foreign key (discounts_id) references discounts
        on update cascade on delete cascade;

alter table if exists flights_discounts
    add constraint flights_discounts_flights_fk foreign key (flights_id) references flights
        on update cascade on delete cascade;

alter table if exists passengers
    add constraint passengers_cities_fk foreign key (cities_id) references cities
        on update cascade on delete cascade;

alter table if exists passports
    add constraint passport_passengers_fk foreign key (passengers_id) references passengers
        on update cascade on delete cascade;

alter table if exists role
    add constraint role_passengers_fk foreign key (passengers_id) references passengers
        on update cascade on delete cascade;

alter table if exists tickets
    add constraint tickets_flights_fk foreign key (flights_id) references flights
        on update cascade on delete cascade;

alter table if exists tickets
    add constraint tickets_passengers_fk foreign key (passengers_id) references passengers
        on update cascade on delete cascade;

alter table if exists cities
    add constraint cities_country_fk foreign key (countries_id) references countries
        on update cascade on delete cascade


/*create table airline
(
    id               int8 generated by default as identity,
    fleet            int8,
    flights_per_year int8,
    name             varchar(255),
    website          varchar(255),
    countries_id       int8,
    primary key (id)
);
create table airplanes
(
    id                  int8 generated by default as identity,
    built               timestamp,
    max_flight_duration time,
    model               varchar(255),
    seats               int8,
    countries_id          int8,
    primary key (id)
);
create table airports
(
    id         int8 generated by default as identity,
    title      varchar(255),
    countries_id int8,
    primary key (id)
);
create table countries
(
    id         int8 generated by default as identity,
    location   varchar(255),
    name       varchar(255),
    population int8,
    primary key (id)
);
create table discounts
(
    id    int8 generated by default as identity,
    cost  float8,
    title varchar(255),
    primary key (id)
);
create table flights
(
    id                   int8 generated by default as identity,
    arrive_date          timestamp,
    changed              timestamp,
    departure_date       timestamp,
    flights_number       varchar(255),
    price                float8,
    airline_id           int8,
    airplane_id          int8,
    arrive_airport_id    int8,
    departure_airport_id int8,
    primary key (id)
);
create table flights_discounts
(
    flights_id   int8  NOT NULL,
    discounts_id int8  NOT NULL,
    primary key (discounts_id, flights_id)
);
create table passengers
(
    id         int8  generated by default as identity,
    date_birth timestamp,
    changed    timestamp,
    created    timestamp,
    login      varchar(255),
    name       varchar(255),
    password   varchar(255),
    surname    varchar(255),
    countries_id int8,
    primary key (id)


);
create table passengers_ticket
(
    ticket_id    int8  NOT NULL,
    passenger_id int8  NOT NULL,
    primary key (passenger_id, ticket_id)
);
create table passports
(
    id            int8 generated by default as identity,
    number        int8,
    series        int8,
    title         varchar(255),
    passengers_id int8,
    primary key (id)
);
create table role
(
    id            int8 generated by default as identity,
    role          varchar(255),
    passengers_id int8,
    primary key (id)
);
create table tickets
(
    id          bigserial  NOT NULL,
    place       varchar(255),
    reservation boolean,
    total_price float8,
    flights_id  int8,
    primary key (id)
);


create table TEST
(
    id bigserial  NOT NULL
);

create unique index TEST_id_uindex
    on TEST (id);

alter table TEST
    add constraint TEST_pk
        primary key (id);




alter table if exists passengers
    add constraint unique_passenger_login unique (login);

alter table if exists airline
    add constraint airline_countries_fk foreign key (countries_id) references countries
        on update cascade on delete cascade;

alter table if exists airplanes
    add constraint airplanes_countries_fk foreign key (countries_id) references countries
        on update cascade on delete cascade;

alter table if exists airports
    add constraint airports_countries_fk foreign key (countries_id) references countries
        on update cascade on delete cascade;

alter table if exists flights
    add constraint flights_airline_fk foreign key (airline_id) references airline
        on update cascade on delete cascade;

alter table if exists flights
    add constraint flights_airplanes_fk foreign key (airplane_id) references airplanes
        on update cascade on delete cascade;

alter table if exists flights
    add constraint flights_arrive_airport_fk foreign key (arrive_airport_id) references airports
        on update cascade on delete cascade;

alter table if exists flights
    add constraint flights_departure_airport_fk foreign key (departure_airport_id) references airports
        on update cascade on delete cascade;

alter table if exists flights_discounts
    add constraint flights_discounts_discount_fk foreign key (discounts_id) references discounts
        on update cascade on delete cascade;

alter table if exists flights_discounts
    add constraint flights_discounts_flights_fk foreign key (flights_id) references flights
        on update cascade on delete cascade;

alter table if exists passengers
    add constraint passengers_countries_fk foreign key (countries_id) references countries
        on update cascade on delete cascade;

alter table if exists passengers_ticket
    add constraint passengers_ticket_passenger_fk foreign key (passenger_id) references passengers
        on update cascade on delete cascade;

alter table if exists passengers_ticket
    add constraint passengers_ticket_tickets_fk foreign key (ticket_id) references tickets
        on update cascade on delete cascade;

alter table if exists passports
    add constraint passport_passengers_fk foreign key (passengers_id) references passengers
        on update cascade on delete cascade;

alter table if exists role
    add constraint role_passengers_fk foreign key (passengers_id) references passengers
        on update cascade on delete cascade;

alter table if exists tickets
    add constraint tickets_flights_fk foreign key (flights_id) references flights
        on update cascade on delete cascade*/