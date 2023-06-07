create table person(
id bigint not null auto_increment,
name varchar(100) not null,
document varchar(50) not null,
document_type varchar(50) not null,

primary key (id)
);