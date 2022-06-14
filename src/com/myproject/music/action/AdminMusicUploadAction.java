package com.myproject.music.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.music.db.MusicDAO;
import com.myproject.music.db.MusicDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AdminMusicUploadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : AdminMusicUploadAction_execute() 호출");
		
		String realPath = request.getSession().getServletContext().getRealPath("/musics");
		
		int maxSize = 20 * 1024 * 1024;
		
		MultipartRequest multi = new MultipartRequest(
				request, 
				realPath,
				maxSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		MusicDTO dto = new MusicDTO();
		
		dto.setMusic_name(multi.getParameter("music_name"));
		dto.setArtist_name(multi.getParameter("artist_name"));
		dto.setGenre(multi.getParameter("genre"));
		dto.setAlbum_name(multi.getParameter("album_name"));
		dto.setDate(multi.getParameter("date"));
		
		dto.setFile(multi.getFilesystemName("file"));
		
		dto.setIp(request.getRemoteAddr());
		
		System.out.println("M : 전달받은 정보 저장 완료 - " + dto);
		MusicDAO dao = new MusicDAO();
		
		dao.insertMusic(dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./AdminMusicList.mu");
		forward.setRedirect(true);
		
		System.out.println("M : 파일 업로드 글쓰기 완료");
		
		return forward;
	}

}
