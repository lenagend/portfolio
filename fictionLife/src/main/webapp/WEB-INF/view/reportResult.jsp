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

if(${reportResult=='OK'}){
	alert("정상적으로 신고 되었습니다");
	location.replace("../home/loadReader.html?bno="+${bno}+"&pni="+${pni}+"&epi_number="+${epi_number});
	
}else{
	alert("이미 신고 한 글입니다");
	location.replace("../home/loadReader.html?bno="+${bno}+"&pni="+${pni}+"&epi_number="+${epi_number});
	
}

</script>
</body>
</html>