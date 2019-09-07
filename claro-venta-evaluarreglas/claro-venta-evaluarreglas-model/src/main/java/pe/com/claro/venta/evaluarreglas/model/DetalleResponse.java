package pe.com.claro.venta.evaluarreglas.model;

import java.math.BigDecimal;

public class DetalleResponse {
	private String cuenta;
	private String plan;
	private String planSisAct;
	private String telefono;
	private String servicio;
	private BigDecimal cargoFijo;
	private BigDecimal solinSumCarCon;
	private String fechaActivacion;
	private String fechaEstado;
	private String estado;
	private String motivoBloqueo;
	private String motivoSuspension;
	private String campana;
	private BigDecimal planBSCS;
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getPlanSisAct() {
		return planSisAct;
	}
	public void setPlanSisAct(String planSisAct) {
		this.planSisAct = planSisAct;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public BigDecimal getCargoFijo() {
		return cargoFijo;
	}
	public void setCargoFijo(BigDecimal cargoFijo) {
		this.cargoFijo = cargoFijo;
	}

	public BigDecimal getSolinSumCarCon() {
		return solinSumCarCon;
	}
	public void setSolinSumCarCon(BigDecimal solinSumCarCon) {
		this.solinSumCarCon = solinSumCarCon;
	}
	public String getFechaActivacion() {
		return fechaActivacion;
	}
	public void setFechaActivacion(String fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}
	public String getFechaEstado() {
		return fechaEstado;
	}
	public void setFechaEstado(String fechaEstado) {
		this.fechaEstado = fechaEstado;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getMotivoBloqueo() {
		return motivoBloqueo;
	}
	public void setMotivoBloqueo(String motivoBloqueo) {
		this.motivoBloqueo = motivoBloqueo;
	}
	public String getMotivoSuspension() {
		return motivoSuspension;
	}
	public void setMotivoSuspension(String motivoSuspension) {
		this.motivoSuspension = motivoSuspension;
	}

	public String getCampana() {
		return campana;
	}
	public void setCampana(String campana) {
		this.campana = campana;
	}
	public BigDecimal getPlanBSCS() {
		return planBSCS;
	}
	public void setPlanBSCS(BigDecimal planBSCS) {
		this.planBSCS = planBSCS;
	}
	@Override
	public String toString() {
		return "DetalleResponse [cuenta=" + cuenta + ", plan=" + plan + ", planSisAct=" + planSisAct + ", telefono="
				+ telefono + ", servicio=" + servicio + ", cargoFijo=" + cargoFijo + ", solinSumCarCon="
				+ solinSumCarCon + ", fechaActivacion=" + fechaActivacion + ", fechaEstado=" + fechaEstado + ", estado="
				+ estado + ", motivoBloqueo=" + motivoBloqueo + ", motivoSuspension=" + motivoSuspension + ", campana="
				+ campana + ", planBSCS=" + planBSCS + "]";
	}

	
	
}
