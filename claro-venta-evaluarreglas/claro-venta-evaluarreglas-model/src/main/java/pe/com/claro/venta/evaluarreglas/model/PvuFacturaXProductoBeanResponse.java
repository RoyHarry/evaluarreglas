package pe.com.claro.venta.evaluarreglas.model;

import java.util.List;

import pe.com.claro.common.bean.BodyResponse;

public class PvuFacturaXProductoBeanResponse extends BodyResponse{
	private List<PvuFacturaXProductoCurBean> c_producto_fact;

	public List<PvuFacturaXProductoCurBean> getC_producto_fact() {
		return c_producto_fact;
	}
	public void setC_producto_fact(List<PvuFacturaXProductoCurBean> c_producto_fact) {
		this.c_producto_fact = c_producto_fact;
	}	
}
