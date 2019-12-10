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
if(${result == '1'}){
	alert("이미 완결처리 된 작품입니다");
}else{
	alert("완결되지 않은 작품이 있습니다. 새 작품은 기존 작품이 완결처리 되야 쓰실 수 있습니다");
	
}

location.replace("../home/loadMyPage.html");
</script>
</body>
</html>