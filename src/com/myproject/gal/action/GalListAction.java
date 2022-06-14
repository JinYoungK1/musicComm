package com.myproject.gal.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.gal.db.GalDAO;

public class GalListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : GalListAction_execute() 호출");
		
		GalDAO dao = new GalDAO();
		
		int result = dao.getGalCount();
		
		int pageSize = 8;
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		
		ArrayList galList = null;
		
		if(result != 0) {
			galList = dao.getGalList(startRow, pageSize);
		}
		
		System.out.println("M : " + galList);
		
		int pageCount = result / pageSize + (result % pageSize == 0? 0 : 1);
		
		int pageBlock = 3;
		
		int startBlock = ((currentPage - 1) / pageBlock) * pageBlock + 1;
		
		int endBlock = startBlock + pageBlock -1;
		
		if(endBlock > pageCount) {
			endBlock = pageCount;
		}
		
		request.setAttribute("galCnt", result);
		request.setAttribute("galList", galList);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startBlock);
		request.setAttribute("endPage", endBlock);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./center/galNotice.jsp");
		forward.setRedirect(false);
		
		return forward;
	
	}

}
