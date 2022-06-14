package com.myproject.gal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.gal.db.GalDAO;
import com.myproject.gal.db.GalDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AdminGalUploadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : AdminGalUploadAction_execute() 호출");
		
		String realPath = request.getSession().getServletContext().getRealPath("/gals");
		
		int maxSize = 20 * 1024 * 1024;
		
		MultipartRequest multi = new MultipartRequest(
				request, 
				realPath,
				maxSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		GalDTO dto = new GalDTO();
		
		dto.setImg_name(multi.getParameter("img_name"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setContent(multi.getParameter("content"));
		
		dto.setFile(multi.getFilesystemName("file"));
		
		dto.setIp(request.getRemoteAddr());
		
		System.out.println("M : 전달받은 정보 저장 완료 - " + dto);
		GalDAO dao = new GalDAO();
		
		dao.insertGal(dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./AdminGalList.ga");
		forward.setRedirect(true);
		
		System.out.println("M : 파일 업로드 글쓰기 완료");
		
		return forward;
	}

}
