<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<jsp:include page="../libs/libsStyles.jsp" flush="false" />
</head>


<body>

	<div>
				
		<h3>회원 리스트</h3>
		<hr>

		<table class="table table-hover" id = "table">
		<tr> 
		<!-- HTML 태그에 속성으로 이벤트 리스너를 등록 -->
		<th><input type = "checkbox" name = "member_check" value="0" 
									onclick='selectAll(this)'>선택</th>
		<th>No.</th>
		<th>닉네임</th>
		<th>이메일</th>
		<th>권한</th>
		<th>연동계정</th>
		<th>가입일</th>

		</tr>
		<tbody id = "product_list">
		<!-- 등록된 회원 리스트 삽입  -->
		<c:forEach items="${memberlist }" var="memberDTO">
		<tr><td class = "table_cnt"><input type = "checkbox" name = "member_check" ></td><td class = "table_cnt"></td><td>${memberDTO.mbNick }</td><td>${memberDTO.mbEmail}</td><td>${memberDTO.mbRole}</td><td>${memberDTO.mbGuard }</td><td>${memberDTO.mbJoinDate}</td></tr>
		</c:forEach>
		
		</tbody>

		</table>
						
	</div>	
	
	
	<script src="/static/js/admin/member.js"></script>

	<jsp:include page="../libs/libsScript.jsp" flush="false" />
		
	<script>
	  // 체크박스 전체선택, 전체 해제 함수
	  function selectAll(selectAll)  {
	      const checkboxes = document.getElementsByName('member_check');
	      
	      alert(checkboxes.length-1 + "개 선택 되었습니다.");
	      
	      checkboxes.forEach((checkbox) => {
	          checkbox.checked = selectAll.checked;})
	  };

	  // 체크박스 선택된 것들의 상품 id를 배열에 담는 함수
	  function getChecked() {
	      const checkedmembers = document.querySelectorAll('input[name="member_check"]:checked');
	      let checked_ids = [];
	      checkedmembers.forEach((id) => {
	          if(id.value != 0) {
	              checked_ids.push(id.value);
	          }
	      })
	      return checked_ids;
	  }
	</script>
</body>
</html>