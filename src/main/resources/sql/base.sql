CREATE TABLE `sys_config` (
  `id` varchar(20) NOT NULL COMMENT '配置id',
  `name` varchar(30) NOT NULL COMMENT '名字',
  `type` int(1) NOT NULL COMMENT '类型 1.字符 2.数字 3.布尔型（Y或N）',
  `value` varchar(20) NOT NULL COMMENT '值',
  `remark` varchar(30) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置表';