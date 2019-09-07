package pe.com.claro.venta.evaluarreglas.model;

public class Ofrecimiento {

	private Autonomia autonomia;
	private String controlDeConsumo;
	private double costoDeInstalacion;
	private Garantia garantia;
	private double limiteDeCreditoCobranza;
	private double montoTopeAutomatico;
	private OpcionDeCuotas opcionDeCuotas;
	private TipoSiNo prioridadPublicar;
	private TipoSiNo procesoDeExoneracionDeRentas;
	private String procesoIDValidator;
	private String procesoValidacionInternaClaro;
	private TipoSiNo publicar;
	private TipoSiNo restriccion;
	private ResultadosAdicionales resultadosAdicionales;
	public Autonomia getAutonomia() {
		return autonomia;
	}
	public void setAutonomia(Autonomia autonomia) {
		this.autonomia = autonomia;
	}
	public String getControlDeConsumo() {
		return controlDeConsumo;
	}
	public void setControlDeConsumo(String controlDeConsumo) {
		this.controlDeConsumo = controlDeConsumo;
	}
	public double getCostoDeInstalacion() {
		return costoDeInstalacion;
	}
	public void setCostoDeInstalacion(double costoDeInstalacion) {
		this.costoDeInstalacion = costoDeInstalacion;
	}
	public Garantia getGarantia() {
		return garantia;
	}
	public void setGarantia(Garantia garantia) {
		this.garantia = garantia;
	}
	public double getLimiteDeCreditoCobranza() {
		return limiteDeCreditoCobranza;
	}
	public void setLimiteDeCreditoCobranza(double limiteDeCreditoCobranza) {
		this.limiteDeCreditoCobranza = limiteDeCreditoCobranza;
	}
	public double getMontoTopeAutomatico() {
		return montoTopeAutomatico;
	}
	public void setMontoTopeAutomatico(double montoTopeAutomatico) {
		this.montoTopeAutomatico = montoTopeAutomatico;
	}
	public OpcionDeCuotas getOpcionDeCuotas() {
		return opcionDeCuotas;
	}
	public void setOpcionDeCuotas(OpcionDeCuotas opcionDeCuotas) {
		this.opcionDeCuotas = opcionDeCuotas;
	}
	public TipoSiNo getPrioridadPublicar() {
		return prioridadPublicar;
	}
	public void setPrioridadPublicar(TipoSiNo prioridadPublicar) {
		this.prioridadPublicar = prioridadPublicar;
	}
	public TipoSiNo getProcesoDeExoneracionDeRentas() {
		return procesoDeExoneracionDeRentas;
	}
	public void setProcesoDeExoneracionDeRentas(TipoSiNo procesoDeExoneracionDeRentas) {
		this.procesoDeExoneracionDeRentas = procesoDeExoneracionDeRentas;
	}
	public String getProcesoIDValidator() {
		return procesoIDValidator;
	}
	public void setProcesoIDValidator(String procesoIDValidator) {
		this.procesoIDValidator = procesoIDValidator;
	}
	public String getProcesoValidacionInternaClaro() {
		return procesoValidacionInternaClaro;
	}
	public void setProcesoValidacionInternaClaro(String procesoValidacionInternaClaro) {
		this.procesoValidacionInternaClaro = procesoValidacionInternaClaro;
	}
	public TipoSiNo getPublicar() {
		return publicar;
	}
	public void setPublicar(TipoSiNo publicar) {
		this.publicar = publicar;
	}
	public TipoSiNo getRestriccion() {
		return restriccion;
	}
	public void setRestriccion(TipoSiNo restriccion) {
		this.restriccion = restriccion;
	}
	public ResultadosAdicionales getResultadosAdicionales() {
		return resultadosAdicionales;
	}
	public void setResultadosAdicionales(ResultadosAdicionales resultadosAdicionales) {
		this.resultadosAdicionales = resultadosAdicionales;
	}
}
