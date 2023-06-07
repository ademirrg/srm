create table person(
id bigint not null auto_increment,
name varchar(100) not null,
document varchar(50) not null,
document_type varchar(50) not null,
dt_create timestamp(6) not null,
dt_modify timestamp(6) null,

primary key (id)
);