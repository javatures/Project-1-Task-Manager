create table types (
    id serial primary key,
    taskType text unique not null
);

-- create table users (
--     id serial primary key,
--     username text unique not null,
--     password
-- );

create table tasks (
    id serial primary key,
    taskDate date not null,
    task text not null,
    typeId integer not null references types (id)
);

create table completedTasks (
    id serial primary key,
    taskDate date not null,
    task text not null,
    typeId integer not null references types (id)
)