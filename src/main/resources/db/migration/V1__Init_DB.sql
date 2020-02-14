create sequence hibernate_sequence start 1 increment 1;

create table city_dbt
(
    id   SMALLSERIAL,
    name varchar(255) not null,
    primary key (id)
);

create table user_dbt
(
    id              BIGSERIAL,
    login           varchar(32)  not null,
    password        varchar(255) not null,
    active          boolean      not null,
    activation_code varchar(255) not null,
    email           varchar(255) not null,
    primary key (id)
);

create table user_role_dbt
(
    id      serial,
    user_id int8 not null references user_dbt,
    roles   varchar(255)
);

create table user_personal_data_dbt
(
    id         BIGSERIAL,
    user_id    int8                        not null references user_dbt,
    first_name varchar(255)                not null,
    last_name  varchar(255)                not null,
    city_id    int2 references city_dbt    not null,
    date_birth timestamp without time zone not null,
    primary key (id)
);

create table publication_dbt
(
    id            BIGSERIAL,
    user_id       int8                        not null references user_dbt,
    text          varchar(2048)               not null,
    tag           varchar(32),
    file_name      varchar(255),
    date_creation timestamp without time zone not null,
    date_change   timestamp without time zone,
    primary key (id)
);