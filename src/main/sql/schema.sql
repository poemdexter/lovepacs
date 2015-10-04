create table users (
  username varchar(32) not null,
  password varchar(60) not null,
  location int not null,
  enabled tinyint not null default 1,
  primary key (username),
  constraint fk_user_location foreign key (location) references locations(id)
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

create table locations (
  id int not null auto_increment,
  name varchar(64) not null,
  enabled tinyint not null default 1,
  primary key (id)
);

create table inventory (
  id int not null auto_increment,
  location int not null,
  item int not null,
  quantity int not null,
  primary key (id),
  unique key uni_location_item (location, item),
  constraint fk_inv_location foreign key (location) references locations(id),
  constraint fk_inv_item foreign key (item) references items(id)
);

create table plans (
  id int not null auto_increment,
  location int not null,
  packdate date not null,
  enabled tinyint not null default 1,
  primary key (id),
  constraint fk_plan_location foreign key (location) references locations(id)
);

create table plan_boxes (
  id int not null auto_increment,
  plan int not null,
  box int not null,
  quantity int not null,
  primary key (id),
  unique key uni_plan_box (plan, box),
  constraint fk_pb_plan foreign key (plan) references plans(id),
  constraint fk_pb_box foreign key (box) references boxes(id)
);