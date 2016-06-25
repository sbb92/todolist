$(function() {

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

	$('#addToDo').click(
			function(e) {
				resetErrorMessage();
				var todo = $('#todo-value');
				if (todo.val() == "") {
					todo.val("");
					addErrorMessage("Lütfen gerekli alanı doldurunuz !");
				} else {
					$.ajax({
						url : "/home/addNote",
						type : "POST",
						data : {
							'noteName' : todo.val()
						},
						success : function(result) {
							var message = result.message;
							var status = result.status;
							if (status == "FAILED") {
								addErrorMessage(message);
							} else {
								$('#mytable tbody').append(
										"<tr id='row_" + result.message + "'><td><a id='status_" + result.message + "' href='#'	class='btn btn-primary btn-danger'>Bekliyor</a></td> <td>" + todo.val() + "</td> <td><a id='" + result.message
												+ "' href='#' class='btn btn-default btn-default change-status'><span class='glyphicon glyphicon-repeat'></span></a></td>" + "<td><a id='" + result.message
												+ "'+ href='#' class='btn btn-default btn-default delete-note'><span class='glyphicon glyphicon-remove'></span></a></td></tr>");
								todo.val("");
							}
						}
					});
				}

			});
	$('#logOutButton').click(
			function(e) {
				resetErrorMessage();
				$.ajax({
					url : "/login/logout",
					type : "POST",
					success : function(result) {
						var message = result.message;
						var status = result.status;
						if (status == "FAILED") {
							addErrorMessage(message);
						} else {
							window.location.href = "/login";
						}
					}
				});

			});

	$(document).on('click', '.delete-note', function(e) {
		resetErrorMessage();
		var id = $(this).attr('id');
		var tr = $(this).closest("tr");
		$.ajax({
			url : "/home/removeNote",
			type : "POST",
			data : {
				'noteId' : id
			},
			success : function(result) {
				var message = result.message;
				var status = result.status;
				if (status == "FAILED") {
					addErrorMessage(message);
				} else {
					tr.remove();
				}
			}
		});

	});

	$(document).on('click', '.change-status', function(e) {
		resetErrorMessage();
		var buttonStatus = 0;
		var id = $(this).attr('id');
		var statusButtonId = '#status_' + id;
		var statusButton = $(statusButtonId);
		var statusClass = statusButton.text();
		if (statusClass == "Bekliyor") {
			buttonStatus = 1;
		}
		$.ajax({
			url : "/home/updateStatus",
			type : "POST",
			data : {
				'noteId' : id,
				'status' : buttonStatus
			},
			success : function(result) {
				var message = result.message;
				var status = result.status;
				if (status == "FAILED") {
					addErrorMessage(message);
				} else {
					if (buttonStatus == 1) {
						statusButton.attr("class", "btn btn-primary btn-success");
						statusButton.text("Yapıldı");
					} else {
						statusButton.attr("class", "btn btn-primary btn-danger");
						statusButton.text("Bekliyor");
					}
				}
			}
		});

	});

});
