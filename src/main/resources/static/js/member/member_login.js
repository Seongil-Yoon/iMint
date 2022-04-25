
$(document).ready(function() {
	<c:if test="${!empty register ? true : false}">
		alert(${register} + "님 회원가입을 축하합니다! 로그인 후 사이트를 이용해주세요.");
  	</c:if>
	
	$(window).scroll(function() {
		$('.fadeinleft').each(function(i) {

			var bottom_of_element = $(this).offset().top + $(this).outerHeight();
			var bottom_of_window = $(window).scrollTop() + $(window).height();

			if (bottom_of_window > bottom_of_element) {
				$(this).animate({ 'opacity': '1', 'margin-left': '0px' }, 2000);
			}

		});
	});
});