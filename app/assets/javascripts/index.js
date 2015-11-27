/**
 * @file index.js
 * @copyright Copyright © 2015 by Intercom, Inc. All rights reserved.
 */

$(function () {
	var form = document.getElementById('formSecret');


	form.addEventListener('submit', function (e) {
		e.preventDefault();

		$.ajax({
			// url: 'https://login.live.com/accesstoken.srf',
			url: 'https://login.live.com/accesstoken.srf',
			type: 'POST',
			contentType: 'application/x-www-form-urlencoded',
			data: $.param({
				grant_type: $('#grant_type').val(),
				client_id: $('#client_id').val(),
				client_secret: $('#client_secret').val(),
				scope: $('#scope').val()
				// scope: 's.notify.live.net'
			}),
			cache: false,
			success: function (data, textStatus, jqXHR) {
				console.log(textStatus);
				console.log(data);
				console.log(jqXHR);
				// console.log(JSON.parse(jqXHR.responseText));
				alert(textStatus);
			},
			error: function (jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
				console.log(textStatus);
				// console.log(JSON.parse(jqXHR.responseText));
				alert(textStatus);
			}
		});

		// WL.Event.subscribe('auth.sessionChange', onLogin);
		// WL.init({
		// 	grant_type : $('#grant_type').val(),
		// 	client_id : $('#client_id').val(),
		// 	client_secret : $('#client_secret').val(),
		// 	scope : $('#scope').val()
		// });

		// function onLogin(s) {
		// 	console.log(s);
		// 	window.confirm('login');
		// }

	});

	var formNotification = document.getElementById('formSendNotification');

	formNotification.addEventListener('submit', function (e) {
		e.preventDefault();

		$.ajax({
			url: '/hub/send',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({
				message: $('#message_text').val()
			}),
			timeout: 300000,
			cache: false,
			success: function (data, textStatus, jqXHR) {
				console.log(textStatus);
				console.log(data);
				console.log(jqXHR);
				// console.log(JSON.parse(jqXHR.responseText));
				alert(textStatus);
			},
			error: function (jqXHR, textStatus, errorThrown) {
				$(document.body).html(jqXHR.responseText);
			}
		});

	});

});
