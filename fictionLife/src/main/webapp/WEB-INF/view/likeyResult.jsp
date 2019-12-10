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

if(${likeyResult == 'OK' }){
	alert("추천 되었습니다");
	location.replace("../home/loadReader.html?epi_number="+${epi_number }+"&pni="+${pni}+"&bno="+${bno});
}else{
	alert("이미 추천했습니다");
	location.replace("../home/loadReader.html?epi_number="+${epi_number }+"&pni="+${pni}+"&bno="+${bno});
}
	

</script>
</body>
</html>