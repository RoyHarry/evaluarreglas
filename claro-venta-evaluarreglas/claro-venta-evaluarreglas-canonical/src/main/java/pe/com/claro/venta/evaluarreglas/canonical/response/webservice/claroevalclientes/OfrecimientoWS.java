package pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes;

import java.util.List;

import pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes.TipoSiNo;

public class OfrecimientoWS {

	private AutonomiaWS autonomiaWS;
	private String controlDeConsumo;
	private double costoDeInstalacion;
	private List<CuotaWS> cuotaWS;
	private GarantiaWS garantiaWS;
	private double limiteDeCreditoCobranza;
	private double montoTopeAutomatico;
	private OpcionDeCuotasWS opcionDeCuotasWS;
	private TipoSiNo prioridadPublicar;
	private TipoSiNo procesoDeExoneracionDeRentas;
	private String procesoIDValidator;
	private String procesoValidacionInternaClaro;
	private TipoSiNo publicar;
	private TipoSiNo restriccion;
	private ResultadosAdicionalesWS resultadosAdicionalesWS;
	private List<RespuestaProactivaWS> vlistaplan;
	private Double porcentajeMinimoCuota;
	private Double porcentajeMaximoCuota;
	private String deuda;
	
	public AutonomiaWS getAutonomia() {
		return autonomiaWS;
	}
	public void setAutonomia(AutonomiaWS autonomiaWS) {
		this.autonomiaWS = autonomiaWS;
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
	public List<CuotaWS> getCuota() {
		return cuotaWS;
	}
	public void setCuota(List<CuotaWS> cuotaWS) {
		this.cuotaWS = cuotaWS;
	}
	public GarantiaWS getGarantia() {
		return garantiaWS;
	}
	public void setGarantia(GarantiaWS garantiaWS) {
		this.garantiaWS = garantiaWS;
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
	public OpcionDeCuotasWS getOpcionDeCuotas() {
		return opcionDeCuotasWS;
	}
	public void setOpcionDeCuotas(OpcionDeCuotasWS opcionDeCuotasWS) {
		this.opcionDeCuotasWS = opcionDeCuotasWS;
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
	public ResultadosAdicionalesWS getResultadosAdicionales() {
		return resultadosAdicionalesWS;
	}
	public void setResultadosAdicionales(ResultadosAdicionalesWS resultadosAdicionalesWS) {
		this.resultadosAdicionalesWS = resultadosAdicionalesWS;
	}
	public List<RespuestaProactivaWS> getVlistaplan() {
		return vlistaplan;
	}
	public void setVlistaplan(List<RespuestaProactivaWS> vlistaplan) {
		this.vlistaplan = vlistaplan;
	}
	public Double getPorcentajeMinimoCuota() {
		return porcentajeMinimoCuota;
	}
	public void setPorcentajeMinimoCuota(Double porcentajeMinimoCuota) {
		this.porcentajeMinimoCuota = porcentajeMinimoCuota;
	}
	public Double getPorcentajeMaximoCuota() {
		return porcentajeMaximoCuota;
	}
	public void setPorcentajeMaximoCuota(Double porcentajeMaximoCuota) {
		this.porcentajeMaximoCuota = porcentajeMaximoCuota;
	}
	public String getDeuda() {
		return deuda;
	}
	public void setDeuda(String deuda) {
		this.deuda = deuda;
	}
	
	
}
