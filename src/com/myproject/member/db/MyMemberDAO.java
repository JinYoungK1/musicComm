package com.myproject.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myproject.board.db.MyBoardDTO;

public class MyMemberDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	// getCon()
	private Connection getCon() throws Exception {
		
		Context initCTX = new InitialContext();
		
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/MySingleProject");
		con = ds.getConnection();
		System.out.println("DAO : 디비 연결 완료!");
		
		return con;
	} // getCon()
	
	// CloseDB()
	public void CloseDB() {
		
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // CloseDB()
	
	// insertMember(dto)
	public void insertMember(MyMemberDTO dto) {
		
		System.out.println("DAO : insertMember(dto) 호출 ");
		
		try {
			con = getCon();
			
			sql = "insert into my_members(id,pass,email,zipcode,address,phone,date) values(?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPass());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getZipcode());
			pstmt.setString(5, dto.getAddress());
			pstmt.setString(6, dto.getPhone());
			pstmt.setTimestamp(7, dto.getDate());
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 회원가입 완료! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		System.out.println("DAO : insertMember(dto) 끝!!!!!!!!!!");
		
	} // insertMember(dto)

	// login(MyMemberDTO dto)
	public int login(MyMemberDTO dto) {
		int result = -1;
		try {
			con = getCon();
			sql = "select pass from my_members where id = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(dto.getPass().equals(rs.getString("pass"))) {
					result = 1;
				} else {
					result = 0;
				}			
			} else {
				result = -1;
			}
			
			System.out.println("DAO : 로그인 체크 완료 = " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return result;
	} // login(MyMemberDTO dto)

	// memberInfo(String id)
	public MyMemberDTO memberInfo(String id) {
		System.out.println("DAO : memberInfo() 호출");
		MyMemberDTO dto = null;
		
		try {
			con = getCon();
			sql = "select * from my_members where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new MyMemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setEmail(rs.getString("email"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddress(rs.getString("address"));
				dto.setPhone(rs.getString("phone"));
				dto.setDate(rs.getTimestamp("date"));
				
			}
			System.out.println("DAO : 회원 한명 정보 저장 완료 :" + dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return dto;
	} // memberInfo(String id)

	// memberUpdate(MyMemberDTO dto)
	public int memberUpdate(MyMemberDTO dto) {
		int result = -1;
		
		try {
			con = getCon();
			sql = "select pass from my_members where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getPass().equals(rs.getString("pass"))) {
					sql = "update my_members set phone = ?, email = ?, zipcode = ?, address = ? where id = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getPhone());
					pstmt.setString(2, dto.getEmail());
					pstmt.setString(3, dto.getZipcode());
					pstmt.setString(4, dto.getAddress());
					pstmt.setString(5, dto.getId());
					pstmt.executeUpdate();
					
					result = 1;
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}
			System.out.println("DAO : 회원정보 업데이트 완료 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return result;
	} // memberUpdate(MyMemberDTO dto)

	public int memberDel(MyMemberDTO dto) {
		int result = -1;
		
		try {
			con = getCon();
			sql = "select pass from my_members where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getPass().equals(rs.getString("pass"))) {
					sql = "delete from my_members where id = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getId());
					pstmt.executeUpdate();
					result = 1;
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}
			System.out.println("M : 회원정보 삭제 완료 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return result;
	}

	public int getMemberCount() {
		int result = 0;
		
		try {
			con = getCon();
			sql = "select count(*) from my_members";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			System.out.println("DAO : 총 멤버 수 " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return result;
	}

	public ArrayList getMemberList(int startRow, int pageSize) {
		ArrayList memberList = new ArrayList();
		
		try {
			con = getCon();
			sql = "select * from my_members order by date desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow -1);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MyMemberDTO dto = new MyMemberDTO();
				
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddress(rs.getString("address"));
				dto.setDate(rs.getTimestamp("date"));
				
				memberList.add(dto);
				
			}
			
			System.out.println("DAO : 유저정보 저장 완료!(List) " + memberList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return memberList;
	}
	

}
