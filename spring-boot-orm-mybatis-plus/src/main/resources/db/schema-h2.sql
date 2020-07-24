DROP TABLE IF EXISTS ab01;

CREATE TABLE ab01
(
    aab001 bigint(20) not null comment '部门编号',
    aab002 varchar(30) not null comment '部门名称',
    aab003 varchar(30) not null comment '部门所在位置',
    primary key (aab001)
);
