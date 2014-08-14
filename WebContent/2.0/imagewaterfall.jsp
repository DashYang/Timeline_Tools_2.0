<%@page import="DataBase.Isqltool"%>
<%@page import="DataBase.ImageFactory"%>
<%@page import="java.util.ArrayList"%>
<%@page import="storage.Imagenode"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
<title>兄弟眼</title>
<link href="../Resource/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="../Resource/waterfall.css" rel="stylesheet" media="screen">
</head>
<body>
	<%@ include file="masterpage.jsp"%>
	<div class="container">
		<div class="jumbotron">
			<h2>
						<center>BrotherEye is watching you！</center>		
			</h2>
		</div>
		<div id="masonry" class="container-fluid">
			<%
				Isqltool tool = new ImageFactory();
				ArrayList<Object> result = tool.show(0);
				String template = "<div class='box'><a href='%s'><div class='bg-primary'>%s</div></a></div>";
				for (Object o : result) {
					if (o instanceof Imagenode) {
						Imagenode in = (Imagenode) o;
						String imagediv = String.format(template , in.getUrl() , in.getContent() ,
								in.getContent() + "..." );
						out.println(imagediv);
					}
				}
			%>
		</div>
	</div>
</body>
<script type="text/javascript" src="../Resource/jquery-1.10.2.js"></script>
<script type="text/javascript" src="../Resource/jquery.masonry.min.js"></script>
<script type="text/javascript" src="../Resource/waterfall.js"></script>
</html>