CREATE DATABASE PORTFOLIO;
use PORTFOLIO;
SHOW DATABASES LIKE 'PORTFOLIO';
drop table members;
CREATE TABLE members (
	age integer not null,
    album varchar(255) not null,
    created_at date not null,
    email varchar(255) not null,
    name varchar(255) not null,
    password varchar(255) not null,
    ranks integer not null,
    primary key(email)
);
delete from members where email='';

select * from members;
insert into members(age, album, created_at,email,name,password,ranks)
values(25, 'VqHh5Jk', now(), 'pelto@naver.com', '조성몬', '1234', 2);
insert into members(age, album, created_at,email,name,password,ranks)
values(25, 'VqHh5Jk', now(), 'admin', '관리자', '1234', 3);
update members
set created_at = now()
where email='admin';

#delete from members
#where email like 'pelto@naver.com';
drop table portfolios;
create table portfolios (
	bno bigint not null auto_increment,
	author varchar(64) not null,
    content text not null,
    created_at date not null,
    img text not null,
    title text not null,
    primary key(bno)
);
select * from portfolios;
insert into portfolios(author, body, created_at, img, title)
values('lee', '34', now(), 'https://i.imgur.com/WupZBof.jpg', '34');
delete from portfolios where bno=2;

drop table posts;
create table posts (
	bno bigint not null auto_increment,
	author varchar(64) not null,
    content text not null,
    created_at date not null,
    title text not null,
    primary key(bno)
);
insert into posts(author, content, created_at, title)
values('Jo', '나는펠토다', now(), '나는펠포다');
#delete from posts where bno=1;
select * from posts;

update members set age = 26 where email = 'lee@naver.com';
SET SQL_SAFE_UPDATES =0;

drop table tokens;
create table tokens (
	email varchar(64) not null,
    token text not null,
    ranks integer not null,
    primary key(email)
);
select * from tokens;

drop table portfoliocomments;
create table portfoliocomments (
	cno bigint auto_increment not null,
    bno bigint not null,
    content text not null,
    created_at date not null,
    author varchar(64) not null,
    primary key(cno),
    constraint bno_fk foreign key(bno) references portfolios(bno) on delete cascade
);
insert into portfoliocomments(bno, content, created_at, author)
values(1, '댓글을 작성합니다!!!', now(), 'pelto@naver.com');
select * from portfoliocomments;

drop table postcomments;
create table postcomments (
	cno bigint auto_increment not null,
    bno bigint not null,
    content text not null,
    created_at date not null,
    author varchar(64) not null,
    primary key(cno),
    constraint post_bno_fk foreign key(bno) references posts(bno) on delete cascade
);
insert into postcomments(bno, content, created_at, author)
values(1, '댓글을 작성합니다!!!', now(), 'pelto@naver.com');
select * from postcomments;