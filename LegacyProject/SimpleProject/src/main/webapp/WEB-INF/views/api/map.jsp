<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지도!</title>
</head>
<body>

	<jsp:include page="../include/header.jsp"/>
	
				
	<div id="map" style="width:1200px; height:600px; margin:auto;"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=서비스키 입력하셈"></script>
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(33.450701, 126.570667),
			level: 3
		};
	
		var map = new kakao.maps.Map(container, options);
	</script>
	
	<jsp:include page="../include/footer.jsp"/>


</body>
</html>