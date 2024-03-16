<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fs.tic.web_shopping.business.util.PropertyLoader"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sample Shopping - 商品一覧ページ</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<!-- メニュー表示 -->
	<jsp:include page="menu.jsp" />

	<!-- 商品一覧 -->
	<h2>商品一覧</h2>
	<c:forEach var="item" items="${items}">
		<form
			action="<%=PropertyLoader.getProperty("url.servlet.ShoppingServlet")%>?action=add_cart_item"
			method="post">

			<p id="images">
				<img src="/web_shopping/images/${item.imagePath}.png" >
			</p>
			商品コード：${item.itemCode}<br> 商品名：${item.itemName}<br> 単価：
			<fmt:formatNumber value="${item.itemPrice}" pattern="#,###" />
			円<br> 個数： <select name="quantity">
				<c:forEach begin="1" end="5" step="1" var="num">
					<option value="${num}">${num}</option>
				</c:forEach>
			</select> 個<br> <input type="hidden" name="item_code"
				value="${item.itemCode}"> <input type="submit"
				value="カートに追加">
		</form>
		<br>
	</c:forEach>
</body>
</html>
