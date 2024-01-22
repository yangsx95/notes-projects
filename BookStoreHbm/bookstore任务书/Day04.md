#Day04
##任务详情说明
此任务书需要项目经理利用半天的时间讲解整个购物车的需求，然后学生利用半天完成
购物车中商品的添加</br>
###功能设计
此功能在商品的详情页面点击订购按钮，把要买的商品加入到自己的购物车中：</br>
此功能是利用ajax提交添加请求，请求成功，后台返回数据，然后在页面的此处</br>
![购物车](../img/212.png)显示购物车中商品数量和总价格。</br>
####后台服务程序处理：
![购物车](../img/215.png)</br>
IShopping.java购物车接口</br>
MemorCart.java购物车实现</br>
<span style="color:red">在此强调，以上的图示只是一个参考，你完全可以自行定义一个任意的Action</span></br>
#####购物车页面如下：
![购物车](../img/214.png)</br>
#####购物车业务层接口定义：

```java
public interface IShoppingCart {
    // 添加到购物车
	void addCart(OrderItem item);

	// 清空购物车
	void clear();

	// 修改数量
	void modifyCount(Long bookId, int count);

	// 删除某个条目
	void delete(Long bookId);

	// 获取总价
	double getTotal();

	// 获取商品数量
	int getCount();
	
	//获取购物车数据
	Map<Long, OrderItem> getItems();
	
	//获取购物车的商品详情
	OrderItem find(Long bookId);
	
}
```

#####参考实现：

```java
public class MemoryCart implements IShoppingCart {

    //属性
	private final Map<Long, OrderItem> items = new HashMap<>();
	private double total; //总费用
	private int count; //商品总数量
	
    ....

}
```

以上只给出这个实现的属性定义，所有的方法请自行根据业务来实现，如果不理解已上三个属性，
请找项目经理沟通。
<span style="color:red">注：Map中的Key类型，不一定是Long，请根据你的OrderItem实体类的id类型来定义。</span></br>
<span style="color:red">由于此业务所需要的数据无需固化到数据库中或文件中，所以，此业务中无需提供Dao层。</span></br>

#####控制层：

```java
WebServlet(urlPatterns="/cart/*")

public class CartAction extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -8315374505680816746L;
    /** 业务接口 */
    private final IShoppingCart shoppingCart;
    private final IBookService bookService;
    /** 请求URL资源常量 */
    private static final String _SHOPPING = "/addCartAjax";// 添加到购物车
    private static final String _TO_SHOPPINGCART = "/showcart";// 查看购物车
    private static final String _MODIFYCART = "/modifyCartAjax";// 修改商品数量
    private static final String _REMOVE_CART = "/removeCartAjax";// 删除商品
    private static final String _CLEAR_CART = "/clearCartAjax";// 清空购物车

    public CartAction() {
        super();
        this.shoppingCart = (IShoppingCart) BeanFactory.getBean("cartService");
        this.bookService = (IBookService) BeanFactory.getBean("bookService");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
	.....
    ....
    }
```

<span style="color:red">注：以上的代码片段只是一个参考，如果没有读懂的话，可以与项目经理沟通</span></br>

#####JSP页面
1.请根据原型自行开发，需要使用EL表达式和JSTL标签。</br>
2.在页面需要开发很多的JS脚本，比如涉及到DOM操作，AJAX操作，请自行完成。</br>
3.页面请求的URL应该与你的控制器的映射保持一致。</br>
4.完成测试的测试。</br>

#####页面视图展示：
![购物车](../img/214.png)</br>

此页面的详细介绍：【<span style="color:red">这几个功能在任务5中实现</span>】</br>
1、继续买，表示回到图书选购页面。</br>
2、删除功能，可以删除选中的一或多个购物条目。</br>
3、数量修改功能，数据可以添加或减少，但不能为负数。</br>
4、结算功能，进入到结束页面，并把购物车中的数据传递到下一个页面。</br>






