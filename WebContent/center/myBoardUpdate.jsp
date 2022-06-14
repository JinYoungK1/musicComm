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

<script type="text/javascript">
 	function updateCheck() {
 		if(document.fr.pass.value ==""){
 			alert("비밀번호를 입력해주세요!");
 			document.fr.pass.focus();
 			return false;
 		}
 	}
 
 </script>


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
			<form action="./MyBoardUpdatePro.bo?pageNum=${requestScope.pageNum }"
				method="post" name="fr" enctype="multipart/form-data" onsubmit="return updateCheck();">
				<h1>게시판 글 수정하기(Update)</h1>

				<input type="hidden" name="num" value="${requestScope.dto.num }">

				<table id="notice">
					<tr>
						<th class="twrite">입력</th>
						<th class="ttitle">게시판정보</th>
					</tr>
					<tr>
						<td>글쓴이</td>
						<td><input type="text" name="name"
							value="${requestScope.dto.name }"></td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" name="subject"
							value="${requestScope.dto.subject }"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea rows="10" cols="20" name="content">${requestScope.dto.content }</textarea>
						</td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td><input type="file" name="file">
						</td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="pass"></td>
					</tr>
				</table>

				<div id="table_search">
					<input type="submit" value="수정하기" class="btn">
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