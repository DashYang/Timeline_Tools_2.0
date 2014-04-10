<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
<title>登录</title>
<link href="../Resource/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
</head>
<body>
	<script>
		function changeimg() {
			var myimg = document.getElementById("code");
			now = new Date();
			myimg.src = "./image.jsp?code=" + now.getTime();
		}
	</script>
	<%@ include file="masterpage.jsp"%>
	<div class="container">
		<form action="../RequestHandler" class="well" role="form"
			style="width: 520px; margin: 0px auto;">
			<h2>Welcome</h2>
			<input name='type' type='hidden' value="login"> 
			<input name='username' type="text" class="form-control" placeholder="Username" maxlength='12'> 
			<input name='password' type="password" class="form-control" placeholder="Password" maxlength='12'>
			<div style="padding-left: 12px;padding-right: 12px;padding-top: 6px;padding-bottom: 6px;">
			验证码<input type="text" name="verifycode" size="8" maxlength='4'>
				<img border=0 src="./image.jsp" id="code"><a
					href="javascript:changeimg()">看不清，换一张 </a>
			</div>
			<button class="btn btn-lg btn-primary btn-block"
				style="margin-top: 5px;" type="submit">Sign in</button>
		</form>
	</div>
</body>
</html>
