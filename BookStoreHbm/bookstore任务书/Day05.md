#Day05
##任务详细说明：
###功能设计
此功能是在添加购物车功能的基础上，进行对购物车中商品数量进行修改、删除和清空购物车功能</br>

控制器功能：
![购物车控制](../img/213.png)</br>
CartAction.java 购物车功能控制器</br>

<span style="color:red">在此强调，以上的图示只是一个参考，你完全可以自行定义任意的Action</span></br>

购物车页面：
![购物车](../img/214.png)</br>
此页面的详细介绍：</br>
1、继续买，表示回到图书选购页面。</br>
2、删除功能，可以删除选中的一或多个购物条目。</br>
3、数量修改功能，数据可以添加或减少，但不能为负数。</br>
4、结算功能，进入到结束页面，并把购物车中的数据传递到下一个页面。</br>

1.继续买：</br>
此操作的请求传到控制器，由控制器响应一个图书页面给客户短，比较容易实现</br>

2.删除购物车：</br>
在做删除功能时，要选择中要删除的商品</br>
前台ajax处理代码【<span style="color:red">只是参考</span>】</br>：

```java
    	function removeCart(){
			var ids = "";
			var count = 0;
			var checks = document.getElementsByName("itemCheck");
			for(var i=0;i<checks.length;i++){
				if(checks[i].checked){
					count++;
					ids += checks[i].value+":";
				}
			}
			
			if(count < 1){
				alert("至少选中一个商品!!!")
				return ;
			}else {
				var xmlHttp = new XMLHttpRequest();
				xmlHttp.onreadystatechange = function(){
					if(xmlHttp.status == 200){
						if(xmlHttp.readyState == 4){
							var str = xmlHttp.responseText;
							
							if(str == '1'){
								alert("成功删除!!!");
								location = "${path }/cart/showcart";
							}
						}
					}
				}
				var url = "${path }/cart/removeCartAjax?ids="+ids;
				xmlHttp.open("get",url,true);
				xmlHttp.send();
				
			}
		}
```

请求发送到后台，后台控制器操作的代码片段：【<span style="color:red">只是参考</span>】</br>

```java
case _REMOVE_CART:
    			try {
					/**接受发送来要删除的id字符串*/
					String ids = request.getParameter("ids");
					/**将id字符串分割成单个id数组，并且遍历这个数组，根据id来逐个删除商品信息,成功则返回'1'*/
					String[] arr = ids.substring(0, ids.length() - 1).split(":");// 把接收到的ID分开成数组
					for (String id : arr) {
						shoppingCart.delete(Long.valueOf(id));
					}
					out.print("1");
				} catch (Exception e) {
					// TODO: handle exception
					/**有异常返回'0'*/
					out.print("0");
					e.printStackTrace();
				}
				break;
```

购物车中的处理逻辑代码片段:【<span style="color:red">只是参考</span>】</br>

```java
@Override
    public void delete(Long bookId) {
		//
		OrderItem oi = find(bookId);
		//
		if(oi != null) {
			items.remove(bookId);
			//更新
			update();
		}
		
	}
```    

3.修改购物车
此修改是在购物车中商品列的输入框中修改数量，然后利用失去焦点事件，利用ajax发送请求到后台
服务进行修改购物车中商品数量：</br>

前台ajax的核心代码：【<span style="color:red">只是参考</span>】</br>

```java
function modifyItem(id,obj){
    		var count = obj.value;
			if(/^[1-9]\d*$/.test(count)){
				var xmlHttp = new XMLHttpRequest();
				xmlHttp.onreadystatechange = function(){
					if(xmlHttp.status == 200){
						if(xmlHttp.readyState == 4){
							var str = xmlHttp.responseText;
							
							if(str == '1'){
								alert("成功修改!!!");
								location.reload();
							}
						}
					}
				}
				var url = "${path }/cart/modifyCartAjax?bookId="+id+"&count="+count;
				xmlHttp.open("get",url,true);
				xmlHttp.send();
				
			}else {
				alert("数字格式不对!!!");
			}
		}
```

后台控制器处理：【<span style="color:red">只是参考</span>】</br>

```java
case _MODIFYCART:
    			/* PrintWriter out = response.getWriter(); */
				try {
					/**接收页面发来的修改参数*/
					String bookId = request.getParameter("bookId");
					String count = request.getParameter("count");
					/**根据参数修改购物车的数据，成功没有异常就返回‘1’*/
					shoppingCart.modifyCount(Long.valueOf(bookId), Integer.parseInt(count));
					out.write("1");
				} catch (Exception e) {
					// TODO: handle exception
					/**有异常则返回'0'*/
					out.print("0");
					e.printStackTrace();
				}
				break;
```

购物车中逻辑处理：【<span style="color:red">只是参考</span>】</br>

```java
@Override
    public void modifyCount(Long bookId, int count) {
		//
		OrderItem oi = find(bookId);
		//修改数量
		if(oi != null) {
			//进一步验证参数的有效性
			if(count > 0) {
				oi.setCount(count);
				//更新属性
				update();
			} else {
				throw new RuntimeException("修改的数量不合法");
			}
		}
	}
    
    /****
     * 此方法的目的就是更新total属性和count属性值
	 */
	private void update() {
		//先清0
		this.total = 0;
		this.count = 0;
		//迭代
		Set<Long> keys = items.keySet();
		//
		for(Long bookId : keys) {
			OrderItem value = items.get(bookId);
			//把费用添加起来
			this.total += (value.getPrice()*value.getCount());
			//把商品总数加起来
			this.count += value.getCount();
		}
	}

```

4.结算功能【<span style="color:red">进入到第6个任务单</span>】


