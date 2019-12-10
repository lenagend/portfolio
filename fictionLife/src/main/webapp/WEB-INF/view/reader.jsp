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
#reply{
  		 width:500px;
	    height:100%;
	    margin-top:1%;
	    margin-bottom: 5%;
  		padding-top: none;
  		padding-bottom: none;
  		font-family: "Nanum Gothic", arial, helvetica, sans-serif;
  		background-repeat: no-repeat;
  		
}

#replTable{
border: 1px solid black;

}
#reReplTable{
border: 1px solid black;

}
</style>
</head>
<body>

<table>
	<tr>
		<td>
			<!-- 작품제목 , 시리즈뷰 -->
			<a href="../home/loadSeries.html?novelId=${parentNovel.id }">${parentNovel.title}</a>
		</td>
	</tr>
	<tr>
		<td>
			<c:if test="${EPISODE.epi_number >1 }">
				<a href="../home/loadReader.html?epi_number=${EPISODE.epi_number - 1 }&pni=${parentNovel.id}&bno=${EPISODE.bno}">◀</a>&nbsp;
			</c:if>		
			<!-- 에피소드 제목 -->${EPISODE.epi_number}화, ${EPISODE.epi_title }&nbsp;
			<c:if test="${EPISODE.epi_number < parentNovel.episode}">
				<a href="../home/loadReader.html?epi_number=${EPISODE.epi_number + 1 }&pni=${parentNovel.id}&bno=${EPISODE.bno}">▶</a>&nbsp;
			</c:if>	
		</td>
	</tr>
	
	<tr>
	
		<td>
			<textarea autofocus="autofocus" cols="70" rows="100" readonly="readonly">${EPISODE.content }</textarea>
	</td>
	</tr>
	
	<tr>
	<td>
	<a href="#" onclick=" goSeries();">목록으로</a>
	<script type="text/javascript">
	function goSeries() {
		var url = '${redirectURI}';
		location.replace(url);
	}	
	
	</script>
	
	
	
	
	
	<c:if test="${sessionScope.LOGINMEMBER != null }"><!-- 로그인 유저만 -->
		
		
		<a href="../novel/favorite.html?novelId=${parentNovel.id }&writer=${parentNovel.email}">관심작품</a>
		<a href="../novel/likey.html?bno=${EPISODE.bno }&writerEmail=${parentNovel.email}&pni=${parentNovel.id}&epi_number=${EPISODE.epi_number}">좋아요</a>
		<a href="#reportForm" onclick="reportForm();">신고</a>
		<div id="reportForm">
		<form action="../novel/report.html?bno=${EPISODE.bno }" method="post">
		
		<input  type="text" name="reportTitle" maxlength="100" value="불량 게시글 신고합니다"><br/>
		<textarea rows="3" cols="60"  name="reportContent">꼭 확인 부탁드립니다</textarea><br/>
		<input style="width: 30%;" type="submit" value="신고">
		<input type="hidden" name="epi_number" value="${EPISODE.epi_number }">
		<input type="hidden" name="pni" value="${parentNovel.id  }">
		</form>
		</div>
		
		<form id="replyForm" action="../reply/reply.html" method="post">
		<input type="hidden" name="epi_number" value="${EPISODE.epi_number }">
		<input type="hidden" name="pni" value="${parentNovel.id }">
		<input type="hidden" name="bno" value="${EPISODE.bno }">
		<input type="hidden" name="rno" id="deleteRno">
		<textarea rows="3" cols="60" name="reply" placeholder="댓글 입력..."></textarea>
		<br/>
		<input style="width: 30%;" type="submit" value="확인">
		</form>
		
		
	</c:if>
	</td>
	</tr>
</table>

<c:if test="${ ! empty REPLY_LIST }">


<c:set var="startPage" value="${currentPage-(currentPage%10 == 0?10:(currentPage%10))+1}"/>
<c:set var="endPage" value="${startPage + 9 }"/>
<c:if test="${endPage > pageCount }">
	<c:set var="endPage" value="${pageCount }"/>
</c:if>
<div id="reply">
		<table>
			<tr>
				<td><c:if test="${currentPage > 1 }">
						<a href="../home/loadReader.html?pageNo=${currentPage -1 }&epi_number=${EPISODE.epi_number}&pni=${parentNovel.id}&bno=${EPISODE.bno}"><img alt="" src="../cssImage/prev.png" width="48" height="48"></a>
					</c:if></td>
				<td>
				
					<table>
					
						<c:forEach var="re" items="${REPLY_LIST }">
							

							<tr>
								<td>
									<table id="replTable">
										<tr>
											<td bgcolor="#66ccff"><img alt="" src="../rank_icon/${re.r_icon_image }" width="32" height="32">	${re.nickname}
											${re.regi_date}
											
											<c:if test="${sessionScope.LOGINMEMBER.nickname == re.nickname }">
											<a id="deleteRepl" href="#deleteRepl" onclick="deleteRepl(${re.rno});" >삭제</a>
											</c:if>
											
											<a href="#reReplyForm" onclick="reReplyForm(${re.rno});">답글(${re.repl_cnt }개) </a></td>							
										</tr>
										<tr>
											<td bgcolor="white">${re.content }<br></td>
										</tr>	
										<tr>
											<td>
									<div id="${re.rno }" class="reReplyForm">
								<table id="reReplTable">
									<tr>
										<td bgcolor="white">
									
									<c:if test="${sessionScope.LOGINMEMBER != null }">
										<form action="../reply/reReply.html" method="post">
											<input type="hidden" name="epi_number"
												value="${EPISODE.epi_number }"> <input type="hidden"
												name="pni" value="${parentNovel.id }"> <input
												type="hidden" name="bno" value="${EPISODE.bno }"> <input
												type="hidden" name="parent_no" value="${EPISODE.bno }">
											<input type="hidden" name="rno" value="${re.rno}"> 
											<textarea rows="3" cols="60" name="reply" placeholder="댓글 입력..."></textarea>
											<input	type="submit" value="확인">
										</form>
										</c:if>
										
											</td>
									</tr>
									<tr>
											<td>
										<table>
										<c:forEach var="rere" items="${REREPLY_LIST }">
											<c:if test="${rere.parent_no == re.rno }">
												<tr>
													<td bgcolor="#0099ff">&nbsp;ㄴ<img alt="" src="../rank_icon/${rere.r_icon_image }" width="32" height="32">${rere.nickname}
													${rere.regi_date}					
													<c:if test="${sessionScope.LOGINMEMBER.nickname == rere.nickname }">
													<a id="deleteReRepl" href="#deleteReRepl" onclick="deleteRepl(${rere.rno});" >삭제</a>
													</c:if>
													</td>
												</tr>
												<tr>
													<td bgcolor="white" align="left">${rere.content}</td>
												</tr>
											</c:if>
										</c:forEach>
										</table>
						
									
										</td>
									</tr>
								</table>
								
								
								<br/>
								</div>
											</td>
										</tr>
			
									</table>
									
									<br/>
									
									
								</td>
							</tr>
						
						</c:forEach>
					</table>
					
				</td>
				<td><c:if test="${currentPage < pageCount }">
						<a href="../home/loadReader.html?pageNo=${currentPage +1 }&epi_number=${EPISODE.epi_number}&pni=${parentNovel.id}&bno=${EPISODE.bno}"><img alt="" src="../cssImage/next.png" width="48" height="48"></a>
					</c:if></td>
			</tr>
		</table>


		<br/>

<c:if test="${startPage > 10 }">
<a href="../home/loadReader.html?pageNo=${startPage-1 }&epi_number=${EPISODE.epi_number}&pni=${parentNovel.id}&bno=${EPISODE.bno}">[10전]</a>
</c:if>

<c:forEach var="pageNo" begin="${startPage}" 
						end="${endPage }">
	<c:if test="${pageNo == currentPage }">
		<font size="110%">
		<a href="../home/loadReader.html?pageNo=${pageNo }&epi_number=${EPISODE.epi_number}&pni=${parentNovel.id}&bno=${EPISODE.bno}">${pageNo }</a>
		</font>
	</c:if>
	
	<c:if test="${pageNo != currentPage }">
		<a href="../home/loadReader.html?pageNo=${pageNo }&epi_number=${EPISODE.epi_number}&pni=${parentNovel.id}&bno=${EPISODE.bno}">${pageNo }</a>
	</c:if>
</c:forEach>




<c:if test="${endPage < pageCount }">
<a href="../home/loadReader.html?pageNo=${endPage +1 }&epi_number=${EPISODE.epi_number}&pni=${parentNovel.id}&bno=${EPISODE.bno}">[10후]</a>
</c:if>


<a href="../home/loadReader.html?pageNo=${pageCount }&epi_number=${EPISODE.epi_number}&pni=${parentNovel.id}&bno=${EPISODE.bno}">[마지막]</a>

</div>
</c:if>


<script type="text/javascript">
$(document).ready(function() {
	$("#reportForm").hide();
	$(".reReplyForm").hide();

});
function reportForm(){
	$("#reportForm").show();
	
};
function reReplyForm(rno){
	$(".reReplyForm").hide();
	$("#"+rno).show();
	
};

function deleteRepl(rno){
	$("#replyForm").attr("action", "../reply/deleteReply.html");
	$("#deleteRno").val(rno);
	$("#replyForm").submit();
}

</script>

<br/>
<br/>
<br/>
<br/>
</body>
</html>