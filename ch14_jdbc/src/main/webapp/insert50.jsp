<%@page import="com.tj.ch14.dto.BoardDto"%>
<%@page import="com.tj.ch14.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/style.css" rel="stylesheet">
</head>
<body>
	<%
		BoardDao bDao = BoardDao.getInstance();
		BoardDto bDto = new BoardDto();
		for(int i=0; i<55; i++){
			bDto.setBname("홍길동");
			bDto.setBtitle("앵무새만세");
			bDto.setBcontent("본문내용이쥐");
			bDto.setBip("192.168.18.48");
			bDao.write(bDto);
			
			int result = bDao.write(bDto);
			System.out.println(result==BoardDao.SUCCESS? i+"번째 성공" : i+"번째 실패");
		}
		
		String conPath = request.getContextPath();
		response.sendRedirect(conPath+"/list.do");
	%>
</body>
</html>