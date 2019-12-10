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
#menu{
   width:510px;
	    height:100%;
	    margin-top:5%;
	
  		font-family: "Nanum Gothic", arial, helvetica, sans-serif;
  		background-repeat: no-repeat;
  	
  		
  		font-size: 200%;
}

#favorite{
   width:510px;
	    height:100%;
	    margin-top:1%;
		
  		font-family: "Nanum Gothic", arial, helvetica, sans-serif;
  		background-repeat: no-repeat;
  		
  		border: 1px solid black;
  		
  		
}

#my{
   width:510px;
	    height:100%;
	    margin-top:1%;
		
		padding-top:10%;
  		font-family: "Nanum Gothic", arial, helvetica, sans-serif;
  		background-repeat: no-repeat;
  		border: 1px solid black;
  		
}
.Text_type{
font-size: 70%;
color: gray;
}

#Text_upload{
font-size: 110%;
color: #cc0000;
}
.input{

  width:300px;
  height:50px;
  font-size:150%;
  font-family: Malgun Gothic;
 
}

.button{
 width:150px;
  height:50px;
  font-family: Malgun Gothic;
 background-color: #66CCFF;
 font-size:150%;
 color: #003399;
}

#loginFormDiv{
   width:510px;
	    height:100%;
	    margin-top:1%;
		
  		font-family: "Nanum Gothic", arial, helvetica, sans-serif;
  		background-repeat: no-repeat;
  		
  		border: 1px solid black;
}
</style>
</head>
<body>
<div id="menu">
<table  style="width: 100%; height: 100%;">
<tr>
<td align="left"><a href="../home/loadModifyMember.html">개인정보수정</a></td>
<td align="center"><a href="../home/loadMyPage.html">내 작품</a></td>
<td align="right"><a href="../home/loadMyPage2.html">관심작품</a></td>
<tr/>
</table>
</div>


<c:if test="${CONTENTNAME == 'MYFAVORITE' }">

<div id="favorite">
<c:if test="${empty NOVEL_LIST }">
<a style="font-size: 200%; margin-top: 30%;" href="#">#관심작품이 없습니다.</a>
</c:if>

<c:if test="${!empty NOVEL_LIST }">

<span style="font-size: 200%">#관심작품</span>
<c:set var="startPage" value="${currentPage-(currentPage%10 == 0?10:(currentPage%10))+1}"/>
<c:set var="endPage" value="${startPage + 9 }"/>
<c:if test="${endPage > pageCount }">
	<c:set var="endPage" value="${pageCount }"/>
</c:if>
	
			<div align="center">완결:<img alt="" src="../cssImage/redLink.png" width="16" height="16">&nbsp;
			미완:<img alt="" src="../cssImage/blueLink.png" width="16" height="16"></div>
	<table>
	
		<tr>
			<td>
				<c:if test="${currentPage > 1 }">
						<a href="../home/loadMyPage2.html?pageNo=${currentPage -1 }"><img alt="" src="../cssImage/prev.png" width="32" height="32"></a>
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
							<td align="left">
							<c:if test="${cnt.finish=='yes' }">
							<a style="color: #cc0000;" href="../home/loadSeries.html?novelId=${cnt.id }">${cnt.title }</a>
							</c:if>
							<c:if test="${cnt.finish=='no' }">
							<a href="../home/loadSeries.html?novelId=${cnt.id }">${cnt.title }</a>
							</c:if>
							</td>
							<td><span class="Text_type">장르 : ${cnt.type }</span></td>
						</tr>
						<tr>
							<td>
							<img alt="" src="../rank_icon/${cnt.w_icon_image}" width="16" height="16">
								${cnt.nickname } 작가님&nbsp;
							</td>
							<td><span id="Text_upload">${cnt.episode }화 업로드!!</span></td>
						</tr>
					</table>
				</td>
				
				<td><a href="../novel/deleteFavorite.html?novelId=${cnt.id }">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
				</td>
				<td>
				<c:if test="${currentPage < pageCount }">
						<a href="../home/loadMyPage2.html?pageNo=${currentPage +1 }"><img alt="" src="../cssImage/next.png" width="32" height="32"></a>
				</c:if>
				</td>
			</tr>

	</table>


		<br/>

<c:if test="${startPage > 10 }">
<a href="../home/loadMyPage2.html?pageNo=${startPage -1 }">[10전]</a>
</c:if>

<c:forEach var="pageNo" begin="${startPage}" 
						end="${endPage }">
	<c:if test="${pageNo == currentPage }">
		<font size="110%">
		<a href="../home/loadMyPage2.html?pageNo=${pageNo }">${pageNo }</a>
		</font>
	</c:if>
	
	<c:if test="${pageNo != currentPage }">
		<a href="../home/loadMyPage2.html?pageNo=${pageNo }">${pageNo }</a>
	</c:if>
</c:forEach>




<c:if test="${endPage < pageCount }">
<a href="../home/loadMyPage2.html?pageNo=${endPage +1 }">[10후]</a>
</c:if>


<a href="../home/loadMyPage2.html?pageNo=${pageCount}">[마지막]</a>

<br/>











</c:if>
</div>
</c:if>











<c:if test="${CONTENTNAME == 'MYNOVEL' }">
<span style="font-size: 200%">#내 작품</span>
<div id="my">
<c:if test="${empty NOVEL_LIST }">
<a style="font-size: 200%; margin-top: 30%" href="../home/loadRegiNovel.html">#작품이 없습니다 새로 작품을 동록하세요</a>
</c:if>

<c:if test="${!empty NOVEL_LIST }">



<c:set var="startPage" value="${currentPage-(currentPage%10 == 0?10:(currentPage%10))+1}"/>
<c:set var="endPage" value="${startPage + 9 }"/>
<c:if test="${endPage > pageCount }">
	<c:set var="endPage" value="${pageCount }"/>
</c:if>


	<table>
		<tr>
			<td>
				<c:if test="${currentPage > 1 }">
						<a href="../home/loadMyPage.html?pageNo=${currentPage -1 }"><img alt="" src="../cssImage/prev.png" width="32" height="32"></a>
				</c:if>
			</td>
				<td>
					<table>
		<c:forEach items="${NOVEL_LIST }" var="cnt">
			<tr>
				
				<td><img alt="" src="../upload/${cnt.image }" width="50"	height="75"></td>
				<td>
					<table>							
						<tr>
						<td>
						&nbsp;
						<img alt="" src="../cssImage/star.png" width="16" height="16">${cnt.favorite_num }
						&nbsp;
						<c:if test="${cnt.finish=='yes' }"><span style="color: #cc0000;">완결!!</span></c:if>
						<c:if test="${cnt.finish=='no' }"><span style="color: #cc0000;">미완!!</span></c:if>
						</td>
						<td>
							<a 	href="../home/loadModiNovel.html?novelId=${cnt.id }&novelImage=${cnt.image}">수정</a>
							 <a href="../home/loadDeleteNovel.html?novelId=${cnt.id }">삭제</a>
						</td>
						</tr>
						<tr>
							<td><a href="../home/loadSeries.html?novelId=${cnt.id }">${cnt.title }</a></td>
							<td><span class="Text_type">장르 : ${cnt.type }</span></td>
						</tr>
						<tr>
							<td><a href="../home/loadEpisodeForm.html?novelId=${cnt.id }&novelTitle=${cnt.title}">#연재하러 가기</a>
								
							</td>
							<td style="color: gray; font-size: 70%;">${cnt.regi_date }</td>
							
						</tr>
					</table>

				</td>
				
			</tr>
		</c:forEach>
	</table>
				</td>
				<td>
				<c:if test="${currentPage < pageCount }">
						<a href="../home/loadMyPage.html?pageNo=${currentPage +1 }"><img alt="" src="../cssImage/next.png" width="32" height="32"></a>
				</c:if>
				</td>
			</tr>

	</table>


		<br/>

<c:if test="${startPage > 10 }">
<a href="../home/loadMyPage.html?pageNo=${startPage -1 }">[10전]</a>
</c:if>

<c:forEach var="pageNo" begin="${startPage}" 
						end="${endPage }">
	<c:if test="${pageNo == currentPage }">
		<font size="110%">
		<a href="../home/loadMyPage.html?pageNo=${pageNo }">${pageNo }</a>
		</font>
	</c:if>
	
	<c:if test="${pageNo != currentPage }">
		<a href="../home/loadMyPage.html?pageNo=${pageNo }">${pageNo }</a>
	</c:if>
</c:forEach>




<c:if test="${endPage < pageCount }">
<a href="../home/loadMyPage.html?pageNo=${endPage +1 }">[10후]</a>
</c:if>


<a href="../home/loadMyPage.html?pageNo=${pageCount}">[마지막]</a>

<br/><br/><br/><br/><br/>
<a href="../home/loadRegiNovel.html"><span style="font-size: 200%">#작품 더 등록하기</span></a>

</c:if>
</div>
</c:if>


<c:if test="${CONTENTNAME == 'modifyForm'}">
<span style="font-size: 200%">#개인정보 수정</span>
<div id="loginFormDiv">
		<form:form id="modifyForm" modelAttribute="member" action="../login/doMemberModify.html" method="post">
			
			<table>
				<tr>
					<td>비밀번호만 변경 가능합니다</td>
				</tr>
				<tr>
					<td>
						<form:input class="input" path="email" value="${member.email }" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td>
						<form:password class="input" path="password" placeholder="기존 비밀번호" id="pwd"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red"><form:errors path="password"/></font>
					</td>
				</tr>
				<tr>
					<td>
						<form:password class="input" path="newPassword" placeholder="바꿀 비밀번호" id="newPwd"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red"><form:errors path="newPassword"/></font>
					</td>
				</tr>
				<tr>
					<td>
						<form:password class="input" path="rePwd" placeholder="바꿀 비밀번호 확인" />
					</td>
				</tr>
				<tr>
					<td>
						<font color="red"><form:errors path="rePwd"/></font>
					</td>
				</tr>
				<tr>
					<td>
						<form:input class="input" path="nickname" value="${member.nickname }" readonly="true" />
					</td>
				</tr>
			
				<tr>
					<td>
						<form:input class="input" path="phone" value="${member.phone }" readonly="true" />
					</td>
				</tr>
				<tr>
					<td>
						<font color="red"><form:errors path="phone"/></font>
					</td>
				</tr>
				<tr>
					<td><input type="button" value="변경" onclick="modi();" class="button" />
					<input type="button" value="탈퇴" onclick="quit();" class="button"/></td>
				</tr>
			</table>
			<form:hidden path="act" id="act"/>
		</form:form>
	</div>
	<script type="text/javascript">
	function modi(){
		 document.getElementById("act").value='수정';
		document.getElementById("modifyForm").submit();

	};
	
	function quit() {
		 document.getElementById("act").value='탈퇴';
		document.getElementById("modifyForm").submit();
	};
	
	</script>


</c:if>
</body>
</html>