
public class ZipCodeApp {

	public static void main(String[] args) {
		ZipCode z1 = new ZipCode();

		try {
			z1.setZipCode("123456789d");
		} catch (InvalidZipCodeException zce) {
			System.out.println(zce.getMessage());
		}
	}
}
