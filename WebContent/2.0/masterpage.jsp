<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
		String user = "游客";
		boolean logout = false;
		if(session.getAttribute("user") != null && !session.getAttribute("user").equals("")){
			user = session.getAttribute("user").toString();
			logout = true;
		}
	%>
<nav class="navbar navbar-default" role="navigation">
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp">欢迎来到时间大师的世界！</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="index.jsp">首页</a></li>
					<li><a href="imagewaterfall.jsp">兄弟眼</a></li>
					<li><a href="#about">关于</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<%
						if(logout == true){
							out.print("<li><a href='../VisitHandler'>欢迎用户  "+ user +"</a></li>");
						}
					%>
					<%
						if(logout == false){
							out.print("<li><a href='login.jsp'>Sign in</a></li>");
						}
					%>
					<%
						if(logout == true){
							out.print("<li><a href='../RequestHandler?type=logout'>log out</a></li>");
						}
					%>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</nav>