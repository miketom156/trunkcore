DROP TABLE `per_user`;
CREATE TABLE `per_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '帐号ID',
  `account_id` int(10) unsigned DEFAULT NULL COMMENT '通行证账号ID',
  `account_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '帐号类型:如猎头帐号',
  `account` varchar(100) NOT NULL COMMENT '个人用户帐号',
  `password` varchar(100) NOT NULL COMMENT '个人用户密码',
  `login_count` int(10) NOT NULL DEFAULT '0' COMMENT '个人用户的登录次数',
  `refresh_remind` tinyint(2) DEFAULT '0' COMMENT '刷新提醒：0表示超过15天没有更新就提示，1表示永不提示。',
  `fre_date` datetime NOT NULL COMMENT '刷新时间',
  `create_date` datetime NOT NULL COMMENT '个人用户的创建时间',
  `login_date` datetime NOT NULL COMMENT '登录时间',
  `public_settings` tinyint(2) DEFAULT '0' COMMENT '是否对智通公开：0为公开，1为不公开，默认为：0',
  `filter_com_id` varchar(500) DEFAULT NULL COMMENT '屏蔽的企业id和企业名',
  `vip_member` tinyint(2) DEFAULT '0' COMMENT '是否为个人会员 默认为0 ，1为会员',
  `admin_id` int(10) DEFAULT NULL COMMENT '导入用户 录入人ID',
  `ip` varchar(100) DEFAULT NULL COMMENT '存放用户登录时的IP地址',
  `recommend_name` varchar(100) DEFAULT NULL COMMENT '猎头推荐人',
  `recommend_phone` varchar(100) DEFAULT NULL COMMENT '猎头推荐人联系方式',
  `user_name` varchar(100) DEFAULT NULL COMMENT '个人用户简历名称',
  `gender` tinyint(2) DEFAULT NULL COMMENT '性别：1为男，2为女',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `location` int(10) unsigned DEFAULT '0' COMMENT '所在地省/市所对应的 编号',
  `location_town` int(10) unsigned DEFAULT '0' COMMENT '所在地城镇所对应的编号',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `hidden_email` tinyint(2) DEFAULT '0' COMMENT '电子邮箱是否显示：0为显示，1为隐藏',
  `card_type` tinyint(2) DEFAULT NULL COMMENT '证件类型',
  `card_num` varchar(100) DEFAULT NULL COMMENT '证件号码',
  `hometown` int(10) unsigned DEFAULT '0' COMMENT '所在地省/市所对应的编号',
  `hometown_town` int(10) unsigned DEFAULT '0' COMMENT '所在地省/市所对应的编号',
  `phone` varchar(100) DEFAULT NULL COMMENT '固定电话',
  `hidden_phone` tinyint(2) DEFAULT '0' COMMENT '是否显示固定电话：0为显示、1为隐藏',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号码',
  `hidden_mobile` tinyint(2) DEFAULT '0' COMMENT '是否显示手机号码：0为显示、1为隐藏',
  `nation` varchar(100) DEFAULT NULL COMMENT '民族',
  `nation_en` varchar(100) DEFAULT NULL COMMENT '英文民族',
  `marriage` tinyint(2) DEFAULT NULL COMMENT '婚姻状况所对应的编码',
  `stature` smallint(5) DEFAULT NULL COMMENT '身高',
  `weight` smallint(5) DEFAULT NULL COMMENT '体重',
  `zipcode` varchar(100) DEFAULT NULL COMMENT '邮编',
  `mobile_activation` tinyint(2) DEFAULT NULL COMMENT '手机激活',
  `identification` tinyint(2) DEFAULT NULL COMMENT '实名认证',
  `mail_activation` tinyint(2) DEFAULT NULL COMMENT '邮件激活',
  `address` varchar(500) DEFAULT NULL COMMENT '地址,格式为{"prov":"省","city":"市","dist":"区","addr":"地址"}',
  `homepage` varchar(100) DEFAULT NULL COMMENT '个人主页',
  `qq` varchar(100) DEFAULT NULL COMMENT 'QQ',
  `political` tinyint(2) DEFAULT NULL COMMENT '政治面貌',
  `jobyear_type` tinyint(2) DEFAULT NULL COMMENT '工作年限类型',
  `job_state` tinyint(2) DEFAULT '0' COMMENT '求职状态类型',
  `now_salary` tinyint(2) DEFAULT NULL COMMENT '目前薪水',
  `user_name_en` varchar(100) DEFAULT NULL COMMENT '英文名称',
  `address_en` varchar(500) DEFAULT NULL COMMENT '英文地址',
  `file_name` varchar(100) DEFAULT NULL COMMENT '头像的文件名称',
  `file_path` varchar(100) DEFAULT NULL COMMENT '头像的文件路径',
  `res_id` int(10) DEFAULT NULL COMMENT '默认简历ID',
  `res_recommend` tinyint(2) DEFAULT '1' COMMENT ' 0不推荐1推荐',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '账户状态-1=已删除，0=启用，1=禁用',
  `account_from` int(10) DEFAULT NULL COMMENT '注册来源',
  `mobile_place` varchar(128) DEFAULT NULL COMMENT '手机归属地信息JSON字符串',
  `degree` int(2) DEFAULT NULL COMMENT '最高学历',
  `signature` varchar(250) DEFAULT NULL COMMENT '个性签名',
  `coord` varchar(250) DEFAULT NULL COMMENT '用户坐标信息',
  PRIMARY KEY (`id`),
  KEY `account_index` (`account`),
  KEY `mobile_index` (`mobile`),
  KEY `email_index` (`email`),
  KEY `user_name` (`user_name`) USING BTREE,
  KEY `create_date_index` (`create_date`) USING BTREE,
  KEY `login_date_index` (`login_date`) USING BTREE,
  KEY `fre_date_index` (`fre_date`) USING BTREE,
  KEY `admin_id_index` (`admin_id`) USING BTREE,
  KEY `location_index` (`location`) USING BTREE,
  KEY `I_account_from` (`account_from`) USING BTREE,
  KEY `I_birthday` (`birthday`),
  KEY `I_status` (`status`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=2000004503 DEFAULT CHARSET=utf8;

DROP TABLE `per_resume`;
CREATE TABLE `per_resume` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '简历信息ID',
  `account_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '账号类型:如猎头账号',
  `account_id` int(10) NOT NULL DEFAULT '0' COMMENT '个人用户帐号ID',
  `resume_name` varchar(100) DEFAULT NULL COMMENT '简历名称',
  `resume_type` tinyint(2) DEFAULT NULL COMMENT '简历类型',
  `resume_id` int(10) DEFAULT NULL COMMENT '简历ID  中文简历 和 英文简历相互对应',
  `language_type` tinyint(2) DEFAULT '0' COMMENT '语言类型:0为中文，1为英文',
  `user_last_edit_by` varchar(100) DEFAULT NULL COMMENT '用户最后修改人',
  `user_last_edit_date` datetime DEFAULT NULL COMMENT '用户的最后修改时间',
  `admin_last_edit_by` varchar(100) DEFAULT NULL COMMENT '后台管理员最后修改人',
  `admin_last_edit_date` datetime DEFAULT NULL COMMENT '后台管理员最后修改时间',
  `fre_date` datetime DEFAULT NULL COMMENT '简历刷新时间',
  `resume_grade` smallint(5) DEFAULT '0' COMMENT '简历类型：0-普通,1-精英(猎头) 2-模具人才. 101:中高级人才（进查询库）  105:中高级人才(保密级不进查库)',
  `pass_date` datetime DEFAULT NULL COMMENT '审核时间',
  `come_from` int(10) DEFAULT NULL COMMENT '简历来源',
  `have_photo` tinyint(2) DEFAULT '0' COMMENT '是否有照片：0为无，1为有',
  `pass` smallint(5) DEFAULT '0' COMMENT '-12：被后台用户删除，-11：被用户自己删除，-10:自杀式简历，不让登录，-9：封锁,-3：审核不合格，-2：过滤状态，-1：未开通，0:未完善，1：暂时开通，10：已开通',
  `flag` tinyint(2) DEFAULT '0' COMMENT '简历保密　0:完全公开　1:完全保密　2:公开但联系方式保密',
  `read_count` int(10) DEFAULT NULL COMMENT '被企业查看次数  ',
  `res_formwork` tinyint(2) DEFAULT NULL COMMENT '简历模板：0-6 默认为0',
  `perfect_num` double(10,5) DEFAULT NULL COMMENT '简历完整度数',
  `perfect_info` tinyint(2) DEFAULT '0' COMMENT '简历是否完整',
  `education_info` longtext COMMENT '教育经历',
  `train_info` longtext COMMENT '培训信息',
  `work_info` longtext COMMENT '工作经历/社会实践信息',
  `intent_info` longtext COMMENT '求职意向信息',
  `language_info` longtext COMMENT '语言技能信息',
  `certificate_info` longtext COMMENT '证书信息',
  `project_info` longtext COMMENT '项目信息',
  `accessory_info` longtext COMMENT '附件信息',
  `resume_style` varchar(100) NOT NULL DEFAULT 'default' COMMENT '简历模板，默认值为default',
  `display_en_resume` tinyint(2) DEFAULT '0' COMMENT '是否显示英文简历(0:默认不显示,1:显示)',
  `brief` longtext COMMENT '我的亮点与自我描述',
  `skill_info` mediumtext COMMENT '专业技能',
  PRIMARY KEY (`id`),
  KEY `account_id_index` (`account_id`),
  KEY `user_last_edit_date_index` (`user_last_edit_date`) USING BTREE,
  KEY `I_come_from` (`come_from`) USING BTREE,
  KEY `I_pass` (`pass`) USING BTREE,
  KEY `I_language_type` (`language_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7679493 DEFAULT CHARSET=utf8;

DROP TABLE `com_right`;
CREATE TABLE `com_right` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `com_id` int(10) NOT NULL COMMENT '企业id',
  `member_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '会员类型[1-试用会员 2-一月期会员 3-三月期会员 4-半年期会员 5:一年期会员 6:导入企业会员 7:终止企业会员]',
  `other_member` tinyint(2) DEFAULT NULL COMMENT '扩展类型[1-LOGO会员 2-热门推荐会员 11-体验会员 21-点数自定义会员]',
  `valid_date` datetime NOT NULL COMMENT '开通日期',
  `expire_date` datetime NOT NULL COMMENT '截止日期',
  `max_pos_num` int(10) NOT NULL DEFAULT '0' COMMENT '发布职位[-1:不限]',
  `max_resume_num` int(10) NOT NULL DEFAULT '0' COMMENT '查看简历(个/月)',
  `viewing_resume` int(10) NOT NULL DEFAULT '0' COMMENT '当月已用点数',
  `viewed_resume` int(10) NOT NULL DEFAULT '0' COMMENT '已用点数',
  `check_user` varchar(100) NOT NULL DEFAULT '' COMMENT '后台操作人姓名',
  `check_date` datetime DEFAULT NULL COMMENT '后台操作时间',
  `max_msg_num` int(10) NOT NULL DEFAULT '0' COMMENT '可发送短信点数',
  `msging_num` int(10) NOT NULL DEFAULT '0' COMMENT '已发送短信条数',
  `saler_id` int(10) NOT NULL DEFAULT '0' COMMENT '业务员id',
  `money` int(10) NOT NULL DEFAULT '0' COMMENT '购买金额 ',
  `count_num` int(10) NOT NULL DEFAULT '0' COMMENT '(一共)购买点数',
  `use_num` int(10) NOT NULL DEFAULT '0' COMMENT '(一共)使用点数(显示联系方式)',
  `mark` text COMMENT '注备',
  `create_date` datetime DEFAULT NULL COMMENT '后台创建日期',
  `create_user_name` varchar(100) DEFAULT NULL COMMENT '后台创建人姓名',
  `restrict_area` varchar(100) DEFAULT NULL COMMENT '限制区域',
  `account_num` int(10) DEFAULT NULL COMMENT '最大子账号数',
  `enter_career` int(10) DEFAULT '0' COMMENT '是否允许进入事业起点 0：不可以 1：可以',
  PRIMARY KEY (`id`),
  KEY `IX_comId` (`com_id`),
  KEY `IX_memberType` (`member_type`),
  KEY `IX_salerId` (`saler_id`)
) ENGINE=MyISAM AUTO_INCREMENT=346711 DEFAULT CHARSET=utf8;

DROP TABLE `com_position`;
CREATE TABLE `com_position` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `com_id` int(10) NOT NULL COMMENT '公司ID',
  `department_id` int(10) NOT NULL COMMENT '所属部门',
  `com_user_id` int(10) NOT NULL COMMENT '发布职位公司账户ID',
  `pos_type` varchar(100) NOT NULL COMMENT '职位类别,用json方式保存',
  `pos_type_sys` int(11) DEFAULT NULL COMMENT '系统判定职位',
  `pos_name` varchar(500) NOT NULL COMMENT '职位名称',
  `urgent` tinyint(2) DEFAULT NULL COMMENT '是否为急聘，Null=否，1=是',
  `pos_keyword` varchar(500) NOT NULL COMMENT '职位关键字以json保存',
  `property` tinyint(2) NOT NULL DEFAULT '0' COMMENT '职位属性，0=未选择，1=全职，2=兼职，3=不限',
  `recruitment_number` int(10) NOT NULL COMMENT '招聘人数，0=若干',
  `end_date` datetime DEFAULT NULL COMMENT '截止时间',
  `work_location` varchar(100) NOT NULL COMMENT '工作地区,用json方式保存',
  `salary` tinyint(2) NOT NULL DEFAULT '-1' COMMENT '薪资待遇,-1=未选择',
  `negotiable_flag` tinyint(2) DEFAULT NULL COMMENT '是否面议，Null=否，1=是',
  `tao_label` varchar(300) DEFAULT NULL COMMENT '职位诱惑 多个以json array保存',
  `req_gender` tinyint(2) NOT NULL DEFAULT '0' COMMENT '性别要求，0=未选择，1=男，2=女，3=不限',
  `req_degree` int(10) NOT NULL DEFAULT '0' COMMENT '学历要求，0=未选择，',
  `req_age_min` tinyint(2) NOT NULL DEFAULT '0' COMMENT '最小年龄,0=未选择',
  `req_age_max` tinyint(2) NOT NULL DEFAULT '0' COMMENT '最大年龄，0=未选择',
  `req_work_year` tinyint(2) NOT NULL DEFAULT '0' COMMENT '工作经验，0=未选择',
  `req_location` int(10) NOT NULL DEFAULT '0' COMMENT '现所在地，0=未选择',
  `req_language` int(10) NOT NULL DEFAULT '0' COMMENT '语言要求，0=未选择',
  `req_language_level` int(10) DEFAULT NULL COMMENT '语言等级，0=未选择',
  `filter_degree` tinyint(2) DEFAULT '0' COMMENT '学历是否必须符合要求，0=否，1=是',
  `filter_age` tinyint(2) DEFAULT '0' COMMENT '年龄是否必须符合要求，0=否，1=是',
  `filter_work_year` tinyint(2) DEFAULT '0' COMMENT '工作经验是否必须符合要求，0=否，1=是',
  `filter_gender` tinyint(2) DEFAULT '0' COMMENT '性别是否必须符合要求，0=否，1=是',
  `description` mediumtext NOT NULL COMMENT '职位描述',
  `interview_note` mediumtext NOT NULL COMMENT '面试须知',
  `auto_reply` tinyint(2) DEFAULT NULL COMMENT '接收到简历后是否自动回复，NULL=否，1=是',
  `school_flag` tinyint(2) DEFAULT NULL COMMENT '是否为校园职位，Null=否，1=是',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '修改日期',
  `refresh_date` datetime NOT NULL COMMENT '职位刷新时间',
  `pos_status` tinyint(2) NOT NULL DEFAULT '-1' COMMENT '职位标识，9=正在招聘（已审核），5=正在招聘（未审核），1=待完善，0=待审核，-1=停止招聘，-5=过期，-15=超时屏蔽，-19=未通过审核，-99=删除',
  `auditor_id` int(10) DEFAULT NULL COMMENT '审核人id',
  `audit_date` datetime DEFAULT NULL COMMENT '审核日期',
  `del_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否已删除，-1=已删除',
  `score` decimal(10,2) NOT NULL COMMENT '职位得分',
  `view_count` int(10) NOT NULL DEFAULT '0' COMMENT '职位被查看次数',
  `resume_count` int(10) NOT NULL DEFAULT '0' COMMENT '收到的简历数',
  `view_count_recent` int(11) DEFAULT '0' COMMENT '最近3天职位被查看次数',
  `resume_count_recent` int(11) DEFAULT '0' COMMENT '最近3天收到的简历数',
  `resume_unread_count` int(10) NOT NULL DEFAULT '0' COMMENT '未读简历数',
  `contact_person` varchar(100) DEFAULT NULL COMMENT '联系人',
  `gender` tinyint(2) DEFAULT NULL COMMENT '性别，0=未知，1=先生，2=女士',
  `contact_phone` varchar(100) DEFAULT NULL COMMENT '联系电话',
  `hide_phone` tinyint(2) DEFAULT '0' COMMENT '1为不公开，其他为公开',
  `mobile` varchar(100) DEFAULT '' COMMENT '手机号',
  `mobile_alert` tinyint(2) DEFAULT '0' COMMENT '收到简历提醒，1提醒',
  `hide_mobile` tinyint(2) DEFAULT '1' COMMENT '1:不公开,其它为公开',
  `fax` varchar(100) DEFAULT NULL COMMENT '传真',
  `hide_fax` tinyint(2) DEFAULT '0' COMMENT '1为不公开传真，其他为公开',
  `email` varchar(500) DEFAULT NULL COMMENT '多个邮箱用, 用json格式存储',
  `hide_email` tinyint(2) DEFAULT NULL COMMENT '1为不公开，其他为公开',
  `email_format` tinyint(2) DEFAULT '0' COMMENT '0简体 1繁体',
  `address` varchar(500) DEFAULT NULL COMMENT '通讯地址',
  `interview_address` varchar(500) DEFAULT NULL COMMENT '面试地址',
  `bus_line` varchar(500) DEFAULT NULL COMMENT '乘车路线',
  `near_station` varchar(100) DEFAULT NULL COMMENT '附近站点',
  `zip_code` varchar(100) DEFAULT NULL COMMENT '编邮',
  `website` varchar(100) DEFAULT NULL COMMENT '企业网址',
  `lat` double(10,5) DEFAULT NULL COMMENT '纬度',
  `lng` double(10,5) DEFAULT NULL COMMENT '经度',
  `reject_email` tinyint(2) DEFAULT NULL COMMENT ' 1为拒收邮件，其他为收邮件',
  `accept_resume_type` tinyint(2) unsigned DEFAULT '0' COMMENT '-1:所有(包含名片)  0:正式简历(默认) 1：长沙简历 2：名片',
  `max_salary` int(11) DEFAULT NULL COMMENT '最高薪资待遇  ',
  `min_salary` int(11) DEFAULT NULL COMMENT '最低薪资待遇  ',
  `filter_res` tinyint(4) DEFAULT '0' COMMENT '是否开启简历过滤，0=否，1=是',
  `contact_id` int(11) DEFAULT NULL COMMENT '联系人ID',
  `irc_pos_id` int(11) DEFAULT NULL COMMENT '招聘会职位ID  ',
  `irc_product_id` int(11) DEFAULT NULL COMMENT '招聘会产品ID',
  PRIMARY KEY (`id`),
  KEY `com_id_index` (`com_id`),
  KEY `update_date_index` (`update_date`) USING BTREE,
  KEY `pos_status_index` (`pos_status`) USING BTREE,
  KEY `I_create_date` (`create_date`)
) ENGINE=MyISAM AUTO_INCREMENT=7797568 DEFAULT CHARSET=utf8 COMMENT='企业职位信息表';

DROP TABLE `com_contact`;
CREATE TABLE `com_contact` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `com_id` int(10) NOT NULL,
  `contact_person` varchar(100) DEFAULT NULL COMMENT '联系人',
  `gender` tinyint(2) DEFAULT NULL COMMENT '0未知 1先生 2女士',
  `contact_phone` varchar(100) DEFAULT NULL COMMENT '联系电话',
  `hide_phone` tinyint(2) DEFAULT '0' COMMENT '1为不公开，其他为公开',
  `mobile` varchar(20) DEFAULT '' COMMENT '手机号',
  `mobile_alert` tinyint(2) DEFAULT '0' COMMENT '收到简历提醒，1提醒',
  `hide_mobile` tinyint(2) DEFAULT '1' COMMENT '是否显示手机:1为不公开，其他为公开',
  `fax` varchar(100) DEFAULT NULL COMMENT '传真',
  `hide_fax` tinyint(2) DEFAULT NULL COMMENT '1为不公开传真，其他为公开',
  `email` varchar(500) DEFAULT NULL COMMENT '多个邮箱用, 用json array格式存储',
  `hide_email` tinyint(2) DEFAULT NULL COMMENT '1为不公开，其他为公开',
  `email_format` tinyint(2) DEFAULT '0' COMMENT '0简体 1繁体',
  `address` varchar(500) DEFAULT NULL COMMENT '通讯地址，格式为{"prov":"省","city":"市","dist":"区","addr":"地址"}',
  `bus_line` varchar(500) DEFAULT NULL COMMENT '乘车路线',
  `near_station` varchar(100) DEFAULT NULL COMMENT '附近站点',
  `zip_code` varchar(100) DEFAULT NULL COMMENT '编邮',
  `website` varchar(100) DEFAULT NULL COMMENT '企业网址',
  `lat` double(10,6) DEFAULT NULL COMMENT '经度',
  `lng` double(10,6) DEFAULT NULL COMMENT '纬度',
  `default_flag` tinyint(2) DEFAULT '0' COMMENT '1为默认联系方式',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `interview_address` varchar(500) DEFAULT NULL COMMENT '面试地址，格式为{"prov":"省","city":"市","dist":"区","addr":"地址"}',
  `del_status` tinyint(2) DEFAULT '0' COMMENT '-1删除',
  `reject_email` tinyint(2) DEFAULT NULL COMMENT ' 1为拒收邮件，其他为收邮件',
  PRIMARY KEY (`id`),
  KEY `i_com_id` (`com_id`)
) ENGINE=MyISAM AUTO_INCREMENT=517936 DEFAULT CHARSET=utf8;

DROP TABLE `com_info`;
CREATE TABLE `com_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `com_name` varchar(100) NOT NULL DEFAULT '' COMMENT '企业名称',
  `business_licence` varchar(100) DEFAULT NULL COMMENT '营业执照',
  `industry` tinyint(2) DEFAULT NULL COMMENT '企业所属行业',
  `property` tinyint(2) DEFAULT NULL COMMENT '企业性质',
  `location` int(10) unsigned DEFAULT '0' COMMENT '所在地区对应编号',
  `found_date` datetime DEFAULT NULL COMMENT '企业成立日期',
  `register_fund` tinyint(2) DEFAULT NULL COMMENT '注册资金',
  `employee_number` tinyint(2) DEFAULT NULL COMMENT '员工数目',
  `company_introduction` text COMMENT '企业简介',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `update_date` datetime NOT NULL COMMENT '修改日期',
  `last_editor` varchar(100) DEFAULT NULL COMMENT '修改人',
  `filter_per_id` text COMMENT '屏蔽个人id',
  `need_hunter` tinyint(2) DEFAULT NULL COMMENT '是否需要猎头服务',
  `saler_id` int(10) DEFAULT '0' COMMENT '业务员ID,默认为0,分配业务员和审核开通都操作此字段',
  `saler_name` varchar(100) DEFAULT NULL COMMENT '业务员姓名',
  `saler_read` tinyint(2) DEFAULT '0' COMMENT '分配业务员之后.他是否看过此公司.默认为0:表示没看过 1:表示看过',
  `illegality` varchar(500) DEFAULT NULL COMMENT '非法企业，记录非法企业备注',
  `crm_com_id` varchar(100) DEFAULT NULL COMMENT '对应CRM系统的企业ID号',
  `logo_path` varchar(100) DEFAULT NULL COMMENT '企业logo图片路径',
  `logo_name` varchar(100) DEFAULT NULL COMMENT '企业logo图片名称',
  `status` tinyint(2) DEFAULT '0' COMMENT '0:新注册 1:已开通 2:公共库企业 -1:被锁定 -2:作废 -3:非法企业 -4:已删除',
  `com_flag` tinyint(2) DEFAULT NULL COMMENT '企业标识 1为正式资料，其他为导入企业',
  `terminate` tinyint(2) DEFAULT '0' COMMENT '是否屏蔽[0-否 1-是]，后台使用，原样保留',
  `register_ip` varchar(100) DEFAULT NULL COMMENT '保留',
  `tao_label` varchar(300) DEFAULT NULL COMMENT '职位诱惑多个以json array保存',
  `weixin` varchar(100) DEFAULT NULL COMMENT '// 企业微信号 //',
  `weibo` varchar(100) DEFAULT NULL COMMENT ' //企业新浪微博号//',
  `home_page` varchar(100) DEFAULT NULL COMMENT '企业网站网址',
  PRIMARY KEY (`id`),
  KEY `IX_comFlag_status` (`com_flag`,`status`),
  KEY `IX_salerId` (`saler_id`),
  KEY `IX_location` (`location`),
  KEY `I_comname` (`com_name`)
) ENGINE=MyISAM AUTO_INCREMENT=1312847 DEFAULT CHARSET=utf8;

-- DROP TABLE `account_exp`;
-- CREATE TABLE `account_exp` (
--   `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
--   `user_id` int(10) unsigned DEFAULT NULL COMMENT '用户id',
--   `update_type` tinyint(2) DEFAULT NULL COMMENT '类型（0，1，2，3，4，5|手机，邮箱，住址(身份城市地区)，地址（街道号码），婚姻状态。）',
--   `update_content` varchar(50) DEFAULT NULL COMMENT '变更类容（以json的形式进行存储如{ before:xxxxxx,now:xxxxxxx}）',
--   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- DROP TABLE `account_info`;
-- CREATE TABLE `account_info` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `user_telphone` varchar(12) DEFAULT NULL COMMENT '用户手机账号',
--   `user_email` varchar(30) DEFAULT NULL COMMENT '用户邮箱',
--   `user_account` varchar(30) DEFAULT NULL COMMENT '个人用户的账号',
--   `user_password` varchar(40) DEFAULT NULL COMMENT '个人用户的密码（对于手机和邮箱等都是同一个密码）',
--   `registered_time` timestamp NULL DEFAULT NULL COMMENT '个人用户注册账号的时间',
--   `account_from` int(10) DEFAULT NULL COMMENT '账号信息的来源',
--   `user_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '用于判断用户的类型（0表示个人用户，1表示企业用户）。企业用户也可以是个人用户',
--   `account_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '用于判断账号的状态（0表示正常，1表示停用，2表示冻结）',
--   `last_login_time` datetime DEFAULT NULL COMMENT '用户最后一次登录的时间',
--   `ip` varchar(16) DEFAULT NULL COMMENT '最后登录ip',
--   `user_login_number` smallint(6) DEFAULT NULL COMMENT '记录用户登录的总次数',
--   `md5_string` varchar(6) DEFAULT NULL COMMENT '随机字符传用于进行md5加密',
--   `admin_id` int(10) DEFAULT NULL COMMENT '后台录入用户id',
--   `role_type` tinyint(2) DEFAULT '0' COMMENT '用户权限类型，0:没有父账号，1表示有父账号',
--   PRIMARY KEY (`id`),
--   KEY `IX_user_account` (`user_account`),
--   KEY `IX_user_telephone` (`user_telphone`),
--   KEY `IX_account_from` (`account_from`),
--   KEY `IX_user_email` (`user_email`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- DROP TABLE `act_accept_record`;
-- CREATE TABLE `act_accept_record` (
--   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `stk_id` int(11) DEFAULT NULL COMMENT '活动ID',
--   `stk_title` varchar(255) DEFAULT NULL COMMENT '活动主题',
--   `winner_record_id` int(11) DEFAULT NULL COMMENT '中奖记录ID',
--   `winner_record_no` varchar(255) DEFAULT NULL COMMENT '中奖序号',
--   `winner_user_id` int(11) DEFAULT NULL COMMENT '中奖用户ID',
--   `winner_user_type` tinyint(4) DEFAULT NULL COMMENT '中奖用户类型',
--   `winner_user_name` varchar(255) DEFAULT NULL COMMENT '中奖用户姓名',
--   `winner_record_name` varchar(255) DEFAULT NULL COMMENT '中奖奖品名称',
--   `winner_record_lev` tinyint(4) DEFAULT NULL COMMENT '奖品等级',
--   `winner_record_type` tinyint(4) DEFAULT NULL COMMENT '奖品类型',
--   `accept_name` varchar(255) DEFAULT NULL COMMENT '兑奖者姓名，以方便邮寄礼物',
--   `accept_address` varchar(255) DEFAULT NULL COMMENT '兑奖者地址，以方便邮寄礼物',
--   `accept_post` varchar(6) DEFAULT NULL COMMENT '兑奖者邮编，以方便邮寄礼物',
--   `accept_tel` varchar(20) DEFAULT NULL COMMENT '兑奖者联系电话，以方便邮寄礼物',
--   `accept_account` varchar(255) DEFAULT NULL COMMENT '兑奖账号，Q币，填写QQ号；充值卡，填写手机号;微信红包，填写微信号;....',
--   `accept_time` datetime DEFAULT NULL COMMENT '兑奖时间',
--   `expiry_date` datetime DEFAULT NULL COMMENT '奖品逾期时间',
--   `sys_user_id` int(11) DEFAULT '0' COMMENT '发放者ID',
--   `sys_user_type` tinyint(4) DEFAULT NULL,
--   `sys_user_name` varchar(255) DEFAULT NULL COMMENT '发放者姓名',
--   `status` tinyint(4) DEFAULT NULL,
--   `create_date` datetime DEFAULT NULL COMMENT '创建时间',
--   `update_date` datetime DEFAULT NULL COMMENT '修改时间',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=224 DEFAULT CHARSET=utf8;

-- DROP TABLE `act_award`;
-- CREATE TABLE `act_award` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `stk_id` int(10) DEFAULT NULL COMMENT '本次活动ID',
--   `name` varchar(200) DEFAULT NULL COMMENT '奖品名称',
--   `lev` tinyint(4) DEFAULT NULL COMMENT '奖品级别 0：特等奖，1：一等奖,...',
--   `qty` int(11) DEFAULT NULL COMMENT '奖品数量',
--   `used_qty` int(11) DEFAULT NULL,
--   `type` tinyint(4) DEFAULT NULL COMMENT '奖品类型 0：实物奖（中奖类别）1：虚拟奖（未中奖类别)',
--   `active_date` datetime DEFAULT NULL COMMENT '有效日期',
--   `create_date` datetime DEFAULT NULL COMMENT '创建时间',
--   `update_date` datetime DEFAULT NULL COMMENT '修改时间',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=144 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `act_message_board`;
-- CREATE TABLE `act_message_board` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `content` varchar(255) DEFAULT NULL COMMENT '内容',
--   `user_type` tinyint(4) DEFAULT NULL COMMENT '用户类型',
--   `user_id` int(10) DEFAULT NULL COMMENT '用户ID',
--   `status` tinyint(4) DEFAULT NULL COMMENT '状态',
--   `create_date` timestamp NULL DEFAULT NULL COMMENT '创建日期',
--   `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
--   `act_id` int(10) NOT NULL COMMENT '活动ID',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=28954 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
--
-- DROP TABLE `act_prize`;
-- CREATE TABLE `act_prize` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `name` varchar(255) DEFAULT NULL,
--   `type` tinyint(4) DEFAULT NULL,
--   `create_date` datetime DEFAULT NULL,
--   `update_date` datetime DEFAULT NULL,
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `act_sign_up`;
-- CREATE TABLE `act_sign_up` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键\r\n',
--   `act_id` int(10) NOT NULL COMMENT '对应活动的id',
--   `act_name` varchar(100) DEFAULT NULL COMMENT '活动名称',
--   `per_user_id` int(10) NOT NULL COMMENT '用户id',
--   `per_user_name` varchar(100) DEFAULT NULL COMMENT '用户姓名',
--   `mobile` varchar(100) DEFAULT NULL COMMENT '用户手机号码',
--   `email` varchar(100) DEFAULT NULL COMMENT '用户注册的email地址',
--   `create_date` datetime DEFAULT NULL COMMENT '创建日期',
--   PRIMARY KEY (`id`),
--   KEY `I_act_name` (`act_name`),
--   KEY `I_act_id` (`act_id`),
--   KEY `I_user_name` (`per_user_name`),
--   KEY `I_email` (`email`),
--   KEY `I_user_id` (`per_user_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=189 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `act_survey`;
-- CREATE TABLE `act_survey` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `name` varchar(100) DEFAULT NULL COMMENT '问卷名',
--   `template` varchar(500) DEFAULT NULL COMMENT '模板名称',
--   `reply_num` int(10) DEFAULT '0' COMMENT '总回复人数',
--   `editor_id` int(10) DEFAULT NULL,
--   `editor` varchar(100) DEFAULT NULL,
--   `com_from` varchar(10) DEFAULT 'per' COMMENT '调查对象per:com',
--   `category` tinyint(2) DEFAULT '1' COMMENT '1:问卷 2：投票',
--   `begin_date` datetime DEFAULT NULL COMMENT '开始时间',
--   `end_date` datetime DEFAULT NULL COMMENT '结束时间',
--   `create_date` datetime DEFAULT NULL,
--   `update_date` datetime DEFAULT NULL,
--   PRIMARY KEY (`id`),
--   KEY `I_survey_name` (`name`) USING HASH,
--   KEY `I_create_date` (`create_date`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `act_survey_answer`;
-- CREATE TABLE `act_survey_answer` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `survey_id` int(10) NOT NULL DEFAULT '0' COMMENT '所属问卷ID',
--   `survey_name` varchar(100) DEFAULT NULL,
--   `question_id` int(10) NOT NULL DEFAULT '0',
--   `per_user_id` int(10) DEFAULT NULL,
--   `per_user_name` varchar(100) DEFAULT NULL,
--   `com_id` int(10) DEFAULT NULL,
--   `com_name` varchar(100) DEFAULT NULL,
--   `answer_flag` set('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z') DEFAULT NULL,
--   `suggestion` varchar(100) DEFAULT NULL COMMENT '建议',
--   `ip` varchar(100) DEFAULT NULL,
--   `votes` int(10) DEFAULT '0' COMMENT '投票票数',
--   `mobile` varchar(100) DEFAULT NULL COMMENT '反馈人的手机',
--   `email` varchar(100) DEFAULT NULL COMMENT '反馈人的email',
--   `create_date` datetime DEFAULT NULL,
--   `update_date` datetime DEFAULT NULL,
--   `location` int(10) unsigned DEFAULT '0' COMMENT '地区对应编号',
--   PRIMARY KEY (`id`),
--   KEY `I_survey_id` (`survey_id`),
--   KEY `I_survey_name` (`survey_name`),
--   KEY `I_question_id` (`question_id`) USING BTREE,
--   KEY `I_per_user_id` (`per_user_id`),
--   KEY `I_answer_flag` (`answer_flag`),
--   KEY `I_create_date` (`create_date`),
--   KEY `I_ip` (`ip`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=1529649 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `act_survey_question`;
-- CREATE TABLE `act_survey_question` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '问题的ID',
--   `survey_id` int(10) NOT NULL DEFAULT '0' COMMENT '所属问卷ID',
--   `question` varchar(100) DEFAULT NULL COMMENT '问题',
--   `answer` varchar(1000) DEFAULT '[]' COMMENT '[{"key":"A","name":"喜欢","button":"radio","inputBox":0}]',
--   `editor_id` int(10) DEFAULT NULL,
--   `editor` varchar(100) DEFAULT NULL,
--   `current_votes` int(10) DEFAULT '0' COMMENT '当前票数',
--   `init_votes` int(10) DEFAULT '0' COMMENT '初始票数',
--   `create_date` datetime DEFAULT NULL,
--   `update_date` datetime DEFAULT NULL,
--   PRIMARY KEY (`id`),
--   KEY `I_survey_id` (`survey_id`),
--   KEY `I_question` (`question`),
--   KEY `I_create_date` (`create_date`),
--   KEY `I_editor` (`editor`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=325 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `act_sweepstakes`;
-- CREATE TABLE `act_sweepstakes` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `title` varchar(255) DEFAULT NULL COMMENT '活动主题',
--   `start_date` timestamp NULL DEFAULT NULL COMMENT '活动开始日期',
--   `expiry_date` timestamp NULL DEFAULT NULL COMMENT '活动结束日期',
--   `award_expiry_date` timestamp NULL DEFAULT NULL,
--   `begin_time` timestamp NULL DEFAULT NULL COMMENT '抽奖开始时间,每天的几点开始',
--   `end_time` timestamp NULL DEFAULT NULL COMMENT '抽奖结束时间,每天的几点结束',
--   `note` mediumtext COMMENT '活动规则说明',
--   `status` tinyint(4) DEFAULT NULL COMMENT '活动状态 0：待审核1：生效2：已结束',
--   `pool_num` int(11) DEFAULT NULL COMMENT '奖池数量',
--   `used_num` int(11) DEFAULT NULL COMMENT '已抽奖次数',
--   `chk_user_id` int(11) DEFAULT NULL COMMENT '审核者ID',
--   `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
--   `update_date` timestamp NULL DEFAULT NULL,
--   `empty_name` varchar(255) DEFAULT NULL,
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `act_user_chance`;
-- CREATE TABLE `act_user_chance` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `stk_id` int(10) DEFAULT NULL COMMENT '本次活动ID',
--   `user_id` int(10) DEFAULT NULL COMMENT '用户ID',
--   `user_type` tinyint(4) DEFAULT NULL COMMENT '用户类型 0:个人用户 1：企业用户',
--   `chance_num` int(11) DEFAULT NULL COMMENT '机会数量 不得大于最大机会数maxNum',
--   `used_num` int(11) DEFAULT NULL COMMENT '已使用机会数量',
--   `max_num` int(11) DEFAULT NULL,
--   `create_date` datetime DEFAULT NULL COMMENT '创建时间',
--   `update_date` datetime DEFAULT NULL COMMENT '修改时间',
--   `chance_rule` varchar(1000) DEFAULT NULL,
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `act_webchat_wind`;
-- CREATE TABLE `act_webchat_wind` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `per_user_id` int(10) NOT NULL COMMENT '个人用户id',
--   `user_name` varchar(100) NOT NULL COMMENT '姓名',
--   `gender` tinyint(2) NOT NULL COMMENT '性别',
--   `email` varchar(100) NOT NULL COMMENT '邮箱',
--   `mobile` varchar(100) NOT NULL COMMENT '手机',
--   `file_path` varchar(100) NOT NULL COMMENT '照片文件路径',
--   `file_name` varchar(100) NOT NULL COMMENT '照片文件名称',
--   `create_date` datetime NOT NULL COMMENT '报名日期',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='微疯活动报名表';
--
-- DROP TABLE `act_winner_record`;
-- CREATE TABLE `act_winner_record` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `stk_id` int(10) DEFAULT NULL COMMENT '本次活动ID',
--   `award_id` int(10) DEFAULT NULL COMMENT '活动奖品的ID',
--   `award_no` varchar(255) DEFAULT NULL COMMENT '中奖序号，按中奖时间生成',
--   `award_name` varchar(255) DEFAULT NULL COMMENT '奖品名称',
--   `award_type` tinyint(4) DEFAULT NULL,
--   `user_id` int(10) DEFAULT NULL COMMENT '用户ID',
--   `user_account` varchar(255) DEFAULT NULL COMMENT '中奖用户账号',
--   `user_type` tinyint(4) DEFAULT NULL COMMENT '用户类型 0:个人用户 1：企业用户',
--   `award_lev` tinyint(4) DEFAULT NULL COMMENT '奖品级别 0：特等奖，1：一等奖,...',
--   `status` tinyint(4) DEFAULT NULL COMMENT '奖品是否兑换',
--   `exchange_time` timestamp NULL DEFAULT NULL,
--   `expiry_date` timestamp NULL DEFAULT NULL,
--   `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
--   `update_date` timestamp NULL DEFAULT NULL COMMENT '修改时间',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- DROP TABLE `com_department`;
-- CREATE TABLE `com_department` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `com_id` int(10) NOT NULL COMMENT '公司ID',
--   `contact_id` int(10) DEFAULT NULL COMMENT '联系人ID',
--   `dept_name` varchar(100) NOT NULL COMMENT '部门名称',
--   `sort` tinyint(2) DEFAULT '0' COMMENT '排序号',
--   `type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '类型，0=部门，1=分子公司',
--   `del_status` tinyint(2) DEFAULT '0' COMMENT '是否为删除状态，-1=已删除',
--   `update_date` datetime DEFAULT NULL COMMENT '修改日期',
--   `create_date` datetime NOT NULL COMMENT '创建日期',
--   PRIMARY KEY (`id`),
--   KEY `I_com_id` (`com_id`),
--   KEY `I_type` (`type`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=179955 DEFAULT CHARSET=utf8 COMMENT='企业部门信息表';
--
-- DROP TABLE `com_extra`;
-- CREATE TABLE `com_extra` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `com_id` int(10) NOT NULL COMMENT '企业ID',
--   `column_name` varchar(20) NOT NULL COMMENT '栏目名称',
--   `column_content` varchar(500) NOT NULL COMMENT '栏目内容',
--   `photo` mediumtext,
--   `create_date` datetime NOT NULL COMMENT '修改时间',
--   `update_date` datetime DEFAULT NULL,
--   `del_status` tinyint(2) DEFAULT NULL COMMENT '-1删除',
--   PRIMARY KEY (`id`),
--   KEY `i_com_id` (`com_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=16825 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `com_login_history`;
-- CREATE TABLE `com_login_history` (
--   `id` bigint(15) NOT NULL AUTO_INCREMENT,
--   `com_id` int(10) NOT NULL COMMENT '登录的企业ID',
--   `com_user_id` int(10) DEFAULT NULL COMMENT '登录的企业账号ID',
--   `com_user_account` varchar(100) DEFAULT NULL COMMENT '登录的企业账号',
--   `ip` varchar(100) DEFAULT NULL COMMENT '登录的IP',
--   `area` varchar(100) DEFAULT NULL COMMENT '登录IP解释出来的地址',
--   `cre_time` datetime NOT NULL COMMENT '登录时间',
--   `sys_user_id` int(10) DEFAULT NULL COMMENT '后台登录的用户ID',
--   `sys_user_account` varchar(100) DEFAULT NULL COMMENT '后台登录的账号',
--   `sys_user_name` varchar(100) DEFAULT NULL COMMENT '后台登录的用户姓名',
--   `login_type` varchar(100) NOT NULL COMMENT '登录类型 【web, sys, crm】',
--   PRIMARY KEY (`id`),
--   KEY `I_comid` (`com_id`),
--   KEY `I_loginType` (`login_type`),
--   KEY `I_creTime` (`cre_time`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=2358 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `com_position_template`;
-- CREATE TABLE `com_position_template` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `com_id` int(10) NOT NULL COMMENT '公司ID',
--   `department_id` int(10) NOT NULL COMMENT '所属部门',
--   `com_user_id` int(10) NOT NULL COMMENT '创建模板账户ID',
--   `pos_type` varchar(100) NOT NULL COMMENT '职位类别,用json方式保存',
--   `pos_name` varchar(100) NOT NULL COMMENT '职位名称',
--   `pos_keyword` varchar(500) NOT NULL COMMENT '职位关键字以json保存',
--   `property` tinyint(2) NOT NULL DEFAULT '0' COMMENT '职位属性，0=未选择，1=全职，2=兼职，3=不限',
--   `recruitment_number` int(10) NOT NULL COMMENT '招聘人数，0=若干',
--   `work_location` varchar(100) NOT NULL COMMENT '工作地区,用json方式保存',
--   `salary` decimal(10,2) NOT NULL COMMENT '薪资待遇',
--   `negotiable_flag` tinyint(2) DEFAULT NULL COMMENT '是否面议，Null=否，1=是',
--   `tao_label` varchar(300) DEFAULT NULL COMMENT '职位诱惑 多个以json array保存',
--   `req_gender` tinyint(2) NOT NULL DEFAULT '0' COMMENT '性别要求，0=未选择，1=男，2=女，3=不限',
--   `req_degree` tinyint(2) NOT NULL DEFAULT '0' COMMENT '学历要求，0=未选择，',
--   `req_age_min` tinyint(2) NOT NULL DEFAULT '0' COMMENT '最小年龄,0=未选择',
--   `req_age_max` tinyint(2) NOT NULL DEFAULT '0' COMMENT '最大年龄，0=未选择',
--   `req_work_year` tinyint(2) NOT NULL DEFAULT '0' COMMENT '工作经验，0=未选择',
--   `req_location` int(10) NOT NULL DEFAULT '0' COMMENT '现所在地，0=未选择',
--   `req_language` int(10) DEFAULT NULL,
--   `req_language_level` int(10) NOT NULL DEFAULT '0' COMMENT '语言等级，0=未选择',
--   `filter_degree` tinyint(2) DEFAULT NULL COMMENT '学历是否必须符合要求，Null=否，1=是',
--   `filter_age` tinyint(2) DEFAULT NULL COMMENT '年龄是否必须符合要求，Null=否，1=是',
--   `filter_work_year` tinyint(2) DEFAULT NULL COMMENT '工作经验是否必须符合要求，Null=否，1=是',
--   `filter_gender` tinyint(2) DEFAULT NULL COMMENT '性别是否必须符合要求，Null=否，1=是',
--   `description` mediumtext NOT NULL COMMENT '职位描述',
--   `create_date` datetime NOT NULL COMMENT '创建日期',
--   `update_date` datetime DEFAULT NULL COMMENT '修改日期',
--   `interview_note` mediumtext COMMENT '面试须知',
--   `accept_resume_type` tinyint(2) DEFAULT '0' COMMENT '-1:所有(包含名片)  0:正式简历(默认) 1：长沙简历 2：名片',
--   `max_salary` int(11) DEFAULT NULL COMMENT '最高薪资待遇  ',
--   `min_salary` int(11) DEFAULT NULL COMMENT '最低薪资待遇  ',
--   `filter_res` tinyint(4) DEFAULT '0' COMMENT '是否开启简历过滤，0=否，1=是',
--   `contact_id` int(11) DEFAULT NULL COMMENT '联系人ID',
--   `urgent` tinyint(4) DEFAULT NULL COMMENT '是否高薪职位，0=否，1=是',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=92 DEFAULT CHARSET=utf8 COMMENT='企业职位模板信息表';
--
-- DROP TABLE `com_resume_buy_history`;
-- CREATE TABLE `com_resume_buy_history` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `com_id` int(10) DEFAULT NULL,
--   `com_name` varchar(100) DEFAULT NULL COMMENT '企业名称',
--   `com_user_id` int(10) DEFAULT NULL COMMENT '企业用户ID',
--   `per_user_id` int(10) DEFAULT NULL COMMENT '个人ID',
--   `per_user_name` varchar(100) DEFAULT NULL COMMENT '个人名称',
--   `resume_id` int(10) DEFAULT NULL COMMENT '简历ID',
--   `gender` varchar(10) DEFAULT NULL,
--   `age` tinyint(2) DEFAULT NULL,
--   `degree` varchar(10) DEFAULT NULL,
--   `jobyear` tinyint(2) DEFAULT NULL,
--   `school_name` varchar(100) DEFAULT NULL COMMENT '学校',
--   `speciality` varchar(100) DEFAULT NULL COMMENT '专业',
--   `ip` varchar(100) DEFAULT '',
--   `create_date` datetime DEFAULT NULL,
--   PRIMARY KEY (`id`),
--   KEY `comid_idx` (`com_id`) USING BTREE,
--   KEY `comuserid_idx` (`com_user_id`) USING BTREE,
--   KEY `peruserid_idx` (`per_user_id`) USING BTREE,
--   KEY `perusername_idx` (`per_user_name`) USING BTREE,
--   KEY `createdate_idx` (`create_date`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=343 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `com_return_points`;
-- CREATE TABLE `com_return_points` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `com_id` int(10) NOT NULL,
--   `com_name` varchar(100) DEFAULT NULL COMMENT '公司名',
--   `sales_id` int(10) DEFAULT NULL,
--   `sales_name` varchar(100) DEFAULT NULL COMMENT '业务员',
--   `applicant_id` int(10) NOT NULL,
--   `applicant` varchar(100) DEFAULT NULL COMMENT '申请人',
--   `request_points` int(10) NOT NULL DEFAULT '0' COMMENT '申请返点点数',
--   `points` int(10) DEFAULT NULL COMMENT '执行的返点点数',
--   `com_old_points` int(10) DEFAULT NULL COMMENT '返点前的点数',
--   `com_new_points` int(10) DEFAULT NULL COMMENT '返点后点数',
--   `feedback_id` int(11) NOT NULL COMMENT '投诉与反馈的相关ID',
--   `reason` varchar(500) DEFAULT NULL COMMENT '返点原因',
--   `option_status` tinyint(2) DEFAULT '0' COMMENT '操作状态 -1:不通过 0:待审核 1:已返点',
--   `audit_id` int(10) DEFAULT NULL COMMENT '审核人ID',
--   `auditor` varchar(100) DEFAULT NULL COMMENT '审核人',
--   `audit_desc` varchar(500) DEFAULT '' COMMENT '审核意见描述',
--   `create_date` datetime DEFAULT NULL,
--   `update_date` datetime DEFAULT NULL,
--   PRIMARY KEY (`id`),
--   KEY `I_com_name` (`com_name`),
--   KEY `I_applicant` (`applicant`),
--   KEY `I_sales_name` (`sales_name`),
--   KEY `I_feedback_id` (`feedback_id`),
--   KEY `I_create_date` (`create_date`),
--   KEY `I_update_date` (`update_date`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `com_searcher`;
-- CREATE TABLE `com_searcher` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `com_user_id` int(10) NOT NULL COMMENT '对应企业用户表的ID',
--   `name` varchar(100) NOT NULL COMMENT '搜索器命名，对于同一个企业来说此字段不能重复',
--   `subscribe` tinyint(2) NOT NULL COMMENT '是否订阅的标识，1=订阅，0=不订阅',
--   `content` mediumtext NOT NULL COMMENT 'JSON格式的搜索器条件内容',
--   `email` varchar(100) DEFAULT NULL COMMENT '订阅到的Email地址，可空，只有当subscribe=1的时候才有用',
--   `url` varchar(500) NOT NULL COMMENT '搜索器的地址',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=178 DEFAULT CHARSET=utf8;

-- DROP TABLE `com_subscription`;
-- CREATE TABLE `com_subscription` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `main_type` varchar(50) DEFAULT '' COMMENT '主类型，如：邮件订阅、手机短信设置',
--   `sub_type` varchar(50) DEFAULT '' COMMENT '子类型，如：邮件订阅的网站资讯',
--   `task_id` int(5) NOT NULL DEFAULT '0' COMMENT '订阅的类别ID',
--   `com_user_id` int(10) DEFAULT NULL,
--   `com_id` int(10) DEFAULT NULL,
--   `email` varchar(100) DEFAULT NULL,
--   `mobile` varchar(100) DEFAULT NULL,
--   `subscribe_status` tinyint(2) DEFAULT '1' COMMENT '是否订阅  0：退订(未订阅)  1：订阅',
--   `create_date` datetime DEFAULT NULL,
--   `update_date` datetime DEFAULT NULL,
--   PRIMARY KEY (`id`),
--   KEY `i_com_id` (`com_id`),
--   KEY `i_com_user_id` (`com_user_id`),
--   KEY `i_email` (`email`),
--   KEY `i_mobile` (`mobile`),
--   KEY `i_main_type` (`main_type`) USING BTREE,
--   KEY `i_sub_type` (`sub_type`),
--   KEY `i_task_id` (`task_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `com_transaction_history`;
-- CREATE TABLE `com_transaction_history` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '交易历史记录id',
--   `com_id` int(10) NOT NULL COMMENT '公司id',
--   `member_type` tinyint(2) NOT NULL COMMENT '会员类型',
--   `valid_date` datetime NOT NULL COMMENT '开通时间',
--   `expire_date` datetime NOT NULL COMMENT '截止时间',
--   `max_pos_num` int(10) DEFAULT '0' COMMENT '发布职位[-1不限]',
--   `max_resume_num` int(10) DEFAULT '0' COMMENT '查看简历(个/月)',
--   `max_msg_num` int(10) DEFAULT '0' COMMENT '可发送短信数量',
--   `count_num` int(10) DEFAULT '0' COMMENT '一共购买点数',
--   `saler_id` int(10) DEFAULT NULL COMMENT '业务员id',
--   `check_user` varchar(30) DEFAULT NULL COMMENT '后台操作人姓名',
--   `restrict_area` varchar(500) DEFAULT NULL COMMENT '限制区域',
--   `create_date` datetime DEFAULT NULL COMMENT '创建日期',
--   `edit_date` datetime DEFAULT NULL COMMENT '修改日期',
--   PRIMARY KEY (`id`),
--   KEY `IX_com_id` (`com_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=441643 DEFAULT CHARSET=utf8 COMMENT='企业历史交易记录表';

DROP TABLE `com_user`;
CREATE TABLE `com_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `com_id` int(10) NOT NULL COMMENT '用户所属企业ID',
  `dept_id` int(10) DEFAULT NULL COMMENT '用户所属企业的部门/分子公司ID',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `role_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '用户权限类型{0:主帐号;1:子帐号;2:分公司帐号}',
  `cre_date` datetime NOT NULL COMMENT '用户创建时间',
  `edit_date` datetime DEFAULT NULL COMMENT '用户修改时间',
  `last_login_date` datetime DEFAULT NULL COMMENT '用户最后登录时间',
  `last_login_ip` varchar(100) DEFAULT NULL COMMENT '用户最后登录IP',
  `login_count` int(10) DEFAULT NULL COMMENT '用户登录次数统计',
  `point_own` int(10) DEFAULT '0' COMMENT '用户分配的点数-1=不限，0=不可查看',
  `point_used` int(10) DEFAULT '0' COMMENT '用户已使用的点数',
  `can_login_dual` tinyint(2) NOT NULL DEFAULT '1' COMMENT '用户是否可以重复登录（安全性设置：{0:不可以;1:可以}，默认为可以）',
  `can_set_position` tinyint(2) DEFAULT '1' COMMENT '用户是否可以发布职位（{0:不可以;1:可以}，默认为可以）',
  `del_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '删除状态{0:正常; -1:删除}',
  `dept_rights` varchar(500) NOT NULL DEFAULT '[]' COMMENT 'json数组格式[123,456]',
  PRIMARY KEY (`id`),
  KEY `IX_comId` (`com_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1200724 DEFAULT CHARSET=utf8;

-- DROP TABLE `email_invaild`;
-- CREATE TABLE `email_invaild` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `email` varchar(255) NOT NULL,
--   `per_user_id` int(10) DEFAULT NULL,
--   `create_date` datetime DEFAULT NULL,
--   PRIMARY KEY (`id`),
--   KEY `idx_email` (`email`(250))
-- ) ENGINE=MyISAM AUTO_INCREMENT=6553823 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `email_queue`;
-- CREATE TABLE `email_queue` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `send_flag` tinyint(2) DEFAULT '0' COMMENT '发送标记[0-待发送 1-发送成功 2-发送失败]',
--   `title` varchar(100) NOT NULL COMMENT '邮件标题',
--   `from_addr` varchar(100) NOT NULL COMMENT '发送方地址',
--   `to_addr` varchar(250) NOT NULL COMMENT '收件人地址',
--   `from_name` varchar(100) DEFAULT NULL COMMENT '发送人名称',
--   `send_date` datetime DEFAULT NULL COMMENT '发送时间',
--   `resend_date` datetime DEFAULT NULL COMMENT '重新发送时间',
--   `task_id` int(10) NOT NULL COMMENT '发送邮件任务类型',
--   `content` mediumtext,
--   `email_type` tinyint(2) DEFAULT '0' COMMENT '邮件类型[0-简体  1-繁体]',
--   `create_date` datetime DEFAULT NULL COMMENT '创建日期',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `eshop`;
-- CREATE TABLE `eshop` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `com_id` int(10) NOT NULL COMMENT '企业ID',
--   `logo_id` int(10) DEFAULT NULL COMMENT 'logo图片ID',
--   `position_info` varchar(500) DEFAULT NULL COMMENT '职位内容',
--   `tao_label_info` varchar(500) DEFAULT NULL COMMENT '淘标签内容',
--   `link_url` varchar(150) DEFAULT NULL COMMENT 'LOGO广告链接地址 ',
--   `sort` smallint(5) DEFAULT '0' COMMENT '排序',
--   `create_date` datetime NOT NULL COMMENT '创建日期',
--   `create_by` varchar(30) DEFAULT NULL COMMENT '创建人',
--   `edit_date` datetime DEFAULT NULL COMMENT '修改日期',
--   `edit_by` varchar(30) DEFAULT NULL COMMENT '修改人',
--   `hits` int(10) DEFAULT '0' COMMENT '点击数',
--   PRIMARY KEY (`id`),
--   KEY `idx_comid` (`com_id`) USING BTREE,
--   KEY `idx_logo_id` (`logo_id`),
--   KEY `idx_create_date` (`create_date`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='电商频道';
--
-- DROP TABLE `invalid_per_user`;
-- CREATE TABLE `invalid_per_user` (
--   `per_user_id` int(10) NOT NULL COMMENT '用户编号',
--   `account` varchar(100) DEFAULT NULL COMMENT '用户账户',
--   `email` varchar(100) DEFAULT NULL COMMENT '用户邮箱',
--   `user_name` varchar(100) DEFAULT NULL COMMENT '用户姓名',
--   `resume_id` int(10) DEFAULT NULL COMMENT '用户默认简历ID',
--   `invalid_type` tinyint(2) NOT NULL COMMENT '无效账户的鉴别类型，1=通过邮件鉴别，2=通过账户鉴别',
--   PRIMARY KEY (`per_user_id`)
-- ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--
-- DROP TABLE `log_access`;
-- CREATE TABLE `log_access` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `user_ip` varchar(20) DEFAULT NULL COMMENT '用户IP',
--   `summary` varchar(100) DEFAULT NULL COMMENT '摘要信息',
--   `classes` varchar(150) DEFAULT NULL COMMENT '执行的类名',
--   `method` varchar(100) DEFAULT NULL COMMENT '执行的方法名称',
--   `operator_id` int(10) DEFAULT '0' COMMENT '操作人id',
--   `operator_name` varchar(30) DEFAULT NULL COMMENT '操作人姓名',
--   `operator_type` varchar(20) DEFAULT NULL COMMENT '用户类型',
--   `type` varchar(30) NOT NULL COMMENT '类型[com, per]',
--   `content` varchar(1000) DEFAULT NULL COMMENT '内容',
--   `create_date` datetime NOT NULL COMMENT '访问时间',
--   PRIMARY KEY (`id`),
--   KEY `IX_create_date` (`create_date`),
--   KEY `IX_summary` (`summary`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `log_access_pos`;
-- CREATE TABLE `log_access_pos` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `pos_id` int(10) DEFAULT NULL COMMENT ' 职位ID',
--   `com_id` int(10) DEFAULT NULL COMMENT '企业ID',
--   `user_id` int(10) DEFAULT NULL COMMENT '用户ID',
--   `user_name` varchar(30) DEFAULT NULL COMMENT '用户姓名',
--   `user_ip` varchar(30) DEFAULT NULL COMMENT '用户IP',
--   `user_agent` varchar(250) DEFAULT NULL COMMENT '客户端信息',
--   `create_date` datetime DEFAULT NULL COMMENT '创建日期',
--   PRIMARY KEY (`id`),
--   KEY `IX_posId` (`pos_id`),
--   KEY `IX_comId` (`com_id`),
--   KEY `IX_createDate` (`create_date`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `log_action`;
-- CREATE TABLE `log_action` (
--   `id` bigint(15) NOT NULL AUTO_INCREMENT,
--   `call_method` varchar(100) NOT NULL COMMENT '调用的方法',
--   `param0` varchar(100) DEFAULT NULL COMMENT '重要参数0',
--   `param1` varchar(100) DEFAULT NULL COMMENT '重要参数1',
--   `param2` varchar(100) DEFAULT NULL COMMENT '重要参数2',
--   `param3` varchar(100) DEFAULT NULL COMMENT '重要参数3',
--   `param4` varchar(100) DEFAULT NULL COMMENT '重要参数4',
--   `param5` varchar(100) DEFAULT NULL COMMENT '重要参数5',
--   `param6` varchar(100) DEFAULT NULL COMMENT '重要参数6',
--   `param7` varchar(100) DEFAULT NULL COMMENT '重要参数7',
--   `param8` varchar(100) DEFAULT NULL COMMENT '重要参数8',
--   `param9` varchar(100) DEFAULT NULL COMMENT '重要参数9',
--   `create_time` datetime NOT NULL COMMENT '创建时间',
--   `json_detail` mediumtext COMMENT '将能记录下来的参数或详细信息，作为json保存下来以便跟踪',
--   PRIMARY KEY (`id`),
--   KEY `I_call_method` (`call_method`),
--   KEY `I_create_time` (`create_time`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=33863 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `log_mobile_app_usage`;
-- CREATE TABLE `log_mobile_app_usage` (
--   `id` int(20) NOT NULL AUTO_INCREMENT,
--   `uuid` varchar(100) NOT NULL,
--   `hardware` varchar(100) NOT NULL,
--   `osversion` varchar(100) NOT NULL,
--   `token` varchar(100) DEFAULT NULL,
--   `url` varchar(500) NOT NULL,
--   `method` varchar(100) DEFAULT NULL,
--   `param` mediumtext,
--   `create_time` datetime NOT NULL,
--   `ip` varchar(100) DEFAULT NULL,
--   `ip_addr` varchar(500) DEFAULT NULL,
--   `channel` tinyint(2) DEFAULT NULL COMMENT '客户端的下载渠道（0=官方渠道，1=渠道1）',
--   `ret` int(11) DEFAULT NULL COMMENT '返回码',
--   PRIMARY KEY (`id`),
--   KEY `I_create_time` (`create_time`),
--   KEY `I_url` (`url`(250)),
--   KEY `I_uuid` (`uuid`),
--   KEY `I_hardware` (`hardware`),
--   KEY `I_osversion` (`osversion`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=4210854 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `log_per_reg_act`;
-- CREATE TABLE `log_per_reg_act` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '活动类别',
--   `act_type` int(4) NOT NULL DEFAULT '0' COMMENT '活动类型',
--   `node_id` int(10) DEFAULT '0' COMMENT '上线的ID',
--   `node_mobile` varchar(11) DEFAULT NULL COMMENT '上线的手机',
--   `node_spread_key` varchar(100) DEFAULT NULL COMMENT '上线的推广KEY',
--   `per_user_id` int(10) NOT NULL DEFAULT '0',
--   `per_resume_id` int(10) DEFAULT NULL COMMENT '简历ID',
--   `per_user_name` varchar(50) DEFAULT '',
--   `mobile` varchar(11) NOT NULL,
--   `email` varchar(200) DEFAULT NULL,
--   `ip` varchar(20) DEFAULT NULL,
--   `come_from` int(5) DEFAULT '0' COMMENT '来源的类型',
--   `area_code` int(5) DEFAULT '0' COMMENT '来源地区代码',
--   `site_id` int(4) DEFAULT '0' COMMENT '站点ID',
--   `my_spread_num` int(7) DEFAULT '0' COMMENT '推广数量',
--   `my_resume_pass` tinyint(2) DEFAULT '0' COMMENT '我的简历审核状态',
--   `reg_num` int(7) DEFAULT '0' COMMENT '注册数',
--   `valid_resume_num` int(7) DEFAULT '0' COMMENT '有效简历',
--   `payable` double(7,0) DEFAULT '0' COMMENT '应付',
--   `paid` double(7,0) DEFAULT '0' COMMENT '实付',
--   `paid_date` date DEFAULT NULL COMMENT '支付的日期',
--   `from_url` varchar(500) DEFAULT NULL COMMENT '推广(来源)链接',
--   `editor_id` int(10) DEFAULT NULL COMMENT '最后编辑人ID',
--   `modify_type` tinyint(2) DEFAULT '0' COMMENT '修改的原因类型',
--   `export_num` int(5) DEFAULT '0' COMMENT '导出次数',
--   `create_date` date NOT NULL,
--   `update_date` date DEFAULT NULL,
--   PRIMARY KEY (`id`,`mobile`),
--   KEY `idx_mobile` (`mobile`) USING BTREE,
--   KEY `idx_per_user_id` (`per_user_id`),
--   KEY `idx_parent_node_id` (`node_id`),
--   KEY `idx_come_from` (`come_from`),
--   KEY `idx_per_resume_id` (`per_resume_id`),
--   KEY `idx_node_id` (`node_id`),
--   KEY `idx_create_date` (`create_date`),
--   KEY `idx_paid_date` (`paid_date`),
--   KEY `idx_my_resume_pass` (`my_resume_pass`),
--   KEY `idx_ip` (`ip`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='送话费注册记录表';
--
-- DROP TABLE `log_per_train`;
-- CREATE TABLE `log_per_train` (
--   `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `create_date` datetime DEFAULT NULL COMMENT '创建日期',
--   `course` varchar(100) NOT NULL COMMENT '课程名称',
--   `per_user_id` int(10) DEFAULT NULL COMMENT '用户ID',
--   `username` varchar(100) DEFAULT NULL COMMENT '姓名',
--   `gender` tinyint(2) DEFAULT NULL COMMENT '性别',
--   `birthday` date DEFAULT NULL COMMENT '出生年月',
--   `degree` tinyint(2) DEFAULT NULL COMMENT '学历',
--   `speciality` varchar(100) DEFAULT NULL COMMENT '专业',
--   `mobile` varchar(100) DEFAULT NULL COMMENT '手机号码',
--   `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
--   `qq` varchar(100) DEFAULT NULL COMMENT 'QQ',
--   `location` int(10) unsigned DEFAULT NULL COMMENT '地区对应编号',
--   PRIMARY KEY (`id`),
--   KEY `i_create_date` (`create_date`),
--   KEY `i_course` (`course`),
--   KEY `i_per_user_id` (`per_user_id`),
--   KEY `i_username` (`username`),
--   KEY `i_mobil` (`mobile`),
--   KEY `i_email` (`email`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=28189 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `log_receive_resume_list_time`;
-- CREATE TABLE `log_receive_resume_list_time` (
--   `id` int(20) NOT NULL AUTO_INCREMENT,
--   `com_id` int(10) NOT NULL COMMENT '企业id',
--   `com_name` varchar(100) NOT NULL COMMENT '企业名称',
--   `start_date` datetime NOT NULL COMMENT '开始时间',
--   `end_date` datetime NOT NULL COMMENT '结束时间',
--   `time` int(9) NOT NULL COMMENT '消耗的时间（秒）',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=2267 DEFAULT CHARSET=utf8 COMMENT='企业收到简历列表耗时日志';
--
-- DROP TABLE `log_resume_export_sync`;
-- CREATE TABLE `log_resume_export_sync` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `sync_time` datetime NOT NULL COMMENT '该次搜索同步任务的开始时间',
--   `sync_count` int(10) NOT NULL COMMENT '本次同步的总量',
--   `sync_type` varchar(100) NOT NULL COMMENT '搜索同步的类型 可选值有[position, irc_position, resume]',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=14005 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `log_search_pos_keyword`;
-- CREATE TABLE `log_search_pos_keyword` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT,
--   `keyword` varchar(100) NOT NULL,
--   `pos_type` smallint(5) NOT NULL,
--   `cre_date` datetime NOT NULL,
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=14300817 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `log_task`;
-- CREATE TABLE `log_task` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT,
--   `task_class` varchar(100) NOT NULL,
--   `task_last_time` datetime NOT NULL,
--   `task_memo` varchar(500) DEFAULT NULL,
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=21658401 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `log_transfer`;
-- CREATE TABLE `log_transfer` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT,
--   `count` int(10) NOT NULL,
--   `start_time` datetime NOT NULL,
--   `end_time` datetime NOT NULL,
--   `use_time` int(11) NOT NULL,
--   `type` varchar(100) NOT NULL,
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=21802 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `log_wexin_app_usage`;
-- CREATE TABLE `log_wexin_app_usage` (
--   `id` int(20) NOT NULL AUTO_INCREMENT,
--   `uuid` varchar(100) NOT NULL,
--   `hardware` varchar(100) NOT NULL,
--   `osversion` varchar(100) NOT NULL,
--   `token` varchar(100) DEFAULT NULL,
--   `url` varchar(500) NOT NULL,
--   `method` varchar(100) DEFAULT NULL,
--   `param` mediumtext,
--   `create_time` datetime NOT NULL,
--   `ip` varchar(100) DEFAULT NULL,
--   `ip_addr` varchar(500) DEFAULT NULL,
--   `channel` tinyint(2) DEFAULT NULL COMMENT '客户端的下载渠道（0=官方渠道，1=渠道1）',
--   `ret` int(11) DEFAULT NULL COMMENT '返回码',
--   PRIMARY KEY (`id`),
--   KEY `I_create_time` (`create_time`),
--   KEY `I_url` (`url`(250)),
--   KEY `I_uuid` (`uuid`),
--   KEY `I_hardware` (`hardware`),
--   KEY `I_osversion` (`osversion`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=4020412 DEFAULT CHARSET=utf8 COMMENT='微信手机客户端操作日志表';
--
-- DROP TABLE `mobile_info`;
-- CREATE TABLE `mobile_info` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `mobile_prefix` int(11) NOT NULL,
--   `mobile_area` varchar(50) NOT NULL,
--   PRIMARY KEY (`id`),
--   KEY `prefix_index` (`mobile_prefix`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=567581 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `per_follow_com`;
-- CREATE TABLE `per_follow_com` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `per_user_id` int(10) NOT NULL COMMENT '个人用户ID',
--   `com_id` int(10) NOT NULL COMMENT '企业ID',
--   `com_name` varchar(100) NOT NULL COMMENT '企业名称',
--   `create_date` datetime NOT NULL COMMENT '关注/创建时间',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- DROP TABLE `per_letter`;
-- CREATE TABLE `per_letter` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '求职信id',
--   `user_id` int(10) NOT NULL COMMENT '用户ID',
--   `title` varchar(100) NOT NULL COMMENT '求职信的名称',
--   `job_letter` mediumtext NOT NULL COMMENT ' 求职信的内容',
--   `update_time` date DEFAULT NULL COMMENT '修改时间',
--   `default_letter` tinyint(2) DEFAULT NULL COMMENT '是否是默认求职信：1为默认，0为不是默认',
--   `sign` int(100) NOT NULL COMMENT '求职信的标识',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=795979 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `per_lock_message`;
-- CREATE TABLE `per_lock_message` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `user_id` int(10) NOT NULL,
--   `email` varchar(100) DEFAULT NULL,
--   `card_num` varchar(100) DEFAULT NULL,
--   `mobile` varchar(100) DEFAULT NULL,
--   PRIMARY KEY (`id`),
--   KEY `IX_cardNum` (`card_num`),
--   KEY `IX_mobile` (`mobile`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

DROP TABLE `per_login_history`;
CREATE TABLE `per_login_history` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `per_user_id` int(10) DEFAULT NULL COMMENT '登录的个人账号ID',
  `per_user_account` varchar(100) DEFAULT NULL COMMENT '登录的个人账号',
  `ip` varchar(100) DEFAULT NULL COMMENT '登录的IP',
  `area` varchar(100) DEFAULT NULL COMMENT '登录IP解释出来的地址',
  `cre_time` datetime NOT NULL COMMENT '登录时间',
  `sys_user_id` int(10) DEFAULT NULL COMMENT '后台登录的用户ID',
  `sys_user_account` varchar(100) DEFAULT NULL COMMENT '后台登录的账号',
  `sys_user_name` varchar(100) DEFAULT NULL COMMENT '后台登录的用户姓名',
  `login_type` varchar(100) NOT NULL COMMENT '登录类型 【web, sys, crm】',
  PRIMARY KEY (`id`),
  KEY `I_perUserId` (`per_user_id`),
  KEY `I_loginType` (`login_type`),
  KEY `I_creTime` (`cre_time`)
) ENGINE=MyISAM AUTO_INCREMENT=706 DEFAULT CHARSET=utf8 COMMENT='个人登陆历史记录';

DROP TABLE `per_searcher`;
CREATE TABLE `per_searcher` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `per_user_id` int(10) NOT NULL COMMENT '对应个人用户表的ID',
  `name` varchar(500) NOT NULL COMMENT '搜索器命名，对于同一个求职来说此字段不能重复',
  `subscribe` tinyint(2) NOT NULL COMMENT '是否订阅的标识，1=订阅，0=不订阅',
  `content` mediumtext NOT NULL COMMENT 'JSON格式的搜索器条件内容',
  `email` varchar(100) DEFAULT NULL COMMENT '订阅到的Email地址，可空，只有当subscribe=1的时候才有用',
  `url` varchar(500) NOT NULL COMMENT '搜索器的地址',
  `send_period` int(3) DEFAULT '1' COMMENT '发送周期（天)',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `i_email` (`email`),
  KEY `i_per_user_id` (`per_user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=195 DEFAULT CHARSET=utf8;

DROP TABLE `per_subscription`;
CREATE TABLE `per_subscription` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL COMMENT '用户ID，外键，per_user表的主键',
  `main_type` varchar(100) NOT NULL COMMENT '主类型，如：邮件订阅、手机短信设置',
  `sub_type` varchar(100) NOT NULL COMMENT '子类型，如：邮件订阅的网站资讯、手机短信的简历被查看等',
  `subscribe_status` tinyint(2) NOT NULL COMMENT '0未订阅 1订阅',
  `task_id` int(10) DEFAULT NULL COMMENT '订阅的进程Id',
  `email` varchar(100) DEFAULT NULL COMMENT '订阅的邮箱地址',
  `send_period` int(3) DEFAULT '1' COMMENT '发送周期',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `create_date` datetime DEFAULT NULL COMMENT '订阅的时间',
  PRIMARY KEY (`id`),
  KEY `I_uid_main_sub` (`user_id`,`main_type`,`sub_type`)
) ENGINE=MyISAM AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;

DROP TABLE `per_user_attach`;
CREATE TABLE `per_user_attach` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键，自动递增',
  `per_user_id` int(10) DEFAULT NULL COMMENT 'Per_user表的id进行关联',
  `user_x` varchar(20) DEFAULT NULL COMMENT '用户的X轴横坐标',
  `user_y` varchar(20) DEFAULT NULL COMMENT '用户的Y轴纵坐标',
  `edit_date` datetime DEFAULT NULL COMMENT '更新时间',
  `recent_job` varchar(50) DEFAULT NULL COMMENT '最近工作岗位\r\n \r\n',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IX_PER_USER_ID` (`per_user_id`) USING BTREE,
  KEY `IX_X` (`user_x`) USING BTREE,
  KEY `IX_Y` (`user_y`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE `per_user_connect`;
CREATE TABLE `per_user_connect` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `account_id` int(10) unsigned DEFAULT NULL COMMENT '通行证账号ID',
  `per_user_id` int(10) NOT NULL COMMENT '个人用户ID',
  `open_id` varchar(100) NOT NULL COMMENT '第三方网站提供的ID',
  `open_name` varchar(100) NOT NULL COMMENT '第三方网站提供的昵称',
  `access_token` varchar(100) NOT NULL COMMENT 'OAuth规范的Token，记录备用',
  `connect_time` datetime NOT NULL COMMENT '绑定时间',
  `type` varchar(100) NOT NULL COMMENT '类型',
  PRIMARY KEY (`id`),
  KEY `IX_account_id` (`account_id`),
  KEY `IX_open_id` (`open_id`),
  KEY `IX_type` (`type`)
) ENGINE=MyISAM AUTO_INCREMENT=103149 DEFAULT CHARSET=utf8;

-- DROP TABLE `proj_easy_factory_blacklist`;
-- CREATE TABLE `proj_easy_factory_blacklist` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键，自动递增',
--   `add_id` int(10) DEFAULT NULL COMMENT 'Account_info表的id字段，添加黑名单用户id',
--   `added_id` int(10) DEFAULT NULL COMMENT 'Account_info表的id字段，被添加到黑名单中用户id',
--   `added_type` tinyint(2) DEFAULT '0' COMMENT '被添加成黑名单用户的类型（0表示陌生人，1表示曾经add_id的好友）',
--   `add_date` datetime DEFAULT NULL COMMENT '添加黑名单的时间。',
--   `perform_type` tinyint(2) DEFAULT '0' COMMENT '操作状态（0表示还是黑名单，1表示add_id用户取消黑名单，成为正常状态）',
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `IX_ID` (`id`) USING BTREE,
--   KEY `IX_ADD_ID` (`add_id`) USING BTREE,
--   KEY `IX_ADDED_ID` (`added_id`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `proj_easy_factory_prosecute`;
-- CREATE TABLE `proj_easy_factory_prosecute` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键，自动递增',
--   `prosecute_id` int(10) DEFAULT NULL COMMENT 'Account_info表的id字段，举报人员的id',
--   `prosecuted_id` int(10) DEFAULT NULL COMMENT 'Account_info表的id字段，被举报人员的id',
--   `prosecute_content` varchar(100) DEFAULT NULL COMMENT '举报内容',
--   `prosecute_date` datetime DEFAULT NULL COMMENT '举报时间',
--   `prosecute_results` tinyint(2) unsigned DEFAULT '0' COMMENT '举报的结果(0表示证据充足被举报人封号，1表示证据不足无法封号)',
--   `admin_id` int(10) DEFAULT NULL COMMENT '操作封号管理人员id',
--   `perform_date` datetime DEFAULT NULL COMMENT '执行时间',
--   `Perform_state` tinyint(2) DEFAULT '0' COMMENT '执行状态（0表示未执行，1表示已执行。）',
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `IX_ID` (`id`) USING BTREE,
--   KEY `IX_PROSECUTE_ID` (`prosecute_id`) USING BTREE,
--   KEY `IX_PROSECUTED_ID` (`prosecuted_id`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `proj_easy_factory_report`;
-- CREATE TABLE `proj_easy_factory_report` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键，自动递增',
--   `user_id` int(10) DEFAULT NULL COMMENT 'Account_info表的id字段，记录持有人的id',
--   `fuser_id` int(10) DEFAULT NULL COMMENT 'Account_info表的id字段，发送信息人的id',
--   `tuser_id` int(10) DEFAULT NULL COMMENT 'Account_info表的id字段，收信息人的id',
--   `content` text COMMENT '聊天记录的内容',
--   `read_state` tinyint(2) DEFAULT '0' COMMENT '用户读取状态（0表示已读取，1表示未读）',
--   `delete_state` tinyint(2) DEFAULT '0' COMMENT '用户是否删除过聊天记录（0表示没有删除，1表示删除）',
--   `send_date` datetime DEFAULT NULL COMMENT '发送时间',
--   `request_state` tinyint(2) DEFAULT '0' COMMENT '用户请求添加好友状态（0表示未请求，1表示请求添加好友）',
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `IX_ID` (`id`) USING BTREE,
--   KEY `IX_USER_ID` (`user_id`) USING BTREE,
--   KEY `IX_FUSER_ID` (`fuser_id`) USING BTREE,
--   KEY `IX_TUSER_ID` (`tuser_id`) USING BTREE,
--   KEY `IX_request_state` (`request_state`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `proj_easy_factory_user_goodf`;
-- CREATE TABLE `proj_easy_factory_user_goodf` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键，自动递增',
--   `myself_id` int(10) DEFAULT NULL COMMENT 'Account_info表的id字段，用来存储的是用户自己的id',
--   `goodf_id` int(10) DEFAULT NULL COMMENT 'Account_info表的id字段，用来存储的是用户好友的id',
--   `add_date` datetime DEFAULT NULL COMMENT '添加好友时间',
--   `delete_date` datetime DEFAULT NULL COMMENT '删除好友时间',
--   `goodf_type` tinyint(2) DEFAULT '0' COMMENT '好友状态是否是当前好友（0表示是当前好友，1表示被myself_id用户删除了）',
--   `user_request` tinyint(2) DEFAULT '0' COMMENT '好友请求申请好友（0表示同意，1表示未同意）',
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `IX_ID` (`id`) USING BTREE,
--   KEY `IX_MYSELF_ID` (`myself_id`) USING BTREE,
--   KEY `IX_GOODF_ID` (`goodf_id`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `proj_human_officer_club_form`;
-- CREATE TABLE `proj_human_officer_club_form` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `user_name` varchar(100) NOT NULL COMMENT '姓名',
--   `gender` tinyint(2) DEFAULT NULL COMMENT '性别：1为男，2为女',
--   `job` varchar(100) DEFAULT NULL COMMENT '职务',
--   `mobile` varchar(100) DEFAULT NULL COMMENT '手机号码',
--   `fax` varchar(100) DEFAULT NULL COMMENT '传真',
--   `phone` varchar(100) DEFAULT NULL COMMENT '固定电话',
--   `qq` varchar(100) DEFAULT NULL COMMENT 'QQ号',
--   `email` varchar(100) DEFAULT NULL COMMENT '邮箱地址',
--   `hobby` varchar(100) DEFAULT NULL COMMENT '业余爱好',
--   `compony` varchar(100) DEFAULT NULL COMMENT '公司名称',
--   `employee_count` int(10) DEFAULT '0' COMMENT '企业员工数',
--   `follow_domain` varchar(500) DEFAULT NULL COMMENT '关注领域(保存的类型为JSON)',
--   `address` varchar(100) DEFAULT NULL COMMENT '杂志邮寄地址',
--   `membership_type` varchar(100) DEFAULT NULL COMMENT '入会类型，如：diamondMember->钻石会员\r\nknowledger->知本家 humanOfficer->人力知本智库',
--   `create_date` datetime DEFAULT NULL COMMENT '创建时间',
--   PRIMARY KEY (`id`),
--   KEY `email` (`email`),
--   KEY `create_date` (`create_date`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `proj_human_officer_online`;
-- CREATE TABLE `proj_human_officer_online` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `user_name` varchar(100) NOT NULL COMMENT '姓名',
--   `gender` tinyint(2) DEFAULT NULL COMMENT '性别：1为男，2为女',
--   `job` varchar(100) DEFAULT NULL COMMENT '职务',
--   `mobile` varchar(100) DEFAULT NULL COMMENT '手机号码',
--   `fax` varchar(100) DEFAULT NULL COMMENT '传真',
--   `phone` varchar(100) DEFAULT NULL COMMENT '固定电话',
--   `qq` varchar(100) DEFAULT NULL COMMENT 'QQ号',
--   `email` varchar(100) DEFAULT NULL COMMENT '邮箱地址',
--   `visit_required` varchar(200) DEFAULT NULL COMMENT '业余爱好',
--   `create_date` datetime DEFAULT NULL COMMENT '创建时间',
--   PRIMARY KEY (`id`),
--   KEY `email` (`email`),
--   KEY `create_date` (`create_date`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `proj_lexicon`;
-- CREATE TABLE `proj_lexicon` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `word` varchar(20) NOT NULL COMMENT '关键词',
--   `dic_file_name` varchar(20) NOT NULL COMMENT '所属词库文件名称',
--   `create_date` datetime DEFAULT NULL COMMENT '创建日期',
--   `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
--   PRIMARY KEY (`id`),
--   KEY `IX_word` (`word`) USING BTREE,
--   KEY `IX_create_date` (`create_date`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=828052 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `proj_lexicon_task`;
-- CREATE TABLE `proj_lexicon_task` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `name` varchar(30) NOT NULL COMMENT '任务名称',
--   `type` varchar(20) NOT NULL COMMENT '操作类型[add、del]',
--   `words` varchar(1000) DEFAULT NULL COMMENT '关键词集合',
--   `state` tinyint(4) DEFAULT '0' COMMENT '执行状态[0-未执行 1-已执行]',
--   `create_date` datetime DEFAULT NULL,
--   `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
--   `edit_date` datetime DEFAULT NULL COMMENT '修改时间',
--   `edit_by` varchar(20) DEFAULT NULL COMMENT '修改人',
--   PRIMARY KEY (`id`),
--   KEY `IX_name` (`name`) USING BTREE,
--   KEY `IX_type_state` (`type`,`state`) USING BTREE,
--   KEY `IX_create_date` (`create_date`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `proj_lexicon_task_item`;
-- CREATE TABLE `proj_lexicon_task_item` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `task_id` int(10) NOT NULL COMMENT '任务ID',
--   `host` varchar(20) NOT NULL COMMENT '主机IP',
--   `oper` varchar(20) NOT NULL COMMENT '操作',
--   `call_type` varchar(20) NOT NULL COMMENT '接口调用类型(ejb、api)',
--   `state` tinyint(4) DEFAULT '0' COMMENT '任务项执行状态',
--   `report` varchar(500) DEFAULT NULL COMMENT '任务项执行报告',
--   `create_date` datetime DEFAULT NULL COMMENT '创建日期',
--   `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
--   `execute_date` datetime DEFAULT NULL COMMENT '任务项执行时间',
--   `execute_by` varchar(20) DEFAULT NULL COMMENT '任务项执行人',
--   `edit_date` datetime DEFAULT NULL COMMENT '任务项修改时间',
--   `edit_by` varchar(20) DEFAULT NULL COMMENT '任务项修改人',
--   PRIMARY KEY (`id`),
--   KEY `IX_task_id` (`task_id`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `proj_official_site_business`;
-- CREATE TABLE `proj_official_site_business` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `name` varchar(50) DEFAULT NULL COMMENT '关联业务名称',
--   `contact_person` varchar(100) DEFAULT NULL COMMENT '联络人',
--   `outlook_mail` varchar(100) DEFAULT NULL COMMENT 'outlook邮箱',
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `IX_name` (`name`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `proj_official_site_contact`;
-- CREATE TABLE `proj_official_site_contact` (
--   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '集团官网联系方式主键id',
--   `name` varchar(100) NOT NULL COMMENT '公司名称',
--   `phone` varchar(50) NOT NULL COMMENT '联系电话',
--   `address` varchar(100) NOT NULL COMMENT '公司地址',
--   `province_code` int(10) DEFAULT NULL COMMENT '所属省',
--   `city_code` int(10) DEFAULT NULL COMMENT '所属市',
--   `service_proj` varchar(100) DEFAULT NULL COMMENT '服务项目',
--   `sort` int(10) DEFAULT '0' COMMENT '默认排序',
--   `edit_user_name` varchar(100) NOT NULL COMMENT '操作人',
--   PRIMARY KEY (`id`),
--   KEY `IX_province_code` (`province_code`),
--   KEY `IX_city_code` (`city_code`),
--   KEY `IX_service_proj` (`service_proj`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `proj_official_site_feedback`;
-- CREATE TABLE `proj_official_site_feedback` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `type` varchar(50) DEFAULT NULL COMMENT '信息类型',
--   `feedback_person` varchar(50) DEFAULT NULL COMMENT '反馈人',
--   `contact` varchar(50) DEFAULT NULL COMMENT '联系方式',
--   `business_id` int(11) DEFAULT NULL COMMENT '关联业务',
--   `leave_message` varchar(1000) DEFAULT NULL COMMENT '用户留言',
--   `feedback_date` datetime DEFAULT NULL COMMENT '反馈时间',
--   PRIMARY KEY (`id`),
--   KEY `IX_type` (`type`),
--   KEY `IX_business_id` (`business_id`),
--   KEY `IX_feedback_date` (`feedback_date`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `proj_ptime_info`;
-- CREATE TABLE `proj_ptime_info` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '兼职ID',
--   `sys_user_id` int(10) NOT NULL COMMENT '后台操作人员id',
--   `ptime_name` varchar(50) NOT NULL COMMENT '兼职名称',
--   `type` int(10) NOT NULL COMMENT '类型id',
--   `area` int(10) NOT NULL COMMENT '地区Id',
--   `salaries` tinyint(1) DEFAULT '0' COMMENT '判断兼职是否高薪（默认是0为不是高薪，1是高薪）',
--   `hot` tinyint(1) DEFAULT '0' COMMENT '判断兼职是否热门（默认是0为不热门，1是热门）',
--   `com_name` varchar(50) DEFAULT NULL COMMENT '发布兼职公司的名称',
--   `time_limit` date DEFAULT NULL COMMENT '招聘时间限制（最后招聘的时间）',
--   `salary` int(6) NOT NULL COMMENT '工资待遇（钱的数额）',
--   `unit` tinyint(2) NOT NULL COMMENT ' 工资待遇的单位(1表示小时，2表示半天，3表示一天)',
--   `start_date` date NOT NULL COMMENT '工作开始时间',
--   `end_date` date NOT NULL COMMENT '工作结束时间',
--   `recruitment_num` int(4) DEFAULT NULL COMMENT '招聘人数',
--   `descript` text COMMENT '兼职描述',
--   `com_user_name` varchar(10) DEFAULT NULL COMMENT '发布企业联系人姓名',
--   `phone` varchar(13) DEFAULT NULL COMMENT '发布企业联系电话',
--   `address` varchar(100) NOT NULL COMMENT '发布企业的详细地址',
--   `release_time` datetime NOT NULL COMMENT '发布时间',
--   `state` tinyint(2) DEFAULT NULL COMMENT '招聘状态（0表示正常，1表示停止，2表示重新开始，3表示已过期）',
--   `del_flag` tinyint(1) DEFAULT '1' COMMENT '显示（0表示不显示，1表示显示）',
--   `X` varchar(30) DEFAULT NULL COMMENT '百度地图X坐标',
--   `Y` varchar(30) DEFAULT NULL COMMENT '百度地图Y坐标',
--   `lat` varchar(30) DEFAULT NULL COMMENT '纬度',
--   `lng` varchar(30) DEFAULT NULL COMMENT '经度',
--   `create_date` datetime NOT NULL COMMENT '创建时间',
--   `edite_date` datetime NOT NULL COMMENT '编辑时间',
--   PRIMARY KEY (`id`),
--   KEY `IX_ptime_name` (`ptime_name`) USING BTREE,
--   KEY `IX_com_name` (`com_name`) USING BTREE,
--   KEY `IX_start_date` (`start_date`) USING BTREE,
--   KEY `IX_end_date` (`end_date`) USING BTREE,
--   KEY `IX_release_time` (`release_time`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `proj_ptime_inv_record`;
-- CREATE TABLE `proj_ptime_inv_record` (
--   `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录id，主键、自增',
--   `account_id` int(10) unsigned DEFAULT NULL COMMENT '通行证id',
--   `resume_id` int(10) unsigned DEFAULT NULL COMMENT '微名片的id',
--   `part_time_id` int(10) unsigned DEFAULT NULL COMMENT '兼职职位id',
--   `interview_start_time` datetime DEFAULT NULL COMMENT '面试开始时间',
--   `interview_end_time` datetime DEFAULT NULL COMMENT '面试结束时间',
--   `contact_people` varchar(50) DEFAULT NULL COMMENT '发布企业联系人姓名',
--   `contact_phone` varchar(50) DEFAULT NULL COMMENT '发布企业联系电话',
--   `detailed_address` varchar(100) DEFAULT NULL COMMENT '发布企业的详细地址',
--   PRIMARY KEY (`id`),
--   KEY `IX_account_id` (`account_id`) USING BTREE,
--   KEY `IX_resume_id` (`resume_id`) USING BTREE,
--   KEY `IX_part_time_id` (`part_time_id`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `proj_ptime_record`;
-- CREATE TABLE `proj_ptime_record` (
--   `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录id，主键、自增',
--   `account_id` int(10) unsigned DEFAULT NULL COMMENT '通行证id',
--   `resume_id` int(10) unsigned DEFAULT NULL COMMENT '微名片的id',
--   `part_time_id` int(10) unsigned DEFAULT NULL COMMENT '兼职职位id',
--   `delivery_time` datetime DEFAULT NULL COMMENT '投递时间',
--   `state` tinyint(1) unsigned DEFAULT NULL COMMENT '投递后的状态（0表示无操作，1表示邀请，2表示录用，3弃用）',
--   PRIMARY KEY (`id`),
--   KEY `IX_part_time_id` (`part_time_id`) USING BTREE,
--   KEY `IX_state` (`state`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=226 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `pub_feedback`;
-- CREATE TABLE `pub_feedback` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `per_user_id` int(10) DEFAULT NULL COMMENT '个人用户ID',
--   `com_id` int(10) DEFAULT NULL COMMENT '企业ID',
--   `type` tinyint(2) NOT NULL COMMENT '提问类型1-建议 2-纠错 3-投诉',
--   `subject` varchar(100) NOT NULL COMMENT '主题',
--   `content` varchar(500) NOT NULL COMMENT '提问内容',
--   `star_level` tinyint(2) DEFAULT NULL COMMENT '评分几颗星',
--   `name` varchar(100) NOT NULL COMMENT '反馈人名称',
--   `email` varchar(100) NOT NULL COMMENT '邮箱',
--   `mobile` varchar(100) DEFAULT NULL COMMENT '手机',
--   `create_date` datetime NOT NULL COMMENT '创建日期',
--   `flag` tinyint(2) DEFAULT '0' COMMENT '回复标识，0=未回复,1=已回复，2=待回复，3=不处理',
--   `reply_date` datetime DEFAULT NULL COMMENT '回复日期',
--   `reply_content` varchar(500) DEFAULT NULL COMMENT '回复内容',
--   `reply_user_id` int(10) DEFAULT NULL COMMENT '回复人ID[后台用户ID]',
--   `sys_msg` varchar(500) DEFAULT NULL COMMENT '站内信',
--   `url` varchar(500) DEFAULT NULL COMMENT '问题路径',
--   `points_id` int(10) DEFAULT NULL COMMENT '申请返点表的关联ID',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `recuit_analysis`;
-- CREATE TABLE `recuit_analysis` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `analy_type` varchar(100) NOT NULL COMMENT '人才分析类型，目前有2类【position=网络职位30天内应聘人才分析 ;fair=招聘会参会人才分析】',
--   `analy_id` int(10) NOT NULL COMMENT '对应的id',
--   `analy_date` datetime NOT NULL COMMENT '分析时间',
--   `analy_result` mediumtext NOT NULL COMMENT '分析的结果，json字段保存的格式',
--   PRIMARY KEY (`id`),
--   KEY `I_type_id` (`analy_type`,`analy_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `resume_shield`;
-- CREATE TABLE `resume_shield` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `com_id` int(11) NOT NULL,
--   `resume_ids` varchar(500) NOT NULL,
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_add_resume_history`;
-- CREATE TABLE `sys_add_resume_history` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `per_user_id` int(10) NOT NULL COMMENT '用户账户id',
--   `sys_user_id` int(10) NOT NULL COMMENT '系统用户id',
--   `create_date` datetime NOT NULL COMMENT '录入日期',
--   PRIMARY KEY (`id`),
--   KEY `I_per_user_id` (`per_user_id`) USING BTREE,
--   KEY `I_sys_user_id` (`sys_user_id`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 COMMENT='后台简历录入信息表';
--
-- DROP TABLE `sys_batch_send_sms_log`;
-- CREATE TABLE `sys_batch_send_sms_log` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `user_name` varchar(100) NOT NULL COMMENT '用户姓名',
--   `send_amout` int(10) NOT NULL COMMENT '目标发送数量',
--   `send_time` datetime NOT NULL COMMENT '发送时间',
--   `user_id` int(10) NOT NULL,
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_com_manage_history`;
-- CREATE TABLE `sys_com_manage_history` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `com_id` int(10) NOT NULL COMMENT '企业ID',
--   `content` varchar(1000) NOT NULL COMMENT '原因描述',
--   `state` tinyint(2) DEFAULT NULL COMMENT '操作状态',
--   `create_date` datetime DEFAULT NULL COMMENT '创建日期',
--   `create_by` varchar(30) DEFAULT NULL COMMENT '创建人',
--   PRIMARY KEY (`id`),
--   KEY `IX_comId` (`com_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=44289 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_com_pos_school`;
-- CREATE TABLE `sys_com_pos_school` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `com_id` int(10) NOT NULL COMMENT '企业id',
--   `com_name` varchar(100) NOT NULL COMMENT '企业名称',
--   `create_date` date NOT NULL COMMENT '创建日期',
--   `create_user_id` int(10) NOT NULL COMMENT '创建人id',
--   `create_user_name` varchar(100) NOT NULL COMMENT '创建人姓名',
--   `order_by` tinyint(4) NOT NULL COMMENT '排序号',
--   `website_id` int(10) NOT NULL COMMENT '分站点id',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='校园招聘企业设置表';
--
-- DROP TABLE `sys_count_item`;
-- CREATE TABLE `sys_count_item` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `item_id` int(11) NOT NULL COMMENT '栏目id',
--   `ip_count` int(11) NOT NULL DEFAULT '0' COMMENT 'ip总数',
--   `pv_count` int(11) NOT NULL DEFAULT '0' COMMENT 'pv总数',
--   `reg_resume_count` int(9) NOT NULL DEFAULT '0' COMMENT '简历注册量总数',
--   `spread_count` int(9) NOT NULL DEFAULT '0' COMMENT '推广带来的访问总数',
--   `count_date` date NOT NULL COMMENT '统计日期',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=94002 DEFAULT CHARSET=utf8 COMMENT='栏目统计结果表';
--
-- DROP TABLE `sys_count_item_setting`;
-- CREATE TABLE `sys_count_item_setting` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `item_name` varchar(100) NOT NULL COMMENT '栏目名称',
--   `item_url` varchar(1000) NOT NULL COMMENT '栏目url，json格式',
--   `item_category` varchar(500) NOT NULL COMMENT '统计项目，1=IP、3=PV、5=简历注册量、7=推广带来的访问量',
--   `tn` varchar(100) DEFAULT NULL COMMENT '栏目编号',
--   `begin_date` date NOT NULL COMMENT '开始日期',
--   `end_date` date DEFAULT NULL COMMENT '结束日期',
--   `create_date` datetime NOT NULL COMMENT '创建时间',
--   `update_date` datetime NOT NULL COMMENT '修改日期',
--   `update_user_name` varchar(255) NOT NULL COMMENT '修改人',
--   `item_mode` tinyint(1) DEFAULT '0' COMMENT '统计方式[0-推广参数统计 1-直接访问统计]',
--   `item_type` varchar(100) DEFAULT NULL COMMENT '项目类型（废弃）',
--   `item_type_no` int(11) DEFAULT NULL COMMENT '项目类型编号(id)',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=2158 DEFAULT CHARSET=utf8 COMMENT='栏目统计设置表';
--
-- DROP TABLE `sys_count_item_setting_type`;
-- CREATE TABLE `sys_count_item_setting_type` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `type_name` varchar(100) NOT NULL COMMENT '类型名称',
--   `is_root` int(2) DEFAULT '0' COMMENT '是否根类型',
--   `has_child` int(2) DEFAULT '0' COMMENT '有没有子类型',
--   `parent_id` int(11) DEFAULT NULL COMMENT '父节点编码',
--   PRIMARY KEY (`id`),
--   KEY `I_parent_type_no` (`parent_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=859 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_count_logo_hits`;
-- CREATE TABLE `sys_count_logo_hits` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `queue_id` int(10) NOT NULL COMMENT 'LOGO队列ID',
--   `resource_id` int(10) NOT NULL COMMENT 'LOGO资源ID',
--   `hits` int(10) DEFAULT '0' COMMENT '点击次数',
--   `site_id` int(10) DEFAULT NULL COMMENT '站点ID',
--   `create_date` date DEFAULT NULL COMMENT '创建日期',
--   PRIMARY KEY (`id`),
--   KEY `I_qid_date` (`queue_id`,`create_date`),
--   KEY `I_resourceId` (`resource_id`),
--   KEY `I_siteId` (`site_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_count_mail_send`;
-- CREATE TABLE `sys_count_mail_send` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `task_id` int(10) NOT NULL COMMENT '任务类型ID，详细查看Constants',
--   `summary` varchar(100) NOT NULL COMMENT '摘要说明',
--   `total` int(10) DEFAULT '0' COMMENT '总计应发数量',
--   `success` int(10) DEFAULT '0' COMMENT '发送成功数量',
--   `fail` int(10) DEFAULT '0' COMMENT '发送失败数量',
--   `count_time` datetime DEFAULT NULL COMMENT '创建日期',
--   `detail` varchar(500) DEFAULT NULL COMMENT '发送邮件详细内容',
--   PRIMARY KEY (`id`),
--   KEY `I_task_id` (`task_id`),
--   KEY `I_count_time` (`count_time`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=347 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_count_mobile_app`;
-- CREATE TABLE `sys_count_mobile_app` (
--   `id` int(20) NOT NULL AUTO_INCREMENT,
--   `device_access_count` int(10) DEFAULT NULL COMMENT '设备访问数',
--   `all_access_count` int(10) DEFAULT NULL COMMENT '访问记录总数',
--   `resume_add_api_call_count` int(10) DEFAULT NULL COMMENT '简历注册接口调用数',
--   `resume_refresh_api_call_count` int(10) DEFAULT NULL COMMENT '简历刷新接口调用数量',
--   `applicant_api_call_count` int(10) DEFAULT NULL COMMENT '应聘接口调用数量',
--   `applicant_api_call_per_count` int(10) DEFAULT NULL COMMENT '应聘接口调用的个人数量',
--   `per_login_count` int(10) DEFAULT NULL COMMENT '登录的个人数量',
--   `count_date` date NOT NULL COMMENT '统计日期',
--   `api_call_type` varchar(20) NOT NULL COMMENT '接口调用类型，iphone，android，wechat，crm',
--   `hardware` varchar(100) DEFAULT NULL COMMENT '使用设备',
--   `osversion` varchar(100) DEFAULT NULL COMMENT '使用系统',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=1171 DEFAULT CHARSET=utf8 COMMENT='后台手机客户端访问统计';
--
-- DROP TABLE `sys_count_resume_position_postype`;
-- CREATE TABLE `sys_count_resume_position_postype` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT,
--   `count_date` date NOT NULL COMMENT '统计日期',
--   `type` tinyint(2) NOT NULL COMMENT '类型，0=简历，1=职位',
--   `category` tinyint(2) NOT NULL COMMENT '类别，0=新增，1=登陆，2=刷新',
--   `pos_type` smallint(5) NOT NULL COMMENT '职位类别',
--   `location` int(10) unsigned DEFAULT '0' COMMENT '地区对应编号',
--   `count_num` int(10) NOT NULL COMMENT '(简历/职位)数量',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=212929 DEFAULT CHARSET=utf8 COMMENT='简历/职位统计信息表';
--
-- DROP TABLE `sys_customer_feedback`;
-- CREATE TABLE `sys_customer_feedback` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `com_id` int(10) NOT NULL COMMENT '企业id',
--   `sys_user_id` int(10) NOT NULL COMMENT '用户id',
--   `feedback_value` int(10) DEFAULT NULL COMMENT '服务反馈值',
--   `feedback_type` smallint(5) DEFAULT NULL COMMENT '反馈类型',
--   `feedback_date` date DEFAULT NULL COMMENT '反馈日期',
--   `create_date` time DEFAULT NULL COMMENT '创建日期',
--   `update_date` time DEFAULT NULL COMMENT '修改日期',
--   PRIMARY KEY (`id`),
--   KEY `index_com_user_id` (`com_id`,`sys_user_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_data_right`;
-- CREATE TABLE `sys_data_right` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '数据权限id',
--   `role_id` int(10) NOT NULL COMMENT '角色id',
--   `module_id` int(10) NOT NULL COMMENT '模块id',
--   `right_value` varchar(1000) DEFAULT NULL COMMENT '权限值,保存json格式字符串',
--   `create_date` datetime DEFAULT NULL COMMENT '创建日期',
--   `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
--   `edit_date` datetime DEFAULT NULL COMMENT '修改日期',
--   `edit_by` varchar(255) DEFAULT NULL COMMENT '修改人',
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `IX_roleid_moduleid` (`role_id`,`module_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_department`;
-- CREATE TABLE `sys_department` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '部门id',
--   `name` varchar(50) NOT NULL COMMENT '部门名称',
--   `parent_id` int(10) DEFAULT '0' COMMENT '父部门id',
--   `dept_type` tinyint(2) DEFAULT NULL COMMENT '部门性质',
--   `dept_property` smallint(5) DEFAULT NULL COMMENT '部门属性',
--   `descript` varchar(100) DEFAULT NULL COMMENT '部门描述',
--   `create_date` datetime DEFAULT NULL COMMENT '创建日期',
--   PRIMARY KEY (`id`),
--   KEY `IX_parent_id` (`parent_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=278 DEFAULT CHARSET=utf8 COMMENT='部门表';
--
-- DROP TABLE `sys_district`;
-- CREATE TABLE `sys_district` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `name` varchar(100) NOT NULL COMMENT '园区名称',
--   `creator_id` int(10) NOT NULL COMMENT '创建人id',
--   `creator` varchar(100) NOT NULL COMMENT '创建人名称',
--   `create_date` datetime NOT NULL COMMENT '创建日期',
--   `status` int(2) DEFAULT NULL COMMENT '是否有效',
--   `com_id_json_list` varchar(4000) DEFAULT NULL COMMENT '公司id以及生效状态列表的json字符串',
--   `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
--   `update_date` datetime NOT NULL COMMENT '最后更新',
--   `updator` varchar(100) NOT NULL,
--   `updator_id` int(10) NOT NULL,
--   PRIMARY KEY (`id`),
--   KEY `I_name` (`name`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_district_com`;
-- CREATE TABLE `sys_district_com` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `district_id` int(10) NOT NULL COMMENT '对应园区id',
--   `com_id` int(10) NOT NULL COMMENT '公司id',
--   `com_name` varchar(100) NOT NULL COMMENT '公司名称',
--   `contact_person` varchar(100) DEFAULT NULL COMMENT '公司联系人',
--   `contact_phone` varchar(100) DEFAULT NULL COMMENT '公司联系电话',
--   `updator_id` int(10) NOT NULL COMMENT '最近更新人id',
--   `updator` varchar(100) NOT NULL COMMENT '最近更新人',
--   `creator_id` int(10) NOT NULL,
--   `creator` varchar(100) NOT NULL COMMENT '创建人',
--   `create_date` datetime NOT NULL COMMENT '创建日期',
--   `update_date` datetime NOT NULL COMMENT '最近更新日期',
--   `status` int(2) DEFAULT NULL COMMENT '状态',
--   `interview_address` varchar(400) DEFAULT NULL COMMENT '面试地址',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=164 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_friend_link`;
-- CREATE TABLE `sys_friend_link` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `com_name` varchar(100) NOT NULL COMMENT '公司名称',
--   `link` varchar(500) DEFAULT NULL COMMENT '友情链接url',
--   `sub_site_info_id` int(10) NOT NULL COMMENT '对应分站点友情链接信息的id',
--   `per_user_id` int(10) DEFAULT NULL COMMENT '创建人id',
--   `per_user_name` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
--   `create_date` datetime DEFAULT NULL COMMENT '创建日期',
--   `is_nofollow` tinyint(4) DEFAULT NULL,
--   `is_show_index` tinyint(4) DEFAULT '1' COMMENT '是否显示于首页 1：是 0：否',
--   `type` int(10) DEFAULT '2' COMMENT '链接类型，1：生活服务 2：行业综合 3：网站合作',
--   PRIMARY KEY (`id`),
--   KEY `i_sub_site_info_id` (`sub_site_info_id`),
--   KEY `i_com_name` (`com_name`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=180 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_home_enterprise`;
-- CREATE TABLE `sys_home_enterprise` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `com_id` int(10) NOT NULL COMMENT '企业id',
--   `com_name` varchar(100) NOT NULL COMMENT '企业名称',
--   `begin_date` date NOT NULL COMMENT '显示开始日期',
--   `end_date` date NOT NULL COMMENT '显示结束日期',
--   `new_or_hot` tinyint(2) NOT NULL DEFAULT '0' COMMENT '新添加或是热门，0-都不是，1-为新的，2-为热门',
--   `website_id` int(11) NOT NULL COMMENT '分站点id',
--   `position_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '职位类型[1-热门 0-最新 2-行业专区]',
--   `pos_type` int(11) DEFAULT NULL COMMENT '职位类别',
--   `order_by` tinyint(4) NOT NULL COMMENT '排序号',
--   `position_json` mediumtext NOT NULL COMMENT '职位信息json',
--   `update_user_id` int(11) NOT NULL COMMENT '操作人id',
--   `update_user_name` varchar(100) NOT NULL,
--   `update_date` datetime DEFAULT NULL COMMENT '最后修改日期',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='后台分站点首页事业单位招聘信息表';
--
-- DROP TABLE `sys_home_position`;
-- CREATE TABLE `sys_home_position` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `com_id` int(10) NOT NULL COMMENT '企业id',
--   `com_name` varchar(100) NOT NULL COMMENT '企业名称',
--   `begin_date` date NOT NULL COMMENT '显示开始日期',
--   `end_date` date NOT NULL COMMENT '显示结束日期',
--   `is_buy` tinyint(2) NOT NULL COMMENT '是否购买，1-付费 0-免费',
--   `new_or_hot` tinyint(2) NOT NULL DEFAULT '0' COMMENT '新添加或是热门，0-都不是，1-为新的，2-为热门',
--   `website_id` int(11) NOT NULL COMMENT '分站点id',
--   `position_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '职位类型[1-热门 0-最新 2-行业专区]',
--   `pos_type` int(11) DEFAULT NULL COMMENT '职位类别',
--   `order_by` tinyint(4) NOT NULL COMMENT '排序号',
--   `position_json` mediumtext NOT NULL COMMENT '职位信息json',
--   `update_user_id` int(11) NOT NULL COMMENT '操作人id',
--   `update_user_name` varchar(100) NOT NULL,
--   `update_date` datetime DEFAULT NULL COMMENT '最后修改日期',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='后台分站点首页职位信息表';
--
-- DROP TABLE `sys_hot_hunter_position`;
-- CREATE TABLE `sys_hot_hunter_position` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `com_id` int(11) DEFAULT NULL COMMENT '企业ID',
--   `pos_id` int(11) DEFAULT NULL COMMENT '职位id',
--   `com_name` varchar(100) DEFAULT NULL COMMENT '企业名称',
--   `pos_name` varchar(100) NOT NULL COMMENT '职位名称',
--   `begin_date` date NOT NULL COMMENT '开始日期',
--   `end_date` date NOT NULL COMMENT '结束日期',
--   `salary` varchar(20) NOT NULL COMMENT '年薪（单位为万元）',
--   `city` varchar(255) NOT NULL COMMENT '工作地点',
--   `url` varchar(500) NOT NULL COMMENT '职位链接地址',
--   `website_id` int(10) NOT NULL COMMENT '所属分站点id',
--   `create_date` date NOT NULL,
--   `create_user_name` varchar(100) NOT NULL COMMENT '创建人',
--   `create_user_id` int(11) NOT NULL COMMENT '创建人id',
--   `update_user_name` varchar(100) NOT NULL COMMENT '修改人',
--   `update_user_id` int(11) NOT NULL COMMENT '修改人id',
--   `del_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '职位状态，0=正常，1=删除',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=104 DEFAULT CHARSET=utf8 COMMENT='热门高薪职位信息';
--
-- DROP TABLE `sys_hot_recruit_com`;
-- CREATE TABLE `sys_hot_recruit_com` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `com_id` int(10) NOT NULL COMMENT '企业ID',
--   `logo_id` int(10) DEFAULT NULL COMMENT 'logo图片ID',
--   `position_info` varchar(1000) DEFAULT NULL,
--   `tao_label_info` varchar(500) DEFAULT NULL COMMENT '淘标签内容',
--   `buy_flag` tinyint(2) DEFAULT '0' COMMENT '购买标记[0-免费 1-付费]',
--   `link_url` varchar(150) DEFAULT NULL COMMENT 'LOGO广告链接地址 ',
--   `sort` smallint(5) DEFAULT '0' COMMENT '排序',
--   `hits` int(10) DEFAULT '0' COMMENT '点击数',
--   `create_date` datetime DEFAULT NULL COMMENT '创建日期',
--   `create_by` varchar(30) DEFAULT NULL COMMENT '创建人',
--   `edit_date` datetime DEFAULT NULL COMMENT '修改日期',
--   `edit_by` varchar(30) DEFAULT NULL COMMENT '修改人',
--   PRIMARY KEY (`id`),
--   KEY `IX_comid` (`com_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='首页热聘企业';
--
-- DROP TABLE `sys_hot_seo_key`;
-- CREATE TABLE `sys_hot_seo_key` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `catagory` char(100) DEFAULT NULL COMMENT '大类',
--   `type` char(100) DEFAULT NULL COMMENT '小类',
--   `seo_key` char(100) NOT NULL,
--   `seo_value` char(100) NOT NULL,
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `idx_seo_key` (`seo_key`) USING HASH,
--   KEY `idx_catagory` (`catagory`) USING BTREE,
--   KEY `idx_type` (`type`) USING BTREE
-- ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_hot_setting`;
-- CREATE TABLE `sys_hot_setting` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `create_man` varchar(100) NOT NULL COMMENT '创建人',
--   `create_date` date NOT NULL COMMENT '创建日期',
--   `setting_info` mediumtext NOT NULL COMMENT '热门设置json信息',
--   `static_content` mediumtext NOT NULL COMMENT '生成静态文件内容',
--   `type` tinyint(2) NOT NULL DEFAULT '1' COMMENT '热门类型，1=热门职位，2=热门类别',
--   `website_id` int(10) NOT NULL COMMENT '所属分站点id',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='热门职位设置信息表';
--
-- DROP TABLE `sys_irc_invite`;
-- CREATE TABLE `sys_irc_invite` (
--   `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `prod_id` int(10) NOT NULL COMMENT '招聘会ID',
--   `com_id` int(10) NOT NULL COMMENT '公司ID',
--   `pos_id` int(10) NOT NULL COMMENT '招聘会职位ID',
--   `per_ids` varchar(280) DEFAULT NULL COMMENT '邀请面试用户id串',
--   `per_count` smallint(6) DEFAULT '0' COMMENT '邀请用户数量',
--   `op_user_id` int(10) DEFAULT NULL COMMENT '后台操作人ID',
--   `op_user_name` varchar(20) DEFAULT NULL COMMENT '操作人姓名',
--   `op_dept_id` int(10) DEFAULT NULL COMMENT '操作人所在部门',
--   `jf_name` varchar(100) DEFAULT NULL COMMENT '招聘会名称',
--   `com_name` varchar(100) DEFAULT NULL COMMENT '公司名称',
--   `pos_name` varchar(200) DEFAULT NULL COMMENT '招聘会职位名称',
--   `create_date` datetime DEFAULT NULL COMMENT '邀请日期',
--   PRIMARY KEY (`id`),
--   KEY `IX_prodId_comId` (`prod_id`,`com_id`),
--   KEY `IX_deptId` (`op_dept_id`),
--   KEY `IX_posId` (`pos_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_log`;
-- CREATE TABLE `sys_log` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '记录id',
--   `summary` varchar(30) DEFAULT NULL COMMENT '操作内容摘要',
--   `content` longtext COMMENT '操作内容',
--   `operator_id` int(11) DEFAULT NULL COMMENT '操作人id',
--   `operator_name` varchar(30) DEFAULT NULL COMMENT '操作人姓名',
--   `create_date` datetime DEFAULT NULL COMMENT '操作时间',
--   PRIMARY KEY (`id`),
--   KEY `IX_summary` (`summary`),
--   KEY `IX_operator_name` (`operator_name`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_log_action`;
-- CREATE TABLE `sys_log_action` (
--   `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `call_method` varchar(100) NOT NULL COMMENT '调用的方法名',
--   `call_count` int(10) NOT NULL DEFAULT '0' COMMENT '方法调用的次数',
--   `count_time` datetime NOT NULL COMMENT '调用方法的时间',
--   PRIMARY KEY (`id`),
--   KEY `I_call_method` (`call_method`),
--   KEY `I_count_time` (`count_time`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=130 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_logo`;
-- CREATE TABLE `sys_logo` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '上传logo主键id',
--   `upload_id` int(10) DEFAULT '0' COMMENT '上传记录id',
--   `logo_size_id` int(10) NOT NULL COMMENT 'logo尺寸大小id',
--   `com_id` int(10) NOT NULL COMMENT '绑定公司id',
--   `logo_path` varchar(150) NOT NULL COMMENT 'logo相对路径',
--   `logo_name` varchar(100) NOT NULL COMMENT 'logo名称',
--   `com_name` varchar(255) DEFAULT NULL COMMENT '公司名称',
--   `logo_type` tinyint(2) DEFAULT NULL COMMENT 'LOGO类型[1-图片 2-FLASH]',
--   `descript` varchar(100) DEFAULT NULL COMMENT '描述',
--   `create_date` datetime DEFAULT NULL,
--   `create_by` varchar(20) DEFAULT NULL,
--   PRIMARY KEY (`id`),
--   KEY `IX_com_id` (`com_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=10781 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_logo_crm_item`;
-- CREATE TABLE `sys_logo_crm_item` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `site_id` int(10) NOT NULL COMMENT '站点ID',
--   `logo_file_id` int(10) NOT NULL COMMENT '后台系统LOGO看板文件ID',
--   `crm_site_name` varchar(100) NOT NULL COMMENT 'CRM中显示的站点名称',
--   `sort` int(10) DEFAULT '0' COMMENT 'LOGO看板文件排序',
--   `create_date` datetime DEFAULT NULL COMMENT '创建日期',
--   `create_by` varchar(30) DEFAULT NULL COMMENT '创建人',
--   `edit_date` datetime DEFAULT NULL COMMENT '修改日期',
--   `edit_by` varchar(30) DEFAULT NULL COMMENT '修改人',
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `IX_logoFileId` (`logo_file_id`),
--   KEY `IX_siteId` (`site_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_logo_file`;
-- CREATE TABLE `sys_logo_file` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'logo文件id',
--   `site_id` int(10) NOT NULL COMMENT '站点id',
--   `file_name` varchar(50) NOT NULL COMMENT '文件名称',
--   `state` tinyint(2) DEFAULT '1' COMMENT '状态[1-启用 0-停用]',
--   `template_id` int(10) DEFAULT NULL COMMENT '文件模板id',
--   `descript` varchar(100) DEFAULT NULL COMMENT '描述',
--   `create_date` datetime DEFAULT NULL COMMENT '创建时间',
--   `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
--   `edit_date` datetime DEFAULT NULL COMMENT '修改时间\r\n',
--   `edit_by` varchar(20) DEFAULT NULL COMMENT '修改人',
--   `file_type` tinyint(4) DEFAULT '0' COMMENT '看板类型  0：默认  1：需要填写职位，掏标签',
--   `templete_type` int(4) DEFAULT '0' COMMENT '模板类型  0：只显示LOGO(默认)  1：知名品牌(大)  2：知名品牌(小)',
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `IX_file_name` (`file_name`),
--   KEY `IX_site_id` (`site_id`),
--   KEY `IX_template_id` (`template_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_logo_position`;
-- CREATE TABLE `sys_logo_position` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'LOGO位置ID',
--   `logo_table_id` int(10) NOT NULL COMMENT 'LOGO区域ID',
--   `logo_size_id` int(10) NOT NULL COMMENT 'logo尺寸大小id',
--   `code_no` varchar(50) NOT NULL COMMENT '位置编号',
--   `row_num` smallint(5) NOT NULL COMMENT '行号',
--   `order_by` int(10) NOT NULL COMMENT '排序',
--   `colspan` smallint(5) DEFAULT '0' COMMENT '合并列',
--   `rowspan` smallint(5) DEFAULT '0' COMMENT '合并行',
--   `create_date` datetime DEFAULT NULL COMMENT '创建时间',
--   `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
--   `edit_date` datetime DEFAULT NULL COMMENT '修改时间\r\n',
--   `edit_by` varchar(20) DEFAULT NULL COMMENT '修改人',
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `IX_code_no` (`code_no`),
--   KEY `IX_logo_tableid` (`logo_table_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=1016 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_logo_queue`;
-- CREATE TABLE `sys_logo_queue` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `resource_id` int(10) NOT NULL COMMENT 'logo资源ID',
--   `position_id` int(10) NOT NULL COMMENT 'LOGO位置ID',
--   `begin_date` date NOT NULL COMMENT '显示开始日期',
--   `end_date` date NOT NULL COMMENT '显示结束日期',
--   `buy_flag` tinyint(2) DEFAULT NULL COMMENT '购买状态[0-免费 1-付费]',
--   `create_date` datetime DEFAULT NULL COMMENT '创建日期',
--   `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
--   `edit_date` datetime DEFAULT NULL COMMENT '修改日期',
--   `edit_by` varchar(20) DEFAULT NULL COMMENT '修改人',
--   `com_short_name` varchar(100) DEFAULT NULL COMMENT '公司简称',
--   `position_info` varchar(1000) DEFAULT NULL COMMENT '职位信息内容(JSON格式内容)',
--   `taoLabel_info` varchar(1000) DEFAULT NULL COMMENT '淘标签内容(JSON格式内容)',
--   `tao_label_info` varchar(1000) DEFAULT NULL COMMENT '淘标签内容(JSON格式内容)',
--   PRIMARY KEY (`id`),
--   KEY `IX_positionId` (`position_id`),
--   KEY `IX_resourceId` (`resource_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=551 DEFAULT CHARSET=utf8 COMMENT='后台LOGO上线队列';
--
-- DROP TABLE `sys_logo_resource`;
-- CREATE TABLE `sys_logo_resource` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'logo资源id',
--   `com_id` int(10) NOT NULL COMMENT '公司id',
--   `logo_id` int(10) NOT NULL COMMENT '上传的logoid',
--   `logo_size_id` int(10) NOT NULL COMMENT 'logo尺寸大小',
--   `logo_type` tinyint(2) NOT NULL COMMENT 'logo类型[1-图片 2-flash]',
--   `link_url` varchar(150) DEFAULT NULL COMMENT '链接地址',
--   `link_type` tinyint(2) DEFAULT NULL COMMENT '链接类型[1-图片文字链接 2-flash内部链接]',
--   `descript` varchar(100) DEFAULT NULL COMMENT '内容描述',
--   `create_date` datetime DEFAULT NULL COMMENT '建创时间',
--   `create_by` varchar(20) DEFAULT NULL COMMENT '建创人',
--   `edit_date` datetime DEFAULT NULL COMMENT '改修时间',
--   `edit_by` varchar(20) DEFAULT NULL COMMENT '改修人',
--   PRIMARY KEY (`id`),
--   KEY `IX_com_id` (`com_id`),
--   KEY `IX_logo_id` (`logo_id`),
--   KEY `IX_size_id` (`logo_size_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=10727 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_logo_size`;
-- CREATE TABLE `sys_logo_size` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'logo尺寸大小id(主键id)',
--   `length` smallint(5) DEFAULT NULL COMMENT 'logo宽',
--   `width` smallint(5) DEFAULT NULL COMMENT 'logo高',
--   `state` tinyint(2) DEFAULT '1' COMMENT '是否启用(1-启用 0-停用)',
--   `descript` varchar(100) DEFAULT NULL COMMENT '描述',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_logo_table`;
-- CREATE TABLE `sys_logo_table` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `file_id` int(10) NOT NULL COMMENT '文件id',
--   `table_id` int(10) NOT NULL COMMENT '所在区域id',
--   `order_by` int(10) NOT NULL COMMENT '排序',
--   PRIMARY KEY (`id`),
--   KEY `IX_fileId` (`file_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=245 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_mail_plan`;
-- CREATE TABLE `sys_mail_plan` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '邮件状态  0：待发送  1：正在发送  2：发送完毕',
--   `start_time` timestamp NULL DEFAULT NULL COMMENT '开始执行时间',
--   `mail_title` varchar(255) DEFAULT NULL COMMENT '邮件标题',
--   `from_addr` varchar(255) DEFAULT NULL COMMENT '发送邮件地址',
--   `from_name` varchar(255) DEFAULT NULL COMMENT '发送邮件名称',
--   `send_type` tinyint(4) DEFAULT NULL COMMENT 'sendType',
--   `content` mediumtext,
--   `count_log` varchar(255) DEFAULT NULL COMMENT '统计日志',
--   `data_sql` varchar(255) DEFAULT NULL COMMENT '数据SQL',
--   `campaign_id` int(11) DEFAULT NULL COMMENT '商业邮件--活动ID',
--   `mailing_id` int(11) DEFAULT NULL COMMENT '商业邮件--邮件ID',
--   `group_id` int(11) DEFAULT NULL COMMENT '商业邮件--组ID',
--   `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
--   `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_mail_plan_mails`;
-- CREATE TABLE `sys_mail_plan_mails` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'PK',
--   `mail_plan_id` int(11) NOT NULL COMMENT '邮件计划ID',
--   `status` tinyint(4) NOT NULL COMMENT '邮件状态 -1:发送失败  0:待发送  1：发送成功',
--   `email_vals` varchar(1000) DEFAULT NULL COMMENT '邮件值',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_manufacture_logo`;
-- CREATE TABLE `sys_manufacture_logo` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '数据权限id',
--   `com_id` int(10) NOT NULL COMMENT '企业id',
--   `com_name` varchar(100) NOT NULL COMMENT '企业名称',
--   `logo_id` int(10) NOT NULL COMMENT 'logoId',
--   `logo_path` varchar(200) DEFAULT NULL,
--   `logo_name` varchar(255) DEFAULT NULL,
--   `logo_url` varchar(300) NOT NULL COMMENT '企业logo路径',
--   `position` varchar(750) NOT NULL COMMENT '职位名称JSON',
--   `field_id` int(10) NOT NULL COMMENT '展示专场的Id',
--   `show_field` varchar(200) NOT NULL COMMENT '展示专场',
--   `hit` int(10) unsigned zerofill NOT NULL COMMENT '点击量',
--   `operator` varchar(100) NOT NULL COMMENT '操作人',
--   `start_date` datetime NOT NULL COMMENT '开始日期',
--   `end_date` datetime NOT NULL COMMENT '结束日期',
--   `create_date` datetime NOT NULL COMMENT '创建时间',
--   `update_date` datetime NOT NULL COMMENT '更新时间',
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `IX_manu_moduleid` (`com_id`,`com_name`,`field_id`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=1008 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_module`;
-- CREATE TABLE `sys_module` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '模块id',
--   `parent_id` int(10) DEFAULT '0' COMMENT '父模块id',
--   `name` varchar(50) DEFAULT NULL COMMENT '模块名称',
--   `state` tinyint(2) DEFAULT '1' COMMENT '模块状态[1-激活 0-禁用]',
--   `order_by` int(10) DEFAULT '0' COMMENT '排序',
--   `descript` varchar(100) DEFAULT NULL COMMENT '模块描述',
--   `create_date` datetime DEFAULT NULL COMMENT '创建日期',
--   `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
--   `edit_date` datetime DEFAULT NULL COMMENT '修改日期',
--   `edit_by` varchar(20) DEFAULT NULL COMMENT '修改人',
--   PRIMARY KEY (`id`),
--   KEY `IX_parent_id` (`parent_id`),
--   KEY `IX_order_by` (`order_by`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_operate`;
-- CREATE TABLE `sys_operate` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '操作id',
--   `name` varchar(50) NOT NULL COMMENT '操作名称',
--   `url` varchar(150) NOT NULL COMMENT '操作url',
--   `module_id` int(10) DEFAULT '0' COMMENT '模块id',
--   `display_flag` tinyint(2) DEFAULT '0' COMMENT '是否作为菜单显示[0-否 1-是]',
--   `display_name` varchar(30) DEFAULT NULL COMMENT '菜单显示名称',
--   `sort` int(10) DEFAULT '0' COMMENT '操作排序',
--   `page_type` tinyint(2) DEFAULT '1' COMMENT '页面类型[1-page 2-frame]',
--   `descript` varchar(100) DEFAULT NULL COMMENT '描述',
--   PRIMARY KEY (`id`),
--   KEY `IX_module_id` (`module_id`),
--   KEY `IX_name` (`name`),
--   KEY `IX_sort` (`sort`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=596 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_operate_log`;
-- CREATE TABLE `sys_operate_log` (
--   `id` int(20) NOT NULL AUTO_INCREMENT,
--   `module_id` mediumint(5) NOT NULL COMMENT '所属模块id',
--   `method` varchar(100) NOT NULL COMMENT '操作方法',
--   `params` longtext NOT NULL COMMENT '操作方法参数json',
--   `description` varchar(100) NOT NULL COMMENT '操作描述',
--   `operator_name` varchar(100) NOT NULL COMMENT '操作人姓名',
--   `operator_ip` varchar(100) NOT NULL COMMENT '操作人ip',
--   `operator_account` int(10) NOT NULL COMMENT '操作人账户id',
--   `operator_account_type` tinyint(1) NOT NULL COMMENT '操作人账户类别，0=个人，1=企业，2=系统',
--   `operate_begin_date` datetime NOT NULL COMMENT '操作开始时间',
--   `operate_end_date` datetime NOT NULL COMMENT '操作结束时间',
--   `operate_time` int(9) NOT NULL COMMENT '操作消耗的时间',
--   PRIMARY KEY (`id`),
--   KEY `I_module` (`module_id`) USING BTREE,
--   KEY `I_method` (`method`) USING BTREE,
--   KEY `I_operate_time` (`operate_time`) USING BTREE,
--   KEY `I_operator_account` (`operator_account`) USING BTREE,
--   KEY `I_operator_account_type` (`operator_account_type`) USING BTREE,
--   KEY `I_operator_ip` (`operator_ip`) USING BTREE
-- ) ENGINE=MyISAM AUTO_INCREMENT=2204 DEFAULT CHARSET=utf8 COMMENT='系统操作日志表';
--
-- DROP TABLE `sys_position_audit_history`;
-- CREATE TABLE `sys_position_audit_history` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `com_id` int(10) NOT NULL COMMENT '企业id',
--   `pos_id` int(10) NOT NULL COMMENT '职位id',
--   `operator_id` int(10) NOT NULL COMMENT '后台操作人账户id',
--   `operator_name` varchar(100) NOT NULL COMMENT '后台操作人姓名',
--   `audit_type` tinyint(2) NOT NULL COMMENT '审核类型[屏蔽型=1、修改型=2、提醒型=3、通过审核=4]',
--   `audit_date` datetime NOT NULL COMMENT '审核时间',
--   `audit_info` varchar(500) NOT NULL COMMENT '审核信息',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='职位审核历史记录';
--
-- DROP TABLE `sys_position_keyword`;
-- CREATE TABLE `sys_position_keyword` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `com_id` int(10) NOT NULL COMMENT '公司id',
--   `com_name` varchar(100) NOT NULL COMMENT '公司名称',
--   `pos_id` int(10) NOT NULL COMMENT '职位id',
--   `pos_name` varchar(100) NOT NULL COMMENT '职位名称',
--   `keyword` varchar(50) NOT NULL COMMENT '搜索关键字',
--   `money` int(10) DEFAULT '0' COMMENT '购买金额',
--   `show_location` varchar(100) DEFAULT NULL COMMENT '显示区域',
--   `sort_flag` tinyint(2) DEFAULT '0' COMMENT '排序位置',
--   `valid_date` date DEFAULT NULL COMMENT '开通时间',
--   `expire_date` date DEFAULT NULL COMMENT '截止时间',
--   `remark` varchar(500) DEFAULT NULL,
--   `create_date` datetime DEFAULT NULL COMMENT '创建日期',
--   `edit_date` datetime DEFAULT NULL,
--   `check_user` varchar(50) NOT NULL COMMENT '操作人',
--   PRIMARY KEY (`id`),
--   KEY `IX_keyword` (`keyword`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='职位关键字排名';
--
-- DROP TABLE `sys_resume_audit_history`;
-- CREATE TABLE `sys_resume_audit_history` (
--   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `resume_id` int(11) NOT NULL COMMENT '简历id',
--   `history_id` int(11) DEFAULT NULL COMMENT '操作历史记录id',
--   `operator_id` int(11) NOT NULL COMMENT '后台操作人账户id',
--   `operator_name` varchar(100) NOT NULL COMMENT '后台操作人姓名',
--   `pass` tinyint(2) NOT NULL COMMENT '是否通过审核1=通过，2=未通过',
--   `audit_date` datetime NOT NULL COMMENT '审核时间',
--   `audit_info` varchar(500) NOT NULL COMMENT '审核信息',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=189 DEFAULT CHARSET=utf8 COMMENT='简历审核历史记录';
--
-- DROP TABLE `sys_resume_follow_up`;
-- CREATE TABLE `sys_resume_follow_up` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'PK',
--   `resume_id` int(10) DEFAULT NULL COMMENT '简历ID',
--   `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
--   `gender` tinyint(4) DEFAULT NULL COMMENT '性别：1为男，2为女',
--   `phone` varchar(255) DEFAULT NULL COMMENT '电话',
--   `mobile` varchar(255) DEFAULT NULL COMMENT '移动电话',
--   `perfect_num` float DEFAULT NULL COMMENT '简历完整率',
--   `pass` smallint(4) DEFAULT NULL COMMENT '简历状态',
--   `resume_create_date` timestamp NULL DEFAULT NULL COMMENT '简历注册日期',
--   `export_date` timestamp NULL DEFAULT NULL COMMENT '导出日期',
--   PRIMARY KEY (`id`),
--   KEY `sys_resume_follow_up_resume_id` (`resume_id`),
--   KEY `sys_resume_follow_up_user_name` (`user_name`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=7854 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_resume_operator_history`;
-- CREATE TABLE `sys_resume_operator_history` (
--   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `user_id` int(11) NOT NULL COMMENT '用户id',
--   `resume_id` int(11) DEFAULT NULL COMMENT '简历id',
--   `operator_name` varchar(100) NOT NULL COMMENT '操作人姓名',
--   `content` varchar(100) NOT NULL COMMENT '操作描述',
--   `operator_date` datetime NOT NULL COMMENT '操作日期',
--   `status` varchar(100) NOT NULL COMMENT '操作状态',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=303 DEFAULT CHARSET=utf8 COMMENT='后台用户操作日志表';
--
-- DROP TABLE `sys_resume_pos_recommand_history`;
-- CREATE TABLE `sys_resume_pos_recommand_history` (
--   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `user_id` int(11) NOT NULL COMMENT '系统用户id',
--   `user_name` varchar(100) NOT NULL COMMENT '系统用户名称',
--   `dept_id` int(11) NOT NULL COMMENT '系统用户所属部门id',
--   `per_ids` varchar(500) DEFAULT NULL COMMENT '推荐的求职者id列表',
--   `pos_ids` varchar(500) DEFAULT NULL COMMENT '推荐的职位id列表',
--   `recommand_result` mediumtext COMMENT '推荐结果json',
--   `per_count` tinyint(2) NOT NULL COMMENT '推荐的求职者数量',
--   `pos_count` tinyint(2) NOT NULL COMMENT '推荐的职位数量',
--   `recommand_type` tinyint(2) NOT NULL COMMENT '推荐类型',
--   `recommand_date` datetime NOT NULL COMMENT '推荐时间',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=404 DEFAULT CHARSET=utf8 COMMENT='职位/简历互荐系统记录表';
--
-- DROP TABLE `sys_resume_stat`;
-- CREATE TABLE `sys_resume_stat` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `com_id` int(10) NOT NULL COMMENT '企业ID',
--   `position_id` int(10) NOT NULL COMMENT '职位ID',
--   `delivery_qty` int(11) DEFAULT NULL COMMENT '主动投递简数量',
--   `buy_qty` int(11) DEFAULT NULL COMMENT '企业购买简数量',
--   `recommend_qty` int(11) DEFAULT NULL COMMENT '后台推荐简历数量',
--   `count_month` int(11) DEFAULT NULL COMMENT '第几月的统计数据（一年中的第X个月）',
--   `count_week` smallint(6) DEFAULT NULL COMMENT '第几周的统计数据（一年中的第X周）',
--   `count_year` smallint(6) DEFAULT NULL COMMENT '统计的年份',
--   `count_type` smallint(6) NOT NULL COMMENT '统计的类型,1为按月统计，2为按周统计',
--   `create_date` datetime DEFAULT NULL COMMENT '生成数据的时间',
--   PRIMARY KEY (`id`),
--   KEY `index_com_position_id` (`com_id`,`position_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=7102884 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_role`;
-- CREATE TABLE `sys_role` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '色角id',
--   `name` varchar(50) NOT NULL COMMENT '色角名称',
--   `descript` varchar(100) DEFAULT NULL COMMENT '角色描述',
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `IX_name` (`name`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_role_operate`;
-- CREATE TABLE `sys_role_operate` (
--   `role_id` int(10) NOT NULL COMMENT '角色id',
--   `operate_id` int(10) NOT NULL COMMENT '操作id',
--   PRIMARY KEY (`role_id`,`operate_id`)
-- ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_scroll_adv`;
-- CREATE TABLE `sys_scroll_adv` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `name` varchar(100) NOT NULL COMMENT '广告名称',
--   `site_name` varchar(100) DEFAULT NULL,
--   `site_id` int(10) NOT NULL COMMENT '分站点id',
--   `description` varchar(400) DEFAULT NULL COMMENT '描述',
--   `width` int(10) DEFAULT NULL COMMENT '图片宽度',
--   `height` int(10) DEFAULT NULL COMMENT '图片高度',
--   `site_ids` varchar(255) DEFAULT NULL COMMENT '站点ids,多个站点用List<Integer> toJson 保存',
--   `site_names` varchar(500) DEFAULT NULL COMMENT '站点names,多个站点用List<String> toJson 保存',
--   PRIMARY KEY (`id`),
--   KEY `i_site_id` (`site_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_scroll_logo`;
-- CREATE TABLE `sys_scroll_logo` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `title` varchar(100) DEFAULT NULL COMMENT '图片标题',
--   `img_url` varchar(100) NOT NULL COMMENT '图片地址url',
--   `begin_date` datetime DEFAULT NULL COMMENT '开始日期',
--   `end_date` datetime DEFAULT NULL COMMENT '截止日期',
--   `a_url` varchar(100) DEFAULT NULL COMMENT '链接地址url',
--   `order_num` int(10) DEFAULT NULL COMMENT '序号',
--   `adv_id` int(10) NOT NULL COMMENT '企鹅并广告主表记录的id',
--   `template` int(10) DEFAULT NULL COMMENT '模板编码',
--   `is_nofollow` int(11) DEFAULT '0' COMMENT '是否nofllow',
--   PRIMARY KEY (`id`),
--   KEY `i_adv_id` (`adv_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_site_pos_resume_statistics`;
-- CREATE TABLE `sys_site_pos_resume_statistics` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `work_location` int(10) NOT NULL COMMENT '工作地区',
--   `pos_type` int(10) NOT NULL COMMENT '职位类别 ',
--   `req_degree` tinyint(4) NOT NULL COMMENT '学历要求，0=不限；1=初中；2=高中；3=中专；\r\n       4=大专；5=本科；6=硕士；7=MBA；8=博士',
--   `type` tinyint(2) NOT NULL COMMENT '类别：0=职位；1=简历',
--   `add_sta_count` int(10) NOT NULL DEFAULT '0' COMMENT '增量统计数量',
--   `save_sta_count` int(10) NOT NULL DEFAULT '0' COMMENT '存量统计数量',
--   `create_date` date NOT NULL COMMENT '每次统计时间',
--   PRIMARY KEY (`id`),
--   KEY `IX_req_degree` (`req_degree`),
--   KEY `IX_type` (`type`),
--   KEY `IX_create_date` (`create_date`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_special_subject`;
-- CREATE TABLE `sys_special_subject` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `sp_name` varchar(50) DEFAULT NULL,
--   `sp_id` int(11) DEFAULT '0',
--   `sp_path` varchar(100) DEFAULT NULL,
--   `sp_desc` longtext,
--   `sp_url` varchar(100) DEFAULT NULL,
--   `category` tinyint(4) DEFAULT NULL,
--   `type` tinyint(4) DEFAULT NULL,
--   `create_date` datetime DEFAULT NULL,
--   `editor` varchar(20) DEFAULT NULL,
--   `sp_other` mediumtext,
--   `sp_sort` tinyint(4) DEFAULT '0',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=6281 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_sub_site_friend_link_info`;
-- CREATE TABLE `sys_sub_site_friend_link_info` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `file_name` varchar(100) NOT NULL COMMENT '文件名',
--   `description` varchar(500) DEFAULT NULL COMMENT '描述',
--   `site_id` int(10) NOT NULL COMMENT '分站点id',
--   `site_name` varchar(100) DEFAULT NULL COMMENT '分站点名称',
--   `per_user_id` int(10) DEFAULT NULL COMMENT '创建人id',
--   `per_user_name` varchar(100) DEFAULT NULL COMMENT '创建人名称',
--   `create_date` datetime DEFAULT NULL COMMENT '创建日期',
--   `qq` varchar(100) DEFAULT NULL COMMENT '联系人qq',
--   PRIMARY KEY (`id`),
--   KEY `i_site_id` (`site_id`),
--   KEY `i_file_name` (`file_name`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_sub_site_seo`;
-- CREATE TABLE `sys_sub_site_seo` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `site_id` int(10) NOT NULL COMMENT '对应分站点的id',
--   `site_name` varchar(100) DEFAULT NULL COMMENT '分站点名称',
--   `title` varchar(100) DEFAULT NULL COMMENT '页面的标题',
--   `keyword` varchar(500) DEFAULT NULL COMMENT '页面的关键字',
--   `description` varchar(500) DEFAULT NULL COMMENT '页面的描述',
--   PRIMARY KEY (`id`),
--   KEY `i_site_name` (`site_name`),
--   KEY `i_site_id` (`site_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=154 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_sur_name`;
-- CREATE TABLE `sys_sur_name` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `sur_name` varchar(50) NOT NULL COMMENT '姓式',
--   `del_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '数据删除状态0=正常，-1=已被删除',
--   `create_date` datetime NOT NULL COMMENT '创建时间',
--   `update_user_id` int(11) DEFAULT NULL COMMENT '修改人用户id',
--   `update_user_name` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
--   `update_date` datetime DEFAULT NULL COMMENT '修改时间',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=1592 DEFAULT CHARSET=utf8 COMMENT='百家姓数据表';

DROP TABLE `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `account` varchar(20) NOT NULL COMMENT '账号',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `name` varchar(20) NOT NULL COMMENT '用户名',
  `dept_id` int(10) DEFAULT NULL COMMENT '部门id',
  `work_number` varchar(20) DEFAULT NULL COMMENT '用户工号',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `mobile` varchar(30) DEFAULT NULL COMMENT '手机号码',
  `user_email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `internal_email` varchar(50) DEFAULT NULL COMMENT '内部邮箱',
  `state` varchar(2) DEFAULT NULL COMMENT '状态',
  `grade` smallint(5) DEFAULT NULL COMMENT '等级',
  `web_site_right` varchar(500) DEFAULT NULL COMMENT '分站点权限',
  `last_login_ip` varchar(20) DEFAULT NULL COMMENT '上次登陆ip',
  `last_login_host` varchar(100) DEFAULT NULL COMMENT '上次登陆主机地址',
  `last_login_date` datetime DEFAULT NULL COMMENT '上次登陆日期',
  `first_login_date` datetime DEFAULT NULL COMMENT '首次登陆日期',
  `login_count` bigint(15) DEFAULT '0' COMMENT '登陆次数',
  `advisor_flag` tinyint(2) DEFAULT NULL COMMENT '是否顾问',
  `score` int(10) DEFAULT '100' COMMENT '分数',
  `fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `avatar_path` varchar(150) DEFAULT NULL COMMENT '用户头像路径',
  `avatar_name` varchar(100) DEFAULT NULL COMMENT '用户头像名称',
  `resume_num` int(10) DEFAULT '150' COMMENT '可下载的简历数量',
  `used_num` int(10) DEFAULT '0' COMMENT '已下载的简历数量',
  `global_call` varchar(30) DEFAULT '95105333' COMMENT '全国呼叫中心',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `edit_date` datetime DEFAULT NULL COMMENT '修改日期',
  `edit_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IX_accounts` (`account`),
  KEY `IX_name` (`name`),
  KEY `IX_work_number` (`work_number`),
  KEY `IX_deptId` (`dept_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5716 DEFAULT CHARSET=utf8 COMMENT='后台用户信息表';

DROP TABLE `sys_user_login_history`;
CREATE TABLE `sys_user_login_history` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `accounts` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `user_grade` tinyint(2) DEFAULT NULL,
  `login_date` datetime NOT NULL,
  `login_ip` varchar(100) NOT NULL,
  `login_city` varchar(100) DEFAULT NULL,
  `dept` int(10) DEFAULT NULL,
  `internal_email` varchar(100) DEFAULT NULL,
  `sys_user_id` int(10) NOT NULL,
  `super_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

DROP TABLE `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `role_id` int(10) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- DROP TABLE `sys_web_site`;
-- CREATE TABLE `sys_web_site` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `name` varchar(30) NOT NULL COMMENT '站点中文名称',
--   `name_en` varchar(50) DEFAULT NULL COMMENT '站点英文名称',
--   `site_location` varchar(1000) DEFAULT NULL COMMENT '站点包含的企业区域，以“，”分割',
--   `open_flag` tinyint(2) DEFAULT '1' COMMENT '打开标记[0-关闭 1-打开]',
--   `site_code` varchar(10) DEFAULT NULL COMMENT '分站点代码',
--   `site_type` tinyint(2) DEFAULT '2' COMMENT '分站点类型[0-总公司 1-分公司 2-分站点 3-其他 4-行业专区]',
--   `descript` varchar(1000) DEFAULT NULL COMMENT '站点描述',
--   `operate_date` datetime DEFAULT NULL COMMENT '操作时间',
--   `operator` varchar(30) DEFAULT NULL COMMENT '操作人',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=791 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_wechat_activity`;
-- CREATE TABLE `sys_wechat_activity` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键，默认情况下生效时将以该值作为二维码中的参数',
--   `sence_id` int(10) DEFAULT NULL COMMENT '实际上二维码的sceneId，初步设想为永久二维码（范围只能从1~100000）',
--   `activity_name` varchar(100) NOT NULL COMMENT '活动名称',
--   `is_effective` int(3) NOT NULL DEFAULT '0' COMMENT '该活动是否生效',
--   `file_name` varchar(100) DEFAULT NULL COMMENT '微信获取得到的二维码在本地网站的url',
--   `update_date` datetime NOT NULL COMMENT '活动更新时间',
--   `updator` varchar(100) DEFAULT NULL COMMENT '更新人',
--   `updator_id` int(10) DEFAULT NULL COMMENT '更新人账户记录主键',
--   `description` varchar(1000) DEFAULT NULL COMMENT '描述',
--   `is_deleted` int(3) NOT NULL DEFAULT '0' COMMENT '是否被删除，0：否，1：是',
--   `img_domain` varchar(100) DEFAULT NULL COMMENT '保存二维码图片的服务器域名',
--   `scan_count` int(10) DEFAULT '0' COMMENT '微信二维码扫描的次数',
--   `msg` text COMMENT '用户扫描二维码后推送的客服消息',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=100004 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `sys_wechat_count`;
-- CREATE TABLE `sys_wechat_count` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `add_amout` int(11) DEFAULT '0' COMMENT '数量',
--   `type` int(11) NOT NULL COMMENT '数量类型，1:微信关注这数量',
--   `create_date` date NOT NULL COMMENT '统计时间',
--   `save_amout` int(11) DEFAULT '0' COMMENT '存量',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `talebase_com_test_apply`;
-- CREATE TABLE `talebase_com_test_apply` (
--   `order_id` int(10) NOT NULL COMMENT '订单号',
--   `com_id` int(10) NOT NULL COMMENT '企业id',
--   `amount` float(10,2) NOT NULL COMMENT '购买金额',
--   `count` int(10) NOT NULL COMMENT '购买总数',
--   `overplus` int(10) NOT NULL DEFAULT '0' COMMENT '购买剩余',
--   `apply_date` datetime NOT NULL COMMENT '申请日期',
--   `create_date` datetime NOT NULL COMMENT '开通日期',
--   `end_date` datetime NOT NULL COMMENT '有效日期',
--   `update_date` datetime NOT NULL COMMENT '最后编辑日期',
--   PRIMARY KEY (`order_id`),
--   KEY `IX_comId` (`com_id`)
-- ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--
-- DROP TABLE `talebase_com_test_auto`;
-- CREATE TABLE `talebase_com_test_auto` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `com_id` int(10) NOT NULL COMMENT '企业ID',
--   `pos_id` int(10) NOT NULL COMMENT '职位ID',
--   `product_id` int(10) NOT NULL COMMENT '产品id',
--   `complete_cycle` int(2) NOT NULL COMMENT '完成期限：1、1周内；2、2周内；3、1月内。',
--   PRIMARY KEY (`id`),
--   KEY `IX_comId` (`com_id`),
--   KEY `IX_posId` (`pos_id`),
--   KEY `IX_productId` (`product_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `talebase_com_test_dependent`;
-- CREATE TABLE `talebase_com_test_dependent` (
--   `order_id` int(10) NOT NULL COMMENT '订单号',
--   `product_id` int(10) NOT NULL COMMENT '产品id',
--   `product_total` int(10) DEFAULT NULL COMMENT '产品总量',
--   `product_overplus` int(10) DEFAULT NULL COMMENT '产品剩余',
--   `product_sum` int(10) DEFAULT NULL COMMENT '产品记录数',
--   PRIMARY KEY (`order_id`,`product_id`)
-- ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--
-- DROP TABLE `talebase_com_test_pro_rep_type`;
-- CREATE TABLE `talebase_com_test_pro_rep_type` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `report_id` int(10) NOT NULL COMMENT '报告类型ID',
--   `product_id` int(10) NOT NULL COMMENT '产品ID',
--   `report_name` varchar(100) NOT NULL COMMENT '报告类型名称',
--   `report_type` int(2) NOT NULL COMMENT '报告类型分（ 1：个人版， ：个人版， 2：企业版， 4：团队版）',
--   PRIMARY KEY (`id`),
--   KEY `IX_reportId` (`report_id`),
--   KEY `IX_productId` (`product_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `talebase_com_test_product`;
-- CREATE TABLE `talebase_com_test_product` (
--   `product_id` int(10) NOT NULL COMMENT '产品id',
--   `product_name` varchar(100) NOT NULL COMMENT '产品名称',
--   `product_logo` varchar(100) DEFAULT NULL COMMENT '产品Logo',
--   `product_number` varchar(30) DEFAULT NULL COMMENT '产品编号',
--   `product_intro` varchar(500) DEFAULT NULL COMMENT '产品介绍',
--   `product_desc` varchar(500) DEFAULT NULL COMMENT '产品描述',
--   `notice` varchar(500) DEFAULT NULL COMMENT '注意事项',
--   `quiz_count` int(10) NOT NULL COMMENT '题目数量',
--   PRIMARY KEY (`product_id`)
-- ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--
-- DROP TABLE `talebase_com_test_report`;
-- CREATE TABLE `talebase_com_test_report` (
--   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `per_id` int(10) NOT NULL COMMENT '用户ID',
--   `resume_id` int(10) NOT NULL COMMENT '简历Id',
--   `com_id` int(10) NOT NULL COMMENT '企业ID',
--   `pos_id` int(10) NOT NULL COMMENT '职位ID',
--   `product_id` int(10) NOT NULL COMMENT '产品id',
--   `state` int(2) NOT NULL DEFAULT '0' COMMENT '状态：0、未开始；1、进行中；2、完成提交；3，超时强制提交。',
--   `complete_num` int(4) DEFAULT '0' COMMENT '完成题数',
--   `token_id` varchar(100) DEFAULT NULL,
--   `user_exam_id` int(20) DEFAULT NULL,
--   `file_path` varchar(255) DEFAULT NULL,
--   `content` mediumtext,
--   `score` float(10,2) DEFAULT NULL,
--   `invite_date` datetime DEFAULT NULL COMMENT '邀请时间',
--   `end_date` datetime DEFAULT NULL COMMENT '有效日期',
--   `complete_date` datetime DEFAULT NULL COMMENT '完成时间',
--   `is_read` int(2) DEFAULT '0',
--   PRIMARY KEY (`id`),
--   KEY `IX_perId` (`per_id`),
--   KEY `IX_comId` (`com_id`),
--   KEY `IX_posId` (`pos_id`),
--   KEY `IX_productId` (`product_id`),
--   KEY `IX_inviteDate` (`invite_date`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `talebase_order`;
-- CREATE TABLE `talebase_order` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `order_no` varchar(32) NOT NULL,
--   `user_id` int(11) NOT NULL,
--   `user_name` varchar(20) DEFAULT NULL,
--   `user_email` varchar(80) DEFAULT NULL,
--   `user_mobile` varchar(20) DEFAULT NULL,
--   `user_phone` varchar(20) DEFAULT NULL,
--   `client_ip` varchar(20) DEFAULT NULL,
--   `product_id` varchar(20) NOT NULL,
--   `product_name` varchar(30) DEFAULT NULL,
--   `order_type` smallint(4) DEFAULT NULL,
--   `price` decimal(10,2) NOT NULL,
--   `quantity` smallint(6) NOT NULL,
--   `amount` decimal(10,2) NOT NULL,
--   `money_type` varchar(30) DEFAULT NULL,
--   `remark` varchar(250) DEFAULT NULL,
--   `payment_info` varchar(500) DEFAULT NULL,
--   `pay_way` varchar(20) DEFAULT NULL,
--   `pay_status` smallint(4) DEFAULT '0',
--   `create_time` datetime DEFAULT NULL,
--   `pay_time` datetime DEFAULT NULL,
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `IX_order_no` (`order_no`),
--   KEY `IX_user_name` (`user_name`),
--   KEY `IX_user_mobile` (`user_mobile`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `upload_info`;
-- CREATE TABLE `upload_info` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT,
--   `upload_path` varchar(100) NOT NULL,
--   `upload_uri` varchar(100) NOT NULL,
--   `upload_type` varchar(100) NOT NULL,
--   `upload_relate_id` int(10) NOT NULL,
--   `upload_time` datetime NOT NULL,
--   `upload_size` bigint(20) DEFAULT NULL,
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=1607 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `user_count_test`;
-- CREATE TABLE `user_count_test` (
--   `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
--   `account` varchar(100) DEFAULT NULL COMMENT '账号',
--   `password` varchar(100) DEFAULT NULL COMMENT '密码',
--   `preaccountid` int(20) DEFAULT NULL COMMENT '前置账号id(父账号)',
--   `comfrom` tinyint(2) DEFAULT NULL COMMENT '账户来源 0为智通人才网，其他待定',
--   `isper` bit(1) DEFAULT NULL COMMENT '是否为个人会员,0为是',
--   `iscom` bit(1) DEFAULT NULL COMMENT '是否为企业会员0为是',
--   `createtime` datetime DEFAULT NULL COMMENT '建立时间',
--   `revtime` datetime DEFAULT NULL COMMENT '修改时间',
--   `logintime` datetime DEFAULT NULL COMMENT '最后登陆时间',
--   `loginip` char(20) DEFAULT NULL COMMENT '最后登陆ip地址',
--   `issafe` bit(1) DEFAULT b'0' COMMENT '账户是否安全，1为异常,2为失效账号',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--
-- DROP TABLE `video_power_com`;
-- CREATE TABLE `video_power_com` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `com_id` int(10) DEFAULT NULL COMMENT '公司ID',
--   `com_name` varchar(100) DEFAULT NULL COMMENT '公司名称',
--   `time_used` int(10) DEFAULT NULL COMMENT '已用时长(分)',
--   `time_total` int(10) DEFAULT NULL COMMENT '总可用时长(分)',
--   `valid` tinyint(2) DEFAULT NULL COMMENT '账户是否可用(0:不可用 1:可用)',
--   `remark` varchar(500) DEFAULT NULL COMMENT '备注',
--   `start_time` datetime DEFAULT NULL COMMENT '有效开始时间',
--   `end_time` datetime DEFAULT NULL COMMENT '有效结束时间',
--   PRIMARY KEY (`id`),
--   KEY `com_id_index` (`com_id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=707 DEFAULT CHARSET=utf8;
--
-- DROP TABLE `wechat_activity_scan`;
-- CREATE TABLE `wechat_activity_scan` (
--   `id` int(10) NOT NULL AUTO_INCREMENT,
--   `sence_id` int(10) NOT NULL COMMENT '二维码中的参数，据此区分活动',
--   `open_id` varchar(100) NOT NULL COMMENT '微信账户的唯一标识',
--   `create_date` datetime NOT NULL COMMENT '记录创建的时间（相当于用户扫描二维码的时间）',
--   `activity_id` int(10) NOT NULL COMMENT '活动id（当出现同一senceId不同活动的情况时用于区分）',
--   PRIMARY KEY (`id`)
-- ) ENGINE=MyISAM AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;


