package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BscsGetCoidResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal result;

	public BigDecimal getResult() {
		return result;
	}

	public void setResult(BigDecimal result) {
		this.result = result;
	}
	
	
	
}
