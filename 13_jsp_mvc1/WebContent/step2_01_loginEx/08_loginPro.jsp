<%@page import="step2_00_loginEx.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
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
	
		
		boolean isValidMember = MemberDAO.getInstance().login(id,passwd);
		
		if(isValidMember){
			session.setAttribute("id", id);
			session.setMaxInactiveInterval(60*10); //연결상태 유지를 10분으로 설정
			response.sendRedirect("00_main.jsp");  // sendRedirect("이동경로") : 해당경로 페이지로 이동
		}
		else{
	%>
			<script>
				alert("check your Id and Password!");
				history.go(-1);
			</script>
	<%	
		}
	%>
</body>
</html>