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

</style>
<script type="text/javascript">
$(document).ready(function() {
	$.mobile.ajaxEnabled=false;
});
</script>
</head>
<body>
	<div id="loginFormDiv">
	<h2>작품 수정</h2>
	<form:form modelAttribute="novel" action="../novel/modifyNovel.html"
		method="post" enctype="multipart/form-data">
		
		<form:hidden path="id" value="${novel.id }"/>
		<form:hidden path="image" value="${novel.image }"/>
		<table>
			<tr>
				<td><form:input class="input" path="title" placeholder="수정할 제목" /></td>
			</tr>
			<tr>
				<td><form:errors path="title" /></td>
			</tr>
			<tr>
				<td><form:select class="input" path="type">
						<form:option value="판타지" />
						<form:option value="무협" />
						<form:option value="로맨스" />
						<form:option value="기타" />
					</form:select></td>
			</tr>
			<tr>
				<td><form:textarea cols="40" rows="5" path="description"  placeholder="작품설명" /></td>
			</tr>
			<tr>
				<td><form:errors path="description" /></td>
			</tr>
			<tr>
				<td>표지이미지: <input type="file" id="imageFile" name="imageFile" /></td>
			</tr>
			<tr>
				<td>미선택시 기존 표지로 등록됩니다</td>
			</tr>
			
			<tr>
				<td><input class="button" type="submit" value="수정"></td>

			</tr>
		</table>
	</form:form>
	</div>
</body>
</html>