--liquibase formatted sql
--changeset vitalina:1

CREATE TABLE "manager"
(
    "id"    serial PRIMARY KEY,
    "fio"   text UNIQUE NOT NULL,
    "phone" text        NOT NULL
);

--changeset vitalina:2

CREATE TABLE "status_order"
(
    "id"          smallserial PRIMARY KEY,
    "name"        text UNIQUE NOT NULL,
    "description" text        NOT NULL
);

--changeset vitalina:3

CREATE TABLE "client"
(
    "id"                    serial PRIMARY KEY,
    "phone_number"          text        NOT NULL,
    "email"                 text        NOT NULL,
    "fio"                   text UNIQUE NOT NULL,
    "passport_number"       text UNIQUE NOT NULL,
    "identification_number" text UNIQUE NOT NULL,
    "date_of_issue"         date        NOT NULL,
    "validity_period"       int         NOT NULL,
    "issued_by_whom"        text        NOT NULL,
    "residence_address"     text        NOT NULL
);

--changeset vitalina:4

CREATE TABLE "driving_license"
(
    "id"              serial PRIMARY KEY,
    "number"          text UNIQUE NOT NULL,
    "category"        text        NOT NULL,
    "date_of_issue"   date        NOT NULL,
    "validity_period" int         NOT NULL,
    "client_id"       serial,

    FOREIGN KEY ("client_id") references "client" ("id") ON DELETE CASCADE
);

--changeset vitalina:5

CREATE TABLE "technical_inspection"
(
    "id"              serial PRIMARY KEY,
    "number"          text UNIQUE NOT NULL,
    "date_of_issue"   date        NOT NULL,
    "validity_period" int         NOT NULL
);

--changeset vitalina:6

CREATE TABLE "insurance_policy"
(
    "id"              serial PRIMARY KEY,
    "number"          text UNIQUE NOT NULL,
    "date_of_issue"   date        NOT NULL,
    "validity_period" int         NOT NULL
);

--changeset vitalina:7

CREATE TABLE "insurance_claim"
(
    "id"                  serial PRIMARY KEY,
    "name"                text      NOT NULL,
    "description"         text      NOT NULL,
    "date_time"           timestamp NOT NULL,
    "insurance_policy_id" serial,

    FOREIGN KEY ("insurance_policy_id") references "insurance_policy" ("id") ON DELETE CASCADE
);

--changeset vitalina:8

CREATE TABLE "status_car"
(
    "id"          smallserial PRIMARY KEY,
    "name"        text UNIQUE NOT NULL,
    "description" text        NOT NULL
);

--changeset vitalina:9

CREATE TABLE "car_group"
(
    "id"          smallserial PRIMARY KEY,
    "name"        text UNIQUE NOT NULL,
    "description" text        NOT NULL
);

--changeset vitalina:10

CREATE TABLE "car_brand"
(
    "id"          smallserial PRIMARY KEY,
    "name"        text UNIQUE NOT NULL,
    "description" text        NOT NULL
);

--changeset vitalina:11

CREATE TABLE "car"
(
    "id"                      serial PRIMARY KEY,
    "number"                  text UNIQUE NOT NULL,
    "name"                    text        NOT NULL,
    "win_number"              text UNIQUE NOT NULL,
    "year_of_release"         int         NOT NULL,
    "cost_minute"             decimal     NOT NULL,
    "is_available"            boolean     NOT NULL,
    "car_group_id"            smallserial,
    "car_brand_id"            smallserial,
    "status_car_id"           smallserial,
    "technical_inspection_id" serial,
    "insurance_policy_id"     serial,

    FOREIGN KEY ("car_group_id") references "car_group" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("car_brand_id") references "car_brand" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("status_car_id") references "status_car" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("technical_inspection_id") references "technical_inspection" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("insurance_policy_id") references "insurance_policy" ("id") ON DELETE CASCADE
);

--changeset vitalina:12

CREATE TABLE "client_order"
(
    "id"                    serial PRIMARY KEY,
    "comment"               text,
    "order_date_time"       timestamp NOT NULL,
    "date_time_start_rent"  timestamp NOT NULL,
    "date_time_finish_rent" timestamp NOT NULL,
    "cost"                  decimal   NOT NULL,
    "client_id"             serial,
    "car_id"                serial,
    "manager_id"            serial,
    "status_order_id"       serial,

    FOREIGN KEY ("client_id") references "client" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("car_id") references "car" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("manager_id") references "manager" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("status_order_id") references "status_order" ("id") ON DELETE CASCADE
);
