
drop table if exists users;
create table users (
  username varchar(256),
  password varchar(256),
  enabled boolean
);

drop table if exists authorities;
create table authorities (
  username varchar(256),
  authority varchar(256)
);