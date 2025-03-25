<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공공데이터 활용(병원데이터 출력해보기)</title>
</head>
<body>
	
	<jsp:include page="../include/header.jsp"/>
	<h1>병원 데이터 출력~!!!!!!!!!!!!!</h1>
	
	<button class="btn btn-sm btn-info" onclick="callHospital();">병원 정보 보기</button>
	<div id="result-div" style="width : 900px; height : 900px; margin:auto;">
		<table class="table">
			<thead>
				<tr>
					<th width="200">병원명</th>
					<th width="400">주소</th>
					<th width="300">오시는 길</th>
					<th width="150">전화번호</th>
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
	</div>	
	<script>
		
	function callHospital() {
		
		$.ajax({
			url : 'hospitals',
			type : 'get',
			success : result =>{
				//console.log(result);
				const hospitals = result.body;
				const resultEl = hospitals.map(e =>
							`
								<tr>
									<td>\${e.INST_NM}</td>
									<td>\${e.ADDR}</td>
									<td>\${e.ESNS_RGHMP}</td>
									<td>\${e.RPRS_TLHN_1}</td>
								</tr>
							`
				).join('');
				document.querySelector('tbody').innerHTML = resultEl;
			}
		})
		/* 보안을 위해서 API는 뒷단에서 작업! */
	}
	
	</script>
	
	<jsp:include page="../include/footer.jsp"/>


</body>
</html>