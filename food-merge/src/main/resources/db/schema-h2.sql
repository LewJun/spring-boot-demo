DROP TABLE IF EXISTS sys_user;

CREATE TABLE sys_user (
  id bigint(11) NOT NULL AUTO_INCREMENT,
  username varchar(32) NOT NULL DEFAULT '',
  password char(60) NOT NULL DEFAULT '',
  status tinyint(1) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
);

DROP TABLE IF EXISTS fd;

create table fd (
fd001 varchar(128)	comment '编码',
fd002 varchar(128)	comment '名称',
fd003 varchar(128)	comment '可食部',
fd004 varchar(128)	comment '一类名称',
fd005 varchar(128)	comment '二类名称',
fd006 varchar(128)	comment '能量',
fd007 varchar(128)	comment '蛋白质',
fd008 varchar(128)	comment '脂肪',
fd009 varchar(128)	comment '维生素A',
fd010 varchar(128)	comment '维生素B1',
fd011 varchar(128)	comment '维生素B2',
fd012 varchar(128)	comment '维生素C',
fd013 varchar(128)	comment '钙',
fd014 varchar(128)	comment '钠',
fd015 varchar(128)	comment '铁',
fd016 varchar(128)	comment '锌',
fd017 varchar(128)	comment '用量',
yyss001 varchar(128) null comment 'yyss 主键',
fd018 datetime null comment '对照时间',
primary key (fd001)
);

DROP TABLE IF EXISTS yyss;

create table yyss (
yyss001 varchar(128)	comment 'ID',
yyss002 varchar(128)	comment '大类编号',
yyss003 varchar(128)	comment '大类名称',
yyss004 varchar(128)	comment '亚类编号',
yyss005 varchar(128)	comment '亚类名称',
yyss006 varchar(128)	comment '食物编号',
yyss007 varchar(128)	comment '食物名称',
yyss008 varchar(128)	comment '食物大类',
yyss009 varchar(128)	comment '食物小类',
yyss010 varchar(128)	comment '标准码',
yyss011 varchar(128)	comment '可食部%',
yyss012 varchar(128)	comment '血糖指数',
yyss013 varchar(128)	comment '关联食物',
yyss014 varchar(128)	comment '食物名称',
yyss015 varchar(128)	comment '食物标准码',
yyss016 varchar(128)	comment '食物编号',
yyss017 varchar(128)	comment '大类编号',
yyss018 varchar(128)	comment '亚类编号',
yyss019 varchar(128)	comment '食物编码',
yyss020 varchar(128)	comment '食物描述',
yyss021 varchar(128)	comment '食物构成',
yyss022 varchar(128)	comment '可食部',
yyss023 varchar(128)	comment '水',
yyss024 varchar(128)	 comment '能量（千卡）',
yyss025 varchar(128)	comment '能量(千焦)',
yyss026 varchar(128)	comment '蛋白质-克',
yyss027 varchar(128)	comment '异亮氨酸',
yyss028 varchar(128)	comment '亮氨酸',
yyss029 varchar(128)	comment '赖氨酸',
yyss030 varchar(128)	comment '含硫氨基酸',
yyss031 varchar(128)	comment '丝氨酸',
yyss032 varchar(128)	comment '蛋氨酸',
yyss033 varchar(128)	comment '胱氨酸',
yyss034 varchar(128)	comment '芳香族氨基酸',
yyss035 varchar(128)	comment '苯丙氨酸',
yyss036 varchar(128)	comment '苷氨酸',
yyss037 varchar(128)	comment '络氨酸',
yyss038 varchar(128)	comment '苏氨酸',
yyss039 varchar(128)	comment '色氨酸',
yyss040 varchar(128)	comment '缬氨酸',
yyss041 varchar(128)	comment '脯氨酸',
yyss042 varchar(128)	comment '精氨酸',
yyss043 varchar(128)	comment '组氨酸',
yyss044 varchar(128)	comment '丙氨酸',
yyss045 varchar(128)	comment '天冬氨酸',
yyss046 varchar(128)	comment '谷氨酸 ',
yyss047 varchar(128)	comment '脂肪-克',
yyss048 varchar(128)	comment '脂肪酸',
yyss049 varchar(128)	comment '饱和脂肪酸',
yyss050 varchar(128)	comment '单不饱和脂肪酸',
yyss051 varchar(128)	comment '多不饱和脂肪酸 ',
yyss052 varchar(128)	comment '碳水化合物-克',
yyss053 varchar(128)	comment '膳食纤维-克',
yyss054 varchar(128)	comment '可溶性膳食纤维-克',
yyss055 varchar(128)	comment '不可溶性膳食纤维-克',
yyss056 varchar(128)	comment '胆固醇-毫克',
yyss057 varchar(128)	comment '胡萝卜素-毫克',
yyss058 varchar(128)	comment '维生素A-微克',
yyss059 varchar(128)	comment '维生素B1-毫克',
yyss060 varchar(128)	comment '核黄素(VB2)毫克',
yyss061 varchar(128)	comment '维生素B6-毫克',
yyss062 varchar(128)	comment '维生素B12-微克',
yyss063 varchar(128)	comment '维生素C-毫克',
yyss064 varchar(128)	comment '维生素D-毫克',
yyss065 varchar(128)	comment '维生素E-毫克',
yyss066 varchar(128)	comment '生育酚-毫克',
yyss067 varchar(128)	comment '维生素K-毫克',
yyss068 varchar(128)	comment '视黄醇-微克',
yyss069 varchar(128)	comment '叶酸-微克',
yyss070 varchar(128)	comment '烟酸(尼克酸)-毫克',
yyss071 varchar(128)	comment '胆碱-毫克',
yyss072 varchar(128)	comment '灰分',
yyss073 varchar(128)	comment '碘-微克',
yyss074 varchar(128)	comment '生物素-微克',
yyss075 varchar(128)	comment '血糖指数',
yyss076 varchar(128)	comment '嘌呤含量',
yyss077 varchar(128)	comment '泛酸-毫克',
yyss078 varchar(128)	comment '钙-毫克',
yyss079 varchar(128)	comment '磷-毫克',
yyss080 varchar(128)	comment '钾-毫克',
yyss081 varchar(128)	comment '钠-毫克',
yyss082 varchar(128)	comment '镁-毫克',
yyss083 varchar(128)	comment '铁-毫克',
yyss084 varchar(128)	comment '锰-毫克',
yyss085 varchar(128)	comment '锌-毫克',
yyss086 varchar(128)	comment '硒-微克',
yyss087 varchar(128)	comment '铜-毫克',
primary key (yyss001)
);