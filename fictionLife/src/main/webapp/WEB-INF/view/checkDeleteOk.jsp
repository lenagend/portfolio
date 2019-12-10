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
var deleteNovelid = ${deleteNovelid};
var check = confirm("정말로 작품을 삭제하시겠습니까?");

if (check == true) {
	location.replace("../novel/deleteNovel.html?deleteNovelId="
			+ deleteNovelid);
} else {
	location.replace('../home/goMain.html');
}
</script>
</body>
</html>