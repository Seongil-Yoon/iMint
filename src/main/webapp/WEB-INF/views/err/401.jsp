private final String ERROR_401_PAGE_PATH = "/err/401";<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>401 | iMint</title>

  <!-- CSS -->
  <jsp:include page="../libs/libsStyles.jsp" flush="false" />
  <link rel="stylesheet" href="/static/css/error.css" />
</head>

<body>
  <div id="main">
    <div class="fof">
      <h1><a href="/register">로그인먼저 해주세요🙏</a></h1>
      <img src="/static/images/401.png" alt="">
    </div>
  </div>
  <div>
    CODE: <span>"${code}"</span><br>
    MSG: <span>"${msg}"</span><br>
    TIMESTAMP: <span>"${timestamp}"</span><br>
  </div>

  <jsp:include page="../libs/libsScript.jsp" flush="false" />
</body>

</html>