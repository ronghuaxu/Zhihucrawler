CREATE TABLE `zhihu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_url` varchar(45) DEFAULT NULL COMMENT '知乎二级url',
  `top_category` int(11) DEFAULT NULL COMMENT '一级话题分类编号',
  `sub_category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28373 DEFAULT CHARSET=utf8;




CREATE TABLE `zhihu_useful` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `upagree` int(11) DEFAULT NULL,
  `questiontitle` varchar(300) DEFAULT NULL,
  `subcategory` varchar(100) DEFAULT NULL,
  `besturl` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61323 DEFAULT CHARSET=utf8;
