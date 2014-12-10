CREATE SCHEMA hzwxuoyr_adminlogin;
CREATE TABLE login(
id int(10) NOT NULL AUTO_INCREMENT,
username varchar(255) NOT NULL,
password varchar(255) NOT NULL,
PRIMARY KEY (id)
);

CREATE USER 'hzwxuoyr_admin'@'%' IDENTIFIED BY '***';
GRANT ALL PRIVILEGES ON *.* TO 'hzwxuoyr_admin'@'%' IDENTIFIED BY '***' 
WITH GRANT OPTION MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;
GRANT ALL PRIVILEGES ON `hzwxuoyr_adminlogin`.* TO 'hzwxuoyr_admin'@'%';