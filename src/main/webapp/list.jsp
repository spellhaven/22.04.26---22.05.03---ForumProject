<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글목록 ㅋ</title>
</head>
<body>

	<h2>자유게시판 글목록</h2>
	<hr>
	
	<table width = "600" cellpadding = "0" cellspacing = "0" border = "1">
		<tr align = "center" bgcolor = "LightSkyBlue">
			<td><b>제 목(My Neck)</b></td>
			<td><b>아이디</b></td>
			<td><b>글쓴이</b></td>
			<td><b>조훼수</b></td>
		</tr>
		
		<c:forEach items = "${list}" var = "dto"> 
		<!-- 우와 스크립틀릿 ㅈㄹ 안 하고 JSTL로 하니 훨씬 편하내. ㅋ. -->
			<tr>
				<td>
					<a href = "content_view.do?bid=${dto.bid}">${dto.btitle}</a>
					<!-- 이렇게 하면 현재 id값이 따라가서 BFrontController에 전달된다 지렸지 -->
				</td>
				<td>${dto.bid}</td>
				<td>${dto.bname}</td>
				<td>${dto.bhit}</td>
			</tr>
		</c:forEach>
	
		<tr>
			<td align = "right" colspan = "4"><a href = "write_view.do">글쓰기</a></td>
		</tr>

	</table>
	
</body>
</html>