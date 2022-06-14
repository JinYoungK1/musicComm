<%@page import="com.myproject.board.db.MyBoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

			<h1>글 본문 (READ)</h1>
			<table id="notice">
				<tr>
					<th class="tno" colspan="4"></th>
				</tr>
				<tr>
					<td>글번호</td>
					<td>${requestScope.dto.num }</td>
					<td>조회수</td>
					<td>${requestScope.dto.readcount }</td>
				</tr>
				<tr>
					<td>글쓴이</td>
					<td>${requestScope.dto.name }</td>
					<td>작성일</td>
					<td>${requestScope.dto.date }</td>
				</tr>
				<tr>
					<td>글제목</td>
					<td colspan="3">${requestScope.dto.subject }</td>
				</tr>
				<tr>
					<td>첨부파일</td>
					<td colspan="3">
					<a href="./MyFileDownAction.bo?file_name=${requestScope.dto.file }">${requestScope.dto.file }</a>
					</td>
				</tr>
				<tr>
					<td>글 내용</td>
					<td colspan="3">${requestScope.dto.content }</td>
				</tr>

			</table>

			<div id="table_search">
				<input type="button" value="수정하기" class="btn"
					onclick="location.href='./MyBoardUpdate.bo?num=${requestScope.dto.num }&pageNum=${requestScope.pageNum}'; ">
				<input type="button" value="삭제하기" class="btn"
					onclick="location.href='./MyBoardDelete.bo?num=${requestScope.dto.num }&pageNum=${requestScope.pageNum}';">
				<input type="button" value="답글쓰기" class="btn"
					onclick="location.href='./MyBoardReWrite.bo?num=${requestScope.dto.num }&re_ref=${requestScope.dto.re_ref }&re_lev=${requestScope.dto.re_lev }&re_seq=${requestScope.dto.re_seq }';">
				<input type="button" value="목록이동" class="btn"
					onclick="location.href='./MyBoardList.bo?pageNum=${requestScope.pageNum}'; ">
			</div>
			<div class="clear"></div>

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