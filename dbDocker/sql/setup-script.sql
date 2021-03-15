CREATE DATABASE customersdb;
CREATE ROLE customers_service WITH
    LOGIN
    NOSUPERUSER
    INHERIT
    NOCREATEDB
    NOCREATEROLE
    NOREPLICATION
    PASSWORD 'password';

CREATE DATABASE productsdb;
CREATE ROLE products_service WITH
    LOGIN
    NOSUPERUSER
    INHERIT
    NOCREATEDB
    NOCREATEROLE
    NOREPLICATION
    PASSWORD 'password';

CREATE DATABASE creditsdb;
CREATE ROLE credits_service WITH
    LOGIN
    NOSUPERUSER
    INHERIT
    NOCREATEDB
    NOCREATEROLE
    NOREPLICATION
    ENCRYPTED PASSWORD 'password';

\c customersdb;

CREATE TABLE "Customers"
(
    "ID"        serial      not null constraint "Customers_pkey" primary key,
    "CreditID"  integer     not null,
    "Pesel"     varchar(11) not null,
    "FirstName" varchar(40),
    "Surname"   varchar(40)
);

GRANT ALL ON SEQUENCE public."Customers_ID_seq" TO customers_service;
GRANT DELETE, INSERT, SELECT, UPDATE ON TABLE public."Customers" TO customers_service;

\c productsdb;

create table "Products"
(
    "ID"       serial  not null constraint "Products_pkey" primary key,
    "CreditID" integer not null,
    "Value"    integer not null
);

GRANT ALL ON SEQUENCE public."Products_ID_seq" TO products_service;
GRANT DELETE, INSERT, SELECT, UPDATE ON TABLE public."Products" TO products_service;

\c creditsdb;

create table "Credits"
(
    "ID"    integer  not null constraint "Credits_pkey" primary key,
    "CreditName" varchar not null
);

create sequence credits_id_seq start 1 increment 1;

GRANT ALL ON SEQUENCE credits_id_seq TO credits_service;
GRANT DELETE, INSERT, SELECT, UPDATE ON TABLE public."Credits" TO credits_service;