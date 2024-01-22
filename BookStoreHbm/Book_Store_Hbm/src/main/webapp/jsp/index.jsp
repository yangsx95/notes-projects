<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head lang="en">
    <meta charset="UTF-8">
    <title>在线书城首页</title>
    <link type="text/css" rel="stylesheet" href="${path}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${path}/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/css/animate.css"/>
</head>

<body>

<%--引入导航部分--%>
<%@include file="common/nav.jsp" %>

<!--图书内容部分 begin-->
<div class="container">
    <!--精选图书 begin-->
    <div>
        <div class="text-primary"><img src="${path}/images/bullet1.gif" alt="" title="">
            <a href="#">精选图书</a>
        </div>
        <div class="row">
            <div class="wrapper row">
                <c:forEach var="b1" items="${bean1}">
                    <div class="col-sm-6 col-md-3 wow fadeInLeft animated">
                        <div class="thumbnail">
                            <a href="${path}/book/details?id=${b1.bookId}">
                                <img src="${path}/images/${b1.image.url}" alt="通用的占位符缩略图">
                            </a>
                            <div class="caption">
                                <h3 style="display: inline;">${b1.name}</h3>
                                <p>价格:${b1.newPrice}元&nbsp;&nbsp;作者:${b1.author}&nbsp;&nbsp;</p>
                                <p>
                                    <a href="${path}/book/details">
                                        更多信息
                                    </a>
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!--分页-->
            <div class="container">
                <ul class="pager">
                    <li>
                        <a href="javascript:update(1, 0)">&larr;上一页</a>
                        <a href="javascript:update(1, 1)">下一页 &rarr;</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!--精选图书 end-->

    <!--推荐图书 begin-->
    <div>
        <div class="text-primary"><img src="${path}/images/bullet1.gif" alt="" title="">
            <a href="#">推荐图书</a>
        </div>
        <div class="row">
            <div class="wrapper row">
                <c:forEach items="${bean2}" var="b2">
                    <div class="col-sm-6 col-md-3 wow fadeInLeft animated">
                        <div class="thumbnail">
                            <a href="${path}/book/details?id=${b2.bookId}">
                                <img src="${path}/images/${b2.image.url}" alt="通用的占位符缩略图"></a>
                            <div class="caption">
                                <h3 style="display: inline;">${b2.name}</h3>
                                <p>价格:${b2.newPrice}元&nbsp;&nbsp;作者:${b2.author}&nbsp;&nbsp;</p>
                                <p>
                                    <a href="${path}/book/details">
                                        更多信息
                                    </a>
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!--分页-->
            <div class="container">
                <ul class="pager">
                    <li>
                        <a href="javascript:update(2, 0)">&larr;上一页</a>
                        <a href="javascript:update(2, 1)">下一页 &rarr;</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!--推荐图书end..-->

    <!--特价图书 begin-->
    <div>
        <div class="text-primary"><img src="${path}/images/bullet1.gif" alt="" title="">
            <a href="#">特价图书</a>
        </div>
        <div class="row">
            <div class="wrapper row">
                <c:forEach var="b3" items="${bean3}">
                    <div class="col-sm-6 col-md-3 wow fadeInLeft animated">
                        <div class="thumbnail">
                            <a href="${path}/book/details?id=${b3.bookId}"><img
                                    src="${path}/images/${b3.image.url}"
                                                       alt="通用的占位符缩略图"></a>
                            <div class="caption">
                                <h3 style="display: inline;">${b3.name}</h3>
                                <p>价格:${b3.newPrice}元&nbsp;&nbsp;作者:${b3.author}&nbsp;&nbsp;</p>
                                <p>
                                    <a href="${path}/book/details">
                                        更多信息
                                    </a>
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <!--分页-->
            <div class="container">
                <ul class="pager">
                    <li>
                        <a href="javascript:update(3, 0)">&larr;上一页</a>
                        <a href="javascript:update(3, 1)">下一页 &rarr;</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!--特价图书 end-->
    <hr>
    <!--图书内容部分end-->
    <%@ include file="common/footer.jsp" %>
</div>

<script src="${path}/dist/js/jquery.min.js"></script>
<script src="${path}/js/carousel.js" type="text/javascript" charset="utf-8"></script>
<script src="${path}/dist/js/bootstrap.min.js"></script>
<script src="${path}/js/wow.js"></script>

<script type="text/javascript">
    if (!(/msie [6|7|8|9]/i.test(navigator.userAgent))) {
        new WOW().init();
    }

    /**
     * 更新页面
     * @param cate 更新的类型  第一个 1 第二个 2 第三个 3
     * @param flag 0 上一页  1 下一页
     */

        ////////////////分页操作
    var bean1PageNow = 1;
    var bean2PageNow = 1;
    var bean3PageNow = 1;

    // 判断要跳转的页数
    var pageNow;

    function update(cate, flag) {
        // 获取页数
        var pageCount;
        if (cate == 1) {
            pageCount = ${bean1Count};
            if (flag == 0) {
                if (bean1PageNow - 1 <= 0) {
                    return;
                } else {
                    bean1PageNow--;
                }
            } else {
                if (bean1PageNow + 1 > pageCount) {
                    return;
                } else {
                    bean1PageNow++;
                }
            }
            pageNow = bean1PageNow;
        } else if (cate == 2) {
            pageCount = ${bean2Count};

            if (flag == 0) {
                if (bean2PageNow - 1 > 0) {
                    bean2PageNow--;
                } else {
                    return;
                }
            } else {
                if (bean2PageNow + 1 <= pageCount) {
                    bean2PageNow++;
                } else {
                    return;
                }
            }
            pageNow = bean2PageNow;
        } else {
            pageCount = ${bean3Count};
            if (flag == 0) {
                if (bean3PageNow - 1 > 0) {
                    bean3PageNow--;
                } else {
                    return;
                }
            } else {
                if (bean3PageNow + 1 <= pageCount) {
                    bean3PageNow++;
                } else {
                    return;
                }
            }
            pageNow = bean3PageNow;
        }

        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var str = xmlhttp.responseText;
                var list = JSON.parse(str);
//                alert(str);
                // 获取数据后，根据类型设置
                var sss = "";
                for (var i = 0; i < list.length; i++) {
                    sss += "<div class='col-sm-6 col-md-3 wow fadeInLeft animated'>";
                    sss += "<div class='thumbnail'>";
                    sss +=
                        "<a href='${path}/book/details?id="+list[i].bookId+"'><img src='${path}/images/" +
                        list[i].image.url + "'  alt='通用的占位符缩略图'></a>'";
                    sss += "<div class='caption'>";
                    sss += "<h3 style='display: inline;'>" + list[i].name + "</h3>";
                    sss += "<p>价格:" + list[i].newPrice + "元&nbsp;&nbsp;作者:" + list[i].author
                        + "&nbsp;&nbsp;</p>";
                    sss += "<p><a href='${path}/book/details'> 更多信息 </a> </p> </div> </div> </div>";
                }

                var div = document.getElementsByClassName("wrapper")[cate - 1];
                div.innerHTML = sss;
            }
        };

        var url = "${path}/book/update?cate=" + cate + "&pageNow=" + pageNow;
//        alert(url);

        xmlhttp.open("get", url, true);
        xmlhttp.send();

    }

</script>
</body>

</html>