-- 初始化用户
create user if not exists mybatis password 'mybatis';
alter user mybatis admin true;

-- 初始化SCHEMA
create schema MYBATIS;
grant alter any schema to mybatis;

-- 初始化表和序列
create sequence MYBATIS.SQ_USER increment by 1;
create table MYBATIS."USER"
(
    ID         NUMBER           not null
        primary key,
    USERNAME   VARCHAR2(32)     not null,
    PASSWORD   VARCHAR2(128)    not null,
    GENDER     NUMBER           not null,
    DEL_STATUS NUMBER default 1 not null,
    STATUS     NUMBER default 1 not null
);

comment on column MYBATIS."USER".GENDER is '0 未知 1 男 2 女';
comment on column MYBATIS."USER".DEL_STATUS is '0 已删除 1 未删除';
comment on column MYBATIS."USER".STATUS is '0 账户无效; 1 账户有效';

-- 数据初始化
insert into MYBATIS."USER" (ID, USERNAME, PASSWORD, GENDER, DEL_STATUS, STATUS)
values ((NEXT VALUE FOR MYBATIS.SQ_USER), '张三', '123456', 1, 1, 1);
