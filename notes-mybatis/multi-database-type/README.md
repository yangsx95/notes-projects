# 多个数据库类型兼容

比如，该项目要同时兼容mysql数据库和oracle数据库，但是，mysql和oracle的部分语法不一致，要想完成数据库的轻松切换，
需要使用到mybatis databaseId 功能。