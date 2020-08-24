INSERT INTO sys_user (id, username, password) VALUES (1,'admin','admin');
INSERT INTO sys_user (id, username , password) VALUES (2,'normal','normal');

INSERT INTO sys_role (id, name) VALUES (1,'ADMIN');
INSERT INTO sys_role (id, name) VALUES (2,'NORMAL');

INSERT INTO sys_permission (id, url, name) VALUES (1,'/admin','admin');
INSERT INTO sys_permission (id, url, name) VALUES (2,'/normal','normal');

INSERT INTO sys_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO sys_user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO sys_role_permission (role_id, permission_id) VALUES (1, 1);
INSERT INTO sys_role_permission (role_id, permission_id) VALUES (2, 2);
