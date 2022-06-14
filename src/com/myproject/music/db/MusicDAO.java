package com.myproject.music.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MusicDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private String sql = "";
	private ResultSet rs = null;
	
	private Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/MySingleProject");
		con = ds.getConnection();
		
		System.out.println("DAO : 디비 연결 완료");
		return con;
	}
	
	public void CloseDB() {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	} // 자원 해제

	// getBoardCount()
	public int getBoardCount() {
		int result = 0;
		
		try {
			con = getCon();
			sql = "select count(*) from my_music";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			System.out.println("DAO : 총 개수 - " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return result;
	} // getBoardCount()

	// getBoardList(int startRow, int pageSize)
	public ArrayList getBoardList(int startRow, int pageSize) {
		ArrayList musicBoardList = new ArrayList();
		
		try {
			con = getCon();
			sql = "select * from my_music order by re_ref desc, re_seq asc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow -1);
			pstmt.setInt(2, pageSize);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MusicDTO dto = new MusicDTO();
				
				dto.setAlbum_name(rs.getString("album_name"));
				dto.setArtist_name(rs.getString("artist_name"));
				dto.setDate(rs.getString("date"));
				dto.setFile(rs.getString("file"));
				dto.setGenre(rs.getString("genre"));
				dto.setMusic_name(rs.getString("music_name"));
				dto.setNum(rs.getInt("num"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setIp(rs.getString("ip"));
				
				musicBoardList.add(dto);
				
			}
			System.out.println("DAO : 음악정보 저장 완료(List)");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return musicBoardList;
	} // getBoardList(int startRow, int pageSize)

	// insertMusic(MusicDTO dto)
	public void insertMusic(MusicDTO dto) {
		int bno = 0;
		
		try {
			con = getCon();
			
			sql = "select max(num) from my_music";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bno = rs.getInt(1) + 1;
			}
			System.out.println("DAO : 글번호 ( " + bno + " )");
			
			sql = "insert into my_music value(?,?,?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setString(2, dto.getMusic_name());
			pstmt.setString(3, dto.getArtist_name());
			pstmt.setString(4, dto.getGenre());
			pstmt.setString(5, dto.getAlbum_name());
			
			pstmt.setInt(6, 0);
			pstmt.setInt(7, bno);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			
			pstmt.setString(10, dto.getDate());
			pstmt.setString(11, dto.getIp());
			pstmt.setString(12, dto.getFile());
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 음악 추가 완료! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
	} // insertMusic(MusicDTO dto)

	public int musicDel(MusicDTO dto) {
		int result = -1;
		
		try {
			con = getCon();
			sql = "select file from my_music where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getFile().equals(rs.getString("file"))) {
					sql = "delete from my_music where num = ?";
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
			CloseDB();
		}
		
		return result;
	}

	public ArrayList getChartList(int startRow, int pageSize) {
		ArrayList musicBoardList = new ArrayList();
		
		try {
			con = getCon();
			sql = "select * from my_music order by readcount desc, re_seq asc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow -1);
			pstmt.setInt(2, pageSize);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MusicDTO dto = new MusicDTO();
				
				dto.setAlbum_name(rs.getString("album_name"));
				dto.setArtist_name(rs.getString("artist_name"));
				dto.setDate(rs.getString("date"));
				dto.setFile(rs.getString("file"));
				dto.setGenre(rs.getString("genre"));
				dto.setMusic_name(rs.getString("music_name"));
				dto.setNum(rs.getInt("num"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setIp(rs.getString("ip"));
				
				musicBoardList.add(dto);
				
			}
			System.out.println("DAO : 음악정보 저장 완료(List)");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return musicBoardList;
	}

	// updateReadCount(int num)
	public void updateReadCount(int num) {
		try {
			con = getCon();
			sql = "update my_music set readcount = readcount + 1 where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			System.out.println("DAO : 추천수 1 증가 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
	} // updateReadCount(int num)

	public MusicDTO getMusicBoard(int num) {
		MusicDTO dto = null;
		
		try {
			con = getCon();
			sql = "select * from my_music where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new MusicDTO();
				
				dto.setAlbum_name(rs.getString("album_name"));
				dto.setArtist_name(rs.getString("artist_name"));
				dto.setDate(rs.getString("date"));
				dto.setFile(rs.getString("file"));
				dto.setGenre(rs.getString("genre"));
				dto.setIp(rs.getString("ip"));
				dto.setMusic_name(rs.getString("music_name"));
				dto.setNum(rs.getInt(rs.getInt("num")));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setReadcount(rs.getInt("readcount"));
				
			}
			
			System.out.println("DAO : DTO 1개 정보 저장 완료! " + dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return dto;
	}

	public ArrayList getSearchList(int pageSize, String mInfo, String search) {
		ArrayList musicBoardList = new ArrayList();
		
		try {
			con = getCon();
			sql = "select * from my_music where ? like '%?%' order by re_ref desc, re_seq asc limit 0,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mInfo);
			pstmt.setString(2, search);
			pstmt.setInt(3, pageSize);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MusicDTO dto = new MusicDTO();
				
				dto.setAlbum_name(rs.getString("album_name"));
				dto.setArtist_name(rs.getString("artist_name"));
				dto.setDate(rs.getString("date"));
				dto.setFile(rs.getString("file"));
				dto.setGenre(rs.getString("genre"));
				dto.setMusic_name(rs.getString("music_name"));
				dto.setNum(rs.getInt("num"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setIp(rs.getString("ip"));
				
				musicBoardList.add(dto);
				
			}
			System.out.println("DAO : 음악정보 저장 완료(List)");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return musicBoardList;
	}
	
}
