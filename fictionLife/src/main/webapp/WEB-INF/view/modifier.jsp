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

	<h2>회차 수정</h2>
	<form:form modelAttribute="novel_board" method="post" action="../novel/modifyEpi.html">
	<form:hidden path="bno" value="${novel_board.bno}"/>
	<form:hidden path="novel_id" value="${novel_board.novel_id}"/>
		<table>
			
			<tr>	
				<td><form:input class="input" path="epi_number" value="${novel_board.epi_number }" readonly="true"/>화</td>
			</tr>
			<tr>	
				<td><form:input class="input" path="epi_title" value="${novel_board.epi_title }"/></td>
			</tr>
			<tr>	
				<td><form:errors path="epi_title"/></td>
			</tr>
			<tr>	
				<td><form:textarea path="content" cols="80" rows="100" value="${novel_board.content }"/></td>
			</tr>
			<tr>	
				<td><form:errors path="content"/></td>
			</tr>
			<tr>
				<td><a href="#">이 화를 완결로 등록하기</a></td>
				
			</tr>
			<tr align="center">
				<td align="center"><input type="submit" class="button" value="수정"></td>
			</tr>
		</table>
	</form:form>
	
</body>
</html>