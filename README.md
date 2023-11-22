# java_springboot-vue-
java仓库管理
# 仓库管理系统——项目概述
后端：Springboot,mybatis-plus,java
前端：nondejs,vue脚手架,element-ui
数据库：mysql
# 项目运行
创建数据库表：

create table USER
(
	id int auto_increment comment '主键' primary key,
	no VARCHAR(20) null comment '账号',
	name VARCHAR(100) not null comment '名字',
	password varchar(20) not null comment '密码',
	age int null,
	sex int null comment '性别',
	phone VARCHAR(20) null COMMENT '电话',
	role_id int null comment '角色 0超级管理员，1管理员，2普通账号',
	isValid VARCHAR(4) default 'Y' null comment '是否有效，Y有效，其他无效'
)
charset=utf8; 
