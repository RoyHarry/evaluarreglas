package pe.com.claro.venta.evaluarreglas.model;

public class PvuCalculoLCxProductoCurBean {
	private Integer PRODUCTO_COD;
	private String DESCRIPCION;
	private Integer PRODUCTO_LC;
	
	public Integer getProducto_cod() {
		return PRODUCTO_COD;
	}
	public void setProducto_cod(Integer producto_cod) {
		this.PRODUCTO_COD = producto_cod;
	}
	public String getDescripcion() {
		return DESCRIPCION;
	}
	public void setDescripcion(String descripcion) {
		this.DESCRIPCION = descripcion;
	}
	public Integer getProducto_lc() {
		return PRODUCTO_LC;
	}
	public void setProducto_lc(Integer producto_lc) {
		this.PRODUCTO_LC = producto_lc;
	}
}
