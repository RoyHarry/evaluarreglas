package pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes;

import java.io.Serializable;

public class ClaroEvalClientesReglasRequestWS implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String decisionId;
	private String flagTipoOperacion;
	private Solicitud solicitud;
	public String getDecisionId() {
		return decisionId;
	}
	public void setDecisionId(String decisionId) {
		this.decisionId = decisionId;
	}
	public Solicitud getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}
	public String getFlagTipoOperacion() {
		return flagTipoOperacion;
	}
	public void setFlagTipoOperacion(String flagTipoOperacion) {
		this.flagTipoOperacion = flagTipoOperacion;
	}
	
}
