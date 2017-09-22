package model;

public class ResponseMessage {
	
	String statusCode;
	
	String message;
	
	
	

	public ResponseMessage(String statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResponseMessage [statusCode=" + statusCode + ", message=" + message + "]";
	}
	
	

}
