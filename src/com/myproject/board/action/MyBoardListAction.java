package com.myproject.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.board.db.MyBoardDAO;

public class MyBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MyBoardListAction_execute() 호출");
		
		MyBoardDAO dao = new MyBoardDAO();
		
		int result = dao.getBoardCount();
		
		System.out.println("M : result " + result);
		
		int pageSize = 6;
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		
		ArrayList boardList = null;
		
		if(result != 0) {
			boardList = dao.getBoardList(startRow, pageSize);
		}
		
		System.out.println("M : " + boardList);
		
		int pageCount = result / pageSize + (result % pageSize == 0? 0 : 1);
		
		int pageBlock = 3;
		
		int startBlock = ((currentPage - 1) / pageBlock) * pageBlock + 1;
		
		int endBlock = startBlock + pageBlock -1;
		
		if(endBlock > pageCount) {
			endBlock = pageCount;
		}
		
		request.setAttribute("boardCnt", result);
		request.setAttribute("boardList", boardList);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startBlock);
		request.setAttribute("endPage", endBlock);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./center/notice.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
