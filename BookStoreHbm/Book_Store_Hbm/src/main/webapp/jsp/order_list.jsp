<%@page pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <link type="text/css" rel="stylesheet" href="${path}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${path}/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/css/animate.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/css/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/css/order_list.css"/>
</head>

<body>

<%@include file="common/nav.jsp" %>

<!--订单begin-->
<!--外层div-->
<div class="container">
    <!--左边-->
    <div class="container pull-left">
        <ol class="breadcrumb">
            <li>
                <a href="#" class="text-success">
                    <span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;我的订单</a>
            </li>
        </ol>

        <!--订单查询导航 begin-->
        <div>
            <nav class="navbar navbar-default" role="navigation">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">所有订单</a>
                </div>

                <form class="navbar-form navbar-left" role="search">
                    <input type="text" class="form-control" placeholder="输入订单号进行查询"
                            id="orderNum">
                    <button type="button" class="btn btn-search" id="orderSearch"
                            onclick="searchOrder()">
                        <span class="text-success">Search</span>&nbsp;&nbsp;<span
                            class="glyphicon glyphicon-search"></span>
                    </button>
                </form>

                <ul class="nav navbar-nav navbar-left">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            交易状态 <input id="orderState" type="hidden" value="">
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="javascript:changeState()">全部</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="javascript:changeState('已付款')">已付款</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="javascript:changeState('未付款')">未付款</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="javascript:changeState('交易成功')">交易成功</a>
                            </li>
                            <li class="divider"></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-left">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            订单排序 <input id="orderBy" value="" type="hidden">
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="javascript:changeOrderBy('desc')">订单日期降序</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="javascript:changeOrderBy('asc')">订单日期升序</a>
                            </li>
                            <li class="divider"></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li style="padding-right: 15px;">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">更多筛选条件</a>
                    </li>
                </ul>

                <ul class="nav navbar-nav">
                    <li>
                        <a href="javascript:deleteOrder();">删除订单</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav">
                    <li>
                        <a href="javascript:clearOrder();">清空订单列表</a>
                    </li>
                </ul>
            </nav>
        </div>

        <!--订单查询导航 end-->
        <!--日期控件-->
        <div id="collapseTwo" class="panel-collapse collapse">
            <div class="panel-body">
                <div class="form-group">
                    <div>
                        <form class="form-horizontal">
                            <div class="form-group has-feedback">
                                <div class="col-md-1" style="padding-top:5px ;">
                                    成交日期:
                                </div>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="datetimepicker"
                                           placeholder="开始时间">
                                    <span class="glyphicon glyphicon-time form-control-feedback"></span>
                                </div>
                                <div class="col-md-1" style="padding-top:5px ;text-align:center;">
                                    至
                                </div>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="datetimepicker2"
                                           placeholder="结束日期">
                                    <span class="glyphicon glyphicon-time form-control-feedback"></span>
                                </div>
                                <div class="col-md-3">
                                    <button type="button" class="btn btn-default"
                                            onclick="searchByDate()">
                                        查询&nbsp;&nbsp;<span
                                            class="glyphicon glyphicon-search"></span>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!--日期控件-->

        <!--内容展示 begin-->
        <div class="table-responsive">
            <c:forEach var="d" items="${pageBean.datas}">
                <table class="table table-hover table-striped">
                    <caption class="text-info">
                        <div class="col-md-10" style="padding-left: 0px;">
                            <input type="checkbox" name="cks" value="${d.orderId}">&nbsp;
                                ${d.createDate}
                            订单号:${d.orderNum}单
                        </div>
                        <div class="col-md-2" style="text-align: right;">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree">订单详情</a>
                        </div>
                    </caption>
                    <thead>

                    <tr id="collapseThree" class="panel-collapse collapse">
                        <td colspan="9">
                            <div class="panel-body">
                                <!--tab选项卡 begin-->
                                <ul class="nav nav-tabs">
                                    <li class="active">
                                        <a href="#home" data-toggle="tab">
                                            联系人信息
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#ios" data-toggle="tab">订单信息</a>
                                    </li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane fade in active" id="home">
                                        <div class="row">
                                            <div>
                                                <ul class="nav navbar-collapse" style="line-height: 3;>
													<li class=" text-info
                                                ">
                                                <li>
                                                    <span class="text-success">姓名:</span><span
                                                        class="text-info">${d.address.receiver}</span>
                                                </li>
                                                <li class="text-info">
                                                        <span
                                                                class="text-success">联系方式:</span>
                                                        ${d.address.tel}
                                                </li>
                                                <li class="text-info">
                                                    <span class="text-success">收货地址:</span>
                                                        ${d.address.detailAddress}
                                                </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="ios">
                                        <div class="row">
                                            <div>
                                                <ul class="nav navbar-collapse" style="line-height: 3;>
													<li class=" text-info
                                                "><span class="text-success">订单编号:</span><span
                                                    class="text-info">${d.orderNum}</span></li>
                                                <li class="text-info"><span
                                                        class="text-success">交易时间:</span>
                                                    ${d.createDate}
                                                </li>
                                                <li class="text-info"><span
                                                        class="text-success">订单总金额:</span>5555元
                                                </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </tr>

                    <tr class="active">
                        <th>序号</th>
                        <th>图片</th>
                        <th>图片名称</th>
                        <th>单价(元)</th>
                        <th>数量</th>
                        <th>实付款(元)</th>
                        <th>付款日期</th>
                        <th>交易状态</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${d.items}" var="i">
                        <tr class="warning">
                            <td>${i.book_isbn}</td>
                            <td><img src="${path}/images/${i.imageUrl}"></td>
                            <td>${i.book_name}</td>
                            <td>${i.price}</td>
                            <td>${i.count}</td>
                            <td>${i.price * i.count}</td>
                            <td>${d.createDate}</td>
                            <td>${d.orderStatus}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:forEach>
        </div>
        <!--内容展示end-->
    </div>

    <%@include file="common/page.jsp" %>
    <script>
        // 定义一个全局变量，用来保存当前的页数
        var nowPage = "${pageBean.pageNow}";

        /**
         * 跳转指定的页数
         * @param pageNow 要跳转的页数
         */
        function jumpPage(pageNow) {
            nowPage = pageNow;
            // 获取pagesize
            var pageSize = $("#pageSize>option:selected").val();

            var url = "${path}/permission/order/page?pageSize=" + pageSize +
                "&pageNow=" + pageNow;
            // 获取当前的所选的订单状态
            var orderState = $("#orderState").val();

            if (orderState != null && orderState.trim().length >0) {
                url += ("&orderState=" + orderState);
            }

            // 获取订单排序方式
            var orderBy = $("#orderBy").val();
            if (orderBy != null && orderBy.trim().length > 0) {
                url += ("&orderBy=" + orderBy);
            }

            // 获取模糊搜索
            var orderNum = $("#orderNum").val();
            if (orderNum != null) {
                url += ("&orderNum=" + orderNum);
            }

            alert(url);
            window.location.href = url;
        }
        
        function searchOrder() {
            nowPage = 1;
            jumpPage(nowPage)
        }
    </script>

</div>

<%@include file="common/footer.jsp" %>

<script src="${path}/dist/js/jquery.min.js"></script>
<script src="${path}/dist/js/bootstrap.min.js"></script>
<script src="${path}/js/moment.js" type="text/javascript" charset="utf-8"></script>
<script src="${path}/js/bootstrap-datetimepicker.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript">
    $('#datetimepicker').datetimepicker();
    $('#datetimepicker2').datetimepicker();

    function changeState(str) {
        if (str == null) {
            $("#orderState").val("");
        }
        $("#orderState").val(str);

        jumpPage(nowPage);
    }

    function changeOrderBy(str) {
        if (str == null) {
            $("#orderBy").val("");
        }
        $("#orderBy").val(str);

        jumpPage(nowPage);
    }

    function deleteOrder(){
        var cks = document.getElementsByName("cks");
        var url = "${path}/permission/deleteOrder?orderId=";
        // 拼接参数
        var cks = document.getElementsByName("cks");
        if(cks.length == 0) {
            alert("请选择要删除的订单");
            return;
        }
        for (var i = 0; i < cks.length; i++) {
            if (cks[i].checked) {
                url += (cks[i].value);
                if (i != cks.length-1) {
                    url += ":";
                }
            }
        }
        alert(url);
        window.location.href = url;
    }
    function clearOrder() {
        var url = "${path}/permission/deleteOrder?orderId=all";
        alert(url);
        window.location.href = url;
    }
    // 根据日期查找订单
    function searchByDate() {
        var start = $("#datetimepicker").val();
        var end = $("#datetimepicker2").val();

        if((start == null || start.length <= 0) && (end == null && end.length <= 0)){
            alert("请输入日期后再查询");
            return;
        }

        var dStart;
        if (start != null) {
            dStart = convertDateFromString(start);
        }
        var dEnd;
        if (end != null) {
            dEnd = convertDateFromString(end);
        }

        // 日期前后判断
        if (dEnd != null && dStart != null) {
            if (dEnd.getTime() < dStart) {
                alert("输入不正确");
                return;
            }
        }

        // 作为参数传入
        var url = "${path}/permission/order/page?";
        if (start != null) {
            url += "start=" + start;
            url += "&";
        }
        if (end != null) {
            url += "end=" + end;
        }
        alert(url);
        window.location.href = url;
    }

    function convertDateFromString(dateString) {
        if (dateString) {
            var arr1 = dateString.split(" ");
            var sdate = arr1[0].split('-');
            var date = new Date(sdate[0], sdate[1]-1, sdate[2]);
            return date;
        }
    }
</script>
</body>
</html>