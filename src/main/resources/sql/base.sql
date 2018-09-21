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
INSERT INTO `sys_config` VALUES ('site_description', '站点描述', '1', '一个个人网站', null);
INSERT INTO `sys_config` VALUES ('site_domain', '域名', '1', 'www.xiaoshabao.com', null);
INSERT INTO `sys_config` VALUES ('site_keywords', '关键字', '1', '博客，技术', null);
INSERT INTO `sys_config` VALUES ('site_metas', '站点meta', '1', '', null);
INSERT INTO `sys_config` VALUES ('site_name', '网站名', '1', '个人博客', null);

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