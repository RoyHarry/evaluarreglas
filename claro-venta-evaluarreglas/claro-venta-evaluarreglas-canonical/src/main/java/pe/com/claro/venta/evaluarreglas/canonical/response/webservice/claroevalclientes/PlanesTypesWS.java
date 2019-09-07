package pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes;

import java.util.List;

public class PlanesTypesWS {

    private List<RespClaroEvalPlanSolic> detallePlanSolicitado;

    private List<RespProactivaPlanAdicionalWS> detallePlanesAdicionales;

	public List<RespClaroEvalPlanSolic> getDetallePlanSolicitado() {
		return detallePlanSolicitado;
	}

	public void setDetallePlanSolicitado(List<RespClaroEvalPlanSolic> detallePlanSolicitado) {
		this.detallePlanSolicitado = detallePlanSolicitado;
	}

	public List<RespProactivaPlanAdicionalWS> getDetallePlanesAdicionales() {
		return detallePlanesAdicionales;
	}

	public void setDetallePlanesAdicionales(List<RespProactivaPlanAdicionalWS> detallePlanesAdicionales) {
		this.detallePlanesAdicionales = detallePlanesAdicionales;
	}
    
    
}
