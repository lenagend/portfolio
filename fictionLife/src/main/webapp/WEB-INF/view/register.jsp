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
   width: 510px;
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
</head>
<body>
	
	<div id="loginFormDiv">
	
	<div align="center" style="margin-bottom: 5%;">
		<img alt="" src="../cssImage/registerForm.png" width="256">
	</div>
		<form:form modelAttribute="member" action="../login/doRegi.html" method="post">
			
			<table>
				<tr>
					<td>
						<form:input class="input" path="email" value="${authEmail }" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td>
						<form:password class="input" path="password" placeholder="비밀번호" id="pwd"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red"><form:errors path="password"/></font>
					</td>
				</tr>
				<tr>
					<td>
						<form:password class="input" path="rePwd" placeholder="비밀번호 확인" />
					</td>
				</tr>
				<tr>
					<td>
						<font color="red"><form:errors path="rePwd"/></font>
					</td>
				</tr>
				<tr>
					<td>
						<form:input class="input" path="nickname" placeholder="닉네임" />
					</td>
				</tr>
				<tr>
					<td>
						<font color="red"><form:errors path="nickname"/></font>
					</td>
				</tr>
				<tr>
					<td>
						<form:input class="input" path="phone" placeholder="휴대전화"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red"><form:errors path="phone"/></font>
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="회원가입" class="button"/></td>
				</tr>
			</table>
			
		</form:form>
	</div>
	
</body>
</html>