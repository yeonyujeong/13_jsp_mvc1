package step3_00_boardEx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {

	private BoardDAO() {}
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	Connection getConnection() {
		
		String dbUrl = "jdbc:mysql://localhost:3306/step3_board_ex?serverTimezone=UTC";
		String dbId  = "root";
		String dbPwd = "1234";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl , dbId , dbPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// 게시글 생성 DAO
	public void insertBoard(BoardDTO boardDTO) {
		
		try {
			
			conn = getConnection();
			String sql = "INSERT INTO BOARD(WRITER,EMAIL,SUBJECT,PASSWORD,REG_DATE,READ_COUNT,CONTENT)";
				   sql += "VALUES(?,?,?,?,now(),0,?)";	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getWriter());
			pstmt.setString(2, boardDTO.getEmail());
			pstmt.setString(3, boardDTO.getSubject());
			pstmt.setString(4, boardDTO.getPassword());
			pstmt.setString(5, boardDTO.getContent());
			pstmt.executeUpdate();
			
			System.out.println("게시글이 추가되었습니다.");
			System.out.println(boardDTO.getSubject() + "/" + boardDTO.getWriter());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn != null)  try {conn.close();}  catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	// 게시글 조회 DAO
	public ArrayList<BoardDTO> getAllBoard() {
		
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD");
			rs = pstmt.executeQuery();
			
			BoardDTO bdto;
			while(rs.next()) {
				bdto = new BoardDTO();
				bdto.setNum(rs.getInt(1));
				bdto.setWriter(rs.getString(2));
				bdto.setEmail(rs.getString(3));
				boardList.add(bdto);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(rs != null)    try {rs.close();}    catch (SQLException e) {e.printStackTrace();}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn != null)  try {conn.close();}  catch (SQLException e) {e.printStackTrace();}
		}
		
		return boardList;
	}
	
	
	
}
