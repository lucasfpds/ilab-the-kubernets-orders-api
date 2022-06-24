drop database if exists dborders;

create database dborders;

drop table orders;

create table orders (
	id_order serial not null primary key,
	id_user integer not null,
	id_Admin integer not null,
	descriptions varchar(255) not null, 
	total_value integer not null,
	orders_date timestamp not null,
	statuss varchar(20) not null default 'aberto',
	status_email varchar(50) not null default 'não enviado'
);