<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fs.tic.web_shopping.business.util.PropertyLoader"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- システム時刻表示 -->
<jsp:useBean id="now" class="java.util.Date" />
<div class="current_time">現在は【 <fmt:formatDate value="${now}" pattern="yyyy年MM月dd日 (EE) HH:mm:ss.SSS" /> 】です。</div>
<hr>

<!-- ようこそ -->
｜
<c:choose>
	<c:when test="${menu_status_top}">
		ようこそ
	</c:when>
	<c:otherwise>
		<a href="<%=PropertyLoader.getProperty("url.servlet.ShoppingServlet")%>?action=top">ようこそ</a>
	</c:otherwise>
</c:choose>

<!-- 商品カテゴリ一覧 -->
<c:forEach var="category" items="${categories}">
	｜
	<c:choose>
		<c:when test="${menu_current_category eq category.categoryCode}">
			${category.categoryName}
		</c:when>
		<c:otherwise>
			<a href="<%=PropertyLoader.getProperty("url.servlet.ShoppingServlet")%>?action=select_category&code=${category.categoryCode}">${category.categoryName}</a>
		</c:otherwise>
	</c:choose>
</c:forEach>

<!-- カート表示 -->
｜
<c:choose>
	<c:when test="${menu_status_cart}">
		カートを見る
	</c:when>
	<c:otherwise>
		<a href="<%=PropertyLoader.getProperty("url.servlet.ShoppingServlet")%>?action=show_cart">カートを見る</a>
	</c:otherwise>
</c:choose>
｜
<hr>
