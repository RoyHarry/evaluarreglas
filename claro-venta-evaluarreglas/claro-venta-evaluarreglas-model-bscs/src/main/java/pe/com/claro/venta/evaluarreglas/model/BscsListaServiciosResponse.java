package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BscsListaServiciosResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ptmcode;
	private String ptmdes;
	private BigDecimal errnum;
	private String errMensaje;
	
	public String getPtmcode() {
		return ptmcode;
	}
	public void setPtmcode(String ptmcode) {
		this.ptmcode = ptmcode;
	}
	public String getPtmdes() {
		return ptmdes;
	}
	public void setPtmdes(String ptmdes) {
		this.ptmdes = ptmdes;
	}
	public BigDecimal getErrnum() {
		return errnum;
	}
	public void setErrnum(BigDecimal errnum) {
		this.errnum = errnum;
	}
	public String getErrMensaje() {
		return errMensaje;
	}
	public void setErrMensaje(String errMensaje) {
		this.errMensaje = errMensaje;
	}
	
	
	
}
