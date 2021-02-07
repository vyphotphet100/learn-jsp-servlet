use learn-jsp-servlet;

insert into role(code, name) values("ADMIN", "Quản trị hệ thống");
insert into role(code, name) values("USER", "Người dùng");

insert into user(username, password, fullname, status, roleid) values("admin", "123456", "admin", 1, 1);
insert into user(username, password, fullname, status, roleid) values("An", "123456", "Nguyễn Văn An", 1, 2);
insert into user(username, password, fullname, status, roleid) values("Bình", "123456", "Nguyễn Thị Bình", 1, 2);




