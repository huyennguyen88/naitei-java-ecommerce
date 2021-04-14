/*
INSERT DATA INTO USERS TABLE
*/

INSERT INTO tripletclothes.users (fullname, username, email, avatar, phone, address, password) VALUES
('Nguyễn Phan Nhi','admin','nguyenphannhi@gmail.com','image-avatar-0.jpg',0532057275,'Số 45 đường Hàm Nghi TP Đà Nẵng','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG'),
('Trần Anh Tú','anhtu','trananhtu@gmail.com','image-avatar-8.jpg',0913436684,'Số 53 đường Võ Văn Kiệt TP Hồ Chí Minh','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG'),
('Đặng Bùi Bảo','buibao','dangbuibao@gmail.com','image-avatar-9.jpg',0652559200,'Số 3 đường Ngô Tất Tố TP Hồ Chí Minh','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG'),
('Đỗ Ngô Nhi','ngonhi','dongonhi@gmail.com','image-avatar-10.jpg',0288545076,'Số 23 đường Nguyễn Xuân Ôn TP Hồ Chí Minh','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG');

insert into tripletclothes.roles(code) values
('ADMIN'),
('USER');

INSERT INTO tripletclothes.userroles(user_id,role_id) VALUES 
(1,1), (2,2), (3,2),(3,2);