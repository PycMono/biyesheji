<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>组合查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	table {
		color: #404040;
		font-size: 25px;
		height: 100%;
	
		
	}
</style>
  </head>
  
  <body>
  <form action="<c:url value='/Book/findByCombination.do'/>" method="get">
  	<input type="hidden" name="method" value="findByCombination"/>
<table align="center">
	<tr>
		<td>书 名:</td>
		<td><input type="text" name="bookName"/></td>
	</tr>
	<tr>
		<td>作 者:</td>
		<td><input type="text" name="bookAuthor"/></td>
	</tr>
	<tr>
		<td>出版社:</td>
		<td><input type="text" name="bookPress"/></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>
			<input  type="submit" value="搜    索"/>
			<input type="reset" value="重     置"/>
		</td>
	</tr>
</table>
	</form>
  </body>
</html>
