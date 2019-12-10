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
var result = '${memberModifyResult}';
if(result == 'modify'){
	alert("정보 수정이 완료 되었습니다");
	location.replace("../home/loadMyPage.html");
}
if(result == 'quit'){
	alert("회원 탈퇴가 완료 되었습니다");
	location.replace("../login/logout.html");
}
</script>
</body>
</html>