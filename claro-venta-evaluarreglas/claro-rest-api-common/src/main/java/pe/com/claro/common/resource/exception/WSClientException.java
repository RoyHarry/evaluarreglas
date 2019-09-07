package pe.com.claro.common.resource.exception;

public class WSClientException extends BaseException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WSClientException(String mensajeError, Exception e) {
		super(mensajeError, e);
	}

	public WSClientException(String codigoError, String mensajeError) {
		super(codigoError, mensajeError);
	}

	public WSClientException(String codError, String mensajeError, Exception objException) {
		super(codError, mensajeError, objException);
	}

	public WSClientException(String mensajeError) {
		super(mensajeError);
	}

}
