drop database if exists itcast_jpa;

create database itcast_jpa character set utf8;

GRANT ALL PRIVILEGES ON itcast_jpa.* TO itcast_jpa@localhost IDENTIFIED BY "itcast_jpa";