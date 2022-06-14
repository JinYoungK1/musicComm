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
		<jsp:include page="../inc/adminMenu.jsp"></jsp:include>
		<!-- 헤더들어가는 곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 메인이미지 -->
<!-- 		<div id="sub_img_center"></div> -->
		<!-- 메인이미지 -->

		<!-- 왼쪽메뉴 -->
		<!-- 왼쪽메뉴 -->

		<!-- 게시판 -->
		<article>
			<form action="./AdminGalUpload.ga" method="post" enctype="multipart/form-data">
				<h1>게시판 글쓰기(첨부파일)</h1>
				<table id="notice">
					<tr>
						<th class="twrite">종류</th>
						<th class="ttitle">내용</th>
					</tr>
					<tr>
						<td>이미지 이름</td>
						<td><input type="text" name="img_name"></td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" name="subject"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><input type="text" name="content"></td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td><input type="file" name="file"></td>
					</tr>
				</table>

				<div id="table_search">
					<input type="submit" value="업로드" class="btn">
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