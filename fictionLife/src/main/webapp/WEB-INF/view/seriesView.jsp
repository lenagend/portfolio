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

#seriesList{
   		width:510px;
	    height:100%;
	    
	    margin-bottom: 10%;
  		
  		padding-bottom: none;
  		font-family: "Nanum Gothic", arial, helvetica, sans-serif;
  		background-repeat: no-repeat;
  		
  		border: 1px solid black;
}

#seriesList2{
  		 width:510px;
	    height:100%;
	    margin-top:5%;
	
  		font-family: "Nanum Gothic", arial, helvetica, sans-serif;
  		background-repeat: no-repeat;
  		
  		border: 1px solid black;
}
#novelTitle{
font-size: 300%;

}
</style>
</head>
<body>

<c:if test="${empty EPI_LIST }">
아직 에피소드가 없습니다
</c:if>
<c:if test="${ ! empty EPI_LIST }">
<div id="seriesList2">

		<div id="novelTitle" align="left">${parentNovel.title }</div>&nbsp;
		
		<br/>
		<div align="left">
		<c:if test="${parentNovel.finish=='yes' }"><span style="color: #cc0000;">완결!!</span></c:if>
		<c:if test="${parentNovel.finish=='no' }"><span style="color: #cc0000;">미완!!</span></c:if>
		<br/>
		<c:if test="${sessionScope.LOGINMEMBER != null }">
		<a href="../novel/favorite.html?novelId=${parentNovel.id }&writer=${parentNovel.email}">선호작 등록</a>
		</c:if>
		<br/>
		글 : <strong>${parentNovel.nickname }</strong><br/>
		총 회차: <strong> ${parentNovel.episode }</strong>회 
		</div>
		</div>
		<br/>	
		
		<div id="seriesList">
<%-- 		<c:if test="${parentNovel.finish =='yes' }">완결!!!</c:if> --%>
		<div align="center" style="margin-bottom: 10%; margin-top: 5%;">
		<table >
			<tr bordercolor="black">
				<td><img alt="" src="../upload/${parentNovel.image }" width="100" height="150"></td>
				<td>${parentNovel.description }</td>
			</tr>
		</table>
		</div>
<c:set var="startPage" value="${currentPage-(currentPage%10 == 0?10:(currentPage%10))+1}"/>
<c:set var="endPage" value="${startPage + 9 }"/>
<c:if test="${endPage > pageCount }">
	<c:set var="endPage" value="${pageCount }"/>
</c:if>

	<div style="margin-bottom: 10%;">
		<table>
			<tr>
				<td><c:if test="${currentPage > 1 }">
						<a href="../home/loadSeries.html?pageNo=${currentPage -1 }&novelId=${novelId }"><img alt="" src="../cssImage/prev.png" width="32" height="32"></a>
					</c:if></td>
				<td>
					<table>
					
						<c:forEach var="epi" items="${EPI_LIST }">


							<tr>
								<td>${epi.epi_number }화-</td>
								<td><font size="6"><a href="../home/loadReader.html?epi_number=${epi.epi_number }&pni=${parentNovel.id}&bno=${epi.bno}">${epi.epi_title }</a></font></td>
								<td><img alt="" src="../cssImage/view.jpg" width="16" height="16"> ${epi.view_cnt } </td>
								<td><img alt="" src="../cssImage/likey.png" width="16" height="16">${epi.reco_cnt } </td>
								<td><img alt="" src="../cssImage/comment.png" width="16" height="16">${epi.repl_cnt }</td>
								<c:if test="${sessionScope.LOGINMEMBER.email == parentNovel.email }">
								<td><a href="../novel/loadModifyEpiForm.html?epiNumber=${epi.epi_number }&parentNovelId=${parentNovel.id}">수정</a></td>
								</c:if>								
							</tr>
						</c:forEach>
					</table>
				</td>
				<td><c:if test="${currentPage < pageCount }">
						<a href="../home/loadSeries.html?pageNo=${currentPage +1 }&novelId=${novelId }"><img alt="" src="../cssImage/next.png" width="32" height="32"></a>
					</c:if></td>
			</tr>
		</table>
		</div>

		<br/>

<c:if test="${startPage > 10 }">
<a href="../home/loadSeries.html?pageNo=${startPage -1 }&novelId=${novelId }">[10전]</a>
</c:if>

<c:forEach var="pageNo" begin="${startPage}" 
						end="${endPage }">
	<c:if test="${pageNo == currentPage }">
		<font size="110%">
		<a href="../home/loadSeries.html?pageNo=${pageNo }&novelId=${novelId }">${pageNo }</a>
		</font>
	</c:if>
	
	<c:if test="${pageNo != currentPage }">
		<a href="../home/loadSeries.html?pageNo=${pageNo }&novelId=${novelId }">${pageNo }</a>
	</c:if>
</c:forEach>




<c:if test="${endPage < pageCount }">
<a href="../home/loadSeries.html?pageNo=${endPage +1 }&novelId=${novelId }">[10후]</a>
</c:if>


<a href="../home/loadSeries.html?pageNo=${pageCount}&novelId=${novelId }">[마지막]</a>

<br/>
<br/>

<script type="text/javascript">
function goNovel_board() {
	var url = '${redirectURI}';
	location.replace(url);
}
</script>
</div>
</c:if>
<a href="#" onclick="goNovel_board();"><span style="font-size: 200%;">#목록으로</span></a>

</body>
</html>