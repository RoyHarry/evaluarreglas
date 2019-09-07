package pe.com.claro.common.exception;

public class ConvertException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String code;
	private final String message;
	private final String messageDeveloper;

	public ConvertException(String code, String message, String messageDeveloper) {
		super(message);
		this.code = code;
		this.message = message;
		this.messageDeveloper = messageDeveloper;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	public String getCode() {
		return code;
	}
	public String getMessageDeveloper() {
		return messageDeveloper;
	}
	@Override
	public String toString() {
		return "ConvertException [code=" + code + ", message=" + message + ", messageDeveloper=" + messageDeveloper
				+ "]";
	}
	
	
}
