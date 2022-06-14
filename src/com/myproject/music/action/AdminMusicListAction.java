package com.myproject.music.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.music.db.MusicDAO;

public class AdminMusicListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : AdminMusicListAction_execute() 호출");
		
		MusicDAO dao = new MusicDAO();
		
		int result = dao.getBoardCount();
		
		System.out.println("M : result - " + result);
		
		int pageSize = 6;
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		
		ArrayList musicBoardList = null;
		
		if(result != 0) {
			musicBoardList = dao.getBoardList(startRow, pageSize);
		}
		System.out.println("M : " + musicBoardList);
		
		int pageCount = result / pageSize + (result % pageSize == 0? 0 : 1);
		
		int pageBlock = 3;
		
		int startBlock = ((currentPage - 1) / pageBlock) * pageBlock + 1;
		
		int endBlock = startBlock + pageBlock - 1;
		
		if(endBlock > pageCount) {
			endBlock = pageCount;
		}
		
		request.setAttribute("musicBoardCnt", result);
		request.setAttribute("musicBoardList", musicBoardList);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startBlock);
		request.setAttribute("endPage", endBlock);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./center/adminMusicBoard.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
