package exceptions;

public class NonExistingBook extends RuntimeException{

	public NonExistingBook(String isbn) {
		super("Book with isbn: " + isbn + " does not exist" );
	}
}
