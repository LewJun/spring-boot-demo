DROP TABLE IF EXISTS ab01;

CREATE TABLE ab01
(
    aab001 bigint(20) not null comment '部门编号',
    aab002 varchar(30) not null comment '部门名称',
    aab003 varchar(30) not null comment '部门所在位置',
    primary key (aab001)
);

drop table if exists ac01;

create table ac01
(
    aac001 bigint(20) not null comment '雇员编号',
    aac002 varchar(30) not null comment '雇员姓名',
    aac003 varchar(30) not null comment '雇员职位',
    aac004 bigint(20) not null default '' comment '领导编号',
    aac005 DATE not null comment '雇佣日期',
    aac006 bigint(20) not null comment '所在部门'
);
