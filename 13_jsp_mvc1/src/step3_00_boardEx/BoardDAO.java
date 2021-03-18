package step3_00_boardEx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BoardDAO {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
				bdto.setNum(rs.getInt(1));						//rs.getInt("num");
				bdto.setWriter(rs.getString(2));				//rs.getString("writer")
				bdto.setEmail(rs.getString(3));					//rs.getString("email")
				bdto.setSubject(rs.getString(4));				//rs.getString("subject")
				bdto.setPassword(rs.getString(5)); 				//rs.getString("password")
				bdto.setRegDate(sdf.format(rs.getDate(6))); 	//sdf.format(rs.getDate("reg_date"))
				bdto.setReadCount(rs.getInt(7));				//rs.getInt("read_count")
				bdto.setContent(rs.getString(8));				//rs.getString("content")
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
	
	// 하나의 게시글을 조회하는 DAO
	public BoardDTO getOneBoard(int bNum) {
		
		BoardDTO bdto = new BoardDTO();
		
		try {
			
			conn = getConnection();
			// 조회수 1 올리기
			pstmt = conn.prepareStatement("UPDATE BOARD SET READ_COUNT = READ_COUNT+1 WHERE NUM=?");
			pstmt.setInt(1, bNum);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM=?");
			pstmt.setInt(1, bNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				bdto.setNum(rs.getInt(1));
				bdto.setWriter(rs.getString(2));
				bdto.setEmail(rs.getString(3));
				bdto.setSubject(rs.getString(4));
				bdto.setRegDate(sdf.format(rs.getDate(6)));
				bdto.setReadCount(rs.getInt(7));
				bdto.setContent(rs.getString(8));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(rs != null)    try {rs.close();}    catch (SQLException e) {e.printStackTrace();}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn != null)  try {conn.close();}  catch (SQLException e) {e.printStackTrace();}
		}
		
		return bdto;
	}
	
	
	// 하나의 게시글을 조회하는 DAO (조회수 증가x)
	public BoardDTO getOneUpdateBoard(int bNum) {
		
		BoardDTO bdto = new BoardDTO();
		
		try {
			
			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM=?");
			pstmt.setInt(1, bNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				bdto.setNum(rs.getInt(1));
				bdto.setWriter(rs.getString(2));
				bdto.setEmail(rs.getString(3));
				bdto.setSubject(rs.getString(4));
				bdto.setRegDate(sdf.format(rs.getDate(6)));
				bdto.setReadCount(rs.getInt(7));
				bdto.setContent(rs.getString(8));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(rs != null)    try {rs.close();}    catch (SQLException e) {e.printStackTrace();}
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn != null)  try {conn.close();}  catch (SQLException e) {e.printStackTrace();}
		}
		
		return bdto;
	}
	
	
}
