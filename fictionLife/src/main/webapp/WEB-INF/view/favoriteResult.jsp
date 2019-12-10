<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
if(${favoriteResult == 'OK' }){
	alert("선호작으로 등록 되었습니다");
	location.replace("../home/loadMyPage2.html");
	
	
}else if(${favoriteResult == 'NOK' }){
	
	alert("이미 선호작으로 등록 된 작품입니다");
	location.replace("../home/loadMyPage2.html");
	
}else if(${favoriteResult == 'DELETE' }){
	alert("선호작 해지 처리 되었습니다");
	location.replace("../home/loadMyPage2.html");
}

</script>
</body>
</html>