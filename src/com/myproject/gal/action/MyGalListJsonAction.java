package com.myproject.gal.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.myproject.gal.db.GalDAO;
import com.myproject.gal.db.GalDTO;


public class MyGalListJsonAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MyGalListJsonAction_execute() 호출");
		
		// BoardDAO 객체 생성
		GalDAO dao = new GalDAO();
		
		// 페이징처리
		// 한페이지에 출력할 글의 개수
		int pageSize = 10;
		// 첫 페이지
		String pageNum = "1";
		
		// 시작행 번호 계산하기		1
		int startRow = 1;
				
		// 끝행 번호 계산하기		5
		int endRow = 5;
		
		ArrayList galList = null;
		galList = dao.getGalList(startRow, pageSize);
		System.out.println("M : boardList -" + galList);
		
		// boardList => json 데이터 변경
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < galList.size(); i++) {
			GalDTO dto = (GalDTO) galList.get(i);
			// 글하나 
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("num", dto.getNum());
			jsonObject.put("img_name", dto.getImg_name());
			jsonObject.put("subject", dto.getSubject());
			jsonObject.put("content", dto.getContent());
			jsonObject.put("size", galList.size());
			jsonObject.put("file_name", dto.getFile());
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
