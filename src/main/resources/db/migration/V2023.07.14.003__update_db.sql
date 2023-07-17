alter table note
add person_id int;

alter table note
    add constraint add_person_id foreign key (person_id) references  person(id);
