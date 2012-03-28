$(function() {

	var CP = {

		bindUserSelection: function() {
			log('bindUserSelection - inited');

			var friendListItems = $('.friends-list_item');

			friendListItems.bind('click', function() {
				if ($(this).hasClass('friends-list_item__selected')) return;

				friendListItems.removeClass('friends-list_item__selected');

				$(this).addClass('friends-list_item__selected');
			});

		}
		, init: function() {
			CP.bindUserSelection();
		}
	};

	CP.init();
});





