<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/subpage.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
</head>
<body>
	<div id="wrap">
		<!-- 헤더들어가는 곳 -->
		<jsp:include page="../inc/topheader.jsp"></jsp:include>
		<!-- 헤더들어가는 곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 메인이미지 -->
<!-- 		<div id="sub_img_center"></div> -->
		<!-- 메인이미지 -->

		<!-- 왼쪽메뉴 -->
		<jsp:include page="../inc/subMenu.jsp"></jsp:include>
		<!-- 왼쪽메뉴 -->

		<!-- 게시판 -->
		<article>
			<form action="./SendMail.me" method="post">
				<h1>게시판 글쓰기(Create)</h1>
				<table id="notice">
					<tr>
						<td>보내는 사람 메일 : </td>
						<td><input type="text" name="sender"></td>
					</tr>
					<tr>
						<td>받는 사람 메일 : </td>
						<td><input type="text" name="receiver" value="rlawlsdud211@naver.com" readonly></td>
					</tr>
					<tr>
						<td>제목 : </td>
						<td><input type="text" name="subject"></td>
					</tr>
					<tr>
						<td>내용 : </td>
						<td><textarea name="content" cols=40 rows=20></textarea></td>
					</tr>
				</table>
				<div id="table_search">
					<input type="submit" value="발송" class="btn">
				</div>
				<div class="clear"></div>
			</form>
		</article>
		<!-- 게시판 -->
		<!-- 본문들어가는 곳 -->
		<div class="clear"></div>
		<!-- 푸터들어가는 곳 -->
		<jsp:include page="../inc/bottomfooter.jsp"></jsp:include>
		<!-- 푸터들어가는 곳 -->
	</div>
</body>
</html>