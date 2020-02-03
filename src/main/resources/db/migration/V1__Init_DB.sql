create sequence hibernate_sequence start 1 increment 1;

create table city_dbt
(
    id   serial,
    name varchar(255) not null,
    primary key (id)
);

create table user_dbt
(
    id              int8         not null,
    login           varchar(32) not null,
    password        varchar(255) not null,
    active          boolean      not null,
    activation_code varchar(255) not null,
    email           varchar(255) not null,
    primary key (id)
);

create table user_role_dbt
(
    user_id int8 not null references user_dbt,
    roles   varchar(255)
);

create table user_personal_data_dbt
(
    id         int8                        not null,
    user_id    int8                        not null references user_dbt,
    first_name varchar(255)                not null,
    last_name  varchar(255)                not null,
    city_id    int4 references city_dbt    not null,
    date_birth timestamp without time zone not null,
    primary key (id)
);