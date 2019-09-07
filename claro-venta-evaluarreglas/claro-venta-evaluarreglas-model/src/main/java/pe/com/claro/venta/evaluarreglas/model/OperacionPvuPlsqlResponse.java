package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;

public class OperacionPvuPlsqlResponse implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	private String codigoRespuesta;
	private String mensajeRespuesta;
	private String codigo;
	
	public OperacionPvuPlsqlResponse() {
		super();		
	}
	
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}

	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
