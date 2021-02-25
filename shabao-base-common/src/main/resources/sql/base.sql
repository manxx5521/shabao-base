CREATE TABLE `sys_config` (
  `id` varchar(20) NOT NULL COMMENT '配置id',
  `name` varchar(30) NOT NULL COMMENT '名字',
  `type` int(1) NOT NULL COMMENT '类型 1.字符 2.数字 3.布尔型（Y或N）',
  `value` varchar(20) NOT NULL COMMENT '值',
  `remark` varchar(400) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置表';

INSERT INTO `sys_config` VALUES ('custom.oss.basePath', '基本目录', '1', '/app/data/upload/', '文件存储');
INSERT INTO `sys_config` VALUES ('custom.oss.type', '文件保存方式', '2', '2', '文件存储');
INSERT INTO `sys_config` VALUES ('site.description', '站点描述', '1', '一个个人网站', null);
INSERT INTO `sys_config` VALUES ('site.domain', '域名', '1', 'www.xiaoshabao.com', null);
INSERT INTO `sys_config` VALUES ('site.keywords', '关键字', '1', '博客，技术', null);
INSERT INTO `sys_config` VALUES ('site.metas', '站点meta', '1', '', null);
INSERT INTO `sys_config` VALUES ('site.name', '网站名', '1', '个人博客', null);

CREATE TABLE `sys_file` (
  `file_id` bigint(18) NOT NULL,
  `upload_name` varchar(50) NOT NULL,
  `ext` varchar(10) NOT NULL,
  `save_path` varchar(50) NOT NULL,
  `size` bigint(10) NOT NULL,
  `md5` varchar(32) NOT NULL,
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sys_static` (
  `type_id` varchar(50) NOT NULL COMMENT '类型',
  `module` varchar(10) NOT NULL DEFAULT 'public' COMMENT '模块，默认public所有可查',
  `data_id` varchar(20) NOT NULL COMMENT '数据id',
  `data_name` varchar(50) NOT NULL COMMENT '数据名字',
  `order_no` int(4) DEFAULT NULL COMMENT '排序',
  `used` int(1) NOT NULL DEFAULT '1' COMMENT '是否使用',
  PRIMARY KEY (`type_id`,`data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统枚举表';