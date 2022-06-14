package com.myproject.music.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.myproject.music.db.MusicDAO;
import com.myproject.music.db.MusicDTO;


public class MyMusicListJsonAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MyMusicListJsonAction_execute() 호출");
		
		// BoardDAO 객체 생성
		MusicDAO dao = new MusicDAO();
		
		// 페이징처리
		// 한페이지에 출력할 글의 개수
		int pageSize = 15;
		// 첫 페이지
		String pageNum = "1";
		
		// 시작행 번호 계산하기		1
		int startRow = 1;
				
		// 끝행 번호 계산하기		5
		int endRow = 5;
		
		ArrayList musicList = null;
		musicList = dao.getBoardList(startRow, pageSize);
		System.out.println("M : boardList -" + musicList);
		
		// boardList => json 데이터 변경
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < musicList.size(); i++) {
			MusicDTO dto = (MusicDTO) musicList.get(i);
			// 글하나 
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("num", dto.getNum());
			jsonObject.put("album_name", dto.getAlbum_name());
			jsonObject.put("artist_name", dto.getArtist_name());
			jsonObject.put("music_name", dto.getMusic_name());
			jsonObject.put("size", musicList.size());
			// 배열 한칸에 저장
			jsonArray.add(jsonObject);
		}
		// json 웹 데이터 출력
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonArray);
		out.close();
		
		return null;
	}

}
