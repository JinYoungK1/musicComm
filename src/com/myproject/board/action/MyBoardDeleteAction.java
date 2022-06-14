package com.myproject.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.board.db.MyBoardDAO;
import com.myproject.board.db.MyBoardDTO;

public class MyBoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MyBoardDeleteAction_execute 호출");
		
		request.setCharacterEncoding("UTF-8");
		
		MyBoardDTO dto = new MyBoardDTO();
		
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setPass(request.getParameter("pass"));
		
		String pageNum = request.getParameter("pageNum");
		
		MyBoardDAO dao = new MyBoardDAO();
		
		int result = dao.deleteBoard(dto);
		
		response.setContentType("text/html; charset=UTF-8");
		
		if(result == 0) {
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print("alert('비밀번호 오류!');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
			return null;
			
		}
		
		if(result == -1) {
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print("alert('해당 글 없음!');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
			return null;
		}
		
		PrintWriter out = response.getWriter();
		
		out.print("<script>");
		out.print("alert('글 삭제완료!');");
		out.print("location.href='./MyBoardList.bo?pageNum="+pageNum+"';");
		out.print("</script>");
		out.close();
		
		return null;
	}

}
