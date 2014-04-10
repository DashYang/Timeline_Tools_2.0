<%@page import="DataBase.WorldNodeFactory"%>
<%@page import="storage.Eventnode"%>
<%@page import="DataBase.EventNodeFactory"%>
<%@page import="storage.Timenode"%>
<%@page import="DataBase.TimeNodeFactory"%>
<%@page import="DataBase.Isqltool"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.UnsupportedEncodingException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
<title>时间线</title>
<link href="../Resource/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<script type="text/javascript" src="../Resource/jquery-1.10.2.js"></script>
<script type="text/javascript" src="../Resource/time.js"></script>
<script type="text/javascript" src="../Resource/js/bootstrap.min.js"></script>
</head>
<body>
	<%
		boolean editable = false;
		if (session.getAttribute("user") != null
				&& session.getAttribute("user") != "") {
			String user = session.getAttribute("user").toString();
			if (user.equals("admin")) {
				editable = true;
			}
		}
		String worldname = "未知世界";
		int id = 0;
		ArrayList<Object> list = new ArrayList<Object>();
		if (request.getParameter("id") != null
				&& request.getParameter("id") != "") {
			id = Integer.parseInt(request.getParameter("id"));
			Isqltool tool = new TimeNodeFactory();
			list = tool.show(id);
			worldname = new WorldNodeFactory().getWorldnodeByID(id).getName();
		}
	%>
	<%@ include file="masterpage.jsp"%>
	<div class="container">
		<div class="jumbotron">
			<h2>
				<%
					out.print(worldname);
				%>
			</h2>
			<input id='worldid' type='hidden' value='<%out.print(id);%>'>
		</div>
		<div class="panel-group" id="accordion">
			<%
				int fathernodeid = 0;
				for (Object item : list) {
					Timenode node = (Timenode) item;
					out.print("<div class='panel panel-default'><div class='panel-heading'><h4 class='panel-title'>");
					out.print("<a id='"
							+ node.getId()
							+ "' data-toggle='collapse' data-toggle='collapse' data-parent='#accordion' href='#eventnode"
							+ node.getId() + "'>" + node.getTime_description()
							+ "</a>");
					out.print("</h4></div>");
					out.print("<div id='eventnode" + node.getId()
							+ "' class='panel-collapse collapse collapse'>");
					out.print("<div id='e_description" + node.getId()
							+ "' class='panel-body'>");
					ArrayList<Object> eventlist = new EventNodeFactory().show(node
							.getId());
					int eventid = 0;
					for (Object event_item : eventlist) {
						Eventnode eventnode = (Eventnode) event_item;
						out.print(eventnode.getEvent_description());
						eventid = eventnode.getId();
					}
					out.print("</div>");
					if (editable == true) {
						out.print("<button type='button' class='btn btn-success' style='margin: 5px;' id = 'addEventnode."
								+ node.getId() + "'>编辑本条目</button>");
					}
					out.print("</div>");
					if (editable == true) {
						out.print("<div class='newitem' style='padding: 5px;'>");
						out.print("<button type='button' class='btn btn-primary' id = 'add."
								+ node.getId() + "'>" + "添加时间节点</button>");
						if (!node.getTime_description().equals("世界诞生")) {
							out.print("<button type='button' class='btn btn-danger' id = 'del."
									+ node.getId()
									+ "."
									+ fathernodeid
									+ "'>"
									+ "删除时间节点</button>");
							out.print("<button type='button' class='btn btn-info' id = 'edt."
									+ node.getId() + "'>编辑时间节点</button>");
						}
						out.print("</div></div>");
						fathernodeid = node.getId();
					}
				}
			%>
		</div>

		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">提示</h4>
					</div>
					<div class="modal-body">确认要删除该时间节点么？</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-warning">删除</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

	</div>
</body>
</html>
