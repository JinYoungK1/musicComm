package com.myproject.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyMemberFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MyMemberFrontController-doProcess() 호출!");
		
		/////////////////////1. 가상주소 계산///////////////////////////
		
		// 매핑된 주소
		String requestURI = request.getRequestURI();
		System.out.println("requestURI(매핑된 주소) : " + requestURI);
		
		// 프로젝트 주소
		String ctxPath = request.getContextPath();
		System.out.println("ctxPath(프로젝트 주소) : " + ctxPath);
		
		// 매핑된 주소 - 프로젝트 주소 = 가상주소
		String command = requestURI.substring(ctxPath.length());
		System.out.println("command(가상주소) : " + command);
		
		System.out.println("C : 1. 가상주소 계산 완료 \n\n");
		
		/////////////////////1. 가상주소 계산///////////////////////////
		
		/////////////////////2. 가상주소 매핑///////////////////////////
		
		// 가상주소 매핑
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/MyMemberJoin.me")) {
			System.out.println("C : /MyMemberJoin.me");
			System.out.println("C : 회원가입페이지 호출 (DB 사용없이 view 페이지 출력)");
			
			forward = new ActionForward();
			forward.setPath("./member/join.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/MyMemberJoinAction.me")) {
			System.out.println("C : /MyMemberJoinAction.me 호출 ");
			System.out.println("C : 이전페이지 정보를 가져와서 DB에 저장 후 페이지 이동");
			
			action = new MyMemberJoinAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MyMemberLogin.me")) {
			System.out.println("C : /MyMemberLogin.me 호출");
			System.out.println("C : 로그인페이지 호출(DB 사용없이 view 페이지 출력)");
			
			forward = new ActionForward();
			forward.setPath("./member/login.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/MyLogin.me")) {
			System.out.println("C : /MyLogin.me 호출");
			System.out.println("C : DB정보와 로그인 정보를 사용하여 비교 후 페이지 출력");
			
			action = new MyLoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MyLogout.me")) {
			System.out.println("C : /MyLogout.me 호출");
			System.out.println("C : DB사용없이 세션id 삭제 후 페이지 출력");
			
			action = new MyLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MyMemberInfo.me")) {
			System.out.println("C : /MyMemberInfo.me 호출");
			System.out.println("C : DB를 사용해서 유저 정보를 페이지에 출력");
			
			action = new MyMemberInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MyMemberUpdateAction.me")) {
			System.out.println("C : /MyMemberUpdateAction.me 호출");
			System.out.println("C : 전달받은 정보를 사용해서 DB에 저장");
			
			action = new MyMemberUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MyMemberDel.me")) {
			System.out.println("C : /MyMemberDel.me 호출");
			System.out.println("C : DB사용없이 페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./member/deleteForm.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/MyMemberDelAction.me")) {
			System.out.println("C : /MyMemberDelAction.me 호출");
			System.out.println("C : 전달받은 정보를 사용해서 DB정보 삭제후 페이지 호출");
			
			action = new MyMemberDelAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MyMemberContent.me")) {
			System.out.println("C : /MyMemberContent.me");
			System.out.println("C : id정보를 가져와서 DB에 조회 아이디 중복여부 출력");
			
			action = new MyMemberContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/MyMemberList.me")) {
			System.out.println("C : /MyMemberList.me 호출");
			System.out.println("C : DB정보를 사용해서 페이지 호출");
			
			action = new MyMemberListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/AdminMemberDel.me")) {
			System.out.println("C : /AdminMemberDel.me 호출");
			System.out.println("C : 전달받은 정보를 사용해서 DB정보를 삭제후 페이지 호출");
			
			action = new AdminMemberDelAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/SendMailPage.me")) {
			System.out.println("C : /SendMailPage.me 호출");
			System.out.println("C : DB를 사용하지 않고 페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./center/myMailForm.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/SendMail.me")) {
			System.out.println("C : /SendMail.me 호출");
			System.out.println("C : 전달받은 정보를 사용해 메일 발송");
			
			action = new SendMailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("C : 2. 가상주소 매핑 완료\n\n");
		/////////////////////2. 가상주소 매핑///////////////////////////
		
		/////////////////////3. 페이지 이동///////////////////////////
		
		if(forward != null) { // 페이지 이동정보가 있다.
			
			if(forward.isRedirect()) { // 이동방식 true
				System.out.println("C : sendRedirect방식 - " + forward.getPath() + "이동");
				response.sendRedirect(forward.getPath());
				
			} else { // false
				System.out.println("C : forward방식 - " + forward.getPath() + "이동");
				
				RequestDispatcher dis
					= request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				
			}
			
			System.out.println("C : 3. 페이지 이동 완료 \n\n");
			
		}
		
		/////////////////////3. 페이지 이동///////////////////////////
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MyMemberFrontController-doGet() 호출!");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MyMemberFrontController-doPost() 호출!");
		doProcess(request, response);
	}

}
