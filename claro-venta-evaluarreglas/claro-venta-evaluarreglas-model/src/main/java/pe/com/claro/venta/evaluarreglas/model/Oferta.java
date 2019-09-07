package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;

public class Oferta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Campana campana;
	private int cantidadDeDecos;
	private int cantidadLineasSEC;
	private String casoEpecial;
	private String combo;
	private String controlDeConsumo;
	private String kitDeInstalacion;
	private int mesOperadorCedente;
	private String modalidadCedente;
	private double montoCFSEC;
	private String operadorCedente;
	private PlanActual planActual;
	private PlanSolicitado planSolicitado;
	private String plazoDeAcuerdo;
	private String productoComercial;
	private TipoSiNo proteccionMovil;
	private String riesgo;
	private String segmentoDeOferta;
	private String tipoDeOperacionEmpresa;
	private String tipoDeProducto;
	public Campana getCampana() {
		return campana;
	}
	public void setCampana(Campana campana) {
		this.campana = campana;
	}
	public int getCantidadDeDecos() {
		return cantidadDeDecos;
	}
	public void setCantidadDeDecos(int cantidadDeDecos) {
		this.cantidadDeDecos = cantidadDeDecos;
	}
	public int getCantidadLineasSEC() {
		return cantidadLineasSEC;
	}
	public void setCantidadLineasSEC(int cantidadLineasSEC) {
		this.cantidadLineasSEC = cantidadLineasSEC;
	}
	public String getCasoEpecial() {
		return casoEpecial;
	}
	public void setCasoEpecial(String casoEpecial) {
		this.casoEpecial = casoEpecial;
	}
	public String getCombo() {
		return combo;
	}
	public void setCombo(String combo) {
		this.combo = combo;
	}
	public String getControlDeConsumo() {
		return controlDeConsumo;
	}
	public void setControlDeConsumo(String controlDeConsumo) {
		this.controlDeConsumo = controlDeConsumo;
	}
	public String getKitDeInstalacion() {
		return kitDeInstalacion;
	}
	public void setKitDeInstalacion(String kitDeInstalacion) {
		this.kitDeInstalacion = kitDeInstalacion;
	}
	public int getMesOperadorCedente() {
		return mesOperadorCedente;
	}
	public void setMesOperadorCedente(int mesOperadorCedente) {
		this.mesOperadorCedente = mesOperadorCedente;
	}
	public String getModalidadCedente() {
		return modalidadCedente;
	}
	public void setModalidadCedente(String modalidadCedente) {
		this.modalidadCedente = modalidadCedente;
	}
	public double getMontoCFSEC() {
		return montoCFSEC;
	}
	public void setMontoCFSEC(double montoCFSEC) {
		this.montoCFSEC = montoCFSEC;
	}
	public String getOperadorCedente() {
		return operadorCedente;
	}
	public void setOperadorCedente(String operadorCedente) {
		this.operadorCedente = operadorCedente;
	}
	public PlanActual getPlanActual() {
		return planActual;
	}
	public void setPlanActual(PlanActual planActual) {
		this.planActual = planActual;
	}
	public PlanSolicitado getPlanSolicitado() {
		return planSolicitado;
	}
	public void setPlanSolicitado(PlanSolicitado planSolicitado) {
		this.planSolicitado = planSolicitado;
	}
	public String getPlazoDeAcuerdo() {
		return plazoDeAcuerdo;
	}
	public void setPlazoDeAcuerdo(String plazoDeAcuerdo) {
		this.plazoDeAcuerdo = plazoDeAcuerdo;
	}
	public String getProductoComercial() {
		return productoComercial;
	}
	public void setProductoComercial(String productoComercial) {
		this.productoComercial = productoComercial;
	}
	public TipoSiNo getProteccionMovil() {
		return proteccionMovil;
	}
	public void setProteccionMovil(TipoSiNo proteccionMovil) {
		this.proteccionMovil = proteccionMovil;
	}
	public String getRiesgo() {
		return riesgo;
	}
	public void setRiesgo(String riesgo) {
		this.riesgo = riesgo;
	}
	public String getSegmentoDeOferta() {
		return segmentoDeOferta;
	}
	public void setSegmentoDeOferta(String segmentoDeOferta) {
		this.segmentoDeOferta = segmentoDeOferta;
	}
	public String getTipoDeOperacionEmpresa() {
		return tipoDeOperacionEmpresa;
	}
	public void setTipoDeOperacionEmpresa(String tipoDeOperacionEmpresa) {
		this.tipoDeOperacionEmpresa = tipoDeOperacionEmpresa;
	}
	public String getTipoDeProducto() {
		return tipoDeProducto;
	}
	public void setTipoDeProducto(String tipoDeProducto) {
		this.tipoDeProducto = tipoDeProducto;
	}
	
	
}
