-- 初始化用户
create user if not exists mybatis password 'mybatis';
alter user mybatis admin true;

-- 初始化SCHEMA
create schema MYBATIS;
grant alter any schema to mybatis;
-- 初始化Computer表
create sequence MYBATIS.SQ_COMPUTER increment by 1;
create table MYBATIS.COMPUTER
(
    ID         NUMBER           not null primary key,
    NAME       VARCHAR2(32)     not null,
    STATUS     NUMBER
);

comment on column MYBATIS.COMPUTER.NAME is '电脑名称';
comment on column MYBATIS.COMPUTER.STATUS is '电脑状态 10-打开 11-关闭 12-离线 200-故障 255-未知';