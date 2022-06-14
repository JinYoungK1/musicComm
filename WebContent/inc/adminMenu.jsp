<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
<div id="login">
<!-- <input type="button" value="플레이어" onclick="window.open('./MusicPlayer.mu','window팝업','width=400, height=200, menubar=no, status=no, toolbar=no');"> -->
<c:choose>
<c:when test="${sessionScope.sessionID eq null }">
<a href="./MyMemberLogin.me">login</a>
</c:when>
<c:when test="${sessionScope.sessionID eq 'admin'}">
<a href="./MyMemberList.me">전체목록관리</a> | <a href="">관리자</a>로 접속중입니다. | <a href="./MyLogout.me">logout</a> | <a href="./MyBoardMain.mu">메인</a>

</c:when>
<c:otherwise>
<a href="./MyMemberInfo.me">${sessionScope.sessionID }</a>님 환영합니다. | <a href="./MyLogout.me">logout</a>
 | <a href="./MyMemberDel.me">회원탈퇴</a>
</c:otherwise>
</c:choose>
 | <a href="./MyMemberJoin.me">join</a></div>
<div class="clear"></div>
<!-- 로고들어가는 곳 -->
<div id="logo">
<!-- <img src="./images/logo.gif" width="265" height="62" alt="Fun Web"> -->
<span style="font-weight: bold;font-style: italic;font-size: 45px;color: blue;text-shadow: 4px 4px 5px aqua;">Music Comm</span>
</div>
<!-- 로고들어가는 곳 -->
<nav id="top_menu" style="font-weight: bold;font-style: italic;font-size: 25px;color: blue;text-shadow: 4px 4px 5px aqua;">
<ul>
	<li><a href="./MyMemberList.me">유저 목록</a></li>
	<li><a href="./AdminMusicList.mu">음악 목록</a></li>
	<li><a href="./AdminBoardList.bo">공유 자료실 목록</a></li>
	<li><a href="./AdminGalList.ga">갤러리 목록</a></li>
</ul>
</nav>
</header>
<hr>