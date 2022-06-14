package com.myproject.gal.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GalDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private String sql = "";
	private ResultSet rs = null;
	
	private Connection getCon() throws Exception{
		Context initCtx = new InitialContext();
		DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/MySingleProject");
		con = ds.getConnection();
		
		System.out.println("DAO : 디비 연결 완료!");
		return con;
	} // 디비 연결
	
	public void closeDB() {
			try {
				if(rs != null)	rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	} // 자원 해제

	public int getGalCount() {
		int result = 0;
		
		try {
			con = getCon();
			sql = "select count(*) from my_galboard";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			System.out.println("DAO : 총 개수 " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}

	public ArrayList getGalList(int startRow, int pageSize) {
		ArrayList galList = new ArrayList();
		
		try {
			con = getCon();
			sql = "select * from my_galboard order by re_ref desc, re_seq asc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow - 1);
			pstmt.setInt(2, pageSize);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GalDTO dto = new GalDTO();
				
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setFile(rs.getString("file"));
				dto.setImg_name(rs.getString("img_name"));
				dto.setIp(rs.getString("ip"));
				dto.setNum(rs.getInt("num"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setSubject(rs.getString("subject"));
				
				galList.add(dto);
				
			}
			System.out.println("DAO : 글정보 저장 완료 " + galList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return galList;
	}

	public void insertGal(GalDTO dto) {
		int bno = 0;
		
		try {
			con = getCon();
			
			sql = "select max(num) from my_galboard";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bno = rs.getInt(1) + 1;
			}
			System.out.println("DAO : 글번호 ( " + bno + " )");
			
			sql = "insert into my_galboard(num,img_name,subject,content,readcount,re_ref,re_lev,re_seq,date,ip,file) value(?,?,?,?,?,?,?,?,now(),?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setString(2, dto.getImg_name());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			
			pstmt.setInt(5, 0);
			pstmt.setInt(6, bno);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			
			pstmt.setString(9, dto.getIp());
			pstmt.setString(10, dto.getFile());
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 글쓰기 완료! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	}

	public int galDel(GalDTO dto) {
		int result = -1;
		
		try {
			con = getCon();
			sql = "select file from my_galboard where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getFile().equals(rs.getString("file"))) {
					sql = "delete from my_galboard where num = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, dto.getNum());
					pstmt.executeUpdate();
					result = 1;
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}
			System.out.println("DAO : 음악정보 삭제 완료! " + result);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
}
