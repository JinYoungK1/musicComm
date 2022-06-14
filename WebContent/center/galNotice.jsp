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
 <style type="text/css">
@charset "UTF-8";

ul#gallery{
    list-style: none;
    padding: 0;
    width: 800px;
}

ul#gallery>li>a{
    position: relative;
    float:left;
    display: inline-block;
    background-color: #000;
    width: 160px;
    height: 160px;
    margin: 1px;
    overflow: hidden;
}

ul#gallery>li>a::after{
    box-sizing: border-box;
    position: absolute;
    display: block;
    top: 50%;
    left: 50%;
    width: 87%;
    height: 87%;
    padding-top: 40px;
    border: 2px solid #fff;
    transform: translate(-50%, -50%);
    text-align: center;
    content: '다운';
    font-size: 35px;
    color: #fff;
    opacity: 0;
    transition: 0.8s;
}

ul#gallery>li>a>img{
    width: 100%;
    height: 100%;
    transition: 0.6s ease-in-out;
    
}

ul#gallery>li>a:hover img{
    transform: scale(1.18);
    opacity: 0.65;
}

ul#gallery>li>a:hover::after{
    opacity: 1;
}
</style>
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
			<h1>게시판 (전체 글 개수 : ${requestScope.galCnt })</h1>
			<ul id="gallery">	
		<c:set var="list" value="${requestScope.galList }"></c:set>
		<c:forEach var="galList" items="${requestScope.galList}"
					varStatus="i">
            <li><a href="./MyGalDownAction.ga?file_name=${list[i.index].file }"><img src="./gals/${list[i.index].file }" alt=""></a></li>   
        </c:forEach> </ul>
			<div class="clear"></div>
			<div id="page_control">
				<c:if test="${requestScope.startPage gt requestScope.pageBlock }">
					<a
						href="./GalList.ga?pageNum=${requestScope.startPage - requestScope.pageBlock }">[◀]</a>
				</c:if>
				<c:forEach begin="${requestScope.startPage }"
					end="${requestScope.endPage }" var="startPage">
					<a href="./GalList.ga?pageNum=${startPage }">[${startPage }]</a>
				</c:forEach>
				<c:if test="${requestScope.endPage lt requestScope.pageCount }">
					<a
						href="./GalList.ga?pageNum=${requestScope.startPage + requestScope.pageBlock }">[▶]</a>
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