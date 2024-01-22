<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--分页begin-->
<%--

page.jsp
会根据当前域中的pageBean对象进行更新信息
会根据当前域中的链接，查询新的信息

--%>
<div class="container">
    <div class="row text-center">
        <div class="col-md-12">
            <ul id="pg_item" class="pagination">
                <li><a href="javascript:jumpPage('1')">首页</a></li>
                <li id="prePage"><a href="javascript:jumpPage(${pageBean.pageNow-1})">&laquo;</a></li>
                <%--判断要显示几个页数选择--%>
                <%--当页数小于5时，显示的item数为本身的页数--%>
                <c:if test="${pageBean.pageCount <= 5}">
                    <c:forEach begin="1" step="1" end="${pageBean.pageCount}" varStatus="i">
                        <li class="pg_item"><a
                                href="javascript:jumpPage('${i.index}')">${i.index}</a></li>
                    </c:forEach>
                </c:if>
                <%--当页数大于5时 保证当前页数在中间 --%>
                <c:if test="${pageBean.pageCount > 5}">
                    <%--根据当前页和总页数设置begin--%>
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>

                    <c:if test="${pageBean.pageNow <= 3}">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="5"/>
                    </c:if>
                    <c:if test="${pageBean.pageNow > 3}">
                        <c:if test="${pageBean.pageNow+2 > pageBean.pageCount}">
                            <c:set var="begin" value="${pageBean.pageCount-4}"/>
                            <c:set var="end" value="${pageBean.pageCount}"/>
                        </c:if>
                        <c:if test="${pageBean.pageNow+2 <= pageBean.pageCount}">
                            <c:set var="begin" value="${pageBean.pageNow-2}"/>
                            <c:set var="end" value="${pageBean.pageNow+2}"/>
                        </c:if>
                    </c:if>
                    <c:forEach begin="${begin}" step="1" end="${end}" varStatus="i">
                        <li class="pg_item"><a  href="javascript:jumpPage('${i.index}')">${i.index}</a></li>
                    </c:forEach>
                </c:if>
                <li id="nextPage"><a href="javascript:jumpPage(${pageBean.pageNow+1})">&raquo;</a></li>
                <li><a href="javascript:jumpPage(${pageBean.pageCount});"
                       style="border: 1px solid #ddd;">尾页
                </a></li>
                <li>
                    <a style="border: 0px;margin-left: 0px;">
                        当前页<span id="pageNow">${pageBean.pageNow}</span>
                        /<span id="pageCount">${pageBean.pageCount}</span>总页</a>
                </li>
                <li>

                    <div id="search" class="input-group" style="positon:relative;">
                        <input id="pg" type="text" class="form-control" placeholder="跳转指定页"/>
                        <span class="input-group-btn">
							            <button class="btn btn-info btn-search"
                                                onclick="jumpThePage()">GO
                                        </button>
									</span>
                    </div>
                </li>
                <li><a style="padding-top: 0px;border: 0px;">
                    <label>每页显示:</label>
                    <select id="pageSize" class="form-control"
                            style="width: 100px;display: inline;"
                            onchange="jumpPage(1)">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="4">4</option>
                        <option value="8">8</option>
                    </select>
                    <label>条</label>
                </a>
                </li>
            </ul>
        </div>
    </div>
</div>
<!--分页end-->

<%--分页相关数据设置--%>
<script>

    // 设置默认的页数
    $("#pageSize>option[value='${pageBean.pageSize}']").attr("selected", "selected");
    // 设置当前页数
    var items = document.getElementsByClassName("pg_item");
    for (var i = 0; i < items.length; i++) {
        if (items[i].innerText == "${pageBean.pageNow}"){
            items[i].className = "active";
        }
    }
    // 设置上一页和下一页是否可用·
    if($("#pageNow").html() == 1) {
        $("#prePage").addClass("disabled");
    }

    if ($("#pageNow").html() == ${pageBean.pageCount}) {
        $("#nextPage").addClass("disabled");
    }

    function jumpThePage(){
        // 获取page
        var pgv = $("#pg").val();
        if (parseInt(pgv)){
            if (pgv > 0 && pgv <= ${pageBean.pageCount}) {
                jumpPage(pgv);
            } else {
                alert("对不起，请输入指定的页数");
            }
        } else  {
            alert("对不起，请输入正确的数字");
        }
    }

</script>
