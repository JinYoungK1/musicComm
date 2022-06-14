package com.myproject.gal.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyGalFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C : MyGal-doProcess() 호출 \n\n\n");
		
		////////////////////1. 가상주소 계산/////////////////////////////
		
		// 매핑된 주소
		String requestURI = request.getRequestURI();
		System.out.println("C : requestURI(매핑된 주소) : " + requestURI);
		// 프로젝트 주소
		String ctxPath = request.getContextPath();
		System.out.println("C : ctxPath(프로젝트 주소) : " + ctxPath);
		// 가상 주소
		String command = requestURI.substring(ctxPath.length());
		System.out.println("C : command(가상주소) : " + command);
		
		System.out.println("C : 1. 가상주소 계산 완료 \n\n");
		
		////////////////////1. 가상주소 계산/////////////////////////////
		
		////////////////////2. 가상주소 매핑/////////////////////////////
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/GalList.ga")) {
			System.out.println("C : /GalList.ga 호출");
			System.out.println("C : DB를 사용해서 페이지 호출");
			
			action = new GalListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/AdminGalList.ga")) {
			System.out.println("C : /AdminGalList.ga 호출");
			System.out.println("C : DB를 사용해서 페이지 호출");
			
			action = new AdminGalListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/AdminGalUploadPage.ga")) {
			System.out.println("C : /AdminGalUploadPage.ga 호출");
			System.out.println("C : DB를 사용하지 않고 페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./center/adminGalUpload.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/AdminGalUpload.ga")) {
			System.out.println("C : /AdminGalUpload.ga 호출");
			System.out.println("C : 전달받은 정보를 DB에 저장(파일업로드)");
			
			action = new AdminGalUploadAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/AdminGalUpLoadDel.ga")) {
			System.out.println("C : /AdminGalUpLoadDel.ga 호출");
			System.out.println("C : 전달받은 정보를 사용하여 DB삭제 후 페이지 호출");
			
			action = new AdminGalUpLoadDel();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MyGalDownAction.ga")) {
			System.out.println("C : /MyGalDownAction.ga 호출");
			System.out.println("C : 전달받은 정보를 사용해 다운로드");
			
			action = new MyGalDownAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MyGalListJson.ga")) {
			System.out.println("C : /MyGalListJson.ga 호출");
			action = new MyGalListJsonAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("C : 2. 가상주소 매핑 완료\n\n");
		////////////////////2. 가상주소 매핑/////////////////////////////
		
		////////////////////3. 페이지 이둥/////////////////////////////
		
		if(forward != null) {
			if(forward.isRedirect()) {
				System.out.println("C : sendRedirect방식 - " + forward.getPath() + "로 이동");
				response.sendRedirect(forward.getPath());
			} else {
				System.out.println("C : forward방식 - " + forward.getPath() + "로 이동");
				RequestDispatcher dis = 
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
			
			System.out.println("C : 3. 페이지 이동 완료!\n\n");
		}
		
		////////////////////3. 페이지 이동/////////////////////////////
		
		
		
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C : MyGal-doGet() 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C : MyGal-doPost() 호출");
		doProcess(request, response);
	}
	
}
