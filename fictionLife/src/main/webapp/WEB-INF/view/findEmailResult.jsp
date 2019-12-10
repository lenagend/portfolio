<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	prefix="c" %>   
   <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

#loginFormDiv{
   width:510px;
	    height:100%;
	    margin-top:5%;
	    
  		padding-top: 5%;
  		padding-bottom: 5%;
  		font-family: "Nanum Gothic", arial, helvetica, sans-serif;
  		background-repeat: no-repeat;
  		
  		border: 1px solid black;
}

.input{

  width:300px;
  height:50px;
  font-size:150%;
  font-family: Malgun Gothic;
 
}

.button{
 width:300px;
  height:50px;
  font-family: Malgun Gothic;
 background-color: #66CCFF;
 font-size:150%;
 color: #003399;
}
.text_link{


  font-size:150%;
	font-family: Malgun Gothic;
	color: black;
}

</style>
</head>
<body>

<div id="loginFormDiv"> <div style="font-size: 200%;">#계정 찾기 결과</div>
	<div align="center" style="margin-bottom: 5%;">
		<img alt="" src="../cssImage/findForm.png" width="256">
		</div>
	<form action="../home/loadFindEmail.html" method="post">
		<table>
			<c:choose>
				<c:when test="${findEmailResult == 'fail'}">
					<tr>
						<td>
							<div id="resultMsg">
								<font color="red" size="5">해당 전화번호는 없습니다</font>
							</div>
						</td>
					</tr>
					<tr>
						<td>관리자 문의: 010-7159-2578</td>
					</tr>
					<tr>
						<td><input class="button" type="submit" value="다시 찾기" /></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td style="font-size: 150%;">이메일 : ${findEmailResult }</td>
					</tr>
					<tr>
						<td><font color="red" size="5">비밀번호가 이메일로 전송되었습니다</font></td>
					</tr>
					<tr>
						<td><a class="text_link" href="../home/goMain.html">홈으로</a></td>
					</tr>
				</c:otherwise>
			</c:choose>


		</table>
	</form>
</div>	
</body>
</html>