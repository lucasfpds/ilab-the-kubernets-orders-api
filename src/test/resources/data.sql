create table orders (
	id_order serial not null primary key,
	id_user integer not null,
	description varchar(255) not null, 
	total_value integer not null,
	orders_date timestamp not null,
	status varchar(20) not null default 'aberto',
  	status_email varchar(50) not null default 'não enviado'
);