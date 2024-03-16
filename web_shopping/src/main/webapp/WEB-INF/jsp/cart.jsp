<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fs.tic.web_shopping.business.util.PropertyLoader"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sample Shopping - カート</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<!-- メニュー表示 -->
	<jsp:include page="menu.jsp" />

	<!-- カート商品 -->
	<h2>現在のカートの中身</h2>
	<c:if test="${empty cart.itemListForReference}">
		カートは空です。
	</c:if>
	<c:if test="${not empty cart.itemListForReference}">
		<table>
			<tr>
				<th>商品コード</th>
				<th>商品名</th>
				<th>単価</th>
				<th>個数</th>
				<th>明細金額</th>
				<th>削除</th>
			</tr>
			<c:forEach var="item" items="${cart.itemListForReference}">
				<tr>
					<td class="table_col_code">${item.value.itemCode}</td>
					<td class="table_col_name">${item.value.itemName}</td>
					<td class="table_col_num"><fmt:formatNumber
							value="${item.value.itemPrice}" pattern="#,###" /> 円</td>
					<td class="table_col_num">${item.value.quantity}</td>
					<td class="table_col_num"><fmt:formatNumber
							value="${item.value.total}" pattern="#,###" /> 円</td>
					<td class="table_col_button">
						<form
							action="<%=PropertyLoader.getProperty("url.servlet.ShoppingServlet")%>?action=delete_cart_item"
							method="post">
							<input type="hidden" name="item_code"
								value="${item.value.itemCode}"> <input type="submit"
								value="削除">
						</form>
					</td>
				</tr>
			</c:forEach>
			<tr class="table_col_footer">
				<td class="table_col_title">合計</td>
				<td class="table_col_num" colspan="4"><fmt:formatNumber
						value="${cart.total}" pattern="#,###" /> 円</td>
				<td></td>
			</tr>
		</table>
		<form
			action="<%=PropertyLoader.getProperty("url.servlet.ShoppingServlet")%>?action=request_customer_info"
			method="post">
			<input type="submit" value="注文する">
		</form>
	</c:if>
</body>
</html>
