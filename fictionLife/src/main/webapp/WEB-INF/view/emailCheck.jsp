<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	prefix="c" %>   
   <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
<title>인증번호 확인</title>
</head>
<body>
	
	<div id="loginFormDiv">
		<div align="center" style="margin-bottom: 5%;">
		<img alt="" src="../cssImage/emailForm.png" width="256">
		</div>	
			인증번호를 입력해주세요
			<form:form modelAttribute="emailCondition" action="../login/emailResult.html">
			<form:hidden path="email" />
			<form:hidden path="dice"/>
			<table>
				<tr>
					<td>
						<form:input class="input" path="userInput"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red"><form:errors path="userInput"/></font>
					</td>
				</tr>
				<tr>
					<td><input class="button" type="submit" value="확인"/></td>
				</tr>
			</table>
			</form:form>
		
	</div>
	
</body>
</html>