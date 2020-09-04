-- 角色表
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`(
    `id` bigint NOT NULL auto_increment COMMENT '主键',
    `name` varchar(30) COMMENT '角色名称',
    `perms` varchar(30) COMMENT '角色标识',
    `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态:1-正常0-禁用',
    `remark` varchar(150) DEFAULT NULL COMMENT '备注',
    `create_time` datetime DEFAULT now() COMMENT '创建时间',
    `created_by` varchar(50) DEFAULT NULL COMMENT '创建者',
    `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
    `modified_by` varchar(50) DEFAULT NULL COMMENT '更新者',
    PRIMARY KEY (`id`),
    unique key unique_role_perms(`perms`)
) DEFAULT CHARSET=utf8 COMMENT='角色表';

-- 权限表
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`(
    `id` bigint NOT NULL auto_increment COMMENT '主键',
    `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '父ID',
    `name` varchar(30) COMMENT '权限名称',
    `perms` varchar(30) COMMENT '权限标识',
    `type` char(1) DEFAULT 0 COMMENT '类型:0-目录1-菜单2-按钮',
    `path` varchar(120) DEFAULT NULL COMMENT '路径',
    `icon` varchar(120) DEFAULT NULL COMMENT '图标',
    `method` varchar(20) DEFAULT NULL COMMENT '方法',
    `sort` int(10) DEFAULT 0 COMMENT '排序',
    `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态:1-正常0-禁用',
    `remark` varchar(150) DEFAULT NULL COMMENT '备注',
    `create_time` datetime DEFAULT now() COMMENT '创建时间',
    `created_by` varchar(50) DEFAULT NULL COMMENT '创建者',
    `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
    `modified_by` varchar(50) DEFAULT NULL COMMENT '更新者',
    PRIMARY KEY (`id`),
    unique key unique_permission_perms(`perms`)
) DEFAULT CHARSET=utf8 COMMENT='权限表';

-- 用户表
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`(
    `id` bigint NOT NULL auto_increment COMMENT '主键',
    `username` varchar(30) NOT NULL COMMENT '用户名',
    `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
    `phone` varchar(100) DEFAULT NULL COMMENT '手机',
    `password` varchar(100) NOT NULL COMMENT '密码',
    `salt` varchar(60) DEFAULT NULL COMMENT '盐',
    `lock_state` char(1) NOT NULL DEFAULT '1' COMMENT '状态:1-正常0-禁用2-注销',
    `create_time` datetime DEFAULT now() COMMENT '创建时间',
    `created_by` varchar(50) DEFAULT NULL COMMENT '创建者',
    `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
    `modified_by` varchar(50) DEFAULT NULL COMMENT '更新者',
    `deleted` char(1) DEFAULT '0' COMMENT '数据删除',
    PRIMARY KEY(`id`),
    UNIQUE INDEX (`username`)
) DEFAULT CHARSET=utf8 COMMENT='用户表';

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`(
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `role_id` bigint NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`)
) DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`(
    `role_id` bigint NOT NULL COMMENT '角色ID',
    `permission_id` bigint NOT NULL COMMENT '权限ID',
    PRIMARY KEY (`permission_id`, `role_id`)
) DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

insert into tb_user (id, username, email, password, created_by) values ('1', 'root', '2554236279@qq.com', '$2a$10$oIiv6JA7K0e4U17Ld6ACKeIKyFFEEs3jLrdzM6rE56sds07TxVQt2', '__SYS_INIT__');
insert into tb_user (id, username, email, password, created_by) values ('2', 'user', '2554236279@qq.com', '$2a$10$xbzY.yGxNq6RE.dNBEmTGOAy2fgzVrjHAuAwL4v0jGeDFE/136u5i', '__SYS_INIT__');

insert into sys_role (id, name, perms, created_by) values ('1', '超级管理员', 'SYSDBA', '__SYS_INIT__');
insert into sys_role (id, name, perms, created_by) values ('2', '管理员', 'ADMIN', '__SYS_INIT__');
insert into sys_role (id, name, perms, created_by) values ('3', '普通用户', 'USER', '__SYS_INIT__');

insert into sys_user_role (user_id, role_id) values (1, 1);

insert into sys_permission (id, name, perms, type, created_by) values ('1', '系统管理', 'system', '0', '__SYS_INIT__');

insert into sys_permission (id, parent_id, name, perms, type, path, created_by) values ('2', 1, '用户管理', 'system:user:list', '1', 'system/user', '__SYS_INIT__');
insert into sys_permission (id, parent_id, name, perms, type, path, created_by) values ('3', 1, '角色管理', 'system:role:list', '1', 'system/role', '__SYS_INIT__');
insert into sys_permission (id, parent_id, name, perms, type, path, created_by) values ('4', 1, '权限管理', 'system:permission:list', '1', 'system/permission', '__SYS_INIT__');

insert into sys_permission (id, parent_id, name, perms, type, method, created_by) values ('5', 2, '添加用户', 'system:user:save', '2', 'POST', '__SYS_INIT__');
insert into sys_permission (id, parent_id, name, perms, type, method, created_by) values ('6', 2, '编辑用户', 'system:user:edit', '2', 'PUT', '__SYS_INIT__');
insert into sys_permission (id, parent_id, name, perms, type, method, created_by) values ('7', 2, '删除用户', 'system:user:del', '2', 'DELETE', '__SYS_INIT__');
insert into sys_permission (id, parent_id, name, perms, type, method, created_by) values ('8', 2, '查询用户', 'system:user:read', '2', 'GET', '__SYS_INIT__');

insert into sys_permission (id, parent_id, name, perms, type, method, created_by) values ('9', 3, '添加角色', 'system:role:save', '2', 'POST', '__SYS_INIT__');
insert into sys_permission (id, parent_id, name, perms, type, method, created_by) values ('10', 3, '编辑角色', 'system:role:edit', '2', 'PUT', '__SYS_INIT__');
insert into sys_permission (id, parent_id, name, perms, type, method, created_by) values ('11', 3, '删除角色', 'system:role:del', '2', 'DELETE', '__SYS_INIT__');
insert into sys_permission (id, parent_id, name, perms, type, method, created_by) values ('12', 3, '查询角色', 'system:role:read', '2', 'GET', '__SYS_INIT__');

insert into sys_permission (id, parent_id, name, perms, type, method, created_by) values ('13', 4, '添加权限', 'system:permission:save', '2', 'POST', '__SYS_INIT__');
insert into sys_permission (id, parent_id, name, perms, type, method, created_by) values ('14', 4, '编辑权限', 'system:permission:edit', '2', 'PUT', '__SYS_INIT__');
insert into sys_permission (id, parent_id, name, perms, type, method, created_by) values ('15', 4, '删除权限', 'system:permission:del', '2', 'DELETE', '__SYS_INIT__');
insert into sys_permission (id, parent_id, name, perms, type, method, created_by) values ('16', 4, '查询权限', 'system:permission:read', '2', 'GET', '__SYS_INIT__');

DROP TABLE IF EXISTS `tb_person`;
CREATE TABLE `tb_person`(
    `user_id` bigint NOT NULL COMMENT '主键',
    `nickname` varchar(30) DEFAULT NULL COMMENT '昵称',
    `avatar` varchar(120) DEFAULT NULL COMMENT '头像',
    `sex` int(1) DEFAULT NULL COMMENT '性别',
    `birthday` date DEFAULT NULL COMMENT '生日',
    `contact` varchar(50) DEFAULT NULL COMMENT '联系方式',
    `presentation` int(1) DEFAULT NULL COMMENT '个人展示',
    `profession` varchar(50) DEFAULT NULL COMMENT '职业',
    `specialty` varchar(50) DEFAULT NULL COMMENT '专业',
    `domain` varchar(50) DEFAULT NULL COMMENT '领域',
    `create_time` datetime DEFAULT now() COMMENT '创建时间',
    `created_by` varchar(50) DEFAULT NULL COMMENT '创建者',
    `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
    `modified_by` varchar(50) DEFAULT NULL COMMENT '更新者',
    primary key (`user_id`)
)DEFAULT CHARSET=utf8 COMMENT='个人信息表';

DROP TABLE IF EXISTS `tb_login_log`;
CREATE TABLE `tb_login_log`(
    `id` bigint NOT NULL auto_increment COMMENT '主键',
    `user` varchar(100) NOT NULL COMMENT '用户',
    `os` varchar(20) default NULL COMMENT '系统',
    `browser` varchar (20) default NULL COMMENT '浏览器',
    `ip` varchar (50) default NULL COMMENT 'IP',
    `address` varchar (100) default NULL COMMENT '地址',
    `login_time` datetime default NULL COMMENT '登录时间',
    `status` char(1) default NULL COMMENT '登录状态',
    PRIMARY KEY (`id`)
)DEFAULT CHARSET=utf8 COMMENT='登录日志表';

-- Token
DROP TABLE IF EXISTS `t_token`;
CREATE TABLE `t_token` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime COMMENT '过期时间',
  `update_time` datetime COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX (`token`)
)DEFAULT CHARSET=utf8 COMMENT='登录';
