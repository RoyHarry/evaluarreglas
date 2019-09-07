package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;

public class Documento implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TipoDeDocumento descripcion;
	private String numero;
	public TipoDeDocumento getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(TipoDeDocumento descripcion) {
		this.descripcion = descripcion;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
}
