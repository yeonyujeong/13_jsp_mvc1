<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 
	
		# 데이터 베이스 연동 방법 
		
		1) mysql_connector.jar파일을 WEB-INF안 폴더의 lib에 넣는다. ( 처음셋팅할때 1번만 )
		2) Class.forName("com.mysql.cj.jdbc.Driver"); 을 작성한다. ( 외울필요x , 인터넷에서 복붙o )
		3) DriverManager의 getConnection(db연결정보,연결id,연결password) 메소드를 통하여서 Connection 객체를 생성한다.
		4) 쿼리문을 작성하여 선처리문 객체를 생성한다.
		5) 선처리문 객체를사용하여 쿼리를 웹에서 실행한다.
		
		
		
		# 쉽게 요약한 연동 방법
		
		1) mysql_connector.jar 파일을 lib폴더에 넣는다.
		2) Class.forName 작성
		3) Connection 객체를 작성
		4) Connection 객체로 PrepareStatement 객체를 생성한 후 쿼리문을 실행
		
	
	--%>
</body>
</html>