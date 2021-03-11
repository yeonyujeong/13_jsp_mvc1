<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		request.setCharacterEncoding("utf-8");
	
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String jdbUrl = "jdbc:mysql://localhost:3306/login_ex?serverTimezone=UTC";
			String dbId = "root";
			String dbPw = "1234";
			
			conn = DriverManager.getConnection(jdbUrl , dbId , dbPw);
			
			pstmt = conn.prepareStatement("select id,passwd from member where id=? and passwd=?");
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				pstmt = conn.prepareStatement("delete from member where id=?");
				pstmt.setString(1, id);
				pstmt.executeUpdate();
				
		%>
				<script>
					alert("탈퇴되었습니다.");
					location.href="100_main.jsp";
				</script>
		<%		
			}else{
		%>		
				<script>
					alert("아이디와 비밀번호를 확인해주세요.");
					history.go(-1);
				</script>
		<%		
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (Exception e){}
			if(pstmt != null) try {pstmt.close();} catch (Exception e){}
			if(conn != null)  try {conn.close();} catch (Exception e){}		
		}
		
		
	
	
	
	
	%>
	

</body>
</html>