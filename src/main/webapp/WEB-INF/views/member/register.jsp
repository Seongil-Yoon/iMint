<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="multi.fclass.iMint.security.dao.ISecurityDAO" %>
<%@ page import="multi.fclass.iMint.member.dto.Role" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<jsp:include page="../libs/libsStyles.jsp" flush="false" />
</head>

<body>
 	<jsp:include page="../include/header.jsp" flush="false" />

 	<h3>회원가입(SNS연동)</h3>	
 	<hr>
 	<h6>${err}</h6>
	
	<br>
 	<form id = "change_url"> <!-- input type = submit, prevent -->
 		<div>닉네임 <button type="button" id = "nick_btn">닉네임 중복확인</button><p id = "nickappend"></p>
	 		<input type = text name = "mbNick" id = "mbNick" placeholder = ${memberDTO.mbNick } class = "memberInfo">
 		</div>
 		<div>이메일<br>
	 		<input type = text name = "mbEmail" id = "mbEmail" value = ${memberDTO.mbEmail } class = "memberInfo">
 		</div>
 		<div id = "interest">관심사<br>
			<select class="memberInfo" id="floatingSelect" name = "mbInterest" id = "mbInterest"
				aria-label="Floating label select example">
			<option value="문구" selected>문구</option>
			<option value="완구">완구</option>
			<option value="도서">도서</option>
			<option value="교구">교구</option>
			<option value="의류">의류</option>
			<option value="가방/신발/잡화">가방/신발/잡화</option>
			<option value="기타">기타</option>
			</select> 		
 		</div>
 		<input type = hidden name = "mbId" id = "mbId" value = ${memberDTO.mbId }>
 		<input type = hidden name = "mbRole" id = "mbRole" value = ${memberDTO.mbRole }>
    	<input type = "submit" id = "register_btn" value = "회원가입">
	</form>
	
	<jsp:include page="../libs/libsScript.jsp" flush="false" />

<!-- 	<script src="/static/js/register.js"></script> -->
	
	<script>
	/* ajax로 닉네임 중복확인 */ 
		$("#nick_btn").on('click', function(){
			var mbNick = $("#mbNick").val();
			$.ajax({
				url: "/register/nickname",
				type: 'get',
				data: {'nickcheck' : $("#mbNick").val(),
					'mbId' : $("#mbId").val()
					}, 
				dataType: "json",
					
					success: function(response) { /* 중복확인 결과  */
						alert(JSON.stringify(response.nickcheck));
 						if(response.result == "ok"){
							$("#nickappend").html('<p style = color: black>사용가능한 닉네임입니다.</p>');
							alert("사용 가능한 닉네임입니다.");
 							
 							$("#change_url").attr("action", "/register");		
							$("#change_url").attr("method", "post"); 
							$("#register_btn").unbind(); // 회원가입 가능
 						} // if end 
						else{
 							$("#nickappend").html('<p style = color: red>다른 사용자가 이미 사용중인 닉네임입니다.</p>');
 							$("#register_btn").preventDefault(); // 회원가입 불가 
 						} // else end
					} // success end 	
			}); // ajax end
		});

	</script>	
	
</body>

<style>
.memberInfo{
width: 300px;
}
</style>

</html>