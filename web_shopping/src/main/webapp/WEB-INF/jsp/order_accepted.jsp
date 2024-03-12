<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fs.tic.web_shopping.business.util.PropertyLoader"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sample Shopping - 注文完了</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<!-- メニュー表示 -->
	<jsp:include page="menu.jsp" />
	
	<!-- メッセージ -->
	<h2>ご注文ありがとうございました！</h2>
	
	<!-- 注文情報 -->
	<p>お客様の注文番号は<span class="accepted_ordered_number">【 ${orderResult.orderedNumber} 】</span>です。</p>
	
	<!-- トップページ遷移 -->
	<p><a href="<%=PropertyLoader.getProperty("url.servlet.ShoppingServlet")%>?action=top">トップページに戻る</a></p>
</body>
</html>
