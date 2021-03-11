<%@page import="java.text.SimpleDateFormat"%>
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
		
			Class.forName("com.mysql.jdbc.Driver");
			
			String jdbcUrl = "jdbc:mysql://localhost:3306/login_ex?serverTimezone=UTC";
			String dbId    = "root";
			String dbPass  = "1234";
			
			conn = DriverManager.getConnection(jdbcUrl , dbId , dbPass);
			
			pstmt = conn.prepareStatement("select * from member");
			rs = pstmt.executeQuery();
			
	%>
			<table border="1">
				<tr>
					<th>ID</th>
					<th>PASSWORD</th>
					<th>NAME</th>
					<th>JOIN DATE</th>
				</tr>
	<%		
			while(rs.next()){
				String rId = rs.getString("id");
				String rPasswd = rs.getString("passwd");
				String rName = rs.getString("name");
				String rJoindate = sdf.format(rs.getTimestamp("joindate"));

	%>
				<tr>
					<td><%=rId%></td>
					<td><%=rPasswd%></td>
					<td><%=rName%></td>
					<td><%=rJoindate%></td>				
				</tr>
	<%
				
			}
	%>
			</table>
	<%		
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if(rs    != null) try {rs.close();}    catch (Exception e){}
			if(pstmt != null) try {pstmt.close();} catch (Exception e){}
			if(conn  != null) try {conn.close();}  catch (Exception e){}				
		}
		
	
	
	%>

</body>
</html>