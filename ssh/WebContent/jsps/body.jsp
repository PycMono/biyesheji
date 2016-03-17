<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="">
    
    <title>body</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	 <link href="../css/style.css" media="screen" rel="stylesheet" type="text/css">
  <link href="../grid.css" media="screen" rel="stylesheet" type="text/css">
	
	 <script src="../js/jquery-1.7.2.min.js" ></script>
  <script src="../js/html5.js" ></script>
  <script src="../js/jflow.plus.js" ></script>
  <script src="../js/jquery.carouFredSel-5.2.2-packed.js"></script>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
a {text-decoration: none;}
</style>
  </head>
   
  <script>
	$(document).ready(function(){
	    $("#myController").jFlow({
			controller: ".control", // must be class, use . sign
			slideWrapper : "#jFlowSlider", // must be id, use # sign
			slides: "#slider",  // the div where all your sliding divs are nested in
			selectedWrapper: "jFlowSelected",  // just pure text, no sign
			width: "978px",  // this is the width for the content-slider
			height: "480px",  // this is the height for the content-slider
			duration: 400,  // time in miliseconds to transition one slide
			prev: ".slidprev", // must be class, use . sign
			next: ".slidnext", // must be class, use . sign
			auto: true
    });
  });
  </script>
  <script>
	$(function() {
	  $('#list_product').carouFredSel({
		prev: '#prev_c1',
		next: '#next_c1',
		auto: false
	  });
          $('#list_product2').carouFredSel({
		prev: '#prev_c2',
		next: '#next_c2',
		auto: false
	  });
	  $(window).resize();
	});
  </script>
  <script>
       $(document).ready(function(){
	      $("button").click(function(){
		     $(this).addClass('click')
	      });
       })
  </script>
  <body>
    <h1>欢迎进入网上书城系统</h1>
     <div class="grid_12">
        <div class="slidprev"><span>Prev</span></div>
        <div class="slidnext"><span>Next</span></div>
        <div id="slider">
          <div id="slide1">
            <img src="../images/slide1.jpg" alt="" title="" />
        <!--     <div class="slid_text">
            <h3 class="slid_title"><span>记忆之城</span></h3>
              <p><span>淡淡的颜色 </span></p>
              <p><span>淡淡的风  淡淡的风</span></p>
              <p><span>---------付辛博</span></p>
            </div> -->  
          </div>

          <div id="slide2">
            <img src="../images/slide2.jpg" alt="" title="" />
       <!--   <div class="slid_text">
              <h3 class="slid_title"><span>Flexibility</span></h3>
              <p><span>Every product, which you are selling,</span></p>
              <p><span>will look great with Breeze theme.</span></p>
            </div> --> 
          </div>

          <div id="slide3">
            <img src="../images/slide3.jpg" alt="" title="" />
            <div class="slid_text">
              <h3 class="slid_title"><span>记忆之城</span></h3>
              <p><span>淡淡的颜色 </span></p>
              <p><span>淡淡的风  淡淡的风</span></p>
              <p><span>---------付辛博</span></p>
            </div>
          </div>
        </div><!-- .slider -->
        <div id="myController">
          <div class="control"><span>1</span></div>
          <div class="control"><span>2</span></div>
          <div class="control"><span>3</span></div>
        </div>
  </body>
</html>
