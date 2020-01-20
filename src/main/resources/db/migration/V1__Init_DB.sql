create sequence hibernate_sequence start 1 increment 1;

create table user_role_dbt
(
    user_id int8 not null,
    roles   varchar(255)
);

create table user_dbt
(
    id              int8         not null,
    login           varchar(255) not null,
    password        varchar(255) not null,
    active          boolean      not null,
    activation_code varchar(255),
    email           varchar(255),

    primary key (id)
);


alter table if exists user_role_dbt
    add constraint user_role_dbt_user_fk
        foreign key (user_id) references user_dbt;