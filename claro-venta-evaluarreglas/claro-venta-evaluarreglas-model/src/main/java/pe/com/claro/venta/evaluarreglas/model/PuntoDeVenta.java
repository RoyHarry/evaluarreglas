package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;

public class PuntoDeVenta implements Serializable{
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String calidadDeVendedor;
	private String canal;
	private String codigo;
	private String descripcion;
	private Direccion direccion;
	private String grupo;
	private boolean riesgoso;
	public String getCalidadDeVendedor() {
		return calidadDeVendedor;
	}
	public void setCalidadDeVendedor(String calidadDeVendedor) {
		this.calidadDeVendedor = calidadDeVendedor;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public boolean isRiesgoso() {
		return riesgoso;
	}
	public void setRiesgoso(boolean riesgoso) {
		this.riesgoso = riesgoso;
	}

}
