package com.myproject.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.board.db.MyBoardDAO;
import com.myproject.board.db.MyBoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MyBoardUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MyBoardUpdateProAction_execute 호출");
		
		request.setCharacterEncoding("UTF-8");
		String realPath = request.getSession().getServletContext().getRealPath("/uploads");
		
		int maxSize = 20 * 1024 * 1024;
		
		MultipartRequest multi = new MultipartRequest(
				request, 
				realPath,
				maxSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		MyBoardDTO dto = new MyBoardDTO();
		
		dto.setName(multi.getParameter("name"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setContent(multi.getParameter("content"));
		dto.setPass(multi.getParameter("pass"));
		
		dto.setFile(multi.getFilesystemName("file"));
		dto.setIp(request.getRemoteAddr());
		dto.setNum(Integer.parseInt(multi.getParameter("num")));
		
		String pageNum = request.getParameter("pageNum");
		System.out.println("M : 수정할 내용 - " + dto);
		System.out.println("M : 페이지 번호 - " + pageNum);
		
		MyBoardDAO dao = new MyBoardDAO();
		
		int result = dao.updateBoard(dto);
		
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
		out.print("alert('글 수정완료!');");
		out.print("location.href='./MyBoardList.bo?pageNum="+pageNum+"';");
		out.print("</script>");
		out.close();
		
		return null;
	}

}
