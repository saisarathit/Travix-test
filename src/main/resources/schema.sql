
DROP TABLE IF EXISTS CRAZY_AIR cascade;
CREATE TABLE CRAZY_AIR (
    airline_id NUMBER(10) PRIMARY KEY, airlines_name VARCHAR2(20), price  NUMBER(10,2), departure_airport_code VARCHAR2(4),
    destination_airport_code VARCHAR2(4), departure_date DATETIME, arrival_date DATETIME
 );
DROP TABLE IF EXISTS TOUGH_JET cascade;
CREATE TABLE TOUGH_JET (
    airline_id NUMBER(10) PRIMARY KEY, airlines_name VARCHAR2(20), price  NUMBER(10,2), departure_airport_code VARCHAR2(4),
     destination_airport_code VARCHAR2(4), departure_date DATETIME, arrival_date DATETIME
);