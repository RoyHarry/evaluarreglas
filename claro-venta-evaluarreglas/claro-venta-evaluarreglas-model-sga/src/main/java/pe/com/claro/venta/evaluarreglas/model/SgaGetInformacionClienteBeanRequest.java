package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;

public class SgaGetInformacionClienteBeanRequest implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pdocumento;
	private String pTipoDocumento;
	private Integer pCantProm;
	
	public String getPdocumento() {
		return pdocumento;
	}
	public void setPdocumento(String pdocumento) {
		this.pdocumento = pdocumento;
	}
	public String getpTipoDocumento() {
		return pTipoDocumento;
	}
	public void setpTipoDocumento(String pTipoDocumento) {
		this.pTipoDocumento = pTipoDocumento;
	}
	public Integer getpCantProm() {
		return pCantProm;
	}
	public void setpCantProm(Integer pCantProm) {
		this.pCantProm = pCantProm;
	}
	
	
}
