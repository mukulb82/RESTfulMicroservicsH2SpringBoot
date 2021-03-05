package vo;

import java.io.Serializable;

public class ApiResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ApiResponse () {
		
	}
	
	public ApiResponse(String code, String msg) {
		
		this.code = code;
		this.message = msg;
	}

	private String code;
	
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
