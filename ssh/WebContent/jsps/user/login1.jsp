<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>海淘网—登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/ssh/jsps/css1/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="/ssh/jsps/css1/fonts.css" rel='stylesheet' type='text/css'>
    <link rel="shortcut icon" href="/ssh/images/icons/favicon.ico">
	<link href="/ssh/jsps/css1/megamenu.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- start menu -->
    <script type="text/javascript" src="/ssh/jsps/js1/jquery1.min.js"></script>
    <script type="text/javascript" src="/ssh/jsps/js1/megamenu.js"></script>
    <script>$(document).ready(function () {
        $(".megamenu").megamenu();
    });
    </script>
    <!-- dropdown -->
    <script src="/ssh/jsps/js/jquery.easydropdown.js"></script>
    <script type="text/javascript" src="/ssh/jsps/js/user/login.js"></script>
	<script src="<c:url value='/js/common.js'/>"></script>
<script type="text/javascript">
	$(function() {/*Map<String(Cookie名称),Cookie(Cookie本身)>*/
		// 获取cookie中的用户名
		var loginname = window.decodeURI("${cookie.loginname.value}");
		if("${requestScope.u.userName}") {
			loginname = "${requestScope.u.userName}";
		}
		$("#loginname").val(loginname);
	});
</script>
</head>
<body>

<!--头部-->
<div class="header-top">
    <div class="wrap">
        <div class="header-top-left">
        </div>
        <div class="cssmenu">
            <ul>
                <li><a href="login1.jsp">登录</a></li>
                |
                <li><a href="regist1.jsp">注册</a></li>
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
                    </li>
                </ul>
                <ul class="icon1 sub-icon1 profile_img">
                    <li><a class="active-icon c2" href="#" title="购物车"> </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="clear"></div>
    </div>
</div>

<!--身体-->
<div class="login">
    <div class="wrap">
        <div class="col_1_of_login span_1_of_login">
            <h4 class="title">免费注册</h4>
            <p align="center"> 注册协议</p>
            <p> 【审慎阅读】您在申请注册流程中点击同意前，应当认真阅读以下协议。请您务必审慎阅读、充分理解协议中相关条款内容，其中包括：<br>
                1、与您约定免除或限制责任的条款；<br>
                2、与您约定法律适用和管辖的条款；<br>
                3、其他以粗体下划线标识的重要条款。<br>
                【特别提示】当您按照注册页面提示填写信息、阅读并同意协议且完成全部注册程序后，即表示您已充分阅读、理解并接受协议的全部内容。如您因平台服务发生争议的，请处理。
            </p>
            <div class="button1">
                <a href="regist1.jsp"><input type="submit" name="Submit" value="用户注册"></a>
            </div>
            <div class="clear"></div>
        </div>
        <div class="col_1_of_login span_1_of_login">
            <div class="login-title">
                <h4 class="title">会员登录</h4>
                <div id="loginbox" class="loginbox">
                    
                    <form target="_top" action="${pageContext.request.contextPath}/loginUI.do" method="post" id="loginForm">
                <input type="hidden" name="method" value="login" />
                  <table>
                    <tr>
                      <td width="50"></td>
                      <td><label class="error" id="msg">${msg }</label></td>
                    </tr>
                    <tr>
                      <td width="65">用户名:</td>
                      <td><input class="input" type="text" name="user.userName" id="loginname" value="${u.userName }"/></td>
                    </tr>
                    <tr>
                      <td height="20">&nbsp;</td>
                      <td class="tdError"><label class="errorClass"
							id="loginnameError">${errors.loginname }</label></td>
                    </tr>
                    <tr>
                      <td width="65">密　码:</td>
                      <td><input class="input" type="password" name="user.userPassword" id="loginpass" value="${u.userPassword }"/></td>
                    </tr>
                    <tr>
                      <td height="20">&nbsp;</td>
                     <td><label class="errorClass" id="loginpassError">${errors.loginpass }</label>
						</td>
                    </tr>
                    <tr>
                      <td width="65">验证码:</td>
                      <td>
                        <input class="input yzm" type="text" name="user.inputClass" id="verifyCode" value="${u.inputClass }"/>
                        <img id="vCode" src="${pageContext.request.contextPath}/randImage.do"/>
                        <a id="verifyCode" href="javascript:_hyz()">换张图</a>
                      </td>
                    </tr>
                    <tr>
                      <td height="20px">&nbsp;</td>
                     <td><label class="errorClass" id="verifyCodeError">${errors.verifyCode}</label>
						</td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td align="left">
                        <input style="background: #555 none repeat scroll 0% 0%;border: medium none;color: #FFF;padding: 10px 20px;cursor: pointer;float: right;font-family: "Exo 2",sans-serif;outline: medium none;font-size: 1em;" type="image" id="submit"  name="Submit" class="loginBtn" value="登录" >
                      </td>
                    </tr>																				
                 </table>
              </form>
                    
                    
                </div>
            </div>
        </div>
        <div class="clear"></div>
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
        <div class="clear"></div>
    </div>
</div>


</body>
</html>