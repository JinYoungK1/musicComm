package com.myproject.music.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AdminAlbumUploadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : AdminAlbumUploadAction_execute() 호출");
		
		String realPath = request.getSession().getServletContext().getRealPath("/albums");
		
		int maxSize = 20 * 1024 * 1024;
		
		MultipartRequest multi = new MultipartRequest(
				request, 
				realPath,
				maxSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		System.out.println("M : 파일 업로드 성공! " + multi.getFilesystemName(realPath));
		ActionForward forward = new ActionForward();
		forward.setPath("./AdminMusicList.mu");
		forward.setRedirect(true);
		
		return forward;
	}

}
