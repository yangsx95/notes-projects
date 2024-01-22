<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head lang="en">
    <meta charset="UTF-8">
    <title>图书列表</title>
    <link type="text/css" rel="stylesheet" href="${path}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${path}/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/css/animate.css"/>
</head>

<body>

<%--引入导航部分--%>
<%@include file="common/nav.jsp" %>

<div class="container">
    <ol class="breadcrumb">
        <li><a href="#">图书列表</a></li>
        <li><a href="#">${category}</a></li>
    </ol>
</div>

<!--图书列表 begin-->
<div class="container">
    <div class="row">
        <c:forEach items="${pageBean.datas}" var="book">
            <div class="col-sm-6 col-md-3 wow fadeInLeft animated">
                <div class="thumbnail">
                    <a href="${path}/book/details?id=${book.bookId}">
                        <img src="${path}/images/${book.image.url}" alt="通用的占位符缩略图"></a>
                    <div class="caption">
                        <h4>${book.name}</h4>
                        <p>价格:${book.newPrice}元&nbsp;&nbsp;作者:${book.author}&nbsp;&nbsp;</p>
                        <p>
                            <a href="${path}/book/details?id=${book.bookId}">
                                更多信息
                            </a>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
</div>
<!--图书列表end-->

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

        // 获取category
        <%--alert("${category}");--%>
        var c;
        switch ("${category}"){
            case "精选图书":
                c = 0;
                break;
            case "推荐图书":
                c = 1;
                break;
            case "特价图书":
                c = 2;
                break;
            default:
                c = null;
        }

        // 获取pagesize
        var pageSize = $("#pageSize>option:selected").val();
        var url = "${path}/book/page?pageSize=" + pageSize +
            "&pageNow=" + pageNow;
        if(c != null) {
            url += ("&category=" + c);
        }
        window.location.href = url;
    }
</script>


<%@include file="common/footer.jsp" %>

<script src="${path}/dist/js/jquery.min.js"></script>
<script src="${path}/js/carousel.js" type="text/javascript" charset="utf-8"></script>
<script src="${path}/dist/js/bootstrap.min.js"></script>
<script src="${path}/js/wow.js"></script>
<script type="text/javascript">
    if (!(/msie [6|7|8|9]/i.test(navigator.userAgent))) {
        new WOW().init();
    }
</script>
</body>
</html>
