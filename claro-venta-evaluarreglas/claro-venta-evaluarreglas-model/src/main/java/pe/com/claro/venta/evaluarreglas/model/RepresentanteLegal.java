package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;

public class RepresentanteLegal implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Documento documento;
	private String riesgo;
	public Documento getDocumento() {
		return documento;
	}
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	public String getRiesgo() {
		return riesgo;
	}
	public void setRiesgo(String riesgo) {
		this.riesgo = riesgo;
	}
	
}
