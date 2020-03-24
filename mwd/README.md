
## How to run

```bash
./mvnw spring-boot:run
```


## Links

* [Dubbo](https://github.com/apache/incubator-dubbo)
* [Dubbo Spring Boot](https://github.com/apache/incubator-dubbo-spring-boot-project) - Spring Boot Project for Dubbo.
* [Developer Mailing list](https://github.com/apache/incubator-dubbo/issues/1393) - Any questions or suggestions? [Subscribe](https://github.com/apache/incubator-dubbo/issues/1393) to (dev@dubbo.incubator.apache.org) to discuss with us.
* [Gitter channel](https://gitter.im/alibaba/dubbo) - Online chat room with Dubbo developers.
* [Dubbo user manual(English)](http://dubbo.apache.org/books/dubbo-user-book-en/) or [Dubbo用户手册(中文)](http://dubbo.apache.org/books/dubbo-user-book/) - Describe how to use Dubbo and all features of Dubbo concretely.
* [Dubbo developer guide(English)](http://dubbo.apache.org/books/dubbo-dev-book-en/) or [Dubbo开发手册(中文)](http://dubbo.apache.org/books/dubbo-dev-book/) - Detailly introduce the design principal, extension mechanisms, code conventions, version control and building project, etc.
* [Dubbo admin manual(English)](http://dubbo.apache.org/books/dubbo-admin-book-en/) or [Dubbo管理手册(中文)](http://dubbo.apache.org/books/dubbo-admin-book/) - Describe how to use Dubbo registry and admin-console.



###用户表
CREATE TABLE `t_user` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `user_account` varchar(20) NOT NULL COMMENT '用户账号',
  `user_name` varchar(255) NOT NULL COMMENT '用户姓名',
  `user_password` varchar(20) NOT NULL COMMENT '用户密码',
  `status` tinyint(2) NOT NULL COMMENT '用户状态',
  `role_type` varchar(255) NOT NULL COMMENT '用户类型',
  `user_mail` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `user_phone` varchar(255) DEFAULT NULL COMMENT '用户电话',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `t_user_account` (`user_account`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

###多媒体作品表
CREATE TABLE `t_media_work` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `media_name` varchar(255) NOT NULL COMMENT '作品名称',
  `media_remark` varchar(255) DEFAULT NULL COMMENT '作品备注',
  `media_cover` varchar(255) NOT NULL COMMENT '作品封面地址',
  `media_url` varchar(255) NOT NULL COMMENT '作品内容地址',
  `media_type` varchar(20) NOT NULL COMMENT '作品所属类型ID',
  `popular_num` int(11) DEFAULT NULL COMMENT '作品人气数量',
  `recommend_num` int(11) DEFAULT NULL COMMENT '作品推荐数量',
  `discuss_num` int(11) DEFAULT NULL COMMENT '作品评论数量',
  `media_grade` double(255,1) DEFAULT NULL COMMENT '作品评分',
  `upload_user` varchar(20) NOT NULL COMMENT '作品发布人',
  `approval_user` varchar(20) DEFAULT NULL COMMENT '作品审批人',
  `approval_remark` varchar(255) DEFAULT NULL COMMENT '作品审批意见',
  `status` tinyint(2) NOT NULL COMMENT '作品状态',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `t_id` (`id`) USING BTREE,
  KEY `t_upload_user` (`upload_user`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


###评论表
CREATE TABLE `t_discuss` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `media_id` varchar(20) NOT NULL COMMENT '评论所属作品ID',
  `discuss_user` varchar(20) DEFAULT NULL COMMENT '评论发起人',
  `content` varchar(255) NOT NULL COMMENT '评论内容',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `t_media_id` (`media_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


###作品类型表
CREATE TABLE `t_media_type` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `type_name` varchar(255) NOT NULL COMMENT '类型名称',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


###作品收藏表
CREATE TABLE `t_bookmark` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `user_account` varchar(20) NOT NULL COMMENT '作品收藏人',
  `media_id` varchar(20) NOT NULL COMMENT '收藏作品',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `t_user_account` (`user_account`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


###新闻公告表
CREATE TABLE `t_news` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `publish_user` varchar(20) NOT NULL COMMENT '新闻发布人',
  `content` blob COMMENT '新闻内容',
  `duration` varchar(255) DEFAULT NULL COMMENT '新闻附件地址',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


###用户消息表
CREATE TABLE `t_information` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `propose_user` varchar(20) DEFAULT NULL COMMENT '消息提出人',
  `propose_content` varchar(255) DEFAULT NULL COMMENT '消息内容',
  `reply_user` varchar(20) DEFAULT NULL COMMENT '消息回复人',
  `reply_content` varchar(255) DEFAULT NULL COMMENT '消息回复内容',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `t_propose_user` (`propose_user`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


###定时任务表
CREATE TABLE `t_job` (
  `id` char(19) NOT NULL COMMENT '主键',
  `job_name` varchar(50) NOT NULL COMMENT '任务名称',
  `job_group` varchar(50) NOT NULL COMMENT '任务组',
  `method_name` varchar(150) NOT NULL COMMENT '要执行的方法',
  `bean_clazz` varchar(200) NOT NULL COMMENT '定时任务所在的类路径',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '任务状态 1：启用  0：禁用',
  `cron_expression` varchar(50) NOT NULL COMMENT '时间表达式',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` varchar(50) NOT NULL COMMENT '创建人(冗余账号和姓名)',
  `update_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` varchar(50) NOT NULL COMMENT '更新人(冗余账号和姓名)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_t_job_name_and_group` (`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务表';


































