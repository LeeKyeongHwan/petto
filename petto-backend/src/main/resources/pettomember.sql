create table pettoMember(
    id_no int not null auto_increment,
    id varchar(30) not null,
    pw varchar(50) not null,
    email varchar(50) not null,
    phoneNumber varchar(80) not null,
    name varchar(30) not null,
    birthday varchar(80) not null,
    email varchar(80) not null,
    petsRaised varchar(20) not null,
    reg_date timestamp not null default now(),
    primary key(id_no)
);