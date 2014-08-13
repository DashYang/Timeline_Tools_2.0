<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="Tools.Downloadtool"%>
<%@ page import="java.io.InputStream"%>

<%
	
response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	
	
	String url = request.getParameter("url");
	InputStream is = Downloadtool.downloadImage(url);
	ServletOutputStream sout = response.getOutputStream();

	byte b[] = new byte[1024];
	int len;
	while ((len = is.read(b)) != -1) {
		sout.write(b , 0 , len);
	}
	is.close();
	sout.flush();
	sout.close();
%>