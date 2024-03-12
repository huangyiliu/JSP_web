<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fs.tic.web_shopping.business.util.PropertyLoader"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sample Shopping - 注文確認</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<!-- メニュー表示 -->
	<jsp:include page="menu.jsp" />
	
	<!-- メッセージ -->
	<h2>下記の内容で注文を行いますか？</h2>
	
	<!-- 注文内容 -->
	<c:if test="${not empty cart.itemListForReference}">
		<!-- カート商品 -->
		<h3>【ご注文商品】</h3>
		<table>
			<tr>
				<th>商品コード</th>
				<th>商品名</th>
				<th>単価</th>
				<th>個数</th>
				<th>明細金額</th>
			</tr>
			<c:forEach var="item" items="${cart.itemListForReference}">
				<tr>
					<td class="table_col_code">${item.value.itemCode}</td>
					<td class="table_col_name">${item.value.itemName}</td>
					<td class="table_col_num">${item.value.itemPrice} 円</td>
					<td class="table_col_num">${item.value.quantity}</td>
					<td class="table_col_num">${item.value.total} 円</td>
				</tr>
			</c:forEach>
			<tr class="table_col_footer">
				<td class="table_col_title">合計</td>
				<td class="table_col_num" colspan="4">${cart.total} 円</td>
			</tr>
		</table>
		
		<!-- お客様情報 -->
		<h3>【お客様情報】</h3>
		<form action="<%=PropertyLoader.getProperty("url.servlet.ShoppingServlet")%>?action=request_order" method="post">
			<table>
				<tr>
					<th>お名前</th><td>${cart.customer.customerName}</td>
				</tr>
				<tr>
					<th>住所</th><td>${cart.customer.address}</td>
				</tr>
				<tr>
					<th>電話番号</th><td>${cart.customer.tel}</td>
				</tr>
				<tr>
					<th>e-mail</th><td>${cart.customer.email}</td>
				</tr>
			</table>
			<input type="submit" value="この内容で注文">
		</form>
	</c:if>
</body>
</html>
