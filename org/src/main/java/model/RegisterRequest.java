package model;

public class RegisterRequest {
	
	private String email;
	
	private String username;
	
	private String password;
	
	private String address;
	
	private String city;
	
	private String country;
	
	private String zip;
	
	private String phoneNumber;
	
	RegisterRequest(){
		
	}

	public RegisterRequest(String email, String username, String password, String address, String city, String country,
			String zip) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.address = address;
		this.city = city;
		this.country = country;
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RegisterRequest [email=").append(email).append(", username=").append(username)
				.append(", password=").append(password).append(", address=").append(address).append(", city=")
				.append(city).append(", country=").append(country).append(", zip=").append(zip).append("]");
		return builder.toString();
	}
	
	

}
