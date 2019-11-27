create table person (
	id int not null auto_increment primary key,
	name char(8) not null,
	gender char(2) not null,
	age int not null	
)engine=InnoDB auto_increment=1 default charset=utf8;  