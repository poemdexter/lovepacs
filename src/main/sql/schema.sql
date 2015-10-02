create table users (
	username varchar(32) not null,
    password varchar(60) not null,
    enabled tinyint not null default 1,
    primary key (username)
);

create table authorities (
	username varchar(32) not null,
    authority varchar(32) not null,
    unique key uni_user_authority (username, authority),
    constraint fk_username foreign key (username) references users(username)
);