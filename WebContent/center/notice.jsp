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
			<h1>게시판 (전체 글 개수 : ${requestScope.boardCnt })</h1>
			<table id="notice">
				<tr>
					<th class="tno">번호</th>
					<th class="ttitle">제목</th>
					<th class="twrite">글쓴이</th>
					<th class="tdate">날짜</th>
					<th class="tread">조회수</th>
				</tr>
				<c:set var="list" value="${requestScope.boardList }"></c:set>
				<c:forEach var="boardList" items="${requestScope.boardList}"
					varStatus="i">

					<tr>
						<td>${list[i.index].num }</td>
						<td class="left"><c:set var="wid" value="0" />
							<c:choose>
								<c:when test="${list[i.index].re_lev gt '0' }">
									<img src="./center/level.gif" width="${10 * list[i.index].re_lev }" height="15">
									<img src="./center/re.gif">
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
							<a href="./MyBoardContent.bo?num=${list[i.index].num }&pageNum=${requestScope.pageNum }">${list[i.index].subject }</a>
						</td>
						<td>${list[i.index].name }</td>
						<td>${list[i.index].date }</td>
						<td>${list[i.index].readcount }</td>
					</tr>
				</c:forEach>
			</table>

<!-- 			<div id="table_search"> -->
<!-- 				<input type="text" name="search" class="input_box">  -->
<!-- 				<input type="button" value="search" class="btn"> -->
<!-- 			</div> -->
			<div class="clear"></div>
			<div id="page_control">
				<c:if test="${requestScope.startPage gt requestScope.pageBlock }">
					<a
						href="./MyBoardList.bo?pageNum=${requestScope.startPage - requestScope.pageBlock }">[◀]</a>
				</c:if>
				<c:forEach begin="${requestScope.startPage }"
					end="${requestScope.endPage }" var="startPage">
					<a href="./MyBoardList.bo?pageNum=${startPage }">[${startPage }]</a>
				</c:forEach>
				<c:if test="${requestScope.endPage lt requestScope.pageCount }">
					<a
						href="./MyBoardList.bo?pageNum=${requestScope.startPage + requestScope.pageBlock }">[▶]</a>
				</c:if>
			</div>
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