$(function(){
	var $container = $('#masonry');
	$container.imagesLoaded( function(){
		$container.masonry({
			itemSelector : '.box',
			gutterWidth : 20,
			isAnimated: true,
		});
	});
});