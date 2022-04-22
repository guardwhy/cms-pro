-- ----------------------------
--  Table structure for cms_user_primary 用户主表  密码: a1234567
-- ----------------------------
CREATE TABLE cms_user
(
    create_time   timestamp   not null default CURRENT_TIMESTAMP,
    update_time   timestamp   not null default '0000-00-00 00:00:00',
    id            int(11)     NOT NULL AUTO_INCREMENT primary key,
    username      varchar(50) not null comment '用户名',
    password      varchar(64) not null comment '用户密码，MD5加密或sha256散列加密',
    salt          varchar(64) not null comment '密码盐',
    email         varchar(50) not null default '' comment '邮箱',
    login_count   int(10)     not null default 0 comment '登陆次数',
    is_backend    tinyint(1)  not null default 0 comment '是否后台用户 0不是 1是',
    is_super      tinyint(1)  not null default 0 comment '是否超级管理员 0不是 1是',
    register_time timestamp   not null comment '注册时间',
    register_ip   varchar(50)          default '127.0.0.1' not null comment '注册IP',
    status        tinyint(1)           default '1' not null comment '状态 是否禁用 0:禁用 1:正常',
    is_delete     tinyint(1)           default '1' not null comment '是否已删除 0:删除 1正常',
    UNIQUE KEY user_name_unique (username) USING BTREE,
    UNIQUE KEY user_email_unique (email) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
INSERT INTO cms_user (id, create_time, update_time, username, password, salt, email, login_count, is_backend, is_super,
                      register_time, register_ip)
VALUES (1, '2019-06-14 11:30:58', null, 'admin', 'e298f9b29585da289080ffebb32e6f931b52a61195bcf6246d3e0f24654897eb',
        '6e4abc9695661ce11f442eaea3cb6540', 'abc@126.com', 0, 1, 1, '2019-06-14 11:30:58', '127.0.0.1');

-- ----------------------------
--  Table structure for cms_log  日志表
-- ----------------------------
CREATE TABLE cms_log
(
    create_time timestamp    not null default CURRENT_TIMESTAMP,
    update_time timestamp    not null default '0000-00-00 00:00:00',
    id          int(11)      NOT NULL AUTO_INCREMENT primary key,
    user_id     int(11)      not null comment '用户id',
    username varchar(25) not null comment '用户名称',
    login_ip    varchar(30)           default '' comment 'ip地址',
    url         varchar(100)          default '' comment 'URL地址',
    content     varchar(100) null comment '日志内容'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for cms_site  站点表
-- ----------------------------
CREATE TABLE cms_site
(
    create_time     timestamp    not null default CURRENT_TIMESTAMP,
    update_time     timestamp    not null default '0000-00-00 00:00:00',
    id              int(11)      NOT NULL AUTO_INCREMENT primary key,
    site_name       varchar(100) not null comment '网站名称',
    keywords        varchar(255) not null default '' comment '站点关键字',
    static_dir      varchar(50)  not null default '' comment '静态页存放目录',
    static_suffix   tinyint               default 0 not null comment '静态页后缀',
    tpl_index          varchar(255)                not null default '' comment '首页模板路径',
    is_static_index char                  default 0 not null comment '是否静态化首页 0:否 1:是',
    description     varchar(255) not null default '' comment '站点描述'
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8 comment ='CMS站点表';
INSERT INTO cms_site (id, site_name, keywords, description)
VALUES (1, 'cms官网', 'cms,内容管理', 'cms内容管理系统');

-- ----------------------------
-- Table structure for cms_permission  权限表
-- ----------------------------
CREATE TABLE cms_permission
(
    create_time timestamp    not null default CURRENT_TIMESTAMP,
    update_time timestamp    not null default '0000-00-00 00:00:00',
    id          int(11)      NOT NULL AUTO_INCREMENT primary key,
    parent_id   int(11)      default 0 comment '父级id',
    is_menu     tinyint(1)   default 0 comment '是否菜单 0:否 1:是',
    icon        varchar(30)  default '' comment '菜单图标',
    name varchar(25) not null comment '权限名称',
    url         varchar(50) not null comment '链接地址',
    action      varchar(100)  default '' comment '权限码',
    priority    int(11) NOT NULL comment '排序'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Table structure for cms_role  角色表
-- ----------------------------
CREATE TABLE cms_role
(
    create_time timestamp               not null default CURRENT_TIMESTAMP,
    update_time timestamp               not null default '0000-00-00 00:00:00',
    id          int(11)                 NOT NULL AUTO_INCREMENT primary key,
    name        varchar(50)             not null default '' comment '权限名称',
    priority    int        default '1' not null comment '排列顺序',
    is_all      tinyint(1) default 0    not null comment '是否所有权限 0.否 1.是',
    status      tinyint(1) default 0  not null comment '状态 0:正常 1:禁用'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Table structure for cms_role_permission  角色权限表
-- ----------------------------
CREATE TABLE cms_role_permission
(
    role_id    int         not null comment '角色id',
    permission_id int not null comment '权限id'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Table structure for cms_user_role  用户角色表
-- ----------------------------
CREATE TABLE cms_user_role
(
    role_id int not null comment '角色id',
    user_id int not null comment '用户id'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for cms_friend_link  友情链接表
-- ----------------------------
create table cms_friend_link
(
    create_time timestamp    not null default CURRENT_TIMESTAMP,
    update_time timestamp    not null default '0000-00-00 00:00:00',
    id          int          not null auto_increment primary key,
    name        varchar(150) not null comment '名称',
    url         varchar(255) not null comment '网站地址',
    priority    int(11)               default '1' not null comment '排列顺序'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for cms_task  CMS 任务表
-- ----------------------------
create table cms_task
(
    create_time     timestamp    not null default CURRENT_TIMESTAMP,
    update_time     timestamp    not null default '0000-00-00 00:00:00',
    id              int auto_increment primary key,
    name            varchar(100) not null comment '任务名称',
    code            varchar(255) null comment 'quartz任务执行名称',
    type            tinyint(1)   not null default '0' comment '任务类型(0首页静态化、1栏目页静态化、2内容页静态化)',
    execution_cycle tinyint(1)   not null default '0' comment '执行周期分类(0:执行周期 非表达式  1:执行方式 cron表达式)',
    day_of_month    int          null comment '每月的哪天',
    day_of_week     tinyint(1)   null comment '周几',
    hour            int          null comment '小时',
    minute          int          null comment '分钟',
    interval_hour   int          null comment '间隔小时',
    interval_minute int          null comment '间隔分钟',
    cron_expression varchar(255) not null default '' comment 'cron规则表达式',
    is_enable       tinyint(1)   not null default 1 comment '是否启用',
    interval_unit   tinyint(1)   not null default 0 comment '0:分钟、1:小时、2:日、3:周、4:月',
    remark          varchar(255) not null default '' comment '任务说明'
) ENGINE = InnoDB
  CHARSET = utf8 comment 'CMS 任务表';