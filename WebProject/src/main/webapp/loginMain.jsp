<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<ul>

	<li><a href="list.do">회원목록</a></li>

	<li><a href="write.html">글쓰기</a></li>
	<li><a href="listboard.do">게시글목록</a></li>
	
</ul>
	<% session = request.getSession();
	out.print("안녕하세요 " + session.getAttribute("memberId") + "님"+ "<br>"); %>
	<a href="logout.jsp">로그아웃</a>
<%-- 
	<%
	//FortuneServlet에서 "fortuneToday"라는 키값으로 담은 String type 얻어내기
	
	request.setCharacterEncoding("euc-kr");

	String id = request.getParameter("id");

	
	%>

<p>안녕하세요:<strong><%=id%></strong></p>
--%>

</body>
</html>