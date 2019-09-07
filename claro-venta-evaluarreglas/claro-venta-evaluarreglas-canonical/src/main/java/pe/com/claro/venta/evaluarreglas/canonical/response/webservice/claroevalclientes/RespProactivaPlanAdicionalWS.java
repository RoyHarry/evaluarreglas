package pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes;

import java.math.BigInteger;

public class RespProactivaPlanAdicionalWS {

    private String descripcionPlan;
    
    private double cargoFijo;

    private String restriccion;
    
    private double montoRA;

    private BigInteger cantidadDeLineasMaximas;

	public String getDescripcionPlan() {
		return descripcionPlan;
	}

	public void setDescripcionPlan(String descripcionPlan) {
		this.descripcionPlan = descripcionPlan;
	}

	public double getCargoFijo() {
		return cargoFijo;
	}

	public void setCargoFijo(double cargoFijo) {
		this.cargoFijo = cargoFijo;
	}

	public String getRestriccion() {
		return restriccion;
	}

	public void setRestriccion(String restriccion) {
		this.restriccion = restriccion;
	}

	public double getMontoRA() {
		return montoRA;
	}

	public void setMontoRA(double montoRA) {
		this.montoRA = montoRA;
	}

	public BigInteger getCantidadDeLineasMaximas() {
		return cantidadDeLineasMaximas;
	}

	public void setCantidadDeLineasMaximas(BigInteger cantidadDeLineasMaximas) {
		this.cantidadDeLineasMaximas = cantidadDeLineasMaximas;
	}
    
    
}
