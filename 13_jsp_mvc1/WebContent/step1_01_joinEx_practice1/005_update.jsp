<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
</head>
<body>
	<h2>회원정보 수정</h2>
	<form method="post" action="006_updatePro.jsp">
		아이디 : <input type="text" name="id"><br>
		비밀번호 : <input type="password" name="passwd"><br>
		이름 : <input type="text" name="name"><br>
		<input type="submit" value="입력완료">
	</form>

</body>
</html>