<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>密码管理系统</title>
	<link rel="stylesheet" href="${ctx}/resource/login/auth.css">
</head>

<body>
	<div class="lowin">
		<div class="lowin-brand">
			<img src="${ctx}/resource/login/kodinger.jpg" alt="logo">
		</div>
		<div class="lowin-wrapper">
			<div class="lowin-box lowin-login">
				<div class="lowin-box-inner">
					<form action="${ctx}/user/login" method="post">
						<p>登录</p>
						<div class="lowin-group">
							<label>用户名</label>
							<input type="text" name="userName" autocomplete="userName" class="lowin-input">
						</div>
						<div class="lowin-group password-group">
							<label>登录密码</label>
							<input type="password" name="password" autocomplete="password" class="lowin-input">
						</div>
						<button class="lowin-btn login-btn">
							登录
						</button>
						<div class="text-foot">
							还没有账户? <a href="" class="register-link">注册</a>
						</div>
					</form>
				</div>
			</div>
			<div class="lowin-box lowin-register">
				<div class="lowin-box-inner">
					<form action="${ctx}/user/register" method="post">
						<p>注册</p>
						<div class="lowin-group">
							<label>用户名</label>
							<input type="text" name="userName" autocomplete="realName" class="lowin-input">
						</div>
						<div class="lowin-group">
							<label>姓名</label>
							<input type="text" name="realName" autocomplete="realName" class="lowin-input">
						</div>
						<div class="lowin-group">
							<label>登录密码</label>
							<input type="password" name="password" autocomplete="current-password" class="lowin-input">
						</div>
						<button type="submit" class="lowin-btn">
							注册
						</button>
						<div class="text-foot">
							已经拥有账户? <a href="" class="login-link">登录</a>
						</div>
					</form>
				</div>
			</div>
		</div>
    </div>

	<script src="${ctx}/resource/login/auth.js"></script>
	<script>
		Auth.init({
			login_url: '${ctx}/user/login',
			forgot_url: '#forgot'
		});
	</script>
</body>
</html>