package exceptions;

public class NonExistingMember extends RuntimeException {

	public NonExistingMember(String email) {
		System.out.println("Member with email " + email + " does not exist");
	}
}
