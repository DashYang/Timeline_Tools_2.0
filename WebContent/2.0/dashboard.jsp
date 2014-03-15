<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="storage.Worldnode"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DataBase.WorldNodeFactory"%>
<%@page import="DataBase.Isqltool"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>仪表盘</title>
<link href="../Resource/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
</head>
<body>
	<%@ include file="masterpage.jsp" %>
	<div class="container">
		<div class="bs-example">
			<form method='post' role='form' action='../ImportantAffairHandler' class='well' role='form'>
				<input name='type' type='hidden' value='addworldnode'>
				<div class='form-group'>
					<label for='exampleInputEmail1'>世界的名称</label>
						<input name='worldname' class='form-control' maxlength='20' placeholder='Enter Description'>
				</div>
				<button type='submit' class='btn btn-default'>Submit</button>
			</form>
		</div>
		<div class="bs-example">
			<table class="table table table-bordered">
				<thead>
					<tr>
						<th>#</th>
						<th>世界名称</th>
						<th>操作</th>
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
						out.print("<td><a href='time.jsp?id=" + tmp.getId() + "&" +
						"name="+tmp.getName()+ "'>"+ tmp.getName()+ "</td>");
						
						out.print("<td><div class='option-list' >");
							out.print("<button type='button' class='btn btn-danger btn-xs' id = 'del." + tmp.getId() + "'>"
									+ "删除</button>");
							out.print("<button type='button' class='btn btn-info btn-xs' id = 'edt." + tmp.getId() + "'>编辑</button>");
						out.print("</div></td>");
						out.print("<td>"+ tmp.getCreattime()+ "</td>");
					}
				%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>