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
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.js"></script>
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
		<div id="player_Menu">
		<jsp:include page="../inc/jpSc.jsp"></jsp:include>
		</div>
		<!-- 왼쪽메뉴 -->

		<!-- 게시판 -->
		<article>
			<h1>게시판 (전체 글 개수 : ${requestScope.musicBoardCnt })</h1>
			<a href="./AdminMusicUploadPage.mu">업로드</a> | <a href="./AdminAlbumUpload.mu">앨범사진 업로드</a>
			<table id="notice">
				<tr>
					<th class="tno">번호</th>
					<th class="ttitle">곡정보</th>
					<th class="twrite">듣기</th>
					<th class="tdate">삭제</th>
				</tr>
				<c:set var="list" value="${requestScope.musicBoardList }"></c:set>
				<c:forEach var="boardList" items="${requestScope.musicBoardList}" varStatus="i">
					<tr>
						<td>${list[i.index].num }</td>
						<td class="left"> 							
							<a href="./MyMusicBoardContent.mu?num=${list[i.index].num }&pageNum=${requestScope.pageNum }"><img src="./albums/${list[i.index].album_name }.jpg" style="width: 70px;height: 70px; float: left;"></a>
							${list[i.index].music_name }<br>${list[i.index].artist_name }<br>${list[i.index].album_name }<br>${list[i.index].genre }
						</td>
						<td>
							<a href="./musics/${list[i.index].file }" class="playlist-add-bubble1" title="${list[i.index].music_name }" id="${list[i.index].artist_name }">${list[i.index].music_name }</a>
						
						</td>
						<td><a href="./AdminMusicUpLoadDel.mu?num=${list[i.index].num }&file=${list[i.index].file}">삭제</a></td>
					</tr>
				</c:forEach>
			</table>

			<div class="clear"></div>
			<div id="page_control">
				<c:if test="${requestScope.startPage gt requestScope.pageBlock }">
					<a
						href="./AdminMusicList.mu?pageNum=${requestScope.startPage - requestScope.pageBlock }">[◀]</a>
				</c:if>
				<c:forEach begin="${requestScope.startPage }"
					end="${requestScope.endPage }" var="startPage">
					<a href="./AdminMusicList.mu?pageNum=${startPage }">[${startPage }]</a>
				</c:forEach>
				<c:if test="${requestScope.endPage lt requestScope.pageCount }">
					<a
						href="./AdminMusicList.mu?pageNum=${requestScope.startPage + requestScope.pageBlock }">[▶]</a>
				</c:if>
			</div>
		</article>
		<!-- 게시판 -->
		<table id="test" border="1">
		
		</table>
		<div id="test2"></div>
		<!-- 본문들어가는 곳 -->
		<div class="clear"></div>
		<!-- 푸터들어가는 곳 -->
		<jsp:include page="../inc/bottomfooter.jsp"></jsp:include>
		<!-- 푸터들어가는 곳 -->
		
	</div>
</body>
</html>