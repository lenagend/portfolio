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
	self.close();
	window.opener.reportResult(1);
	
	
	
	
}else{

	self.close();
	window.opener.reportResult(2);
}

</script>
</body>
</html>