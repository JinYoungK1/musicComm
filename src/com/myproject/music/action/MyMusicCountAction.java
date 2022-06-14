package com.myproject.music.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.music.db.MusicDAO;
import com.myproject.music.db.MusicDTO;

public class MyMusicCountAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MyMusicCountAction_execute() 호출");
		
		request.setCharacterEncoding("UTF-8");
		
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		
		MusicDAO dao = new MusicDAO();
		
		dao.updateReadCount(num);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print("<script>");
		out.print("location.href='./MusicBoardList.mu?pageNum="+pageNum+"';");
		out.print("</script>");
		out.close();
		
		return null;
	}

}
