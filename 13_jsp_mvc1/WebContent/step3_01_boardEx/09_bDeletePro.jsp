<%@page import="step3_00_boardEx.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09_bDeletePro</title>
</head>
<body>

	<%
		request.setCharacterEncoding("utf-8");
	%>

	<jsp:useBean id="boardDTO" class="step3_00_boardEx.BoardDTO">
		<jsp:setProperty name="boardDTO" property="*"/>
	</jsp:useBean>
	
	<%
		boolean isDelete = BoardDAO.getInstance().deleteBoard(boardDTO);
	
		if(isDelete){
	%>
			<script>
				alert("삭제되었습니다.");
				locatin.href="04_bList.jsp";
			</script>		
	<%		
		}else{
	%>
			<script>
				alert("비밀번호가 틀렸습니다.");
				history.go(-1);
			</script>
	<%		
		}
	%>
	
</body>
</html>