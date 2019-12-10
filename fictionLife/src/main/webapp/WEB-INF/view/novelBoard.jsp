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
#Text_type{
font-size: 100%;
color: gray;
}

#Text_title{
font-size: 200%;
}
#Text_title2{
font-size: 200%;
color: #cc0000;
}

#Text_upload{
font-size: 110%;
color: #cc0000;
}



</style>
</head>
<body>
<c:if test="${searchResult == 'noResult' }">
<script type="text/javascript">
alert("검색 결과가 없습니다");
location.replace("../home/goMain.html");
</script>
</c:if>
<c:if test="${empty NOVEL_LIST }">
망한 홈페이진가봅니다 ㅠㅠ  아직 아무도 소설을 등록하지 않았습니다...
</c:if>


<c:if test="${!empty NOVEL_LIST }">

<c:set var="startPage" value="${currentPage-(currentPage%10 == 0?10:(currentPage%10))+1}"/>
<c:set var="endPage" value="${startPage + 9 }"/>
<c:if test="${endPage > pageCount }">
	<c:set var="endPage" value="${pageCount }"/>
</c:if>

<div id="board" align="center">
	<table>
	<tr>
	<td>
		<c:if test="${currentPage > 1 }">
						<a href="../home/goMain.html?pageNo=${currentPage -1 }"><img alt="" src="../cssImage/prev.png" width="32" height="32"></a>
		</c:if>
	</td>
	<td>
	<table>
		<c:forEach items="${NOVEL_LIST }" var="cnt">
		
			<tr>
				
				<td>
					<img alt="" src="../upload/${cnt.image }" width="50"	height="75">
				</td>
				<td>
					<table>
						<tr>
							<td>
								<c:if test="${cnt.finish=='yes' }">
								<a href="../home/loadSeries.html?novelId=${cnt.id }"><span id="Text_title2">${cnt.title }</span></a>
								</c:if>
								<c:if test="${cnt.finish=='no' }">
								<a href="../home/loadSeries.html?novelId=${cnt.id }"><span id="Text_title">${cnt.title }</span></a>
								</c:if>
								&nbsp;<span id="Text_type">장르 : ${cnt.type } </span>
								<c:if test="${ADMIN == LOGINMEMBER.nickname }">
									<a href="../admin/novelBlind.html?id=${cnt.id}"> 블라인드</a>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>
								<img alt="" src="../rank_icon/${cnt.w_icon_image}" width="16" height="16">
							
								${cnt.nickname } 작가님
								<c:if test="${ADMIN == LOGINMEMBER.nickname }">
									<a href="../admin/userBlind.html?nickname=${cnt.nickname}"> 블라인드</a>
								</c:if>
								 &nbsp;<span id="Text_upload">${cnt.episode }화 업로드!!</span>
								&nbsp;<img alt="" src="../cssImage/star.png" width="16" height="16">${cnt.favorite_num } 
							</td>
						
						</tr>
							
					</table>
				</td>
				
			</tr>
			
		</c:forEach>
	</table>
	</td>
	<td>
		<c:if test="${currentPage < pageCount }">
						<a href="../home/goMain.html?pageNo=${currentPage +1 }"><img alt="" src="../cssImage/next.png" width="32" height="32"></a>
		</c:if>
	</td>
	</tr>
	</table>
	<form action="../home/goMain.html">
	<input type="text" name="search" maxlength="15">
	<input type="submit" value="검색">
	</form>

		<br/>
		
		
		<br/>

<c:if test="${startPage > 10 }">
<a href="../home/goMain.html?pageNo=${startPage -1 }">[10전]</a>
</c:if>

<c:forEach var="pageNo" begin="${startPage}" 
						end="${endPage }">
	<c:if test="${pageNo == currentPage }">
		<font size="110%">
		<a href="../home/goMain.html?pageNo=${pageNo }">${pageNo }</a>
		</font>
	</c:if>
	
	<c:if test="${pageNo != currentPage }">
		<a href="../home/goMain.html?pageNo=${pageNo }">${pageNo }</a>
	</c:if>
</c:forEach>




<c:if test="${endPage < pageCount }">
<a href="../home/goMain.html?pageNo=${endPage +1 }">[10후]</a>
</c:if>


<a href="../home/goMain.html?pageNo=${pageCount}">[마지막]</a>

<br/>

</div>
</c:if>
</body>
</html>