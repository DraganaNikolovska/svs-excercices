package exceptions;

public class NonExistingMagazine extends RuntimeException {

	public NonExistingMagazine(String issn) {
		super("Magazine with issn: " + issn + " does not exist");
	}
}
