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
#replySpace{
  		 width:500px;
	    height:100%;
	    margin-top:1%;
	    margin-bottom: 5%;
  		padding-top: none;
  		padding-bottom: none;
  		font-family: "Nanum Gothic", arial, helvetica, sans-serif;
  		background-repeat: no-repeat;
  		
}

.replTable{
border: 1px solid black;
width: 500px;

}
#reReplTable{
border: 1px solid black;
width: 450px;
}

#pageRemocon{
 position:   fixed;
    right:       15%;
    bottom:     0;
	border: 1px solid #0099ff;
}

</style>
<script type="text/javascript">
var replPageNo = 1;
$(document).ready(function() {
	
	
	
	//좋아요
	$('#likeyBtn').click(function() {
		
		$.ajax({
             type: "POST",
             url: "../novel/ajaxLikey.html",
             data:{"bno": $("#bno").val(),
            	 "email": $("#email").val()},
             success: function(result) {
            	 if(result == 'suc'){
            		 alert('추천되었습니다'); 
            		 $.ajax({
            			 type:"POST",
            			 url:"../novel/getReco.html",
            			 data:{"bno": $("#bno").val()},
            			 success: function(result){
            				 $("#recoCnt").html("추천수: "+result);
            			 }
            			 
            		 });
            	 }else if (result == 'fail'){
            		 alert('이미 추천 했습니다');
            	 }
                
                
             }, error: function() {
                 alert('오류');
             }

	});
});//좋아요
	//북마크
	$('#favoriteBtn').click(function() {
		
		$.ajax({
            type: "POST",
            url: "../novel/favorite.html",
            data:{"novelId": '${parentNovel.id }',
           	 "email": $("#email").val()},
            success: function(result) {
           	 if(result == 'favoSuc'){
           		 var movePage = confirm("관심목록에 추가되었습니다, 마이페이지로 이동하시겠습니까?");
	       		 if(movePage == true){
	       			 location.replace("../home/loadMyPage2.html");
	       		 }
           	 }else if (result == 'favoFail'){
           		 alert('이미 관심목록에 있습니다');
           	 }
               
               
            }, error: function() {
                alert('오류');
            }

	});
		
	});//북마크

	$("#replyForm").hide();
	$("#CallMoreRepl").click(function() {
		replPageNo+=1;
		
		$.ajax({
	        type: "POST",
	        url: "../reply/loadReply.html",
	        data:{"bno": $("#bno").val(),
	        	"pageNo": replPageNo,
	        	
	       	 },
	      	dataType:"json",
	        success: function(json) {
	        	$('#replySpace').append(replyList(json));
	        	$("#replyForm").show();
	        	if(json[0].endPage =='no'){
	        		$("#CallMoreRepl").show();
	        	}else{
	        		$("#CallMoreRepl").hide();
	        		$('#replySpace').append("<span style='font-size: 2em;'><a href='#page_first'>#마지막 댓글입니다</a></span>");
	        	}
	        }, error: function() {
	            alert('오류');
	        }

	});
	});
	$("#CallMoreRepl").hide();
	//댓글페이지
	$('#callReplPage').click(
			function () {
				$.ajax({
			        type: "POST",
			        url: "../reply/loadReply.html",
			        data:{"bno": $("#bno").val()
			        	
			       	 },
			       	dataType:"json",
			        success: function(json) {
			        	$('#replySpace').html(replyList(json));
			        	$("#replyForm").show();
			        	if(json[0].endPage =='no'){
			        		$("#CallMoreRepl").show();
			        	}
			        }, error: function() {
			            alert('오류');
			        }

			});
				
			});
	//댓글페이지

	//댓글인서트
$('#replyBtn').click(function() {
		if(${sessionScope.LOGINMEMBER == null}){
			alert("로그인 해야 합니다");
		}
		else if($('#reply').val()==''){
			alert("댓글 내용을 입력하지 않았습니다");
		}else{
			$.ajax({
	            type: "POST",
	            url: "../reply/reply.html",
	            data:{"bno": $("#bno").val(),
	           	 "reply": $("#reply").val()
	           	},
	            success: function(result) {
	           	 if(result == 'replSuc'){
	           		replPageNo =1;
	           		$('#reply').val('');
	           		
	           		$.ajax({
				        type: "POST",
				        url: "../reply/loadReply.html",
				        data:{"bno": $("#bno").val()
				        	
				       	 },
				       	dataType:"json",
				        success: function(json) {
				        	$('#replySpace').html(replyList(json));
				        	$("#replyForm").show();
				        	if(json[0].endPage =='no'){
				        		$("#CallMoreRepl").show();
				        	}
				        }, error: function() {
				            alert('오류');
				        }

				});
	           	 }else if (result == 'replFail'){
	           		 alert('댓글을 못달았습니다');
	           	 }
	               
	               
	            }, error: function() {
	                alert('오류');
	            }

		});
			 
		}
		
	});//댓글

	
});

function report() {
	var popupX = (window.screen.width/2)-(200/2);
	// 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음

	var popupY= (window.screen.height/2)-(300/2);
	// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
	var url="../home/report.html?pid="+document.getElementById('pid').value+"&epiNum="+document.getElementById('epi_num').value+"&bno="+document.getElementById('bno').value;
	window.open(url, "_blank", "width=450, height=200, left="+popupX+",top="+popupY);
};

function deleteRepl(rno){
	$("#replyForm").attr("action", "../reply/deleteReply.html");
	$("#deleteRno").val(rno);
	$("#replyForm").submit();
};

function reportResult(result) {
	if(result == 1){
		alert("정상적으로 신고 되었습니다");
	}else if (result == 2){
		alert("이미 신고 한 글입니다");
	}
};

function replyList(json){
	var result = '';
    var loginNick = '${LOGINMEMBER.nickname}';

	for(var i=0, item; item=json[i]; i++){		
		result += "<table id='replTable"+json[i].rno+"'";
		result += " class='replTable' style='font-size:14px;'>";
		result+="<tr>";
		result+="<td >";
		result+="<img src='../rank_icon/"+json[i].iconImage+"'width='32' height='32'/>"+json[i].nickname+'&nbsp;'+json[i].regiDate;
	
		if(loginNick == json[i].nickname && json[i].content != '삭제된 댓글입니다'){
			
			result+="<a onClick='deleRepl("+json[i].rno+");'>삭제</a>";
		}
		
		result+="</td>";
		result+="<tr>";
		result+="<td>";
		result+=json[i].content;
		result+="</td>";
		result+="</tr>";
		result+="<tr>";
		result+="<td>";
		result+="<a id='reReLink"+json[i].rno+"' onClick='reReply(replTable"+json[i].rno+", "+json[i].rno+"); this.onclick=null;'>";
		result+="답글 ";
		result+=json[i].rereCnt;		
		result+="</a>";
		result+="</td>";
		result+="</tr>";
		result += "</table>";
		result += "</br>";
	}
	
	return result;
}

function deleRepl(rno){
	replPageNo =1;
	
	$.ajax({
        type: "POST",
        url: "../reply/deleteReply.html",
        data:{"rno": rno
       	
       	},
        success: function(result) {
       	 if(result == 'deleSuc'){
       		
       		$('#reply').val('');
       		$('#replySpace').empty();
       		$.ajax({
		        type: "POST",
		        url: "../reply/loadReply.html",
		        data:{"bno": $("#bno").val()
		        	
		       	 },
		       	dataType:"json",
		        success: function(json) {
		        
		        	$('#replySpace').html(replyList(json));
		        	$("#replyForm").show();
		        	if(json[0].endPage =='no'){
		        		$("#CallMoreRepl").show();
		        	}
		        }, error: function() {
		            alert('댓글 불러오기 오류');
		        }

		});
       	 }else if (result == 'deleFail'){
       		 alert('삭제실패');
       	 }
           
           
        }, error: function() {
            alert('삭제오류');
        }

});
	
}

function reReply(tableName, rno) {
	
	//테이블을 아이디로 불러오고
	var replTable = tableName;
	var parent_no = rno;
		
	//새로운행과 아이디 지정
	var newTr = replTable.insertRow(replTable.rows.length);
	newTr.id = "newTr" + replTable.rows.length;
	
	var newTr2 = replTable.insertRow(replTable.rows.length);
	newTr2.id = "newTr2" + replTable.rows.length;
	
	var newTd = newTr.insertCell(0);
	var newTd2 = newTr2.insertCell(0);
	newTd2.id = "newTd2_" +parent_no;
	
	var reReForm = "<form method='post'><table><tr><td><textarea row='3' cols='60' id='reReply"+parent_no+"' placeholder='댓글 입력...'></textarea> ";
	reReForm += "</td></tr><tr><td><input type='button' value='확인' onClick='insertReReply("+parent_no+");'/> </td></tr></table></form> "
	newTd.innerHTML = reReForm;
	
	//여기서 대댓글목록을 불러와야한다
	//댓글페이지
	
			
				$.ajax({
			        type: "POST",
			        url: "../reply/loadreReply.html",
			        data:{"rno":parent_no
			        	
			       	 },
			       	dataType:"json",
			        success: function(json) {
			        	
			        	newTd2.innerHTML = replyList(json);
			        }, error: function() {
			            alert('오류');
			        }

			});
				
		
	//댓글페이지
	
};

function insertReReply(parent_no) {
	var rereTd = document.getElementById("newTd2_"+parent_no);
	//댓글링크의 답글수를 새로 변환해야됨
	var replLink = document.getElementById("reReLink"+parent_no);
	
	if(${sessionScope.LOGINMEMBER == null}){
		alert("로그인 해야 합니다");
	}
	else if($('#reReply'+parent_no).val()==''){
		alert("댓글 내용을 입력하지 않았습니다");
	}else{
		$.ajax({
            type: "POST",
            url: "../reply/reReply.html",
            data:{"bno": $("#bno").val(),
           	 "reply": $("#reReply"+parent_no).val(),
           	 "parent_no": parent_no
           	},
            success: function(result) {
         		if(result = 'replSuc'){
         			  $.ajax({
        			        type: "POST",
        			        url: "../reply/loadreReply.html",
        			        data:{"rno":parent_no
        			        	
        			       	 },
        			       	dataType:"json",
        			        success: function(json) {
        			        	//답글수도 새로받아와야되는데...
        			        	replLink.innerHTML ="답글 "+ json[0].parentCnt;
        			        	rereTd.innerHTML = replyList(json);
        			        
        			        }, error: function() {
        			            alert('오류');
        			        }

        			});
         		}
             
         		
         		else
         		alert("댓글달기실패");
               
            }, error: function() {
                alert('오류');
            }

	});
		 
	}
	
}

</script>
</head>
<body>
<input type="hidden" value="${EPISODE.bno }" id="bno">
<input type="hidden" value="${parentNovel.email }" id="email">
<input type="hidden" value="${EPISODE.epi_number }" id="epi_num">
<input type="hidden" value="${parentNovel.id }" id="pid">
<table>
	<tr>
		<td>
			<!-- 작품제목 , 시리즈뷰 -->
			<a style="font-size: 1.2em;" href="../home/loadSeries.html?novelId=${parentNovel.id }">${parentNovel.title}</a>
		</td>
	</tr>
	<tr>
		<td>
			<c:if test="${EPISODE.epi_number >1 }">
				<a href="../home/loadReader.html?epi_number=${EPISODE.epi_number - 1 }&pni=${parentNovel.id}&bno=${EPISODE.bno}">◀</a>&nbsp;
			</c:if>		
			<!-- 에피소드 제목 --><span style="font-size: 1.2em;">${EPISODE.epi_number}화, ${EPISODE.epi_title }</span>&nbsp;
			<c:if test="${EPISODE.epi_number < parentNovel.episode}">
				<a href="../home/loadReader.html?epi_number=${EPISODE.epi_number + 1 }&pni=${parentNovel.id}&bno=${EPISODE.bno}">▶</a>&nbsp;
			</c:if>	
		</td>
	</tr>
	
	<tr>
	
		<td>
			<textarea style="font-size: 1.5em;" autofocus="autofocus" cols="80" rows="100" readonly="readonly">${EPISODE.content }</textarea>
	</td>
	</tr>
	<tr>
	<td>
	<div id="recoCnt">추천수:${EPISODE.reco_cnt }</div>
	</td>
	</tr>
	<tr>
	<td>	
	<c:if test="${sessionScope.LOGINMEMBER != null }"><!-- 로그인 유저만 -->
		
		<button id="favoriteBtn">관심작품</button>
		<button id="likeyBtn" >좋아요</button>
   		<button id="reportBtn" onclick="report()">신고</button>
				

	</c:if>
	
	</td>
	</tr>
</table>

<form id="replyForm" method="post">
		<table style="background-color: #e9ecef">
		<tr><td>
		
		<textarea rows="3" cols="60" name="reply" id="reply" placeholder="댓글 입력..."></textarea>
		</td></tr>
		<tr><td>
		<input style="width: 30%;" type="button" value="확인" id="replyBtn">
		</td></tr>
		</table>
		</form>
<div id="replySpace">
<a id="callReplPage" style="font-size: 200%;">${COUNT }개의 댓글이 있습니다. #댓글보기</a>
</div>
<a id="CallMoreRepl" style="font-size: 200%">댓글 더보기</a>

<div id="pageRemocon">
<button onclick="location.href='#page_first'"> ↑ </button>&nbsp;<button onclick="location.href='#page_end'"> ↓ </button>
</div>
</body>
</html>