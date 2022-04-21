<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>500 | iMint</title>
	
	<!-- CSS -->
	<jsp:include page="../libs/libsStyles.jsp" flush="false" />
    <link rel="stylesheet" href="/static/css/error.css" />
</head>

<body>
    <div id="main">
      <div class="fof">
        <h1><a href="/main">홈으로 돌아가기</a></h1>
        <img src="/static/images/404.jpg" alt="">
      </div>
    </div>
    
     <jsp:include page="../libs/libsScript.jsp" flush="false" />
</body>
</html>