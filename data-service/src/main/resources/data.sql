create table customers (id bigint generated by default as identity (start with 1), email varchar(255), name varchar(255), password varchar(255), primary key (id));

INSERT INTO customers (name, email, password) VALUES ('Joe', 'joe@gmail.com', 'passworD');
INSERT INTO customers (name, email, password) VALUES ('Anna', 'anna@gmail.com', '1PASSWORD');
INSERT INTO customers (name, email, password) VALUES ('Kate', 'kate@gmail.com', 'haslo123');