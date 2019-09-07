package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;

public class PvuCuotaIniComercialRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String P_CODIGOLISTAPRECIO;
	private String P_CODMATERIAL;
	private String P_CODPLAZO;
	
	
	public String getP_CODIGOLISTAPRECIO() {
		return P_CODIGOLISTAPRECIO;
	}
	public void setP_CODIGOLISTAPRECIO(String p_CODIGOLISTAPRECIO) {
		P_CODIGOLISTAPRECIO = p_CODIGOLISTAPRECIO;
	}
	public String getP_CODMATERIAL() {
		return P_CODMATERIAL;
	}
	public void setP_CODMATERIAL(String p_CODMATERIAL) {
		P_CODMATERIAL = p_CODMATERIAL;
	}
	public String getP_CODPLAZO() {
		return P_CODPLAZO;
	}
	public void setP_CODPLAZO(String p_CODPLAZO) {
		P_CODPLAZO = p_CODPLAZO;
	}
	
	
	
}
