$(function() {

	var CP = {

		elems: {
			friendListItems: $('.friends-list_item')
			, choosePartnerButton: $('#choose-partner-button')
		}

		, bindUserSelection: function() {
			log('bindUserSelection - inited');

			CP.elems.friendListItems.bind('click', function() {
				if ($(this).hasClass('friends-list_item__selected')) return;

				CP.elems.friendListItems.removeClass('friends-list_item__selected');

				$(this).addClass('friends-list_item__selected');

				CP.elems.choosePartnerButton.removeClass('disabled');
			});

		}

		, bindFormSubmit: function() {
			log('bindFormSubmit - inited');

			CP.elems.choosePartnerButton.bind('click', function(e) {
				if ($(this).hasClass('disabled')) e.preventDefault();

			});
		}

		, init: function() {
			CP.bindUserSelection();
			CP.bindFormSubmit();

		}
	};

	CP.init();
});





