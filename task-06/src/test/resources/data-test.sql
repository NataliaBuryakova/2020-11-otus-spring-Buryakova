


insert into genre (`kind`) values ( 'Приключения');
insert into genre (`kind`) values ( 'Сказки');
insert into genre (`kind`) values ( 'Детективы');


insert into author (`name`) values ( 'Р. Киплинг');
insert into author (`name`) values ( 'Н. Носов');
insert into author (`name`) values ( 'Л. Кэрролл');
insert into author (`name`) values ( 'А. Дойл');
insert into author (`name`) values ( 'О. Рой');

insert into book (`title`, authorid, genreid) values ( 'Незнайка в солнечном городе',2,2);
insert into book (`title`, authorid, genreid) values ('Маугли',1,1);
insert into book (`title`, authorid, genreid) values ('Алиса в стране чудес',3,2);
insert into book (`title`, authorid, genreid) values ( 'Шерлок Холмс',4,3);

insert into comment (text, bookid)
values  ('comment_1_To_Book_1', 1),
        ('comment_2_To_Book_1', 1),
        ('comment_1_To_Book_2', 2),
        ('comment_2_To_Book_2', 2),
        ('comment_1_To_Book_3', 3);

