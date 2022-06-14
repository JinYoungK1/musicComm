package com.myproject.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.member.db.MyMemberDAO;
import com.myproject.member.db.MyMemberDTO;

public class MyMemberContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MyMemberContentAction_execute 호출");
		
		String id = request.getParameter("id");
		System.out.println("M : 전달된 아이디 - " + id);
		
		MyMemberDAO dao = new MyMemberDAO();
		
		MyMemberDTO dto = dao.memberInfo(id);
		
		response.setContentType("text/html; charset=UTF-8");
		
		if(dto == null) {
			PrintWriter out = response.getWriter();
			out.print("아이디 사용가능");
			out.close();
		} else {
			PrintWriter out = response.getWriter();
			out.print("아이디 중복");
			out.close();
		}
		System.out.println("M : 결과출력됨");
		return null;
	}

}
