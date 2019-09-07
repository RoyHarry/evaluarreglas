package pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes;

import java.io.Serializable;

public class DireccionWS implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  String codigoDePlano;
	private  String departamento;
	private  String distrito;
	private  String provincia;
	private  String region;
	public String getCodigoDePlano() {
		return codigoDePlano;
	}
	public void setCodigoDePlano(String codigoDePlano) {
		this.codigoDePlano = codigoDePlano;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	
}
