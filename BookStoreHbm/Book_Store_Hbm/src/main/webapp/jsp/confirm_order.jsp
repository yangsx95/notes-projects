<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Feathers
  Date: 2017/7/1
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>结算</title>
    <link type="text/css" rel="stylesheet" href="${path}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${path}/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/css/animate.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/css/cart.css"/>
    <style type="text/css">
        .form-control {
            width: 100%;
        }
    </style>
</head>
<body>

<%@include file="common/nav.jsp" %>

<!--购物车 begin-->
<!--外层div-->
<div class="container">
    <!--左边-->
    <div class="col-md-8 col-sm-12">
        <ol class="breadcrumb">
            <li>
                <a href="#" class="text-success"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;送货地址</a>
            </li>
        </ol>

        <!--送货地址  begin-->
        <div style="margin-bottom: 5px;" id="addrDiv">
            <ul class="list-group" id="address">
                <c:forEach var="i" items="${addresses}" varStatus="ii">
                    <c:if test="${ii.index <= 3}">
                        <li class="list-group-item">
                            <input type="radio" name="addr" value="${i.addressId}"
                        <c:if test="${i.isDefault eq '1'}"> checked </c:if>
                        >&nbsp;
                                ${i.area}&nbsp;${i.detailAddress}
                            <c:if test="${i.isDefault eq '1'}">
                                &nbsp;<span class="text-success">默认地址</span>
                            </c:if>
                        </li>
                    </c:if>
                    <c:if test="${ii.index > 3}">
                        <li class="list-group-item hidden"><input type="radio" name="addr"
                        <c:if test="${i.isDefault eq '1'}"> checked </c:if>
                        >&nbsp;
                                ${i.area}&nbsp;${i.detailAddress}
                        <c:if test="${i.isDefault eq '1'}">
                            &nbsp;<span class="text-success">默认地址</span>
                        </c:if>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
            <a href="#" class="text-info" onclick="dispalyMoreAddr(this)">更多地址↓&nbsp;&nbsp;</a>
            <a href="javascript:void(0)" id="addAddr" class="text-success" data-toggle="modal"
               data-target="#myModal">添加新地址</a>
        </div>
        <hr>


        <!--添加新地址模态框 begin-->
        <!-- 模态框（Modal） -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button id="closeAddAddr" type="button" class="close" data-dismiss="modal"
                                 aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="myModalLabel">
                            <span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;添加新收货地址
                        </h4>
                    </div>
                    <div class="modal-body">
                        <!--登录的form表单-->
                        <form action="" class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-md-2 control-label" style="margin-right: 2.9%;">所在地区:</label>

                                <div data-toggle="distpicker">
                                    <div class="form-group col-sm-12 col-md-3">
                                        <label class="sr-only" for="province1">Province</label>
                                        <select class="form-control" id="province1"></select>
                                    </div>
                                    <div class="form-group col-sm-12 col-md-3">
                                        <label class="sr-only" for="city1">City</label>
                                        <select class="form-control" id="city1"></select>
                                    </div>
                                    <div class="form-group col-sm-12 col-md-3">
                                        <label class="sr-only" for="district1">District</label>
                                        <select class="form-control" id="district1"></select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group has-feedback" style="clear:both">
                                <label for="deatails" class="col-sm-2 control-label">详细地址:</label>
                                <div class="col-sm-5">
                                    <textarea class="form-control" id="deatails"
                                              onblur="checkDetail()"></textarea>
                                </div>
                                <%--<p class="col-md-3 text-danger">错误</p>--%>
                            </div>
                            <div class="form-group has-feedback">
                                <label class="col-sm-2 control-label">邮政编码</label>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" id="emailCode"
                                           placeholder="邮政编码" onblur="checkEmailCode()">
                                    <span class="glyphicon glyphicon-hand-left form-control-feedback"></span>
                                </div>
                            </div>

                            <div class="form-group has-feedback">
                                <label class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="recname"
                                           placeholder="收货人姓名" name="receiver"
                                           onblur="checkRecname()">
                                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
                                </div>
                            </div>

                            <div class="form-group has-feedback">
                                <label class="col-sm-2 control-label">电话</label>
                                <div class="col-sm-5">
                                    <input type="tel" class="form-control" id="tel"
                                           placeholder="合法手机格式" name="tel"
                                            onblur="checkTel()">
                                    <span class="glyphicon glyphicon-phone form-control-feedback"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" id="setDefaultAddr"><span
                                                class="text-success" name="isDefault">设置默认地址</span>
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                    关闭
                                </button>
                                <input type="button" class="btn btn-primary"
                                       value="提交地址" onclick="addAddress()">
                            </div>
                        </form>
                        <!--form结束-->
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
        </div>
        <!--左边-->
        <ol class="breadcrumb">
            <li>
                <a href="#" class="text-success"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;结算清单</a>
            </li>
        </ol>

        <!--购物车表格 begin-->
        <div class="table-responsive" id="imgDiv">
            <table class="table table-hover table-striped" style="vertical-align:middle;">
                <thead>
                <tr class="text-success success">
                    <th id="sequence">序号</th>
                    <th>图片</th>
                    <th>书名</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>合计</th>
                </tr>
                </thead>
                <tbody id="tby">
                <c:forEach var="b" items="${newItems}" varStatus="i">
                    <tr>
                        <td>${b.value.book_isbn}</td>
                        <td><img src="${path}/images/${b.value.imageUrl}"></td>
                        <td>${b.value.book_name}</td>
                        <td class="price">${b.value.price}元</td>
                        <td class="count con_count">${b.value.count}</td>
                        <td class="con_price">100$</td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="4"></td>
                    <td class="text-success">总价:</td>
                    <td class="text-success" id="con_total">$:</td>
                </tr>
                <tr style="background-color: white;">
                    <td>
                        <a href="index.html" class="btn btn-info">&lt;&lt;继续购买</a>
                    </td>
                    <td colspan="4"></td>
                    <td>
                        <a href="javascript:tijiao();" class="btn btn-warning">提交订单</a>
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
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">&times;
                            </button>
                            <h4 class="modal-title text-warning" id="myModalLabel">抱歉!请您先选择商品!</h4>
                        </div>
                        <div class="modal-body">请选进行勾选!再执行删除删除!</div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                            </button>
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
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">&times;
                            </button>
                            <h4 class="modal-title text-warning" id="myModalLabel">删除商品,慎重操作!</h4>
                        </div>
                        <div class="modal-body">此操作一旦进行,数据将不可恢复!</div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                            </button>
                            <button type="button" class="btn btn-danger" onclick="delRows(this)">
                                确定删除
                            </button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
            </div>
            <!--two_del  end-->
        </div>
        <!--删除提示模态框 end-->
    </div>

    <!--右边-->
    <%@include file="common/right.jsp" %>
</div>

<%@include file="common/footer.jsp" %>

<script src="${path}/dist/js/jquery.min.js"></script>
<script src="${path}/js/wow.js"></script>
<script src="${path}/js/carousel.js" type="text/javascript"></script>
<script src="${path}/dist/js/bootstrap.min.js"></script>
<script src="${path}/dist/js/distpicker.data.min.js"></script>
<script src="${path}/dist/js/distpicker.min.js"></script>
<script src="${path}/js/details.js" type="text/javascript" charset="utf-8"></script>
<script src="${path}/js/cart.js" type="text/javascript" charset="utf-8"></script>
<script src="${path}/js/confirm_order.js"></script>
<script type="text/javascript">


    if (!(/msie [6|7|8|9]/i.test(navigator.userAgent))) {
        new WOW().init();
    }

    function updateTotal() {
        // alert("更新界面");
        // 获取合计
        $(".con_price").each(function () {
            // 获取总价格
            var price = parseFloat($(this).prev().prev().html());
            var count = $(this).prev().html();
//            alert(price + "==" + count);
            $(this).html((price * count).toFixed(2) + "$");
        });

        // 获取总价
        var tt = 0;
        $("#tby>tr").each(function () {
            var p = parseFloat($(this).children("td:last").html());
            tt += p;
        });
        $("#con_total").html(tt + "$");
    }

    updateTotal();

    /**
     * 显示错误
     * @param obj 要进行显示错误的控件
     * @param message 显示错误的内容
     * @param test 验证规则 闭包函数（回调）
     */
    function showError(obj, message, test) {
        if (!test()) { // 验证失败
            if (!$(obj).parent().parent().has("p.text-danger").length){
                $(obj).parent().parent().append("<p class='text-danger col-md-3'>" + message +
                    "</p>")
            }
        } else { // 验证陈宫
            $(obj).parent().parent().children("p.text-danger").remove();
        }
    }


    var flag = true;
    // 检查详细地址
    function checkDetail() {
        // 获取详细地址
        showError($("#deatails"), "请输入详细信息", function () {
            if($("#deatails").val().trim().length == 0) {
                flag = false;
                return false;
            }
            flag = true;
            return true;
        });
    }

    // 检查邮编
    function checkEmailCode() {
        showError($("#emailCode"), "请输入邮编", function () {
            if($("#emailCode").val().trim().length == 0) {
                flag = false;
                return false;
            }
            flag = true;
            return true;
        });
    }

    // 检查姓名
    function checkRecname() {
        showError($("#recname"), "请输入姓名", function () {
            if($("#recname").val().trim().length == 0) {
                flag = false;
                return false;
            }
            flag = true;
            return true;
        });
    }

    // 检查手机号
    function checkTel() {
        showError($("#tel"), "格式不正确", function () {
            var partten = /^[1][358][0-9]{9}$/;
            if(!partten.exec($("#tel").val())) {
                flag = false;
                return false;
            }
            flag = true;
            return true;
        });
    }

    function addAddress() {
        flag = true;
        // 进行验证
        checkDetail();
        checkEmailCode();
        checkRecname();
        checkTel();
        if (flag) {
            // 获取area
            var area = $("#province1>option:selected").html() +
                $("#city1>option:selected").html() +
                $("#district1>option:selected").html();
            // 获取详细信息
            var detail = $("#deatails").val();
            // 获取邮政编码
            var emailCode = $("#emailCode").val();
            // 获取姓名
            var name = $("#recname").val();
            // 获取电话号码
            var tel = $("#tel").val();
            // 拼接url
            var url = "area="+area
                +"&emailCode="+emailCode
                +"&detail="+detail
                +"&name="+name
                +"&tel="+tel
                +"&isDefault="+$("#setDefaultAddr").is(":checked");
//            alert(url);
            // 启用ajax ，将信息发送到后台，完成添加地址
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    alert("===="+xmlhttp.responseText);
                    if (xmlhttp.responseText == 1) {
                        complete();
                        // 关闭模态框
                        $("#closeAddAddr").click();
                    }
                }
            };
            xmlhttp.open("POST","${path}/permission/addAddress",true);
            xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xmlhttp.send(url);
        } else {
            alert("验证失败");
        }
    }

    // 完成address增加
    function complete() {
        // 关闭模态框

        // 将这些数据添加到前台中
        // 获取area
        var area = $("#province1>option:selected").html() +
            $("#city1>option:selected").html() +
            $("#district1>option:selected").html();
        // 获取详细信息
        var detail = $("#deatails").val();

        // 拼接一个li
        var li = "<li class='list-group-item'>" +
            "<input type='radio' name='addr' checked> &nbsp;";
        li += area + detail;
        if ($("#setDefaultAddr").is(":checked")) {
            // 先清除所有默认地址的span标签
            $("#address span").remove();
            li += "&nbsp;<span class='text-success'>默认地址</span>";
        }
        li += "</li>";

        var address = $("#address").append(li);
    }

    function tijiao() {
        var rd = $("[name=addr]:checked");
        if (rd.length==0) {
            alert("对不起，请选择地址");
            return;
        }
        alert(rd.val());
        window.location.href = "${path}/permission/addOrder?addressId="+rd.val();
    }
</script>
</body>
</html>
