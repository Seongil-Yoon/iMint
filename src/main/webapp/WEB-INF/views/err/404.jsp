<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>404 | iMint</title>

  <!-- CSS -->
  <jsp:include page="../libs/libsStyles.jsp" flush="false" />
  <link rel="stylesheet" href="/static/css/error.css" />
</head>

<body>
  <div id="main">
    <div class="fof">
      <h1>${timestamp}</h1><br>
      <h1>${status} - ${error}</h1><br>
      <h1>${message}</h1><br>
      <button type="button">
        <a href="${path}">홈으로 돌아가기</a>
      </button><br>
      <img src="/static/images/404.jpg" alt="">
    </div>
  </div>

  <jsp:include page="../libs/libsScript.jsp" flush="false" />
</body>

</html>