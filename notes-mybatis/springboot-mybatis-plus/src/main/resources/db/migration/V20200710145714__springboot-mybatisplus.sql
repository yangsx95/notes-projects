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
    ID          NUMBER           not null
        primary key,
    USERNAME    VARCHAR2(32)     not null,
    PASSWORD    VARCHAR2(128)    not null,
    GENDER      NUMBER,
    DELETED     NUMBER default 0 not null,
    STATUS      NUMBER default 0 not null,
    CREATE_TIME DATETIME,
    UPDATE_TIME DATETIME
);

comment on column MYBATIS."USER".GENDER is '0 未知 1 男 2 女';
comment on column MYBATIS."USER".DELETED is '0 未删除 1 已删除';
comment on column MYBATIS."USER".STATUS is '0 账户有效; 1 账户无效';

-- 数据初始化
insert into MYBATIS."USER" (ID, USERNAME, PASSWORD, GENDER, DELETED, STATUS)
values ((NEXT VALUE FOR MYBATIS.SQ_USER), '张三', '123456', null, 0, 0);
