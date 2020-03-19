<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="text-align: center;">
		<div style="display: inline;">
			<img src="<%= request.getContextPath()%>/uploadFile/${room.photo}">
			<h3>${room.meet_title }</h3>
			<p>${room.content }</p>


			<c:forEach var="bookli" items="${booklist }">
		${bookli.meet_subtitle}
		${bookli.meet_date }
		${bookli.meet_time }
		${bookli.meet_location }
		${bookli.fee }
	
	</c:forEach>
		<form action="regist" method="post">
		<input type="hidden" name="num" value="${room.num }">
		<input type="hidden" name="like_ca" value="${room.like_ca }">
		<input type="hidden" name="membercnt" value="${room.membercnt }">
		<input type="hidden" name="meet_title" value="${room.meet_title }">
		<input type="hidden" name="check" value="${check }"> 
		<c:if test="${check==0 }">
		<input type="submit" value="가입하기"> 
		</c:if>
		<c:if test="${check!=0 }">
		<input type="submit" value="탈퇴하기"> 
		
		</c:if>
			</form>
			
			<a href="${pageContext.request.contextPath}/room/roomList">뒤로가기</a>
		</div>
	</div>
	<
	<script>
		function check() {
			alert("가입이 완료되었습니다.");

		}
	</script>
</body>
</html>