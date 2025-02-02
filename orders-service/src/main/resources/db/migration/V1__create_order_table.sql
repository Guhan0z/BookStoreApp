create sequence order_id_seq start with 1 increment by 10;
create sequence order_item_id_seq start with 1 increment by 10;

create table orders (
    id  bigint default nextval('order_id_seq') not null primary key,
    order_number text not null unique,
    username text not null,
    customer_name text not null,
    customer_email text not null,
    customer_phone text not null,
    address_line1 text not null,
    address_line2 text,
    address_city text not null,
    address_state text not null,
    address_pin_code text not null,
    address_country text not null,
    status text not null,
    comments text,
    create_time timestamp,
    update_time timestamp
);

create table order_item (
    id bigint default nextval('order_item_id_seq') not null primary key,
    code text not null,
    name text not null,
    price numeric not null,
    quantity integer not null,
    order_id bigint not null references orders(id)
);