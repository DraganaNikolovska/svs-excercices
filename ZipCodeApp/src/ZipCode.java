
public class ZipCode {

	private String zipCode;
	
	public void setZipCode(String zipCode) {
	
		if(zipCode.matches("^[0-9]{5}") || zipCode.matches("^[0-9]{9}")){
			this.zipCode = zipCode;
		} else throw new InvalidZipCodeException("Zip code format is invalid");
		
			
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
}
