package pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes;

import pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes.TipoSiNo;

public class RespuestaProactivaWS {
	  private int cantidadDeLineasAdicionalesRUC;
	  private int cantidadDeLineasMaximas;
	  private String capacidadDePago;
	  private CuotaWS cuotaWS;
	  private String descripcion;
	  private double factorDeEndeudamientoCliente;
	  private double factorDeRenovacionCliente;
	  private double montoCFParaRUC;
	  private double montoDeGarantia;
	  private double precioDeVentaTotalEquipos;
	  private String procesoIDValidator;
	  private TipoSiNo restriccion;
	  private String riesgoOferta;
	  private int riesgoTotalEquipo;
	  private String tipoDeAutonomiaCargoFijo;
	  private TipoDeCobro tipodecobro;
	public int getCantidadDeLineasAdicionalesRUC() {
		return cantidadDeLineasAdicionalesRUC;
	}
	public void setCantidadDeLineasAdicionalesRUC(int cantidadDeLineasAdicionalesRUC) {
		this.cantidadDeLineasAdicionalesRUC = cantidadDeLineasAdicionalesRUC;
	}
	public int getCantidadDeLineasMaximas() {
		return cantidadDeLineasMaximas;
	}
	public void setCantidadDeLineasMaximas(int cantidadDeLineasMaximas) {
		this.cantidadDeLineasMaximas = cantidadDeLineasMaximas;
	}
	public String getCapacidadDePago() {
		return capacidadDePago;
	}
	public void setCapacidadDePago(String capacidadDePago) {
		this.capacidadDePago = capacidadDePago;
	}
	public CuotaWS getCuota() {
		return cuotaWS;
	}
	public void setCuota(CuotaWS cuotaWS) {
		this.cuotaWS = cuotaWS;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getFactorDeEndeudamientoCliente() {
		return factorDeEndeudamientoCliente;
	}
	public void setFactorDeEndeudamientoCliente(double factorDeEndeudamientoCliente) {
		this.factorDeEndeudamientoCliente = factorDeEndeudamientoCliente;
	}
	public double getFactorDeRenovacionCliente() {
		return factorDeRenovacionCliente;
	}
	public void setFactorDeRenovacionCliente(double factorDeRenovacionCliente) {
		this.factorDeRenovacionCliente = factorDeRenovacionCliente;
	}
	public double getMontoCFParaRUC() {
		return montoCFParaRUC;
	}
	public void setMontoCFParaRUC(double montoCFParaRUC) {
		this.montoCFParaRUC = montoCFParaRUC;
	}
	public double getMontoDeGarantia() {
		return montoDeGarantia;
	}
	public void setMontoDeGarantia(double montoDeGarantia) {
		this.montoDeGarantia = montoDeGarantia;
	}
	public double getPrecioDeVentaTotalEquipos() {
		return precioDeVentaTotalEquipos;
	}
	public void setPrecioDeVentaTotalEquipos(double precioDeVentaTotalEquipos) {
		this.precioDeVentaTotalEquipos = precioDeVentaTotalEquipos;
	}
	public String getProcesoIDValidator() {
		return procesoIDValidator;
	}
	public void setProcesoIDValidator(String procesoIDValidator) {
		this.procesoIDValidator = procesoIDValidator;
	}
	public TipoSiNo getRestriccion() {
		return restriccion;
	}
	public void setRestriccion(TipoSiNo restriccion) {
		this.restriccion = restriccion;
	}
	public String getRiesgoOferta() {
		return riesgoOferta;
	}
	public void setRiesgoOferta(String riesgoOferta) {
		this.riesgoOferta = riesgoOferta;
	}
	public int getRiesgoTotalEquipo() {
		return riesgoTotalEquipo;
	}
	public void setRiesgoTotalEquipo(int riesgoTotalEquipo) {
		this.riesgoTotalEquipo = riesgoTotalEquipo;
	}
	public String getTipoDeAutonomiaCargoFijo() {
		return tipoDeAutonomiaCargoFijo;
	}
	public void setTipoDeAutonomiaCargoFijo(String tipoDeAutonomiaCargoFijo) {
		this.tipoDeAutonomiaCargoFijo = tipoDeAutonomiaCargoFijo;
	}
	public TipoDeCobro getTipodecobro() {
		return tipodecobro;
	}
	public void setTipodecobro(TipoDeCobro tipodecobro) {
		this.tipodecobro = tipodecobro;
	}
	  
	  
}
