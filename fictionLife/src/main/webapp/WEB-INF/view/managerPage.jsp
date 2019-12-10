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

.loginFormDiv{
    width:510px;
	    height:100%;
	    margin-top:5%;
		margin-bottom:none;
  		font-family: "Nanum Gothic", arial, helvetica, sans-serif;
  		background-repeat: no-repeat;
  		
  		border: 1px solid black;
}
</style>

</head>
<body>



<c:if test="${empty REPORTS }">
신고글이 없습니다
</c:if>


<c:if test="${!empty REPORTS }">
<div class="loginFormDiv">
<c:set var="startPage" value="${currentPage-(currentPage%10 == 0?10:(currentPage%10))+1}"/>
<c:set var="endPage" value="${startPage + 9 }"/>
<c:if test="${endPage > pageCount }">
	<c:set var="endPage" value="${pageCount }"/>
</c:if>


<div align="center" style="font-size: 200%">#유저 신고글</div>
			<table>
				<tr>
					<td><c:if test="${currentPage > 1 }">
							<a href="../admin/loadAdminPage.html?pageNo=${currentPage -1 }"><img alt="" src="../cssImage/prev.png" width="48" height="48"></a>
						</c:if></td>
					<td>
						<table class="table table-striped">
								<tr>
									<th>글 번호</th>
									<th>제목</th>
									<th>신고자</th>
									<th>신고일</th>
								</tr>
							<c:forEach items="${REPORTS }" var="cnt">
								
								<tr>
									<td>${cnt.seqno }</td>
									<td><a href="../admin/loadReportReader.html?content=${cnt.content }&bno=${cnt.r_bno}&novelId=${cnt.novel_id}&epi=${cnt.epi}">${cnt.title }</a></td>
									<td>${cnt.email }</td>
									<td>${cnt.regi_date }</td>

								</tr>
								
							</c:forEach>
						</table>
					</td>
					<td><c:if test="${currentPage < pageCount }">
							<a href="../admin/loadAdminPage.html?pageNo=${currentPage +1 }"><img alt="" src="../cssImage/next.png" width="48" height="48"></a>
						</c:if></td>
				</tr>

			</table>


			<br />

			<c:if test="${startPage > 10 }">
				<a href="../admin/loadAdminPage.html?pageNo=${startPage -1 }">[10전]</a>
			</c:if>

			<c:forEach var="pageNo" begin="${startPage}" end="${endPage }">
				<c:if test="${pageNo == currentPage }">
					<font size="110%"> <a
						href="../admin/loadAdminPage.html?pageNo=${pageNo }">${pageNo }</a>
					</font>
				</c:if>

				<c:if test="${pageNo != currentPage }">
					<a href="../admin/loadAdminPage.html?pageNo=${pageNo }">${pageNo }</a>
				</c:if>
			</c:forEach>




			<c:if test="${endPage < pageCount }">
<a href="../admin/loadAdminPage.html?pageNo=${endPage +1 }">[10후]</a>
</c:if>


<a href="../admin/loadAdminPage.html?pageNo=${pageCount}">[마지막]</a>

<br/>

</div>
</c:if>




<div class="loginFormDiv">
<div align="center" style="font-size: 200%">#공지사항 쓰기</div>
<form action="../admin/notice.html" method="post">
<input style="width: 300px" type="text" name="title" placeholder="공지 제목"/><br/>
<textarea rows="5" cols="50" name="content">공지 내용</textarea><br/>
<input style="width: 300px" type="submit" value="확인">
</form>
</div>

<div class="loginFormDiv">
유저 블라인드
<form action="../admin/userBlind.html" method="post">
<input type="text" name="nickname"><input type="submit">
</form>


유저 블라인드 풀기
<form action="../admin/userBlind2.html" method="post">
<input type="text" name="nickname"><input type="submit">
</form>

작품 블라인드
<form action="../admin/novelBlind.html" method="post">
<input type="text" name="id"><input type="submit">
</form>


작품 블라인드 풀기
<form action="../admin/novelBlind2.html" method="post">
<input type="text" name="id"><input type="submit">
</form>

</div>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>

</body>
</html>