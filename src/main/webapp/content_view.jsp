<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글보기. 220426 15:43 이젠 머리아파 한계야</title>
</head>
<body>
	<h2>자유게시판 글 내용 보기</h2>
	<h3>(교수님: 귀찮아. 이 페이지에서 수정, 삭제도 가능하게 합시다.)</h3>
	<hr>
			
	<table width = "600" cellpadding = "0" cellspacing = "0" border="1">
		<form action = "modify.do" method = "post">
		
			<!-- 이 줄은 왜 있냐? 유저가 bid를 수정하는 건 싫으나, 폼이 넘어갈 때 bid에 해당하는 값도 request 객체에 실렸으면 해서다. -->
			<!-- 이렇게 hidden으로 하면 유저한테 이게 폼이라는 게 보이지도 않는다. 보이긴 하지만 수정은 못 하게 read only로 해도 된다. -->
			<input type = "hidden" name = "bid" value = "${content_view.bid}">
			
			<tr>
				<td>아이디</td>
				<td>${content_view.bid}</td>
				<!-- BContentCommand.java에서 dto 이름을 "content_view"로 설정했었기 때문에, 우리는 거기에서 bid를 추출하는 것이다. -->
			</tr>
			<tr>
				<td>조회수</td>
				<td>${content_view.bhit}</td>
			</tr>
			<tr>
				<td>이름</td> 
				<td><input type = "text" name = "bname" size = "80" value ="${content_view.bname}"></td>
			</tr>
			<tr>
				<td>글제목</td> 
				<td><input type = "text" name = "btitle" size = "80" value ="${content_view.btitle}"></td>
			</tr>
			<tr>
				<td>글내용</td> 
				<td>
					<textarea name = "bcontent" rows = "10" cols = "60" size = "80" >${content_view.bcontent}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan = "2" align = "center"><input type = "submit" value = "글 수정">
				&nbsp; &nbsp; &nbsp; &nbsp;
				<a href = "delete.do">글삭제</a>
				&nbsp; &nbsp; &nbsp; &nbsp;
				<a href = "list.do">글목록보기</a></td>
			</tr>
		
		
		</form>
	
	</table>
</body>
</html>