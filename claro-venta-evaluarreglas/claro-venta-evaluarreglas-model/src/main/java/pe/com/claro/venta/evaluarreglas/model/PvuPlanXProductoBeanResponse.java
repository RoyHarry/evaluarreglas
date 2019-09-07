package pe.com.claro.venta.evaluarreglas.model;

import java.util.List;

import pe.com.claro.common.bean.BodyResponse;

public class PvuPlanXProductoBeanResponse extends BodyResponse{
	private List<PvuPlanXProductoCurBean> p_cursor;

	public List<PvuPlanXProductoCurBean> getP_cursor() {
		return p_cursor;
	}

	public void setP_cursor(List<PvuPlanXProductoCurBean> p_cursor) {
		this.p_cursor = p_cursor;
	}
}
