create sequence hibernate_sequence start 1 increment 1;

create table cities_dbt (
                            id serial,
                            name varchar(255) not null,
                            primary key (id)
);

create table user_dbt
(
    id              int8         not null,
    login           varchar(255) not null,
    password        varchar(255) not null,
    active          boolean      not null,
    activation_code varchar(255) not null,
    email           varchar(255) not null,
    first_name  varchar(255) not null,
    last_name  varchar(255) not null,
    city  int4 references cities_dbt not null,
    date_birth timestamp without time zone not null,
    primary key (id)
);

create table user_role_dbt
(
    user_id int8 not null references user_dbt,
    roles   varchar(255)
);