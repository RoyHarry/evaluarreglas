package pe.com.claro.venta.evaluarreglas.model;

import java.util.List;

public class BscsDetalleXLineaBeanResponse {
	private List<BscsDXLpCurClienteBean> P_CUR_CLIENTE;
	private List<BscsDXLpCurDetalleBean> P_CUR_DETALLE;
	
	public List<BscsDXLpCurDetalleBean> getP_CUR_DETALLE() {
		return P_CUR_DETALLE;
	}
	public void setP_CUR_DETALLE(List<BscsDXLpCurDetalleBean> p_CUR_DETALLE) {
		P_CUR_DETALLE = p_CUR_DETALLE;
	}
	public List<BscsDXLpCurClienteBean> getP_CUR_CLIENTE() {
		return P_CUR_CLIENTE;
	}
	public void setP_CUR_CLIENTE(List<BscsDXLpCurClienteBean> p_CUR_CLIENTE) {
		P_CUR_CLIENTE = p_CUR_CLIENTE;
	}
}
