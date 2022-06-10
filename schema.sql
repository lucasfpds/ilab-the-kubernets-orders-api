drop database if exists db_orders;

create database db_orders;

create table orders (
	id_oreder serial not null primary key,
	id_user integer not null,
	descricao varchar(255) not null, 
	valor_total integer not null,
	data_pedido timestamp not null,
	status varchar(20) not null
);