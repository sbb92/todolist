$(function() {

	$('#login-form-link').click(function(e) {
		resetErrorMessage();
		$("#login-form").delay(100).fadeIn(100);
		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		resetErrorMessage();
		$("#register-form").delay(100).fadeIn(100);
		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

	$('#login-submit').click(function(e) {
		var email = $('#login-email')
		var password = $('#login-password')
		if (email.val() == "" || password.val() == "") {
			email.val("");
			password.val("");
			addErrorMessage("Lütfen email ve şifre alanlarını doldurunuz ! ");
		} else {
			$.ajax({
				url : $("#login-form").attr("action"),
				type : "POST",
				data : {
					'email' : email.val(),
					'password' : password.val()
				},
				success : function(result) {
					var message = result.message;
					var status = result.status;
					if (status == "FAILED") {
						addErrorMessage(message);
					} else {
						window.location.href = "/home";
					}
				}
			});
		}

	});

	$('#register-submit').click(function(e) {
		var name = $('#register-name')
		var surname = $('#register-surname')
		var email = $('#register-email')
		var password = $('#register-password')
		if (email.val() == "" || password.val() == "" || name.val() == "" || surname.val() == "") {
			name.val("");
			surname.val("");
			email.val("");
			password.val("");
			addErrorMessage("Lütfen gerekli alanlarını doldurunuz ! ");
		} else {
			$.ajax({
				url : $("#register-form").attr("action"),
				type : "POST",
				data : {
					'name' : name.val(),
					'surname' : surname.val(),
					'email' : email.val(),
					'password' : password.val()
				},
				success : function(result) {
					var message = result.message;
					var status = result.status;
					if (status == "FAILED") {
						addErrorMessage(message);
					} else {
						window.location.href = "/home";
					}
				}
			});
		}
	});

	function addErrorMessage(message) {
		var errorMessage = $("#error-message");
		errorMessage.addClass("alert alert-danger");
		errorMessage.html(message);
	}

	function resetErrorMessage() {
		var errorMessage = $("#error-message");
		errorMessage.removeClass("alert alert-danger");
		errorMessage.html("");
	}
});
