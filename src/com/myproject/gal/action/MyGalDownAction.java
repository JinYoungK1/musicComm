package com.myproject.gal.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyGalDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MyGalDownAction_execute() 호출");
		
		request.setCharacterEncoding("UTF-8");
		String path = "gals";
		String downPath = request.getServletContext().getRealPath(path);
		
		String fileName = request.getParameter("file_name");
		String savePath = downPath + "\\" + fileName;
		
		File file = new File(savePath);
		response.setContentType("application/download");
		response.setContentLengthLong(file.length());
		
		fileName = URLEncoder.encode(fileName, "UTF-8".replace("+", "%20").replace("(", "%28").replace(")", "%29"));
		
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
		
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;
		
		try {
			int temp;
			fis = new FileInputStream(file);
			while((temp = fis.read()) != -1) {
				out.write(temp);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(fis != null) {
				try {
					fis.close();
				} catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return null;
	}

}
