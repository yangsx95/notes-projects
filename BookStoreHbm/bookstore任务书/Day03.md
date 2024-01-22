#Day03
##任务详情说明
###功能设计
此任务单完成用户模块的功能开发，包括用户的登录、注册、注销
整体的功能的层次机构如下：</br>
视图层JSP：</br>
 <span style="color:red">注：登录和注册是通过bootstrap来编写的，都在index主页上，如下图：</span></br>
 ![登录](../img/209.png)
 ![注册](../img/210.png)

控制层控制器：</br>
![控制层](../img/211.png)

UserAction.java：用户的控制器

<span style="color:red">注：与以前的任务单一样，我们不限定各位开发控制器的个数和模式，你们完全可以自定义已上的各个类型，或只写一个Action也行【你的项目，你做主】</span></br>

业务接口：

```java
public interface IUserService {
    /**
	 * 验证用户是否合法
	 * @param name 用户名
 	 * @param pwd  密码
	 * @return
	 */
	User login(String name, String pwd) throws LoginException;
	/**
	 * 完成用户的注册功能
	 * @param user
	 */
	void register(User user);
	/*****
	 * 验证某个用户名是否可用， 如果可用，则返回true, 否则，返回false
	 * @param name
	 * @return
	 */
	boolean validate(String name);
	/*****
	 * 添加地址
	 * @param a
	 */
	void addAddress(Address a);
	/******
	 * 删除地址
	 * @param a
	 */
	void delAddress(Address a);
	
	/*****
	 * 更新指定的地址信息
	 * @param a
	 */
	void updAddress(Address a);
	
	/****
	 * 根据用户查询此用户的所有地址
	 * @param user
	 * @return
	 */
	List<Address> getAddressByUser(User user);
	
	/*****
	 * 按主键查询地址
	 * @param id
	 * @return
	 */
	Address getAddrById(Long id);
}
```

数据访问层接口：

```java
public interface IUserDao {
    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    User selectByName(String userName);

    /**
     * 保存用户对象
     *
     * @param user
     */
    void save(User user);

    /*****
     * 更新用户
     *
     * @param user
     */
    void update(User user);
}

public interface IAddressDao {
    /**********
     * 保存地址
     * @param a
     */
    void save(Address a);

    /****
     * 获取指定用户id的所有地址
     * @return
     */
    List<Address> findByUserId(Long userId);

    /****
     * 删除某个地址
     * @param a
     */
    void delete(Address a);

    /*****
     * 依ID查询
     * @param id
     * @return
     */
    Address findById(Long id);

    /****
     * 更新地址
     * @param a
     */
    void update(Address a);
}

```

<span style="color:red">注：此业务汉两个Dao，每个Dao只关注自己的GRUD
操作，在业务层方面进行整合。</span></br>

用户注册:</br>
![用户注册](../img/210.png)

在录入玩用户名之后，有一个失去焦点事件，在此事件上，利用ajax请求服务程序
某个Action中的某个验证方法来验证用户名是否重复</br>

注册的控制器实现：【<span style="color:red">请自行实现</span>】</br>

注册的业务实现：【<span style="color:red">请自行实现</span>】</br>

JSP实现：【<span style="red">请自行实现</span>】</br>

登录功能：</br>
通过页面导航的登录按钮点击，弹出登录界面</br>
![登录界面](../img/209.png)

登录的控制器实现：【<span style="color:red">请自行实现</span>】</br>

登录的业务实现：【<span style="color:red">请自行实现</span>】</br>

登录的数据访问实现：【<span style="color:red">请自行实现</span>】</br>

JSP实现：【<span style="color:red">请自行实现</span>】</br>

用户注销：</br>
提供给用户退出登录功能</br>
当用户在登录状态，点击页面的注销，即可退出登录状态：</br>
此功能只需要删除session中的用户对象即可，返回首页</br>

注销的控制器实现：【<span style="color:red">请自行实现</span>】</br>

注销的业务实现：【<span style="color:red">请自行实现</span>】</br>

注销的数据访问实现：【<span style="color:red">请自行实现</span>】</br>

JSP实现：【<span style="color:red">请自行实现</span>】</br>



















