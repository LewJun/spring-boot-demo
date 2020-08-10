DROP TABLE IF EXISTS AB01;

CREATE TABLE AB01
(
    AAB001 BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '部门编号',
    AAB002 VARCHAR(30) NOT NULL COMMENT '部门名称',
    AAB003 VARCHAR(30) NOT NULL COMMENT '部门所在位置',
    CREATE_TIME TIMESTAMP,
    UPDATE_TIME TIMESTAMP,
    PRIMARY KEY (AAB001)
);

DROP TABLE IF EXISTS AC01;

CREATE TABLE AC01
(
    AAC001 BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '雇员编号',
    AAC002 VARCHAR(30) NOT NULL COMMENT '雇员姓名',
    AAC003 VARCHAR(30) NOT NULL COMMENT '雇员职位',
    AAC004 BIGINT(20)  NOT NULL DEFAULT -1 COMMENT '领导编号',
    AAC005 DATE        NOT NULL COMMENT '雇佣日期',
    AAC006 BIGINT(20)  NOT NULL COMMENT '所在部门',
    CREATE_TIME TIMESTAMP,
    UPDATE_TIME TIMESTAMP,
    PRIMARY KEY (AAC001)
);
