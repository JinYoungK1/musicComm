package com.myproject.music.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.music.db.MusicDAO;
import com.myproject.music.db.MusicDTO;

public class AdminMusicUpLoadDel implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : AdminMusicUpLoadDel_execute() 호출");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		MusicDTO dto = new MusicDTO();
		
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setFile(request.getParameter("file"));
		String fileName = request.getParameter("file");
		String realPath = request.getSession().getServletContext().getRealPath("/musics/");
		
		System.out.println("M : 전달받은 정보 저장 완료! " + dto);
		
		MusicDAO dao = new MusicDAO();
		int result = dao.musicDel(dto);
		
		if(result == 0) {
			return null;
		}
		if(result == -1) {
			return null;
		}
		File file = new File(realPath + fileName);
		
		if(file.exists()) {
			file.delete();
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print("alert('음악 삭제 완료!');");
			out.print("location.href='./AdminMusicList.mu';");
			out.print("</script>");
			out.close();
			return null;
		} 
		
		
		return null;
	}

}
