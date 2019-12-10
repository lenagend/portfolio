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
if(${delete !=null}){
	alert("삭제 완료");
	location.replace("../home/loadNotice.html");
}else if(${modify != null}){
	alert("수정 완료");
	location.replace("../home/loadNotice.html");
	
}
else if(${notice != null}){
	alert("공지작성 완료");
	location.replace("../admin/loadAdminPage.html");
}else{
	alert("블라인드 완료");
	location.replace("../admin/loadAdminPage.html");
}




</script>
</body>
</html>