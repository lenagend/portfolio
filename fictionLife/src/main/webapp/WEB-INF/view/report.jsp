<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

</script>
</head>
<body>
 		
	<div id="reportForm">
		<form action="../novel/report.html?bno=${bno }" method="post">
		<input type="hidden" name="epi_number" value="${epiNum }">
		<input type="hidden" name="pni" value="${pid  }">
		<table>
			<tr>
			<td>
			<table>
			<tr><td><input type="radio" name="reportTitle" value="음란성 게시물">음란성 게시물</td></tr>
			<tr><td><input type="radio" name="reportTitle" value="광고성 게시물">광고성 게시물</td></tr><tr><td><input type="radio" name="reportTitle" value="지나치게 폭력적인 게시물">폭력적인 게시물</td></tr>
			<tr><td><input type="radio" name="reportTitle" value="저작권 위반">저작권 위반</td></tr><tr><td><input type="radio" name="reportTitle" value="지나친 정치/종교 글">지나친 정치/종교 글</td></tr>
			<tr><td><input type="radio" name="reportTitle" value="도배성 게시물">도배성 게시물</td></tr>
			</table>		
			</td>
			<td>
				<table>
				<tr>
					<td><textarea rows="6" cols="25" name="reportContent">신고 사유</textarea></td>
				</tr>
				<tr>	
					<td><input style="width: 30%;" type="submit" value="확인"></td>
				</tr>
				</table>				
			</td>
			</tr>
		</table>
					
		</form>
	</div>
</body>
</html>