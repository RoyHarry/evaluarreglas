package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;
import java.util.List;

public class SgaGetInformacionClienteBeanResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<SgaCabeceraInfCurBean> p_cant_pp_cabecera_inf;
	private List<SgaDetalleInfCurBean> p_detalles_inf;
	
	private String codigoRespuesta;
    private String mensajeRespuesta;
    
	public List<SgaCabeceraInfCurBean> getP_cant_pp_cabecera_inf() {
		return p_cant_pp_cabecera_inf;
	}
	public void setP_cant_pp_cabecera_inf(List<SgaCabeceraInfCurBean> p_cant_pp_cabecera_inf) {
		this.p_cant_pp_cabecera_inf = p_cant_pp_cabecera_inf;
	}
	public List<SgaDetalleInfCurBean> getP_detalles_inf() {
		return p_detalles_inf;
	}
	public void setP_detalles_inf(List<SgaDetalleInfCurBean> p_detalles_inf) {
		this.p_detalles_inf = p_detalles_inf;
	}
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}
	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}
	
	
}
