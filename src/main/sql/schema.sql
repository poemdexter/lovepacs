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

create table items (
    id int not null auto_increment,
    name varchar(64) not null,
    price decimal(5,2) default 0.00,
    enabled tinyint not null default 1,
    primary key (id)
);

create table boxes (
    id int not null auto_increment,
    name varchar(64) not null,
    enabled tinyint not null default 1,
    primary key (id)
);

create table contents (
    id int not null auto_increment,
    box int not null,
    item int not null,
    quantity int not null,
    primary key (id),
    constraint fk_box foreign key (box) references boxes(id),
    constraint fk_item foreign key (item) references items(id)
);