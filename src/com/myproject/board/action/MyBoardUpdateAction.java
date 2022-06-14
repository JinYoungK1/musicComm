package com.myproject.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.board.db.MyBoardDAO;
import com.myproject.board.db.MyBoardDTO;

public class MyBoardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		MyBoardDAO dao = new MyBoardDAO();
		
		MyBoardDTO dto = dao.getBoard(num);
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./center/myBoardUpdate.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
