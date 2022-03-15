insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');

insert into account(kind, email, pw, nickname) values (0, 'test01@gmail.com', '$2a$10$c0HWGStVRYPbLeJqWxGFpuHKmfl6PKew6kZzBNNm/gyaN6yNgc3M2', 'test01');
insert into account(kind, email, pw, nickname) values (1, 'test02@gmail.com', '$2a$10$c0HWGStVRYPbLeJqWxGFpuHKmfl6PKew6kZzBNNm/gyaN6yNgc3M2', 'test02');
insert into account(kind, email, pw, nickname) values (1, 'test03@gmail.com', '$2a$10$c0HWGStVRYPbLeJqWxGFpuHKmfl6PKew6kZzBNNm/gyaN6yNgc3M2', 'test03');

insert into account_authorities(account_id, authority_name) values (1, 'ROLE_ADMIN');
insert into account_authorities(account_id, authority_name) values (1, 'ROLE_USER');
insert into account_authorities(account_id, authority_name) values (2, 'ROLE_USER');
insert into account_authorities(account_id, authority_name) values (3, 'ROLE_USER');

insert into place(name, address, open_at, close_at, reserve, reserve_max) values ('testPlace01', 'testPlace01address', '11:00:00', '20:00:00', true, 20);
insert into place(name, address, open_at, close_at, reserve, reserve_max) values ('testPlace02', 'testPlace02address', '10:00:00', '22:00:00', false, 10);
insert into place(name, address, open_at, close_at, reserve, reserve_max) values ('testPlace03', 'testPlace03address', '13:00:00', '23:00:00', true, 200);