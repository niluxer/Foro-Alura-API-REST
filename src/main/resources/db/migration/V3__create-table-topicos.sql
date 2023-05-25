create table topicos (
     id bigint not null auto_increment,
     titulo varchar(200) not null unique,
     mensaje text not null,
     fecha_creacion date not null default (CURRENT_DATE),
     estatus varchar(100) not null,
     autor_id bigint not null references autores(id),
     curso_id bigint not null references cursos(id),
     primary key(id)
);