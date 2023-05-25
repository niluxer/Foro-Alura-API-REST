create table autores (
     id bigint not null auto_increment,
     nombre varchar(100) not null,
     email  varchar(100) not null unique,
     direccion varchar(100),
     telefono varchar(50),
     primary key(id)
);