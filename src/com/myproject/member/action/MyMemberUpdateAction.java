package com.myproject.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myproject.member.db.MyMemberDAO;
import com.myproject.member.db.MyMemberDTO;

public class MyMemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MyMemberUpdateAction_execute 호출");
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sessionID");
		if(id == null) {
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print("alert('아이디가 없습니다');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
			return null;
		}
		MyMemberDTO dto = new MyMemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPass(request.getParameter("pass"));
		dto.setEmail(request.getParameter("email"));
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setAddress(request.getParameter("address"));
		dto.setPhone(request.getParameter("phone"));
		MyMemberDAO dao = new MyMemberDAO();
		int result = dao.memberUpdate(dto);
		
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
			out.print("alert('아이디 없음!');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
			return null;
		}
		PrintWriter out = response.getWriter();
		
		out.print("<script>");
		out.print("alert('회원정보 수정 완료!');");
		out.print("location.href='./MusicBoardList.mu';");
		out.print("</script>");
		out.close();
		
		
		return null;
	}

}
