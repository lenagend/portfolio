<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
</style>
<title>Insert title here</title>
</head>
<body>
	
	<div id="loginFormDiv">
	<div align="center" style="margin-bottom: 5%;">
		<img alt="" src="../cssImage/findForm.png" width="256">
		</div>
	<div style="font-size: 200%">#계정 찾기,전화번호를 입력해주세요</div>
		
		<form action="../login/findEmail.html" method="post">
			<input class="input" type="hidden" name="findEmailTokken" id="findEmailTokken" value="${findEmailTokkenMsg }"/>
			<table>
				<tr>
					<td><input class="input" type="number" name="phone"/></td>
				</tr>
				<tr>
					<td><font color="gray">비밀번호는 가입 이메일로 자동 전송됩니다</font></td>
				</tr>

				<tr>
					<td><input class="button" type="submit" value="확인" /></td>
				</tr>


			</table>

		</form>
	</div>


</body>
</html>