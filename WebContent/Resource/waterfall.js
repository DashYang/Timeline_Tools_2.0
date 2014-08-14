$(function() {
	var $container = $('#masonry');
	$container.imagesLoaded(function() {
		$container.masonry({
			itemSelector : '.box',
			gutterWidth : 20,
			columnWidth : function(containerWidth) {
				return containerWidth / 15;
			},
			isFitWidth : true,
			isAnimated : true,
		});
	});
});
