package com.myproject.music.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MusicController extends HttpServlet{

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C : MusicBoard-doProcess() 호출  \n\n\n");
		////////////////////1. 가상주소 계산/////////////////////////////
		
		// 매핑된 주소
		String requestURI = request.getRequestURI();
		// 프로젝트 주소
		String ctxPath = request.getContextPath();
		// 가상 주소
		String command = requestURI.substring(ctxPath.length());
		
		System.out.println("C : 1. 가상 주소 계산 완료 \n\n" + command);
		
		////////////////////1. 가상주소 계산/////////////////////////////
		
		////////////////////2. 가상주소 매핑/////////////////////////////
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/MusicBoardList.mu")) {
			System.out.println("C : /MusicBoardList.mu 호출");
			System.out.println("C : DB정보를 사용해서 뷰페이지 호출");
			
			action = new MusicBoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MusicPlayer.mu")) {
			System.out.println("C : /MusicPlayer.mu 호출");
			
			forward = new ActionForward();
			forward.setPath("./inc/jpSc.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/AdminMusicList.mu")) {
			System.out.println("C : /AdminMusicList.mu 호출");
			System.out.println("C : DB정보를 사용해서 페이지 호출");
			
			action = new AdminMusicListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/AdminMusicUploadPage.mu")) {
			System.out.println("C : /AdminMusicUpload.mu 호출");
			System.out.println("C : DB를 사용하지 않고 페이지 호출");
			
			forward = new ActionForward();
			forward.setPath("./center/adminMusicUpload.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/AdminMusicUploadAction.mu")) {
			System.out.println("C : /AdminMusicUploadAction.mu 호출");
			System.out.println("C : 전달받은 정보를 DB에 저장(파일업로드)");
			
			action = new AdminMusicUploadAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/AdminMusicUpLoadDel.mu")) {
			System.out.println("C : /AdminMusicUpLoadDel.mu 호출");
			System.out.println("C : 전달받은 정보를 DB삭제후 페이지 호출");
			
			action = new AdminMusicUpLoadDel();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MyMusicDownAction.mu")) {
			System.out.println("C : /MyMusicDownAction.mu 호출");
			System.out.println("C : 전달받은 정보를 사용하여 다운로드");
			
			action = new MyMusicDownAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MyMusicListJson.mu")) {
			System.out.println("C : /MyMusicListJson.mu 호출");
			System.out.println("C : DB를 사용해서 Json 글 가져오기");
			
			action = new MyMusicListJsonAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MyBoardMain.mu")) {
			System.out.println("C : /MyBoardMain.mu 호출");
			System.out.println("C : DB정보를 사용하지 않고 페이지 호출");
			
			forward = new ActionForward();
			forward.setPath("./main/main.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/AdminAlbumUploadAction.mu")) {
			System.out.println("C : /AdminAlbumUploadAction.mu 호출");
			System.out.println("C : 파일업로드후 페이지 호출");
			
			action = new AdminAlbumUploadAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/AdminAlbumUpload.mu")) {
			System.out.println("C : /AdminAlbumUpload.mu 호출");
			System.out.println("C : DB를 사용하지 않고 페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./center/adminAlbumUpload.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/MyMusicChartJson.mu")) {
			System.out.println("C : /MyMusicChartJson.mu 호출");
			
			action = new MyMusicChartJsonAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MyMusicCountAction.mu")) {
			System.out.println("C : /MyMusicCountAction.mu 호출");
			System.out.println("C : DB를 사용해 카운트 1증가 후 페이지 호출");
			
			action = new MyMusicCountAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MusicSearchList.mu")) {
			System.out.println("C : /MusicSearchList.mu 호출");
			
			action = new MusicSearchListAction();
			
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
			
			System.out.println("C : 3.페이지 이동 완료 \n\n");
		}
		
		////////////////////3. 페이지 이동/////////////////////////////
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C : MusicBoard-doGet() 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C : MusicBoard-doPost() 호출");
		doProcess(request, response);
	}

}
