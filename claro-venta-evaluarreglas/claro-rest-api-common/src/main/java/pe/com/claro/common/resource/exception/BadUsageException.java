package pe.com.claro.common.resource.exception;

public class BadUsageException extends ApiException {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int code;
	private final Integer status;
	private final String developerMessage;
     
 	public BadUsageException(int status, int code, String msg, String developerMessage) {
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
