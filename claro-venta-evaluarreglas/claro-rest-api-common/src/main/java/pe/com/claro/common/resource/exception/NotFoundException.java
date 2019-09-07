
package pe.com.claro.common.resource.exception;

public class NotFoundException extends ApiException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8992570382210399390L;
	private final int code;
	private final Integer status;
	private final String developerMessage;

	public NotFoundException(int status, int code, String msg, String developerMessage) {
		super(status, code, msg, developerMessage);
		this.status = status;
		this.code = code;
		this.developerMessage = developerMessage;
	}
	
	@Override
	public int getCode() {
		return code;
	}
	@Override
	public Integer getStatus() {
		return status;
	}
	@Override
	public String getDeveloperMessage() {
		return developerMessage;
	}
}
