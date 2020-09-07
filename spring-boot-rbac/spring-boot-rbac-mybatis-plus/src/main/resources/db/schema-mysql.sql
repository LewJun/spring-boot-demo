CREATE DATABASE IF NOT EXISTS `rbac` DEFAULT CHARACTER SET utf8mb4 ;

USE `rbac` ;

DROP TABLE IF EXISTS sys_user_role ;

DROP TABLE IF EXISTS sys_role_permission ;

DROP TABLE IF EXISTS sys_user ;

DROP TABLE IF EXISTS sys_role ;

DROP TABLE IF EXISTS sys_permission ;


CREATE TABLE IF NOT EXISTS sys_user (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR (32) NOT NULL,
  `password` VARCHAR (120) NOT NULL,
  PRIMARY KEY (id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 ;

CREATE TABLE IF NOT EXISTS sys_role (
  id INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR (10) NOT NULL,
  PRIMARY KEY (id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 ;

CREATE TABLE IF NOT EXISTS sys_user_role (
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (user_id, role_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 ;

CREATE TABLE IF NOT EXISTS sys_permission (
  id INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR (120) NOT NULL,
  url VARCHAR (120) NOT NULL,
  parent_id INT,
  description VARCHAR (120) not null default '',
  PRIMARY KEY (id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 ;

CREATE TABLE IF NOT EXISTS sys_role_permission (
  role_id INT NOT NULL,
  permission_id INT NOT NULL,
  PRIMARY KEY (role_id, permission_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 ;

ALTER TABLE sys_permission
  ADD CONSTRAINT uk_sys_permission__parent_id__name UNIQUE (parent_id, `name`) ;

ALTER TABLE sys_permission
  ADD CONSTRAINT uk_sys_permission__url UNIQUE (`url`) ;

ALTER TABLE sys_role
  ADD CONSTRAINT uk_sys_role__name UNIQUE (NAME) ;

ALTER TABLE sys_user
  ADD CONSTRAINT uk_sys_user__username UNIQUE (username) ;

-- 添加外键
ALTER TABLE sys_user_role
  ADD CONSTRAINT fk_sys_user_role__user_id FOREIGN KEY (user_id) REFERENCES sys_user (id)
  ON UPDATE CASCADE ON DELETE CASCADE ;

ALTER TABLE sys_user_role
  ADD CONSTRAINT fk_sys_user_role__role_id FOREIGN KEY (role_id) REFERENCES sys_role (id) ON UPDATE CASCADE ON DELETE CASCADE ;

ALTER TABLE sys_role_permission
  ADD CONSTRAINT fk_sys_role_permission__role_id FOREIGN KEY (role_id) REFERENCES sys_role (id) ON UPDATE CASCADE ON DELETE CASCADE ;

ALTER TABLE sys_role_permission
  ADD CONSTRAINT fk_sys_role_permission__permission_id FOREIGN KEY (permission_id) REFERENCES sys_permission (id) ON UPDATE CASCADE ON DELETE CASCADE ;

ALTER TABLE sys_permission
  ADD CONSTRAINT fk_sys_permission__parent_id FOREIGN KEY (parent_id) REFERENCES sys_permission (id) ON UPDATE CASCADE ON DELETE CASCADE ;

-- -------------------------------------

DROP TABLE IF EXISTS AB01;

CREATE TABLE AB01
(
    AAB001 INT NOT NULL AUTO_INCREMENT COMMENT '部门编号',
    AAB002 VARCHAR(30) NOT NULL COMMENT '部门名称',
    AAB003 VARCHAR(30) NOT NULL COMMENT '部门所在位置',
    CREATE_TIME TIMESTAMP, -- 尽量使用TIMESTAMP，而非DATETIME
    UPDATE_TIME TIMESTAMP, -- 尽量使用TIMESTAMP，而非DATETIME
    PRIMARY KEY (AAB001)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 ;

DROP TABLE IF EXISTS AC01;

CREATE TABLE AC01
(
    AAC001 INT NOT NULL AUTO_INCREMENT COMMENT '雇员编号',
    AAC002 VARCHAR(30) NOT NULL COMMENT '雇员姓名',
    AAC003 VARCHAR(30) NOT NULL COMMENT '雇员职位',
    AAC004 INT NOT NULL DEFAULT -1 COMMENT '领导编号',
    AAC005 DATE        NOT NULL COMMENT '雇佣日期',
    AAC006 INT NOT NULL COMMENT '所在部门',
    CREATE_TIME TIMESTAMP, -- 尽量使用TIMESTAMP，而非DATETIME
    UPDATE_TIME TIMESTAMP, -- 尽量使用TIMESTAMP，而非DATETIME
    PRIMARY KEY (AAC001)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 ;

-- ------------------------
alter table sys_user drop column `password`;
alter table sys_user add column `email` VARCHAR(32) not null default '';
alter table sys_user add column `avatar` VARCHAR(128) not null default '';


DROP TABLE IF EXISTS sys_user_login ;

CREATE TABLE IF NOT EXISTS sys_user_login (
  user_id INT not null,
  `password` CHAR(60) NOT NULL, -- 固定大小的字符串，用char来指定类型
  PRIMARY KEY (user_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 ;

ALTER TABLE sys_user_login
  ADD CONSTRAINT fk_sys_user_login__user_id FOREIGN KEY (user_id) REFERENCES sys_user (id)
  ON UPDATE CASCADE ON DELETE CASCADE ;

ALTER TABLE sys_user_login
  ADD CONSTRAINT uk_sys_user_login__user_id UNIQUE (user_id) ;

-- ------------------------

drop table if exists sys_dept;

create table if not exists sys_dept (
  id INT not null AUTO_INCREMENT,
  `name` VARCHAR (32) not null default '',
  `description` VARCHAR (32) not null default '',
  `parent_id` int,
  PRIMARY KEY (id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4;


drop table if exists sys_dept_role;

CREATE TABLE IF NOT EXISTS sys_dept_role (
  dept_id INT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (dept_id, role_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 ;

alter table sys_dept
  add CONSTRAINT fk_sys_dept__parent_id FOREIGN key (parent_id) REFERENCES sys_dept (id)
  on update CASCADE on delete cascade ;

ALTER TABLE sys_dept
  ADD CONSTRAINT uk_sys_dept__parent_id__name UNIQUE (parent_id, `name`) ;

ALTER TABLE sys_dept_role
  ADD CONSTRAINT fk_sys_dept_role__dept_id FOREIGN KEY (dept_id) REFERENCES sys_dept (id)
  ON UPDATE CASCADE ON DELETE CASCADE ;

ALTER TABLE sys_dept_role
  ADD CONSTRAINT fk_sys_dept_role__role_id FOREIGN KEY (role_id) REFERENCES sys_role (id)
  ON UPDATE CASCADE ON DELETE CASCADE ;

alter table sys_user add column `nickname` VARCHAR (32) not null default '';
alter table sys_user add column `dept_id` INT;

alter table sys_user add CONSTRAINT fk_sys_user__dept_id FOREIGN KEY (dept_id) REFERENCES sys_dept (id)
on update CASCADE on DELETE set null;
