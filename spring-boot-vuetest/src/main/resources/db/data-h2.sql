delete from AB01 where 1=1;

insert into AB01(AAB001, AAB002, AAB003)
values
(110, 'ACCOUNTING110', 'NEW YORK'),
(210, 'ACCOUNTING210', 'NEW YORK'),
(310, 'ACCOUNTING310', 'NEW YORK'),
(410, 'ACCOUNTING410', 'NEW YORK'),
(510, 'ACCOUNTING510', 'NEW YORK'),
(610, 'ACCOUNTING610', 'NEW YORK'),
(710, 'ACCOUNTING710', 'NEW YORK'),
(810, 'ACCOUNTING810', 'NEW YORK'),
(111, 'ACCOUNTING', 'NEW YORK'),
(112, 'RESEARCH', 'DALLAS'),
(113, 'SALES', 'CHICAGO'),
(114, 'OPERATIONS', 'BOSTON');

delete from AC01 where 1 = 1;

insert into AC01
values (7369, 'SMITH', 'CLERK', 7902, '1980-12-17', 112),
       (7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', 113),
       (7521, 'WARD', 'SALESMAN', 7698, '1981-02-22', 113),
       (7566, 'JONES', 'MANAGER', 7839, '1981-04-02', 112),
       (7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28', 113),
       (7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01', 113),
       (7782, 'CLARK', 'MANAGER', 7839, '1981-06-09', 111),
       (7839, 'KING', 'PRESIDENT', -1, '1981-11-17', 111),
       (7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08', 113),
       (7900, 'JAMES', 'CLERK', 7698, '1981-12-03', 113),
       (7902, 'FORD', 'ANALYST', 7566, '1981-12-03', 112),
       (7934, 'MILLER', 'CLERK', 7782, '1982-01-23', 111);
