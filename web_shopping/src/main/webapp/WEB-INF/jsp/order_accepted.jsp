<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fs.tic.web_shopping.business.util.PropertyLoader"%>
<%@ page import="com.fs.tic.web_shopping.business.model.CartItem"%>
<%@ page import="com.fs.tic.web_shopping.business.model.OrderResult"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.fs.tic.web_shopping.business.model.Cart"%>
<%@ page
	import="com.fs.tic.web_shopping.business.model.bean.OrderedDetailBean"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

	<!-- カート商品 -->
	<h3>【ご注文商品】</h3>
	<table>
		<thead>
			<tr>
				<th>商品コード</th>
				<th>商品名</th>
				<th>単価</th>
				<th>数量</th>
				<th>小計</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="entry" items="${orderResult.orderedCart.itemList}">
				<c:set var="item" value="${entry.value}" />
				<tr>
					<td>${item.itemCode}</td>
					<td>${item.itemName}</td>
					<td class="table_col_num"><fmt:formatNumber
							value="${item.itemPrice}" pattern="#,###" /> 円</td>
					<td class="table_col_num">${item.quantity}</td>
					<td class="table_col_num"><fmt:formatNumber
							value="${item.total}" pattern="#,###" /> 円</td>
				</tr>
			</c:forEach>
		</tbody>

		<tr class="table_col_footer">
			<td class="table_col_title">合計</td>
			<td class="table_col_num" colspan="4"><fmt:formatNumber
					value="${orderResult.orderedCart.total}" pattern="#,###" /> 円</td>

		</tr>

	</table>

	<!-- 注文情報 -->
	<p>
		お客様の注文番号は<span class="accepted_ordered_number">【
			${orderResult.orderedNumber} 】</span>です。
	</p>

	<!-- トップページ遷移 -->
	<p>
		<a
			href="<%=PropertyLoader.getProperty("url.servlet.ShoppingServlet")%>?action=top">トップページに戻る</a>
	</p>
</body>
</html>
