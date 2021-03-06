<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/book/list.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/pager/pager.css'/>" />
    <script type="text/javascript" src="<c:url value='/jsps/pager/pager.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jsps/js/book/list.js'/>"></script>
  </head>
  
  <body>

<ul>
<c:forEach items="${pb.beanList }" var="book">
  <li>
  <div class="inner">
    <a class="pic" href="${pageContext.request.contextPath}/Book/findByBid.do?bookId=${book.bookId }"><img src="<c:url value='/${book.bookImageS }'/>" border="0"/></a>
    <p class="price">
		<span class="price_n">&yen;${book.bookCurrPrice }</span>
		<span class="price_r">&yen;${book.bookPrice }</span>
		(<span class="price_s">${book.bookDiscount }折</span>)
	</p>
	<p><a id="bookname" title="${book.bookName }" href="${pageContext.request.contextPath}/Book/findByBid.do?bookId=${book.bookId }">${book.bookName }</a></p>
	<%-- url标签会自动对参数进行url编码 --%>
	<c:url value="/Book/findByAuthor.do" var="authorUrl">
		<c:param name="bookAuthor" value="${book.bookAuthor }"/>
	</c:url>
	<c:url value="/Book/findByPress.do" var="pressUrl">
		<c:param name="bookPress" value="${book.bookPress }"/>
	</c:url>
	
	<p><a href="${authorUrl }" name='P_zz' title='${book.bookAuthor }'>${book.bookAuthor }</a></p>
	<p class="publishing">
		<span>出 版 社：</span><a href="${pressUrl }">${book.bookPress }</a>
	</p>
  </div>
  </li>
</c:forEach>







 




</ul>

<div style="float:left; width: 100%; text-align: center;">
	<hr/>
	<br/>
	<%@include file="/jsps/pager/pager.jsp" %>
</div>

  </body>
 
</html>

