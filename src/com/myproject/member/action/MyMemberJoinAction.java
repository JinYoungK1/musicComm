package com.myproject.member.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.member.db.MyMemberDAO;
import com.myproject.member.db.MyMemberDTO;

public class MyMemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : MyMemberJoinAction_execute() 호출");
		
		request.setCharacterEncoding("UTF-8");
		
		MyMemberDTO dto = new MyMemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPass(request.getParameter("pass"));
		dto.setEmail(request.getParameter("email"));
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setAddress(request.getParameter("address"));
		dto.setPhone(request.getParameter("phone"));
		dto.setDate(new Timestamp(System.currentTimeMillis()));
		
		System.out.println("M : 전달된 회원가입정보 ");
		System.out.println("M : " + dto);
		
		// DAO 객체 생성
		MyMemberDAO dao = new MyMemberDAO();
				
		// 회원가입 메서드 호출
		dao.insertMember(dto);
		
		System.out.println("M : 회원가입 완료! ");
				
		// 페이지 이동 (로그인 페이지- ./MemberLogin.me)
		ActionForward forward = new ActionForward();
		forward.setPath("./MyMemberLogin.me");
		forward.setRedirect(true);
			
		System.out.println("M : 페이지 이동정보를 컨트롤러로 리턴");
				
		return forward;
	}

}
