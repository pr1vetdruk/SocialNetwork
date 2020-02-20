insert into user_dbt (login, password, active, activation_code, email, date_creation)
              values ('admin', '123', true, 'q123', 'test@test.test', current_date);
insert into user_role_dbt (user_id, roles) values (1, 'USER');

create extension if not exists pgcrypto;

update user_dbt set password = crypt(password, gen_salt('bf', 8));

insert into user_personal_data_dbt (user_id, first_name, last_name, city_id, date_birth, date_creation)
                             values(1, 'Дима', 'Сомсин', 19, current_date - 9500, current_date);