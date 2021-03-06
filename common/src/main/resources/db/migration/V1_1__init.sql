create table airline
(
    id               bigserial   NOT NULL,
    fleet            int8,
    flights_per_year int8,
    name             varchar(50) NOT NULL,
    website          varchar(50) NOT NULL,
    countries_id     int8,
    status           varchar(50) NOT NULL,
    primary key (id)
);

create unique index airline_id_uindex
    on airline (id);

create unique index airline_name_site_uindex
    on airline USING btree (name, website);

create table cities
(
    id           bigserial   NOT NULL,
    longitude    float4      NOT NULL,
    latitude     float4      NOT NULL,
    name         varchar(50) NOT NULL,
    countries_id int8        NOT NULL,
    status       varchar(50) NOT NULL,
    primary key (id)
);

create unique index cities_id_uindex
    on cities (id);


create table airplanes
(
    id                     bigserial   NOT NULL,
    built                  timestamp,
    max_flight_duration    int8,
    model                  varchar(50) NOT NULL,
    seats                  int4        NOT NULL,
    number_of_row          int4        NOT NULL,
    business_seats         int4,
    business_number_of_row int4,
    comfort_seats          int4,
    comfort_number_of_row  int4,
    countries_id           int8,
    status                 varchar(50) NOT NULL,
    primary key (id)
);

create unique index airplanes_id_uindex
    on airplanes (id);
create unique index airplanes_model_uindex
    on airplanes (model);

create table airports
(
    id        bigserial   NOT NULL,
    title     varchar(75) NOT NULL,
    cities_id int8,
    status    varchar(50) NOT NULL,
    primary key (id)
);

create unique index airports_title_uindex
    on airports (title);

create unique index airports_id_uindex
    on airports (id);

create table countries
(
    id         bigserial   NOT NULL,
    name       varchar(50) NOT NULL,
    population int8,
    status     varchar(50) NOT NULL,
    primary key (id)
);

create unique index countries_id_uindex
    on countries (id);

create unique index countries_name_uindex
    on countries (name);

create table discounts
(
    id     bigserial   NOT NULL,
    cost   float8      NOT NULL,
    title  varchar(50) NOT NULL,
    status varchar(50) NOT NULL,
    primary key (id)
);

create unique index discounts_id_uindex
    on discounts (id);

create unique index discounts_title_uindex
    on discounts (title);

create table flights
(
    id                   bigserial   NOT NULL,
    arrive_date          date        NOT NULL,
    arrive_time          time        NOT NULL,
    changed              timestamp,
    departure_date       date         NOT NULL,
    departure_time       time        NOT NULL,
    flights_number       varchar(50) NOT NULL,
    price                float8      NOT NULL,
    airline_id           int8        NOT NULL,
    airplane_id          int8        NOT NULL,
    arrive_airport_id    int8        NOT NULL,
    departure_airport_id int8        NOT NULL,
    status               varchar(50) NOT NULL,
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

create table passenger
(
    id              bigserial    NOT NULL,
    date_birth      timestamp,
    changed         timestamp,
    created         timestamp,
    login           varchar(50)  NOT NULL,
    name            varchar(50)  NOT NULL,
    password        varchar(255) NOT NULL,
    surname         varchar(50)  NOT NULL,
    cities_id       int8,
    status          varchar(50)  NOT NULL,
    code_activation varchar(50),
    primary key (id)
);

create unique index passengers_login_uindex
    on passenger (login);

create unique index passengers_id_uindex
    on passenger (id);


create table passports
(
    id           bigserial   NOT NULL,
    number       int8        NOT NULL,
    series       int8        NOT NULL,
    types        varchar(50) NOT NULL,
    passenger_id int8        NOT NULL,
    status       varchar(50) NOT NULL,
    primary key (id)
);

create unique index passports_id_uindex
    on passports (id);

create unique index passports_serial_number_uindex
    on passports
        using btree (number, series);

create unique index passports_unique_types_uindex
    on passports
        using btree (passenger_id, types);



create table tickets
(
    id              bigserial   NOT NULL,
    place           varchar(10) NOT NULL,
    reservation     boolean,
    total_price     float8      NOT NULL,
    flights_id      int8        not null,
    passenger_id    int8        NOT NULL,
    class           varchar(50) NOT NULL,
    code_activation varchar(50),
    primary key (id)
);

create unique index tickets_id_uindex
    on tickets (id);

create unique index tickets_place_flight_uindex
    on tickets USING btree (flights_id, place);

create unique index tickets_flight_id_passenger_id_uindex
    on tickets USING btree (flights_id, passenger_id);


create table role
(
    id   bigserial   NOT NULL,
    role varchar(50) NOT NULL,
    primary key (id)
);

create unique index role_id_uindex
    on role (id);

create table passengers_roles
(
    role_id      int8 NOT NULL,
    passenger_id int8 NOT NULL,
    primary key (role_id, passenger_id)
);

create unique index passengers_roles_uindex
    on passengers_roles USING btree (role_id, passenger_id);


alter table if exists airline
    add constraint airline_countries_fk foreign key (countries_id) references countries
        on update cascade on delete cascade;

alter table if exists airplanes
    add constraint airplanes_countries_fk foreign key (countries_id) references countries
        on update cascade on delete cascade;

alter table if exists airports
    add constraint airports_cities_fk foreign key (cities_id) references cities
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

alter table if exists passenger
    add constraint passengers_cities_fk foreign key (cities_id) references cities
        on update cascade on delete cascade;

alter table if exists passports
    add constraint passport_passengers_fk foreign key (passenger_id) references passenger
        on update cascade on delete cascade;

alter table if exists tickets
    add constraint tickets_flights_fk foreign key (flights_id) references flights
        on update cascade on delete cascade;

alter table if exists tickets
    add constraint tickets_passengers_fk foreign key (passenger_id) references passenger
        on update cascade on delete cascade;

alter table if exists cities
    add constraint cities_country_fk foreign key (countries_id) references countries
        on update cascade on delete cascade;

alter table if exists passengers_roles
    add constraint passengers_roles_role_fk foreign key (role_id) references role
        on update cascade on delete cascade;

alter table if exists passengers_roles
    add constraint passengers_roles_passenger_fk foreign key (passenger_id) references passenger
        on update cascade on delete cascade;