package model;

public class UserRequest {
	
	private String username;

	public UserRequest() {
		
	}
	
	public UserRequest(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserRequest [username=").append(username).append("]");
		return builder.toString();
	}
}
