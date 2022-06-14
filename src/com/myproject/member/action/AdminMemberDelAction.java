package com.myproject.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.member.db.MyMemberDAO;
import com.myproject.member.db.MyMemberDTO;

public class AdminMemberDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : AdminMemberDelAction_execute() 호출");
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		MyMemberDTO dto = new MyMemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPass(request.getParameter("pass"));
		System.out.println("M : 전달받은 정보 저장 완료 : " + dto);
		MyMemberDAO dao = new MyMemberDAO();
		int result = dao.memberDel(dto);
		
		if(result == 0) {
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print("alert('비밀번호가 오류!');");
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
		out.print("alert('회원 삭제 완료!');");
		out.print("location.href='./MyMemberList.me';");
		out.print("</script>");
		out.close();
		
		
		return null;
	}

}
