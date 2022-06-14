package com.myproject.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MyBoardDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private String sql = "";
	private ResultSet rs = null;
	
	private Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/MySingleProject");
		con = ds.getConnection();
		
		System.out.println("DAO : 디비 연결 완료!");
		return con;
	} // 디비 연결
	
	public void CloseDB() {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	} // 자원 해제

	// insertBoard(MyBoardDTO dto)
	public void insertBoard(MyBoardDTO dto) {
		int bno = 0;
		
		try {
			con = getCon();
			
			sql = "select max(num) from my_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bno = rs.getInt(1) + 1;
			}
			System.out.println("DAO : 글번호 ( " + bno + " )");
			
			sql = "insert into my_board value(?,?,?,?,?,?,?,?,?,now(),?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPass());
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getContent());
			
			pstmt.setInt(6, 0);
			pstmt.setInt(7, bno);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			
			pstmt.setString(10, dto.getIp());
			pstmt.setString(11, dto.getFile());
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 글쓰기 완료! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
	} // insertBoard(MyBoardDTO dto)

	// getBoardCount()
	public int getBoardCount() {
		int result = 0;
		
		try {
			con = getCon();
			
			sql = "select count(*) from my_board";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			System.out.println("DAO : 총 개수 " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return result;
	} // getBoardCount()

	// getBoardList(int startRow, int pageSize)
	public ArrayList getBoardList(int startRow, int pageSize) {
		ArrayList boardList = new ArrayList();
		
		try {
			con = getCon();
			sql = "select * from my_board order by re_ref desc, re_seq asc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow - 1);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MyBoardDTO dto = new MyBoardDTO();
				
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setFile(rs.getString("file"));
				dto.setIp(rs.getString("ip"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getInt("num"));
				dto.setPass(rs.getString("pass"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setSubject(rs.getString("subject"));
				
				boardList.add(dto);
				
			}
			
			System.out.println("DAO : 글정보 저장 완료!(List) ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return boardList;
	} // getBoardList(int startRow, int pageSize)

	// updateReadCount(int num)
	public void updateReadCount(int num) {
		try {
			con = getCon();
			sql = "update my_board set readcount = readcount + 1 where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			System.out.println("DAO : 조회수 1증가 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
	} // updateReadCount(int num)

	// getBoard(int num)
	public MyBoardDTO getBoard(int num) {
		MyBoardDTO dto = null;
		
		try {
			con = getCon();
			sql = "select * from my_board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new MyBoardDTO();
				
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setFile(rs.getString("file"));
				dto.setIp(rs.getString("ip"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getInt("num"));
				dto.setPass(rs.getString("pass"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setSubject(rs.getString("subject"));
			}
			System.out.println("DAO DTO 1개정보: " + dto);
			System.out.println("DAO : 게시판 글 정보 저장 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return dto;
	} // getBoard(int num)

	// updateBoard(MyBoardDTO dto)
	public int updateBoard(MyBoardDTO dto) {
		int result = -1;
		
		try {
			con = getCon();
			sql = "select pass from my_board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getPass().equals(rs.getString("pass"))) {
					sql = "update my_board set name = ?, subject = ?, content = ?, file = ? where num = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getName());
					pstmt.setString(2, dto.getSubject());
					pstmt.setString(3, dto.getContent());
					pstmt.setString(4, dto.getFile());
					pstmt.setInt(5, dto.getNum());
					
					pstmt.executeUpdate();
					
					result = 1;
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}
			System.out.println("DAO : 회원 정보 수정 완료 (" + result + ")");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return result;
	} // updateBoard(MyBoardDTO dto)

	// deleteBoard(MyBoardDTO dto)
	public int deleteBoard(MyBoardDTO dto) {
		int result = -1;
		
		try {
			con = getCon();
			sql = "select * from my_board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(dto.getNum() == rs.getInt("re_ref")) {
					if(dto.getPass().equals(rs.getString("pass"))) {
						sql = "delete from my_board where re_ref = ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, rs.getInt("re_ref"));
						pstmt.executeUpdate();
						
						result = 1;
					} else {
						result = 0;
					}
				} else {
				if(dto.getPass().equals(rs.getString("pass"))) {
					sql = "delete from my_board where num = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, dto.getNum());
					pstmt.executeUpdate();
					
					result = 1;
				} else {
					result = 0;
				}
				}
			} else {
				result = -1;
			}
			
			System.out.println("DAO : 정보 삭제 완료 result : (" + result + ")");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return result;
	} // deleteBoard(MyBoardDTO dto)

	public void reWriteBoard(MyBoardDTO dto) {
		int bno = 0;
		
		try {
			con = getCon();
			sql = "select max(num) from my_board";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bno = rs.getInt(1) + 1;
			}
			System.out.println("DAO : 답글번호 : " + bno);
			
			sql = "update my_board set re_seq = re_seq + 1 where re_ref = ? and re_seq > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getRe_ref());
			pstmt.setInt(2, dto.getRe_seq());
			
			int tmp = pstmt.executeUpdate();
			
			if(tmp > 0) {
				System.out.println("DAO : 답글순서 재배치 완료");
			}
			
			sql = "insert into my_board (num,name,pass,subject,content,readcount,re_ref,re_lev,re_seq,date,ip,file) value(?,?,?,?,?,?,?,?,?,now(),?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPass());
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getContent());
			pstmt.setInt(6, 0);
			pstmt.setInt(7, dto.getRe_ref());
			pstmt.setInt(8, dto.getRe_lev() + 1);
			pstmt.setInt(9, dto.getRe_seq() + 1);
			pstmt.setString(10, dto.getIp());
			pstmt.setString(11, dto.getFile());
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 답글 작성 완료");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
	}
	
	
}
