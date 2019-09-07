package pe.com.claro.venta.evaluarreglas.model;

import java.util.List;

import pe.com.claro.common.bean.BodyResponse;

public class Pvu3Play6ConDetalleLineaBeanResponse extends BodyResponse{
	private List<Pvu3P6DetalleLineaCurCABBean> p_cursor_cab;
	private List<Pvu3P6DetalleLineaCurDETBean> p_cursor_det;
	
	public List<Pvu3P6DetalleLineaCurCABBean> getP_cursor_cab() {
		return p_cursor_cab;
	}
	public void setP_cursor_cab(List<Pvu3P6DetalleLineaCurCABBean> p_cursor_cab) {
		this.p_cursor_cab = p_cursor_cab;
	}
	public List<Pvu3P6DetalleLineaCurDETBean> getP_cursor_det() {
		return p_cursor_det;
	}
	public void setP_cursor_det(List<Pvu3P6DetalleLineaCurDETBean> p_cursor_det) {
		this.p_cursor_det = p_cursor_det;
	}
}
