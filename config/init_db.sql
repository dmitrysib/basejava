drop table if exists contact;
drop table if exists section;
drop table if exists resume;

CREATE TABLE resume (
    uuid varchar(36) not null
        primary key,
    full_name varchar(256) not null
);

create table contact
(
    id serial not null
        constraint contact_pk
            primary key,
    type varchar(256) not null,
    value varchar(256) not null,
    resume_uuid varchar(36) not null
        constraint contact_resume_uuid_fk
            references resume
            on delete cascade
);

create table section
(
    id serial not null
        constraint section_pk
            primary key,
    section_name varchar(256) not null,
    value text not null,
    resume_uuid varchar(36) not null
        constraint section_resume_uuid_fk
            references resume
            on delete cascade
);