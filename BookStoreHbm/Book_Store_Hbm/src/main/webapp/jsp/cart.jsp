<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>我的购物车</title>
		<link type="text/css" rel="stylesheet" href="${path}/dist/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${path}/css/index.css"/>
		<link rel="stylesheet" type="text/css" href="${path}/css/animate.css"/>
		<link rel="stylesheet" type="text/css" href="${path}/css/cart.css" />
	</head>

	<body onload="changeNum()">
		<%@include file="common/nav.jsp"%>
		<!--购物车 begin-->
		<!--外层div-->
		<div class="container">
			<!--左边-->
			<div class="col-md-8 col-sm-12">
				<!--左边-->
				<ol class="breadcrumb">
					<li>
						<a href="#" class="text-success"><span class="glyphicon glyphicon-heart"></span>&nbsp;&nbsp;我的宝贝</a>
					</li>
				</ol>

				<!--购物车表格 begin-->
				<div class="table-responsive" id="imgDiv">
					<table class="table table-hover table-striped" style="vertical-align:middle;">
						<thead>
							<tr>
								<td colspan="8">
									<label class="text-primary">显示条数:</label>
									<select id="cartSzie" class="form-control" style="display: inline" onchange="displaySize(this)">
										<option value="2">2</option>
										<option value="4">4</option>
										<option value="6" selected>6</option>
										<option value="8">8</option>
									</select>
								</td>
							</tr>
							<tr class="text-success success">
								<th><input type="checkbox" id="selectAll" onclick="selectAll(this)"></th>
								<th id="sequence">序号</th>
								<th>图片</th>
								<th>书名</th>
								<th>单价</th>
								<th>数量</th>
								<th>合计</th>
							</tr>
						</thead>
						<tbody id="tby">
							<c:forEach var="b" items="${sc.items}" varStatus="i">
							<tr>
								<td><input type="checkbox" name="cks" value="${b.value.itemId}"></td>
								<td>${b.value.book_isbn}</td>
								<td><img src="${path}/images/${b.value.imageUrl}"></td>
								<td>${b.value.book_name}</td>
								<td class="price">${b.value.price}元</td>
								<td class="count">${b.value.count}</td>
								<td>100$</td>
							</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="5"></td>
								<td  class="text-success">总价:</td>
								<td id="total" class="text-success">345$:</td>
							</tr>
							<tr style="background-color: white;">
								<td>
									<a href="${path}/book/index" class="btn btn-info">&lt;&lt;继续购买</a>
								</td>
								<td><button class="btn btn-danger" data-toggle="modal" data-target="#myModal" onclick="del()">删除商品</button></td>
								<td colspan="4"></td>
								<td>
									<a href="javascript:jiesuan();" class="btn btn-warning">结算商品</a>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
				<!--购物车 end-->

				<!--删除提示模态框 begin-->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					  aria-labelledby="myModalLabel" aria-hidden="true">
					<!--如果没有选中任何一个商品的时候,出现-->
					<div id="first_del" style="display: none;">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title text-warning" id="myModalLabel">抱歉!请您先选择商品!</h4>
								</div>
								<div class="modal-body">请选进行勾选!再执行删除删除!</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
					</div>
					<!--first_del  end-->

					<!--如果没有选中了一个商品的时候,出现-->
					<div id="two_del" style="display: none;">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title text-warning" id="myModalLabel">删除商品,慎重操作!</h4>
								</div>
								<div class="modal-body">此操作一旦进行,数据将不可恢复!</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
									<button type="button" class="btn btn-danger" onclick="delRows(this)">确定删除</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
					</div>
					<!--two_del  end-->
				</div>
				<!--删除提示模态框 end-->
			</div>

			<%@include file="common/right.jsp"%>
		</div>

		<%@include file="common/footer.jsp"%>

		<!--引入js文件-->
		<script src="${path}/dist/js/jquery.min.js"></script>
		<script src="${path}/js/wow.js"></script>
		<script src="${path}/js/carousel.js" type="text/javascript"></script>
		<script src="${path}/dist/js/bootstrap.min.js"></script>
		<script src="${path}/js/details.js" type="text/javascript" charset="utf-8"></script>
		<script src="${path}/js/cart.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			if(!(/msie [6|7|8|9]/i.test(navigator.userAgent))) {
				new WOW().init();
			}


            updateTotal();

            //删除任意行
            function delRows(obj){
                var cks = document.getElementsByName("cks");
                var tby = document.getElementById("tby");
                var str = "?";
                for(var i=cks.length-1;i>=0;i--){
                    if(cks[i].checked){
//                        tby.removeChild(cks[i].parentNode.parentNode);
                        str += "id=";
                        str += $(cks[i]).val();
                        str += "&";
                    }
                }
                <%--alert("${path}/permission/cart/removeCartAjax"+str);--%>
                //调用关闭按钮的单击事件
				// 调用ajax进行实际删除
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function () {
					if (xmlhttp.readyState ==4 && xmlhttp.status == 200) {
                        if (xmlhttp.responseText == 0) {
                            alert("删除失败");
                        } else {
                            var arr = xmlhttp.responseText.split(":");
                            // 更新右侧的面包屑
                            $("#spcount").html(arr[0]);
                            $("#sptotal").html(arr[1]);
                            for(var i=cks.length-1;i>=0;i--){
                                if(cks[i].checked){
                                    tby.removeChild(cks[i].parentNode.parentNode);
                                }
                            }
                            alert("删除成功");

                        }
                        obj.previousElementSibling.click();
                    }
                };
                xmlhttp.open("get", "${path}/permission/cart/removeCartAjax" + str);
                xmlhttp.send();
            }

            function jiesuan() {
				// 获取被选中的id
                var cks = document.getElementsByName("cks");
                var str = "?";
                if (cks.length == 0) {
                    alert("对不起，请加入商品后结算");
                    return;
                }
                var count = 0;
                for (var i = 0; i < cks.length; i ++){
                    if (cks[i].checked){
                        str += "id=";
                        str += cks[i].value;
                        str += "&";
                        count++;
					}
				}
				if (count == 0){
                    str = "?id=all";
				}
                var url = "${path}/permission/confirmOrder" + str;
//                alert(url);
                window.location.href = url;
            }
		</script>
	</body>

</html>