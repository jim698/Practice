<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/style.css" rel="stylesheet">
</head>
<body>

	<h1>${msg }</h1>
	<hr>
	<form action="${conPath }/input.do" method="get">
	<h2>개인 정보 입력</h2>
		<table>			
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" required="required"></td>
			</tr>
			<tr>
				<th>국어</th>
				<td><input type="number" name="kor" min=1 max=100 class="number" required="required"></td>
			</tr>
			<tr>
				<th>영어</th>
				<td><input type="number" name="eng" min=1 max=100 class="number" required="required"></td>
			</tr>
			<tr>
				<th>수학</th>
				<td><input type="number" name="mat" min=1 max=100 class="number" required="required"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="입력"></td>
			</tr>
		</table>
	</form>
</body>
</html>