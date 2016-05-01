$(function() {

	$(document).on('click', '.deleteBook', function() {
		bookID = $(this).attr("data-id");
		$bookPanel = $(this).parents(".book").eq(0);
		$.ajax({
			method : "POST",
			url : "/books/delete",
			data : {
				'book_id' : bookID
			},
			dataType : "json",
			processData : true,
			success : function(response) {
				$bookPanel.remove();
			},
			error : function(error) {
				alert("error !! " + error);
			}
		});

	});

	$(document).on('click', '.lendBook', function() {
		$bookPanel = $(this).parents(".book").eq(0);
		var ISBN = $bookPanel.find('.isbn').text();
		var title = $bookPanel.find('.title').text();
		$(".modal-title").text("Lend " + title);
		$("#modal_book_id").val(ISBN);
		$('#modal_lend_book').modal('show');
	});

	$('.date').datetimepicker();

	/*$(document).on('click', '#btn_lend_book', function() {
		$('#modal_form_lend')[0].reset();
		alert("click");
	});*/

})