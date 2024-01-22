FROM mysql:5.7

# 免密登录
ENV MYSQL_ALLOW_EMPTY_PASSWORD yes

# 脚本注入
COPY setup.sh /mysql/setup.sh
COPY schema.sql /mysql/schema.sql
COPY privileges.sql /mysql/privileges.sql

#设置容器启动时执行的命令
CMD ["sh", "/mysql/setup.sh"]