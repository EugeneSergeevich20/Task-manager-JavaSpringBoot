create table tb_user
(
    id         bigserial not null,
    reg_date   timestamp(6),
    email      varchar(255),
    login      varchar(255),
    name       varchar(255),
    password   varchar(255),
    patronymic varchar(255),
    role       varchar(255) check (role in ('ROLE_ADMIN', 'ROLE_CLIENT')),
    surname    varchar(255),
    primary key (id)
);

create table tb_project
(
    id          bigserial not null,
    create_date timestamp(6),
    name        varchar(255),
    description varchar(255),
    primary key (id)
);

create table tb_user_project
(
    project_id bigserial not null,
    user_id    bigserial not null
);

create table tb_task
(
    id          bigserial not null,
    title       varchar(255),
    description varchar(255),
    status      varchar(255) check ( status in ('NEW', 'COMPLETED')),
    deadline    timestamp(6),
    priority    varchar(255) check ( priority in ('HIGH', 'MEDIUM', 'LOW', 'NONE')),
    create_date timestamp(6),
    project_id  bigserial,
    primary key (id)
);

alter table if exists tb_user_project
    add constraint project_user_fk
    foreign key (project_id) references tb_project;

alter table if exists tb_user_project
    add constraint user_project_fk
    foreign key (user_id) references tb_user;

alter table if exists tb_task
    add constraint task_project_fk
    foreign key (project_id) references tb_project;