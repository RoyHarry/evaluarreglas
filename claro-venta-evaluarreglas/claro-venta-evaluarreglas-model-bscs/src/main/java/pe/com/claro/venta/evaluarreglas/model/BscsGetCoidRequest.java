package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BscsGetCoidRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	
	private String lineaClinete;

	public String getLineaClinete() {
		return lineaClinete;
	}

	public void setLineaClinete(String lineaClinete) {
		this.lineaClinete = lineaClinete;
	}
	
}
