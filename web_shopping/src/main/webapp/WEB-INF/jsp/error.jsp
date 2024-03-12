<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fs.tic.web_shopping.business.util.PropertyLoader"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sample Shopping - エラーページ</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<!-- メニュー表示 -->
	<jsp:include page="menu.jsp" />
	
	<!-- エラーメッセージ -->
	<h2>エラーが発生しました。</h2>
	<p>${errorMessage}</p>
	<p><%=PropertyLoader.getProperty("message.cause.out_of_session")%></p>
</body>
</html>
