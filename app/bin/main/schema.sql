create table types (
    id serial primary key,
    taskType text unique not null
);

create table users (
    id serial primary key,
    username text unique not null,
    password text not null,
    firstName text not null,
    lastName text not null,
    email text unique not null
);

create table tasks (
    id serial primary key,
    taskDate date not null,
    task text not null,
    typeId integer not null references types (id),
    userId integer not null references users (id)
);

create table completedTasks (
    id serial primary key,
    taskDate date not null,
    task text not null,
    typeId integer not null references types (id),
    userId integer not null references users (id)
)