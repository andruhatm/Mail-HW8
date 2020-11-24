create table products
(
    id       serial      not null
        constraint products_pkey
            primary key,
    name     varchar(50) not null,
    quantity smallint    not null
);

alter table products
    owner to postgres;
