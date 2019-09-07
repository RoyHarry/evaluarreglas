package pe.com.claro.venta.evaluarreglas.model;

public class Garantia {
	
	  private int cantidadDeAplicacionesRenta;
	  private int frecuenciaDeAplicacionMensual;
	  private int mesInicioRentas;
	  private double montoDeGarantia;
	  private String tipoDeGarantia;
	  private TipoDeCobro tipodecobro;
	public int getCantidadDeAplicacionesRenta() {
		return cantidadDeAplicacionesRenta;
	}
	public void setCantidadDeAplicacionesRenta(int cantidadDeAplicacionesRenta) {
		this.cantidadDeAplicacionesRenta = cantidadDeAplicacionesRenta;
	}
	public int getFrecuenciaDeAplicacionMensual() {
		return frecuenciaDeAplicacionMensual;
	}
	public void setFrecuenciaDeAplicacionMensual(int frecuenciaDeAplicacionMensual) {
		this.frecuenciaDeAplicacionMensual = frecuenciaDeAplicacionMensual;
	}
	public int getMesInicioRentas() {
		return mesInicioRentas;
	}
	public void setMesInicioRentas(int mesInicioRentas) {
		this.mesInicioRentas = mesInicioRentas;
	}
	public double getMontoDeGarantia() {
		return montoDeGarantia;
	}
	public void setMontoDeGarantia(double montoDeGarantia) {
		this.montoDeGarantia = montoDeGarantia;
	}
	public String getTipoDeGarantia() {
		return tipoDeGarantia;
	}
	public void setTipoDeGarantia(String tipoDeGarantia) {
		this.tipoDeGarantia = tipoDeGarantia;
	}
	public TipoDeCobro getTipodecobro() {
		return tipodecobro;
	}
	public void setTipodecobro(TipoDeCobro tipodecobro) {
		this.tipodecobro = tipodecobro;
	}
	  
	  
}
