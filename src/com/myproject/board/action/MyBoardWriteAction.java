package com.myproject.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.board.db.MyBoardDAO;
import com.myproject.board.db.MyBoardDTO;

public class MyBoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MyBoardWriteAction_execute() 호출");
		
		request.setCharacterEncoding("UTF-8");
		
		MyBoardDTO dto = new MyBoardDTO();
		
		dto.setName(request.getParameter("name"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		dto.setPass(request.getParameter("pass"));
		
		dto.setIp(request.getRemoteAddr());
		
		System.out.println("M : " + dto);
		
		MyBoardDAO dao = new MyBoardDAO();
		
		dao.insertBoard(dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./MyBoardList.bo");
		forward.setRedirect(true);
		
		System.out.println("M : 글쓰기 완료, 페이지 정보를 리턴");
		
		return forward;
	}

}
