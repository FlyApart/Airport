
alter table role
    alter column role set default USER;

alter table airline
    alter column status set default 'ACTIVE';

alter table airplanes
    alter column status set default 'ACTIVE';

alter table airports
    alter column status set default 'ACTIVE';

alter table flights
    alter column status set default 'ACTIVE';

alter table passenger
    alter column status set default 'ACTIVE';

alter table passports
    alter column status set default 'ACTIVE';

alter table cities
    alter column status set default 'ACTIVE';

alter table countries
    alter column status set default 'ACTIVE';

alter table discounts
    alter column status set default 'ACTIVE';
