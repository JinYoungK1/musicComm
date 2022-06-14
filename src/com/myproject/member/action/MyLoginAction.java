package com.myproject.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myproject.member.db.MyMemberDAO;
import com.myproject.member.db.MyMemberDTO;

public class MyLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MyLoginAction_execute() 호출");
		
		request.setCharacterEncoding("UTF-8");
		
		MyMemberDTO dto = new MyMemberDTO();
		
		dto.setId(request.getParameter("id"));
		dto.setPass(request.getParameter("pass"));
		
		System.out.println("M : 전달된 정보 저장 완료");
		System.out.println("M : " + dto);
		
		MyMemberDAO dao = new MyMemberDAO();
		
		int result = dao.login(dto);
		response.setContentType("text/html; charset=UTF-8");
		if(result == 1) {
			HttpSession session = request.getSession();
			System.out.println("request parameter id : " + request.getParameter("id"));
			session.setAttribute("sessionID", dto.getId());
			
			
		} else if(result == 0){
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print("alert('비밀번호 오류!');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
			return null;
		} else {
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print("alert('비회원 입니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
		}
		ActionForward forward = new ActionForward();
		forward.setPath("./MyBoardMain.mu");
		forward.setRedirect(true);
		
		return forward;
	}

}
