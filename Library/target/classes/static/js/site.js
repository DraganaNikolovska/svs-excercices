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

	$(document).on('click', '.deleteMagazine', function() {
		magazineID = $(this).attr("data-id");
		$magazinePanel = $(this).parents(".magazine").eq(0);
		$.ajax({
			method : "POST",
			url : "/magazines/delete",
			data : {
				'magazine_id' : magazineID
			},
			dataType : "json",
			processData : true,
			success : function(response) {
				$magazinePanel.remove();
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

	$(document).on('click', '.lendMagazine', function() {
		$magazinePanel = $(this).parents(".magazine").eq(0);
		var ISBN = $magazinePanel.find('.isbn').text();
		var title = $magazinePanel.find('.title').text();
		$(".modal-title").text("Lend " + title);
		$("#modal_magazine_id").val(ISBN);
		$('#modal_lend_magazine').modal('show');
	});
	
	$('.date').datetimepicker();

})