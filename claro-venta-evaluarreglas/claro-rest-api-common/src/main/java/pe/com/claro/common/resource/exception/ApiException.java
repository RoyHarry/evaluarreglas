
package pe.com.claro.common.resource.exception;

public class ApiException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4463545099205456203L;
	private final int code;
	private final Integer status;
	private final String developerMessage;

	public ApiException(int status, int code, String msg, String developerMessage) {
		super(msg);
		this.status = status;
		this.code = code;
		this.developerMessage = developerMessage;
	}

	public int getCode() {
		return code;
	}

	public Integer getStatus() {
		return status;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

}
