package pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes;

public class RespClaroEvalPlanSolic {

    private String descripcionPlan;
    private double cargoFijo;
    private String restriccion;
    private int cantidadDeLineasMaximas;
    private double montoDeGarantia;
    private int cantidadDeAplicacionesRenta;
    
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
	public int getCantidadDeLineasMaximas() {
		return cantidadDeLineasMaximas;
	}
	public void setCantidadDeLineasMaximas(int cantidadDeLineasMaximas) {
		this.cantidadDeLineasMaximas = cantidadDeLineasMaximas;
	}
	public double getMontoDeGarantia() {
		return montoDeGarantia;
	}
	public void setMontoDeGarantia(double montoDeGarantia) {
		this.montoDeGarantia = montoDeGarantia;
	}
	public int getCantidadDeAplicacionesRenta() {
		return cantidadDeAplicacionesRenta;
	}
	public void setCantidadDeAplicacionesRenta(int cantidadDeAplicacionesRenta) {
		this.cantidadDeAplicacionesRenta = cantidadDeAplicacionesRenta;
	}
    
    
    
}
