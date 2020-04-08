DROP DATABASE IF EXISTS HeartRate;
CREATE DATABASE HeartRate;

USE HeartRate;

CREATE TABLE IF NOT EXISTS User (
email varchar(50),
password varchar(15) NOT NULL,
firstName varchar(15),
lastName varchar(15),
PRIMARY KEY (email)
);

CREATE TABLE IF NOT EXISTS Elder (
deviceID int AUTO_INCREMENT,
firstName varchar(15),
lastName varchar(15),
age int,
location POINT NOT NULL,
-- Lat FLOAT(10, 6) NOT NULL,
-- Lng FLOAT(10, 6) NOT NULL,
PRIMARY KEY (deviceID)
);

CREATE TABLE IF NOT EXISTS User_Elder_List (
email_user varchar(50),
deviceID_elder int,
PRIMARY KEY (email_user, deviceID_elder),
FOREIGN KEY (email_user) REFERENCES User(email),
FOREIGN KEY (deviceID_elder) REFERENCES Elder(deviceID)
);