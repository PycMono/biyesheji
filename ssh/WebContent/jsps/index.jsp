<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>保健品   海淘团购_海淘网</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/ssh/jsps/css1/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="/ssh/jsps/css1/form.css" rel="stylesheet" type="text/css" media="all" />
<link href='/ssh/jsps/css1/fonts.css' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="/ssh/jsps/js1/jquery1.min.js"></script>
<link rel="shortcut icon" href="/ssh/images/icons/favicon.ico">

<!-- start menu -->
<link href="/ssh/jsps/css1/megamenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="/ssh/jsps/js1/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<!--start slider -->
    <link rel="stylesheet" href="/ssh/jsps/css1/fwslider.css" media="all">
    <script src="/ssh/jsps/js1/jquery-ui.min.js"></script>
    <script src="/ssh/jsps/js1/css3-mediaqueries.js"></script>
    <script src="/ssh/jsps/js1/fwslider.js"></script>
<!--end slider -->
<script src="/ssh/jsps/js1/jquery.easydropdown.js"></script>


<!-- 分级菜单 -->

	<script type="text/javascript" src="/ssh/menu/mymenu.js"></script>
	<link rel="stylesheet" href="/ssh/menu/mymenu.css" type="text/css" media="all">
<script language="javascript">
/*
 * 1. 对象名必须与第一个参数相同！
   2. 第二个参数是显示在菜单上的大标题
 */
var bar = new Q6MenuBar("bar", "Books网上书城");
$(function() {
	bar.colorStyle = 4;//指定配色样式，一共0,1,2,3,4
	bar.config.imgDir = "<c:url value='/menu/img/'/>";//小工具所需图片的路径
	bar.config.radioButton=true;//是否排斥，多个一级分类是否排斥

	/*
	1. 程序设计：一级分类名称
	2. Java Javascript：二级分类名称
	3. /goods/jsps/book/list.jsp：点击二级分类后链接到的URL
	4. body:链接的内容在哪个框架页中显示
	*/
<c:forEach items="${parents}" var="parent">
<c:forEach items="${parent.TCategories}" var="child">
	bar.add("${parent.categoryName}", "${child.categoryName}", "${pageContext.request.contextPath}/Book/findByCategory.do?categoryId=${child.categoryId}", "body");
</c:forEach>
</c:forEach>
	
	$("#menu").html(bar.toString());
});
</script>

</head>
<body>
<!--头部-->
<div class="header-top">
	<div class="wrap">
		<div class="header-top-left">
			<!--<div class="box">-->
			<!--<select tabindex="4" class="dropdown">-->
			<!--<option value="" class="label" value="">Language :</option>-->
			<!--<option value="1">English</option>-->
			<!--<option value="2">French</option>-->
			<!--<option value="3">German</option>-->
			<!--</select>-->
			<!--</div>-->
			<!--<div class="box1">-->
			<!--<select tabindex="4" class="dropdown">-->
			<!--<option value="" class="label" value="">Currency :</option>-->
			<!--<option value="1">$ Dollar</option>-->
			<!--<option value="2">€ Euro</option>-->
			<!--</select>-->
			<!--</div>-->
			<!--<div class="clear"></div>-->
		</div>
		<div class="cssmenu">
			<ul>
				<!--<li class="active"><a href="login.html">Account</a></li> |-->
				<!--<li><a href="checkout.html">Wishlist</a></li> |-->
				<!--<li><a href="checkout.html">Checkout</a></li> |-->
				<%-- 根据用户是否登录，显示不同的链接 --%>
				<c:choose>
					<c:when test="${empty sessionScope.sessionUser }">
						<li><a href="/ssh/jsps/user/login1.jsp">登录</a></li> |
						<li><a href="/ssh/jsps/user/regist1.jsp">注册</a></li>
					</c:when>
					<c:otherwise>
						<li>网上书城会员：${sessionScope.sessionUser.userName }  </li>|
						<li><a href="${pageContext.request.contextPath}/CartItem/myCart.do" target="body">我的购物车</a>  </li>|
						<li><a href="${pageContext.request.contextPath}/Order/myOrders.do" target="body">我的订单</a>  </li>|
						<li><a href="<c:url value='/jsps/user/pwd.jsp'/>" target="body">修改密码</a>  </li>|
						<li><a href="quitUser.do" target="_parent">退出</a>  </li>|
						<li><a href="http://www.baidu.com" target="_top">联系我们</a></li>
					</c:otherwise>
				</c:choose>
				<!-- <li><a href="/ssh/jsps/user/login1.jsp">登录</a></li> |
				<li><a href="/ssh/jsps/user/regist1.jsp">注册</a></li> -->
				
			</ul>
		</div>
		<div class="clear"></div>
	</div>
</div>
<div class="header-bottom">
	<div class="wrap">
		<div class="header-bottom-left">
			<div class="logo">
				<a href="index.html"><img src="/ssh/jsps/images/logo1.png" alt=""/></a>
			</div>
			<div class="menu">
				<ul class="megamenu skyblue">
					<li class="active grid"><a href="index.html">首页</a></li>
					<li><a class="color4" href="#">女士</a>
						<div class="megapanel">
							<div class="row">
								<div class="col1">
									<div class="h_nav">
										<h4>隐形眼镜</h4>
										<ul>
											<li><a href="womens.html">女士眼镜一</a></li>
											<li><a href="womens.html">眼镜2</a></li>
											<li><a href="womens.html">眼镜3</a></li>
											<li><a href="womens.html">眼镜4</a></li>
										</ul>
									</div>
								</div>
								<div class="col1">
									<div class="h_nav">
										<h4>太阳镜</h4>
										<ul>
											<li><a href="womens.html">眼镜一</a></li>
											<li><a href="womens.html">眼镜一</a></li>
											<li><a href="womens.html">眼镜一</a></li>
											<li><a href="womens.html">眼镜一</a></li>
										</ul>
									</div>
								</div>
								<div class="col1">
									<div class="h_nav">
										<h4>木架眼镜</h4>
										<ul>
											<li><a href="womens.html">眼镜一</a></li>
											<li><a href="womens.html">眼镜一</a></li>
											<li><a href="womens.html">眼镜一</a></li>
											<li><a href="womens.html">眼镜一</a></li>
											<li><a href="womens.html">眼镜一</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li><a class="color5" href="#">男士</a>
						<div class="megapanel">
							<div class="row">
								<div class="col1">
									<div class="h_nav">
										<h4>隐形眼镜</h4>
										<ul>
											<li><a href="mens.html">男士眼镜一</a></li>
											<li><a href="mens.html">眼镜一</a></li>
											<li><a href="mens.html">眼镜一</a></li>
											<li><a href="mens.html">眼镜一</a></li>
										</ul>
									</div>
								</div>
								<div class="col1">
									<div class="h_nav">
										<h4>太阳镜</h4>
										<ul>
											<li><a href="mens.html">眼镜一</a></li>
											<li><a href="mens.html">眼镜一</a></li>
											<li><a href="mens.html">眼镜一</a></li>
											<li><a href="mens.html">眼镜一</a></li>
										</ul>
									</div>
								</div>
								<div class="col1">
									<div class="h_nav">
										<h4>木架眼镜</h4>
										<ul>
											<li><a href="mens.html">眼镜一</a></li>
											<li><a href="mens.html">眼镜一</a></li>
											<li><a href="mens.html">眼镜一</a></li>
											<li><a href="mens.html">眼镜一</a></li>
											<li><a href="mens.html">眼镜一</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li><a class="color6" href="other.html">其他</a></li>
					<!--<li><a class="color7" href="other.html">Purchase</a></li>-->
				</ul>
			</div>
		</div>

		<div class="header-bottom-right">
			<div class="search">
				<input type="text" name="s" class="textbox" value="Search" onfocus="this.value = '';"
					   onblur="if (this.value == '') {this.value = 'Search';}">
				<input type="submit" value="Subscribe" id="submit" name="submit">
				<div id="response"></div>
			</div>
			<div class="tag-list">
				<ul class="icon1 sub-icon1 profile_img">
					<li><a class="active-icon c1" href="#" title="我的信息"> </a>
						<!--<ul class="sub-icon1 list">-->
						<!--<li><h3>个人信息</h3><a href=""></a></li>-->
						<!--<li><p>Lorem ipsum dolor sit amet, consectetuer <a href="">adipiscing elit, sed diam</a></p>-->
						<!--</li>-->
						<!--</ul>-->
					</li>
				</ul>
				<ul class="icon1 sub-icon1 profile_img">
					<li><a class="active-icon c2" href="#" title="购物车"> </a>
						<!--<ul class="sub-icon1 list">-->
						<!--<li><h3>购物车</h3><a href=""></a></li>-->
						<!--<li><p>Lorem ipsum dolor sit amet, consectetuer <a href="">adipiscing elit, sed diam</a></p>-->
						<!--</li>-->
						<!--</ul>-->
					</li>
				</ul>
				<!--<ul class="last">-->
				<!--<li><a href="#">Cart(0)</a></li>-->
				<!--</ul>-->
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>

<!--身体-->
  <!-- start slider -->
    <div id="fwslider">
        <div class="slider_container">
            <div class="slide"> 
                <!-- Slide image -->
                    <img src="/ssh/jsps/images/banner.jpg" alt=""/>
                <!-- /Slide image -->
                <!-- Texts container -->
               <!--  <div class="slide_content">
                    <div class="slide_content_wrap">
                        Text title
                        <h4 class="title">Aluminium Club</h4>
                        /Text title
                        
                        Text description
                        <p class="description">Experiance ray ban</p>
                        /Text description
                    </div>
                </div> -->
                 <!-- /Texts container -->
            </div>
            <!-- /Duplicate to create more slides -->
            <div class="slide">
                <img src="/ssh/jsps/images/banner1.jpg" alt=""/>
                <!-- <div class="slide_content">
                    <div class="slide_content_wrap">
                        <h4 class="title">consectetuer adipiscing </h4>
                        <p class="description">diam nonummy nibh euismod</p>
                    </div>
                </div> -->
            </div>
            <!--/slide -->
        </div>
        <div class="timers"></div>
        <div class="slidePrev"><span></span></div>
        <div class="slideNext"><span></span></div>
    </div>
    <!--/slider -->
<div class="main">
	<div class="wrap">
		<div class="section group">
		  <div class="cont span_2_of_3">
		  	<h2 class="head">挪威小鱼</h2>
			<div class="top-box">
			 <div class="col_1_of_3 span_1_of_3"> 
			   <a href="single.html">
				<div class="inner_content clearfix">
					<div class="product_image">
						<img src="/ssh/shop_img/1.jpg" alt=""/>
					</div>
                    <div class="sale-box"><span class="on_sale title_shop">new</span></div>	
                    <div class="price">
					   <div class="cart-left">
							<p class="title">北冰洋深海鳕鱼</p>
							<div class="price1">
							  <span class="actual">￥ 82.00</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                 </a>
				</div>
			   <div class="col_1_of_3 span_1_of_3">
			   	 <a href="single.html">
					<div class="inner_content clearfix">
					<div class="product_image">
						<img src="/ssh/shop_img/2.jpg" alt=""/>
					</div>
                    <div class="price">
					   <div class="cart-left">
							<p class="title">怀孕、哺乳期DHA软胶囊</p>
							<div class="price1">
							  <span class="actual">￥149.00</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                   </a>
				</div>
				<div class="col_1_of_3 span_1_of_3">
				 <a href="single.html">
				  <div class="inner_content clearfix">
					<div class="product_image">
						<img src="/ssh/shop_img/3.jpg" alt=""/>
					</div>
                    <div class="sale-box1"><span class="on_sale title_shop">Sale</span></div>	
                    <div class="price">
					   <div class="cart-left">
							<p class="title">孕妇DHA ，调整心情与神经系统 </p>
							<div class="price1">
							  <span class="reducedfrom">￥269.00</span>
							  <span class="actual">￥168.00</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                   </a>
				</div>
				<div class="clear"></div>
			</div>	
			<div class="top-box">
			  <div class="col_1_of_3 span_1_of_3">
			  	 <a href="single.html">
				 <div class="inner_content clearfix">
					<div class="product_image">
						<img src="/ssh/shop_img/4.jpg" alt=""/>
					</div>
                    <div class="price">
					   <div class="cart-left">
							<p class="title">儿童专用</p>
							<div class="price1">
							  <span class="actual">￥75.00</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                   </a>
				</div>
				<div class="col_1_of_3 span_1_of_3">
					<a href="single.html">
					<div class="inner_content clearfix">
					<div class="product_image">
						<img src="/ssh/shop_img/5.jpg" alt=""/>
					</div>
					 <div class="sale-box"><span class="on_sale title_shop">New</span></div>	
                    <div class="price">
					   <div class="cart-left">
							<p class="title">草莓口味 DHA软糖</p>
							<div class="price1">
							  <span class="actual">￥78.00</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                   </a>
				</div>
				<div class="col_1_of_3 span_1_of_3">
				 <a href="single.html">
				 <div class="inner_content clearfix">
					<div class="product_image">
						<img src="/ssh/shop_img/6.jpg" alt=""/>
					</div>
                    <div class="price">
					   <div class="cart-left">
							<p class="title">天然深海鱼油 营养补充剂</p>
							<div class="price1">
							  <span class="actual">￥129.00</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                 </a>
				</div>
				<div class="clear"></div>
			</div>	
			<div class="top-box1">
			  <div class="col_1_of_3 span_1_of_3">
			  	 <a href="single.html">
				 <div class="inner_content clearfix">
					<div class="product_image">
						<img src="/ssh/shop_img/7.jpg" alt=""/>
					</div>
                     <div class="sale-box"><span class="on_sale title_shop">New</span></div>	
                    <div class="price">
					   <div class="cart-left">
							<p class="title">Lorem Ipsum simply</p>
							<div class="price1">
							  <span class="actual">$12.00</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                   </a>
				</div>
				<div class="col_1_of_3 span_1_of_3">
				 <a href="single.html">
					<div class="inner_content clearfix">
					<div class="product_image">
						<img src="/ssh/shop_img/8.jpg" alt=""/>
					</div>
					 <div class="sale-box1"><span class="on_sale title_shop">Sale</span></div>	
                    <div class="price">
					   <div class="cart-left">
							<p class="title">Lorem Ipsum simply</p>
							<div class="price1">
							  <span class="reducedfrom">$66.00</span>
							  <span class="actual">$12.00</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                   </a>
				</div>
				<div class="col_1_of_3 span_1_of_3">
				  <a href="single.html">
				 <div class="inner_content clearfix">
					<div class="product_image">
						<img src="/ssh/shop_img/8.jpg" alt=""/>
					</div>
                   	 <div class="sale-box"><span class="on_sale title_shop">New</span></div>	
                    <div class="price">
					   <div class="cart-left">
							<p class="title">Lorem Ipsum simply</p>
							<div class="price1">
							  <span class="actual">$12.00</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                   </a>
				</div>
				<div class="clear"></div>
			</div>	
		  <h2 class="head">Staff Pick</h2>
		  <div class="top-box1">
			  <div class="col_1_of_3 span_1_of_3">
			  	 <a href="single.html">
				 <div class="inner_content clearfix">
					<div class="product_image">
						<img src="/ssh/jsps/images/pic8.jpg" alt=""/>
					</div>
                     <div class="sale-box"><span class="on_sale title_shop">New</span></div>	
                    <div class="price">
					   <div class="cart-left">
							<p class="title">Lorem Ipsum simply</p>
							<div class="price1">
							  <span class="actual">$12.00</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                   </a>
				</div>
				<div class="col_1_of_3 span_1_of_3">
					 <a href="single.html">
					<div class="inner_content clearfix">
					<div class="product_image">
						<img src="/ssh/jsps/images/pic4.jpg" alt=""/>
					</div>
				    <div class="price">
					   <div class="cart-left">
							<p class="title">Lorem Ipsum simply</p>
							<div class="price1">
							  <span class="actual">$12.00</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                   </a>
				</div>
				<div class="col_1_of_3 span_1_of_3">
				 <a href="single.html">
				 <div class="inner_content clearfix">
					<div class="product_image">
						<img src="/ssh/jsps/images/pic2.jpg" alt=""/>
					</div>
                   	 <div class="sale-box"><span class="on_sale title_shop">New</span></div>	
                    <div class="price">
					   <div class="cart-left">
							<p class="title">Lorem Ipsum simply</p>
							<div class="price1">
							  <span class="actual">$12.00</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                   </a>
				</div>
				<div class="clear"></div>
			</div>	
	        <h2 class="head">New Products</h2>	
		    <div class="section group">
			  <div class="col_1_of_3 span_1_of_3">
			  	 <a href="single.html">
				 <div class="inner_content clearfix">
					<div class="product_image">
						<img src="/ssh/jsps/images/pic5.jpg" alt=""/>
					</div>
                     <div class="sale-box"><span class="on_sale title_shop">New</span></div>	
                    <div class="price">
					   <div class="cart-left">
							<p class="title">Lorem Ipsum simply</p>
							<div class="price1">
							  <span class="actual">$12.00</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                   </a>
				</div>
				<div class="col_1_of_3 span_1_of_3">
					<a href="single.html">
					<div class="inner_content clearfix">
					<div class="product_image">
						<img src="/ssh/jsps/images/pic2.jpg" alt=""/>
					</div>
					 <div class="sale-box"><span class="on_sale title_shop">New</span></div>	
                    <div class="price">
					   <div class="cart-left">
							<p class="title">Lorem Ipsum simply</p>
							<div class="price1">
							  <span class="actual">$12.00</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                   </a>
				</div>
				<div class="col_1_of_3 span_1_of_3">
				 <a href="single.html">
				 <div class="inner_content clearfix">
					<div class="product_image">
						<img src="/ssh/jsps/images/pic3.jpg" alt=""/>
					</div>
                   	 <div class="sale-box"><span class="on_sale title_shop">New</span></div>	
                    <div class="price">
					   <div class="cart-left">
							<p class="title">Lorem Ipsum simply</p>
							<div class="price1">
							  <span class="actual">$12.00</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                   </a>
				</div>
				<div class="clear"></div>
			</div>			 						 			    
		  </div>
			<div class="rsidebar span_1_of_left">
				<div class="top-border"> </div>
				<div class="sidebar-bottom">
			    
			    <div id="menu"></div>
			    
			    
			    
			    
			</div>
				 
           <div class="top-border"> </div>
           
           <div class="border">
           
	             <link href="/ssh/jsps/css1/default.css" rel="stylesheet" type="text/css" media="all" />
	             <link href="/ssh/jsps/css1/nivo-slider.css" rel="stylesheet" type="text/css" media="all" />
				  <script src="/ssh/jsps/js1/jquery.nivo.slider.js"></script>
				    <script type="text/javascript">
				    $(window).load(function() {
				        $('#slider').nivoSlider();
				    });
				    </script>
		    <div class="slider-wrapper theme-default">
              <div id="slider" class="nivoSlider">
                <img src="/ssh/jsps/images/t-img1.jpg"  alt="" />
               	<img src="/ssh/jsps/images/t-img2.jpg"  alt="" />
                <img src="/ssh/jsps/images/t-img3.jpg"  alt="" />
              </div>
             </div>
              <div class="btn"><a href="single.html">Check it Out</a></div>
             </div>
           
           
			
	    </div>
	   <div class="clear"></div>
	</div>
	</div>
	</div>
	
	
<!--底部-->
<div class="footer">
	<div class="footer-top">
		<div class="wrap">
			<div class="section group example">
				<div class="col_1_of_2 span_1_of_2">
					<ul class="f-list">
						<li><img src="/ssh/jsps/images/2.png"><span class="f-text">满49全场包邮哦！</span>
							<div class="clear"></div>
						</li>
					</ul>
				</div>
				<div class="col_1_of_2 span_1_of_2">
					<ul class="f-list">
						<li><img src="/ssh/jsps/images/3.png"><span class="f-text">热线服务：400-280-8820</span>
							<div class="clear"></div>
						</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<div class="footer-middle">
		<div class="wrap">
			<div class="section group example">
				<div class="col_1_of_f_1 span_1_of_f_1">
					<div class="section group example">
						<div class="col_1_of_f_2 span_1_of_f_2">
							<h3>消费者保障 </h3>
							<ul class="f-list1">
								<li><a href="market.html">保障范围 </a></li>
								<li><a href="#">退货退款流程</a></li>
								<li><a href="#">服务中心</a></li>
								<li><a href="#">特色服务 </a></li>
							</ul>
						</div>
						<div class="col_1_of_f_2 span_1_of_f_2">
							<h3>新手上路</h3>
							<ul class="f-list1">
								<li><a href="new.html">新手专区 </a></li>
								<li><a href="#">消费警示</a></li>
								<li><a href="#">交易安全</a></li>
								<li><a href="#">在线帮助 </a></li>
							</ul>
							<div class="clear"></div>
						</div>

					</div>
					<div class="clear"></div>
				</div>
			</div>
			<div class="col_1_of_f_1 span_1_of_f_1">
				<div class="section group example">
					<div class="col_1_of_f_2 span_1_of_f_2">
						<h3>付款方式</h3>
						<ul class="f-list1">
							<li><a href="pay.html">快捷支付 </a></li>
							<li><a href="#">信用卡 </a></li>
							<li><a href="#">余额宝</a></li>
							<li><a href="#">货到付款</a></li>
						</ul>
					</div>
					<div class="col_1_of_f_2 span_1_of_f_2">
						<h3>联系我们</h3>
						<div class="company_address">
							<p>四川省郫县</p>
							<p>郫筒镇中信大道2段</p>
							<p>电话:400-820-8820</p>
							<p>Email: <span>4008208820@qq.com</span></p>
						</div>
						<!--<div class="social-media">-->
						<!--<ul>-->
						<!--<li><span class="simptip-position-bottom simptip-movable" data-tooltip="Google"><a-->
						<!--href="#" target="_blank"> </a></span></li>-->
						<!--<li><span class="simptip-position-bottom simptip-movable"-->
						<!--data-tooltip="Linked in"><a href="#" target="_blank"> </a> </span></li>-->
						<!--<li><span class="simptip-position-bottom simptip-movable" data-tooltip="Rss"><a-->
						<!--href="#" target="_blank"> </a></span></li>-->
						<!--<li><span class="simptip-position-bottom simptip-movable" data-tooltip="Facebook"><a-->
						<!--href="#" target="_blank"> </a></span></li>-->
						<!--</ul>-->
						<!--</div>-->
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
</div>
<div class="footer-bottom">
	<div class="wrap">
		<div class="copy">
			<p>Copyright &copy; 2014. All rights reserved.</p>
		</div>
		<!--<div class="f-list2">-->
		<!--<ul>-->
		<!--<li class="active"><a href="about.html">关于我们</a></li>-->
		<!--|-->
		<!--<li><a href="delivery.html">联系我们</a></li>-->
		<!--|-->
		<!--<li><a href="delivery.html">Terms & Conditions</a></li>-->
		<!--|-->
		<!--<li><a href="contact.html">Contact Us</a></li>-->
		<!--</ul>-->
		<!--</div>-->
		<div class="clear"></div>
	</div>
</div>
</div>

</body>
</html>