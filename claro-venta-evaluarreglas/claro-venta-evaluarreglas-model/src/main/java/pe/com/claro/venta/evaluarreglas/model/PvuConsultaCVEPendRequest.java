package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;

public class PvuConsultaCVEPendRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String p_tipo_doc;
	private String p_nro_doc;
	
	public String getP_tipo_doc() {
		return p_tipo_doc;
	}
	public void setP_tipo_doc(String p_tipo_doc) {
		this.p_tipo_doc = p_tipo_doc;
	}
	public String getP_nro_doc() {
		return p_nro_doc;
	}
	public void setP_nro_doc(String p_nro_doc) {
		this.p_nro_doc = p_nro_doc;
	}
	

	

}
