<%@page import="storage.Worldnode"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DataBase.WorldNodeFactory"%>
<%@page import="DataBase.Isqltool"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
<title>时间大师</title>
<link href="../Resource/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
</head>
<body>
	<%@ include file="masterpage.jsp" %>
	<div class="container">
		<div class="bs-example">
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>世界名称</th>
						<th>创建者</th>
						<th>创建时间</th>
					</tr>
				</thead>
				<tbody>
				<%
					Isqltool tool = new WorldNodeFactory();
					ArrayList<Object> e_list = tool.show(1);
					for(Object en:e_list)
					{
						Worldnode tmp = (Worldnode)en;
						out.print("<tr>");
						out.print("<td>"+ tmp.getId()+ "</td>");
						out.print("<td><a href='time.jsp?id=" + tmp.getId() + "'>"+ tmp.getName()+ "</td>");
						out.print("<td>"+ tmp.getCreator()+ "</td>");
						out.print("<td>"+ tmp.getCreattime()+ "</td>");
					}
				%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
