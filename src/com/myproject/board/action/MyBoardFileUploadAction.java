package com.myproject.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.board.db.MyBoardDAO;
import com.myproject.board.db.MyBoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MyBoardFileUploadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MyBoardFileUploadAction_execute 호출");
		
		String realPath = request.getSession().getServletContext().getRealPath("/uploads");
		
		int maxSize = 20 * 1024 * 1024;
		
		MultipartRequest multi = new MultipartRequest(
				request, 
				realPath,
				maxSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		MyBoardDTO dto = new MyBoardDTO();
		
		dto.setName(multi.getParameter("name"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setContent(multi.getParameter("content"));
		dto.setPass(multi.getParameter("pass"));
		
		dto.setFile(multi.getFilesystemName("file"));
		
		dto.setIp(request.getRemoteAddr());
		
		System.out.println("M : 전달받은 정보 저장 완료 - " + dto);
		MyBoardDAO dao = new MyBoardDAO();
		
		dao.insertBoard(dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./MyBoardList.bo");
		forward.setRedirect(true);
		
		System.out.println("M : 파일 업로드 글쓰기 완료");
		
		return forward;
	}

}
