package pe.com.claro.venta.evaluarreglas.model;

import java.util.List;

import pe.com.claro.common.bean.BodyResponse;

public class PvuCalculoLCxProductoBeanResponse extends BodyResponse{
	
	private List<PvuCalculoLCxProductoCurBean> c_producto_lc;	
	public List<PvuCalculoLCxProductoCurBean> getC_producto_lc() {
		return c_producto_lc;
	}
	public void setC_producto_lc(List<PvuCalculoLCxProductoCurBean> c_producto_lc) {
		this.c_producto_lc = c_producto_lc;
	}

}
