DROP DATABASE IF EXISTS HeartRate;
CREATE DATABASE HeartRate;

USE HeartRate;

CREATE TABLE IF NOT EXISTS User (
Email varchar(50),
Password varchar(15) NOT NULL,
Firstname varchar(15),
Lastname varchar(15),
PRIMARY KEY (Email)
);

CREATE TABLE IF NOT EXISTS Elder (
DeviceID int AUTO_INCREMENT,
Firstname varchar(15),
Lastname varchar(15),
Age int,
location POINT NOT NULL,
-- Lat FLOAT(10, 6) NOT NULL,
-- Lng FLOAT(10, 6) NOT NULL,
PRIMARY KEY (DeviceID)
);

CREATE TABLE IF NOT EXISTS User_Elder_List (
Email_User varchar(50),
DeviceID_Elder int,
PRIMARY KEY (Email_User, DeviceID_Elder),
FOREIGN KEY (Email_user) REFERENCES User(Email),
FOREIGN KEY (DeviceID_Elder) REFERENCES Elder(DeviceID)
);