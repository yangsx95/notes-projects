# BookStoreHbm


2017-0-29 至 2017-07-06-03

- 持久层 Hibernate
- UI界面 boostrap框架绘制
- 构建工具 maven
- 开发工具 IDE又有A

# 问题
1. Hibernate 在级联保存时，Order中OrderItems如果items已经有id，则进行级联保存时，items不会被保存在数据库中
2. Hibernate 无法进行级联分页查询
3. 在订单排序上，采用对List集合保存的方式，个人认为不太合理
4. 使用JSP进行开发过于复杂，很难维护，同时代码结构较为混乱，细节处理较多
