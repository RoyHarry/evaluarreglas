package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BscsDetalleCFXContrato implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal poCargoFijo;
	private Integer poCodResp;
	private String poMsgResp;
	public BigDecimal getPoCargoFijo() {
		return poCargoFijo;
	}
	public void setPoCargoFijo(BigDecimal poCargoFijo) {
		this.poCargoFijo = poCargoFijo;
	}
	public Integer getPoCodResp() {
		return poCodResp;
	}
	public void setPoCodResp(Integer poCodResp) {
		this.poCodResp = poCodResp;
	}
	public String getPoMsgResp() {
		return poMsgResp;
	}
	public void setPoMsgResp(String poMsgResp) {
		this.poMsgResp = poMsgResp;
	}
	
	
}
