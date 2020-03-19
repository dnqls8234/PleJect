<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "model.InterestDataBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name = "viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content = "ie-edge">
<meta name = "format-detection" content = "telephone-no">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap&subset=korean" rel="stylesheet">
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<link rel = "stylesheet" href="${pageContext.request.contextPath}/css/memberLikeForm.css">
<link rel= "stylesheet" href="${pageContext.request.contextPath}/css/memberLikeForm_tablet.css" media="all and (max-width:1420px)">
<link rel= "stylesheet" href="${pageContext.request.contextPath}/css/memberLikeForm_moblie.css" media="all and (max-width:760px)">
<title>Insert title here</title>
</head>
<body>
<script>
	var maxChecked = 3;   //선택가능 갯수
	var totalChecked = 0; // 설정 끝

	function CountChecked(cont) {
	    if (cont.checked)
	        totalChecked += 1;
	    else
	        totalChecked -= 1;
	
	    if (totalChecked > maxChecked) {
	        alert ("최대 3개 까지만 가능합니다.");
	        cont.checked = false;
	    	totalChecked -= 1;
	    }
	}
	
</script>
	
	<div class = "likeWrap">
		<form name = "like" id = "like" method = "post" action = "index">
			<h2 class = "like_tit">관심사</h2>
			<p class ="memo">최대 <script>document.write(maxChecked);</script> 까지만 선택할 수 있습니다.</p>
			<div class = "likelike">
			<c:forEach var = "interestList" items="${interestList}">
				<div class = "likelist">
					<div class = "interestList">
						<input type="checkbox" id="like" name = "like"  value = "${interestList.interestName}" onclick=CountChecked(this) >
						<label for="like">${interestList.interestName}</label>
					</div>
				</div>
			</c:forEach>
			</div>
			<div class = "likeCon">
			<input type="submit" value="선택완료" class="btn">
			</div>
		</form>
	</div>
</body>
</html>