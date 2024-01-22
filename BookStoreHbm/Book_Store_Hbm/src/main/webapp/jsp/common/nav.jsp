<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--导航部分 begin-->

<div class="container" style="margin-top: 5px;">
    <nav class="navbar navbar-default well-sm" style="padding-left: 0px;" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#example-navbar-collapse">
                    <span class="sr-only">切换导航</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">在线书城</a>
            </div>
            <div class="collapse navbar-collapse" id="example-navbar-collapse">
                <ul class="nav navbar-nav pull-left">
                    <li class="active">
                        <a href="${path}/book/index">首页</a>
                    </li>

                    <li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            图书类型
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="${path}/book/list?category=0">精选图书</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="${path}/book/list?category=1">推荐图书</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="${path}/book/list?category=2">特价图书</a>
                            </li>
                            <li class="divider"></li>
                        </ul>
                    </li>
                    <c:if test="${user == null}">
                        <li>
                            <a href="#" data-toggle="modal" data-target="#login">登录</a>
                        </li>
                    </c:if>
                    <c:if test="${user != null}">
                        <li>
                            <a href="#">${user.userName}</a>
                        </li>
                    </c:if>
                    <c:if test="${user != null}">
                        <li>
                            <a href="${path}/user/exit">安全退出</a>
                        </li>
                    </c:if>
                    <li>
                        <a href="#" data-toggle="modal" data-target="#register">注册</a>
                    </li>

                    <c:if test="${user != null}">
                        <li>
                            <a href="${path}/permission/orderList">我的订单</a>
                        </li>

                        <li>
                            <a href="${path}/permission/mycar">
                                <span class="glyphicon glyphicon-shopping-cart"> </span>我的购物车</a>
                        </li>
                    </c:if>

                </ul>
                <div class="input-group col-md-3 pull-right" style="positon:relative;padding: 7px;">
                    <input type="text" class="form-control" placeholder="请输入图书名" id="book_name"/>
                    <span class="input-group-btn">
					            <button class="btn btn-info btn-search" onclick="searchBook()">
					            	<span class="glyphicon glyphicon-search"></span>
					            </button>
							</span>
                </div>
            </div>
        </div>
    </nav>
</div>
<!--导航部分 end-->


<!--最顶端轮播图片 begin-->
<div id="gcarouse" class="container">
    <!--轮播-->
    <div id="myCarousel" class="carousel slide">
        <!-- 轮播（Carousel）指标 -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <!-- 轮播（Carousel）项目 -->
        <div class="carousel-inner">
            <div class="item active">
                <a href="details.jsp"><img src="${path}/images/advert1.jpg" class="pull-left"
                                           alt="First slide"></a>

            </div>
            <div class="item">
                <a href="details.jsp"><img src="${path}/images/advert2.jpg" class="pull-left"
                                           alt="First slide"></a>

            </div>
            <div class="item">
                <a href="details.jsp"><img src="${path}/images/advert3.jpg" class="pull-left"
                                           alt="First slide"></a>

            </div>
        </div>

        <!-- 轮播（Carousel）导航 -->
        <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
        <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
    </div>
</div>
<!--最顶端轮播图片 end-->


<!-- 登录模态框（Modal） -->
<div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    用户登录
                </h4>
            </div>
            <div class="modal-body">
                <!--登录的form表单-->
                <form class="form-horizontal" role="form">
                    <div class="form-group has-feedback">
                        <label for="login_username" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-5">

                            <input type="text" class="form-control" id="login_username"
                                   placeholder="请输入用户名" value="${cookie.username.value}">
                            <span class="glyphicon glyphicon-user form-control-feedback"></span>
                        </div>
                        <div id="no_user" class="col-sm-3" style="padding-top: 10px; display: none">
                            <label class="alert-danger">用户名不存在</label>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <label for="login_password" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="login_password"
                                   placeholder="请输入密码" value="${cookie.password.value}">
                            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                        </div>
                        <div class="col-sm-3" style="padding-top: 10px;">
                            <label id="wrong_pwd" class="alert-danger"
                                   style="display: none">密码不正确</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox">
                                <label>
                                    <input id="remember" name="remember" type="checkbox">请记住我
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <button type="button" class="btn btn-primary" onclick="login()">登陆</button>
                    </div>
                </form>
                <!--form结束-->
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
</div>
<!-- 登录modal end/.modal -->


<%--注册模态框start--%>
<div class="modal fade" id="register" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    用户注册
                </h4>
            </div>
            <div class="modal-body">
                <!--登录的form表单-->
                <form id="reg" class="form-horizontal" role="form">
                    <div class="form-group has-feedback">
                        <label for="reg_username" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="reg_username"
                                   placeholder="小写字母开头,不含中文." onblur="authuser()">
                            <span class="glyphicon glyphicon-user form-control-feedback"></span>
                        </div>
                        <div class=" prompt col-sm-3" style="padding-top: 10px; display: none">
                            <label class="alert-danger">用户名不合法</label>
                        </div>
                        <div class=" prompt col-sm-3" style="padding-top: 10px; display: none">
                            <label class="alert-danger">用户名已存在</label>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <label for="lastname" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-5">
                            <input id="zc_uname" type="password" class="form-control" id="lastname"
                                   placeholder="密码长度6-8位" minlength="6" maxlength="8"
                                   onblur="proPassword()">
                            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                        </div>
                        <div class=" prompt col-sm-3" style="padding-top: 10px; display: none">
                            <label class="alert-danger">密码不合法</label>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <label class="col-sm-2 control-label">确认密码</label>
                        <div class="col-sm-5">
                            <input type="password" class="form-control" id="lastname2" minlength="6"
                                   maxlength="8" placeholder="和密码保持一致" onblur="proSecPassword()">
                            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                        </div>
                        <div class=" prompt col-sm-3" style="padding-top: 10px; display: none">
                            <label class="alert-danger">两次密码不一致</label>
                        </div>
                    </div>

                    <div class="form-group has-feedback">
                        <label class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-5">
                            <input type="email" class="form-control"
                                   placeholder="合法邮箱格式" onblur="proEmail()">
                            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                        </div>
                        <div class="prompt col-sm-3" style="padding-top: 10px; display: none">
                            <label class="alert-danger">邮箱格式不正确</label>
                        </div>
                    </div>

                    <div class="form-group has-feedback">
                        <label class="col-sm-2 control-label">电话</label>
                        <div class="col-sm-5">
                            <input type="tel" class="form-control" placeholder="合法手机格式"
                                   onblur="proTel()">
                            <span class="glyphicon glyphicon-earphone form-control-feedback"></span>
                        </div>
                        <div class="prompt col-sm-3" style="padding-top: 10px; display: none">
                            <label class="alert-danger">格式不正确</label>
                        </div>
                    </div>

                    <div class="form-group has-feedback">
                        <label class="col-sm-2 control-label">公司</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control"
                                   placeholder="请输入公司地址">
                            <span class="glyphicon glyphicon-home form-control-feedback"></span>
                        </div>
                    </div>

                    <div class="form-group has-feedback">
                        <label class="col-sm-2 control-label">地址</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="email" placeholder="请输入收货">
                            <span class="glyphicon glyphicon-home form-control-feedback"></span>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <input id="reg_bt" type="button" class="btn btn-primary"
                               value="注册" onclick="register()">
                    </div>
                </form>
                <!--form结束-->
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
</div>
<%--注册模态框end--%>


<script src="${path}/dist/js/jquery.min.js"></script>
<script>
    // 登陆
    function login() {
//        alert($("#login_username").val());
//        alert($("#login_password").val());
        // 使用ajax对登陆进行验证

        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
//                alert(xmlhttp.responseText);
                // 根据返回的状态码 对界面进行控制
                if (xmlhttp.responseText == 0) {   // 用户名不存在
                    $("#no_user").show();
                    $("#wrong_pwd").hide();
                } else if (xmlhttp.responseText == 1) {  // 密码错误
                    $("#wrong_pwd").show();
                    $("#no_user").hide();
                } else if (xmlhttp.responseText == 2) {
                    $("#wrong_pwd").hide();
                    $("#no_user").hide();
                    // 此时用户名和密码正确,进行重定向
                    window.location.href = "${path}/book/index";
                }
            }
        };
        xmlhttp.open("POST", "${path}/user/doLogin", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//        alert($("#remember").get(0).checked);
        xmlhttp.send("username=" + $("#login_username").val() +
            "&password=" + $("#login_password").val() +
            "&remember=" + $("#remember").get(0).checked);
    }

    var flag = true;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var s = xmlhttp.responseText;
            if (s == "0") { // 用户名已存在
                $("#reg>div:eq(0) .prompt:eq(1)").show();
                flag = false;
            } else if (s == "1") {
                $("#reg>div:eq(0) .prompt:eq(1)").hide();
                flag = true;
            }
        }
    };

    // 注册验证，检测用户名是否合法
    function authuser() {
        if ($("#reg>div:eq(0) input").val().match("[a-z][A-Za-z0-9]{2,15}")) {
            $("#reg>div:eq(0) .prompt:first").hide();
            // 如果输入的用户名合法，则判断用户名是否存在
            xmlhttp.open("get", "${path}/user/checkName?username=" + $("#reg>div:eq(0) input").val());
            xmlhttp.send();
            return flag;
        } else {
            $("#reg>div:eq(0) .prompt:first").show();
            $("#reg>div:eq(0) .prompt:eq(1)").hide();
            return false;
        }
    }

    // 注册验证，检测密码是否合法
    function proPassword() {
        if ($("#reg>div:eq(1) input").val().match("[a-zA-Z0-9]{6,8}")) {
            $("#reg>div:eq(1) .prompt:first").hide();
            return true;
        } else {
            $("#reg>div:eq(1) .prompt:first").show();
            return false;
        }
    }

    // 注册验证，二次密码验证
    function proSecPassword() {
        if ($("#reg>div:eq(2) input").val() != $("#reg>div:eq(1) input").val()) {
            $("#reg>div:eq(2) .prompt:first").show();
            return false;
        } else {
            $("#reg>div:eq(2) .prompt:first").hide();
            return true;
        }
    }

    // 注册验证，邮箱验证
    function proEmail() {
        if ($("#reg>div:eq(3) input").val()
                .match("[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+")) {
            $("#reg>div:eq(3) .prompt:first").hide();
            return true;
        } else {
            $("#reg>div:eq(3) .prompt:first").show();
            return false;
        }
    }

    // 注册验证，手机号验证
    function proTel() {
        if ($("#reg>div:eq(4) input").val().match("1(3|4|5|7|8)[0-9]{9}")) {
            $("#reg>div:eq(4) .prompt:first").hide();
            return true;
        } else {
            $("#reg>div:eq(4) .prompt:first").show();
            return false;
        }
    }


    // 点击注册， 再对所有的输入框进行验证
    function register() {
        if (authuser() && proPassword() && proSecPassword() && proEmail() && proTel()) {
            var json = {
                userName: $("#reg>div:eq(0) input").val(),
                password: $("#reg>div:eq(1) input").val(),
                email: $("#reg>div:eq(3) input").val(),
                phone: $("#reg>div:eq(4) input").val(),
                company: $("#reg>div:eq(5) input").val(),
                address: $("#reg>div:eq(6) input").val()
            };
//            alert(JSON.stringify(json));
            var xh = new XMLHttpRequest();
            xh.onreadystatechange = function () {
                if (xh.readyState == 4 && xh.status == 200) {
                    if (xh.responseText == 0) {
                        alert("注册失败");
                    } else if (xh.responseText == 1) {
                        alert("注册成功,请登录");
                        window.location.href = "${path}/book/index";
                    }
                }
            };
            // 开启一个ajax请求，进行注册
            xh.open("POST", "${path}/user/reg", true);
            xh.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xh.send("user=" + JSON.stringify(json));

        } else {
            alert("验证失败");
        }
    }

    // 根据图书名进行查找
    function searchBook() {
        var keywords = $("#book_name").val();
        if (keywords==null || keywords.trim().length<=0) {
            alert("请输入正确的书名");
            return;
        }
        window.location.href = "${path}/book/page?bookName=" + keywords;
    }
</script>