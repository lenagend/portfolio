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
	    margin-bottom: 1%;
  		padding-top: none;
  		padding-bottom: none;
  		padding-left:5%;
  		padding-right:5%;
  		font-family: "Nanum Gothic", arial, helvetica, sans-serif;
  		background-repeat: no-repeat;
  		
  		border: 1px solid #cc0000;
  		
}
.input{

  width:350px;
  height:50px;
  font-size:150%;
  font-family: Malgun Gothic;
 
}

.button{
 width:350px;
  height:50px;
  font-family: Malgun Gothic;
 background-color: #66CCFF;
 font-size:150%;
 color: #003399;
}

.paper {
    position: relative;
    width:510px;
    max-width: 800px;
    min-width: 400px;
    height: 390px;
    margin: 0 auto;
    background: #fafafa;
    border-radius: 10px;
    box-shadow: 0 2px 8px rgba(0,0,0,.3);
    overflow: hidden;
}
.paper:before {
    content: '';
    position: absolute;
    top: 0; bottom: 0; left: 0;
    width: 60px;
    background: radial-gradient(#575450 6px, transparent 7px) repeat-y;
    background-size: 30px 30px;
    border-right: 3px solid #D44147;
    box-sizing: border-box;
}

.paper-content {
    position: absolute;
    top: 30px; right: 0; bottom: 30px; left: 60px;
    background: linear-gradient(transparent, transparent 28px, #91D1D3 28px);
    background-size: 30px 30px;
}

.paper-content textarea {
    width: 100%;
    max-width: 100%;
    height: 100%;
    max-height: 100%;
    line-height: 30px;
    padding: 0 10px;
    border: 0;
    outline: 0;
    background: transparent;
    color: black;
    font-family: 'Handlee', cursive;
    font-weight: bold;
    font-size: 12px;
    box-sizing: border-box;
    z-index: 1;
}
</style>
</head>
<body>
	
	<form:form id="regiForm" modelAttribute="novel_board" method="post" action="../novel/regiEpi.html">
	<div id="loginFormDiv">
	<form:hidden path="novel_id" value="${novelId }"/>
	
		<table>
			<tr>	
				<td style="color: #cc0000;">작품 제목:<input type="text" class="input" value="${novelTitle }" readonly="readonly"></td>
			</tr>
			<tr>	
				<td style="color: #cc0000;">회차:<form:input class="input" path="epi_number" value="${epinum }" readonly="true"/></td>
			</tr>
			<tr>	
				<td><font color="#cc0000">에피소드 제목:</font><form:input class="input" path="epi_title"/></td>
			</tr>
			<tr>	
				<td><form:errors path="epi_title"/></td>
			</tr>
			
		</table>
		<input type="hidden" id="finish" name="finish">
		</div>
		
		
		<div class="paper">
		<div class="paper-content">
		<form:textarea cols="70" rows="40" path="content" placeholder="본문"/>
		</div>
		</div>
		
		
		
		
		<br/>
		<form:errors path="content"/>
		<br/>
		<a href="javascript:finish();">이 화를 완결로 등록하기</a>
		<br/>
		<input type="submit" class="button" value="등록">
	</form:form>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	
	<script type="text/javascript">
	function finish(){
	
		$("#finish").val("finish");
		$("#regiForm").submit();
	}
	
	</script>
</body>
</html>