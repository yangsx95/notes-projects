# Mybatis

整理了有关mybatis框架的知识，方便查阅和学习


MyBatis 本是apache的一个开源项目iBatis, 2010年这个项目由apache software foundation 迁移到了google code，并且改名为MyBatis 。2013年11月迁移到Github。MyBatis是一个优秀的持久层框架，它对jdbc的操作数据库的过程进行封装，使开发者只需要关注 SQL 本身，而不需要花费精力去处理例如注册驱动、创建connection、创建statement、手动设置参数、结果集检索等jdbc繁杂的过程代码。

Mybatis通过xml或注解的方式将要执行的各种statement（statement、preparedStatement、CallableStatement）配置起来，并通过java对象和statement中的sql进行映射生成最终执行的sql语句，最后由mybatis框架执行sql并将结果映射成java对象并返回。

## Mybatis优势

### JDBC 问题总结

JDBC编程步骤：

1. 加载数据库驱动
2. 创建并获取数据库连接
3. 创建jdbc statement 语句对象
4. 设置sql语句
5. 设置sql语句参数（preparedStatement）
6. 使用statement执行sql并获取结果
7. 对sql执行结果进行解析处理
8. 释放资源（resultSet、preparement、connection）

主要问题：

1.  数据库链接创建、释放频繁造成系统资源浪费从而影响系统性能，如果使用数据库链接池可解决此问题。
2.  Sql语句在代码中硬编码，造成代码不易维护，实际应用sql变化的可能较大，sql变动需要改变java代码。
3.  使用preparedStatement向占有位符号传参数存在硬编码，因为sql语句的where条件不一定，可能多也可能少，修改sql还要修改代码，系统不易维护。
4.  对结果集解析存在硬编码（查询列名），sql变化导致解析代码变化，系统不易维护，如果能将数据库记录封装成pojo对象解析比较方便。

### Mybatis VS Hibernate

| Hibernate                                 | Mybatis                             |
| ----------------------------------------- | ----------------------------------- |
| 以对象为中心的ORM                         | 以数据库中心                        |
| 不适合大吞吐量                            |                                     |
| 极为方便的ORM操作,以及HQL                 | 需要写SQL，较为冗余                 |
| 简单查询支持好，但复杂SQL以及多表查询较差 | 复杂SQL支持好                       |
| 学习成本高，SQL优化困难                   | 学习成本低，针对原生SQL进行优化即可 |
| 内部实现复杂，拓展难度大                  | 内部实现简单                        |
| 随项目增长维护成本逐渐升高                | 维护成本相对较低                    |

Mybatis和hibernate不同，它不完全是一个ORM框架，因为MyBatis需要程序员自己编写Sql语句，不过mybatis可以通过XML或注解方式灵活配置要运行的sql语句，并将java对象和sql语句映射生成最终执行的sql，最后将sql执行的结果再映射生成java对象。

Mybatis学习门槛低，简单易学，程序员直接编写原生态sql，可严格控制sql执行性能，灵活度高，非常适合对关系数据模型要求不高的软件开发，例如互联网软件、企业运营类软件等，因为这类软件需求变化频繁，一但需求变化要求成果输出迅速。但是灵活的前提是mybatis无法做到数据库无关性，如果需要实现支持多种数据库的软件则需要自定义多套sql映射文件，工作量大。 

Hibernate对象/关系映射能力强，数据库无关性好，对于关系模型要求高的软件（例如需求固定的定制化软件）如果用hibernate开发可以节省很多代码，提高效率。但是Hibernate的学习门槛高，要精通门槛更高，而且怎么设计O/R映射，在性能和对象模型之间如何权衡，以及怎样用好Hibernate需要具有很强的经验和能力才行。

总之，按照用户的需求在有限的资源环境下只要能做出维护性、扩展性良好的软件架构都是好架构，所以框架只有适合才是最好。 

## MyBatis架构

<img src="assets/架构.png">

1. mybatis配置，`SqlMapConfig.xml`，mybatis的全局配置文件，配置了mybatis的运行环境等信息。`mapper.xml`文件即sql映射文件，文件中配置了操作数据库的sql语句。此文件需要在`SqlMapConfig.xml`中加载。
2.  通过`mybatis`环境等配置信息构造`SqlSessionFactory`即会话工厂
3.  由会话工厂创建sqlSession即会话，操作数据库需要通过`sqlSession`进行。
4.  `mybatis`底层自定义了Executor执行器接口操作数据库，`Executor`接口有两个实现，一个是基本执行器、一个是缓存执行器。
5.  `MappedStatement`也是`mybatis`一个底层封装对象，它包装了`mybatis`**配置信息**及**sql映射信息**等。`mapper.xml`文件中**一个sql对应一个`MappedStatement`对象**，**sql的id即是`MappedStatement`的id**。
6.  `MappedStatement`对sql执行输入参数进行定义，包括`HashMap`、基本类型、`pojo`，`Executor`通过`MappedStatement`在执行sql前将输入的java对象映射至sql中，输入参数映射就是jdbc编程中对`preparedStatement`设置参数。
7.  `MappedStatement`对sql执行输出结果进行定义，包括`HashMap`、基本类型、`pojo`，`Executor`通过MappedStatement在执行sql后将输出结果映射至java对象中，输出结果映射过程相当于jdbc编程中对结果的解析处理过程。

## 环境构建

官方网址：https://github.com/mybatis/mybatis-3/releases

- mybatis-3.2.7.jar----mybatis的核心包
- lib----mybatis的依赖包
- mybatis-3.2.7.pdf----mybatis使用手册

**添加maven依赖**：

```xml
<!--添加数据库驱动-->
<!--添加数据库连接池-->
<!--添加mybatis依赖-->
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>3.4.2</version>
</dependency>
```



**mybatis默认使用log4j进行日志输出，需要在resources下添加log4j配置文件`log4j.properties`**

```properties
# Global logging configuration
log4j.rootLogger=DEBUG, stdout
# Console output...
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n
```



**核心配置文件编写：**

在classpath/resources下创建`SqlMapConfig.xml`，如下：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
  <!-- 和spring整合后 environments配置将废除-->
  <environments default="development">
    <environment id="development">
      <!-- 使用jdbc事务管理-->
      <transactionManager type="JDBC" />
      <!-- 数据库连接池-->
      <dataSource type="POOLED">
        <property name="driver" value="oracle.jdbc.driver.OracleDriver" />
        <property name="url" value="jdbc:oracle:thin:@localhost:1521:XE" />
        <property name="username" value="jsd1703" />
        <property name="password" value="jsd1703" />
      </dataSource>
    </environment>
  </environments>
  <!--加载sql映射文件，否则不会生效-->
  <mappers>
    <mapper resource="User.xml"/>
  </mappers>
</configuration>
```



### 配置sql映射文件

`User.xml`:

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
    PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="user">
    <!--
        id sql的唯一标识
        parameterType 传入的参数的类型
        resultType 返回结果集类型
        #{} 占位符，如果是常见的基本类型，那么中的变量名称可以随意设置
    -->
    <!--查询单个-->
    <select id="findById" parameterType="java.lang.Integer" resultType="me.feathers.pojo.User">
        SELECT *
        FROM WEB_USER
        WHERE id = #{id}
    </select>

    <!--查询多个-->
  	<!--注意，在sql语句上不可以使用/**/或者-- 注释，否则会被解析为ognl表达式，报 org.apache.ibatis.ognl.ExpressionSyntaxException: Malformed OGNL expression:  [org.apache.ibatis.ognl.ParseException-->
    <select id="findAll" parameterType="java.lang.String"
            resultType="me.feathers.pojo.User">
        SELECT *
        FROM WEB_USER
        WHERE username LIKE '%${value}%'
    </select>

    <!--插入-->
    <!--如果传入的是pojo类型，那么#{}中必须是pojo的属性-->
    <!--
		selectKey：用来生成主键
		keyProperty:返回的主键存储在pojo中的哪个属性,
		order:selectKey的执行顺序（相对于insert语句来说） 有berfore和after两种选择，oracle的序列需要先生成，所以这里使用berfore
				而mysql的自增原理执行完insert语句之后才能将主键生成，mysql需要使用after
	-->
    <insert id="save" parameterType="me.feathers.pojo.User">
        <selectKey keyProperty="id" order="BEFORE" resultType="java.lang.Long">
            SELECT "sq_web_user".nextval FROM dual
        </selectKey>
        INSERT INTO WEB_USER (ID, PASSWORD, STATE, USERNAME)
        VALUES (${id},#{password}, #{state}, #{username})
    </insert>

       <delete id="delById" parameterType="java.lang.Integer">
        DELETE FROM WEB_USER
        WHERE ID = #{id}
    </delete>

    <update id="update" parameterType="me.feathers.pojo.User">
        update WEB_USER set username=#{username}, PASSWORD=#{password}, STATE=#{state} 
      	where id=#{id}
    </update>
  
</mapper>
```



> 注意：
>
> `#{}`表示一个占位符号，通过`#{}`可以实现`preparedStatement`向占位符中设置值，自动进行java类型和jdbc类型转换，`#{}`可以有效防止sql注入。`#{}`可以接收简单类型值或pojo属性值。 如果`parameterType`传输单个简单类型值，`#{}`括号中可以是value或其它名称。
>
> `${}`表示拼接sql串，通过`${}`可以将`parameterType` 传入的内容拼接在sql中且不进行jdbc类型转换， `${}`可以接收简单类型值或pojo属性值，如果`parameterType`传输单个简单类型值，`${}`括号中只能是value。

> mysql的主键生成策略：
>
> 使用uuid：`select uuid()`
>
> 使用 ast_insert_id()寒素， `select AST_INSERT_ID()`，返回auto_increment自增列新记录id值。



### 书写Java代码

在sql映射文件中已经包含了增删改查的sql语句，现在可以通过代码完成增删改查的操作了。

```java
// 查找单个
@Test
public void testFindById() throws IOException {
  // 1. 加载配置文件
  InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
  // 2. 使用核心配置文件拿到会话工厂
  SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
  // 3. 使用工厂获取会话
  SqlSession s = factory.openSession();
  // 4. 使用session进行操作
  // 参1： sql语句，namespace.sqlId
  User u = s.selectOne("user.findById", 1);
  System.out.println(u+"---------");
  // 5. 关闭会话
  s.close();
}
```

查找多个：

```java
List<User> us = s.selectList("user.findAll", "ro");
```

插入：

```java
User user = s.selectOne("user.findById", 4);
int count = s.delete("user.delById", user);
s.commit(); //注意提交
```

更新：

```java
User user = s.selectOne("user.findById", 4);
user.setPassword("456789");
user.setUsername("李四");
int count = s.update("user.update", user);
s.commit();
```

删除：

```java
int count = s.delete("user.delById", 4); // count为1 代表删除了一条
```



> selectOne查询一条记录，如果使用selectOne查询多条记录则抛出异常：
>
> ```
> org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 3
> 	at org.apache.ibatis.session.defaults.DefaultSqlSession.selectOne(DefaultSqlSession.java:70)
> ```



### MyBatis 解决的问题

1. 数据库链接创建、释放频繁造成系统资源浪费从而影响系统性能，如果使用数据库链接池可解决此问题。

   解决：在`SqlMapConfig.xml`中配置数据链接池，使用连接池管理数据库链接。

2. Sql语句写在代码中造成代码不易维护，实际应用sql变化的可能较大，sql变动需要改变java代码。

   解决：将Sql语句配置在`XXXXmapper`.xml文件中与java代码分离。

3. 向sql语句传参数麻烦，因为sql语句的where条件不一定，可能多也可能少，占位符需要和参数一一对应。

   解决：Mybatis自动将java对象映射至sql语句，通过statement中的parameterType定义输入参数的类型。

4. 对结果集解析麻烦，sql变化导致解析代码变化，且解析前需要遍历，如果能将数据库记录封装成pojo对象解析比较方便。

   解决：Mybatis自动将sql执行结果映射至java对象，通过statement中的resultType定义输出结果的类型。



## MyBatis常见类的作用

### SqlSessionFactoryBuilder

`SqlSessionFactoryBuilder`用于创建`SqlSessionFacoty`，`SqlSessionFacoty`一旦创建完成就不需要`SqlSessionFactoryBuilder`了，因为`SqlSession`是通过`SqlSessionFactory`生产，所以可以将`SqlSessionFactoryBuilder`当成一个工具类使用，最佳使用范围是方法范围即方法体内局部变量。

### SqlSessionFactory

是一个接口，接口中定义了openSession()的不同重载方法，SqlSessionFactory的最佳使用范围是整个应用运行期间，一旦创建后可以重复使用，通常以单例模式管理SqlSessionFactory。

### SqlSession

SqlSession是一个面向用户的接口，封装了对数据库的操作，如：查询、插入、更新、删除等。

通过`SqlSessionFactory`创建`SqlSession`，而`SqlSessionFactory`是通过`SqlSessionFactoryBuilder`进行创建。

​	每个线程都应该有它自己的SqlSession实例。SqlSession的实例不能共享使用，它也是线程不安全的。因此最佳的范围是请求或方法范围。绝对不能将SqlSession实例的引用放在一个类的静态字段或实例字段中。

​	打开一个 SqlSession；使用完毕就要关闭它。通常把这个关闭操作放到 finally 块中以确保每次都能执行关闭。如下：

```java
SqlSession session = sqlSessionFactory.openSession();
try {
  // do work
} finally {
  session.close();
}
```

## 使用MyBatis开发DAO

使用MyBatis开发Dao有两种方式：

1. 使用原始的Dao开发方式
2. 使用Mapper接口开发方式

### 原始Dao开发

interface:` UserDao`

```java
public interface UserDao {
    void save(User user);
    User findById(Integer id);
    List<User> findAll(String name);
    void update(User user);
    void delById(Integer id);
}
```

实现：

```java
/**
 * @author Feathers
 * @create 2017-07-17-14:59
 */
public class UserDaoImpl implements UserDao {

   private final SqlSessionFactory ssf;

   public UserDaoImpl(SqlSessionFactory ssf) {
      this.ssf = ssf;
   }

   @Override
   public void save(User user) {
      SqlSession s = ssf.openSession();
      try {
         s.insert("user.save", user);
         s.commit();
      } finally {
         s.close();
      }
   }

   @Override
   public User findById(Integer id) {
      SqlSession s = ssf.openSession();
      User user;
      try {
         user = s.selectOne("user.findById", id);
         s.commit();
      } finally {
         s.close();
      }
      return user;
   }

   @Override
   public List<User> findAll(String name) {
      SqlSession s = ssf.openSession();
      List<User> users;
      try {
         users = s.selectList("user.findAll", name);
         s.commit();
      } finally {
         s.close();
      }
      return users;
   }

   @Override
   public void update(User user) {
      SqlSession s = ssf.openSession();
      try {
         s.update("user.update", user);
         s.commit();
      } finally {
         s.close();
      }
   }

   @Override
   public void delById(Integer id) {
      SqlSession s = ssf.openSession();
      try {
         s.delete("user.delById", id);
         s.commit();
      } finally {
         s.close();
      }
   }
}
```



测试类：

```java
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import me.feathers.dao.UserDao;
import me.feathers.dao.UserDaoImpl;
import me.feathers.pojo.User;

/**
 * @author Feathers
 * @create 2017-07-17-15:12
 */
public class UserDaoTest {

    private UserDao dao;

    @BeforeTest
    public void test() throws IOException {
        // 1. 加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2. 使用核心配置文件拿到会话工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        dao = new UserDaoImpl(factory);
    }

    @Test(priority = 1)
    public void testSave() {
        dao.save(new User("赵六", "123456", 0));
    }

    @Test(priority = 2)
    public void testFindById(){
        User byId = dao.findById(4);
        System.out.println(byId);
    }

    @Test(priority = 3)
    public void testUpdate() {
        User byId = dao.findById(4);
        byId.setUsername("王五");
        dao.update(byId);
    }

    @Test(priority = 4)
    public void testFindAll(){
        List<User> all = dao.findAll("");
        System.out.println(all);
    }

    @Test(priority = 5)
    public void testDelById(){
        dao.delById(4);
    }
}
```



>  原始Dao开发中存在以下问题：
>
>  - Dao方法体存在重复代码：通过SqlSessionFactory创建SqlSession，调用SqlSession的数据库操作方法
>  - 调用sqlSession的数据库操作方法需要指定statement的id，这里存在硬编码，不利于开发维护。

#### MyBatis模板工具类

```java
/*MyBatisUtil,负责加载配置文件，创建sqlSessionFactory，获取sqlSession*/
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession() {
        return sqlSessionFactory == null ? null : sqlSessionFactory.openSession();
    }

    public static void close(SqlSession sqlSession){
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}
```

```java
/*MyBatis回调接口，负责完成执行的操作*/
import org.apache.ibatis.session.SqlSession;
public interface MyBatisCallback {
    Object doInMyBatis(SqlSession ses);
}
```

```java
package me.feathers.online.util;

import org.apache.ibatis.session.SqlSession;

/**
 * mybatis模板工具类
 *
 * @author Feathers
 * @create 2017-07-30-10:51
 */
public class MyBatisTemplate {

    public static Object excute(MyBatisCallback callback) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        Object o = null;
        try {
            o = callback.doInMyBatis(sqlSession);
        } finally {
            MyBatisUtil.close(sqlSession);
        }
        return o;
    }
}
```

```java
/**调用实例**/
 @Override
public User findById(Long id) {
  return (User) MyBatisTemplate.excute(ses -> {
    return ses.selectOne("user.findById", id);;
  });
}
```



> 如果你要添加Transaction，请修改Template类



### Mapper动态代理方式

#### 开发规范

​	Mapper接口开发方法只需要程序员编写Mapper接口（相当于Dao接口），由Mybatis框架根据接口定义创建接口的动态代理对象，代理对象的方法体同上边Dao接口实现类方法。

Mapper接口开发需要遵循以下规范：

1. `Mapper.xml`文件中的`namespace`与`Mapper`接口的类路径相同。
2. `Mapper`接口方法名和`Mapper.xml`中定义的每个`statement`的id相同 
3. `Mapper`接口方法的输入参数类型和`mapper.xml`中定义的每个sql 的`parameterType`的类型相同
4. `Mapper`接口方法的输出参数类型和`mapper.xml`中定义的每个sql的`resultType`的类型相同

#### 定义Mapper接口

UserMapper.java:

```java
public interface UserMapper {

    void save(User user);

    User findById(Integer id);

    List<User> findAll(String name);

    void update(User user);

    void delById(Integer id);
}
```



#### Mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
    PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--1. Mapper.xml文件中的namespace与mapper接口的类路径相同。-->
<mapper namespace="me.feathers.mapper.UserMapper">

    <!--2. Mapper接口方法名和Mapper.xml中定义的每个statement的id相同 -->
    <!--3. Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql 的parameterType的类型相同-->
    <!--4. Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同-->
    <select id="findById" parameterType="java.lang.Integer" resultType="me.feathers.pojo.User">
        SELECT *
        FROM WEB_USER
        WHERE id = #{id}
    </select>

  	<!--注意，这里结果集类型不是java.util.List-->
    <select id="findAll" parameterType="java.lang.String"
            resultType="me.feathers.pojo.User">
        SELECT *
        FROM WEB_USER
        WHERE username LIKE '%${value}%'
    </select>

    <insert id="save" parameterType="me.feathers.pojo.User">
        <selectKey keyProperty="id" order="BEFORE" resultType="java.lang.Long">
            SELECT "sq_web_user".nextval FROM dual
        </selectKey>
        INSERT INTO WEB_USER (ID, PASSWORD, STATE, USERNAME)
        VALUES (${id},#{password}, #{state}, #{username})
    </insert>

    <delete id="delById" parameterType="java.lang.Integer">
        DELETE FROM WEB_USER
        WHERE ID = #{id}
    </delete>

    <update id="update" parameterType="me.feathers.pojo.User">
        update WEB_USER set username=#{username}, PASSWORD=#{password}, STATE=#{state} where
            id=#{id}
    </update>

</mapper>
```

#### 加载映射文件

```xml
<!-- 加载映射文件 -->
<mappers>
  <mapper resource="Mapper.xml"/>
</mappers>
```

#### 编写测试类

```java
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import me.feathers.dao.UserDao;
import me.feathers.dao.UserDaoImpl;
import me.feathers.mapper.UserMapper;
import me.feathers.pojo.User;

/**
 * @author Feathers
 * @create 2017-07-17-15:12
 */
public class UserMapperTest {

    SqlSessionFactory factory;

    @BeforeTest
    public void test() throws IOException {
        // 1. 加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2. 使用核心配置文件拿到会话工厂
        factory = new SqlSessionFactoryBuilder().build(in);
    }

    @Test(priority = 1)
    public void testSave() {
        SqlSession session = factory.openSession();
        //获取mapper接口的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
        //调用代理对象方法
        userMapper.save(new User("赵六", "123456", 0));
    }

    @Test(priority = 2)
    public void testFindById(){
        SqlSession session = factory.openSession();
        //获取mapper接口的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User byId = userMapper.findById(4);
        System.out.println(byId);
    }

    @Test(priority = 3)
    public void testUpdate() {
        SqlSession session = factory.openSession();
        //获取mapper接口的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
        //调用代理对象方法
        User byId = userMapper.findById(4);
        byId.setUsername("王五");
        userMapper.update(byId);
    }

    @Test(priority = 4)
    public void testFindAll(){
        SqlSession session = factory.openSession();
        //获取mapper接口的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
        //调用代理对象方法
        List<User> all = userMapper.findAll("");
        System.out.println(all);
    }

    @Test(priority = 5)
    public void testDelById() {
        SqlSession session = factory.openSession();
        //获取mapper接口的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
        //调用代理对象方法
        userMapper.delById(4);
    }
}
```



## SqlMapConfig.xml

配置内容：

- **properties**（属性）
- settings（全局配置参数）
- **typeAliases**（类型别名）
- typeHandlers（类型处理器）
- objectFactory（对象工厂）
- plugins（插件）
- environments（环境集合属性对象）
- environment（环境子属性对象）
- transactionManager（事务管理）
- dataSource（数据源）
- **mappers**（映射器）



### properties

将数据库信息提取到 `db.properties`中，然后引入到myBatis的核心配置文件中进行配置

```xml
<!--引入db.properties-->
<properties resource="config/db.properties"/>

<!-- 和spring整合后 environments配置将废除-->
<environments default="development">
  <environment id="development">
    <!-- 使用jdbc事务管理-->
    <transactionManager type="JDBC" />
    <!-- 数据库连接池-->
    <dataSource type="POOLED">
      <property name="driver" value="${driverClass}" />
      <property name="url" value="${url}" />
      <property name="username" value="${user}" />
      <property name="password" value="${password}" />
    </dataSource>
  </environment>
</environments>
```



 MyBatis 将按照下面的顺序来加载属性：

- 在 properties 元素体内定义的属性首先被读取。 
- 然后会读取properties 元素中resource或 url 加载的属性，它会覆盖已读取的同名属性。 



###  typeAliases

类型别名，MyBatis支持的类型别名：

| 别名         | 映射的类型      |
| ---------- | ---------- |
| _byte      | byte       |
| _long      | long       |
| _short     | short      |
| _int       | int        |
| _integer   | int        |
| _double    | double     |
| _float     | float      |
| _boolean   | boolean    |
| string     | String     |
| byte       | Byte       |
| long       | Long       |
| short      | Short      |
| int        | Integer    |
| integer    | Integer    |
| double     | Double     |
| float      | Float      |
| boolean    | Boolean    |
| date       | Date       |
| decimal    | BigDecimal |
| bigdecimal | BigDecimal |
| map        | Map        |

> 作用：
>
> 在MyBatis中的类型属性可以使用一上的类型别名替代，比如`parameterType="int"`就同`parameterType="java.lang.Integer"`是一样的
>
> 好处，简化了开发



自定以类型别名：

> 类型别名的定义在MyBatis `SqlMapConfig.xml`的核心配置文件中！！！

```xml
<typeAliases>
  <!-- 单个别名定义 -->
  <typeAlias alias="user" type="me.feathers.pojo.User"/>
  <!-- 批量别名定义，扫描整个包下的类，每个类的别名为类的类名（首字母大写或小写都可以） -->
  <!--<package name="me.feathers.pojo"/>-->
  <!--<package name="其它包"/>-->
</typeAliases>
```



### mappers

配置映射器。

- 使用相对类路径的资源：`<mapper resource="sqlmap/User.xml" />`
- 使用mapper接口类路径：`<mapper class="me.feathers.mapper.UserMapper"/>`
- 自动注册指定包下的所有mapper接口，`<package name="me.feathers.mapper"/>`



## 输入映射和输出映射

Mapper.xml映射文件中定义了操作数据库的sql，每个sql是一个statement，映射文件是mybatis的核心。

### parameterType 输入类型

1. **传递简单类型**
2. **传递pojo对象**
3. **传递pojo包装类型**



**传递pojo包装类型：**

开发中通过pojo传递查询条件 ，查询条件是综合的查询条件，不仅包括用户查询条件还包括其它的查询条件（比如将用户购买商品信息也作为查询条件），这时可以使用包装对象传递输入参数。



**UserVo （Value Object）**:

```java
// 一定要提供getter/setter方法，ognl 表达式底层和el表达式相同，是使用getter setter方法获取属性的
public class UserVo {

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
```



Mapper接口方法：

```java
List<User> findUserByUserVo(UserVo userVo);
```



Mapper配置文件：

```xml
<select id="findUserByUserVo" parameterType="me.feathers.pojo.vo.UserVo"
        resultType="user">
  SELECT * FROM WEB_USER where username like '%${user.username}%' 
</select>
<!--这里使用ognl取出查询参数，并拼接到sql语句中-->
```



### resultType 输出类型

1. 输出简单类型
2. 输出pojo类型
3. 输出pojo列表

这里不再演示，请查看上面的代码。



### resultMap 

**resultType可以将查询结果映射为pojo**，但需要pojo的属性名和sql查询的列名一致方可映射成功。如果查询字段与pojo的属性名不一致，可以通过resultMap将字段名和属性名一一对应，完成映射。

```xml
<resultMap type="user" id="userListResultMap">
	<id column="id_" property="id"/>
  	<result column="username_" property="username"/>
   	<result column="password_" property="password"/>
</resultMap>
```

`<id/>`标签：此属性表示查询结果集的唯一标识，非常重要。如果是多个字段为复合唯一约束则定义多个`<id />`。

- `Property`：表示User类的属性。
- `Column`：表示sql查询出来的字段名。Column和property放在一块儿表示将sql查询出来的字段映射到指定的pojo类属性上。
- `<result />`：普通结果，即pojo的属性。

如果列名和表名一致，可以不写。

## 动态sql

通过mybatis提供的各种标签方法实现动态拼接sql。

### if 

之前在传递参数到sql中时，如果参数为null，则会发生错误，为了避免查询参数不合法的情况，我们可以使用if进行有效性判断。

```xml
<select id="findAll" parameterType="java.lang.String"
        resultType="User">
  SELECT *
  FROM WEB_USER
  WHERE 1=1
  <if test="username!=null and username!=''">
    AND username LIKE '%${value}%'
  </if>
</select>
```

> 注意，字符串参数一定要进行空字符串判断



### where

上面使用了`1=1` 来保证sql语句的完成性，我们也可以使用where标签替代。

```xml
<select id="findAll" parameterType="java.lang.String"
        resultType="User">
  SELECT *
  FROM WEB_USER
  <where>
    <if test="username!=null and username!=''">
    	AND username LIKE '%${value}%'
  	</if>
  </where>
</select>
```

备注：`<where />`可以自动处理第一个and。



### foreach

 当向sql传递数组或List，mybatis使用foreach进行解析：

```sql
SELECT * FROM USERS WHERE username LIKE '%张%' AND id IN (10,89,16)
```

sql片段：

```xml
<if test="ids!=null and ids.size>0">
  <!--ids 要遍历的集合，将集合遍历，拼接为sql字符串 open 开始拼接  close 结束拼接， separator 分隔符， 得到的结果为 and id in (10,89,16)-->
  <foreach collection="ids" open=" and id in(" close=")" item="id" separator="," > 
    #{id}
  </foreach>
</if>
```



### Sql 片段

xml中的sql可能会发生大量重复的sql片段，我们可以将他们抽取到sql标签中，然后使用include引用这些sql片段。

```xml
<select id="findUserList" parameterType="user" resultType="user">
  select * from user 
  <where>
    <if test="id!=null and id!=''">
      and id=#{id}
    </if>
    <if test="username!=null and username!=''">
      and username like '%${username}%'
    </if>
  </where>
</select>
```

抽出条件

```xml
<sql id="query_user_where">
	<if test="id!=null and id!=''">
		and id=#{id}
	</if>
	<if test="username!=null and username!=''">
		and username like '%${username}%'
	</if>
</sql>
```

使用include引用：

```xml
<select id="findUserList" parameterType="user" resultType="user">
  select * from user 
  <where>
    <include refid="query_user_where"/>
  </where>
</select>
```



##  关联查询

### 一对一关联查询

#### 预备工作

```sql
drop table wife;
drop table husband;

create table husband(
	hus_id number(19) primary key,
	hus_name varchar2(20), 
	age number(3), 
	birthday date
);

create table wife(
	wife_id number(19) primary key,
	wife_name varchar2(255), 
	age number(3), 
	birthday date, 
	hus_id number(19),
	foreign key(hus_id) references husband(hus_id) 
);

insert into husband(hus_id, hus_name, age, birthday) values(1, '张山', 24, sysdate);
insert into husband(hus_id, hus_name, age, birthday) values(2, '李斯', 25, sysdate);
insert into wife(wife_id, wife_name, age, birthday, hus_id) values(1, '小红', 24, sysdate, 2);
insert into wife(wife_id, wife_name, age, birthday, hus_id) values(2, '丽丽', 23, sysdate, 1);
```

有两张表，分别为妻子和丈夫， 属于一对一的关系。要求根据丈夫查找对应的妻子。

#### 方式一 

一对一自动映射,试用resultType，定义夫妻信息po类，该类包含了丈夫和妻子的信息，即所要查的信息。

现在我们要查询所有的夫妻对，有如下sql语句：

```sql
 select w.wife_name, w.age wife_age, hus.* from husband hus join wife w on w.hus_id = HUS.hus_id;  --给age起个别名方便使用
```

定义sql映射：

```xml
<select id="findAll" resultType="me.feathers.pojo.WifeAndHusband">
  SELECT
  w.wife_name,
  w.age wife_age,
  hus.*
  FROM husband hus
  JOIN wife w ON w.hus_id = HUS.hus_id
</select>
```

```java
/**使用resultType，定义夫妻信息类，此po类中包括了丈夫信息和妻子信息**/

/**Husband类**/
public class Husband {
    private Long hus_id;
    private String hus_name;
    private int age;
    private Date birthday;
	// setter / getter
}
/**夫妻类继承husband类**/
public class WifeAndHusband extends Husband {
    private String wife_name;
    private int age;
	// setter / getter
}
```

这样就将所需要的信息封装到实体类中了。

测试类：

```java
 @Test
public void findAll() {
  SqlSession session = factory.openSession();
  //获取mapper接口的代理对象l
  HusbandMapper husbandMapper = session.getMapper(HusbandMapper.class);
  //调用代理对象方法
  List<WifeAndHusband> all = husbandMapper.findAll();
  all.forEach(System.out :: println);
}
```

> 这种方式在开发中最为常用，不仅可以处理这种一对一的表关系，根据订单查用户也是属于一对一的查询，此种方式也是试用的。

#### 方式二

一对一手动映射，定义一个resultMap，用于映射一对一查询结果。

sql语句仍然不变：`select w.wife_name, w.age wife_age, hus.* from husband hus join wife w on w.hus_id = HUS.hus_id; `

```java
/**定义husband 和 wife**/

/**Husband类**/
public class Husband {
    private Long hus_id;
    private String hus_name;
    private int age;
    private Date birthday;
	// setter / getter
}

/**Wife类**/
public class Wife {
   private Long wife_id;
   private String wife_name;
   private int age;
   private Date birthday;
   private Husband husband; // 在wife中维护一个husband对象
   // setter / getter
}
```

```xml
<!-- 定义resultMap -->
<resultMap id="wifeAndHus" type="me.feathers.pojo.Wife">
  <id property="wife_id" column="wife_id"/>
  <result property="age" column="age"/>
  <result property="wife_name" column="wife_name"/>
  <result property="birthday" column="birthday"/>
  <!-- 一对一关联映射 -->
  <association property="husband" javaType="me.feathers.pojo.Husband">
    <id property="hus_id" column="hus_id"/>
    <result property="age" column="age"/>
    <result property="hus_name" column="hus_name"/>
    <result property="birthday" column="birthday"/>
  </association>
</resultMap>

<select id="findAll1" resultMap="wifeAndHus">
  SELECT
  w.wife_name,
  w.age wife_age,
  hus.*
  FROM husband hus
  JOIN wife w ON w.hus_id = HUS.hus_id
</select>
```

> association：表示进行关联查询单条记录
>
> property：表示将关联查询数据库column列的值放入到property属性中
>
> javaType：表示关联查询的结果类型析



### 一对多级联查询

一对多关联映射同一对一关联映射的方式二相同，试用自定义的ResultMap

```xml
<!-- 一对多关联映射 这里会映射为orders集合  -->
<collection property="orders" ofType="cn.itheima.po.Orders"> 
  <id property="id" column="oid"/>	
  <!--用户id已经在user对象中存在，此处可以不设置-->
  <!-- <result property="userId" column="id"/> -->
  <result property="number" column="number"/>
  <result property="createtime" column="createtime"/>
  <result property="note" column="note"/>
</collection>
```



## Spring整合MyBatis

1. `SqlSessionFactory`对象应该放到spring容器中作为单例存在。
2. 传统dao的开发方式中，应该从spring容器中获得`sqlsession`对象。
3. Mapper代理形式中，应该从spring容器中直接获得`mapper`的代理对象。
4. 数据库的连接以及数据库连接池事务管理都交给spring容器来完成。



```xml
<!--sqlMapConfig.xml-->
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<typeAliases>
		<package name="cn.itcast.mybatis.pojo"/>
	</typeAliases>
	<mappers>
		<mapper resource="sqlmap/User.xml"/>
	</mappers>
</configuration>
```

```xml
<!-- applicationContext.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context" xmlns:p="http://www.springframework.org/schema/p"
	xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
	http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd
	http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.0.xsd 			   http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.0.xsd
	http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-4.0.xsd">

	<!-- 加载配置文件 -->
	<context:property-placeholder location="classpath:db.properties" />
	<!-- 数据库连接池 -->
	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
		destroy-method="close">
		<property name="driverClassName" value="${jdbc.driver}" />
		<property name="url" value="${jdbc.url}" />
		<property name="username" value="${jdbc.username}" />
		<property name="password" value="${jdbc.password}" />
		<property name="maxActive" value="10" />
		<property name="maxIdle" value="5" />
	</bean>
	<!-- mapper配置 -->
	<!-- 让spring管理sqlsessionfactory 使用mybatis和spring整合包中的 -->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<!-- 数据库连接池 -->
		<property name="dataSource" ref="dataSource" />
		<!-- 加载mybatis的全局配置文件 -->
		<property name="configLocation" value="classpath:mybatis/SqlMapConfig.xml" />
	</bean>
</beans>
```

> db.properties 省略

参考模块代码：`spring-mybatis`



## 逆向工程 

### mybatis-generator

Mybatis需要程序员手动编写SQL，表过多时，会出现大量的重复工作，所以，mybatis提供了逆向工程。可以根据数据表生成 Mapper接口，SqlMapper映射文件，实体类等。

**Maven插件方式具体操作：**

1. 添加maven依赖mybatis-generator-core
2. 依赖maven插件mybatis-generator-maven-plugin
3. 使用 idea mybatis plugins 插件 new 一个Mybatis GeneratorFile
4. 配置这个配置文件
5. 执行maven插件即可`mybatis-generator:generate `

### mybatis-generator-gui

可视化界面generator： https://github.com/zouzg/mybatis-generator-gui.git

