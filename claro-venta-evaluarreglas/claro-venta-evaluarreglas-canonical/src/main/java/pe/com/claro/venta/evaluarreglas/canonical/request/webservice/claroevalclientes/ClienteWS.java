package pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes;

import java.io.Serializable;
import java.util.List;

public class ClienteWS implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int cantidadDeLineasActivas;
	private int cantidadDePlanesPorProducto;
	private int cantidadDeRepresentantesLegales;
	private String capacidadDePago;
	private int comportamientoConsolidado;
	private int comportamientoDePago;
	private int comportamientoDePagoC1;
	private double creditScore;
	private int creditScoreEntero;
	private String deuda;
	private DireccionWS direccionWS;
	private int edad;
	private double factorDeEndeudamiento;
	private double factorDeRenovacion;
	private double facturacionPromedioClaro;
	private double facturacionPromedioProducto;
	private double limiteDeCreditoDisponible;
	private String riesgo;
	private String riesgoClaro;
	private String riesgoTotalRepresentantesLegales;
	private String sexo;
	private int tiempoDePermanencia;
	private String tipo;
	public int getCantidadDeLineasActivas() {
		return cantidadDeLineasActivas;
	}
	public void setCantidadDeLineasActivas(int cantidadDeLineasActivas) {
		this.cantidadDeLineasActivas = cantidadDeLineasActivas;
	}
	public int getCantidadDePlanesPorProducto() {
		return cantidadDePlanesPorProducto;
	}
	public void setCantidadDePlanesPorProducto(int cantidadDePlanesPorProducto) {
		this.cantidadDePlanesPorProducto = cantidadDePlanesPorProducto;
	}
	public int getCantidadDeRepresentantesLegales() {
		return cantidadDeRepresentantesLegales;
	}
	public void setCantidadDeRepresentantesLegales(int cantidadDeRepresentantesLegales) {
		this.cantidadDeRepresentantesLegales = cantidadDeRepresentantesLegales;
	}
	public String getCapacidadDePago() {
		return capacidadDePago;
	}
	public void setCapacidadDePago(String capacidadDePago) {
		this.capacidadDePago = capacidadDePago;
	}
	public int getComportamientoConsolidado() {
		return comportamientoConsolidado;
	}
	public void setComportamientoConsolidado(int comportamientoConsolidado) {
		this.comportamientoConsolidado = comportamientoConsolidado;
	}
	public int getComportamientoDePago() {
		return comportamientoDePago;
	}
	public void setComportamientoDePago(int comportamientoDePago) {
		this.comportamientoDePago = comportamientoDePago;
	}
	public int getComportamientoDePagoC1() {
		return comportamientoDePagoC1;
	}
	public void setComportamientoDePagoC1(int comportamientoDePagoC1) {
		this.comportamientoDePagoC1 = comportamientoDePagoC1;
	}
	public double getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(double creditScore) {
		this.creditScore = creditScore;
	}
	public int getCreditScoreEntero() {
		return creditScoreEntero;
	}
	public void setCreditScoreEntero(int creditScoreEntero) {
		this.creditScoreEntero = creditScoreEntero;
	}
	public String getDeuda() {
		return deuda;
	}
	public void setDeuda(String deuda) {
		this.deuda = deuda;
	}
	public DireccionWS getDireccion() {
		return direccionWS;
	}
	public void setDireccion(DireccionWS direccionWS) {
		this.direccionWS = direccionWS;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public double getFactorDeEndeudamiento() {
		return factorDeEndeudamiento;
	}
	public void setFactorDeEndeudamiento(double factorDeEndeudamiento) {
		this.factorDeEndeudamiento = factorDeEndeudamiento;
	}
	public double getFactorDeRenovacion() {
		return factorDeRenovacion;
	}
	public void setFactorDeRenovacion(double factorDeRenovacion) {
		this.factorDeRenovacion = factorDeRenovacion;
	}
	public double getFacturacionPromedioClaro() {
		return facturacionPromedioClaro;
	}
	public void setFacturacionPromedioClaro(double facturacionPromedioClaro) {
		this.facturacionPromedioClaro = facturacionPromedioClaro;
	}
	public double getFacturacionPromedioProducto() {
		return facturacionPromedioProducto;
	}
	public void setFacturacionPromedioProducto(double facturacionPromedioProducto) {
		this.facturacionPromedioProducto = facturacionPromedioProducto;
	}
	public double getLimiteDeCreditoDisponible() {
		return limiteDeCreditoDisponible;
	}
	public void setLimiteDeCreditoDisponible(double limiteDeCreditoDisponible) {
		this.limiteDeCreditoDisponible = limiteDeCreditoDisponible;
	}
	public String getRiesgo() {
		return riesgo;
	}
	public void setRiesgo(String riesgo) {
		this.riesgo = riesgo;
	}
	public String getRiesgoClaro() {
		return riesgoClaro;
	}
	public void setRiesgoClaro(String riesgoClaro) {
		this.riesgoClaro = riesgoClaro;
	}
	public String getRiesgoTotalRepresentantesLegales() {
		return riesgoTotalRepresentantesLegales;
	}
	public void setRiesgoTotalRepresentantesLegales(String riesgoTotalRepresentantesLegales) {
		this.riesgoTotalRepresentantesLegales = riesgoTotalRepresentantesLegales;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public int getTiempoDePermanencia() {
		return tiempoDePermanencia;
	}
	public void setTiempoDePermanencia(int tiempoDePermanencia) {
		this.tiempoDePermanencia = tiempoDePermanencia;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
		  
}
