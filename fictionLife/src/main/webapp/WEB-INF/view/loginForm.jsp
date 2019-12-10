<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	prefix="c" %>   
   <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  
<title>로그인</title>
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

#text_link{


  font-size:150%;
	font-family: Malgun Gothic;
	color: black;
}



</style>
</head>
<body>
	<c:if test="${! empty LOGINMEMBER }">
	이미 로그인 되어있습니다. 새로고침해 보세요
	</c:if>
	
	<div id="loginFormDiv" align="center">
	<c:if test="${ empty LOGINMEMBER }">
	<div align="center" style="margin-bottom: 5%;">
		<img alt="" src="../cssImage/loginForm.png" width="256">
	</div>
	
		<form:form modelAttribute="member" action="../login/login.html">
			
			<table >
				<tr>
					<td>
						<form:input path="email" placeholder="email" class="input" maxlength="30"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red"><form:errors path="email"/></font>
					</td>
				</tr>
				<tr>
					<td>
						<form:password path="password" placeholder="password" class="input" maxlength="15"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red"><form:errors path="password"/></font>
					</td>
				</tr>
				<tr align="center">
					<td align="center"><input class="button" type="submit" value="로그인"/></td>
				</tr>
			</table>
			<table >
				<tr>
				<td><a id="text_link" href="../home/loadFindEmail.html">계정찾기</a></td>
				</tr>
			
				<tr align="center">
					<td align="center"><input  class="button" type="button" value="회원가입" onclick="register();"></td>
					
				</tr>
			
			</table>
		</form:form>
	
	</c:if>
	</div>
	<script type="text/javascript">
	
	function register(){
		location.href="../home/loadEmail.html";
		
	}
	</script>
</body>
</html>