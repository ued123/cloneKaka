CREATE DATABASE clonekaka DEFAULT CHARACTER SET utf8mb4;

-- sample table
CREATE TABLE kuser (

user_id int(3) NOT NULL AUTO_INCREMENT,
user_name VARCHAR(20) default '',
user_organ VARCHAR(20) default '',
user_state int(2) default 1,
password VARCHAR(20) default '',
password_type VARCHAR(10) default '',
PRIMARY KEY (user_id)

) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;

-- sample data
INSERT INTO kuser(user_name, user_state, password)
VALUE ('testAdmin',2,'qwer1234');
