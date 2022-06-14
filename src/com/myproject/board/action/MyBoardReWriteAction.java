package com.myproject.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.board.db.MyBoardDAO;
import com.myproject.board.db.MyBoardDTO;

public class MyBoardReWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MyBoardReWriteAction_execute 호출");
		
		request.setCharacterEncoding("UTF-8");
		
		MyBoardDTO dto = new MyBoardDTO();
		// (num, re_ref, re_lev, re_seq, name, subject, content, pass)
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
		dto.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
		dto.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
		dto.setName(request.getParameter("name"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		dto.setPass(request.getParameter("pass"));
		
		MyBoardDAO dao = new MyBoardDAO();
		
		dao.reWriteBoard(dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./MyBoardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}

}
