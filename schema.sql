drop database if exists db_orders;

create database db_orders;

drop table if exists orders;

create table orders (
	id_order serial not null primary key,
	id_user integer not null,
	description varchar(255) not null, 
	total_value integer not null,
	orders_date timestamp not null,
	status varchar(20) not null default 'aberto'
);