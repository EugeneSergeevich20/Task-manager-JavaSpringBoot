create table tb_user (
    id bigserial not null,
    reg_date timestamp(6),
    email varchar(255),
    login varchar(255),
    name varchar(255),
    password varchar(255),
    patronymic varchar(255),
    role varchar(255) check (role in ('ROLE_ADMIN','ROLE_CLIENT')),
    surname varchar(255),
    primary key (id)
);