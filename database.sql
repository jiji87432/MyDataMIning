create table main(
	id int(100) primary key auto_increment,
	userId int(100) not null,
	tagId int(100) not null,
	topicId int(100) not null
);
create table tag(
	tagId int(80) primary key auto_increment,
	content varchar(55) not null
);
create table user(
	userId int(80) primary key auto_increment,
	location varchar(25),
	profession varchar(25),
	gender int(5)
);
create table topic(
	topicId int(80) primary key auto_increment,
	content varchar(55)
);