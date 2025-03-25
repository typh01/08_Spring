<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문자문자</title>
<style>
	#wrap{
		width : 1000px;
		min-height : 400px;
		margin : auto;
		height : auto;
		border : 1px solid rgba(0, 0, 0, 0.6);
		border-radius : 16px;
		background-color : rgba(16, 16, 16, 0.8);
		padding : 20px;
		margin-top : 30px;
	}
	
	#wrap > div{
		width : 100%;
	}
	
	#content{
		width : 90%
		margin : auto;
		height : auto;
		min-height : 300px;
	}
	
	button{
		width : 100%;
	}
	
	.message {
    border: 2px solid #008CBA;
    display: inline-block;
    width: 250px;
    height: 300px;
    text-align: center;
    margin: 30px;
    padding: 15px;
    border-radius: 15px; /* 둥근 모서리 추가 */
    box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.2); /* 그림자 효과 */
    background-color: #f0f8ff; /* 밝은 배경색 추가 */
}

.content {
    height: 250px;
    font-size: 16px;
    color: #333;
    display: flex;
    align-items: center; /* 수직 정렬 */
    justify-content: center; /* 수평 정렬 */
}

.category {
    color: #ffffff;
    background-color: #008CBA; /* 색 대비를 위해 배경색 추가 */
    padding: 5px 10px; /* 여백 추가 */
    border-radius: 5px; /* 둥근 모서리 */
    font-weight: bold;
}

.region {
    color: yellow;
    font-weight: bold;
    text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.3); /* 텍스트 그림자 추가 */
}

	
</style>
</head>
<body>
		
	<jsp:include page="../include/header.jsp"/>
	
	
	<div id="wrap">
		<div id="content">
		
		</div>
		
		<div>
			<button class="btn btn-outline-secondary" onclick="getMessage();">더보기 🔻</button>
		</div>
	</div>	
	
	<script> /* 이 문서가 모두 로딩된 후 */
		$(function(){
			getMessage();
			
		});
	
		let pageNo = 1;
		function getMessage(){
			$.ajax({
				url : `message?pageNo=\${pageNo}`,
				type : 'get',
				success : result =>{
					//console.log(result);
					const messages = result.body;
					
					const outputStr = messages.map(e => 
												  `
												  <div class="message">
												  	<h3 class="category">\${e.DST_SE_NM}</h3>
												  	<h6 class ="region">\${e.RCPTN_RGN_NM}</h6>
												  	<p class="content">\${e.MSG_CN}</p>
												  </div>
												  `
												   ).join('');
					$('#content').append(outputStr);
					pageNo++;
				}
			});
			}
	</script>
		
	<jsp:include page="../include/footer.jsp"/>
	
</body>
</html>