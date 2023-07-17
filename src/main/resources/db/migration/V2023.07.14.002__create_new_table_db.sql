
create table if not exists role(
  id identity,
  role varchar(30),
  primary key(id),
  check (role in('USER','ADMIN','SUPER_ADMIN'))
);

create table if not exists person(
    id identity,
    role_id int not null,
    username varchar(200),
    passwoard varchar(350),
    primary key(id),
    foreign key (role_id) references role(id)
);

create table if not exists person_role (
  person_id int not null,
  role_id int not null,
  primary key (person_id, role_id),
  foreign key (person_id) references person (id),
 foreign key (role_id) references role (id)
);
