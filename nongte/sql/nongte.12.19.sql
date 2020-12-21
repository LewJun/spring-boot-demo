use nongte;

CREATE TABLE `sys_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL DEFAULT '',
  `password` char(60) NOT NULL DEFAULT '',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

INSERT INTO `sys_user` (`id`, `username`, `password`, `status`)
VALUES
	(1, 'admin', '$2a$10$0HYHyrtZV86J4orePe6x0u8qL95WVWZe4pk5sxeZPM3RiZUXGuisi', 1);

alter table product
modify column `desc` VARCHAR(48) not null default '';


ALTER TABLE product ADD COLUMN html2 TEXT;