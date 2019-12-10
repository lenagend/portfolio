<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
#loginUserNickname{
font-family: 맑은고딕;
color: black;
font-size: 150%;
}

</style>
<title>Insert title here</title>
</head>
<body>
<div>
	<table>
		<tr>
			<td>
				<span id="loginUserNickname">${LOGINMEMBER.nickname}</span>  &nbsp;님 &nbsp; <a href="../login/logout.html">로그아웃</a>
			</td>
			
		</tr>
		<tr>
			<td>
				<table>
					<tr>
						<td>
							<img alt="" src="../rank_icon/${memberRank.wicon.icon_image }" width="32" height="32">
						</td>
						<td>
							작가포인트 : 
						</td>
						<td>
							<font color="tomato">${memberRank.ur.w_point }점</font>
						</td>
						
					</tr>
					<tr>
						<td>
							<img alt="" src="../rank_icon/${memberRank.ricon.icon_image }" width="32" height="32">
						</td>
						<td>
							독자포인트 : 
						</td>
						<td>
							<font color="tomato">${memberRank.ur.r_point }점</font>
						</td>
						
					</tr>
					<tr>
					<td>&nbsp;▶</td>
					<td>
							<a href="../home/loadMyPage.html">My Page</a>
						</td>
					<td>
						<c:if test="${ADMIN == LOGINMEMBER.nickname }">
						<a href="../admin/loadAdminPage.html"><span style="color: tomato; font-size: 75%">관리자 페이지</span></a>
						</c:if>							
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>
</body>
</html>