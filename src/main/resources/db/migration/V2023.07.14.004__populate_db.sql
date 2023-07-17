insert into role(role)
values
('USER'),
('ADMIN');

insert into person(role_id,username,passwoard)
select r.id, 'admin', '$2a$12$wc0TyZdxc1ao/4G6VDknIelpOBf57JU9dLHbUVDK1.QDNu59HBptq'
from role r where r.role='ADMIN'
union
select r.id, 'user', '$2a$12$PvBYipokFEuvIXLWOH3ksORM35H6SZWb7JMdn570l7eoZHfkxI5TS'
from role r where r.role='USER';

insert into person_role(person_id,role_id)
select r.id, p.id
from role r
inner join person p on p.username = 'user'
where r.role='USER'
union
select r.id, p.id
from role r
join person p on p.username = 'admin'
where r.role='ADMIN';

