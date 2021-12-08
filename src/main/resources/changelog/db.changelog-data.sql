--liquibase formatted sql

-- changeset drymlj:1
-- comment: data 1 - insert Admin user
INSERT INTO users (username, email, role, password, active)
VALUES ('admin', 'admin@test.cz', 'ROLE_ADMIN', '$2a$10$qw1xEK9CrHizlecUs8r/zedWzOL4Q3f5Tpm3SwmbCX0W9Ha9LAZHm', true);
-- encrypted 'password'

-- changeset drymlj:2
-- comment: data 2 - insert regions
INSERT INTO region
    (code, name)
VALUES ('PHA', 'Hlavní město Praha'),
       ('JHC', 'Jihočeský kraj'),
       ('JHM', 'Jihomoravský kraj'),
       ('KVK', 'Karlovarský kraj'),
       ('VYS', 'Kraj Vysočina'),
       ('HKK', 'Královéhradecký kraj'),
       ('LBK', 'Liberecký kraj'),
       ('MSK', 'Moravskoslezský kraj'),
       ('OLK', 'Olomoucký kraj'),
       ('PAK', 'Pardubický kraj'),
       ('PLK', 'Plzeňský kraj'),
       ('STC', 'Středočeský kraj'),
       ('ULK', 'Ústecký kraj'),
       ('ZLK', 'Zlínský kraj');

-- changeset drymlj:3
-- comment: data 3 - insert stations
INSERT INTO station (code, name, x, y, region_id)
VALUES ('B001', 'Brno', 49.190605, 16.613098, 8);
INSERT INTO station (code, name, x, y, region_id)
VALUES ('B002', 'Blansko', 49.361462, 16.638968, 8);
INSERT INTO station (code, name, x, y, region_id)
VALUES ('C001', 'Česká Třebová', 49.90686, 16.44269, 10);
INSERT INTO station (code, name, x, y, region_id)
VALUES ('C002', 'Český Brod', 50.068668, 14.856504, 12);
INSERT INTO station (code, name, x, y, region_id)
VALUES ('P001', 'Praha', 50.08351, 14.436218, 1);
INSERT INTO station (code, name, x, y, region_id)
VALUES ('P002', 'Poděbrady', 50.14933, 15.12448, 12);
INSERT INTO station (code, name, x, y, region_id)
VALUES ('H001', 'Hradec Králové', 50.214925, 15.80955, 6);
INSERT INTO station (code, name, x, y, region_id)
VALUES ('P003', 'Pardubice', 50.03167, 15.756488, 10);
INSERT INTO station (code, name, x, y, region_id)
VALUES ('P004', 'Přelouc', 50.03996, 15.573799, 10);
INSERT INTO station (code, name, x, y, region_id)
VALUES ('K001', 'Kolín', 50.025654, 15.213913, 12);
INSERT INTO station (code, name, x, y, region_id)
VALUES ('K002', 'Kutná Hora', 49.962242, 15.300431, 12);
INSERT INTO station (code, name, x, y, region_id)
VALUES ('Z001', 'Zbraslavice', 49.81559, 15.189128, 12);
INSERT INTO station (code, name, x, y, region_id)
VALUES ('Z002', 'Zruč nad Sázavou', 49.738708, 15.104088, 12);

-- changeset drymlj:4
-- comment: data 4 - insert rails
INSERT INTO rail (code, enabled, name, source_station_id, target_station_id)
VALUES ('T001', true, 'Zruč - Zbraslavice', 13, 12);
INSERT INTO rail (code, enabled, name, source_station_id, target_station_id)
VALUES ('T002', true, 'Kutná Hora - Zbraslavice', 11, 12);
INSERT INTO rail (code, enabled, name, source_station_id, target_station_id)
VALUES ('T003', true, 'Kolín - Kutná Hora', 10, 11);
INSERT INTO rail (code, enabled, name, source_station_id, target_station_id)
VALUES ('T004', true, 'Kolín - Přelouč', 10, 9);
INSERT INTO rail (code, enabled, name, source_station_id, target_station_id)
VALUES ('T005', true, 'Kolín - Poděbrady', 10, 6);
INSERT INTO rail (code, enabled, name, source_station_id, target_station_id)
VALUES ('T006', true, 'Kolín - Český Brod', 10, 4);
INSERT INTO rail (code, enabled, name, source_station_id, target_station_id)
VALUES ('T007', true, 'Pardubice - Přelouc ', 8, 9);
INSERT INTO rail (code, enabled, name, source_station_id, target_station_id)
VALUES ('T008', true, 'Pardubice - Hradec Králové', 8, 7);
INSERT INTO rail (code, enabled, name, source_station_id, target_station_id)
VALUES ('T009', true, 'Pardubice - Česká Třebová', 8, 3);
INSERT INTO rail (code, enabled, name, source_station_id, target_station_id)
VALUES ('T010', true, 'Praha - Český Brod', 5, 4);
INSERT INTO rail (code, enabled, name, source_station_id, target_station_id)
VALUES ('T011', true, 'Česká Třebová - Blansko', 3, 2);
INSERT INTO rail (code, enabled, name, source_station_id, target_station_id)
VALUES ('T012', true, 'Blansko - Brno', 2, 1);

-- changeset drymlj:5
-- comment: data 5 - second phase data
insert into transport_company (code, name, cin)
values ('A51', 'Flixbus', '5484415');
insert into transport_company (code, name, cin)
values ('B59', 'ČSAD', '9964487');

insert into vehicle (name, capacity, color, year, actuation, company_id)
values ('Bus Standard B1', 45, 'Cerna', 1985, 'Nafta', 1);
insert into vehicle (name, capacity, color, year, actuation, company_id)
values ('Bus Standard B1', 45, 'Zelena', 1955, 'Nafta', 1);
insert into vehicle (name, capacity, color, year, actuation, company_id)
values ('Bus Standard B1', 45, 'Bila', 2005, 'Nafta', 2);
insert into vehicle (name, capacity, color, year, actuation, company_id)
values ('Jumbo-Bus J4', 75, 'Bila', 2010, 'Elektro', 1);
insert into vehicle (name, capacity, color, year, actuation, company_id)
values ('Mini-Bus A1', 25, 'Bila', 1944, 'Benzin', 2);
insert into vehicle (name, capacity, color, year, actuation, company_id)
values ('Bus Standard B3', 35, 'Modra', 1921, 'Benzin', 1);

insert into chauffeur (firstname, lastname, driving_license, driving_experience, phone_number, vehicle_id)
values ('Franta', 'Novak', 'A1, B, D', '5 let', '+420 123 456 789', 1);
insert into chauffeur (firstname, lastname, driving_license, driving_experience, phone_number, vehicle_id)
values ('Petr', 'Svoboda', 'AM, D, DE', '13 let', '+420 987 654 321', 2);
insert into chauffeur (firstname, lastname, driving_license, driving_experience, phone_number, vehicle_id)
values ('Milan', 'Dvořák', 'BE, DE', '28 let', '+420 654 987 321', 3);
insert into chauffeur (firstname, lastname, driving_license, driving_experience, phone_number, vehicle_id)
values ('Lukáš', 'Černý', 'A, B, D', '4 let', '+420 789 456 123', 4);
insert into chauffeur (firstname, lastname, driving_license, driving_experience, phone_number, vehicle_id)
values ('Martin', 'Krejčí', 'D1, D1E', '17 let', '+420 321 654 987', 5);
insert into chauffeur (firstname, lastname, driving_license, driving_experience, phone_number, vehicle_id)
values ('Petr', 'Veselý', 'D1, C', '9 let', '+420 456 789 123', 6);

-- changeset drymlj:6
-- comment: data 6 - train route
INSERT INTO train_route (train_code, closure, capacity)
VALUES ('Praha - Pardubice', false, 79);
INSERT INTO train_route (train_code, closure, capacity)
VALUES ('Zruč - Pardubice', false, 65);
INSERT INTO train_route (train_code, closure, capacity)
VALUES ('Praha - Brno', false, 120);

INSERT INTO public.train_route_section (route_order, station_id, train_route_id)
VALUES (0, 5, 1);
INSERT INTO public.train_route_section (route_order, station_id, train_route_id)
VALUES (1, 4, 1);
INSERT INTO public.train_route_section (route_order, station_id, train_route_id)
VALUES (2, 10, 1);
INSERT INTO public.train_route_section (route_order, station_id, train_route_id)
VALUES (3, 9, 1);
INSERT INTO public.train_route_section (route_order, station_id, train_route_id)
VALUES (4, 8, 1);
INSERT INTO public.train_route_section (route_order, station_id, train_route_id)
VALUES (0, 13, 2);
INSERT INTO public.train_route_section (route_order, station_id, train_route_id)
VALUES (1, 12, 2);
INSERT INTO public.train_route_section (route_order, station_id, train_route_id)
VALUES (2, 11, 2);
INSERT INTO public.train_route_section (route_order, station_id, train_route_id)
VALUES (3, 10, 2);
INSERT INTO public.train_route_section (route_order, station_id, train_route_id)
VALUES (4, 9, 2);
INSERT INTO public.train_route_section (route_order, station_id, train_route_id)
VALUES (5, 8, 2);
INSERT INTO public.train_route_section (route_order, station_id, train_route_id)
VALUES (0, 5, 3);
INSERT INTO public.train_route_section (route_order, station_id, train_route_id)
VALUES (1, 4, 3);
INSERT INTO public.train_route_section (route_order, station_id, train_route_id)
VALUES (2, 10, 3);
INSERT INTO public.train_route_section (route_order, station_id, train_route_id)
VALUES (3, 9, 3);
INSERT INTO public.train_route_section (route_order, station_id, train_route_id)
VALUES (4, 8, 3);
INSERT INTO public.train_route_section (route_order, station_id, train_route_id)
VALUES (5, 3, 3);
INSERT INTO public.train_route_section (route_order, station_id, train_route_id)
VALUES (6, 2, 3);
INSERT INTO public.train_route_section (route_order, station_id, train_route_id)
VALUES (7, 1, 3);
