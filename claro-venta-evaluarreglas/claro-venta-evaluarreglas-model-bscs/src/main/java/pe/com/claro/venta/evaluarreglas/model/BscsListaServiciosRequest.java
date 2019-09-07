package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BscsListaServiciosRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal coid;
	
	public BigDecimal getCoid() {
		return coid;
	}
	public void setCoid(BigDecimal coid) {
		this.coid = coid;
	}
	
	
}
