package pe.com.claro.venta.evaluarreglas.model;

import java.math.BigDecimal;
import java.util.Date;

public class DatosResponse {
	private String codigo;
	private String pdv;
	private String canal;
	private String departamento;
	private String provincia;
	private String distrito;
	private String region;
	private String calidadVendedor;
	private String buroDes;
	private BigDecimal factorSubMenor;
	private BigDecimal factorSubMayor;
	private Date sopodFecActivacion;
	private BigDecimal numeroOperacion;
	private String riesgo;
	private String puntaje;
	private BigDecimal edad;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getPdv() {
		return pdv;
	}
	public void setPdv(String pdv) {
		this.pdv = pdv;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCalidadVendedor() {
		return calidadVendedor;
	}
	public void setCalidadVendedor(String calidadVendedor) {
		this.calidadVendedor = calidadVendedor;
	}
	public String getBuroDes() {
		return buroDes;
	}
	public void setBuroDes(String buroDes) {
		this.buroDes = buroDes;
	}
	public BigDecimal getFactorSubMenor() {
		return factorSubMenor;
	}
	public void setFactorSubMenor(BigDecimal factorSubMenor) {
		this.factorSubMenor = factorSubMenor;
	}
	public BigDecimal getFactorSubMayor() {
		return factorSubMayor;
	}
	public void setFactorSubMayor(BigDecimal factorSubMayor) {
		this.factorSubMayor = factorSubMayor;
	}
	public Date getSopodFecActivacion() {
		return sopodFecActivacion;
	}
	public void setSopodFecActivacion(Date sopodFecActivacion) {
		this.sopodFecActivacion = sopodFecActivacion;
	}
	public BigDecimal getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(BigDecimal numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	public String getRiesgo() {
		return riesgo;
	}
	public void setRiesgo(String riesgo) {
		this.riesgo = riesgo;
	}
	public String getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(String puntaje) {
		this.puntaje = puntaje;
	}
	public BigDecimal getEdad() {
		return edad;
	}
	public void setEdad(BigDecimal edad) {
		this.edad = edad;
	}

}
