<%@ page import="me.feathers.online.entity.Book" %>
<%@ page import="java.util.Date" %>
<%@ page import="me.feathers.online.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>图书详情</title>
    <link type="text/css" rel="stylesheet" href="${path}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${path}/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/css/animate.css"/>
</head>

<body>

<%--引入导航部分--%>
<%@include file="common/nav.jsp" %>

<!--外层div-->
<div class="container">
    <!--左边-->
    <div class="col-md-8 col-sm-12">
        <ol class="breadcrumb">
            <li>
                <a href="#">图书详情</a>
            </li>
            <li>
                <a href="#">${book.name}</a>
            </li>
        </ol>

        <div class="row">
            <div class="col-sm-12 col-md-6">
                <div class="thumbnail">
                    <a><img src="${path}/images/${book.image.url}" style="height: 200px;"
                            alt="通用的占位符缩略图"></a>
                    <div class="caption">
                        <h3>${book.name}</h3>
                        <p>双十一特价,
                            包邮哟!亲<img src="${path}/images/3.gif"
                                      style="width: 24px;height: 24px;"></p>
                        <p>
                        <div style="margin-bottom: 5px;width: 100px;">
                            <input type="number" class="form-control" placeholder="购买数量" min="1"
                                   max="100" onclick="selectByCount(this)" id="count">
                        </div>
                        <div style="clear: both;">
                            <!--<input type="number" class="form-control" placeholder="请输入" min="1" max="100" onclick="selectByCount(this)">-->
                            <a href="${path}/permission/confirmOrder?bynow=true"
                               class="btn btn-default"
                               role="button">
                                <span class="glyphicon glyphicon-usd"></span> 立即购买
                            </a>
                            <a href="javascript:joinCart(${book.bookId})" class="btn btn-default"
                               role="button">
                                <span class="glyphicon glyphicon-shopping-cart"></span> 加入购物车
                            </a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-sm-12 col-md-6">
                <div class="thumbnail">
                    <ul class="list-group">
                        <li class="list-group-item"><span class="text-success">图书名称:
                            ${book.name}</span>
                        </li>
                        <li
                                class="list-group-item"><span
                                class="text-info">作者:</span>${book.author}</li>
                        <%
                            Book book = (Book) request.getAttribute("book");
                            Date date = book.getPublish_date();
                            String s = DateUtil.format(date, "yyyy年MM月dd日");
                            request.setAttribute("date", s);
                        %>
                        <li class="list-group-item"><span class="text-info">出版日期:${date}
                            </span>
                        </li>
                        <li class="list-group-item"><span
                                class="text-info">出版社:</span>${book.publisher}</li>
                        <li class="list-group-item"><span class="text-info"
                                                          style="text-decoration: line-through;">原价:</span><span
                                style="text-decoration: line-through;">$ ${book.oldPrice}元</span>
                        </li>
                        <li class="list-group-item"><span class="text-info">现价:</span>$${book
                                .newPrice}元
                        </li>
                        <li class="list-group-item"><span class="text-info">说明:</span>
                            ${book.info}
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!--<hr>-->
        <!--折叠部分 begin-->
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                        展开详细信息
                    </a>
                </h4>
            </div>
            <div id="collapseTwo" class="panel-collapse collapse">
                <div class="panel-body">
                    <!--tab选项卡 begin-->
                    <ul id="myTab" class="nav nav-tabs">
                        <li class="active">
                            <a href="#home" data-toggle="tab">
                                商品详情
                            </a>
                        </li>
                        <li>
                            <a href="#ios" data-toggle="tab"> <span
                                    class="badge pull-right">50</span>累计评价</a>
                        </li>
                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade in active" id="home">
                            <p>
                                <em>产品参数：</em>
                            </p>
                            <div class="row">
                                <div class="col-sm-12 col-md-6">
                                    <ul class="nav navbar-collapse" style="line-height: 3">
                                        <li class=" text-info
                                    ">产品名称：${book.name}</li>
                                        <li class="text-info">是否是套装: ${book.suit}</li>
                                        <li class="text-info">书名: ${book.name}</li>
                                        <li class="text-info">定价: ${book.newPrice}元</li>
                                        <li class="text-info">出版社名称:${book.publisher}</li>
                                        <li class="text-info">出版时间: ${date}</li>
                                        <li class="text-info">作者: ${book.author}</li>
                                        <li class="text-info">作者地区: ${book.author_loc}</li>
                                        <li class="text-info">书名: ${book.name}</li>
                                        <li class="text-info">ISBN编号: ${book.isbn}</li>
                                    </ul>
                                </div>
                                <span class="visible-sm visible-xs"><hr></span>

                            </div>
                        </div>
                        <div class="tab-pane fade" id="ios">
                            <p>
                            <ul class="list-group">
                                <li class="list-group-item"><span class="text-info">小明:</span><span
                                        class="text-success ">这真的是一本好书<img
                                        src="${path}/images/emotions/1.gif "></span></li>
                                <li class="list-group-item"><span
                                        class="text-info">success:</span><span class="text-danger ">书的质量很差<img
                                        src="${path}/images/emotions/2.gif "></span></li>
                                <li class="list-group-item"><span class="text-info">叶老师:</span><span
                                        class="text-success ">很值得初学者学习<img
                                        src="${path}/images/emotions/13.gif "></span></li>
                                <li class="list-group-item"><span
                                        class="text-info">amdin:</span><span class="text-danger ">被坑大发了.<img
                                        src="${path}/images/emotions/17.gif "></span></li>
                                <li class="list-group-item"><span
                                        class="text-info">rose:</span><span class="text-danger ">卖家态度恶劣.<img
                                        src="${path}/images/emotions/11.gif "></span></li>
                            </ul>
                            <p>
                            <ul class="pager">
                                <li>
                                    <a href="# ">&larr;上一页</a>
                                    <a href="# ">下一页 &rarr;</a>
                                </li>
                            </ul>
                            </p>
                        </div>
                    </div>
                    <!--tab选项卡 end-->
                </div>
            </div>
        </div>
        <!--折叠部分end-->
    </div>
    <%@include file="common/right.jsp"%>
    <hr>
</div>

<%@include file="common/footer.jsp" %>
<script src="${path}/dist/js/jquery.min.js"></script>
<script src="${path}/js/carousel.js" type="text/javascript" charset="utf-8"></script>
<script src="${path}/dist/js/bootstrap.min.js"></script>
<script src="${path}/js/wow.js"></script>
<script src="${path}/js/details.js"></script>

<script type="text/javascript">
    if (!(/msie [6|7|8|9]/i.test(navigator.userAgent))) {
        new WOW().init();
    }

    function joinCart(id) {

        var count = $("#count").val();
        if (count == null) {
            alert("请输入数量");
            return;
        } else if (count <= 0) {
            alert("请输入正确的数量");
            return;
        }

        // 启动一个ajax,将要加入的图书信息传送给后台servlet
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
//                alert(xmlhttp.responseText);
                // 更新页面中的信息
                var str = xmlhttp.responseText;
                var arr;
                if (str != null) {
                    arr = str.split(":");
                }
                $("#spcount").html(arr[0]);
                $("#sptotal").html(arr[1]);
            }
        };

        xmlhttp.open("POST", "${path}/permission/cart/addCartAjax", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("id=" + id + "&count=" + count);
    }
</script>
</body>
</html>