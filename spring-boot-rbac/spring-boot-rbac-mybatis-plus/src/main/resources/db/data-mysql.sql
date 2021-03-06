
--  -------------------------

TRUNCATE TABLE sys_user;
TRUNCATE TABLE sys_user_login;
TRUNCATE TABLE sys_role;
TRUNCATE TABLE sys_permission;
TRUNCATE TABLE sys_user_role;
TRUNCATE TABLE sys_role_permission;

INSERT INTO sys_user (id, username, nickname)
VALUES
  (1, 'admin', '超级管理员')
  ,(2, 'normal', '普通人')
  ,(3, 'user', '用户')
  ;

INSERT INTO sys_user_login (user_id, `password`)
VALUES
  (1, '$2a$10$WkmpZmq.pwpTwhCSzv6QXehG7UJ7vvQb/Ig7z0IKfZBxQv4hmsRGm')
  ,(2, '$2a$10$v3wVT1WTpFBOLOHCfINr9utndMlKQiMMsDxn2gv6iF/5zIPa72StG')
  ,(3, '$2a$10$6dqkOA7c7NBMEXW3yjMvRuBrOtFVHr10jSNXLMyczQaHKJ/65v1te')
  ;

INSERT INTO sys_role (id, `name`)
VALUES
  (1, 'ADMIN')
  ,(2, 'NORMAL')
  ,(3, 'DEV')
  ,(4, 'SALE')
  ;

INSERT INTO sys_user_role (user_id, role_id)
VALUES
  (1, 1)
  ,(2, 2)
  ;

INSERT INTO sys_permission (id, url, `name`)
VALUES
  (1, '/admin', 'admin')
  ,(2, '/normal', 'normal')
  ;

INSERT INTO sys_role_permission (role_id, permission_id)
VALUES
  (1, 1)
  ,(2, 2)
  ;


-- -----------------------------------------

DELETE FROM AB01 WHERE 1=1;

INSERT INTO AB01(AAB001, AAB002, AAB003)
VALUES (10, 'ACCOUNTING', 'NEW YORK'),
       (20, 'RESEARCH', 'DALLAS'),
       (30, 'SALES', 'CHICAGO'),
       (40, 'OPERATIONS', 'BOSTON');

DELETE FROM AC01 WHERE 1 = 1;

INSERT INTO AC01(aac001, aac002, aac003, aac004, aac005, aac006)
VALUES (7369, 'SMITH', 'CLERK', 7902, '1980-12-17', 20),
       (7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', 30),
       (7521, 'WARD', 'SALESMAN', 7698, '1981-02-22', 30),
       (7566, 'JONES', 'MANAGER', 7839, '1981-04-02', 20),
       (7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28', 30),
       (7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01', 30),
       (7782, 'CLARK', 'MANAGER', 7839, '1981-06-09', 10),
       (7839, 'KING', 'PRESIDENT', -1, '1981-11-17', 10),
       (7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08', 30),
       (7900, 'JAMES', 'CLERK', 7698, '1981-12-03', 30),
       (7902, 'FORD', 'ANALYST', 7566, '1981-12-03', 20),
       (7934, 'MILLER', 'CLERK', 7782, '1982-01-23', 10);

-- -----------------------------------------

truncate table sys_dept;

insert into sys_dept (id, `name`, `description`, parent_id)
VALUES
(1, '根部门', '实际公司或部门名称，id永远是1', NULL),
(2, '开发部门', '', 1),
(3, '销售部门', '', 1),
(4, '销售A小组', '', 3),
(5, '销售B小组', '', 3),
(6, '销售C小组', '', 3),
(7, '幼儿园开发组', '', 2),
(8, '银行开发组', '', 2),
(9, '行政部门', '', 1)
;

truncate table sys_dept_role;

insert into sys_dept_role (dept_id, role_id)
values
(2,3),
(3,4);
