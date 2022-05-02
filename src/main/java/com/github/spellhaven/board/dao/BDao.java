package com.github.spellhaven.board.dao;
import java.sql.*;
import java.util.ArrayList;

import com.github.spellhaven.board.dto.BDto;

public class BDao {
	
	static String driverName = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/webdb";
	static String user = "root";
	static String password = "12345";
	
	
	// DB에 직접 넣어 주는 write 메소드를 만들어 주자. 여기가 DAO 안이라는 걸 잘 기억해봐. 최종 단계임 ㅋ
	// 조회수 bhit은 유저가 입력한 게 아니고 알아서 만들어질 거다. 그래서 나머지 네 argument만 직접입력으로 받음 ㅋ
	public void write(String bid, String bname, String btitle, String bcontent) {
		
		String sql = "INSERT INTO jsp_board(bid, bname, btitle, bcontent, bhit) VALUES('" + bid + "','" + bname + "','" + btitle + "','" + bcontent + "', 0)";
		
		int dbFlag = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;//sql 실행 객체
		
		try {
			Class.forName(driverName);//jdbc 드라이버 로딩
			conn = DriverManager.getConnection(url, user, password);//DB 연동			
			pstmt = conn.prepareStatement(sql);
			
			dbFlag = pstmt.executeUpdate();//sql실행->실행 성공시 1 반환
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}			
			
		}
		
	}
	
	
	
	// 글 목록을 불러오는 함수의 리턴형이 뭐여야 할까? ArrayList여야 한다는 걸 맞췄디 ㅋ 
	// (우와. 예전에 배운 개념인데도 맞췄다, 크킄. 크킄. 자랑스럽다. 크킄. 하하. 크킄. 하하.)
	// 어. 근데 <BDto> 넣어야 되는 건 몯 맞춴내 ㅋ ㅋ ㅋ 크킄 ArrayList 헫깔려. 크킄 크킄.
	public ArrayList<BDto> list() {
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		// L73~의 while문 안에서 dtos 안에 dto들을 10개씩 넣을 거야 잘 봐 ㅋ
		
		String sql = "SELECT * FROM jsp_board";
			
			Connection conn = null;
			PreparedStatement pstmt = null;//sql 실행 객체
			ResultSet rs = null;
			
			try {
				Class.forName(driverName);//jdbc 드라이버 로딩
				conn = DriverManager.getConnection(url, user, password);//DB 연동			
				pstmt = conn.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) { // 이 루프 조건 기억나지? "rs 안의 데이터 개수만큼만 돌려라"
					String bid = rs.getString("bid");
					String bname = rs.getString("bname");
					String btitle = rs.getString("btitle");
					String bcontent = rs.getString("bcontent");
					int bhit = rs.getInt("bhit");
					
					BDto dto = new BDto(bid, bname, btitle, bcontent, bhit);
					dtos.add(dto);
					
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null) {
						rs.close();
					}
					if(pstmt != null) {
						pstmt.close();
					}
					if(conn != null) {
						conn.close();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}			
				
			}
			
			
			return dtos;
		}
		
	
	
	// 이미 작성된 글을 클릭하면 내용을 보게 해 주는 놈, content_view를 만들어 보자.
	// 글 '목록'과 다르게, 글 한 놈에 해당하는 것만 오면 된다. 그래서 리턴형이 ArrayList가 아닌 그냥 BDto다.
	public BDto content_view(String str_id) {
		
		BDto dto = null;
		
		String sql = "SELECT * FROM jsp_board WHERE bid = ?"; 
		
		Connection conn = null;
		PreparedStatement pstmt = null;//sql 실행 객체
		ResultSet rs = null;
		
		try {
			Class.forName(driverName);//jdbc 드라이버 로딩
			conn = DriverManager.getConnection(url, user, password);//DB 연동			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, str_id);
			
		
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // "rs 안에 데이터가 하나 있으면 그 글을 반환해라". Content view는 구조상 루프가 아니라 그냥 글 하나 보여 주는 놈이니까.
				String bid = rs.getString("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				int bhit = rs.getInt("bhit");
				
				dto = new BDto(bid, bname, btitle, bcontent, bhit);
				// 아까와 달리 dtos.add(dto); 이런 거 할 필요 없다. 글 "하나만 본다니까"		
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}			
			
		}
			
		return dto;
	}
	
	
	// 글 수정해 주는 놈 만들자. String bid는 유저가 변경하는 값으로 만들지 않았지만, Primary Key라서 넣어야 한다.
	// 않 그러면 ㅇㄴ 대체 글을 수정하긴 하는데 무슨 글을 수정해야 하냐고?? <= 일케됨.
	public void modify(String bid, String bname, String btitle, String bcontent) {
	
		String sql = "UPDATE jsp_board SET bname = ?, btitle = ?, bcontent = ? WHERE bid = ?";
		
		int dbFlag = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;//sql 실행 객체
		
		try {
			Class.forName(driverName);//jdbc 드라이버 로딩
			conn = DriverManager.getConnection(url, user, password);//DB 연동			
			pstmt = conn.prepareStatement(sql);
			
			// String sql의 4개의 ?를 순서대로 채워 주자. 그래서 bid가 4번째 순서다 유의하자 ㅋ
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.setString(4, bid);			
			
			dbFlag = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}			
			
		}
		
		
		
	}
	
}

