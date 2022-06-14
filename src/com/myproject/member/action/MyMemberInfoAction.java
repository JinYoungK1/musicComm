package com.myproject.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myproject.member.db.MyMemberDAO;
import com.myproject.member.db.MyMemberDTO;

public class MyMemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MyMemberInfoAction_execute 호출");
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sessionID");
		
		if(id == null) {
			forward = new ActionForward();
			forward.setPath("./MyLogin.me");
			forward.setRedirect(true);
		} else if( id.equals("admin")) {
			String uId = request.getParameter("id");
			MyMemberDAO dao = new MyMemberDAO();
			MyMemberDTO dto = dao.memberInfo(uId);
			System.out.println("M : 가져온 회원 정보  : " + dto);
			request.setAttribute("dto", dto);
			forward = new ActionForward();
			forward.setPath("./member/memberInfo.jsp");
			forward.setRedirect(false);
		} else {
			MyMemberDAO dao = new MyMemberDAO();
			MyMemberDTO dto = dao.memberInfo(id);
			System.out.println("M : 가져온 회원 정보  : " + dto);
			request.setAttribute("dto", dto);
			forward = new ActionForward();
			forward.setPath("./member/memberInfo.jsp");
			forward.setRedirect(false);
		}
		
		
		return forward;
	}

}
