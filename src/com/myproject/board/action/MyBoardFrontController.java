package com.myproject.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyBoardFrontController extends HttpServlet{

	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C : MyBoard-doProcess() 호출  \n\n\n");
		
		////////////////////1. 가상주소 계산/////////////////////////////
		
		// 매핑된 주소
		String requestURI = request.getRequestURI();
		System.out.println("C : requestURI(매핑된 주소) : " + requestURI);
		
		// 프로젝트 주소
		String ctxPath = request.getContextPath();
		System.out.println("C : ctxPath(프로젝트 주소) : " + ctxPath);
		
		// 가상주소
		String command = requestURI.substring(ctxPath.length());
		System.out.println("C : command(가상 주소) : " + command);
		
		System.out.println("C : 1. 가상주소 계산 완료 \n\n ");
		
		////////////////////1. 가상주소 계산/////////////////////////////
		
		////////////////////2. 가상주소 매핑/////////////////////////////
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/MyBoardWrite.bo")) {
			System.out.println("C : /MyBoardWrite.bo 호출 ");
			System.out.println("C : DB정보없이 화면(view) 호출");
			
			forward = new ActionForward();
			forward.setPath("./center/myBoardWrite.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/MyBoardWriteAction.bo")) {
			System.out.println("C : /MyBoardWriteAction.bo 호출");
			System.out.println("C : 정보를 전달받아서 DB에 저장");
			
			action = new MyBoardWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/MyBoardList.bo")) {
			System.out.println("C : /MyBoardList.bo 호출 ");
			System.out.println("C : DB정보를 사용해서 view페이지 호출");
			
			action = new MyBoardListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/MyBoardContent.bo")) {
			System.out.println("C : /MyBoardContent.bo 호출");
			System.out.println("C : DB정보를 가져와서 화면에 출력");
			
			action = new MyBoardContentAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MyBoardUpdate.bo")) {
			System.out.println("C : /MyBoardUpdate.bo 호출");
			System.out.println("C : DB정보를 가져와서 화면에 출력");
			
			action = new MyBoardUpdateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MyBoardUpdatePro.bo")) {
			System.out.println("C : /MyBoardUpdatePro.bo 호출");
			System.out.println("C : 전달한 정보를 DB수정 후 리스트페이지 이동");
			
			action = new MyBoardUpdateProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MyBoardDelete.bo")) {
			System.out.println("C : /MyBoardDelete.bo 호출");
			System.out.println("C : DB연결 X, 뷰페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./center/myBoardDelete.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/MyBoardDeleteAction.bo")) {
			System.out.println("C : /MyBoardDeleteAction.bo 호출");
			System.out.println("C : 전달된 정보를 사용해서 DB삭제 후 리스트페이지 이동");
			
			action = new MyBoardDeleteAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MyBoardReWrite.bo")) {
			System.out.println("C : /MyBoardReWrite.bo 호출");
			System.out.println("C : DB연결 X, 뷰페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./center/myBoardReWrite.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/MyBoardReWriteAction.bo")) {
			System.out.println("C : /MyBoardReWriteAction.bo 호출");
			System.out.println("C : 전달된 정보를 DB에 저장 후 리스트페이지 이동");
			
			action = new MyBoardReWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MyBoardFileUploadAction.bo")) {
			System.out.println("C : /MyFileUploadAction.bo 호출");
			System.out.println("C : 전달받은 정보를 DB에 저장(파일 업로드)");
			
			action = new MyBoardFileUploadAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MyBoardFileUpload.bo")) {
			System.out.println("C : /MyBoardFileUpload.bo 호출");
			System.out.println("C : DB사용 없이 페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./center/myBoardFileUpload.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/MyFileDownAction.bo")) {
			System.out.println("C : /MyFileDownAction.bo 호출");
			System.out.println("C : 전달받은 정보를 사용해 다운로드");
			
			action = new MyFileDownAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/AdminBoardList.bo")) {
			System.out.println("C : /AdminBoardList.bo 호출");
			System.out.println("C : DB정보를 사용해서 페이지 호출");
			
			action = new AdminBoardListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("C : 2. 가상주소 매핑 완료\n\n");
		////////////////////2. 가상주소 매핑/////////////////////////////
				
		////////////////////3. 페이지 이동/////////////////////////////
		
		if(forward != null) {
			if(forward.isRedirect()) {
				System.out.println("C : sendRedirect 방식 - " + forward.getPath() + " 이동");
				response.sendRedirect(forward.getPath());
				
			} else {
				System.out.println("C : forward 방식 - " + forward.getPath() + " 이동");
				RequestDispatcher dis = 
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				
			}
			
			System.out.println("C : 3. 페이지 이동 완료 \n\n");
			
		}
		////////////////////3. 페이지 이동/////////////////////////////
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C : MyBoard-doGet() 호출 ");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C : MyBoard-doPost() 호출 ");
		doProcess(request, response);
	}
	

}
