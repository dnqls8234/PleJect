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
			<table style="display: inline;">
				<tr>
					<td>카테고리</td>
					<td>모임명</td>
					<td>회원수</td>
					<td>지위</td>
					<td></td>
				</tr>

				<c:forEach var="li" items="${li }" varStatus="index">
					<tr>
						<td>${li.like_ca }</td>
						<td><a
							href="${pageContext.request.contextPath}/room/roomcontent?num=${li.classnum }">${li.meet_title }</a></td>
						<td>${nowcnt.get(index.index) }/${li.membercnt}</td>
						<td><c:if test="${li.status==1 }">
				모임장</td>
						<td><button>관리하기</button></td>
						</c:if>
						<c:if test="${li.status==2 }">
				운영진
				</c:if>
						<c:if test="${li.status==3 }">
				회원
				</c:if>
						</td>

					</tr>

				</c:forEach>
			</table>
	</div>
</body>
</html>