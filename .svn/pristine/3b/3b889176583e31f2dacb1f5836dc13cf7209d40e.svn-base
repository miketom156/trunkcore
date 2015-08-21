DROP TABLE `PV_Person_Register`;
CREATE TABLE `PV_Person_Register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `userIp` varchar(30) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `fromDomain` varchar(100) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  `fromUrl` varchar(800) DEFAULT NULL,
  `keyWord` varchar(100) DEFAULT NULL,
  `register` varchar(30) DEFAULT NULL,
  `semRegister` varchar(30) DEFAULT NULL COMMENT 'sem推广参数',
  `targetUrl` varchar(500) DEFAULT NULL,
  `account_from` int(10) DEFAULT NULL COMMENT '注册方式，0-网络注册，20-后台录入，400-第三方绑定，500-手机app，602-微信，603-触屏版',
  PRIMARY KEY (`id`),
  KEY `IX_domain` (`fromDomain`),
  KEY `IX_date` (`credate`),
  KEY `IX_word` (`keyWord`),
  KEY `IX_userid` (`userId`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=2254808 DEFAULT CHARSET=gbk;

