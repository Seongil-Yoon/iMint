// 로그인 페이지에서 스크롤을 아래로 내리면 fade in으로 나타나는 효과 
$(document).ready(function() {
	$(window).scroll(function() {
		$('.fadeinleft').each(function(i) {

			var start_of_element = $(this).offset().top; // 하단으로 스크롤 하자마자 fade in
			var bottom_of_window = $(window).scrollTop() + $(window).height(); 

			if (bottom_of_window > start_of_element) {
				$(this).animate({ 'opacity': '1', 'margin-left': '0px' }, 1700);
			}
		});
	});
});