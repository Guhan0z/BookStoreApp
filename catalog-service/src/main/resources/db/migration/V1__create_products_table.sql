create sequence id_seq start with 1 increment by 50;

create table products (
    id bigint default nextval('id_seq') not null primary key,
    code text not null unique,
    name text not null,
    description text,
    image_url text,
    price numeric not null
);