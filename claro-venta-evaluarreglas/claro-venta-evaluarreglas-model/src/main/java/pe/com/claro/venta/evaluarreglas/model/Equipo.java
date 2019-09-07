package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;

public class Equipo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double costo;
	private int cuotas;
	private double factorDePagoInicial;
	private double factorDeSubsidio;
	private String formaDePago;
	private String gama;
	private String grupo;
	private String modelo;
	private double montoDeCuota;
	private double porcentajecuotaInicial;
	private double precioDeVenta;
	private int riesgo;
	private String tipoDeDeco;
	private String tipoOperacionKit;
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	public int getCuotas() {
		return cuotas;
	}
	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}
	public double getFactorDePagoInicial() {
		return factorDePagoInicial;
	}
	public void setFactorDePagoInicial(double factorDePagoInicial) {
		this.factorDePagoInicial = factorDePagoInicial;
	}
	public double getFactorDeSubsidio() {
		return factorDeSubsidio;
	}
	public void setFactorDeSubsidio(double factorDeSubsidio) {
		this.factorDeSubsidio = factorDeSubsidio;
	}
	public String getFormaDePago() {
		return formaDePago;
	}
	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}
	public String getGama() {
		return gama;
	}
	public void setGama(String gama) {
		this.gama = gama;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public double getMontoDeCuota() {
		return montoDeCuota;
	}
	public void setMontoDeCuota(double montoDeCuota) {
		this.montoDeCuota = montoDeCuota;
	}
	public double getPorcentajecuotaInicial() {
		return porcentajecuotaInicial;
	}
	public void setPorcentajecuotaInicial(double porcentajecuotaInicial) {
		this.porcentajecuotaInicial = porcentajecuotaInicial;
	}
	public double getPrecioDeVenta() {
		return precioDeVenta;
	}
	public void setPrecioDeVenta(double precioDeVenta) {
		this.precioDeVenta = precioDeVenta;
	}
	public int getRiesgo() {
		return riesgo;
	}
	public void setRiesgo(int riesgo) {
		this.riesgo = riesgo;
	}
	public String getTipoDeDeco() {
		return tipoDeDeco;
	}
	public void setTipoDeDeco(String tipoDeDeco) {
		this.tipoDeDeco = tipoDeDeco;
	}
	public String getTipoOperacionKit() {
		return tipoOperacionKit;
	}
	public void setTipoOperacionKit(String tipoOperacionKit) {
		this.tipoOperacionKit = tipoOperacionKit;
	}
	
}
