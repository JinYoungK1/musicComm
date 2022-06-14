<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// model2 개발방식에서 실행 가능한 유일한 jsp페이지
	
	System.out.println("프로젝트 시작!");
// 	response.sendRedirect("./main/main.jsp");
	//response.sendRedirect("http://localhost:8088/MySingleProject/MyMemberJoin.me");
// 	response.sendRedirect("./MyMemberJoin.me");
	
	//    ./ ~~~~ .me    => . 프로젝트경로
	//    ./ ~~~~ .jsp   => . WebContent 폴더
// 	response.sendRedirect("./MyBoardWrite.bo");
// 	response.sendRedirect("./MyBoardList.bo");
// 	response.sendRedirect("./MusicBoardList.mu");
// 	response.sendRedirect("./GalList.ga");
	response.sendRedirect("./MyBoardMain.mu");
	
%>