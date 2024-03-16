<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fs.tic.web_shopping.business.util.PropertyLoader"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sample Shopping - お客様情報入力</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>


	<!-- メニュー表示 -->
	<jsp:include page="menu.jsp" />

	<!-- メッセージ -->
	<h2>お客様情報入力</h2>

	<!-- カート商品 -->
	<h3>【カートの中身】</h3>
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
				</tr>
			</c:forEach>
			<tr class="table_col_footer">
				<td class="table_col_title">合計</td>
				<td class="table_col_num" colspan="4"><fmt:formatNumber
						value="${cart.total}" pattern="#,###" /> 円</td>
			</tr>
		</table>


		<!-- お客様情報入力 -->
		<h3>【お客様情報を入力してください】</h3>

		<form
			action="<%=PropertyLoader.getProperty("url.servlet.ShoppingServlet")%>?action=confirm_order"
			method="post">
			<table>
				<tr>
					<th>お名前</th>
					<td><input type="text" name="name"
						value="${empty errors['name'] ? cart.customer.customerName : ''}" />
						<c:if test="${not empty errors['name']}"> <span class="error-message">${errors['name']}</span></c:if>
					</td>
				</tr>
				<tr>
					<th>住所</th>
					<td><input type="text" name="address"
						value="${empty errors['address'] ? cart.customer.address : ''}" />
						<c:if test="${not empty errors['address']}"> <span class="error-message">${errors['address']}</span></c:if>
					</td>
				</tr>
				<tr>
					<th>電話番号</th>
					<td><input type="text" name="tel"
						value="${empty errors['tel'] ? cart.customer.tel : ''}" /> <c:if
							test="${not empty errors['tel']}"> <span class="error-message">${errors['tel']}</span></c:if></td>
				</tr>
				<tr>
					<th>e-mail</th>
					<td><input type="text" name="email"
						value="${empty errors['email'] ? cart.customer.email : ''}" /> <c:if
							test="${not empty errors['email']}"> <span class="error-message">${errors['email']}</span></c:if></td>
				</tr>
			</table>
			<input type="submit" value="注文画面へ">
		</form>
	</c:if>
</body>
</html>
