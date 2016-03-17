<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>订单详细</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/jsps/css/order/desc.css'/>">
</head>

<body>
	<div class="divOrder">
		<span>订单号：${order.orderId } <c:choose>
				<c:when test="${order.orderStatus eq 1 }">(等待付款)</c:when>
				<c:when test="${order.orderStatus eq 2 }">(准备发货)</c:when>
				<c:when test="${order.orderStatus eq 3 }">(等待确认)</c:when>
				<c:when test="${order.orderStatus eq 4 }">(交易成功)</c:when>
				<c:when test="${order.orderStatus eq 5 }">(已取消)</c:when>
			</c:choose> 下单时间：${order.orderTime }
		</span>
	</div>
	<div class="divContent">
		<div class="div2">
			<dl>
				<dt>收货人信息</dt>
				<dd>${order.orderAddress }</dd>
			</dl>
		</div>
		<div class="div2">
			<dl>
				<dt>商品清单</dt>
				<dd>
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th class="tt">商品名称</th>
							<th class="tt" align="left">单价</th>
							<th class="tt" align="left">数量</th>
							<th class="tt" align="left">小计</th>
						</tr>


						<c:forEach items="${order.orderItemList }" var="item">
							<tr style="padding-top: 20px; padding-bottom: 20px;">
								<td class="td" width="400px">
									<div class="bookname">
										<img align="middle" width="70"
											src="<c:url value='/${item.orderitemImage  }'/>" /> <a
											href="<c:url value='/BookServlet?method=load&bid=${item.TBook.bookId }'/>">${item.orderitemName }</a>
									</div>
								</td>
								<td class="td"><span>&yen;${item.orderitemPrice}</span></td>
								<td class="td"><span>${item.orderitemCount }</span></td>
								<td class="td"><span>&yen;${item.orderitemSubtotal }</span>
								</td>
							</tr>
						</c:forEach>


					</table>
				</dd>
			</dl>
		</div>
		<div style="margin: 10px 10px 10px 550px;">
			<span style="font-weight: 900; font-size: 15px;">合计金额：</span> <span
				class="price_t">&yen;${order.orderTotal }</span><br />
			<c:if test="${order.orderStatus eq 1 }">
				<a
					href="${pageContext.request.contextPath}/Order/paymentPre.do?orderId=${order.orderId }"
					class="pay"></a>
				<br />
			</c:if>
			<c:if test="${order.orderStatus eq 1 and btn eq 'cancel'}">
				<a id="cancel"
					href="${pageContext.request.contextPath}/Order/cancel.do?orderId=${order.orderId }">取消订单</a>
				<br />
			</c:if>
			<c:if test="${order.orderStatus eq 3 and btn eq 'confirm'}">
				<a id="confirm"
					href="${pageContext.request.contextPath}/Order/confirm.do?orderId=${order.orderId }">确认收货</a>
				<br />
			</c:if>
		</div>
	</div>
</body>
</html>

