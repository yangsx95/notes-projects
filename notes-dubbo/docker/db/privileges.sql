use mysql;
select host, user from user;
-- 因为mysql版本是5.7，因此新建用户为如下命令：
create user dubbo identified by 'root';
-- 将所有数据库和表的权限给予dubbo用户
grant all on *.* to dubbo@'%' identified by 'dubbo' with grant option;
-- 这一条命令一定要有：
flush privileges;