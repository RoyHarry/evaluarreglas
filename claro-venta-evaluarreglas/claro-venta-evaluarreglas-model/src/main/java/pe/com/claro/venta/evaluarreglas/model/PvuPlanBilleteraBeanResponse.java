package pe.com.claro.venta.evaluarreglas.model;

import java.util.List;

import pe.com.claro.common.bean.BodyResponse;

public class PvuPlanBilleteraBeanResponse extends BodyResponse{
	private List<PvuPlanBilleteraCurBean> p_consulta;
	public List<PvuPlanBilleteraCurBean> getP_consulta() {
		return p_consulta;
	}
	public void setP_consulta(List<PvuPlanBilleteraCurBean> p_consulta) {
		this.p_consulta = p_consulta;
	}
}
