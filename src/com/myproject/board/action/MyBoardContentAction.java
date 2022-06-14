package com.myproject.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.board.db.MyBoardDAO;
import com.myproject.board.db.MyBoardDTO;

public class MyBoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : MyBoardContentAction_execute 호출");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		MyBoardDAO dao = new MyBoardDAO();
		
		dao.updateReadCount(num);
		
		MyBoardDTO dto = dao.getBoard(num);
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./center/myBoardContent.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
