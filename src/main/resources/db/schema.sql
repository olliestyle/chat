create table persons (
    id serial primary key ,
    username text,
    password text
);

create table roles (
    id serial primary key ,
    name text
);

create table rooms (
    id serial primary key ,
    name text
);

create table messages (
    id serial primary key ,
    text_message text,
    person_id int references persons(id),
    room_id int references rooms(id)
);

create table room_details (
    id serial primary key ,
    person_id int references persons(id),
    room_id int references rooms(id),
    role_id int references roles(id)
);