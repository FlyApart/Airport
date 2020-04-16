insert into countries
    (name, population)
values ('Russia', 146745098),
       ('Belarus', 9491800),
       ('England', 55977178);

insert into cities
( name, countries_id, latitude, longitude)
values ( 'Moscow', 1, 152.0157, 155.2073),
       ( 'Minsk', 2, 34.1321, 54.1344),
       ('London', 3, 67.3332, 78.3233);

insert into airline
    (fleet, flights_per_year, name, website, countries_id)
values (180, 18000, 'Ural Airline', 'ural.ru', 1),
       (50, 9000, 'Belavia', 'belavia.com', 2),
       (300, 19000, 'EN', 'en.com', 3);


insert into airplanes
(built, max_flight_duration, model, seats, countries_id,number_of_row,comfort_seats,comfort_number_of_row,business_seats,business_number_of_row)
values ('2007-04-27', 15400, 'Airbus A380', 400, 3, 10,100,5,36,3),
       ('1995-06-07', 17500, 'Boeing 777', 440, 3, 8,80,5,30,3),
       ('2011-04-21', 4600, 'Superjet 100', 88, 1, 6,9,3,null,null);

insert into airports
(title, cities_id)
values ('Kolchovo', 1),
       ('Minsk International Airport', 2),
       ('Heathrow', 3);


insert into discounts
(cost, title)
values (3500, 'PROMO_35'),
       (4500, 'PROMO_45'),
       (7000, 'PROMO_777');


insert into passenger
(date_birth, changed, created, login, name, password, surname, cities_id, status)
values ('13-05-1997', null, '30-03-2020', 'oglorn@mail.ru', 'Alex', '$2y$12$KxfPSQWNH/5rDZSCSDy5AeDB65WK18klM7UpOF7qPBhk.upVaQK8G', 'Zuev', '1','ACTIVE'),
       ('21-12-1996', null, '20-02-2020', 'dunya@mail.ru', 'Dima', '$2y$12$KxfPSQWNH/5rDZSCSDy5AeDB65WK18klM7UpOF7qPBhk.upVaQK8G', 'Dunya', '2','ACTIVE'),
       ('21-12-1996', null, '20-02-2020', 'dunya2@mail.ru', 'Dima', '$2y$12$2XQv6j8KGC4RyM9aMccjKeUPvOjzXFsdiSYk8PnS78lUPIKmBWCuC', 'Dunya', '2','ACTIVE'),
       ('21-12-1996', null, '20-02-2020', 'dunya3@mail.ru', 'Dima', '$2y$12$KxfPSQWNH/5rDZSCSDy5AeDB65WK18klM7UpOF7qPBhk.upVaQK8G', 'Dunya', '2','ACTIVE'),
       ('21-12-1996', null, '20-02-2020', 'dunya4@mail.ru', 'Dima', '$2y$12$2XQv6j8KGC4RyM9aMccjKeUPvOjzXFsdiSYk8PnS78lUPIKmBWCuC', 'Dunya', '2','ACTIVE'),
       ('06-04-1971', null, '17-01-2020', 'lelya@qmail.com', 'Olga', '$2y$12$2XQv6j8KGC4RyM9aMccjKeUPvOjzXFsdiSYk8PnS78lUPIKmBWCuC', 'Zinevich', '1','ACTIVE'),
       ('06-04-1971', null, '17-01-2020', 'lel2ya@qmail.com', 'Olga', '$2y$12$KxfPSQWNH/5rDZSCSDy5AeDB65WK18klM7UpOF7qPBhk.upVaQK8G', 'Zinevich', '1','ACTIVE'),
       ('06-04-1971', null, '17-01-2020', 'lel3ya@qmail.com', 'Olga', '$2y$12$2XQv6j8KGC4RyM9aMccjKeUPvOjzXFsdiSYk8PnS78lUPIKmBWCuC', 'Zinevich', '1','ACTIVE');

insert into passports
(number, series, types, passenger_id)
values (1234, 1234, 'CITIZEN', 1),
       (4321, 4321, 'FOREIGN', 1),
       (22221, 2222, 'CITIZEN', 2),
       (22222, 2222, 'CITIZEN', 4),
       (22223, 2222, 'CITIZEN', 5),
       (22224, 2222, 'CITIZEN', 6),
       (33335, 3333, 'DIPLOMATIC', 3);

insert into flights
(arrive_date, changed, departure_date, flights_number, price, airline_id, airplane_id, arrive_airport_id, departure_airport_id)
values ('2020-11-11 15:10:00', null, '2020-11-11 12:20:00', 'AOPRR133', 24000, 1, 2, 1, 3),
       ('2020-12-06 05:10:00', null, '2020-12-05 23:20:00', 'DHUE908', 20000, 2, 1, 3, 2);

insert into tickets
(place, reservation, total_price, flights_id, passenger_id,class)
values ('1A', TRUE, 22000, 1, 1,'NORMAL'),
       ('43D', FALSE, 22000, 1,2,'COMFORT'),
       ('66C', TRUE, 22000, 1,3,'BUSINESS'),
       ('6D', TRUE, 22000, 1,4,'NORMAL'),
       ('8C', TRUE, 22000, 1,5,'NORMAL'),
       ('7E', TRUE, 22000, 1,6,'NORMAL'),
       ('22C', FALSE, 17000, 2,1,'NORMAL'),
       ('50A', TRUE, 17000, 2,3,'COMFORT');

insert into flights_discounts (flights_id, discounts_id)
values (1,1),
       (1,2),
       (1,3),
       (2,1),
       (2,2);

insert into role (role)
 values ('ADMIN'),
        ('MODER'),
        ('USER');

insert into passengers_roles
    (role_id,passenger_id)
values (1,1),
       (1,2),
       (1,3),
       (2,1),
       (2,2);