<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
<span style="font-size: 200%;">#<a href="../home/loadReader.html?epi_number=${epi }&pni=${novelId}&bno=${bno}">회차 확인하러 가기</a></span><br/>

<textarea rows="20" cols="60" readonly="readonly">${content }</textarea>
<br/>
<br/>
<a onclick="goNoticeList();">목록으로</a>
</div>

<script type="text/javascript">
function goNoticeList() {
	
	location.replace("../admin/loadAdminPage.html");
}
</script>
</body>
</html>