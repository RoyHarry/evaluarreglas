package pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes;

public class ClaroEvalClientesReglasResponseWS {
	private String decisionID;
	private OfrecimientoWS ofrecimientoWS;
	public String getDecisionID() {
		return decisionID;
	}
	public void setDecisionID(String decisionID) {
		this.decisionID = decisionID;
	}
	public OfrecimientoWS getOfrecimiento() {
		return ofrecimientoWS;
	}
	public void setOfrecimiento(OfrecimientoWS ofrecimientoWS) {
		this.ofrecimientoWS = ofrecimientoWS;
	}
}
