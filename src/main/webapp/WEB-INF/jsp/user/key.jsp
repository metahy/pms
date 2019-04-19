<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>秘钥管理</title>
    <meta name="description" content="Ela Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${ctx}/resource/admin/assets/css/normalize.css">
    <link rel="stylesheet" href="${ctx}/resource/admin/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/resource/admin/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/resource/admin/assets/css/style.css">
    <style>
    @page { margin: 0; }
    </style>
</head>

<body>
<!-- Left Panel -->
<aside id="left-panel" class="left-panel">
    <nav class="navbar navbar-expand-sm navbar-default">
        <div id="main-menu" class="main-menu collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li>
                    <a href="${ctx}/user/encode"><i class="menu-icon fa fa-lock"></i>加密</a>
                </li>
                <li>
                    <a href="${ctx}/user/decode"> <i class="menu-icon fa fa-unlock"></i>解密</a>
                </li>
                <c:if test="${sessionScope.role == 'admin'}">
                    <li>
                        <a href="${ctx}/user/arithmetic"> <i class="menu-icon fa fa-bar-chart-o"></i>算法管理</a>
                    </li>
                    <%--<li class="active">--%>
                        <%--<a href="${ctx}/user/key"> <i class="menu-icon fa fa-key"></i>秘钥管理</a>--%>
                    <%--</li>--%>
                </c:if>
                <li>
                    <a href="${ctx}/user/user"> <i class="menu-icon fa fa-user"></i>个人信息</a>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </nav>
</aside>
<!-- /#left-panel -->
<!-- Right Panel -->
<div id="right-panel" class="right-panel">
    <!-- Header-->
    <header id="header" class="header">
        <div class="top-left">
            <div class="navbar-header">
                <a class="navbar-brand" href="./"><img src="${ctx}/resource/admin/images/logo.png" alt="Logo"></a>
                <a class="navbar-brand hidden" href="./"><img src="${ctx}/resource/admin/images/logo2.png"
                                                              alt="Logo"></a>
                <a id="menuToggle" class="menutoggle"><i class="fa fa-bars"></i></a>
            </div>
        </div>
        <div class="top-right">
            <div class="header-menu">
                <div class="user-area dropdown float-right">
                    <a href="#" class="dropdown-toggle active" data-toggle="dropdown" aria-haspopup="true"
                       aria-expanded="false">
                        <img class="user-avatar rounded-circle" src="${ctx}/resource/admin/images/admin.jpg"
                             alt="User Avatar">
                    </a>
                    <div class="user-menu dropdown-menu">
                        <a class="nav-link" href="${ctx}/user/logout"><i class="fa fa-power -off"></i>Logout</a>
                    </div>
                </div>

            </div>
        </div>
    </header>
    <!-- /#header -->
    <!-- Content -->
    <div class="content">
        <div class="animated fadeIn">
            <div class="clearfix"></div>
            <div class="orders">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="card">
                            <form action="${ctx}/user/setSecretKey" method="post" enctype="multipart/form-data" class="form-horizontal">
                                <div class="card-header">
                                    <strong>秘钥管理</strong>
                                </div>
                                <div class="card-body card-block">
                                    <c:forEach items="${arithmetics}" var="arithmetic">
                                        <div class="row form-group">
                                            <div style="width: 100px; margin-left: 15px;">
                                                <label for="aes" class="form-control-label">算法</label>
                                            </div>
                                            <div>
                                                <input type="text" readonly id="aes" name="${arithmetic.name}" value="${arithmetic.name}" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div style="width: 100px; margin-left: 15px;">
                                                <label for="aesSecretKey" class="form-control-label">秘钥</label>
                                            </div>
                                            <div>
                                                <textarea name="${arithmetic.name}SecretKey" id="aesSecretKey" rows="3" placeholder="secretKey" class="form-control">${arithmetic.secretKey}</textarea>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="card-footer">
                                    <button type="submit" class="btn btn-primary btn-sm">
                                        <i class="fa fa-dot-circle-o"></i> 保存
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/resource/admin/assets/js/vendor/jquery-2.1.4.min.js"></script>
<script src="${ctx}/resource/admin/assets/js/popper.min.js"></script>
<script src="${ctx}/resource/admin/assets/js/plugins.js"></script>
<script src="${ctx}/resource/admin/assets/js/main.js"></script>
</body>
</html>
