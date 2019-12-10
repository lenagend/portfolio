<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style type="text/css">
#div_notice{
 		width:510px;
	    height:100%;
	    margin-top: none;
	    margin-bottom:3%;
  		padding-top: none;
  		padding-bottom:  none;
  		font-family: "Nanum Gothic", arial, helvetica, sans-serif;
  		background-repeat: no-repeat;
  		padding-left:5%;
  		padding-right:5%;
  		

}
</style>
</head>
<body>
<c:if test="${empty NOTICE_LIST }">
 공지사항이 없습니다
</c:if>

<c:if test="${!empty NOTICE_LIST }">

<div id="div_notice">
<span style="font-size: 200%; margin-top: 10%;">#공지사항</span>

<c:set var="startPage" value="${currentPage-(currentPage%10 == 0?10:(currentPage%10))+1}"/>
<c:set var="endPage" value="${startPage + 9 }"/>
<c:if test="${endPage > pageCount }">
	<c:set var="endPage" value="${pageCount }"/>
</c:if>

			
	
					<table class="table table-striped" >
					<tr>
						<th>글 번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
		<c:forEach items="${NOTICE_LIST }" var="cnt">
			<c:if test="${cnt.blind != 'yes' }">			
			<tr>
				<td>${cnt.bno }</td>
				<td><a href="../home/loadNoticeReader.html?content=${cnt.content }">${cnt.title }</a></td>
				<td>${cnt.nickname }</td>
				<td>${cnt.regi_date }
				<c:if test="${LOGINMEMBER.nickname == cnt.nickname }">
				
				
					<a href="../admin/loadModifyNoticeForm.html?bno=${cnt.bno }&title=${cnt.title}&content=${cnt.content}">수정</a>
					<a href="../admin/deleteNotice.html?bno=${cnt.bno }">삭제</a>
					
					</c:if>
				</td>
					
			</tr>
		
			</c:if>
		</c:forEach>
	</table>

		<br/>

<c:if test="${startPage > 10 }">
<a href="../home/loadNotice.html?pageNo=${startPage -1 }">[10전]</a>
</c:if>

<c:forEach var="pageNo" begin="${startPage}" 
						end="${endPage }">
	<c:if test="${pageNo == currentPage }">
		<font size="110%">
		<a href="../home/loadNotice.html?pageNo=${pageNo }">${pageNo }</a>
		</font>
	</c:if>
	
	<c:if test="${pageNo != currentPage }">
		<a href="../home/loadNotice.html?pageNo=${pageNo }">${pageNo }</a>
	</c:if>
</c:forEach>




<c:if test="${endPage < pageCount }">
<a href="../home/loadNotice.html?pageNo=${endPage +1 }">[10후]</a>
</c:if>




</div>
</c:if>
</body>
</html>